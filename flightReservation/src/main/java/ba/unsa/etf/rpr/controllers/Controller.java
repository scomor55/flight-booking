package ba.unsa.etf.rpr.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.Properties;

public class Controller {
    public TextField fieldUsername;
    public String tempUsername;
    public String tempPassword;
    @FXML
    public void initialize(){
        fieldUsername.getStyleClass().add("invalidField");
        fieldUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(fieldUsername.getText().trim().isEmpty()){
                    fieldUsername.getStyleClass().removeAll("validField");
                    fieldUsername.getStyleClass().add("invalidField");
                }else{
                    fieldUsername.getStyleClass().removeAll("invalidField");
                    fieldUsername.getStyleClass().add("validField");
                }
            }
        });
    }

    public boolean Check(String username, String password){

        try{
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("application.properties.template").openStream());
            String url = p.getProperty("db.connection_string");
            String usr = p.getProperty("db.username");
            String pswd= p.getProperty("db.password");

            Connection conn = DriverManager.getConnection(url,usr,pswd);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Users WHERE username=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                tempUsername = rs.getString("username");
                tempPassword = rs.getString("password");
                return true;
            }
            stmt.close();
            conn.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void buttonClick(ActionEvent actionEvent) {
        if(fieldUsername.getText().isEmpty()){
            fieldUsername.getStyleClass().add("invalidField");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Niste unijeli korisničko ime");
            alert.setContentText("Korisničko ime ne smije biti prazno");

            alert.showAndWait();
            return;
        }
        tempUsername = fieldUsername.getText();



    }
}
