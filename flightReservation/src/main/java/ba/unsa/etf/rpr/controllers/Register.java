package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

/**
 * Register class is responsible for registering a user into the database by creating a new account.
 * @author Safet Čomor
 */

public class Register  {

    /**
     * The signinBtn button object used to initiate the sign in process
     */
    public Button signinBtn;
    /**
     * The fieldUsername text field object used to store the username of the user
     */
    public TextField fieldUsername;
    /**
     * The tempUsername string variable used to temporarily store the username
     */
    public String tempUsername;
    /**
     * The tempPassword string variable used to temporarily store the password
     */
    public String tempPassword;
    /**
     * The fieldPassword text field object used to store the password of the user
     */
    public TextField fieldPassword;
    /**
     * The signin method is used to add the new user to the database.
     * @return true if the insertion into the database was successful, false otherwise.
     */
    public boolean signin(){
        try{
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("application.properties.sample").openStream());
            String url = p.getProperty("db.connection_string");
            String usr = p.getProperty("db.username");
            String pswd = p.getProperty("db.password");

            Connection conn = DriverManager.getConnection(url, usr, pswd);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Users (username , password)"+ "VALUES (?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, fieldUsername.getText());
            preparedStatement.setString(2, fieldPassword.getText());

            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * The signinClick method is used to initiate the sign in process.
     * It checks if the username field is empty, and shows an error message if it is.
     * If the username is not empty, the signin method is called to add the user to the database.
     * If the signin method returns true, the user is informed that the account has been created successfully.
     * If the signin method returns false, the user is informed that the entry is incorrect.
     * @param actionEvent the event that triggered the sign in process
     */
    public void signinClick(ActionEvent actionEvent) {
        if (fieldUsername.getText().isEmpty()) {
            fieldUsername.getStyleClass().add("invalidField");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You have not entered a username");
            alert.setContentText("Username must not be empty");

            alert.showAndWait();
            return;
        }

        boolean check = signin();
        if (!check) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incorrect entry");
            alert.setContentText("You have entered incorrect information");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct entry");
            alert.setHeaderText("Account created!");
            alert.setContentText("You have successfully created an account");
            alert.showAndWait();

            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
    }
}
