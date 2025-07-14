

public class Car {
    String bookID;       
    String color;  
    double price;     

    public Car(String bookID, String color, double price) {
        this.bookID = bookID;
        this.color = color;
        this.price = price;
    }
    
    public String getID() {
        return this.bookID;
    }

    public void setID(String id) {
        this.bookID = id;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return bookID + " | " + color + " | " + price;
    }
}
