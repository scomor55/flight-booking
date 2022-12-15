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
                Passengers p = new Passengers();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setSurname(rs.getString(3));
                p.setDateOfBirth(rs.getDate(4));
                p.setAdress(rs.getString(5));
                passengersList.add(p);
            }
            return passengersList;
        } catch (SQLException e) {
            throw new FlightBookingException(e.getMessage(), e);
        }
    }

    @Override
    public List<Passengers> searchBySurname(String surname) {
        String query = "SELECT * FROM Passengers WHERE surname LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, surname);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Passengers> passengersList = new ArrayList<>();
            while (rs.next()) {
                Passengers p = new Passengers();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setSurname(rs.getString(3));
                p.setDateOfBirth(rs.getDate(4));
                p.setAdress(rs.getString(5));
                passengersList.add(p);
            }
            return passengersList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
