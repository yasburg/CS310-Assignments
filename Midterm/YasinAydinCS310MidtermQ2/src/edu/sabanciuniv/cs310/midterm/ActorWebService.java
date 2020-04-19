package edu.sabanciuniv.cs310.midterm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("ActorWebService")
public class ActorWebService {

	//http://localhost:8080/YasinAydinCS310MidtermQ2/rest/ActorWebService/getAllActors
	@GET
	@Path("getAllActors")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Actor> getAllActors()
	{
		ArrayList<Actor> actors = new ArrayList<Actor>();
		try {
        	
        	//Creating a connection object to connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310", "root", "1997");
            
            //Creating a prepared statement object to prepare sql query
            PreparedStatement pStatement = connection.prepareStatement("Select * from Actor");
            
            ResultSet rSet = pStatement.executeQuery();
            
            while(rSet.next())
            {
            	
            	int currentID = rSet.getInt("id");
            	String currentMovieName= rSet.getString("moviename");
            	String currentActorName = rSet.getString("name");
            	
            	//using full constructor to create an actor object which will be pushed into the list
            	Actor actor = new Actor(currentID, currentMovieName, currentActorName);
            	
            	//adding the actor to the Actors list
            	actors.add(actor);
            	
            }
            
        } catch (Exception e) {
        	System.out.println("Problem occured while connecting to database!!");
        	e.printStackTrace();
        }
		
		return actors;
	}
}
