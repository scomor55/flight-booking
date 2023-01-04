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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AdminPassengersController {


    public TextField nameField;
    public TextField surnameField;
    public DatePicker dateOfBirthField;
    public TextField addressField;
    public TextField emailField;
    public TextField IdField;
    public ListView<Passengers> listField;

    private PassengersManager manager = new PassengersManager();

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

    public void deleteButton(ActionEvent actionEvent) throws FlightBookingException {
        Passengers passenger = manager.getById(Integer.parseInt(IdField.getText()));
        manager.delete(passenger.getId());
    }

    public void updateField(ActionEvent actionEvent) throws FlightBookingException {
        Passengers passenger = manager.getById(Integer.parseInt(IdField.getText()));
        passenger.setName(nameField.getText());
        passenger.setSurname(surnameField.getText());
        passenger.setDateOfBirth(dateOfBirthField.getValue());
        passenger.setAdress(addressField.getText());
        passenger.setEmail(emailField.getText());
        passenger = manager.update(passenger);
    }

    private void refreshPassengers()throws FlightBookingException{
        try{
            listField.setItems(FXCollections.observableList(manager.getAll()));
        }catch(FlightBookingException f){
            new Alert(Alert.AlertType.NONE, f.getMessage(), ButtonType.OK).show();
        }
    }
}
