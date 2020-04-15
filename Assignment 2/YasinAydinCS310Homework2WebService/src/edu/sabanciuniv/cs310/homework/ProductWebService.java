package edu.sabanciuniv.cs310.homework;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("ProductWebService")
public class ProductWebService {

	@GET
	@Path("addNewProduct/{pn}/{pp}/{ps}")
	public boolean addNewProduct(
			@PathParam("pn") String pName, 
			@PathParam("pp") double pPrice, 
			@PathParam("ps") int pStock){
		Product p1 = new Product(pName, pPrice, pStock);
		boolean result = JDBCManager.insertProduct(p1);
		return result;
	}

	@GET
	@Path("deleteProduct/{pid}")
	public boolean deleteProduct(
			@PathParam("pid") int productID) {
		boolean result = JDBCManager.deleteProduct(productID);
		return result;
	}

	@GET	
	@Path("updateProductStock/{pid}/{pp}/{ps}")
	public boolean updateProductStock(
			@PathParam("pid") int productID, 
			@PathParam("pp") double productPrice, 
			@PathParam("ps") int productStock) {
		boolean result = JDBCManager.updateProductStock(productID, productPrice, productStock);
		return result;
	}
}
