package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.domain.Passengers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengersDaoSQLImpl implements PassengersDao {

    private Connection connection;
    public PassengersDaoSQLImpl(){
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/root", "root", "root");
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public Passengers getById(int id) {
        String query = "SELECT * FROM Passengers WHERE passengerID = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Passengers passenger = new Passengers();
                passenger.setPassengerID(rs.getInt("passengerID"));
                passenger.setName(rs.getString("name"));
                passenger.setSurname(rs.getString("surname"));
                passenger.setDateOfBirth(rs.getDate("dateOfBirth"));
                passenger.setAdress(rs.getString("address"));
                rs.close();
                return passenger;
            }else{
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    @Override
    public Passengers add(Passengers item) {
        String insert = "INSERT INTO Passengers(name) VALUES(?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getName());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setPassengerID(rs.getInt(1)); //set id to return it back
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Passengers update(Passengers item) {
        String insert = "UPDATE Passengers SET name = ? WHERE passengerID = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getName());
            stmt.setObject(2, item.getPassengerID());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM Passengers WHERE passengerID = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Passengers> getAll() {
        String query = "SELECT * FROM Passengers";
        List<Passengers> passengers = new ArrayList<Passengers>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Passengers passenger = new Passengers();
                passenger.setPassengerID(rs.getInt("passengerID"));
                passenger.setName(rs.getString("name"));
                passenger.setSurname(rs.getString("surname"));
                passenger.setDateOfBirth(rs.getDate("dateOfBirth"));
                passenger.setAdress(rs.getString("address"));
                passengers.add(passenger);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return passengers;
    }

    @Override
    public List<Passengers> searchByName(String name) {
        String query = "SELECT * FROM Passengers WHERE name LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Passengers> passengersLista = new ArrayList<>();
            while (rs.next()) {
                Passengers p = new Passengers();
                p.setPassengerID(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setSurname(rs.getString(3));
                p.setDateOfBirth(rs.getDate(4));
                p.setAdress(rs.getString(5));
                passengersLista.add(p);
            }
            return passengersLista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Passengers> searchBySurname(String surname) {
        String query = "SELECT * FROM Passengers WHERE surname LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, surname);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Passengers> passengersLista = new ArrayList<>();
            while (rs.next()) {
                Passengers p = new Passengers();
                p.setPassengerID(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setSurname(rs.getString(3));
                p.setDateOfBirth(rs.getDate(4));
                p.setAdress(rs.getString(5));
                passengersLista.add(p);
            }
            return passengersLista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
