package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.FlightsManager;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightBookingException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

import ba.unsa.etf.rpr.dao.AbstractDao;

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
    }

    private void refreshFlights()throws FlightBookingException{
        try{
        flightsList.setItems(FXCollections.observableList(manager.getAll()));
        }catch(FlightBookingException f){
            new Alert(Alert.AlertType.NONE, f.getMessage(), ButtonType.OK).show();
        }
    }
}
