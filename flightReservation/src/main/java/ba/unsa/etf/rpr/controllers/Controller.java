package ba.unsa.etf.rpr.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Controller {
    public TextField fieldUsername;

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


    public void buttonClick(ActionEvent actionEvent) {
        if(fieldUsername.getText().isEmpty()){
            fieldUsername.getStyleClass().add("invalidField");
          /*  Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Niste unijeli korisničko ime");
            alert.setContentText("Korisničko ime ne smije biti prazno");

            alert.showAndWait();*/
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("USERNAME : "+ fieldUsername.getText());

        alert.showAndWait();
    }
}
