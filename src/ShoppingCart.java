import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;

public class ShoppingCart extends ItemList implements Visitor {
	double buget;
	Comparator<Item> comp = new MyComparator(); 
	
	class MyComparator implements Comparator<Item>{
		//compara in functie de pret; daca doua prodse au acelasi pret, compara dupa nume
		public int compare(Item o1, Item o2) {
			if ( o1.getPret()-o2.getPret() == 0)
				return o1.getNume().compareTo(o2.getNume());
			else
				if ( o1.getPret()-o2.getPret() < 0 )
					return -1;
				else
					return 1;
		}
	}

	public ShoppingCart(){
		super.comp = this.comp;
	}
	
	public ShoppingCart(double buget){
		super.comp = this.comp;
		this.buget = buget;
	}
	
	public boolean add(Item item){
		//adauga doar daca mai are buget disponibil
		if ( item.getPret() < this.buget + item.getPret() ){
			super.add(item);
			return true;
		}
		return false;
	}
	
	public void setBuget(double buget){
		this.buget = buget;
	}
	
	public double getBuget(){
		return this.buget;
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

	@Override
	public void visit(BookDepartment bookDepartment) {
		//scade 10% din pretul tuturor produselor din cos care apartin bookDep
		// TODO Auto-generate method stub
		for ( int i=0; i< this.size(); i++ ){
			if ( this.getItem(i).getDep() == bookDepartment.getID() ){
				double oldPr = this.getItem(i).getPret();
				this.getItem(i).setPrice(oldPr - 0.1*oldPr);
			}
		}

	}


	@Override
	public void visit(MusicDepartment musicDepartment) {
		//adauga 10% bugetului din totalul produselor din musicDep
		// TODO Auto-generated method stub
		double getTotal = 0;
		for (int i = 0; i<this.size(); i++){
			if ( this.getItem(i).getDep() == musicDepartment.getID() )
				getTotal = getTotal + this.getItem(i).getPret();
		}
		this.buget = this.buget + getTotal*0.1;
	}

	@Override
	public void visit(SoftwareDepartment softwareDepartment) {
		// TODO Auto-generated method stub
		double bg = 0;
		for (int i = 0; i<this.size(); i++){
			if (this.getItem(i).getDep() == softwareDepartment.getID()){
				bg = bg + this.getItem(i).getPret();
			}
			//cel mai ieftin produs = produsul de pe prima pozitie
			if ( bg < this.getItem(0).getPret()){
				for ( i = 0; i<this.size(); i++){
					double oldPr = this.getItem(i).getPret();
					oldPr = oldPr - 0.2*oldPr;
					this.getItem(i).setPrice(oldPr);
				}
			}
		}
	}

	@Override
	public void visit(VideoDepartment videoDepartment) { 
		// TODO Auto-generated method stub
		double bg = 0;
		for (int i=0; i<this.size(); i++){
			if (this.getItem(i).getDep() == videoDepartment.getID() ){
				bg = bg + this.getItem(i).getPret();
			}
			
			}
		if ( bg > this.getItem(this.size()-1).getPret()){
			for (int i=0; i<this.size(); i++){
				if (this.getItem(i).getDep() == videoDepartment.getID()){
					double oldPr = this.getItem(i).getPret();
					oldPr = oldPr - 0.15*oldPr;
					this.getItem(i).setPrice(oldPr);
				}
			}
		}
		double oldBg = this.buget;
		oldBg = oldBg - oldBg*0.05;
		this.setBuget(oldBg);
	}
}
