package edu.sabanciuniv.cs310.homework1.model;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {

    @Id
    private int countryID;

    private String country;
    private String continent;
    private String city;
    private int countryPopulation;

    public Country() {
    }

    public Country(int countryID, String country, String continent, String city, int countryPopulation) {
        this.countryID = countryID;
        this.country = country;
        this.continent = continent;
        this.city = city;
        this.countryPopulation = countryPopulation;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCountryPopulation() {
        return countryPopulation;
    }

    public void setCountryPopulation(int countryPopulation) {
        this.countryPopulation = countryPopulation;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryID=" + countryID +
                ", country='" + country + '\'' +
                ", continent='" + continent + '\'' +
                ", city='" + city + '\'' +
                ", countryPopulation=" + countryPopulation +
                '}';
    }
}
