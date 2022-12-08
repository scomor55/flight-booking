package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.domain.Tickets;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketsDaoSQLImpl implements TicketsDao{

    private Connection connection;

    public TicketsDaoSQLImpl(){
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/root", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tickets getById(int id) {
        String query = "SELECT * FROM Tickets WHERE ticketID = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // result set is iterator.
                Tickets ticket = new Tickets();
                ticket.setTicketID(rs.getInt("ticketID"));
                ticket.setFlightID(rs.getInt("flightID"));
                ticket.setPassengerID(rs.getInt("passengerID"));
                ticket.setTravelClass(rs.getString("class"));
                ticket.setPrice(rs.getInt("price"));
                rs.close();
                return ticket;
            } else {
                return null; // if there is no elements in the result set return null
            }
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    @Override
    public Tickets add(Tickets item) {
     //   String insert = "INSERT INTO Tickets(name) VALUES(?)";
        String insert = "INSERT INTO Tickets(class) VALUES(?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getTravelClass());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setTicketID(rs.getInt(1)); //set id to return it back
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Tickets update(Tickets item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Tickets> getAll() {
        return null;
    }

    @Override
    public List<Tickets> searchByClass(String ticketClass) {
        return null;
    }
}
