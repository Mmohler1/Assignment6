//Michael Mohler 

package controller;


import java.sql.DriverManager;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.Order;
import beans.User;
import business.OrdersBusinessInterface;

import java.sql.*;

@ManagedBean
@ViewScoped
public class formController 
{
	
	//Insert the interface
	@Inject
	OrdersBusinessInterface service;


	//Submit by initializing the user in the method 
	public String onSubmit()
	{
		//Get the User Managed bean
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		service.test();
		
	//Test update
		//Order testing = new Order(1, "05643423","Cooler",(float)180.23,9);
		//service.updateOrder(testing);
		
	//Test delete
		//deleteOrder(testing);
		
	//Test findbyid
		Order testing2 = service.findFromID(2);
		System.out.println(testing2.getId() + "-" + testing2.getNumber() + "-" + testing2.getName() + "-" + testing2.getPrice() + "-" + testing2.getQuantity());
		
	//Test create
		//Order testing3 = new Order(1, "05832422","Hotter",(float)75.23,5);
		//service.createOrder(testing3);
		
		getAllOrders();
		
		
		//Forward to Test Response View along with the user Managed Bean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		return "TestResponse.xhtml";
	}
	
	
	public String onSendOrder()
	{
		Order things = new Order(15, "09274423","Thingies",(float)30.63,3);
		service.sendOrder(things);
		return "OrderResponse.xhtml";
	}
	
	
	
	public OrdersBusinessInterface getService()
	{
		return service;
	}
	
	
	//Displays all orders to the console
	private void getAllOrders()
	{
		//Info for database
		Connection conn = null;
		
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "chair";
		
		String sql = "SELECT * FROM testapp.ORDERS";
		//List<Order> orders = new ArrayList<Order>();
		
		try
		{
			//Connecting to database
			conn = DriverManager.getConnection(url, username, password);
			
			//Execute SQL Query and loop
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				System.out.println(String.format("ID is %d for Product %s at a price of %f", rs.getInt("ID"), rs.getString("PRODUCT_NAME"), rs.getFloat("PRICE")));
			}
			
			System.out.println("Success!");
			conn.close();
		}
		catch (SQLException e)
		{
			System.out.println("Failure!");
			e.printStackTrace();
			
		}
		finally
		{
			//Database cleaning
			if(conn != null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	
	
	
	public void deleteOrder(Order order)
	{
		service.deleteOrder(order);
	}
	
}
