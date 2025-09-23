package controller;

import dal.PersonDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Person;

@WebServlet(name="EditServlet", urlPatterns={"/edit"})
public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            int id=Integer.parseInt(request.getParameter("id"));

            PersonDAO personDAO=new PersonDAO();
            Person person=personDAO.getPersonByID(id);

            request.setAttribute("person", person);
            request.getRequestDispatcher("edit.jsp").forward(request, response);
        } catch(SQLException ex){
            Logger.getLogger(EditServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            int id=Integer.parseInt(request.getParameter("id"));
            String name=request.getParameter("name");
            int age=Integer.parseInt(request.getParameter("age"));

            Person person=new Person(id, name, age);
            PersonDAO personDAO=new PersonDAO();
            personDAO.updatePerson(person);

            response.sendRedirect("list");
        } catch(SQLException ex){
            Logger.getLogger(EditServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex);
        }
    }
}
