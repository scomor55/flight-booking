package ba.unsa.etf.rpr.dao;

import java.sql.Connection;
/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Safet Čomor
 */

public class DaoFactory {

    private static final FlightsDao flightsDao = FlightsDaoSQLImpl.getInstance();

    private static final PassengersDao passengersDao = PassengersDaoSQLImpl.getInstance();
    private static final TicketsDao ticketsDao = TicketsDaoSQLImpl.getInstance();

    private DaoFactory(){
    }

    public static FlightsDao flightsDao(){
        return flightsDao;
    }

    public static PassengersDao passengersDao(){
        return passengersDao;
    }

    public static TicketsDao ticketsDao(){
        return ticketsDao;
    }

}
