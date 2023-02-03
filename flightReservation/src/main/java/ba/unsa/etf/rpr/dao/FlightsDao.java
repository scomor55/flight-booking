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

    List<Flights> searchBySource(String flightSource) throws FlightBookingException ;
    List<Flights> searchByDestination(String flightDestination) throws FlightBookingException ;
    List<Flights> searchBySourceAndDestination(String flightSource,String flightDestination) throws FlightBookingException ;

    public List<Flights> searchFlightsForPassenger(int passengerID ) throws FlightBookingException;

}
