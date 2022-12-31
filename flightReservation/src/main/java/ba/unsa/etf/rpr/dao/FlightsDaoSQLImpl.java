package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Flights;


import ba.unsa.etf.rpr.exceptions.FlightBookingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;

/**
 * MySQL implementation of the DAO
 * @author Safet Čomor
 */

public class FlightsDaoSQLImpl extends AbstractDao<Flights> implements FlightsDao {


    public FlightsDaoSQLImpl() {
        super("Flights");
    }


    @Override
    public Flights row2object(ResultSet rs) throws FlightBookingException {
        try {

            Flights flight = new Flights();
            flight.setId(rs.getInt("id"));
            flight.setSource(rs.getString("source"));
            flight.setDestination(rs.getString("destination"));
            flight.setDeparture(rs.getDate("departure").toLocalDate());
            flight.setArrival(rs.getDate("arrival").toLocalDate());
            flight.setSeats(rs.getInt("seats"));
            flight.setPriceEconomy(rs.getInt("priceEconomy"));
            flight.setPriceBusiness(rs.getInt("priceBusiness"));
            return flight;
        } catch (SQLException e) {
            throw new FlightBookingException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Flights object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("source", object.getSource());
        row.put("destination", object.getDestination());
        row.put("departure", object.getDeparture());
        row.put("arrival", object.getArrival());
        row.put("seats", object.getSeats());
        row.put("priceEconomy", object.getPriceEconomy());
        row.put("priceBusiness", object.getPriceBusiness());
        return row;
    }

    @Override
    public List<Flights> searchBySource(String flightSource) throws FlightBookingException{
        return executeQuery("SELECT * FROM Flights WHERE source LIKE concat('%',?,'%')",new Object[]{flightSource});
    }

    @Override
    public List<Flights> searchByDestination(String flightDestination) throws FlightBookingException{
        return executeQuery("SELECT * FROM Flights WHERE destination LIKE concat('%',?,'%')",new Object[]{flightDestination});
    }
    @Override
    public List<Flights> searchBySourceAndDestination(String flightSource,String flightDestination) throws FlightBookingException{
        return executeQuery("SELECT * FROM Flights WHERE source LIKE concat('%',?,'%') AND destination LIKE concat('%',?,'%')",new Object[]{flightSource,flightDestination});
    }

}
