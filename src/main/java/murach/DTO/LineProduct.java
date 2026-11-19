package murach.DTO;

public class LineProduct {
    private Product product;
    private int quantity;


    public LineProduct () {}
    public LineProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getAmount(){
        return this.getQuantity() * this.getProduct().getPrice();
    }

}
