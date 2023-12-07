package com.example.bankappsimplified;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class BankAppSimplified extends Application {


    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LogInPage.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Sea Hawk Bank");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            event.consume();
            close(stage);
        });
    }

    public void close(Stage stage) { // Closing the window, ability to let user know their work may not be saved
        Alert close = new Alert(Alert.AlertType.CONFIRMATION);
        close.setTitle("Close");
        close.setHeaderText("Yoe Are About To Close The Program!");
        close.setContentText("Some Of Your Changes May Not Be Saved\n" + "Are You Sure You Want To Leave?");
        close.showAndWait();
        if(close.getResult() == ButtonType.OK){
            stage.close();
        }
    }
}

// Tasks: Get the login page full functional with going to the dashboard and making a new account(COMPLETED)
// make a prompt for saving changes when pressing x and logging out (COMPLETED)
// go through and do exception handling, numberformat basically
// add back buttons into needed scenes (COMPLETED SO FAR)
// add in password hashing  URGENT
// make the account more specific, add a savings, checkings, blah, blah, blah
// eventually make it where there have to be unique usernames or just use account numbers to log in
// make a loan area, credit score already added
// make a popup for creating an account to show them their account number
// see about moving the hashmap this part and see if you can grab information from the other controllers
// color
// make : seperating the pieces of information in the textfile, spaces in the textfield crashed the program