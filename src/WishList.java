import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

public class WishList extends ItemList{
	private Item lastAdded = new Item();	//se retine ultimul element adaugat in WishList (ajutor pentru Strategia C)
	private Strategy strategy;
	
	Comparator<Item> comp = new MyComparator();
	
	class MyComparator implements Comparator<Item>{	 	//se ordoneaza elementele dupa nume

		public int compare(Item o1, Item o2) {
			return o1.getNume().compareTo(o2.getNume());
		}
	}
	
	public Item getLastAdded(){
		return this.lastAdded;
	}

	public void setLastAdded(Item item){
		this.lastAdded=item;
	}
	
	public WishList(){
		super.comp = this.comp;
	}
	
	public WishList(Strategy strategy){
		super.comp = this.comp;
		this.strategy = strategy;
	}
	
	public Item executeStrategy(){
		return strategy.execute(this);
	}
	

	@Override
	public boolean removeAll(Collection<? extends Item> collection) {
		Iterator<Item> l = (Iterator<Item>) collection.iterator();
		while (l.hasNext()){
			this.remove(l.next());
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends Item> collection) {
		Iterator<Item> l = (Iterator<Item>) collection.iterator();
		while (l.hasNext()){
			this.add(l.next());
		}
		return true;
	}
	


}
