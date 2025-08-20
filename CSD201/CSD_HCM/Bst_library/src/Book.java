public class Book {
    private String code;
    private String title;
    private int quantity;
    private int category; //

    public Book(String code, String title, int quantity, int category) {
        this.code = code;
        this.title = title;
        this.quantity = quantity;
        this.category = category;
    }

    public Book(String title, int quantity) {
        this.title = title;
        this.quantity = quantity;
    }

    public Book() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "(" + code + "," + title + "," + quantity + "," + category + ")";
    }
} 