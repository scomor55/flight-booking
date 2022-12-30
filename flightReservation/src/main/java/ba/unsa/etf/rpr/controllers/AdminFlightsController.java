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
                    departureField.setValue(n.getDeparture());
                    seatsField.setText(String.valueOf(n.getSeats()));
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
            flight.setDeparture(departureField.getValue()); /* TRY TO USE DATE ONLY */
            flight.setArrival(arrivalField.getValue()); /* TRY TO USE DATE ONLY */
            flight.setSeats(Integer.parseInt(seatsField.getText())); /*RENAME THIS IF YOU DONT HAVE TIME */
            flight = manager.add(flight);
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
        flight.setArrival(arrivalField.getValue());
        flight.setSeats(Integer.parseInt(seatsField.getText()));
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
