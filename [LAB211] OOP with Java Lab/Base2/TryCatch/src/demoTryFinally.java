public class demoTryFinally{
    public static void main(String[] args){
        try{
            int data=5/0;
        } finally{
            System.out.println("Khối lệnh finally luôn được");
        }
        System.out.println("Finished!");
    }
}
