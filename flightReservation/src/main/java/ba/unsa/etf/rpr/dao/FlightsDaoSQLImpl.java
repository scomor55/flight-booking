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
                flight.setDeparture(rs.getDate("departure"));
                flight.setArrival(rs.getDate("arrival"));
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
        /* TRY USING CLASSES IF THIS DOESNT WORK */
       /* String insert = "INSERT INTO Flights(flightID) VALUES(?)";*/
        String insert = "INSERT INTO Flights(source) VALUES(?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getSource());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setFlightID(rs.getInt(1)); //set id to return it back
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Flights update(Flights item) {
        String insert = "UPDATE flights SET source = ? WHERE flightID = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getSource());
            stmt.setObject(2, item.getFlightID());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM categories WHERE flightID = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
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
