package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.domain.Passengers;

import java.util.List;

/**
 * Dao interface for Passengers domain bean
 *
 * @author Safet Čomor
 */
public interface PassengersDao extends Dao<Passengers> {
    List<Passengers> searchByName(String name);
    List<Passengers> searchBySurname(String surname);
}
