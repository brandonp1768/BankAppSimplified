package com.example.bankappsimplified;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class CreateAccountController {

    @FXML
    private TextField nameTextBox;

    @FXML
    private TextField passwordTextBox;

    @FXML
    private TextField creditscoreTextField;
    @FXML
    private TextField usernameTextField;

    private Stage stage;

    @FXML
    void createaccountButtonPressed(ActionEvent event) throws IOException { // add the try and catch
        try {
            FileReader reader = new FileReader("Accounts.txt");
            BufferedReader bufferedreader = new BufferedReader(reader);
            String username = usernameTextField.getText();
            String line2;
            boolean found2 = false;
            while ((line2 = bufferedreader.readLine()) != null) { // Making usernames unique
                String[] account_data = line2.split(" ");
                if (usernameTextField.getText() == account_data[5]) {
                    found2 = true;
                }
            }
            if (found2) {
                usernameTextField.setText("Username Taken!");
            }
            else {
                String name = nameTextBox.getText();
                String password = passwordTextBox.getText();
                int creditScore = Integer.parseInt(creditscoreTextField.getText());
                if (creditScore > 850 || creditScore < 300) {
                    throw new IOException();
                }
                int account_number = (int) (Math.random() * 10000);
                boolean found = false;
                boolean not_found = true;
                String line;
                while (not_found) { // Making account numbers unique, may be possible to simply down in future
                    while ((line = bufferedreader.readLine()) != null) {
                        String[] account_data = line.split(" ");
                        if ((Integer.parseInt(account_data[0]) == account_number)) {
                            found = true;
                            account_number = (int) (Math.random() * 10000);
                        } else {
                            found = false;
                        }
                    }
                    if (!found) {
                        not_found = false;
                    }
                }
                Account account = new Account(0, name, account_number, password, creditScore, username);
                FileWriter fw = new FileWriter("Accounts.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write((account.getAccount_number() + " " + account.getName() + " " + account.getPassword() + " " +
                        account.getBalance() + " " + account.getCreditScore() + " " + account.getUsername() + "\n"));
                bw.close();
                fw.close();
                changeScene(event, "LogInPage.fxml");
            }
        }
        catch (IOException e) {
            System.err.println("Exception: " + e);
            creditscoreTextField.setText("Has To Be Between 300 and 850");
        }
        catch (NumberFormatException e) {
            System.err.println("Exception: " + e);
            creditscoreTextField.setText("Credit Score Is A Number");
        }
        // continue creating account, now you need to write it to the txt file to finish!
    } // for this we are using file write, bufferedwriter, i think buffered writer just makes it faster
    // you are going to be adding in a username portion eventually, or use account number to log in, either way
    // make something to stop it from leaving the page immedeately so you can show them their account number

    @FXML
    void backButtonPressed (ActionEvent event) throws IOException {
        changeScene(event, "LogInPage.fxml");
    }

    public void changeScene(ActionEvent event, String filename) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(filename)));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // this is the only part that is weird and I don't understand
        stage.setScene(scene);
        stage.show();
    }
}

