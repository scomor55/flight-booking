package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.FlightsManager;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightBookingException;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AdminFlightsController /*extends Application*/ {

    public TextField sourceField;
    public TextField destinationField;
    public DatePicker departureField;
    public DatePicker arrivalField;
    public TextField seatsField;
    public TextField idField;
    private FlightsManager manager = new FlightsManager();


  /*  @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/adminFlights.fxml"));
        stage.setTitle("Flights management");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }*/

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

    public void updateFlight(ActionEvent actionEvent) {

    }

    public void deleteFlight(ActionEvent actionEvent) {

    }
}
