public class demoTryCatchFinally{
    public static void main(String[] args){
        try{
            int data=5/0;
        } catch(ArithmeticException e){
            System.out.println(e);
        } finally{
            System.out.println("Khối lệnh finally luôn được thực hiện");
        }
        System.out.println("Finished!");
    }
}
