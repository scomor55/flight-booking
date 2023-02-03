package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.domain.Tickets;
import ba.unsa.etf.rpr.exceptions.FlightBookingException;

import java.util.List;

/**
 * Dao interface for Tickets domain bean
 *
 * @author Safet Čomor
 */

public interface TicketsDao extends Dao<Tickets>{
    /**
     * This method retrieves a list of {@link Tickets} objects based on the ticket class provided.
     * @param ticketClass The class of the tickets to be retrieved.
     * @return A list of {@link Tickets} objects that match the specified ticket class.
     * @throws FlightBookingException if there was an error while searching for tickets by class.
     */
    List<Tickets> searchByClass(String ticketClass) throws FlightBookingException ;

}
