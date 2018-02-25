import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

public abstract class ItemList implements Iterable<Item>{
	private int n;
	Comparator comp;
	
	public static class Node<Item>{
		private Item item;
		private Node<Item> next;
		private Node<Item> prev;
		
		public Node(){
		}
		
		public Item getNodeItem(){
			return this.item;
		}
	}
	
	private Node<Item> head;
	private Node<Item> tail; 
	
	public boolean isEmpty(){
		return n==0;
	}
	
	public int size(){
		return n;
	}
	
	public boolean add(Item item){ 
		Node x = new Node();
		n++;
		x.item = item;
		//pentru primul element adaugat in lista
		if (head == null){ 
			x.next = null;
			x.prev = null;
			head = tail = x;
			return true;
		}
		//daca se adauga inaintea primului element
		if ( comp.compare(item, head.getNodeItem()) <= 0 ){ 
			x.next = head;
			head.prev = x;
			head = x;
			return true;
		}
		//daca se adauga dupa ultimul element
		if (comp.compare(item,(Item) tail.getNodeItem())>=0){
			tail.next = x;
			x.prev = tail;
			tail = x;
			return true;
		}
		//se adauga intre 2 noduri random
		Node first = new Node();
		first = head;
		while (comp.compare(item, (Item) first.getNodeItem()) < 0){
			first = first.next;
		}
		Node aux = new Node();
		aux = first.next;
		first.next = x;
		x.next = aux;
		x.prev = first;
		aux.prev = x;
		return true;
	}
	
	public String toString() {
		String s = "[";
		int i;
		if (this.size()==0)
			s += "]";
		else{
			for( i = 0; i < this.size()-1; i++)
				s += this.getItem(i).getNume() + ";"+this.getItem(i).getID()+";"+this.getItem(i).getPret()+", ";
			s +=this.getItem(i).getNume() + ";"+this.getItem(i).getID()+";"+this.getItem(i).getPret() +"]";
		}	
		return s;
	}
	
	//returneaza iteratorul
	public ListIterator<Item> iterator(){
		return new ItemIterator();
	}
	
	class ItemIterator<Item> implements ListIterator<Item>{
		private Node current = head;
		private Node lastAcc = null;
		private int currentIndex = 0;
		
	public ItemIterator(){
	}
	
	public ItemIterator(int currentIndex){
		this.currentIndex = currentIndex;
	}
		
	public ItemIterator(Node<Item> current, int currentIndex){
		this.current = current;
		this.currentIndex = currentIndex;
	}
	
	@Override
	public void add(Item e) {
		Node x = current.prev;
		Node y = new Node();
		Node z = current;
		y.item = e;
		x.next = z;
		y.next = z;
		z.prev = y;
		y.prev = x;
		n++;
		currentIndex++;
		lastAcc = null;	
	}

	@Override
	public boolean hasNext() {
		if ( (n-currentIndex) > 0 )
			return true;
		return false;
	}

	@Override
	public boolean hasPrevious() {
		if ( (n-currentIndex) <= 0 )
			return true;
		return false;
	}

	@Override
	public Item next() {
		// TODO Auto-generated method stub
		if (!hasNext())
			return null;
		else{
			Item item = (Item) current.item;
			lastAcc=current;
			current = current.next;
			currentIndex++;
			return item;
		}
	}

	@Override
	public int nextIndex() {
		// TODO Auto-generated method stub
		if (current == null)
			return 0;
		else
			return currentIndex;
	}

	@Override
	public Item previous() {
		// TODO Auto-generated method stub
		if (!hasPrevious())
			return null;
		else{
			current = current.prev;
			currentIndex--;
			lastAcc = current;
			return (Item)current.item;
		}
	}

	@Override
	public int previousIndex() {
		// TODO Auto-generated method stub
		return this.currentIndex-1;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		if (lastAcc == null)
			return;
		Node x = lastAcc.prev;
		Node y = lastAcc.next;
		x.next = y;
		y.prev = x;
		n--;
		if ( lastAcc == current )
			current = y;
		else
			currentIndex--;
		lastAcc = null;
	}

	@Override
	public void set(Item e) {
		// TODO Auto-generated method stub
		if (lastAcc == null)
			return;
		lastAcc.item = e;
	}
	
}

