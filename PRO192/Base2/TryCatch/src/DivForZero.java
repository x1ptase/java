public class DivForZero{
    public static void main(String[] args){
        try{
            int data=5/0;
        } catch(Exception e){
            System.out.println(e);
        }
        System.out.println("Phép chia cho 0");
    }
}
