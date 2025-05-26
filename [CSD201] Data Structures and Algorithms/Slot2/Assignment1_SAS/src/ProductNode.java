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
