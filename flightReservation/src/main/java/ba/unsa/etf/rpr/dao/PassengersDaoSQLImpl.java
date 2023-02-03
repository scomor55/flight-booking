package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Passengers;

import ba.unsa.etf.rpr.exceptions.FlightBookingException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PassengersDaoSQLImpl extends AbstractDao<Passengers> implements PassengersDao {

    private static PassengersDaoSQLImpl instance = null;
    /**
     * Private constructor that calls the super class constructor with the table name "Passengers".
     */
    private PassengersDaoSQLImpl() {
        super("Passengers");
    }
    /**
     * Returns the single instance of this class. If no instance exists, creates one.
     * @return The single instance of this class
     */
    public static PassengersDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new PassengersDaoSQLImpl();
        return instance;
    }
    /**
     * Removes the current instance of this class.
     */
    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }
    /**
     * Converts a row from the "Passengers" table in the database to a Passengers object.
     * @param rs The ResultSet representing a row from the "Passengers" table.
     * @return A Passengers object created from the data in the ResultSet.
     * @throws FlightBookingException If there was an error reading from the ResultSet.
     */
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
    /**
     * Converts a Passengers object to a map representing a row in the "Passengers" table.
     * @param object The Passengers object to be converted.
     * @return A Map representing a row in the "Passengers" table, with keys as column names and values as column values.
     */
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
    /**
     * This method is used to search the passengers by their name.
     * @param name Name of the passenger to be searched.
     * @return A List of {@link Passengers} objects that match the name provided as a parameter.
     * @throws FlightBookingException when an exception occurs during the execution of this method.
     */
    @Override
    public List<Passengers> searchByName(String name)throws FlightBookingException{
        return executeQuery("SELECT * FROM Passengers WHERE name LIKE concat('%', ?, '%')",new Object[]{name});
    }
    /**
     * This method is used to search the passengers by their surname.
     * @param surname Surname of the passenger to be searched.
     * @return A List of {@link Passengers} objects that match the surname provided as a parameter.
     * @throws FlightBookingException when an exception occurs during the execution of this method.
     */
    @Override
    public List<Passengers> searchBySurname(String surname) throws FlightBookingException{

        return executeQuery("SELECT * FROM Passengers WHERE surname LIKE concat('%', ?, '%')",new Object[]{surname});
    }


}