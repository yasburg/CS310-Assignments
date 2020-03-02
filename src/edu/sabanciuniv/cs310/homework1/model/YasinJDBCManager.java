package edu.sabanciuniv.cs310.homework1.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class YasinJDBCManager {

    private ArrayList<Country> countryList = new ArrayList<Country>();

    public YasinJDBCManager() {
    }

    public ArrayList<Country> getCountryList() {
        return this.countryList;
    }

    public void printCountries() {
        for (Country country : this.countryList){
            System.out.println(country.getCountry());
        }
    }

    public void readFromFile(String filename){

        try
        {
            FileReader reader = new FileReader(filename);
            BufferedReader bfr = new BufferedReader(reader);
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

                Country currentCountry = new Country(separatedLine[0], separatedLine[1], separatedLine[2], currentPopulation);
                this.countryList.add(currentCountry);

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
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs310", "root", "1997");

            for(Country country : countries){

                String sql = "SELECT countryName, continentName, cityName, countryPopulation FROM countries WHERE countryName=? AND continentName=? AND cityName=?";
                PreparedStatement pStatement = connection.prepareStatement(sql);
                pStatement.setString(1, country.getCountry());
                pStatement.setString(2, country.getContinent());
                pStatement.setString(3, country.getCity());

                ResultSet resultSet = pStatement.executeQuery();

                if (!resultSet.next()) {//if does not exists
                    sql = "INSERT INTO Countries (countryName, continentName, cityName, countryPopulation) VALUES (?, ?, ?, ?)";
                    pStatement = connection.prepareStatement(sql);

                    pStatement.setString(1, country.getCountry());
                    pStatement.setString(2, country.getContinent());
                    pStatement.setString(3, country.getCity());
                    pStatement.setInt(4, country.getCountryPopulation());
                    pStatement.execute();
                }



            }
            System.out.println("All countries were inserted successfully.");
        }
        catch (Exception e) {
            System.out.println("Exception occurred while writing into the table!");
        }

    }

    public static Country getCountryByID(int countryID){

        Country country = new Country();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs310", "root", "1997");

            String sql = "SELECT countryName, continentName, cityName, countryPopulation FROM countries WHERE countryID=? ";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, countryID);

            ResultSet resultSet = pStatement.executeQuery();

            if (resultSet.next()) {
                String countryName = resultSet.getString("countryName");
                String continentName = resultSet.getString("continentName");
                String cityName = resultSet.getString("cityName");
                int population = resultSet.getInt("countryPopulation");

                country.setCountry(countryName);
                country.setContinent(continentName);
                country.setCity(cityName);
                country.setCountryPopulation(population);
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception occurred while getting the country from the database!");
        }
        return country;
    }

    public static void deleteCountryByID(int countryID) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310", "root", "1997");

            String sql = "DELETE FROM countries WHERE countryID=? ";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, countryID);

            int rowsDeleted = pStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("The country with id " + countryID + " was deleted successfully.");
            }


        } catch (SQLException e) {
            System.out.println("SQL Exception occurred while deleting a country!");
        }

    }

    public static void updateCountryPopulationByID(int countryID, int population) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310", "root", "1997");

            String sql = "UPDATE countries SET countryPopulation=? WHERE countryID=?";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, population);
            pStatement.setInt(2, countryID);

            int rowsUpdated = pStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing country population was updated successfully.");
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception occurred while updating a country!");
        }

    }

}
