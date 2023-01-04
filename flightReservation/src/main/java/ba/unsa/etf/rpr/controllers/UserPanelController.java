package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.FlightsManager;
import ba.unsa.etf.rpr.business.PassengersManager;
import ba.unsa.etf.rpr.business.TicketsManager;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.domain.Passengers;
import ba.unsa.etf.rpr.domain.Tickets;
import ba.unsa.etf.rpr.exceptions.FlightBookingException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class UserPanelController  {

    private final FlightsManager flightsManager = new FlightsManager();
    private final PassengersManager passengersManager = new PassengersManager();

    private final TicketsManager ticketsManager = new TicketsManager();
    public TextField sourceFindField;
    public TextField destinationFindField;
    public TableColumn<Flights,String> idColumn;
    public TableColumn<Flights,String> sourceColumn;
    public TableColumn<Flights,String> destinationColumn;
    public TableColumn<Flights,Date> departureColumn;
    public TableColumn<Flights,Date> arrivalColumn;
    public TableColumn<Flights,Integer> seatsColumn;
    public TableView<Flights> flightsTable;
    public TextField idShowField;
    public TextField sourceShowField;
    public TextField destinationShowField;
    public TextField arrivalShowField;
    public TextField classShowField;
    public TextField seatsShowField;
    public TextField departureShowField;
    public TextField priceShowField;
    public ChoiceBox<String> choiceBox;
    public TableColumn<Flights, String> economyPriceColumn;
    public TableColumn<Flights, String> businessPriceColumn;
    public TextField classChooseField;


    /*****/


    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Flights,String>("id"));
        sourceColumn.setCellValueFactory(new PropertyValueFactory<Flights,String>("source"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<Flights,String>("destination"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<Flights,Date>("departure"));
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<Flights,Date>("arrival"));
        seatsColumn.setCellValueFactory(new PropertyValueFactory<Flights,Integer>("seats"));
        economyPriceColumn.setCellValueFactory(new PropertyValueFactory<Flights,String>("priceEconomy"));
        businessPriceColumn.setCellValueFactory(new PropertyValueFactory<Flights,String>("priceBusiness"));

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


    public int getID(String firstName, String lastName , LocalDate dateOfBirth , String address, String email) {

        try {
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("application.properties.sample").openStream());
            String url = p.getProperty("db.connection_string");
            String usr = p.getProperty("db.username");
            String pswd = p.getProperty("db.password");

            Connection conn = DriverManager.getConnection(url, usr, pswd);

            Statement stmt = conn.createStatement();
            String sql = "SELECT id FROM Passengers WHERE name = ? AND surname = ? AND dateOfBirth = ? AND address = ? AND email = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setDate(3, java.sql.Date.valueOf(dateOfBirth));
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                return id;
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    private int idFlight;


    public void selectedFlight(MouseEvent mouseEvent) {
      Flights selectedFlight = flightsTable.getSelectionModel().getSelectedItem();
      int idFlight = selectedFlight.getId();
      idShowField.setText(String.valueOf(selectedFlight.getId()));
        sourceShowField.setText(String.valueOf(selectedFlight.getSource()));
        destinationShowField.setText(String.valueOf(selectedFlight.getDestination()));
        arrivalShowField.setText(String.valueOf(selectedFlight.getArrival()));
        departureShowField.setText(String.valueOf(selectedFlight.getDeparture()));
        if(classChooseField.equals("")){
            priceShowField.setText(String.valueOf(selectedFlight.getPriceEconomy()));
        }
        priceShowField.setText(String.valueOf(selectedFlight.getPriceEconomy()));
    }

    public TextField firstNameField;
    public TextField lastNameField;
    public TextField addressField;
    public TextField emailField;
    public DatePicker datePicker;

    public boolean Check(String firstName, String lastName, String address , String email, LocalDate dateOfBirth) throws FlightBookingException {
        List<Passengers> passengers = passengersManager.getAll();
        for(Passengers temp : passengers){
            if(temp.getName().equals(firstName) && temp.getSurname().equals(lastName) && temp.getAdress().equals(address) && temp.getEmail().equals(email) && temp.getDateOfBirth().equals(dateOfBirth)){
                return true;
            }
        }
        return false;
    }

    public void save(ActionEvent actionEvent) throws FlightBookingException {

            boolean check = Check(firstNameField.getText(),lastNameField.getText(),addressField.getText(),emailField.getText(),datePicker.getValue());
            if(!check){
                try {
                    Passengers passenger = new Passengers();
                    passenger.setName(firstNameField.getText());
                    passenger.setSurname(lastNameField.getText());
                    passenger.setDateOfBirth(datePicker.getValue());
                    passenger.setAdress(addressField.getText());
                    passenger.setEmail(emailField.getText());
                    passenger = passengersManager.add(passenger);
                }catch(FlightBookingException f){
                    new Alert(Alert.AlertType.NONE, f.getMessage(), ButtonType.OK).show();
                }
            }else{

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Greška");
                alert.setHeaderText("Korisnik već postoji");
                alert.setContentText("Registriran korisnik sa ovim podacima");
                alert.showAndWait();
            }

    }

    /* Sve upite prebaci u dao */

    public void bookFlight(ActionEvent actionEvent) {

        int passengerID = getID(firstNameField.getText(),lastNameField.getText(),datePicker.getValue(),addressField.getText(),emailField.getText());
        try {
        Tickets ticket = new Tickets();
        ticket.setFlightID(Integer.parseInt(idShowField.getText()));
        ticket.setPassengerID(passengerID);
        ticket.setTravelClass(classChooseField.getText());
        ticket.setPrice(Integer.parseInt(priceShowField.getText()));
        ticket = ticketsManager.add(ticket);
        } catch (FlightBookingException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }

    }

    public void inputClass(ActionEvent actionEvent) {
        String text = classChooseField.getText();
        if(text.equals("Economy") || text.isEmpty()){
            int price = EconomyPrice(Integer.parseInt(idShowField.getText()));
            priceShowField.setText(String.valueOf(price));
        }else{
            int price = BusinessPrice(Integer.parseInt(idShowField.getText()));
            priceShowField.setText(String.valueOf(price));
        }
    }
}
