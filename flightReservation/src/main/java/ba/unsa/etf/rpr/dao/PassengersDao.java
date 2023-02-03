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
    /**
     * Searches for Passengers objects with a name matching the specified name.
     * @param name The name to search for.
     * @return A list of Passengers objects with matching names.
     * @throws FlightBookingException if an error occurs during the search.
     */
    List<Passengers> searchByName(String name) throws FlightBookingException;
    /**
     * Searches for Passengers objects with a surname matching the specified surname.
     * @param surname The surname to search for.
     * @return A list of Passengers objects with matching surnames.
     * @throws FlightBookingException if an error occurs during the search.
     */
    List<Passengers> searchBySurname(String surname)throws FlightBookingException;

}
