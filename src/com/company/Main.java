package com.company;

import Models.AccountsModel;
import Models.ProductObservable;
import Models.ProductsModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    private static final AccountsModel accountsModel = new AccountsModel();
    private static final ProductsModel productsModel = new ProductsModel();

    public static AccountsModel getAccountsModel()
    {
        return accountsModel;
    }
    public static ProductsModel getProductsModel()
    {
        return productsModel;
    }
    public static ProductObservable getProductsObservable()
    {
        return productsModel;
    }
    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/AuthorizationView.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Authorization");
            stage.setScene(scene);
            stage.show();
    }
}