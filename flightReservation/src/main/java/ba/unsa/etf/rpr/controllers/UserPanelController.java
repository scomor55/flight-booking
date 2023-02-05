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

/**
 * The UserPanelController class is responsible for managing the interactions between the user interface and the backend.
 * It is responsible for searching for flights, selecting a flight, and displaying flight details, as well as buying tickets.
 * @author Safet Čomor
 */
public class UserPanelController  {
    /**
     * The FlightsManager instance for managing flights.
     */
    private final FlightsManager flightsManager = new FlightsManager();
    /**
     * The PassengersManager instance for managing passengers.
     */
    private final PassengersManager passengersManager = new PassengersManager();
    /**
     * The TicketsManager instance for managing tickets.
     */
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
    public TextField departureShowField;
    public TextField priceShowField;
    public TableColumn<Flights, String> economyPriceColumn;
    public TableColumn<Flights, String> businessPriceColumn;
    public TextField classChooseField;
    public TableColumn<Flights, String> departureTimeColumn;
    public TableColumn<Flights, String> arrivalTimeColumn;
    public ChoiceBox boxBox;
    public TextField departureTimeShowField;
    public TextField arrivalTimeShowField;
    public TableView ticketsTable;



    /**
     * The method that initializes the user interface, including adding listeners to certain elements, setting the value factory for table columns and refreshing the flights table.
     */
    public void initialize(){
        boxBox.getSelectionModel().selectedItemProperty().addListener((obs,oldValue, newValue)->{
            if(newValue.toString().equals("Economy") || newValue.toString().isEmpty()){
                int price = EconomyPrice(Integer.parseInt(idShowField.getText()));
                priceShowField.setText(String.valueOf(price));
            }else{
                int price = BusinessPrice(Integer.parseInt(idShowField.getText()));
                priceShowField.setText(String.valueOf(price));
            }
        });
        idColumn.setCellValueFactory(new PropertyValueFactory<Flights,String>("id"));
        sourceColumn.setCellValueFactory(new PropertyValueFactory<Flights,String>("source"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<Flights,String>("destination"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<Flights,Date>("departure"));
        departureTimeColumn.setCellValueFactory(new PropertyValueFactory<Flights,String>("departureTime"));
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<Flights,Date>("arrival"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<Flights,String>("arrivalTime"));
        seatsColumn.setCellValueFactory(new PropertyValueFactory<Flights,Integer>("seats"));
        economyPriceColumn.setCellValueFactory(new PropertyValueFactory<Flights,String>("priceEconomy"));
        businessPriceColumn.setCellValueFactory(new PropertyValueFactory<Flights,String>("priceBusiness"));

        refreshFlights();
    }

    /**
     * The method that handles the action of finding a flight, either by source or source and destination.
     * @param actionEvent The event triggered by the user's action.
     */
    public void findFlight(ActionEvent actionEvent) {
        try {
            if(sourceShowField==null && destinationShowField != null){
                flightsTable.setItems(FXCollections.observableList(flightsManager.searchByDestination(destinationFindField.getText())));
            }else {
                flightsTable.setItems(FXCollections.observableList(flightsManager.searchBySourceAndDestination(sourceFindField.getText(), destinationFindField.getText())));
            }
            flightsTable.refresh();
        }catch(FlightBookingException f){
            new Alert(Alert.AlertType.NONE, f.getMessage(), ButtonType.OK).show();
        }
     }

    /**
     * refreshFlights is a method used to refresh the flights table by retrieving the list of all flights from the flights manager and updating the flights table with it.
     * If there is an exception during the retrieval process, it will be caught and displayed in an Alert dialog box with the exception's message.
     */
     private void refreshFlights(){
        try {
            flightsTable.setItems(FXCollections.observableList(flightsManager.getAll()));
            flightsTable.refresh();
        }catch(FlightBookingException f){
            new Alert(Alert.AlertType.NONE, f.getMessage(), ButtonType.OK).show();
        }
     }
    /**
     * EconomyPrice is a method used to retrieve the economy class price of a specific flight given its ID.
     * This method connects to the database, retrieves the price from the 'Flights' table and returns it.
     * In case of any exceptions, it will print the stack trace.
     * @param flightID the ID of the flight for which the economy class price is to be retrieved.
     * @return the economy class price of the specified flight. If no flight with the given ID is found, returns 0.
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
     * BusinessPrice is a method used to retrieve the business class price of a specific flight given its ID.
     * This method connects to the database, retrieves the price from the 'Flights' table and returns it.
     * In case of any exceptions, it will print the stack trace.
     * @param flightID the ID of the flight for which the business class price is to be retrieved.
     * @return the business class price of the specified flight. If no flight with the given ID is found, returns 0.
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
     * getID() method is used to retrieve the passenger ID from the database.
     * The method reads the database connection properties from the "application.properties.sample" file.
     * It then creates a connection with the database using the JDBC DriverManager class.
     * The method then prepares and executes a SQL query to retrieve the ID of the passenger.
     * If a matching record is found, the method returns the ID of the passenger.
     * @param firstName The first name of the passenger
     * @param lastName The last name of the passenger
     * @param dateOfBirth The date of birth of the passenger
     * @param address The address of the passenger
     * @param email The email of the passenger
     * @return int The ID of the passenger if a match is found, else 0
     */
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

    /**
     * selectedFlight() method is used to set the selected flight information to UI fields.
     * The method retrieves the selected flight information from the flightsTable,
     * sets the values of the various text fields such as source, destination, arrival, departure, etc.
     * The method also sets the default value for the boxBox to "Economy".
     * If the boxBox is empty, it sets the price to the price of the Economy class.
     * @param mouseEvent The mouse event that triggers the method
     */

    public void selectedFlight(MouseEvent mouseEvent) {
      Flights selectedFlight = flightsTable.getSelectionModel().getSelectedItem();
      int idFlight = selectedFlight.getId();
      idShowField.setText(String.valueOf(selectedFlight.getId()));
        sourceShowField.setText(String.valueOf(selectedFlight.getSource()));
        destinationShowField.setText(String.valueOf(selectedFlight.getDestination()));
        arrivalShowField.setText(String.valueOf(selectedFlight.getArrival()));
        arrivalTimeShowField.setText(selectedFlight.getArrivalTime());
        departureShowField.setText(String.valueOf(selectedFlight.getDeparture()));
        departureTimeShowField.setText(selectedFlight.getDepartureTime());
        boxBox.setValue("Economy");
        if(boxBox.getSelectionModel().isEmpty()){
            priceShowField.setText(String.valueOf(selectedFlight.getPriceEconomy()));
        }
        priceShowField.setText(String.valueOf(selectedFlight.getPriceEconomy()));
    }

    public TextField firstNameField;
    public TextField lastNameField;
    public TextField addressField;
    public TextField emailField;
    public DatePicker datePicker;

    /**
     * Check() method is used to check if the passenger information already exists in the database.
     * The method retrieves a list of all passengers and iterates through it to find a match with the input information.
     * If a match is found, the method returns true, otherwise false.
     * @param firstName The first name of the passenger
     * @param lastName The last name of the passenger
     * @param address The address of the passenger
     * @param email The email of the passenger
     * @param dateOfBirth The date of birth of the passenger
     * @return boolean True if the passenger information is found, else False.
     * @throws FlightBookingException If any exception occurs during the execution of the method
     */
    public boolean Check(String firstName, String lastName, String address , String email, LocalDate dateOfBirth) throws FlightBookingException {
        List<Passengers> passengers = passengersManager.getAll();
        for(Passengers temp : passengers){
            if(temp.getName().equals(firstName) && temp.getSurname().equals(lastName) && temp.getAdress().equals(address) && temp.getEmail().equals(email) && temp.getDateOfBirth().equals(dateOfBirth)){
                return true;
            }
        }
        return false;
    }

    /**
     * save() method is used to save the passenger information to the database.
     * The method calls the Check() method to check if the passenger information already exists in the database.
     * If the passenger information doesn't exist, the method creates a new passenger object, sets its values from the UI fields,
     * and saves it to the database using the passengersManager.add() method.
     * If the passenger information already exists, an alert is shown to the user.
     * @param actionEvent The action event that triggers the method
     * @throws FlightBookingException If any exception occurs during the execution of the method
     */

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
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Passenger added successfully");
                    alert.setContentText("Passenger with this data is successfully added");
                    alert.showAndWait();
                }catch(FlightBookingException f){
                    new Alert(Alert.AlertType.NONE, f.getMessage(), ButtonType.OK).show();
                }
            }else{

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Passenger already exists");
                alert.setContentText("Passenger with this data is already registered");
                alert.showAndWait();
            }

    }

    /**
     * bookFlight() method is used to book a flight for the passenger.
     * The method calls the getID() method to retrieve the passenger ID from the database.
     * If the passenger ID is found, the method creates a new booking object, sets its values, and saves it to the database using the bookingsManager.add() method.
     * An alert is shown to the user to indicate the success or failure of the booking.
     * @param actionEvent The action event that triggers the method
     * @throws FlightBookingException If any exception occurs during the execution of the method
     */

    public void bookFlight(ActionEvent actionEvent) throws FlightBookingException {

        int passengerID = getID(firstNameField.getText(),lastNameField.getText(),datePicker.getValue(),addressField.getText(),emailField.getText());
        try {
        Tickets ticket = new Tickets();
        ticket.setFlightID(Integer.parseInt(idShowField.getText()));
        ticket.setPassengerID(passengerID);
        ticket.setTravelClass(boxBox.getSelectionModel().getSelectedItem().toString());
        ticket.setPrice(Integer.parseInt(priceShowField.getText()));
        ticket = ticketsManager.add(ticket);
        } catch (FlightBookingException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Success");
        alert.setContentText("Flight for this passenger is successfully booked");
        alert.showAndWait();

    }

}
