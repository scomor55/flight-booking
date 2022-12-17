package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // FXML style
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        primaryStage.setTitle("Login App");
        primaryStage.setScene(new Scene(root, 300, 110));
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}