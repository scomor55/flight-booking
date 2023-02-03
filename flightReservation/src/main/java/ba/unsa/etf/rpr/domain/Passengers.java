package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * A class that represents a passenger.
 * @author Safet Čomor
 */

public class Passengers implements Idable{

    private int passengerID;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;   /* TRY TO USE DATE INSTEAD OF THIS */
    private String adress;
    private String email;

    /**
     * Returns the ID of the passenger.
     * @return the ID of the passenger
     */
    public int getId() {
        return passengerID;
    }
    /**
     * Sets the ID of the passenger.
     * @param passengerID the new ID of the passenger
     */
    public void setId(int passengerID) {
        this.passengerID = passengerID;
    }
    /**
     * Returns the name of the passenger.
     * @return the name of the passenger
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the passenger.
     * @param name the new name of the passenger
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns the surname of the passenger.
     * @return the surname of the passenger
     */
    public String getSurname() {
        return surname;
    }
    /**
     * Sets the surname of the passenger.
     * @param surname the new surname of the passenger
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    /**
     * Returns the date of birth of the passenger.
     * @return the date of birth of the passenger
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    /**
     * Sets the date of birth of the passenger.
     * @param dateOfBirth the new date of birth of the passenger
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    /**
     * Returns the address of the passenger.
     * @return the address of the passenger
     */
    public String getAdress() {
        return adress;
    }
    /**
     * Sets the address of the passenger.
     * @param adress the new address of the passenger
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }
    /**
     * Returns the email of the passenger.
     * @return the email of the passenger
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets the email of the passenger.
     * @param email the new email of the passenger
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Constructor with name as argument.
     * @param name the name of the passenger
     */
    public Passengers(String name){
        this.name = name;
    }
    /**
     * Default constructor.
     */
    public Passengers(){
    }
    /**
     * Overridden method that returns a string representation of the {@code Passengers} object.
     * The string contains the passengerID, name, surname, dateOfBirth, address, and email of the passenger.
     * @return String representation of the {@code Passengers} object.
     */
    @Override
    public String toString() {
        return "Passengr{" +
                "passengerID=" + passengerID +
                ", name:'" + name + '\'' +
                ", surname:'" + surname + "'"+
                ", dateOfBirth:" + dateOfBirth +
                ", adress:'" + adress + '\'' +
                ", email: "+ email +
                '}';
    }
    /**
     * Overridden method that checks if two {@code Passengers} objects are equal.
     * The passengers are considered equal if their passengerIDs are equal.
     * @param o Object to compare with the current {@code Passengers} object.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passengers passenger = (Passengers) o;
        return passengerID == passenger.passengerID;
    }
    /**
     Overridden method that calculates the hash code of the {@code Passengers} object.
     The hash code is calculated based on the passengerID, name, surname, dateOfBirth, address, and email.
     @return Hash code of the {@code Passengers} object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(passengerID, name, surname, dateOfBirth,adress,email);
    }

}
