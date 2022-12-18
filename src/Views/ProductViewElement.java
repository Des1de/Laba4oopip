package Views;

import Models.ProductObserver;
import com.company.Main;
import Models.Product;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class ProductViewElement extends HBox implements ProductObserver {

    private Product product;
    private Label nameLabel;
    private Label priceLabel;
    private Label quantityLabel;
    private Button addToCartBtn;
    private Button showProductBtn;

    public Product getProduct()
    {
        return product;
    }
    public Button getAddToCartBtn() {
        return addToCartBtn;
    }
    public Button getShowProductBtn() {
        return showProductBtn;
    }



    public ProductViewElement(Product product) {
        Main.getProductsObservable().addSubscriber(this);
        this.product = product;

        initGraphics();
    }

    private void initGraphics() {

        //Set label values
        nameLabel = new Label(product.getProductName());
        nameLabel.setPrefWidth(150);
        nameLabel.setFont(new Font(20));
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.prefHeightProperty().bind(this.widthProperty());;

        priceLabel = new Label("Price: " + product.getProductPrice().toString());
        priceLabel.setPrefWidth(150);
        priceLabel.setFont(new Font(20));
        priceLabel.setAlignment(Pos.CENTER);
        priceLabel.prefHeightProperty().bind(this.widthProperty());;

        quantityLabel = new Label("In cart: " + product.getQuantityInCart().toString());
        quantityLabel.setPrefWidth(150);

        setSpacing(10);

        //Set button values
        addToCartBtn = new Button("Add to Cart");
        addToCartBtn.prefWidth(100);
        showProductBtn = new Button("Show product");
        showProductBtn.prefWidth(150);

        //Set button-handler values
        VBox cartInfo = new VBox();
        cartInfo.setSpacing(10);
        cartInfo.setPrefWidth(100);
        cartInfo.getChildren().addAll(addToCartBtn, quantityLabel);

        //Add all elements to HBox
        getChildren().addAll(nameLabel, priceLabel, cartInfo, showProductBtn);

        this.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.setPadding(new Insets(10, 10, 10, 10));
    }

    @Override
    public void updateProduct(Product updatedValue) {
        if(!product.getProductName().equals(updatedValue.getProductName())) return;
        nameLabel.setText(updatedValue.getProductName());
        priceLabel.setText("Price: " + updatedValue.getProductPrice().toString());
        quantityLabel.setText("In cart: " + updatedValue.getQuantityInCart().toString());
    }
}