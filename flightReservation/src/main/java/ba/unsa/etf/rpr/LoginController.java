package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    public TextField loginField;
    public Button loginBtn;

    @FXML
    public GridPane loginScreen;

    @FXML
    public void initialize() {
        loginField.getStyleClass().add("invalidField");
       /*
       // OLD School Style
       loginField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (n.isEmpty()) {
                    loginField.getStyleClass().add("invalidField");
                } else {
                    loginField.getStyleClass().removeAll("invalidField");
                }
            }
        });
        */

        // lambda style
        loginField.textProperty().addListener((observableValue, o, n) -> {
            if (n.isEmpty()) {
                loginField.getStyleClass().add("invalidField");
            } else {
                loginField.getStyleClass().removeAll("invalidField");
            }
        });
    }

    public void loginClick(ActionEvent actionEvent) {
        String login = loginField.getText();
        System.out.println("Login filed value: " +  login);
        loginBtn.setText(loginField.getText());
        if(login.isEmpty()){
            loginField.getStyleClass().add("invalidField");
        }else{
            try {
                final Stage loginStage = (Stage) loginScreen.getScene().getWindow();

                Stage myStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
                loader.load();
                MainController mainController = loader.getController();
                mainController.setUsername(loginField.getText());
                myStage.setTitle("Main Screen");
                myStage.setScene(new Scene(loader.getRoot(), 300, 275));
                myStage.show();
                loginStage.hide();
                myStage.setOnHiding(event -> {
                    System.out.println(mainController.getUsername());
                    loginStage.show();
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
