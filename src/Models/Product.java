package Models;

public class Product {
    private String productName;
    private Double productPrice;
    private Integer quantityInCart;

    public Product(String productName, Double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantityInCart = 0;
    }

    public String getProductName() {
        return productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }
    public Integer getQuantityInCart(){
        return quantityInCart;
    }

    public void addToCart(){
        quantityInCart++;
    }
}
