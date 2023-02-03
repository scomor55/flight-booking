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

    /**
     * Search for flights based on their source location.
     * @param flightSource the source location of the flight.
     * @return a list of flights with the specified source location.
     * @throws FlightBookingException if an error occurs while searching for flights.
*/
    List<Flights> searchBySource(String flightSource) throws FlightBookingException ;
    /**
     * Search for flights based on their destination location.
     * @param flightDestination the destination location of the flight.
     * @return a list of flights with the specified destination location.
     * @throws FlightBookingException if an error occurs while searching for flights.
     */
    List<Flights> searchByDestination(String flightDestination) throws FlightBookingException ;
    /**
     * Search for flights based on both their source and destination location.
     * @param flightSource the source location of the flight.
     * @param flightDestination the destination location of the flight.
     * @return a list of flights with the specified source and destination location.
     * @throws FlightBookingException if an error occurs while searching for flights.
     */
    List<Flights> searchBySourceAndDestination(String flightSource,String flightDestination) throws FlightBookingException ;
    /**
     * Search for flights for a specific passenger.
     * @param passengerID the ID of the passenger.
     * @return a list of flights for the specified passenger.
     * @throws FlightBookingException if an error occurs while searching for flights.
     */
    public List<Flights> searchFlightsForPassenger(int passengerID ) throws FlightBookingException;

}
