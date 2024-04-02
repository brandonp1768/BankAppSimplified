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

    public void close(Stage stage) {
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
