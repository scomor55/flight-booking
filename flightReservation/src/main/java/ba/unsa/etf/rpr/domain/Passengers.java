package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;
public class Passengers {

    private int passengerID;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String adress;

    public int getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "Passengr{" +
                "passengerID=" + passengerID +
                ", name:'" + name + '\'' +
                ", surname:'" + surname + "'"+
                ", dateOfBirth:" + dateOfBirth +
                ", name:'" + name + '\'' +
                '}';
    }
}
