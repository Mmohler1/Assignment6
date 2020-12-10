package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@ViewScoped
public class Orders {

	
	//Makes a list of the order object
	List<Order> list = new ArrayList<Order>();
	
	
	//Default constructor that makes a bunch of dummy data for list. 
	public Orders()
	{
		//list.add(new Order("03345873","WRONG!!!!",(float)43.54,3));
		//list.add(new Order("05875643","Hat",(float)42.58,2));
		//list.add(new Order("05643423","Cooler",(float)180.23,9));
		//list.add(new Order("36922574","Soda Cans",(float)12.84,4));
	}
	
	
	//Get and Set methods
	public List<Order> getList() {
		return list;
	}
	public void setList(List<Order> list) {
		this.list = list;
	}
	
	
	
	
}
