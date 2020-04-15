package edu.sabanciuniv.cs310.homework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class MainClass {

	static void callAddProduct(String pName, double pPrice, int pStock) {
		try 
		{
			URL url  =  new URL("http://localhost:8080/YasinAydinCS310Homework2WebService/rest/ProductWebService/addNewProduct/" + pName + "/" + pPrice + "/" + pStock);

			InputStreamReader reader = new InputStreamReader(url.openStream());
			
			BufferedReader rd = new BufferedReader(reader);
			
			while(true)
			{
				String line = rd.readLine();
				if(line==null)
					break;
				System.out.println(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void callDeleteProduct(int pID) {
		try 
		{
			URL url  =  new URL("http://localhost:8080/YasinAydinCS310Homework2WebService/rest/ProductWebService/deleteProduct/" + pID);

			InputStreamReader reader = new InputStreamReader(url.openStream());
			
			BufferedReader rd = new BufferedReader(reader);
			
			while(true)
			{
				String line = rd.readLine();
				if(line==null)
					break;
				System.out.println(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void callUpdateProductStock(int pID, double pPrice, int pStock) {
		try 
		{
			URL url  =  new URL("http://localhost:8080/YasinAydinCS310Homework2WebService/rest/ProductWebService/updateProductStock/" + pID + "/" + pPrice + "/" + pStock);

			InputStreamReader reader = new InputStreamReader(url.openStream());
			
			BufferedReader rd = new BufferedReader(reader);
			
			while(true)
			{
				String line = rd.readLine();
				if(line==null)
					break;
				System.out.println(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		//example calls:

		callAddProduct("Apple1", 5.0, 3000);
		callAddProduct("Apple2", 5.0, 3000);
		callAddProduct("Apple3", 5.0, 3000);
		callAddProduct("Apple4", 5.0, 3000);
		callAddProduct("Apple5", 5.0, 3000);
		callAddProduct("Apple6", 5.0, 3000);
		callAddProduct("Apple7", 5.0, 3000);
		callAddProduct("Apple8", 5.0, 3000);
		callAddProduct("Apple9", 5.0, 3000);
		callAddProduct("Apple10", 5.0, 3000);
		callAddProduct("Apple11", 5.0, 3000);
		
		callDeleteProduct(1);
		
		callUpdateProductStock(11, 11.0, 11);
	}

}
