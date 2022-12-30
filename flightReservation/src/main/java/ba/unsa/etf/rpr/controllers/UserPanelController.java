package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.FlightsManager;
import ba.unsa.etf.rpr.business.PassengersManager;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.domain.Passengers;
import ba.unsa.etf.rpr.exceptions.FlightBookingException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.table.DefaultTableColumnModel;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class UserPanelController  {

    private final FlightsManager flightsManager = new FlightsManager();
    private final PassengersManager passengersManager = new PassengersManager();
    public TextField sourceFindField;
    public TextField destinationFindField;
    public TableColumn<Flights,String> idColumn;
    public TableColumn<Flights,String> sourceColumn;
    public TableColumn<Flights,String> destinationColumn;
    public TableColumn<Flights,Date> departureColumn;
    public TableColumn<Flights,Date> arrivalColumn;
    public TableColumn<Flights,Integer> seatsColumn;
    public TableView flightsTable;
    public TextField idShowField;
    public TextField sourceShowField;
    public TextField destinationShowField;
    public TextField arrivalShowField;
    public TextField classShowField;
    public TextField seatsShowField;



    /*****/


    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Flights,String>("id"));
        sourceColumn.setCellValueFactory(new PropertyValueFactory<Flights,String>("source"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<Flights,String>("destination"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<Flights,Date>("departure"));
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<Flights,Date>("arrival"));
        seatsColumn.setCellValueFactory(new PropertyValueFactory<Flights,Integer>("seats"));

        refreshFlights();
    }


    public void findFlight(ActionEvent actionEvent) {
        try {
            flightsTable.setItems(FXCollections.observableList(flightsManager.searchBySourceAndDestination(sourceFindField.getText(),destinationFindField.getText())));
            flightsTable.refresh();
        }catch(FlightBookingException f){
            new Alert(Alert.AlertType.NONE, f.getMessage(), ButtonType.OK).show();
        }
     }

     private void refreshFlights(){
        try {
            flightsTable.setItems(FXCollections.observableList(flightsManager.getAll()));
            flightsTable.refresh();
        }catch(FlightBookingException f){
            new Alert(Alert.AlertType.NONE, f.getMessage(), ButtonType.OK).show();
        }
     }


    public void selectedFlight(MouseEvent mouseEvent) {
      Flights selectedFlight = (Flights) flightsTable.getSelectionModel().getSelectedItem();
      idShowField.setText(String.valueOf(selectedFlight.getId()));
        sourceShowField.setText(String.valueOf(selectedFlight.getSource()));
        destinationShowField.setText(String.valueOf(selectedFlight.getDestination()));
        arrivalShowField.setText(String.valueOf(selectedFlight.getArrival()));
        classShowField.setText(String.valueOf(selectedFlight.getClass()));
        seatsShowField.setText(String.valueOf(selectedFlight.getSeats()));

    }

    public TextField firstNameField;
    public TextField lastNameField;
    public TextField addressField;
    public TextField emailField;
    public DatePicker datePicker;

    public boolean Check(String firstName, String lastName, String address , String email, String dateOfBirth) throws FlightBookingException {

        List<Passengers> passengers = passengersManager.getAll();
        for(Passengers temp : passengers){

        }
        return false;
    }

    public void save(ActionEvent actionEvent) {



    }
}
