package Controllers;

import Models.AccountsModel;
import Models.Account;
import com.company.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    @FXML
    private Label errorLabel;
    @FXML
    private Label successLabel;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button registerBtn;
    @FXML
    private Button authorizationBtn;

    private AccountsModel accountsModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountsModel = Main.getAccountsModel();
        successLabel.visibleProperty().bind(accountsModel.getIsRegistrationSuccessful());
        errorLabel.visibleProperty().bind(accountsModel.getIsRegistrationFailed());
    }

    @FXML
    private void clickRegister(ActionEvent actionEvent) {
        accountsModel.RegisterAccount(new Account(loginField.getText(),passwordField.getText()));
    }
    @FXML
    private void clickAuthorization(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AuthorizationView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Authorization");
        stage.setScene(scene);
        stage.show();
    }

}
