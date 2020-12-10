package business;



import java.io.Serializable;
import java.util.List;


import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.faces.bean.ViewScoped;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

//Imported beans
import beans.Order;
import data.DataAccessInterface;

/**
 * Session Bean implementation class OrdersBusinessService
 */
@Stateless
@Local(OrdersBusinessInterface.class)
@LocalBean
@Alternative
@ViewScoped
public class OrdersBusinessService implements OrdersBusinessInterface {

	//Array list for orders

	
	@EJB
	DataAccessInterface service;
	
    /**
     * Default constructor. 
     */
    public OrdersBusinessService() {
        // TODO Auto-generated constructor stub


    }
    
    
    //Causes Testrespone to crash
    @Resource(mappedName="java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName="java:/jms/queue/Order")
	private Queue queue;

    
    public void sendOrder(Order order)
    {
    	
    	// Send a Message for an Order
		try 
		{
			Connection connection = connectionFactory.createConnection();
			Session  session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			MessageProducer messageProducer = session.createProducer(queue);
			
			TextMessage message1 = session.createTextMessage();
			message1.setText("This is test message");
			messageProducer.send(message1);
			
			ObjectMessage message2 = session.createObjectMessage();
			message2.setObject(order);
			messageProducer.send(message2);
			
			//Close connection to prevent errors
			connection.close();
		} 
		catch (JMSException e) 
		{
			e.printStackTrace();
		}

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
