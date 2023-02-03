package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;
import java.util.Objects;
/**
 * The Flights class represents a flight with relevant information such as flight ID, source,
 * destination, departure date and time, arrival date and time, number of seats, economy class price
 * and business class price.
 *
 * @author Safet Čomor
 */
public class Flights implements Idable{

    private int flightID;
    private String source;


    private String destination;
    //private Date departure;//

    private LocalDate departure;
   // private Date arrival;//
    private String departureTime;
   private LocalDate arrival;
    private String arrivalTime;
    private int seats;
    private int priceEconomy;
    private int priceBusiness;

    public int getPriceEconomy() {
        return priceEconomy;
    }

    public void setPriceEconomy(int priceEconomy) {
        this.priceEconomy = priceEconomy;
    }

    public int getPriceBusiness() {
        return priceBusiness;
    }

    public void setPriceBusiness(int priceBusiness) {
        this.priceBusiness = priceBusiness;
    }

    /**
     * Gets the flight ID.
     * @return flight ID
     */
    public int getId() {
        return flightID;
    }
    /**
     * Sets the flight ID.
     * @param flightID the flight ID to be set
     */
    public void setId(int flightID) {
        this.flightID = flightID;
    }
    /**
     * Gets the source of the flight.
     * @return source of the flight
     */
    public String getSource() {
        return source;
    }
    /**
     * Sets the source of the flight.
     * @param source the source of the flight to be set
     */
    public void setSource(String source) {
        this.source = source;
    }
    /**
     * Gets the destination of the flight.
     * @return destination of the flight
     */
    public String getDestination() {
        return destination;
    }
    /**
     * Sets the destination of the flight.
     * @param destination the destination of the flight to be set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }
    /**
     * Gets the departure date of the flight.
     * @return departure date of the flight
     */
    public LocalDate getDeparture() {
        return departure;
    }
    /**
     * Sets the departure date of the flight.
     * @param departure the departure date of the flight to be set
     */
    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }
    /**
     * Gets the arrival date of the flight.
     * @return arrival date of the flight
     */
    public LocalDate getArrival() {
        return arrival;
    }
    /**
     * Sets the arrival date of the flight.
     * @param arrival the arrival date of the flight to be set
     */
    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }
    /**
     * Gets the number of seats on the flight.
     * @return number of seats on the flight
     */
    public int getSeats() {
        return seats;
    }
    /**
     * Sets the number of seats on the flight.
     * @param seats the number of seats on the flight to be set
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }
    /**
     * Gets the departure time of this flight.
     * @return The departure time of the flight.
     */
    public String getDepartureTime() {
        return departureTime;
    }
    /**
     * Sets the departure time of this flight.
     * @param departureTime The departure time of the flight.
     */
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    /**
     * Gets the arrival time of this flight.
     * @return The arrival time of the flight.
     */
    public String getArrivalTime() {
        return arrivalTime;
    }
    /**
     * Sets the arrival time of this flight.
     * @param arrivalTime The arrival time of the flight.
     */
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Constructs a Flight object with the specified destination.
     * @param destination The destination of the flight.
     */
    public Flights(String destination){
        this.destination = destination;
    }
    /**
     * Default constructor for the Flight object.
     */
    public Flights(){
    }
    /**
     * Returns a string representation of this flight object.
     * @return A string representation of this flight object.
     */
    @Override
    public String toString() {
        return "Flight{" +
                "id:" + flightID +
                ", source:'" + source + '\'' +
                ", destination:'" + destination + '\'' +
                ", departure:" + departure +
                ", departureTime:" + departureTime +
                ", arrival:" + arrival +
                ", arrivalTime:" + arrivalTime +
                ", seats=" + seats +
                ", priceEconomy=" + priceEconomy +
                ", priceBusiness=" + priceBusiness +
                '}';
    }
    /**
     * Determines if this Flight object is equal to another object.
     * @param o The object to compare to this Flight object.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flights flight = (Flights) o;
        return flightID == flight.flightID;
    }
    /**
     * Generates a hash code for the flight object based on its flight ID, source, destination, departure, departure time,
     * arrival, arrival time, seats, price for economy class and price for business class.
     * @return an integer value representing the hash code of the flight object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(flightID, source, destination, departure,departureTime,arrival,arrivalTime, seats, priceEconomy, priceBusiness);
    }
}
