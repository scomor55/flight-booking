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

public class Register  {


    public Button signinBtn;
    public TextField fieldUsername;

    public String tempUsername;
    public String tempPassword;
    public TextField fieldPassword;


    public boolean signin(){

        try{

            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("application.properties.sample").openStream());
            String url = p.getProperty("db.connection_string");
            String usr = p.getProperty("db.username");
            String pswd = p.getProperty("db.password");

            Connection conn = DriverManager.getConnection(url, usr, pswd);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Users (username , password)"+
                    "VALUES (?,?)";
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
    public void signinClick(ActionEvent actionEvent) {
        if (fieldUsername.getText().isEmpty()) {
            fieldUsername.getStyleClass().add("invalidField");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Niste unijeli korisničko ime");
            alert.setContentText("Korisničko ime ne smije biti prazno");

            alert.showAndWait();
            return;
        }

        boolean check = signin();
        if (!check) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Pogrijesio si");
            alert.setContentText("Greska Safete");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ispravan unos");
            alert.setHeaderText("Uspjesno ste kreirali racun ");
            alert.setContentText("Svaka cast");
            alert.showAndWait();

            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }






    }
}
