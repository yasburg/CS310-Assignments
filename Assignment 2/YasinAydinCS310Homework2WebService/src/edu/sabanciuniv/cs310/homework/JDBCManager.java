package edu.sabanciuniv.cs310.homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCManager {

	public static boolean insertProduct(Product p1) 
	{
		try 
		{
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cs310", "root", "1997");
			PreparedStatement ps =  con.prepareStatement("INSERT INTO product (productName, productPrice, productStock) VALUES (?,?,?)");
			ps.setString(1, p1.getPname());
			ps.setDouble(2, p1.getpPrice());
			ps.setInt(3, p1.getpStock());
			
			int result = ps.executeUpdate();
			
			if(result==1)
			{
				return true;
			}
			
			
		} catch (Exception e) {
			System.out.println("Problem in addProduct");
		}
		return false;
	}

	public static boolean deleteProduct(int productID) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310", "root", "1997");

            String sql = "DELETE FROM product WHERE productID=? ";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, productID);

            int rowsDeleted = pStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("The product with id " + productID + " was deleted successfully.");
            }
            connection.close();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL Exception occurred while deleting a product!");
        }
        return false;
	}
	
	public static boolean updateProductStock(int productID, double productPrice, int productStock) {
        
		try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310", "root", "1997");

            String sql = "UPDATE product SET productPrice=?, productStock=? WHERE productID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, productPrice);
			ps.setInt(2, productStock);
			ps.setInt(3, productID);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing product was updated successfully.");
            }
            connection.close();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL Exception occurred while updating a product!");
        }
		
		return false;
	}
}
