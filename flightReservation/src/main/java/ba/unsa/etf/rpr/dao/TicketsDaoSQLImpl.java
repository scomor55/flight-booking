package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Tickets;
import ba.unsa.etf.rpr.exceptions.FlightBookingException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TicketsDaoSQLImpl extends AbstractDao<Tickets> implements TicketsDao{

    private static TicketsDaoSQLImpl instance = null;
    private TicketsDaoSQLImpl() {
        super("Tickets");
    }
    public static TicketsDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new TicketsDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }
    @Override
    public Tickets row2object(ResultSet rs) throws FlightBookingException{
        try {
            Tickets p = new Tickets();
            p.setId(rs.getInt("id"));
            p.setFlightID(rs.getInt("flightID"));
            p.setPassengerID(rs.getInt("passengerID"));
            p.setTravelClass(rs.getString("class"));
            p.setPrice(rs.getInt("price"));
            return p;
        } catch (Exception e) {
            throw new FlightBookingException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Tickets object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("flightID", object.getFlightID());
        item.put("passengerID", object.getPassengerID());
        item.put("class", object.getTravelClass());
        item.put("price", object.getPrice());
        return item;
    }


    @Override
    public List<Tickets> searchByClass(String ticketClass) throws FlightBookingException{
        return executeQuery("SELECT * FROM Tickets WHERE class LIKE concat('%', ?, '%')",new Object[]{ticketClass});
    }
}
