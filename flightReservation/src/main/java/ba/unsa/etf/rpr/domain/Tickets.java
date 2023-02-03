package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

 /**
  * The Tickets class represents a ticket for a flight.
  * It implements the Idable interface, which requires the implementation of the `getId` and `setId` methods.
  * @author  Safet Čomor
        */
public class Tickets implements Idable{
    private int ticketID;
    private int flightID;
    private int passengerID;
    private String travelClass;
    private int price;

    /**
    * Gets the ticket ID.
    * @return the ticket ID.
    **/
 public int getId() {
        return ticketID;
    }
     /**
      * Sets the ticket ID.
      * @param ticketID the ticket ID to set.
      */
    public void setId(int ticketID) {
        this.ticketID = ticketID;
    }
     /**
      * Gets the flight ID.
      * @return the flight ID.
      */
    public int getFlightID() {
        return flightID;
    }
     /**
      * Sets the flight ID.
      * @param flightID the flight ID to set.
      */
    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }
     /**
      * Gets the passenger ID.
      * @return the passenger ID.
      */
    public int getPassengerID() {
        return passengerID;
    }
     /**
      * Sets the passenger ID.
      * @param passengerID the passenger ID to set.
      */
    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }
     /**
      * Gets the travel class.
      * @return the travel class.
      */
    public String getTravelClass() {
        return travelClass;
    }
     /**
      * Sets the travel class.
      * @param travelClass the travel class to set.
      */
    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }
     /**
      * Gets the price of the ticket.
      * @return the price of the ticket.
      */
    public int getPrice() {
        return price;
    }
     /**
      * Sets the price of the ticket.
      * @param price the price to set.
      */
    public void setPrice(int price) {
        this.price = price;
    }
     /**
      * Overridden method that returns a string representation of the {@code Tickets} object.
      * The string contains the ticketID, flightID, passengerID, travelClass, and price of the ticket.
      * @return String representation of the {@code Tickets} object.
      */
      @Override
    public String toString() {
        return "Tickets{" +
                "ticketID=" + ticketID +
                ", flightID=" + flightID +
                ", passengerID=" + passengerID +
                ", class:'" + travelClass + '\'' +
                ", price=" + price +
                '}';
    }
/**
 * Overridden method that checks if two {@code Tickets} objects are equal.
 * The tickets are considered equal if their ticketIDs are equal.
 * @param o Object to compare with the current {@code Tickets} object.
 * @return {@code true} if the objects are equal, {@code false} otherwise.
 */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tickets ticket = (Tickets) o;
        return ticketID == ticket.ticketID;
    }
     /**
      Overridden method that calculates the hash code of the {@code Tickets} object.
      The hash code is calculated based on the ticketID, flightID, passengerID, travelClass, and price.
      @return Hash code of the {@code Tickets} object.
      */
    @Override
    public int hashCode() {
        return Objects.hash(ticketID, flightID, passengerID, travelClass,price);
    }

}
