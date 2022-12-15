package ba.unsa.etf.rpr.exceptions;

public class FlightBookingException extends Exception{

    public FlightBookingException(String msg, Exception reason){
        super(msg, reason);
    }

    public FlightBookingException(String msg){
        super(msg);
    }

}
