package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

public class Flights {

    private int flightID;
    private String source;
    private String destination;
    private Date departure;
    private Date arrival;
    private int avalivableSeats;

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public int getAvalivableSeats() {
        return avalivableSeats;
    }

    public void setAvalivableSeats(int avalivableSeats) {
        this.avalivableSeats = avalivableSeats;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "source:" + flightID +
                ", source:'" + source + '\'' +
                ", destination:'" + destination + '\'' +
                ", arrival:" + arrival +
                ", avalivableSeats=" + avalivableSeats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flights flight = (Flights) o;
        return flightID == flight.flightID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightID, source, destination, departure,arrival,avalivableSeats);
    }


}
