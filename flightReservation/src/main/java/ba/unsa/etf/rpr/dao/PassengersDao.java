package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.domain.Passengers;
import ba.unsa.etf.rpr.exceptions.FlightBookingException;

import java.time.LocalDate;
import java.util.List;

/**
 * Dao interface for Passengers domain bean
 *
 * @author Safet Čomor
 */
public interface PassengersDao extends Dao<Passengers> {
    List<Passengers> searchByName(String name) throws FlightBookingException;
    List<Passengers> searchBySurname(String surname)throws FlightBookingException;

}
