package ba.unsa.etf.rpr.dao;

import java.sql.Connection;
/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Safet Čomor
 */

public class DaoFactory {
    /**
     * Instance of FlightsDao implementation
     * **/
    private static final FlightsDao flightsDao = FlightsDaoSQLImpl.getInstance();
    /**
     *  Instance of PassengersDao implementation
     **/
    private static final PassengersDao passengersDao = PassengersDaoSQLImpl.getInstance();
    /**
     *  Instance of TicketsDao implementation
     **/
    private static final TicketsDao ticketsDao = TicketsDaoSQLImpl.getInstance();
    /**
     * Private constructor to prevent creating instances of this class
     */
    private DaoFactory(){
    }
    /**
     * Returns an instance of FlightsDao implementation.
     * @return an instance of FlightsDao implementation.
     */
    public static FlightsDao flightsDao(){
        return flightsDao;
    }
    /**
     * Returns an instance of PassengersDao implementation.
     * @return an instance of PassengersDao implementation.
     */
    public static PassengersDao passengersDao(){
        return passengersDao;
    }
    /**
     * Returns an instance of TicketsDao implementation.
     * @return an instance of TicketsDao implementation.
     */
    public static TicketsDao ticketsDao(){
        return ticketsDao;
    }

}
