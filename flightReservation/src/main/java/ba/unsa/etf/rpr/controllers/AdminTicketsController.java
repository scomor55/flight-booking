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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * AdminTicketsController is a JavaFX Controller class that implements the functionalities of adding, updating, and deleting
 * {@link Tickets} objects in the database. It contains several GUI components such as TextFields, ListView, and ChoiceBox
 * to allow the user to input, view, and modify the ticket information. The class also has a method for calculating the ticket
 * price based on the chosen travel class and flight.
 @author Safet Čomor
 */
public class AdminTicketsController  {
    /**
     * TextField for entering the flight id of a ticket
     */
    public TextField flightIdField;
    /**
     * TextField for entering the passenger id of a ticket
     */
    public TextField passengerIdField;
    /**
     * TextField for entering the passenger id of a ticket
     */
    public TextField classField;
    /**
     * TextField for entering the price of a ticket
     */
    public TextField priceField;
    /**
     * TextField for entering the ticket id of a ticket
     */
    public TextField ticketIDField;
    /**
     * ListView for displaying a list of all tickets
     */
    public ListView<Tickets> ticketsList;
    /**
     * ChoiceBox for selecting the travel class of a ticket
     */
    public ChoiceBox boxBox;
    /**
     * A manager object for performing CRUD operations on the Tickets objects
     */
    private TicketsManager manager = new TicketsManager();
    /**
     * The initialize method is called when the GUI is set up. It initializes the ListView of tickets and sets up listeners
     * for the ChoiceBox and the ListView. The method also sets the default values for the ticket information fields.
     */
    @FXML
    public void initialize(){
        try {
            refreshTickets();
            ticketsList.getSelectionModel().selectedItemProperty().addListener((obs, o , n) ->{
                if(n != null){
                    flightIdField.setText(String.valueOf(n.getFlightID()));
                    passengerIdField.setText(String.valueOf(n.getPassengerID()));
                    boxBox.setValue(n.getTravelClass());
                    if(n.getTravelClass().equals("Economy")){
                        int price = EconomyPrice(Integer.parseInt(flightIdField.getText()));
                        priceField.setText(String.valueOf(price));
                    }else {
                        int price = BusinessPrice(Integer.parseInt(flightIdField.getText()));
                        priceField.setText(String.valueOf(price));
                    }
                    ticketIDField.setText(String.valueOf(n.getId()));
                }
            }
            );
            boxBox.getSelectionModel().selectedItemProperty().addListener((obs,oldValue, newValue)->{
                if(newValue.toString().equals("Economy") || newValue.toString().isEmpty()){
                    int price = EconomyPrice(Integer.parseInt(flightIdField.getText()));
                    priceField.setText(String.valueOf(price));
                }else{
                    int price = BusinessPrice(Integer.parseInt(flightIdField.getText()));
                    priceField.setText(String.valueOf(price));
                }
            });

        }catch(FlightBookingException f){
            throw new RuntimeException(f);
        }
    }

    /**
     * Adds a new ticket to the list of tickets.
     * @param actionEvent triggered event to add a new ticket
    */
    public void addTicket(ActionEvent actionEvent) {
        try {
            Tickets ticket = new Tickets();
            ticket.setFlightID(Integer.parseInt(flightIdField.getText()));
            ticket.setPassengerID(Integer.parseInt(passengerIdField.getText()));
            ticket.setTravelClass(String.valueOf(boxBox.getSelectionModel().getSelectedItem()));
            ticket.setPrice(Integer.parseInt(priceField.getText()));
            ticket = manager.add(ticket);
            ticketsList.getItems().add(ticket);
        }catch(FlightBookingException f){
            new Alert(Alert.AlertType.NONE, f.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     * Updates an existing ticket in the list of tickets.
     * @param actionEvent triggered event to update a ticket
     * @throws FlightBookingException if the operation fails
     */
    public void updateTicket(ActionEvent actionEvent) throws FlightBookingException {
        Tickets ticket = manager.getById(Integer.parseInt(ticketIDField.getText()));
        ticket.setFlightID(Integer.parseInt(flightIdField.getText()));
        ticket.setPassengerID(Integer.parseInt(passengerIdField.getText()));
        ticket.setTravelClass(String.valueOf(boxBox.getSelectionModel().getSelectedItem()));
        ticket.setPrice(Integer.parseInt(priceField.getText()));
        ticket = manager.update(ticket);
        refreshTickets();
    }
    /**
     * Deletes an existing ticket from the list of tickets.
     * @param actionEvent triggered event to delete a ticket
     * @throws FlightBookingException if the operation fails
     */
    public void deleteTicket(ActionEvent actionEvent) throws FlightBookingException {
        Tickets ticket = manager.getById(Integer.parseInt(ticketIDField.getText()));
        manager.delete(ticket.getId());
        refreshTickets();
    }
    /**
     * Refreshes the list of tickets.
     * @throws FlightBookingException if the operation fails
     */
    private void refreshTickets()throws FlightBookingException{
        try{
            ticketsList.setItems(FXCollections.observableList(manager.getAll()));
        }catch(FlightBookingException f){
            new Alert(Alert.AlertType.NONE, f.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Gets the price of Economy class for a flight.
     * @param flightID ID of the flight
     * @return price of Economy class
     */
    public int EconomyPrice(int flightID) {

        try {
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("application.properties.sample").openStream());
            String url = p.getProperty("db.connection_string");
            String usr = p.getProperty("db.username");
            String pswd = p.getProperty("db.password");

            Connection conn = DriverManager.getConnection(url, usr, pswd);

            Statement stmt = conn.createStatement();
            String sql = "SELECT priceEconomy FROM Flights WHERE id = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, flightID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int price = rs.getInt("priceEconomy");
                return price;
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Returns the price of business class for a specific flight.
     * @param flightID the ID of the flight to get the business class price for.
     * @return the price of the business class for the specified flight.
     */

    public int BusinessPrice(int flightID) {
        try {
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("application.properties.sample").openStream());
            String url = p.getProperty("db.connection_string");
            String usr = p.getProperty("db.username");
            String pswd = p.getProperty("db.password");

            Connection conn = DriverManager.getConnection(url, usr, pswd);

            Statement stmt = conn.createStatement();
            String sql = "SELECT priceBusiness FROM Flights WHERE id = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, flightID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int price = rs.getInt("priceBusiness");
                return price;
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * Loads the flight management screen.
     * It also closes the current stage.
     * @param actionEvent the event that triggers this action.
     * @throws IOException if the FXML file cannot be loaded.
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
     * Loads the passenger management screen.
     * It also closes the current stage.
     * @param actionEvent the event that triggers this action.
     * @throws IOException if the FXML file cannot be loaded.
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
}



