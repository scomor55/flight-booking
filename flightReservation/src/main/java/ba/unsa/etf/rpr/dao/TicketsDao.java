package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.domain.Tickets;

import java.util.List;

/**
 * Dao interface for Tickets domain bean
 *
 * @author Safet Čomor
 */

public interface TicketsDao extends Dao<Tickets>{
    List<Tickets> searchByClass(String ticketClass) ;
}
