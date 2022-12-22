package ba.unsa.etf.rpr.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AdminPanelController extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/adminPanel.fxml"));
        stage.setTitle("Admin panel");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }

    public void userClick(ActionEvent actionEvent) throws IOException {
        Stage userStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminUser.fxml"));
        Parent root = loader.load();
       /* AdminUsersController adminUsersController = new AdminUsersController();
        loader.setController(adminUsersController);*/
        userStage.setTitle("User management");
        userStage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        userStage.setResizable(false);
        userStage.show();
    }

    public void passengerClick(ActionEvent actionEvent)throws IOException {
        Stage passengerStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminPassengers.fxml"));
        Parent root = loader.load();
       /* AdminUsersController adminUsersController = new AdminUsersController();
        loader.setController(adminUsersController);*/
        passengerStage.setTitle("Passengers management");
        passengerStage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        passengerStage.setResizable(false);
        passengerStage.show();
    }


    public void flightsClick(ActionEvent actionEvent)throws IOException {
        Stage flightsStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminFlights.fxml"));
        Parent root = loader.load();
       /* AdminUsersController adminUsersController = new AdminUsersController();
        loader.setController(adminUsersController);*/
        flightsStage.setTitle("Flights management");
        flightsStage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        flightsStage.setResizable(false);
        flightsStage.show();
    }


    public void ticketsClick(ActionEvent actionEvent)throws IOException{
        Stage ticketsStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminTickets.fxml"));
        Parent root = loader.load();
       /* AdminUsersController adminUsersController = new AdminUsersController();
        loader.setController(adminUsersController);*/
        ticketsStage.setTitle("Tickets management");
        ticketsStage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        ticketsStage.setResizable(false);
        ticketsStage.show();
    }
}
