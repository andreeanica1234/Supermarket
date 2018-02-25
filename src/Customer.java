import java.util.ArrayList;
import java.util.Collection;

public class Customer implements Observer{
	
	private String name;
	private double buget;
	private String strategy;
	private ShoppingCart cart;
	private WishList wish;
	private ArrayList<Notification> coll = new ArrayList<Notification>() ;
	
	public Customer(){		
		cart = new ShoppingCart();
		wish = new WishList();
	}
	public Customer(String name, double buget){
		cart = new ShoppingCart(buget);
		wish = new WishList();
		this.name = name;
		this.buget = buget;
	}
	public Customer(String name, double buget, String strategy){
		cart = new ShoppingCart(buget);
		this.name = name;
		this.buget = buget;
		this.strategy = strategy;
		//asociaza la WishList ul clientului un tip de strategie
		if ( strategy.compareTo("A")==0 )
			wish = new WishList(new StrategyA());
		if ( strategy.compareTo("B")==0 )
			wish = new WishList(new StrategyB());
		if ( strategy.compareTo("C")==0 )
			wish = new WishList(new StrategyC());
	}
	
	//ii aloca unui client un ShoppingCart nou
	public void newCart(ShoppingCart nou){
		this.cart = nou;
	}
	
	public String getStrategy(){
		return this.strategy;
	}
	
	public void setStrategy(String strategy){
		this.strategy = strategy;
	}
	
	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public Customer getCustomer(){
		return this;
	}
	
	public void addWish(Item item){
		wish.add(item);
	}
	
	public WishList getWish(){
		return this.wish;
	}
	
	public ShoppingCart getCart(){
		return this.cart;
	}
	
	public Collection<Notification> getNotifications(){
		return this.coll;
	}
	
	public boolean addCartItem(Item item){
		//se adauga doar daca mai are destul buget
		if (cart.add(item) == true )
			return true;
		return false;
	}
	
	public void removeItemWish(Item item){
		wish.remove(item);
	}
	
	public void removeItemCart(Item item){
		cart.remove(item);
	}

	@Override
	public void update(Notification notification) {
		// TODO Auto-generated method stub
		this.coll.add(notification);	
	}
	
	public String toString(){
		return this.getName();
	}
}
