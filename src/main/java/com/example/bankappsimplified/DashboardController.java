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

public class DashboardController implements Initializable{

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label accountnumberLabel;

    @FXML
    private Label balanceLabel;

    @FXML
    private ChoiceBox methodChoiceBox;

    @FXML
    private TextField amountTextField;

    private Stage stage;

    private String[] methods = {"Deposit", "Withdrawal"};

    private Account account = new Account();

    protected static HashMap h = new HashMap();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {  // Put the hashmap code in the LoginPage Initialize
        methodChoiceBox.getItems().addAll(methods);
        try {
            // try using this like account class is used in LogInPage
            FileReader reader = new FileReader("Accounts.txt");
            BufferedReader bufferedreader = new BufferedReader(reader);
            String line;
            while ((line = bufferedreader.readLine()) != null) {
                String[] account_data = line.split(" ");
                String[] account_info = {account_data[1], account_data[2], account_data[3], account_data[4], account_data[5]};
                h.put(account_data[0], account_info);
                account.setAccount_number(Integer.parseInt(account_data[0]));
                account.setName(account_data[1]);
                account.setPassword(account_data[2]);
                account.setBalance(Double.parseDouble(account_data[3]));
                account.setCreditScore(Integer.parseInt(account_data[4]));
                account.setUsername(account_data[5]);
            }
            String[] ai = (String[]) h.get(String.valueOf(account.getAccount_number()));
            for (String i : ai) { // keeping track of things in terminal, can be taken out if wanted
                System.out.println(i);
            }
            welcomeLabel.setText("Welcome " + account.getName());
            accountnumberLabel.setText("Account Number: " + account.getAccount_number());
            balanceLabel.setText("Balance: " + account.getBalance());
        }
        catch (FileNotFoundException e) {
            System.err.println("Exception: " + e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void actionButtonPressed (ActionEvent event) throws Exception {
        try {
            String method = (String) methodChoiceBox.getValue();
            String[] account_data = (String[]) h.get(String.valueOf(account.getAccount_number()));
            double balance = Double.parseDouble(account_data[2]);
            double change = Double.parseDouble(amountTextField.getText());
            if (method.equals("Deposit")) {
                balance += change;
                account_data[2] = String.valueOf(balance);
                account.setBalance(balance);
                balanceLabel.setText("Balance: " + balance);
            }
            else if (method.equals("Withdrawal")) {
                balance -= change;
                account_data[2] = String.valueOf(balance);
                account.setBalance(balance);
                balanceLabel.setText("Balance: " + balance);
            }
            h.remove(String.valueOf(account.getAccount_number()));
            h.put(String.valueOf(account.getAccount_number()), account_data);
        }
        catch (NullPointerException e) {
            System.err.println("Exception: " + e);
            amountTextField.setText("Please Select A Method");
        }
        catch (NumberFormatException e) {
            System.err.println("Exception: " + e);
            amountTextField.setText("Please Only Use Numbers!");
        }
    }

    @FXML
    void logoutButtonPressed (ActionEvent event) throws IOException {
        Alert logout = new Alert(Alert.AlertType.CONFIRMATION);
        logout.setTitle("Logout");
        logout.setHeaderText("You Are About To Logout!");
        logout.setContentText("Are You Sure You Want To Log Out?");
        logout.showAndWait();
        if(logout.getResult() == ButtonType.OK){
            FileWriter fw = new FileWriter ("Accounts.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Object account_number : h.keySet()) {
                String an = (String) account_number;
                String[] account_data = (String[]) h.get(an);
                bw.write(an + " " + account_data[0] + " " + account_data[1] + " " + account_data[2] + " " +
                        account_data[3] + " " + account_data[4]+ "\n");
            }
            changeScene(event, "LogInPage.fxml");
            bw.close();
            fw.close();
        }
    }

    @FXML
    void loanButtonPressed (ActionEvent event) throws IOException {
        changeScene(event, "Loan.fxml");
    }



    public void changeScene(ActionEvent event, String filename) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(filename)));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.show();
    }
}
