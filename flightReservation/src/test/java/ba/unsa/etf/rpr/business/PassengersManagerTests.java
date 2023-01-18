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
}
