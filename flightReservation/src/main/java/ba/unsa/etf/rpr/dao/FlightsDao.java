package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightBookingException;

import java.util.List;
/**
 * Dao interface for Passengers domain bean
 *
 * @author Safet Čomor
 */
public interface FlightsDao extends Dao<Flights> {

    List<Flights> searchByDestination(String flightDestination) throws FlightBookingException ;

}
