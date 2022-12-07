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
        return null;
    }

    @Override
    public Flights add(Flights item) {
        return null;
    }

    @Override
    public Flights update(Flights item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Flights> getAll() {
        return null;
    }

    @Override
    public List<Flights> searchByDestination(String flightDestination) {
        return null;
    }
}
