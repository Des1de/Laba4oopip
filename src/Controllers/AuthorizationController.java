package Controllers;

import Models.AccountsModel;
import Models.Account;
import com.company.Main;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthorizationController implements Initializable {

    @FXML
    private Label successLabel;
    @FXML
    private Button goToProductsBtn;
    @FXML
    private Label errorLabel;
    @FXML
    private Button authorizationBtn;
    @FXML
    private Button registrationBtn;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField loginField;

    private AccountsModel accountsModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountsModel = Main.getAccountsModel();
        successLabel.visibleProperty().bind(accountsModel.getIsSignedIn());
        errorLabel.visibleProperty().bind(accountsModel.getIsAuthorizationFailed());
        loginField.disableProperty().bind(accountsModel.getIsSignedIn());
        passwordField.disableProperty().bind(accountsModel.getIsSignedIn());
        authorizationBtn.visibleProperty().bind(accountsModel.getIsSignedIn().isNotEqualTo(new SimpleBooleanProperty(true)));
        registrationBtn.visibleProperty().bind(accountsModel.getIsSignedIn().isNotEqualTo(new SimpleBooleanProperty(true)));
        goToProductsBtn.visibleProperty().bind(accountsModel.getIsSignedIn());
    }

    @FXML
    private void clickSignIn(ActionEvent actionEvent) {
        accountsModel.Authorization(new Account(loginField.getText(), passwordField.getText()));
    }

    @FXML
    private void clickSignOn(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Views/RegistrationView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Registration");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clickGoToProdBtn(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Views/ProductsView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Products");
        stage.setScene(scene);
        stage.show();
    }
}
