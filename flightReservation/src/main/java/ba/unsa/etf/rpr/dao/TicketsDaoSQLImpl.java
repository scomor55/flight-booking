package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Tickets;
import ba.unsa.etf.rpr.exceptions.FlightBookingException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * managing Tickets data using SQL database.
 * @author Safet Čomor
 */
public class TicketsDaoSQLImpl extends AbstractDao<Tickets> implements TicketsDao{

    private static TicketsDaoSQLImpl instance = null;
    /**
     * Private constructor that calls the super class constructor with the table name "Tickets".
     */
    private TicketsDaoSQLImpl() {
        super("Tickets");
    }
    /**
     * Returns the single instance of this class. If no instance exists, creates one.
     * @return The single instance of this class
     */
    public static TicketsDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new TicketsDaoSQLImpl();
        return instance;
    }
    /**
     * Removes the current instance of this class.
     */
    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }
    /**
     * Converts the current row in the ResultSet to a Tickets object.
     * @param rs ResultSet to be converted to a Tickets object.
     * @return Tickets object
     * @throws FlightBookingException if an error occurs during the conversion
     */
    @Override
    public Tickets row2object(ResultSet rs) throws FlightBookingException{
        try {
            Tickets p = new Tickets();
            p.setId(rs.getInt("id"));
            p.setFlightID(rs.getInt("flightID"));
            p.setPassengerID(rs.getInt("passengerID"));
            p.setTravelClass(rs.getString("class"));
            p.setPrice(rs.getInt("price"));
            return p;
        } catch (Exception e) {
            throw new FlightBookingException(e.getMessage(), e);
        }
    }

    /**
     * Converts the given Tickets object to a Map representing the data of a row in the database table.
     * @param object the Tickets object to be converted
     * @return Map representation of the data
     */
    @Override
    public Map<String, Object> object2row(Tickets object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("flightID", object.getFlightID());
        item.put("passengerID", object.getPassengerID());
        item.put("class", object.getTravelClass());
        item.put("price", object.getPrice());
        return item;
    }

    /**
     * Searches the Tickets in the database by ticket class.
     * @param ticketClass the class to search the Tickets by
     * @return list of Tickets with the given class
     * @throws FlightBookingException if an error occurs during the search
     */
    @Override
    public List<Tickets> searchByClass(String ticketClass) throws FlightBookingException{
        return executeQuery("SELECT * FROM Tickets WHERE class LIKE concat('%', ?, '%')",new Object[]{ticketClass});
    }
}
