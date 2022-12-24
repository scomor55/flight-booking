package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Flights implements Idable{

    private int flightID;
    private String source;
    private String destination;
    //private Date departure;//

    private LocalDate departure;
   // private Date arrival;//
   private LocalDate arrival;
    private int seats;

    public int getId() {
        return flightID;
    }

    public void setId(int flightID) {
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

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id:" + flightID +
                ", source:'" + source + '\'' +
                ", destination:'" + destination + '\'' +
                ", arrival:" + arrival +
                ", seats=" + seats +
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
        return Objects.hash(flightID, source, destination, departure,arrival, seats);
    }



}
