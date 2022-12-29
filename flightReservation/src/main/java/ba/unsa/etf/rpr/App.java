package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.TicketsDao;
import ba.unsa.etf.rpr.dao.PassengersDao;
import ba.unsa.etf.rpr.dao.FlightsDao;
import ba.unsa.etf.rpr.dao.PassengersDaoSQLImpl;
import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.FlightsDaoSQLImpl;
import ba.unsa.etf.rpr.dao.TicketsDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Passengers;
import ba.unsa.etf.rpr.domain.Tickets;
import ba.unsa.etf.rpr.exceptions.FlightBookingException;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Hello world!
 *
 */

public class App
{
    public static void main( String[] args )
    {
        TicketsDao dao = new TicketsDaoSQLImpl();
        Tickets tickets = new Tickets();

        tickets.setId(3);
        tickets.setFlightID(3);
        tickets.setPassengerID(1);
        tickets.setTravelClass("Business");
        tickets.setPrice(1000);


        ArrayList<Tickets> t = null;
        try {
            t = new ArrayList<Tickets>(dao.searchByClass("Business"));
        } catch (FlightBookingException e) {
            throw new RuntimeException(e);
        }
        for(Tickets temp : t){
            System.out.println(temp);
        }

        PassengersDao dao1 = new PassengersDaoSQLImpl();
        ArrayList<Passengers> passengers = null;
        try {
            passengers = new ArrayList<Passengers>(dao1.searchByName("Denan"));
        } catch (FlightBookingException e) {
            throw new RuntimeException(e);
        }
        for(Passengers temp: passengers){
            System.out.println(temp);
        }

        ArrayList<Passengers> passengers1 = null;
        try {
            passengers1 = new ArrayList<Passengers>(dao1.searchBySurname("Krupalija"));
        } catch (FlightBookingException e) {
            throw new RuntimeException(e);
        }
        for(Passengers temp: passengers1){
            System.out.println(temp);
        }

    }
   /*public static void main( String[] args ){
       Runtime.Version version = Runtime.version();
       System.out.println("Java version: " + version);
   }*/

}


/*package ba.unsa.etf.rpr;*/

/*import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // FXML style
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        primaryStage.setTitle("Login App");
        primaryStage.setScene(new Scene(root, 300, 110));
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}*/