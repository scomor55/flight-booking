package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.FlightsManager;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightBookingException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;

import ba.unsa.etf.rpr.dao.AbstractDao;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * This class is used for Admin functionality for managing Flights.
 * It allows the Admin to add, edit, delete, and view flights.
 * @author Safet Čomor
 */

public class AdminFlightsController {
    /**
     * sourceField - text field for source location of flight
     */
    public TextField sourceField;
    /**
     * destinationField - text field for destination location of flight
     */
    public TextField destinationField;
    /**
     * departureField - date picker for departure date of flight
     */
    public DatePicker departureField;
    /**
     * arrivalField - date picker for arrival date of flight
     */
    public DatePicker arrivalField;
    /**
     * seatsField - text field for number of seats in flight
     */
    public TextField seatsField;
    /**
     * idField - text field for id of flight
     */
    public TextField idField;
    /**
     * flightsList - list view to display flights
     */
    public ListView<Flights> flightsList;
    /**
     * economyTicketField - text field for price of economy class ticket
     */
    public TextField economyTicketField;
    /**
     * businessTicketField - text field for price of business class ticket
     */
    public TextField businessTicketField;
    /**
     * departureTimeField - text field for departure time of flight
     */
    public TextField departureTimeField;
    /**
     * arrivalTimeField - text field for arrival time of flight
     */
    public TextField arrivalTimeField;
    /**
     * goToPassengers - button to go to passengers management
     */
    public Button goToPassengers;
    /**
     * goToTickets - button to go to tickets management
     */
    public Button goToTickets;
    /**
     * manager - instance of FlightsManager
     */
    private FlightsManager manager = new FlightsManager();
    /**
     * This method is called during the initialization of the class.
     * It is used to initialize the flight list and bind the selected item to input fields.
     */
    @FXML
    public void initialize(){
        try {
            refreshFlights();
           flightsList.getSelectionModel().selectedItemProperty().addListener((obs, o , n) ->{
                if(n != null){
                    sourceField.setText(n.getSource());
                    destinationField.setText(n.getDestination());
                    arrivalField.setValue(n.getArrival());
                    arrivalTimeField.setText(n.getArrivalTime());
                    departureField.setValue(n.getDeparture());
                    departureTimeField.setText(n.getDepartureTime());
                    seatsField.setText(String.valueOf(n.getSeats()));
                    economyTicketField.setText(String.valueOf(n.getPriceEconomy()));
                    businessTicketField.setText(String.valueOf(n.getPriceBusiness()));
                    idField.setText(String.valueOf(n.getId()));
                }
            });
        }catch(FlightBookingException f){
            throw new RuntimeException(f);
        }
    }
    /**
     * Adds a new flight to the database.
     * @param actionEvent The event that triggers the method call.
     */
    public void addFlight(ActionEvent actionEvent) {
        try {
            Flights flight = new Flights();
            flight.setSource(sourceField.getText());
            flight.setDestination(destinationField.getText());
            flight.setDeparture(departureField.getValue());
            flight.setDepartureTime(departureTimeField.getText());
            flight.setArrival(arrivalField.getValue());
            flight.setArrivalTime(arrivalTimeField.getText());
            flight.setSeats(Integer.parseInt(seatsField.getText()));
            flight.setPriceEconomy(Integer.parseInt(economyTicketField.getText()));
            flight.setPriceBusiness(Integer.parseInt(businessTicketField.getText()));
            flight = manager.add(flight);
            flightsList.getItems().add(flight);
        }catch(FlightBookingException f){
            new Alert(Alert.AlertType.NONE, f.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     * Updates an existing flight in the database.
     * @param actionEvent The event that triggers the method call.
     * @throws FlightBookingException if an error occurs while updating the flight.
     */
    public void updateFlight(ActionEvent actionEvent) throws FlightBookingException {

        Flights flight = flightsList.getSelectionModel().getSelectedItem();
        flight.setSource(sourceField.getText());
        flight.setDestination(destinationField.getText());
        flight.setDeparture(departureField.getValue());
        flight.setDepartureTime(departureTimeField.getText());
        flight.setArrival(arrivalField.getValue());
        flight.setArrivalTime(arrivalTimeField.getText());
        flight.setSeats(Integer.parseInt(seatsField.getText()));
        flight.setPriceEconomy(Integer.parseInt(economyTicketField.getText()));
        flight.setPriceBusiness(Integer.parseInt(businessTicketField.getText()));
        flight = manager.update(flight);
        refreshFlights();
    }
    /**
     * Deletes a flight from the database.
     *
     * @param actionEvent The event that triggers the method call.
     * @throws FlightBookingException if an error occurs while deleting the flight.
     */
    public void deleteFlight(ActionEvent actionEvent)throws FlightBookingException{
        Flights flight = manager.getById(Integer.parseInt(idField.getText()));
        manager.delete(flight.getId());
        refreshFlights();
    }
    /**
     * Refreshes the flight information displayed in the UI.
     * @throws FlightBookingException if there is an issue retrieving the flight information
     */
    private void refreshFlights()throws FlightBookingException{
        try{
        flightsList.setItems(FXCollections.observableList(manager.getAll()));
        }catch(FlightBookingException f){
            new Alert(Alert.AlertType.NONE, f.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     * Navigates the user to the Passengers Management page.
     * @param actionEvent the event that triggered this action
     * @throws IOException if there is an issue loading the FXML file for the Passengers Management page
     */
    public void goToPassengers(ActionEvent actionEvent) throws IOException {
        Stage passengerStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminPassengers.fxml"));
        Parent root = loader.load();
        passengerStage.setTitle("Passengers management");
        passengerStage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        passengerStage.setResizable(false);
        passengerStage.show();

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    /**
     * Navigates the user to the Tickets Management page.
     * @param actionEvent the event that triggered this action
     * @throws IOException if there is an issue loading the FXML file for the Tickets Management page
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
