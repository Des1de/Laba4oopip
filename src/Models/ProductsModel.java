package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ProductsModel implements ProductObservable {
    private final List<Product> products;
    private final ObservableList<String> cart;
    private double totalPrice = 0;
    private final List<ProductObserver> subscribers;

    public List<Product> getProducts() {
        return products;
    }
    public ObservableList<String> getCart()
    {
        return cart;
    }
    public double getTotalPrice()
    {
        return totalPrice;
    }
    public void addToCart(String productName)
    {
        products.stream().filter(s -> s.getProductName().equals(productName)).forEach(p ->
        {
            p.addToCart();
            String oldProductString = p.getProductName() + " | price: " + p.getProductPrice().toString() + " | x" + (p.getQuantityInCart() - 1);
            String newProductString = p.getProductName() + " | price: " + p.getProductPrice().toString() + " | x" + (p.getQuantityInCart());
            cart.remove(oldProductString);
            cart.add(newProductString);
            totalPrice+=p.getProductPrice();
            NotifySubscribers(p);
        });
    }
    private void NotifySubscribers(Product product)
    {
        for(ProductObserver subscriber: subscribers)
        {
            subscriber.updateProduct(product);
        }
    }
    public ProductsModel() {
        cart = FXCollections.observableArrayList();
        subscribers = new ArrayList<>();
        products = new ArrayList<>();
        products.add(new Product("Tomato", 12.5d));
        products.add(new Product("Milk", 4d));
        products.add(new Product("Meat", 20d));
        products.add(new Product("Potato", 3d));
        products.add(new Product("Fish", 17d));
        products.add(new Product("Ice-cream", 12d));
        products.add(new Product("Apple", 2.5d));
        products.add(new Product("Apricot", 16d));
        products.add(new Product("Yogurt", 13d));
        products.add(new Product("Hamburger", 4.5d));
        products.add(new Product("Lemonade", 12.5d));
    }

    @Override
    public void addSubscriber(ProductObserver subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ProductObserver subscriber) {
        subscribers.remove(subscriber);
    }

    public void Checkout(OrderInformation orderInformation)
    {
        System.out.println(orderInformation);
    }
}
