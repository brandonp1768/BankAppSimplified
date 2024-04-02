package com.example.bankappsimplified;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.ResourceBundle;


public class LoanController {

    private Stage stage;

    @FXML
    private TextField loanTextField;

    @FXML
    void applyButtonPressed (ActionEvent event) {
        System.out.println("WORK IN PROGRESS NOT COMPLETE");
    }

    @FXML
    void backButtonPressed (ActionEvent event) throws IOException {
        changeScene(event, "SDashboard.fxml");
    }

    public void changeScene(ActionEvent event, String filename) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(filename)));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
