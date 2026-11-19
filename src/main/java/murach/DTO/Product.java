package murach.DTO;

public class Product {
    private int productCode ;
    private String description;
    private double price;

    public Product() {}
    public Product(int productCode, String description, double price) {
        this.productCode = productCode;
        this.description = description;
        this.price = price;
    }

    public int getProductCode() {
        return productCode;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
