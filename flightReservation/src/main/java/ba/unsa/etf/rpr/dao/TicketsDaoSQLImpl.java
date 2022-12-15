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
            this.connection = DriverManager.getConnection("jdbc:mysql://sql8.freemysqlhosting.net:3306/sql8582914", "sql8582914", "wqZdXspcAm");
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
                ticket.setId(rs.getInt("ticketID"));
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
            item.setId(rs.getInt(1)); //set id to return it back
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Tickets update(Tickets item) {
        String insert = "UPDATE Tickets SET class = ? WHERE ticketID = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getTravelClass());
            stmt.setObject(2, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM Tickets WHERE ticketID = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Tickets> getAll() {
        String query = "SELECT * FROM Tickets";
        List<Tickets> tickets = new ArrayList<Tickets>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Tickets ticket = new Tickets();
                ticket.setId(rs.getInt("ticketID"));
                ticket.setFlightID(rs.getInt("flightID"));
                ticket.setPassengerID(rs.getInt("passengerID"));
                ticket.setTravelClass(rs.getString("class"));
                ticket.setPrice(rs.getInt("price"));
                tickets.add(ticket);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return tickets;
    }

    @Override
    public List<Tickets> searchByClass(String ticketClass) {
        String query = "SELECT * FROM Tickets WHERE class LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, ticketClass);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Tickets> ticketsList = new ArrayList<>();
            while (rs.next()) {
                Tickets t = new Tickets();
                t.setId(rs.getInt(1));
                t.setFlightID(rs.getInt(2));
                t.setPassengerID(rs.getInt(3));
                t.setTravelClass(rs.getString(4));
                t.setPrice(rs.getInt(5));
                ticketsList.add(t);
            }
            return ticketsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
