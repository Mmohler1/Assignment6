package business;

import java.util.List;

import javax.ejb.Local;


//Imported beans
import beans.Order;

@Local
public interface OrdersBusinessInterface {

	public void test();
	public List<Order> getOrders();
	public void setOrders(List<Order> orders);
	public void updateOrder(Order order);
	public void deleteOrder(Order order);
	public Order findFromID(int id);
	public void createOrder(Order order);
	public void sendOrder(Order order);
}
