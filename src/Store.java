import java.util.ArrayList;
import java.util.Collection;

public class Store {
	private static Store instance;
	
	private Store(){}
	
	public static Store getInstance(){
		instance = new Store();
		return instance;
	}
	
	private String name;
	private ArrayList<Department> dep = new ArrayList<Department>() ;
	private ArrayList<Customer> clients = new ArrayList<Customer>();
	
	public void setName(String name){
		this.name = name;
	}

	public ArrayList<Item> getItems(){
		ArrayList<Item> items = new ArrayList<Item>();
		for (int i = 0 ; i<dep.size(); i++){
			items.addAll(dep.get(i).getItems());
		}
		return items;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void enter(Customer c){
		if ( clients.contains(c) == true )
			return;
		else
			clients.add(c);
	}
	
	public void exit(Customer c){
		if ( clients.contains(c) == false )
			return;
		else
			clients.remove(c);	
	}
	
	public Collection<Customer> getCustomers(){
		return clients;
	}
	
	public Customer getCustomer(String name){
		for(Customer clients: clients){
			if (clients.getName().compareTo(name)==0)
				return clients.getCustomer();
		}
		return null;
	}
	
	public Item getItem(int id){
		for (Department dep : dep){
			if (dep.containsItem(id))
				return dep.getItem(id);
			}
		return null;
		}
	
	public Item getItem(String nume){
		for (Department dep : dep){
			if (dep.containsItem(nume))
				return dep.getItem(nume);
		}
	return null;
	}
	
	public Department getItemDep(int item_id){
		for (Department dep : dep){
			if (dep.containsItem(item_id))
				return dep;
			}
		return null;
	}
	
	public Collection<Department> getDepartments(){
		return dep;
	}
	
	public void addDepartment(Department d){
		if ( dep.contains(d) == true )
			return;
		else
			dep.add(d);
	}
	
	public Department getDepartement(int ID){
		for (int i = 0; i < dep.size(); i++)
			if ( dep.get(i).getID() == ID )
				return dep.get(i);
		return null;
	}

	public boolean containsItem(int item_id) {
		for (Department dep : dep){
			if (dep.containsItem(item_id))
				return true;
			}
		return false;
		}
}
