package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.FlightsDaoSQLImpl;
import ba.unsa.etf.rpr.dao.PassengersDaoSQLImpl;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Flights;
import ba.unsa.etf.rpr.domain.Passengers;
import ba.unsa.etf.rpr.domain.Passengers;
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

public class PassengersManagerTests {

    private PassengersManager passengersManager;
    private Passengers passenger;
    private PassengersDaoSQLImpl passengersDaoSQLMock;
    private List<Passengers> passengers;


    @BeforeEach
    public void initializeObjectsWeNeed(){
        passengersManager = Mockito.mock(PassengersManager.class);
        passenger = new Passengers();
        passenger.setName("Stig");
        passenger.setSurname("Stigić");
        passenger.setDateOfBirth(LocalDate.parse("2022-12-22"));
        passenger.setAdress("Silverstone 00");
        passenger.setAdress("stig.stigovic@topgear.uk");

        passenger.setId(1);

        passengersDaoSQLMock = Mockito.mock(PassengersDaoSQLImpl.class);
        passengers = new ArrayList<>();
        passengers.addAll(Arrays.asList( new Passengers("A") ,new Passengers("B"), new Passengers("C"), new Passengers("D"), new Passengers("E") ));
    }

    @Test
    void add() throws FlightBookingException{
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);

        daoFactoryMockedStatic.when(DaoFactory::passengersDao).thenReturn(passengersDaoSQLMock);
        when(DaoFactory.passengersDao().getAll()).thenReturn(passengers);
        Mockito.doCallRealMethod().when(passengersManager).add(passenger);


        FlightBookingException flightBookingException = Assertions.assertThrows(FlightBookingException.class, () ->{
            passengersManager.add(passenger);
        },"Can't add passenger with ID. ID is autogenerated");
        Assertions.assertEquals("Can't add passenger with ID. ID is autogenerated",flightBookingException.getMessage());

        Assertions.assertEquals("Can't add passenger with ID. ID is autogenerated",flightBookingException.getMessage());

        daoFactoryMockedStatic.verify(DaoFactory::passengersDao);
        Mockito.verify(passengersManager).add(passenger);
        daoFactoryMockedStatic.close();
    }


    @Test
    public void add2()throws FlightBookingException{
        Passengers newPassenger = new Passengers("Ceiling");
        passengersManager.add(newPassenger);

        Assertions.assertTrue(true);
        Mockito.verify(passengersManager).add(newPassenger);
    }


    @Test
    void validatePassengerName() throws FlightBookingException{
        String correctName ="Mustafa";
        try {
            Mockito.doCallRealMethod().when(passengersManager).validatePassengerName(correctName);
        }catch(FlightBookingException f){
            f.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectName = "A";
        Mockito.doCallRealMethod().when(passengersManager).validatePassengerName(incorrectName);
        FlightBookingException flightBookingException = Assertions.assertThrows(FlightBookingException.class, () -> {passengersManager.validatePassengerName(incorrectName);},"Passenger name must be between 3 and 20 characters long");
        Assertions.assertEquals("Passenger name must be between 3 and 20 characters long",flightBookingException.getMessage());


    }



}