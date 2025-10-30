package model;

public class CartItem {
    private Mobiles mobile;   // đối tượng sản phẩm
    private int quantity;    // số lượng mua

    public CartItem(Mobiles mobile, int quantity) {
        this.mobile = mobile;
        this.quantity = quantity;
    }

    public Mobiles getMobile() {
        return mobile;
    }

    public void setMobile(Mobiles mobile) {
        this.mobile = mobile;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotal() {
        return mobile.getPrice() * quantity;
    }
}