	public Item getItem(int index){
		int i=0;
		Node first = head;
		while( first != null ){
			if (i == index)
				return (Item) first.getNodeItem();
			i++;
			first = first.next;
		}
		return null;
	}
	
	public Node<Item> getNode(int index){
		int i=0;
		Node<Item> first = new Node<Item>();
		first = head;
		while( first != null ){
			if (i == index)
				return first;
			i++;
			first = first.next;
		}
		return null;
	}
	
	public int indexOf(Item item){
		Node<Item> first = head;
		int i=0;
		while ( first != null ){
			if ( first.getNodeItem() == item )
				return i;
			first = first.next;
			i++;
		}
		return -1;
	}
	
	public int indexOf(Node<Item> node){
		Node<Item> first = head;
		int i=0;
		while(first!=null){
			if (first == node)
				return i;
			i++;
			first = first.next;
		}
		return -1;
	}
	
	public boolean contains(Node<Item> node){
		Node<Item> first = head;
		while(first!=null){
			if (first == node)
				return true;
			first = first.next;
		}
		return false;
	}
	
	public boolean contains(Item item){
		Node<Item> first = head;
		while(first!=null){
			if ( first.getNodeItem().equalsTo(item) )
				return true;
			first = first.next;
		}
		return false;
	}
	
	
	public Item remove(int index){ 
		Node<Item> first = head;
		Item item;
		int i = 0;
		//se ajunge pe pozitia care precede nodul de sters
		while (first != null ){ 
			if (i == index)
				break;
			i++;
			first = first.next;
		}
		
		Node<Item> aux = first;
		if ( aux.next == null ){ 	//daca se sterge ultimul element
			item = aux.getNodeItem();
			if ( aux.prev != null ){		//daca e un singur nod in toat lista
				aux = null;
				n--;
				return item;
			}
			aux = aux.prev;
			first = aux;
			n--;
		}
		else
			if (aux.prev == null){ 		//daca se sterge primul nod din lista
				item = aux.getNodeItem();
				aux=aux.next;
				aux.prev = null;
				first = aux;
				head = aux;
				n--;
			}
			else{ 			//daca se sterge un nod intermediar
				item = aux.getNodeItem();
				aux = first =aux.prev;
				aux.next = aux.next.next;
				aux = aux.next.prev;
				aux.next = null;
				aux.prev = null;
				aux = null;
				aux = first;
				aux = aux.next;
				aux.prev = first;
				n--;
			}
		return item;
	}
	
	public boolean remove(Item item){ if (item.getID()==62) 
		if (n==0)
			return false;
		Node<Item> first = head;
		while (first != null  && (first.getNodeItem().getID() != item.getID()) ){  
				first = first.next;
			}
		Node<Item> aux = first;
		if ( aux.next == null ){		//se sterge ultimul element din lista
			if ( aux.prev == null ){ 		//daca e un singur nod in toat lista
				aux = null;
				head = aux;
				n--;
				return true;
			}
			first = aux;
			aux = aux.prev;
			first.prev = null;
			aux.next = null;
			first = aux;
			tail = aux;	//aiici ai modificat daca crapa
			n--;
			return true;
		}
		else
			if (aux.prev == null){		//se sterge primul element din lista 
				item = aux.getNodeItem();
				aux=aux.next;
				aux.prev = null;
				first = aux;
				head = aux;
				n--;
				return true;
			}
			else{		// se sterge un nod intermediar   
				item = aux.getNodeItem();
				aux = first =aux.prev;
				aux.next = aux.next.next;
				aux = aux.next.prev;
				aux.next = null;
				aux.prev = null;
				aux = null;
				aux = first;
				aux = aux.next;
				aux.prev = first;
				n--;
				return true;
			}
	}
	
	public ListIterator<Item> listIterator(int index){
		return new ItemIterator<Item>(index);
	}
	
	public ListIterator<Item> listIterator(){
		return new ItemIterator<Item>();
	}
	
	public Double getTotalPrice(){
		Node<Item> cursor = new Node<Item>();
		cursor = head;
		double price = 0;
		while(cursor!=null){
			price = price + cursor.getNodeItem().getPret();
			cursor = cursor.next;
		}
		return price;
		
	}
	
	public abstract boolean removeAll(Collection<? extends Item> collection);
	
	public abstract boolean addAll(Collection<? extends Item> collection);
}
