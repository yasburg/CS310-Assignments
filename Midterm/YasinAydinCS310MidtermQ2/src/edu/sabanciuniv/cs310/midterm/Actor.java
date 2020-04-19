package edu.sabanciuniv.cs310.midterm;

public class Actor {

	private int id;
	private String name;
	private String moviename;
	
	public Actor() {
		super();
	}

	public Actor(int id, String name, String moviename) {
		super();
		this.id = id;
		this.name = name;
		this.moviename = moviename;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	
	
	
}
