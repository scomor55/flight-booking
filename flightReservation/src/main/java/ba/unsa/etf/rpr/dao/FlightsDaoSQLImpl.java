package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Flights;


import ba.unsa.etf.rpr.exceptions.FlightBookingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;

/**
 * MySQLs implementation of the DAO
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
            flight.setId(rs.getInt("flightID"));
            flight.setSource(rs.getString("source"));
            flight.setDestination(rs.getString("destination"));
            flight.setDeparture(rs.getDate("departure"));
            flight.setArrival(rs.getDate("arrival"));
            flight.setAvalivableSeats(rs.getInt("avalivableSeats"));
            return flight;
        } catch (SQLException e) {
            throw new FlightBookingException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Flights object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("flightID", object.getId());
        row.put("source", object.getSource());
        row.put("destination", object.getDestination());
        row.put("departure", object.getDeparture());
        row.put("arrival", object.getArrival());
        row.put("avalivableSeats", object.getAvalivableSeats());
        return row;
    }



    @Override
    public List<Flights> searchByDestination(String flightDestination) {
        String query = "SELECT * FROM Flights WHERE destinacija LIKE concat('%',?,'%')";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1,flightDestination);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Flights> flightsList = new ArrayList<>();
            while(rs.next()){
                Flights f = new Flights();
                f.setId(rs.getInt(1));
                f.setSource(rs.getString(2));
                f.setDestination(rs.getString(3));
                f.setDeparture(rs.getDate(4));
                f.setArrival(rs.getDate(5));
                f.setAvalivableSeats(rs.getInt(6));
                flightsList.add(f);
            }
            return flightsList;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
