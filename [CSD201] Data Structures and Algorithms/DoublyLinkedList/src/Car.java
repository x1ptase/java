public class Car {
    private String id;
    private String brand;
    private String color;
    private int exp;
    
    public Car(){
    }
    
    public Car(String id, String brand, String color, int exp){
        this.id=id;
        this.brand=brand;
        this.color=color;
        this.exp=exp;
    }

    @Override
    public String toString() {
        return "Car" + "id=" + id + ", brand=" + brand + ", color=" + color + ", exp=" + exp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
    
    
    
}
