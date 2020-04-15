package edu.sabanciuniv.cs310.homework1.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class YasinJPAManager {
    ArrayList<Country> countries = new ArrayList<Country>();

    public YasinJPAManager() {

    }

    public ArrayList<Country> getCountries() {
        return this.countries;
    }

    public void readFromFile (String filename ) {
        try
        {
            FileReader reader = new FileReader(filename);
            BufferedReader bfr = new BufferedReader(reader);

            int id = 1;
            while(true)
            {
                String line = bfr.readLine();
                if (line == null)
                {
                    break;
                }
                String[] separatedLine = line.split(",");

                int currentPopulation = 0;
                try {
                    currentPopulation = Integer.parseInt(separatedLine[3]);
                } catch (NumberFormatException e) {
                    System.out.println("Cannot convert string to int - line: " + line);
                }

                Country currentCountry = new Country(id, separatedLine[0], separatedLine[1], separatedLine[2], currentPopulation);
                this.countries.add(currentCountry);
                id++;
            }
            reader.close();

        }
        catch (FileNotFoundException e) {
            System.out.println("File do not exist!");
        }
        catch (IOException e) {
            System.out.println("Do not have right to read that file!");
        }
    }

    public static void writeIntoTable(ArrayList<Country> countries) {

        try
        {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
            EntityManager entityManager = emf.createEntityManager();

            for(Country country : countries){

                entityManager.getTransaction().begin();
                entityManager.persist(country);
                entityManager.getTransaction().commit();
            }

            System.out.println("All countries were inserted successfully.");
        }
        catch (Exception e) {
            System.out.println("Exception occurred while writing into the table!");
        }
    }

    public static Country getCountryByID (int countryID) {

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
            EntityManager entityManager = emf.createEntityManager();

            Country foundCountry = entityManager.find(Country.class, countryID);

            return foundCountry;
        } catch (Exception e) {
            System.out.println("Exception occurred while retrieving country from the database!");
        }

        System.out.println("Could not find the country with id " + countryID + "!");
        Country country = new Country();
        return country;

    }

    public static void deleteCountryByID (int countryID) {

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
            EntityManager entityManager = emf.createEntityManager();

            Country countryToBeDeleted = entityManager.find(Country.class, countryID);
            if (countryToBeDeleted == null) {
                System.out.println("The country is already deleted!");
            }
            else {

                try {
                    entityManager.getTransaction().begin();
                    entityManager.remove(countryToBeDeleted);
                    entityManager.getTransaction().commit();
                    System.out.println("The country with id " + countryID + " was deleted successfully.");

                } catch (Exception e) {
                    System.out.println("There was a problem with the transaction.");
                }
            }
        } catch (Exception e) {
            System.out.println("There was a problem with the Entity Manager!");
        }
    }

    public static void updateCountryPopulationByID (int countryID, int population) {

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
            EntityManager entityManager = emf.createEntityManager();

            Country country = entityManager.find(Country.class, countryID);

            entityManager.getTransaction().begin();
            country.setCountryPopulation(population);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Exception occurred while updating the population!");
        }
    }

}
