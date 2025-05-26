package core;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FeastMenuList extends ArrayList<FeastMenu>{
    private ArrayList<FeastMenu> menuList=new ArrayList<>();
    private static final String FILE_NAME="src/data/FeastMenu.csv";

    // dọc danh sách thực đơn từ file
    public void readFromFile(){
        menuList.clear();
        try(BufferedReader br=new BufferedReader(new FileReader(FILE_NAME))){
            // bỏ qua dòng đầu tiên (header)
            br.readLine();
            
            String line;
            while((line=br.readLine())!=null){
                String[] parts=line.split(",");
                if(parts.length==4){
                    String menuID=parts[0].trim();
                    String name=parts[1].trim();
                    double price=Double.parseDouble(parts[2].trim());
                    String ingredients=parts[3].trim();
                    menuList.add(new FeastMenu(menuID, name, price, ingredients));
                }
            }
            System.out.println("Menu data loaded successfully.");
        } catch(IOException e){
            System.out.println("Cannot read data from feastMenu.csv. Please check it.");
        }
    }

    // hiển thị danh sách thực đơn (sắp xếp theo giá)
    public void displayAllMenus() {
        if (menuList.isEmpty()) {
            System.out.println("No menu data available.");
            return;
        }
            
        // Tiêu đề
        System.out.println("List of Set Menus for ordering party:");
        
        // sắp xếp thực đơn theo giá (ascending order)
        Collections.sort(menuList, Comparator.comparingDouble(FeastMenu::getPrice));
        for (FeastMenu item : menuList) {
            System.out.printf("Code    :%s\n", item.getMenuID());
            System.out.printf("Name    :%s\n", item.getName());
            System.out.printf("Price   :%,d Vnd\n", (int)item.getPrice());
            System.out.println("Ingredients:");
        
            // Phân tách các phần ingredients bằng dấu #
            String[] categories = item.getIngredients().split("#");
            for (String category : categories) {
                // Loại bỏ dấu + ở đầu nếu có
                String cleanedCategory = category.trim();
                if (cleanedCategory.startsWith("+ ")) {
                    cleanedCategory = cleanedCategory.substring(2);
                }
                System.out.println("+ " + cleanedCategory.replace(";", "; "));
            }
            System.out.println("---------------------------------------------");
        }
    }

    // tìm thực đơn theo ID
    public FeastMenu findMenuByID(String menuID){
        for(FeastMenu item : menuList){
            if(item.getMenuID().equalsIgnoreCase(menuID)){
                return item;
            }
        }
        return null; // không tìm thấy thực đơn
    }
}