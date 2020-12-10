package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import beans.Order;



@Stateless
public class OrdersDataService implements DataAccessInterface {

	//Saves all tables from SQL to List and returns it. 
	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		//Info for database
				Connection conn = null;
				
				String url = "jdbc:postgresql://localhost:5432/postgres";
				String username = "postgres";
				String password = "chair";
				
				String sql = "SELECT * FROM testapp.ORDERS";
				List<Order> orders = new ArrayList<Order>();
				
				try
				{
					//Connecting to database
					conn = DriverManager.getConnection(url, username, password);
					
					//Execute SQL Query and loop
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next())
					{
						//add new order with each new table
						orders.add(new Order(rs.getInt("ID"),
								rs.getString("ORDER_NO"),
								rs.getString("PRODUCT_NAME"), 
								rs.getFloat("PRICE"), 
								rs.getInt("QUANTITY")));			
					}
					
					
					conn.close();
					return orders;
				}
				catch (SQLException e)
				{
					
					e.printStackTrace();
					return null;
					
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

	//Pulls and returns an Order in the database based on the id.
	@Override
	public Order findById(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;	
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "chair";
		String sql = "SELECT * FROM testapp.ORDERS";
		Order order = null;
		int newId = id;
		
		try
		{
			//Connecting to database
			conn = DriverManager.getConnection(url, username, password);
			
			//Execute SQL Query and loop
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				
				//add new order with each new table
				if (rs.getInt("ID") == newId)
				{
					
					order = new Order(rs.getInt("ID"),
							rs.getString("ORDER_NO"),
							rs.getString("PRODUCT_NAME"), 
							rs.getFloat("PRICE"), 
							rs.getInt("QUANTITY"));
				}
			}
			
			
			conn.close();
			return order;
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
			return null;
			
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

	//Inserts order from the parameters into the database
	@Override
	public void create(Order order) {
		// TODO Auto-generated method stub
		Connection conn = null;
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "chair";
		Order createOrder = order;
		
		String sql = "INSERT INTO  testapp.ORDERS(ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) VALUES(" + 
				"'"+ createOrder.getNumber() + "', " +
				"'"+ createOrder.getName() + "', "
					+ createOrder.getPrice() + ", " + 
					createOrder.getQuantity() + ");";
		
		try
		{
			conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch (SQLException e)
		{
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

	//Updates order by using UPDATE SQL command and adding info from the order
	@Override
	public void update(Order order) {
		// TODO Auto-generated method stub
	Connection conn = null;
	String url = "jdbc:postgresql://localhost:5432/postgres";
	String username = "postgres";
	String password = "chair";
	
    	Order updateOrder = order;
    	//Add info to the string to have a string to update.
		String sql = "UPDATE testapp.ORDERS SET " + 
				"ORDER_NO = '"+ updateOrder.getNumber() + "', " +
				"PRODUCT_NAME = '"+ updateOrder.getName() + "', " +
				"PRICE = "+ updateOrder.getPrice() + ", " + 
				"QUANTITY = "+ updateOrder.getQuantity() +
				" WHERE ID = " + order.getId() + ";";
		
		try
		{
			conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch (SQLException e)
		{
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

	//Deletes order in database based on the id from the order in the parameter
	@Override
	public void delete(Order order) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Connection conn = null;
				String url = "jdbc:postgresql://localhost:5432/postgres";
				String username = "postgres";
				String password = "chair";
				String sql = "DELETE FROM testapp.ORDERS WHERE ID = " +
							order.getId() + ";";
				
				try
				{
					conn = DriverManager.getConnection(url, username, password);
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sql);
				}
				catch (SQLException e)
				{
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

}
