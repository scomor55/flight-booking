package ba.unsa.etf.rpr.dao;

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
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setSurname(rs.getString("surname"));
            p.setDateOfBirth(rs.getDate("dateOfBirth").toLocalDate());
            p.setAdress(rs.getString("address"));
            p.setEmail(rs.getString("email"));
            return p;
        } catch (Exception e) {
            throw new FlightBookingException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Passengers object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("name", object.getName());
        item.put("surname", object.getSurname());
        item.put("dateOfBirth", object.getDateOfBirth());
        item.put("address", object.getAdress());
        item.put("email",object.getEmail());
        return item;
    }

    @Override
    public List<Passengers> searchByName(String name)throws FlightBookingException{
        return executeQuery("SELECT * FROM Passengers WHERE name LIKE concat('%', ?, '%')",new Object[]{name});
    }

    @Override
    public List<Passengers> searchBySurname(String surname) throws FlightBookingException{

        return executeQuery("SELECT * FROM Passengers WHERE surname LIKE concat('%', ?, '%')",new Object[]{surname});
    }


}