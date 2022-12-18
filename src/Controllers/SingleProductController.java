package Controllers;

import Models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SingleProductController implements Initializable {
    @FXML
    private Label priceLabel;
    @FXML
    private Label quantityLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Button goBackBtn;
    private Product product;

    public void setProduct(Product product)
    {
        this.product = product;
        nameLabel.setText(product.getProductName());
        priceLabel.setText("Price: " + product.getProductPrice().toString());
        quantityLabel.setText("In cart: " + product.getQuantityInCart().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void clickGoBack(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/ProductsView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Products");
        stage.setScene(scene);
        stage.show();
    }
}
