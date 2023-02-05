package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Flights;


import ba.unsa.etf.rpr.exceptions.FlightBookingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;

/**
 * MySQL's implementation of the DAO
 * @author Safet Čomor
 */

public class FlightsDaoSQLImpl extends AbstractDao<Flights> implements FlightsDao {

    private static FlightsDaoSQLImpl instance = null;
    /**
     * Private constructor that calls the super class constructor with the table name "Flights".
     */
   private FlightsDaoSQLImpl() {
        super("Flights");
    }
    /**
     * Returns the singleton instance of the class. If the instance is null, a new instance is created.
     * @return singleton instance of the FlightsDaoSQLImpl class
     */
    public static FlightsDaoSQLImpl getInstance(){
       if (instance == null){
           instance = new FlightsDaoSQLImpl();
       }
       return instance;
    }
    /**
     * Removes the singleton instance if it exists.
     */
    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }
    /**
     * This method maps the data from a {@link ResultSet} to a {@link Flights} object.
     * It retrieves the data for each column of the Flights table and sets it to the corresponding
     * field of the Flights object.
     * @param rs a ResultSet object representing a row in the Flights table
     * @return a Flights object with data from the ResultSet
     * @throws FlightBookingException if a database access error occurs
     */
    @Override
    public Flights row2object(ResultSet rs) throws FlightBookingException {
        try {

            Flights flight = new Flights();
            flight.setId(rs.getInt("id"));
            flight.setSource(rs.getString("source"));
            flight.setDestination(rs.getString("destination"));
            flight.setDeparture(rs.getDate("departure").toLocalDate());
            flight.setDepartureTime(rs.getString("departureTime"));
            flight.setArrival(rs.getDate("arrival").toLocalDate());
            flight.setArrivalTime(rs.getString("arrivalTime"));
            flight.setSeats(rs.getInt("seats"));
            flight.setPriceEconomy(rs.getInt("priceEconomy"));
            flight.setPriceBusiness(rs.getInt("priceBusiness"));
            return flight;
        } catch (SQLException e) {
            throw new FlightBookingException(e.getMessage(), e);
        }
    }
    /**
     * Converts a Flights object to a Map representation.
     * @param object the Flights object to be converted
     * @return a Map representation of the Flights object
     */
    @Override
    public Map<String, Object> object2row(Flights object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("source", object.getSource());
        row.put("destination", object.getDestination());
        row.put("departure", object.getDeparture());
        row.put("departureTime",object.getDepartureTime());
        row.put("arrival", object.getArrival());
        row.put("arrivalTime",object.getArrivalTime());
        row.put("seats", object.getSeats());
        row.put("priceEconomy", object.getPriceEconomy());
        row.put("priceBusiness", object.getPriceBusiness());
        return row;
    }
    /**
     * Searches for flights based on the source.
     * @param flightSource the source of the flight
     * @return a List of Flights that match the source
     * @throws FlightBookingException if there is an error in the query execution
     */
    @Override
    public List<Flights> searchBySource(String flightSource) throws FlightBookingException{
        return executeQuery("SELECT * FROM Flights WHERE source LIKE concat('%',?,'%')",new Object[]{flightSource});
    }
    /**
     * Searches for flights based on the destination.
     * @param flightDestination the destination of the flight
     * @return a List of Flights that match the destination
     * @throws FlightBookingException if there is an error in the query execution
     */
    @Override
    public List<Flights> searchByDestination(String flightDestination) throws FlightBookingException{
        return executeQuery("SELECT * FROM Flights WHERE destination LIKE concat('%',?,'%')",new Object[]{flightDestination});
    }
    /**
     * Searches for flights based on the source and destination.
     * @param flightSource the source of the flight
     * @param flightDestination the destination of the flight
     * @return a List of Flights that match the source and destination
     * @throws FlightBookingException if there is an error in the query execution
     */
    @Override
    public List<Flights> searchBySourceAndDestination(String flightSource,String flightDestination) throws FlightBookingException{
        return executeQuery("SELECT * FROM Flights WHERE source LIKE concat('%',?,'%') AND destination LIKE concat('%',?,'%')",new Object[]{flightSource,flightDestination});
    }

}
