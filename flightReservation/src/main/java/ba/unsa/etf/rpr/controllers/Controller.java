package ba.unsa.etf.rpr.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Controller class for the main user interface. Handles user input and database interactions.
 * @author Safet Čomor
 */

public class Controller {
    /**
     * Text field for entering username.
     */
    public TextField fieldUsername;
    /**
     * String to temporarily store entered username.
     */
    public String tempUsername;
    /**
     * String to temporarily store entered password.
     */
    public String tempPassword;
    /**
     * Password field for entering password.
     */
    public PasswordField fieldPassword;
    /**
     * Initializes the main interface by adding a change listener to the username text field.
     * Adds the "invalidField" style class if the text field is empty.
     */
    @FXML
    public void initialize() {
        fieldUsername.getStyleClass().add("invalidField");
        fieldUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (fieldUsername.getText().trim().isEmpty()) {
                    fieldUsername.getStyleClass().removeAll("validField");
                    fieldUsername.getStyleClass().add("invalidField");
                } else {
                    fieldUsername.getStyleClass().removeAll("invalidField");
                    fieldUsername.getStyleClass().add("validField");
                }
            }
        });
    }
    /**
     * Check method that takes a username and password as parameters and returns a boolean indicating if the
     * combination is found in the database.
     * @param username entered username
     * @param password entered password
     * @return true if the combination is found in the database, false otherwise
     */
    public boolean Check(String username, String password) {

        try {
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("application.properties.sample").openStream());
            String url = p.getProperty("db.connection_string");
            String usr = p.getProperty("db.username");
            String pswd = p.getProperty("db.password");

            Connection conn = DriverManager.getConnection(url, usr, pswd);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Users WHERE username=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method is triggered when the "Button" is clicked. It checks the validity of the entered username and password.
     * If the entered username is empty, it displays an error message and adds the "invalidField" style class to the "fieldUsername".
     * If the check method returns false, it displays an error message.
     * If the check method returns true, it displays a success message and opens the relevant window based on the username.
     * @param actionEvent the event triggered by the button click
     */
    public void buttonClick(ActionEvent actionEvent) {
        if (fieldUsername.getText().isEmpty()) {
            fieldUsername.getStyleClass().add("invalidField");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You have not entered a username");
            alert.setContentText("Username must not be empty");
            alert.showAndWait();
            return;
        }
        tempUsername = fieldUsername.getText();
        tempPassword = fieldPassword.getText();

        boolean check = Check(tempUsername, tempPassword);
        if (!check) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You made an error");
            alert.setContentText("Wrong input!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct input");
            alert.setHeaderText("Logged in");
            alert.setContentText("You are successfully logged in");
            alert.showAndWait();
            alert.close();

            if(tempUsername.equals("safa")){
                try {
                    Stage passengerStage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminFlights.fxml"));
                    Parent root = loader.load();
                    passengerStage.setTitle("Flights management");
                    passengerStage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
                    passengerStage.setResizable(false);
                    passengerStage.show();

                    Node n = (Node) actionEvent.getSource();
                    Stage stage = (Stage) n.getScene().getWindow();
                    stage.close();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
                try {
                    final Stage userStage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/userPanel.fxml"));
                    loader.load();
                    userStage.setTitle("User panel");
                    userStage.setScene(new Scene((Parent) loader.getRoot(),USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
                    userStage.setResizable(false);
                    userStage.show();

                    Node n = (Node) actionEvent.getSource();
                    Stage stage = (Stage) n.getScene().getWindow();
                    stage.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    /**
     * This method is triggered when the "Register" button is clicked. It opens the registration window.
     * @param actionEvent the event triggered by the button click
     */
    public void registerClick(ActionEvent actionEvent) {
        try {
            final Stage myStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
            loader.load();
            Register register = loader.getController();
            myStage.setTitle("Main Screen");
            myStage.setScene(new Scene((Parent) loader.getRoot(),USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
            myStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}