package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.domain.Passengers;

import ba.unsa.etf.rpr.exceptions.FlightBookingException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PassengersDaoSQLImpl extends AbstractDao<Passengers> implements PassengersDao {


    public PassengersDaoSQLImpl() {
        super("Passengers");
    }

    @Override
    public Passengers row2object(ResultSet rs) throws FlightBookingException{
        try {
            Passengers p = new Passengers();
            p.setId(rs.getInt("passengerID"));
            p.setName(rs.getString("name"));
            p.setSurname(rs.getString("surname"));
            p.setDateOfBirth(rs.getDate("dateOfBirth"));
            p.setAdress(rs.getString("address"));
            return p;
        } catch (Exception e) {
            throw new FlightBookingException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Passengers object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("passengerID", object.getId());
        item.put("name", object.getName());
        item.put("surname", object.getSurname());
        item.put("dateOfBirth", object.getDateOfBirth());
        item.put("address", object.getAdress());
        return item;
    }

    @Override
    public List<Passengers> searchByName(String name)throws FlightBookingException{
        String query = "SELECT * FROM Passengers WHERE name LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Passengers> passengersList = new ArrayList<>();
            while (rs.next()) {
                passengersList.add(row2object(rs));
            }
            return passengersList;
        } catch (SQLException e) {
            throw new FlightBookingException(e.getMessage(), e);
        }
    }

    @Override
    public List<Passengers> searchBySurname(String surname) throws FlightBookingException{
        String query = "SELECT * FROM Passengers WHERE surname LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, surname);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Passengers> passengersList = new ArrayList<>();
            while (rs.next()) {
                passengersList.add(row2object(rs));
            }
            return passengersList;
        } catch (SQLException e) {
            throw new FlightBookingException(e.getMessage(), e);
        }
    }


}
