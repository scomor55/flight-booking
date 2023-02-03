package ba.unsa.etf.rpr.exceptions;

/**
 * The FlightBookingException class is a custom exception that extends the standard Java Exception class
 * It is used to handle exceptions that occur during flight booking.
 * @author Safet Čomor
 * @version 1.0
 *
 */

public class FlightBookingException extends Exception{

    /**
     * Constructs a new FlightBookingException with the specified message and reason.
     * @param msg the detail message.
     * @param reason the cause of the exception.
     */
    public FlightBookingException(String msg, Exception reason){
        super(msg, reason);
    }
    /**
     * Constructs a new FlightBookingException with the specified message.
     * @param msg the detail message.
     */
    public FlightBookingException(String msg){
        super(msg);
    }

}
