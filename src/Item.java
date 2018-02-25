
public class Item {
	private String name;
	private int ID;
	private double price;
	private int ID_dep;
	
	public Item(String name, int ID, double price){
		this.name = name;
		this.ID = ID;
		this.price = price;
	}
	
	public Item(){
		this( " ", 0, 0 );
	}
	
	public String getNume(){
		return this.name;
	}
	
	public int getID(){
		return this.ID;
	}
	
	public double getPret(){
		return this.price;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setId(int ID){
		this.ID = ID;
	}
	
	public void setPrice(double d){
		this.price = d;
	}
	
	public int getDep(){
		return this.ID_dep;
	}
	
	public void setDep(int ID_dep){
		this.ID_dep = ID_dep;
	}
	
	public boolean equalsTo(Item item){
		if (this.getID() ==item.getID())
			return true;
		return false;
	}
	
	public String toString(){
		return this.name+";"+this.ID+";"+this.price;
	}
	
}
