package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.FlightsDaoSQLImpl;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.exceptions.FlightBookingException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

public class FlightsManagerTest {

    private FlightsManager flightsManager;
    private Flights flight;
    private FlightsDaoSQLImpl flightsDaoSQLMock;
    private List<Flights> flights;

    @BeforeEach
    public void initializeObjectsWeNeed(){
        flightsManager = Mockito.mock(FlightsManager.class);
        flight = new Flights();
        flight.setSource("Sarajevo");
        flight.setDestination("Zagreb");
        flight.setDeparture(LocalDate.parse("2022-12-22"));
        flight.setDepartureTime("12:00 PM");
        flight.setArrival(LocalDate.parse("2022-12-22"));
        flight.setArrivalTime("12:00 PM");
        flight.setSeats(120);
        flight.setPriceEconomy(200);
        flight.setPriceBusiness(400);

        flight.setId(1);

        flightsDaoSQLMock = Mockito.mock(FlightsDaoSQLImpl.class);
        flights = new ArrayList<>();
        flights.addAll(Arrays.asList(new Flights("Dortmund"),new Flights("Rostock"),new Flights("Plzen"),new Flights("Olomuc"),new Flights("Pribram")));
    }

    @Test
    void validateSourceName() throws FlightBookingException{
        String correctSource ="Sarajevo";
        try {
            Mockito.doCallRealMethod().when(flightsManager).validateSourceName(correctSource);
        }catch(FlightBookingException f){
            f.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectSource = "Ub";
        Mockito.doCallRealMethod().when(flightsManager).validateSourceName(incorrectSource);
        FlightBookingException flightBookingException = Assertions.assertThrows(FlightBookingException.class, () -> {flightsManager.validateSourceName(incorrectSource);},"Source name must be between 3 and 30 characters");
        Assertions.assertEquals("Source name must be between 3 and 30 characters",flightBookingException.getMessage());


        String incorrectSourceL  = RandomStringUtils.randomAlphabetic(31);
        Mockito.doCallRealMethod().when(flightsManager).validateSourceName(incorrectSourceL);
        FlightBookingException flightBookingExceptionL = Assertions.assertThrows(FlightBookingException.class, () -> {flightsManager.validateSourceName(incorrectSourceL);},"Source name must be between 3 and 30 characters");
        Assertions.assertEquals("Source name must be between 3 and 30 characters",flightBookingExceptionL.getMessage());


    }

    @Test
    void validateDestinationName()throws FlightBookingException{
       String correctDestination ="Riga";
       try {
           Mockito.doCallRealMethod().when(flightsManager).validateDestinationName(correctDestination);
       }catch(FlightBookingException f){
           f.printStackTrace();
           Assertions.assertTrue(false);
       }

       String incorrectDestination = "Ub";
       Mockito.doCallRealMethod().when(flightsManager).validateDestinationName(incorrectDestination);
       FlightBookingException flightBookingException = Assertions.assertThrows(FlightBookingException.class, () -> {flightsManager.validateDestinationName(incorrectDestination);},"Destination name must be between 3 and 30 characters");
       Assertions.assertEquals("Destination name must be between 3 and 30 characters",flightBookingException.getMessage());


        String incorrectDestinationL = RandomStringUtils.randomAlphabetic(31);
        Mockito.doCallRealMethod().when(flightsManager).validateDestinationName(incorrectDestinationL);
        FlightBookingException flightBookingExceptionL = Assertions.assertThrows(FlightBookingException.class, () -> {flightsManager.validateDestinationName(incorrectDestination);},"Destination name must be between 3 and 30 characters");
        Assertions.assertEquals("Destination name must be between 3 and 30 characters",flightBookingExceptionL.getMessage());


    }

    @Test
    void validateNumberOfSeats()throws FlightBookingException{
        int correctNumOfSeats = 156;
        try{
            Mockito.doCallRealMethod().when(flightsManager).validateSeats(correctNumOfSeats);
        }catch(FlightBookingException f){
            f.printStackTrace();
            Assertions.assertTrue(false);
        }

        int incorrectNumOfSeats = 1000;
        Mockito.doCallRealMethod().when(flightsManager).validateSeats(incorrectNumOfSeats);
        FlightBookingException flightBookingException = Assertions.assertThrows(FlightBookingException.class, () -> {flightsManager.validateSeats(incorrectNumOfSeats);},"Number of seats must be between 20 and 853");
        Assertions.assertEquals("Number of seats must be between 20 and 853",flightBookingException.getMessage());
    }

    @Test
    public void add2()throws FlightBookingException{
        Flights newFlight = new Flights("Teplice");
        flightsManager.add(newFlight);

        Assertions.assertTrue(true);
        Mockito.verify(flightsManager).add(newFlight);
    }

    /**
     * We are testing add() method. An explanation will be covered in the comments below
     */

    @Test
    void add() throws FlightBookingException{
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);

        daoFactoryMockedStatic.when(DaoFactory::flightsDao).thenReturn(flightsDaoSQLMock);
        when(DaoFactory.flightsDao().getAll()).thenReturn(flights);
        Mockito.doCallRealMethod().when(flightsManager).add(flight);


        FlightBookingException flightBookingException = Assertions.assertThrows(FlightBookingException.class, () ->{
            flightsManager.add(flight);
        },"Can't add category with ID. ID is autogenerated");
        Assertions.assertEquals("Can't add category with ID. ID is autogenerated",flightBookingException.getMessage());

       Assertions.assertEquals("Can't add category with ID. ID is autogenerated",flightBookingException.getMessage());

        daoFactoryMockedStatic.verify(DaoFactory::flightsDao);
        Mockito.verify(flightsManager).add(flight);
        daoFactoryMockedStatic.close();
    }


}
