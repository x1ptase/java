/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PHAM KHAC VINH
 */
public class ProductNode {

    Product info;
    ProductNode next;

    public ProductNode(Product info, ProductNode next) {
        super();
        this.info = info;
        this.next = next;
    }

    public ProductNode() {
        super();
    }

    public ProductNode(Product info) {
        super();
        this.info = info;
        this.next = null;
    }
}
