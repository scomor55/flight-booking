package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.FlightsManager;
import ba.unsa.etf.rpr.business.PassengersManager;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.domain.Passengers;
import ba.unsa.etf.rpr.exceptions.FlightBookingException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * The class AdminPassengersController is responsible for the management of passenger data.
 * It contains the functions for adding, updating, and deleting passenger data, as well as
 * displaying all passenger data in a list view. The data is stored and manipulated through
 * the PassengersManager class.
 * @author Safet Čomor
 */

public class AdminPassengersController {

    /**
     * A text field for entering the name of the passenger.
     */
    public TextField nameField;
    /**
     * A text field for entering the surname of the passenger.
     */
    public TextField surnameField;
    /**
     * A date picker for entering the date of birth of the passenger.
     */
    public DatePicker dateOfBirthField;
    /**
     * A text field for entering the address of the passenger.
     */
    public TextField addressField;
    /**
     * A text field for entering the email of the passenger.
     */
    public TextField emailField;
    /**
     * A text field for entering the id of the passenger.
     */
    public TextField IdField;
    /**
     * A list view for displaying all passenger data.
     */
    public ListView<Passengers> listField;
    /**
     * An instance of the PassengersManager class for managing passenger data.
     */
    private PassengersManager manager = new PassengersManager();
    /**
     * This method initializes the list view with the data of all passengers and sets
     * a listener for the selected item property of the list view. If a passenger is selected,
     * the data for that passenger is displayed in the text fields.
     * @throws RuntimeException if there is an error in retrieving the passenger data.
     */
    @FXML
    public void initialize(){
        try {
            refreshPassengers();
            listField.getSelectionModel().selectedItemProperty().addListener((obs, o , n) ->{
                if(n != null){
                    nameField.setText(n.getName());
                    surnameField.setText(n.getSurname());
                    dateOfBirthField.setValue(n.getDateOfBirth());
                    addressField.setText(n.getAdress());
                    emailField.setText(n.getEmail());
                    IdField.setText(String.valueOf(n.getId()));
                }
            });
        }catch(FlightBookingException f){
            throw new RuntimeException(f);
        }
    }

    /**
     * This method adds a new passenger based on the data entered in the text fields.
     * The new passenger is then added to the list view.
     * @param actionEvent the event that triggers the addition of a new passenger.
     */

    public void addButton(ActionEvent actionEvent) {

        try {
            Passengers passenger = new Passengers();
            passenger.setName(nameField.getText());
            passenger.setSurname(surnameField.getText());
            passenger.setDateOfBirth(dateOfBirthField.getValue());
            passenger.setAdress(addressField.getText());
            passenger.setEmail(emailField.getText());
            passenger = manager.add(passenger);
            listField.getItems().add(passenger);
        }catch(FlightBookingException f){
            new Alert(Alert.AlertType.NONE, f.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * deleteButton method is used to delete a passenger using the passenger id specified in the IdField.
     * The method first gets the passenger object using the `manager.getById` method,
     * then deletes the passenger using the `manager.delete` method and finally calls the `refreshPassengers` method.
     *
     * @param actionEvent the event that triggers the method
     * @throws FlightBookingException if there's an error while deleting the passenger
     */
    public void deleteButton(ActionEvent actionEvent) throws FlightBookingException {
        Passengers passenger = manager.getById(Integer.parseInt(IdField.getText()));
        manager.delete(passenger.getId());
        refreshPassengers();
    }
    /**
     * updateField method is used to update the details of a passenger.
     * The method first gets the passenger object using the `manager.getById` method,
     * then updates the details of the passenger object and finally calls the `refreshPassengers` method.
     *
     * @param actionEvent the event that triggers the method
     * @throws FlightBookingException if there's an error while updating the passenger
     */
    public void updateField(ActionEvent actionEvent) throws FlightBookingException {
        Passengers passenger = manager.getById(Integer.parseInt(IdField.getText()));
        passenger.setName(nameField.getText());
        passenger.setSurname(surnameField.getText());
        passenger.setDateOfBirth(dateOfBirthField.getValue());
        passenger.setAdress(addressField.getText());
        passenger.setEmail(emailField.getText());
        passenger = manager.update(passenger);
        refreshPassengers();
    }
    /**
     * refreshPassengers method is used to refresh the list of passengers in the listField.
     * The method calls the `manager.getAll` method and sets the result to the listField.
     * In case of an error, it shows an alert with the error message.
     * @throws FlightBookingException if there's an error while getting the list of passengers
     */
    private void refreshPassengers()throws FlightBookingException{
        try{
            listField.setItems(FXCollections.observableList(manager.getAll()));
        }catch(FlightBookingException f){
            new Alert(Alert.AlertType.NONE, f.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     * goToFlights method is used to navigate to the flights management page.
     * The method creates a new stage, sets its title, scene, and size, and then shows it.
     * It also closes the current stage.
     * @param actionEvent the event that triggers the method
     * @throws IOException if there's an error while loading the fxml file
     */
    public void goToFlights(ActionEvent actionEvent) throws IOException {
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

    }

    /**
     * goToTickets method is used to navigate to the tickets management page.
     * The method creates a new stage, sets its title, scene, and size, and then shows it.
     * It also closes the current stage.
     * @param actionEvent the event that triggers the method
     * @throws IOException if there's an error while loading the fxml file
     */
    public void goToTickets(ActionEvent actionEvent) throws IOException {
        Stage ticketsStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminTickets.fxml"));
        Parent root = loader.load();
        ticketsStage.setTitle("Tickets management");
        ticketsStage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        ticketsStage.setResizable(false);
        ticketsStage.show();

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
