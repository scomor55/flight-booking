package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.PassengersManager;
import ba.unsa.etf.rpr.business.TicketsManager;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.domain.Passengers;
import ba.unsa.etf.rpr.domain.Tickets;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
public class AdminTicketsController  {


    public TextField flightIdField;
    public TextField passengerIdField;
    public TextField classField;
    public TextField priceField;
    public TextField ticketIDField;
    public ListView<Tickets> ticketsList;

    private TicketsManager manager = new TicketsManager();

    @FXML
    public void initialize(){
        try {
            refreshTickets();
            ticketsList.getSelectionModel().selectedItemProperty().addListener((obs, o , n) ->{
                if(n != null){
                   flightIdField.setText(String.valueOf(n.getFlightID()));
                   passengerIdField.setText(String.valueOf(n.getPassengerID()));
                   classField.setText(n.getTravelClass());
                   priceField.setText(String.valueOf(n.getPrice()));
                   ticketIDField.setText(String.valueOf(n.getId()));
                }
            });
        }catch(FlightBookingException f){
            throw new RuntimeException(f);
        }
    }

    public void addTicket(ActionEvent actionEvent) {
        try {
            Tickets ticket = new Tickets();
            ticket.setFlightID(Integer.parseInt(flightIdField.getText()));
            ticket.setPassengerID(Integer.parseInt(passengerIdField.getText()));
            ticket.setTravelClass(classField.getText());
            ticket.setPrice(Integer.parseInt(priceField.getText()));
            ticket = manager.add(ticket);
        }catch(FlightBookingException f){
            new Alert(Alert.AlertType.NONE, f.getMessage(), ButtonType.OK).show();
        }
    }

    public void updateTicket(ActionEvent actionEvent) throws FlightBookingException {
        Tickets ticket = manager.getById(Integer.parseInt(ticketIDField.getText()));
        ticket.setFlightID(Integer.parseInt(flightIdField.getText()));
        ticket.setPassengerID(Integer.parseInt(passengerIdField.getText()));
        ticket.setTravelClass(classField.getText());
        ticket.setPrice(Integer.parseInt(priceField.getText()));
        ticket = manager.update(ticket);
    }

    public void deleteTicket(ActionEvent actionEvent) throws FlightBookingException {
        Tickets ticket = manager.getById(Integer.parseInt(ticketIDField.getText()));
        manager.delete(ticket.getId());
    }

    private void refreshTickets()throws FlightBookingException{
        try{
            ticketsList.setItems(FXCollections.observableList(manager.getAll()));
        }catch(FlightBookingException f){
            new Alert(Alert.AlertType.NONE, f.getMessage(), ButtonType.OK).show();
        }
    }
}



