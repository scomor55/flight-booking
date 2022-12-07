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
                passenger.setDateOfBirth(rs.getDate("dateOfBirth"));
                passenger.setAdress(rs.getString("adress"));
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
        return null;
    }

    @Override
    public Passengers update(Passengers item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Passengers> getAll() {
        return null;
    }

    @Override
    public List<Passengers> searchByName(String name) {
        return null;
    }

    @Override
    public List<Passengers> searchBySurname(String surname) {
        return null;
    }
}
