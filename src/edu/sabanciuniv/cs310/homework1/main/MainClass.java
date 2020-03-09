package edu.sabanciuniv.cs310.homework1.main;

import edu.sabanciuniv.cs310.homework1.model.YasinJDBCManager;

public class MainClass {

    public static void main(String[] args){

        YasinJDBCManager dbManager = new YasinJDBCManager();
        dbManager.readFromFile("data\\world.txt");
        dbManager.writeIntoTable(dbManager.getCountryList());
        System.out.println(dbManager.getCountryByID(1).toString());
        dbManager.deleteCountryByID(2);
        dbManager.updateCountryPopulationByID(1, 1);

    }
}
