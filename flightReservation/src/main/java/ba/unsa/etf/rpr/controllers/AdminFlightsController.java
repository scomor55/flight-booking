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

public class AdminFlightsController {

    public TextField sourceField;
    public TextField destinationField;
    public DatePicker departureField;
    public DatePicker arrivalField;
    public TextField seatsField;
    public TextField idField;
    public ListView<Flights> flightsList;
    public TextField economyTicketField;
    public TextField businessTicketField;
    public TextField departureTimeField;
    public TextField arrivalTimeField;
    public Button goToPassengers;
    public Button goToTickets;

    private FlightsManager manager = new FlightsManager();


  /*  @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/adminFlights.fxml"));
        stage.setTitle("Flights management");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }*/
    
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

    public void updateFlight(ActionEvent actionEvent) throws FlightBookingException {
        //   Flights flight = manager.getById(Integer.parseInt(idField.getText()));

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


    public void deleteFlight(ActionEvent actionEvent)throws FlightBookingException{
        Flights flight = manager.getById(Integer.parseInt(idField.getText()));
        manager.delete(flight.getId());
        refreshFlights();
    }

    private void refreshFlights()throws FlightBookingException{
        try{
        flightsList.setItems(FXCollections.observableList(manager.getAll()));
        }catch(FlightBookingException f){
            new Alert(Alert.AlertType.NONE, f.getMessage(), ButtonType.OK).show();
        }
    }

    public void goToPassengers(ActionEvent actionEvent) throws IOException {
        Stage passengerStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminPassengers.fxml"));
        Parent root = loader.load();
       /* AdminUsersController adminUsersController = new AdminUsersController();
        loader.setController(adminUsersController);*/
        passengerStage.setTitle("Passengers management");
        passengerStage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        passengerStage.setResizable(false);
        passengerStage.show();

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }


    public void goToTickets(ActionEvent actionEvent) throws IOException {
        Stage ticketsStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminTickets.fxml"));
        Parent root = loader.load();
       /* AdminUsersController adminUsersController = new AdminUsersController();
        loader.setController(adminUsersController);*/
        ticketsStage.setTitle("Tickets management");
        ticketsStage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        ticketsStage.setResizable(false);
        ticketsStage.show();

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
