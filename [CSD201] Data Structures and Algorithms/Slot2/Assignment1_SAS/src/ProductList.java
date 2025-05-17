
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PHAM KHAC VINH
 */
public class ProductList {

    static ProductNode head, tail;

    public static void writeFile(String fileName) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {

            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);
            //lap tu gia tri dau tien den gia tri cuoi cung cua mang chuoi
            ProductNode currentNode = head;
            while (currentNode != null) {
                bufferedWriter.write(currentNode.info.toString());
                bufferedWriter.newLine();
                currentNode = currentNode.next;

            }

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void readFromFile(String fileName) {
        File file = new File(fileName);
        ArrayList<Product> listProducts = new ArrayList<>();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.err.println("IO Exception phan file");
            }
        }
        Product product;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String lineString = "";
            while (true) {
                //doc du lieu cua 1 dong
                lineString = bufferedReader.readLine();
                if (lineString == null) {
                    break;
                }
                String[] txt = lineString.split("[||]+");
                //dong nay la doc duoc du lieu cua ID
                String pcode = txt[0].trim();
                //dong nay la doc du lieu cua Name
                String pro_name = txt[1].trim();
                int quantity = Integer.parseInt(txt[2].trim());
                int saled = Integer.parseInt(txt[3].trim());
                double price = Double.parseDouble(txt[4].trim());
                //tao ra doi tuong student voi ID va name doc duoc cua dong` do
                product = new Product(pcode, pro_name, quantity, saled, price);

                addLast(product);
                //add doi tuong student do vao list
                ;

            }
            traverse();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            try {
                fileReader.close();
                bufferedReader.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.err.println("File Not Found Exception in function " + "readFromFile");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println("IO Exception cua ham buffereader trong readFromFile");
        }

    }

    static ProductNode searchByString(String pCode) {
        ProductNode p = head;
        while (p != null) {
            if (p.info.pcode.equals(pCode)) {
                return (p);
            }
            p = p.next;
        }
        return (null);
    }

    static void addLast(Product x) {
        ProductNode qNode = new ProductNode(x);
        if (isEmpty()) {
            head = tail = qNode;
            return;
        }
        tail.next = qNode;
        tail = qNode;

    }

    static boolean isEmpty() {
        return (head == null);
    }

    static void traverse() {
        ProductNode currentNode = head;
        System.out.printf("%-10s | %-10s | %-10s | %-10s | %-10s | %-10s ",
                "code", "pro_name", "quantity", "saled", "price", "value");
        System.out.println("\n-------------------------------------------------------------------");
        while (currentNode != null) {
            System.out.println(currentNode.info.toString());
            currentNode = currentNode.next;
        }

    }

    static void inputAndAdd(String productstxt) {
        System.out.println("\nInput and Add !!!");
        String pCode;
        while (true) {
            pCode = GetInput.getString("Pcode: ", "Wrong", "[a-zA-Z0-9]+");
            if (!checkDuplicate(pCode)) {
                break;
            }
        }

        String pro_name = GetInput.getString("Pro_name: ", "Wrong", "[a-zA-Z0-9]+");
        int quantiy = GetInput.getInt("quantity: ", "Wrong", 0, Integer.MAX_VALUE);
        int saled = GetInput.getInt("Saled: ", "Wrong", 0, Integer.MAX_VALUE);
        double price = GetInput.getDouble("Price: ", "Wrong", 0, Double.MAX_VALUE);

        Product product = new Product(pCode, pro_name, quantiy, saled, price);
        addLast(product);
    }

    private static boolean checkDuplicate(String pCode) {
        ProductNode currentNode = head;

        while (currentNode != null) {
            //if duplicate => return true
            if (currentNode.info.pcode.equals(pCode)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    static void saveProduct() {
        String fileName = GetInput.getString("Input file name (.txt): ", "Wrong", "[a-zA-Z0-9.]+");
        writeFile(fileName);
    }

    static ProductNode searchByName(String xName) {
        ProductNode pNode = head;
        while (pNode != null) {
            if (pNode.info.pcode.equals(xName)) {
                return pNode;
            }
            pNode = pNode.next;
        }

        return null;
    }

    static void Search() {
        String name = GetInput.getString("Input name: ", "Wrong", "[a-zA-Z0-9]+");
        ProductNode pNode = head;
        while (pNode != null) {
            if (pNode.info.pro_name.equals(name)) {
                System.out.println(pNode.info.toString());
                return;
            }
            pNode = pNode.next;
        }

        System.out.println("NOT FOUND");
    }

    static void remove(ProductNode q) {
        if (q == null) {
            return;
        }
        if (q == head) { // xoa not dau tien
            removeFirst();
            return;

        }
        // tim node F truoc node q
        ProductNode fNode = head;
        while (fNode != null && fNode.next != q) {
            fNode = fNode.next;
        }
        if (fNode == null) { // q khong co trong list
            return;

        }
        // xoa q khoi list
        fNode.next = q.next;
        if (fNode.next == null) {
            tail = null;
        }

    }

    static void removeFirst() {
        if (isEmpty()) {
            return;
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }

    }

    static void sortByName() {
        ProductNode piNode, pjNode;
        Product temp;
        piNode = head;
        while (piNode != null) {
            pjNode = piNode.next;
            while (pjNode != null) {
                if (piNode.info.pcode.compareTo(pjNode.info.pcode) > 0) {
                    temp = piNode.info;
                    piNode.info = pjNode.info;
                    pjNode.info = temp;
                }
                pjNode = pjNode.next;
            }
            piNode = piNode.next;
        }
    }

    // (13)
    static void remove(String xName) {
        ProductNode pNode = searchByName(xName);
        remove(pNode);
    }

    static void Delete() {
        String pCode = GetInput.getString("Input pcode: ", "Wrong", "[a-zA-Z0-9]+");
        remove(pCode);

    }
    
    static int size() {
        int count = 0;
        ProductNode pNode = head;
        while (pNode != null) {
            count++;
            pNode = pNode.next;
        }
        return count;
    }

    static void q8() {
        String pCode;
        while (true) {
            pCode = GetInput.getString("Pcode: ", "Wrong", "[a-zA-Z0-9]+");
            if (!checkDuplicate(pCode)) {
                break;
            }
        }

        String pro_name = GetInput.getString("Pro_name: ", "Wrong", "[a-zA-Z0-9]+");
        int quantiy = GetInput.getInt("quantity: ", "Wrong", 0, Integer.MAX_VALUE);
        int saled = GetInput.getInt("Saled: ", "Wrong", 0, Integer.MAX_VALUE);
        double price = GetInput.getDouble("Price: ", "Wrong", 0, Double.MAX_VALUE);

        Product product = new Product(pCode, pro_name, quantiy, saled, price);
        
        int k = GetInput.getInt("Enter k: ", "Wrong", 0, size()-1 );
        insertAfter(pos(k), product);
        
    }
    static ProductNode pos(int k) {
        int count = 0;
        ProductNode pNode = head;
        while (pNode != null) {
            if (count == k) {
                return pNode;

            }
            count++;
            pNode = pNode.next;
        }

        return (null);
    }
    
    static void insertAfter(ProductNode q, Product x) {
        if (q == null) {
            return;

        }
        ProductNode qNext = q.next;
        ProductNode pNode = new ProductNode(x, qNext);
        q.next = pNode;
        if (tail == q) { // insert sau Node tail
            // cap nhat lai tail
            tail = pNode;
        }

    }

    static void q9() {
        String code = GetInput.getString("Enter xcode: ", "Wrong", "[a-zA-Z0-9]+");
        ProductNode node = searchByName(code);
        remove(node.next);
    }
    
    

}
