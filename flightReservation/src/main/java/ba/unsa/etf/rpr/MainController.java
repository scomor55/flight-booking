package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {

    public TextField textField;
    private SimpleStringProperty textFieldProperty;

    private String username;

    public MainController(){
        this.textFieldProperty = new SimpleStringProperty("");
    }
    @FXML
    public void initialize() {
        textField.textProperty().bindBidirectional(textFieldProperty);
    }

    public SimpleStringProperty getTextFieldProperty(){
        return textFieldProperty;
    }

    public String getTextField(){
        return textFieldProperty.get();
    }

    public void setUsername(String username){
        this.username =  username;
    }

    public String getUsername(){
        return this.username;
    }

    public void logout(ActionEvent actionEvent){
        Node n =(Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}