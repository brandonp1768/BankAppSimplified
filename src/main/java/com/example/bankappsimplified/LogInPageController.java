package com.example.bankappsimplified;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class LogInPageController {

    @FXML
    private PasswordField passwordTextBox;

    @FXML
    private TextField tempTextField;

    @FXML
    private TextField usernameTextBox;

    public static Account account = new Account(); // this kind of creates an object for this whole controller

    private Stage stage;

    @FXML
    void loginButtonPressed(ActionEvent event) throws FileNotFoundException {
        try { // add the addition of a credti score in this
            FileReader reader = new FileReader("Accounts.txt");
            BufferedReader bufferedreader = new BufferedReader(reader);
            String line;
            String username = usernameTextBox.getText();
            String password = passwordTextBox.getText();
            boolean found = false; //true or false flag to show if we found the account
            while ((line = bufferedreader.readLine()) != null) {
                String[] account_data = line.split(" ");
                if (username.equals(account_data[5]) && password.equals(account_data[2])) {
                    found = true;
                    account.setAccount_number(Integer.parseInt(account_data[0]));
                    account.setName(account_data[1]);
                    account.setPassword(account_data[2]);
                    account.setBalance(Double.parseDouble(account_data[3]));
                    account.setCreditScore(Integer.parseInt(account_data[4]));
                    account.setUsername(account_data[5]);
                }
            }
            if (found) {
                changeScene(event, "SDashboard.fxml");
            }
            else {
                tempTextField.setText("Username Or Password Is Incorrect :( ");
            }
            bufferedreader.close();
            reader.close();
        }
        catch (IOException e) {
            System.err.println("Exception: " + e);
            System.out.println("Do Better!");
        }
    }

    @FXML
    void createaccountButtonPressed(ActionEvent event) throws IOException {
        try {
            changeScene(event, "SCreateAccount.fxml");
        }
        catch (IOException e) {
            System.err.println("Exception: " + e);
        }
    }


    public void changeScene(ActionEvent event, String filename) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(filename)));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}


