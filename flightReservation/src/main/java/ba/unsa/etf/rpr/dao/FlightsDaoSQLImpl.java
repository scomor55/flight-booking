package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Flights;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightsDaoSQLImpl implements FlightsDao {

    private Connection connection;
    public FlightsDaoSQLImpl(){
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/root", "root", "root");
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public Flights getById(int id) {
        String query = "SELECT * FROM quotes WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Flights flight = new Flights();
                flight.setFlightID(rs.getInt("flightID"));
                flight.setSource(rs.getString("source"));
                flight.setDestination(rs.getString("destination"));
                flight.setDeparture(rs.getString("departure"));
                flight.setArrival(rs.getString("arrival"));
                flight.setAvalivableSeats(rs.getInt("avalivableSeats"));
                rs.close();
                return flight;
            }else{
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    @Override
    public Flights add(Flights item) {
        return null;
    }

    @Override
    public Flights update(Flights item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Flights> getAll() {
        return null;
    }

    /**
     * @param flightDestination search string for avalivable destinations
     * @return list of destinations
     * @author Safet Čomor
     */

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
                f.setFlightID(rs.getInt(1));
                f.setSource(rs.getString(2));
                f.setDestination(rs.getString(3));
                f.setDeparture(rs.getString(4));
                f.setArrival(rs.getString(5));
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
