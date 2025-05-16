public class demoTryCatch{
    public static void main(String[] args){
        try{
            // code gây ra nhiều loại ngoại lệ khác nhau
        } catch(NullPointerException e){
            System.out.println(e);
        } catch(ArithmeticException e){
            // các khối catch khác
        } 
        System.out.println("Finished!");
    }
}
