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
                f.setDeparture(rs.getString(2));
                f.setDestination(rs.getString(3));
                f.setDeparture(rs.getString(4));
                f.setArrival(rs.getString(5));
                f.setAvalivableSeats(rs.getInt(6));
            }
            return flightsList;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
