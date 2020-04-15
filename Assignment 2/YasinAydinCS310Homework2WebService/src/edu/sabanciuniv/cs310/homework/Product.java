package edu.sabanciuniv.cs310.homework;

public class Product {

	private int id;
	private String pname;
	private double pPrice;
	private int pStock;
	
	public Product() {
		super();
	}

	public Product(String pname, double pPrice, int pStock) {
		super();
		this.pname = pname;
		this.pPrice = pPrice;
		this.pStock = pStock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getpPrice() {
		return pPrice;
	}

	public void setpPrice(double pPrice) {
		this.pPrice = pPrice;
	}

	public int getpStock() {
		return pStock;
	}

	public void setpStock(int pStock) {
		this.pStock = pStock;
	}
	
}
