package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.faces.bean.ViewScoped;


import beans.Order;
import data.DataAccessInterface;

/**
 * Session Bean implementation class AnotherOrdersBusinessService
 */
@Stateless
@Local(OrdersBusinessInterface.class)
@LocalBean
@Alternative
@ViewScoped
public class AnotherOrdersBusinessService implements OrdersBusinessInterface {

	//Array list for orders
	List <Order> orders = new ArrayList<Order>();
	
	@EJB
	DataAccessInterface service;
	
    /**
     * Default constructor. 
     */
    public AnotherOrdersBusinessService() {
        // TODO Auto-generated constructor stub


    }
    

    //Tells the console that the program is running
    public void test() {
        // TODO Auto-generated method stub
    	
    }
    
    //Updates a current order by calling the database with the order.
    public void updateOrder(Order order) {
    	service.update(order);
    }

    //Delete the database based on the order.
    public void deleteOrder(Order order) {
    	service.delete(order);
    }
    
    //Adds an order to the database
    public void createOrder(Order order)
    {
    	service.create(order);
    }
    
    //Returns order from database based on id.
    public Order findFromID(int id)
    {
    	return service.findById(id);
    }
    
    
    //Getters and setters for orders
    public List<Order> getOrders()
    {
    	return service.findAll();
    	
    }
    
    public void setOrders(List<Order> orders)
    {
    	//this.orders = orders;
    	
    }
}
