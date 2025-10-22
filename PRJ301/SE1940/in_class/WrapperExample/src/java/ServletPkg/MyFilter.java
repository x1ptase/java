package ServletPkg;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class MyFilter implements Filter {
    public MyFilter() {
    }    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException{
        try{
            RequestWrapper requestWrapper=new RequestWrapper((HttpServletRequest) request);
            ResponseWrapper responseWrapper=new ResponseWrapper((HttpServletResponse) response);
            chain.doFilter(requestWrapper, responseWrapper);
            PrintWriter out=response.getWriter();
            String alteredContent=responseWrapper.getAlteredContent();
            response.setContentLength(alteredContent.length());
            out.write(alteredContent);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void destroy() {        
    }

    public void init(FilterConfig filterConfig) {        
    } 
    
    class RequestWrapper extends HttpServletRequestWrapper{
        public RequestWrapper(HttpServletRequest request){
            super(request);
        }
        @Override
        public String getParameter(String name){
            String userName=super.getParameter(name);
            if(userName != null){
                if(userName.isEmpty()){
                    userName="Data invalid: please enter user name.";
                } else{
                    userName=userName.toUpperCase();
                }
            }
            return userName;
        }
    }
    
    class ResponseWrapper extends HttpServletResponseWrapper{
        private CharArrayWriter writer;
        public ResponseWrapper(HttpServletResponse response) throws IOException {
            super(response);
            writer=new CharArrayWriter();
        }
        public String getAlteredContent() throws IOException {
            String alteredContent=null;
            if(this.getContentType().contains("text/html")) {
                CharArrayWriter charWriter=new CharArrayWriter();
                String originalContent=this.toString();
                int indexOfCloseBodyTag=originalContent.indexOf("</body>") - 1;
                charWriter.write(originalContent.substring(0, indexOfCloseBodyTag));
                String message="<p style='color: blue'><b>Request-Response Wrapper Example</b></p>";
                String closeHTMLTags="</body></html>";
                charWriter.write(message);
                charWriter.write(closeHTMLTags);
                alteredContent=charWriter.toString();
            }
            return alteredContent;
        }

        @Override
        public PrintWriter getWriter() {
            return new PrintWriter(writer);
        }
        @Override
        public String toString() {
            return writer.toString();
        }
    }
}
