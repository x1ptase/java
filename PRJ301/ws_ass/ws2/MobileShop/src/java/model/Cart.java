package model;

import java.util.*;

public class Cart {
    // Map key là id điện thoại, value là CartItem
    private Map<String, CartItem> items = new LinkedHashMap<>();

    // Thêm sản phẩm vào giỏ:
    public void addItem(Mobiles mobile, int quantity) {
        String id = mobile.getMobileId();
        if (items.containsKey(id)) {
            CartItem item = items.get(id);
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            items.put(id, new CartItem(mobile, quantity));
        }
    }

    // Xóa sản phẩm khỏi giỏ:
    public void removeItem(String mobileId) {
        items.remove(mobileId);
    }

    // Cập nhật số lượng mới cho sản phẩm:
    public void updateQuantity(String mobileId, int quantity) {
        CartItem item = items.get(mobileId);
        if (item != null) {
            if (quantity <= 0) {
                items.remove(mobileId);
            } else {
                item.setQuantity(quantity);
            }
        }
    }

    // Lấy toàn bộ các CartItem (cho jsp hiển thị):
    public Collection<CartItem> getItems() {
        return items.values();
    }

    // Tổng tiền trong giỏ:
    public float getTotal() {
        float total = 0;
        for (CartItem item : items.values()) {
            total += item.getTotal();
        }
        return total;
    }
}