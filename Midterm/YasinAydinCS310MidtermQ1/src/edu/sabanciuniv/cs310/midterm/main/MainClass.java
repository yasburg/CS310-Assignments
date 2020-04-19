package edu.sabanciuniv.cs310.midterm.main;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MainClass {

	public static void main(String[] args) {
		
        try {
        	
        	//Creating a connection object to connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310", "root", "1997");
            
            PreparedStatement pStatement = connection.prepareStatement("Select * from Actor");
            
            ResultSet rs = pStatement.executeQuery();
            
            //Creating the actors.txt file to write into
            File myFile = new File("actors.txt");
            
            if(myFile.createNewFile())
            {
                System.out.println("No problem while creating the actor.txt");
            }
            else {
            	System.out.println("Problem occured while creating the actor.txt");
            }
            
            FileWriter fWriter = new FileWriter("actors.txt");
            
            while(rs.next())
            {
            	
            	String currentID = Integer.toString(rs.getInt("id"));
            	String currentMovieName= rs.getString("moviename");
            	String currentActorName = rs.getString("name");
            	String result = currentID + "-" + currentMovieName + "-" + currentActorName + "\n";
            	fWriter.write( result );
            	//System.out.println(result); //Printing out to the console to check
            	
            }
            fWriter.close();
            
        } catch (Exception e) {
        	System.out.println("Problem occured while connecting to database!!");
        	e.printStackTrace();
        }

		
		
	}

}
