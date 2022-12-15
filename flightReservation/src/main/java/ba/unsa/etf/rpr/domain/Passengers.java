package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;
public class Passengers implements Idable{

    private int passengerID;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String adress;

    public int getId() {
        return passengerID;
    }

    public void setId(int passengerID) {
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
                ", adress:'" + adress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passengers passenger = (Passengers) o;
        return passengerID == passenger.passengerID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(passengerID, name, surname, dateOfBirth,adress);
    }

}
