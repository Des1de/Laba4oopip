package Controllers;

import Models.ProductsModel;
import Views.ProductViewElement;
import com.company.Main;
import Models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductsController implements Initializable {

    @FXML
    private Button checkoutBtn;
    @FXML
    private VBox products;
    private ProductsModel productsModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productsModel = Main.getProductsModel();
        generateProducts();
    }

    private void addToCartClicked(ActionEvent actionEvent) {
        products.getChildren().stream().filter(prVE -> ((ProductViewElement)prVE).getAddToCartBtn().equals(actionEvent.getSource())).forEach(prVE ->
                productsModel.addToCart(((ProductViewElement) prVE).getProduct().getProductName()));
    }

    public void addProduct(ProductViewElement productView) {
        products.getChildren().add(productView);
    }
    public void showProductClicked(ActionEvent actionEvent) throws IOException {
        ProductViewElement selectedProduct = null;
        for(Node product: products.getChildren())
        {
            if(((ProductViewElement)product).getShowProductBtn().equals(actionEvent.getSource()))
            {
                selectedProduct = (ProductViewElement) product;
                break;
            }
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ProductView.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);

        SingleProductController productController = loader.getController();
        productController.setProduct(selectedProduct.getProduct());

        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle(selectedProduct.getProduct().getProductName());
        stage.setScene(scene);
        stage.show();
    }
    private void generateProducts() {
        for(Product product: productsModel.getProducts())
        {
            ProductViewElement productView = new ProductViewElement(product);
            productView.getAddToCartBtn().setOnAction(this::addToCartClicked);
            productView.getShowProductBtn().setOnAction(
                    actionEvent ->
                    {
                        try {
                            showProductClicked(actionEvent);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
            addProduct(productView);
        }
    }

    public void checkoutBtnClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/CheckoutView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Checkout");
        stage.setScene(scene);
        stage.show();
    }
}
