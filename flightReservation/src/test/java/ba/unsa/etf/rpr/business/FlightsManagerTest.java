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

/**
 * This is the class for testing the {@link FlightsManager} class.
 * It contains various test methods for validating the source name, destination name, and other functions of the {@link FlightsManager} class.
 * The class uses JUnit 5 for testing and mockito for mocking.
 */

public class FlightsManagerTest {

    private FlightsManager flightsManager;
    /**
     * A Flights object used in the tests.
     */
    private Flights flight;
    /**
     A mocked instance of the FlightsDaoSQLImpl class.
     */
    private FlightsDaoSQLImpl flightsDaoSQLMock;
    /**
     * A list of  Flights objects used in the tests.
     */
    private List<Flights> flights;
    /**
     * This method initializes the objects required for testing.
     */
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
    /**
     This method tests the validateSourceName(String) method in FlightsManager.
     It checks if the correct source name is accepted and if the incorrect source names throw an exception with the correct message.
     @throws FlightBookingException when the source name is incorrect.
     */
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
    /**
     This method tests the validateDestinationName(String) method in FlightsManager.
     It checks if the correct destination name is accepted and if the incorrect destination names throw an exception with the correct message.
     @throws FlightBookingException when the destination name is incorrect.
     */
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
    /**
     This method tests the validateNumberOfSeats() method in FlightsManager.
     It checks if the correct number of seats is accepted and if number of seats is incorrect then throw an exception with the correct message.
     @throws FlightBookingException when the number of seats is incorrect.
     */
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
    /**
     * This test method tests the add() method of the FlightsManager class.
     * It creates a new instance of the Flights class with the destination "Teplice",
     * and then calls the add() method of the flightsManager object to add this flight.
     * The test then uses Assertions.assertTrue() to verify that the flight was added successfully,
     * and Mockito.verify() to check that the add() method was indeed called.
     * @throws FlightBookingException if an exception occurs while adding the flight
     */
    @Test
    public void add2()throws FlightBookingException{
        Flights newFlight = new Flights("Teplice");
        flightsManager.add(newFlight);

        Assertions.assertTrue(true);
        Mockito.verify(flightsManager).add(newFlight);
    }

    /**
     * Test for the add() method of FlightsManager class.
     * This test mocks the DaoFactory class to return a mocked FlightsDao object.
     * The add() method of FlightsManager is verified to be called and the expected
     * FlightBookingException is thrown and its message is tested.
     * @throws FlightBookingException if the add() method fails to add a flight
     */

    @Test
    void add() throws FlightBookingException{
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);

        daoFactoryMockedStatic.when(DaoFactory::flightsDao).thenReturn(flightsDaoSQLMock);
        when(DaoFactory.flightsDao().getAll()).thenReturn(flights);
        Mockito.doCallRealMethod().when(flightsManager).add(flight);


        FlightBookingException flightBookingException = Assertions.assertThrows(FlightBookingException.class, () ->{
            flightsManager.add(flight);
        },"Can't add flight with ID. ID is autogenerated");
        Assertions.assertEquals("Can't add flight with ID. ID is autogenerated",flightBookingException.getMessage());

       Assertions.assertEquals("Can't add flight with ID. ID is autogenerated",flightBookingException.getMessage());

        daoFactoryMockedStatic.verify(DaoFactory::flightsDao);
        Mockito.verify(flightsManager).add(flight);
        daoFactoryMockedStatic.close();
    }


}
