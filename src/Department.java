import java.util.ArrayList;

public abstract class Department implements Subject{
	private String name;
	private ArrayList<Item> list_item = new ArrayList<Item>();
	private ArrayList<Customer> buyers = new ArrayList<Customer>();
	private ArrayList<Customer> wishers = new ArrayList<Customer>();
	private ArrayList<Customer> users = new ArrayList<Customer>();	
	private int ID;
	
	public Department(){
	}
	
	public Department(String name, int ID){
		this.name = name;
		this.ID = ID;
	}
	
	public int getID(){
		return this.ID;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setId(int ID){
		this.ID = ID;
	}
	
	public void enter(Customer c){
		buyers.add(c);
	}
	
	public boolean containsItem(int id){
		for (Item list_item : list_item){
			if (list_item.getID() == id )
				return true;
		}
		return false;
	}
	
	public void removeItem(Item item){
		list_item.remove(item);
	}
	
	//returneaza item-ul de pe o anumita pozitie
	public Item getItem(int id){
		for (Item list_item : list_item){
			if (list_item.getID() == id )
				return list_item;
		}
		return null;
	}

	public void exit(Customer c){
		buyers.remove(c);
	}
	
	public void enterWisher(Customer c){
		wishers.add(c);
	}
	
	public ArrayList<Customer> getCustomers(){
		//intoarce toti clientii care au cumparat cel putin un produs al departamentului
		return buyers;
	}
	
	public ArrayList<Item> getlist_item(){
		return this.list_item;
	}
		
	public void addItem(Item item){
		list_item.add(item);
	}
	
	public ArrayList<Item> getItems(){
		return list_item;
	}
	
	public ArrayList<Customer> getObservers(){
		return this.wishers;
	}
	
	public boolean containsObserver(Customer c){
		if ( wishers.contains(c) )
			return true;
		return false;
	}
	
	public boolean containsCustomer(Customer c){
		if (buyers.contains(c))
			return true;
		return false;
	}
	
	public void addObserver(Customer c){
		//metoda apelata pentru a inregistra unn client ca observator
		wishers.add(c);
	}
	
	public void removeObserver(Customer c){
		wishers.remove(c);
	}
	
	public void notifyAllObservers(Notification n){
		for (Customer wishers:wishers)
			wishers.update(n);
	}

	public Item getItem(String nume) {
		for (int i = 0; i<this.list_item.size(); i++)
			if (list_item.get(i).getNume().compareTo(nume) == 0)
				return list_item.get(i);
		return null;
	}
	
	public boolean containsItem(String nume){
		for (int i = 0; i<this.list_item.size(); i++)
			if (list_item.get(i).getNume().compareTo(nume) == 0)
				return true;
		return false;
	}
	
	public abstract void accept(Visitor visitor);
}
