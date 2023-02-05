package ba.unsa.etf.rpr.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
/**
 * The class AdminPanelController handles the actions of buttons in the administrative panel.
 * @author Safet Čomor
 */
public class AdminPanelController  {
    /**
     * Default constructor for the AdminPanelController class.
     */
    public AdminPanelController() {

    }
    /**
     * This method opens a new stage to manage the users in the system when the "User" button is clicked.
     * @param actionEvent the event that triggers the method.
     * @throws IOException if there is an error in loading the FXML file.
*/
    public void userClick(ActionEvent actionEvent) throws IOException {
        Stage userStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminUser.fxml"));
        Parent root = loader.load();
        userStage.setTitle("User management");
        userStage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        userStage.setResizable(false);
        userStage.show();
    }
    /**
     * This method opens a new stage to manage the passengers in the system when the "Passenger" button is clicked.
     * @param actionEvent the event that triggers the method.
     * @throws IOException if there is an error in loading the FXML file.
     */
    public void passengerClick(ActionEvent actionEvent)throws IOException {
        Stage passengerStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminPassengers.fxml"));
        Parent root = loader.load();
        passengerStage.setTitle("Passengers management");
        passengerStage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        passengerStage.setResizable(false);
        passengerStage.show();
    }

    /**
     * This method opens a new stage to manage the flights in the system when the "Flights" button is clicked.
     * @param actionEvent the event that triggers the method.
     * @throws IOException if there is an error in loading the FXML file.
     */
    public void flightsClick(ActionEvent actionEvent) throws IOException {

        Stage passengerStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminFlights.fxml"));
        Parent root = loader.load();
        passengerStage.setTitle("Flights management");
        passengerStage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        passengerStage.setResizable(false);
        passengerStage.show();


    }

    /**
     * This method opens a new stage to manage the tickets in the system when the "Tickets" button is clicked.
     * @param actionEvent the event that triggers the method.
     * @throws IOException if there is an error in loading the FXML file.
     */
    public void ticketsClick(ActionEvent actionEvent)throws IOException{
        Stage ticketsStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminTickets.fxml"));
        Parent root = loader.load();
        ticketsStage.setTitle("Tickets management");
        ticketsStage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        ticketsStage.setResizable(false);
        ticketsStage.show();
    }
}
