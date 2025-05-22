package core;

public class FeastMenu{
    private String menuID; // mã thực đơn (PWxxx)
    private String name; // tên thực đơn
    private double price; // giá thực đơn
    private String ingredients; // danh sách nguyên liệu
    
    public FeastMenu(){
    }

    // Ctor
    public FeastMenu(String menuID, String name, double price, String ingredients){
        this.menuID=menuID;
        this.name=name;
        this.price=price;
        this.ingredients=ingredients;
    }

    // Getters
    public String getMenuID() {
        return menuID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getIngredients() {
        return ingredients;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    // toString() - Hiển thị thông tin thực đơn
    @Override
    public String toString() {
        return String.format("CodeID: %s | Name: %s | Price: %.2f | Ingredients: %s",
                menuID, name, price, ingredients);
    }
}
