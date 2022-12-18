package Controllers;

import Command.*;
import Models.OrderInformation;
import Models.ProductsModel;
import com.company.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckoutController implements Initializable {
    @FXML
    private Label emptyLbl;
    @FXML
    private AnchorPane checkoutPage;
    @FXML
    private Button payBtn;
    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField cvvField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private ComboBox<String> paymentComboBox;
    @FXML
    private ListView<String> cartLst;
    @FXML
    private Label totalPriceLbl;

    private Command checkout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProductsModel productsModel = Main.getProductsModel();
        checkout = new CheckoutCommand(productsModel);
        cartLst.setItems(productsModel.getCart());
        totalPriceLbl.setText("Total: " + productsModel.getTotalPrice());
        ObservableList<String> paymentOptions = FXCollections.observableArrayList();
        paymentOptions.add("PayPal");
        paymentOptions.add("Credit Card");
        paymentComboBox.setItems(paymentOptions);
    }

    public void paymentChanged(ActionEvent actionEvent) {
        String payment = ((ComboBox<String>)actionEvent.getSource()).getValue();
        if(payment.equals("Credit Card"))
        {
            cvvField.setVisible(true);
            cardNumberField.setVisible(true);
            dateField.setVisible(true);
            emailField.setVisible(false);
            passwordField.setVisible(false);
        }
        else if (payment.equals("PayPal"))
        {
            cvvField.setVisible(false);
            cardNumberField.setVisible(false);
            dateField.setVisible(false);
            emailField.setVisible(true);
            passwordField.setVisible(true);
        }
        payBtn.setVisible(true);
    }

    public void payBtnClicked() {
        if(paymentComboBox.getValue().equals("Credit Card"))
        {
            if(cardNumberField.getText().isEmpty() ||
                dateField.getText().isEmpty()||
                cvvField.getText().isEmpty())
            {
                emptyLbl.setVisible(true);
                return;
            }
            emptyLbl.setVisible(false);
        }
        else if(paymentComboBox.getValue().equals("PayPal"))
        {
            if(emailField.getText().isEmpty() ||
                    passwordField.getText().isEmpty())
            {
                emptyLbl.setVisible(true);
                return;
            }
            emptyLbl.setVisible(false);
        }
        checkout.execute(new OrderInformation(paymentComboBox.getValue()));
        checkoutPage.setDisable(true);
    }
}
