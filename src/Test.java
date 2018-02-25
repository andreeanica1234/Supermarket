import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.StringTokenizer;

public class Test{
	
	public static void main(String[] args) throws IOException{
		
		//Citire si initializare magazin si departamente
		FileReader file = new FileReader("store.txt");
		BufferedReader br = new BufferedReader(file);
		
		Store magazin = Store.getInstance();	//creare magazin
		
		String line;
		line = br.readLine();
		
		magazin.setName(line);
		
		while ( (line = br.readLine()) != null ){ 		//citeste pana se termina fisierul
			StringTokenizer st = new StringTokenizer(line, ";");
			if ( st.countTokens() == 2 ){ //=>nume departament
				String name_dep = st.nextToken();
				int id_dep =Integer.parseInt(st.nextToken());
				
				//creez departamentul
				if ( name_dep.compareTo("BookDepartment") == 0 ){
					BookDepartment department = new BookDepartment(name_dep , id_dep);
					magazin.addDepartment(department);					
					//daca pe randul curent a fost creat un nou departament => sigur pe urmatorul rand se afla numarul de item-uri din dep;
					line = br.readLine();
					int n = Integer.parseInt(line); // numarul de ite-uri din departament
					for (int i = 0; i < n; i++){
						line  = br.readLine();
						st = new StringTokenizer(line, ";");
						String name_item = st.nextToken();
						int ID_item = Integer.parseInt(st.nextToken());
						double ID_price = Double.parseDouble(st.nextToken());
						Item item = new Item(name_item, ID_item, ID_price); 
						item.setDep(id_dep);
						department.addItem(item);	
					}
					
					}
				if ( name_dep.compareTo("MusicDepartment") == 0 ){
					MusicDepartment department = new MusicDepartment(name_dep , id_dep);
					magazin.addDepartment(department);					
					//daca pe randul curent a fost creat un nou departament => sigur pe urmatorul rand se afla numarul de item-uri din dep;
					line = br.readLine();
					int n = Integer.parseInt(line); // numarul de ite-uri din departament
					for (int i = 0; i < n; i++){
						line  = br.readLine();
						st = new StringTokenizer(line, ";");
						String name_item = st.nextToken();
						int ID_item = Integer.parseInt(st.nextToken());
						double ID_price = Double.parseDouble(st.nextToken());
						Item item = new Item(name_item, ID_item, ID_price); 
						item.setDep(id_dep);
						department.addItem(item);	
						}
					
					}
				if ( name_dep.compareTo("VideoDepartment") == 0 ){
					VideoDepartment department = new VideoDepartment(name_dep , id_dep);
					magazin.addDepartment(department);					
					//daca pe randul curent a fost creat un nou departament => sigur pe urmatorul rand se afla numarul de item-uri din dep;
					line = br.readLine();
					int n = Integer.parseInt(line); // numarul de ite-uri din departament
					for (int i = 0; i < n; i++){
						line  = br.readLine();
						st = new StringTokenizer(line, ";");
						String name_item = st.nextToken();
						int ID_item = Integer.parseInt(st.nextToken());
						double ID_price = Double.parseDouble(st.nextToken());
						Item item = new Item(name_item, ID_item, ID_price); 
						item.setDep(id_dep);
						department.addItem(item);	
						}
					
					}
				if ( name_dep.compareTo("SoftwareDepartment") == 0 ){
					SoftwareDepartment department = new SoftwareDepartment(name_dep , id_dep);
					magazin.addDepartment(department);					
					//daca pe randul curent a fost creat un nou departament => sigur pe urmatorul rand se afla numarul de item-uri din dep;
					line = br.readLine();
					int n = Integer.parseInt(line); // numarul de ite-uri din departament
					for (int i = 0; i < n; i++){
						line  = br.readLine();
						st = new StringTokenizer(line, ";");
						String name_item = st.nextToken();
						int ID_item = Integer.parseInt(st.nextToken());
						double ID_price = Double.parseDouble(st.nextToken());
						Item item = new Item(name_item, ID_item, ID_price); 
						item.setDep(id_dep);
						department.addItem(item);	
						}
					}
			}
		}
		
		//Citire si initializare customeri
		file = new FileReader("customers.txt");
		br = new BufferedReader(file);
		
		int nr_custom = Integer.parseInt(br.readLine());
		for (int i = 0; i < nr_custom; i++){
			line  = br.readLine();
			StringTokenizer st = new StringTokenizer(line, ";");
			String name_cl = st.nextToken();
			double buget = Double.parseDouble(st.nextToken());
			String strategy = (String)st.nextToken();
			Customer customer = new Customer(name_cl, buget, strategy);
			magazin.enter(customer);
		}
		
		file = new FileReader("events.txt");
		br = new BufferedReader(file);
		
		int nr_events = Integer.parseInt(br.readLine());
		for (int i = 0; i < nr_events; i++){
			line = br.readLine();
			StringTokenizer st = new StringTokenizer(line, ";");
			String get_event = st.nextToken();
			//particularizam pentru fiecare tip de eveniment
			//se adauga un produs in ShoppingCart/WishList
			if ( get_event.compareTo("addItem") == 0 ){ 
				int item_id = Integer.parseInt(st.nextToken());
				String cart_wish = st.nextToken();
				String custom_name = st.nextToken();
				if ( cart_wish.compareTo("ShoppingCart") == 0 ){
					// adauga itemul cu nr item_id in ShoppingCart-ul clientului custom_name
					Item item = magazin.getItem(item_id);
					magazin.getCustomer(custom_name).addCartItem(item); 
				}
				if ( cart_wish.compareTo("WishList") == 0 ){
					Item item = magazin.getItem(item_id);
					//adaug produsul in WishList 
					magazin.getCustomer(custom_name).getWish().add(item);
					magazin.getCustomer(custom_name).getWish().setLastAdded(item);
					//adaug clientul in lista de observeri a departamentului
					
					Department depart = magazin.getDepartement(item.getDep());
					Customer c = magazin.getCustomer(custom_name);
					if (depart.containsObserver(c) == false)
						depart.addObserver(c);
				}
			}
			//adauga un produs in cadrul magazinului
			if ( get_event.compareTo("addProduct") == 0 ){ 
				int dep_id = Integer.parseInt(st.nextToken());
				int item_id = Integer.parseInt(st.nextToken());
				double price = Double.parseDouble(st.nextToken());
				String item_name = st.nextToken();
				Item item = new Item(item_name, item_id, price); 
				item.setDep(dep_id);
				magazin.getDepartement(dep_id).addItem(item);
				Notification notification = new Notification(new Date(), Notification.NotificationType.ADD, dep_id, item_id);
				magazin.getDepartement(dep_id).notifyAllObservers(notification);
			}
			//se sterge un produs dintr-un ShoppingCart/WishList
			if ( get_event.compareTo("delItem") == 0 ){
				int item_id = Integer.parseInt(st.nextToken());
				String cart_wish = st.nextToken();
				String custom_name = st.nextToken();
				if ( cart_wish.compareTo("ShoppingCart") == 0 ){
					// sterge itemul cu nr item_id in ShoppingCart-ul clientului custom_name
					Item item = magazin.getItem(item_id);
					magazin.getCustomer(custom_name).getCart().remove(item); 
				}
				if ( cart_wish.compareTo("WishList") == 0 ){
					Item item = magazin.getItem(item_id);
					int departament_item = item.getDep();
					magazin.getCustomer(custom_name).getWish().remove(item);
					//se verifica daca clientul care si-a sters produsul mai este observer in cadrul acelui departament
					WishList w = magazin.getCustomer(custom_name).getWish();
					int ok = 1;
					if (w.size() == 0){
						magazin.getDepartement(item.getDep()).removeObserver(magazin.getCustomer(custom_name));
					}
					else{
						for (int k = 0; k < w.size(); k++){	
							if ( w.getItem(k).getDep() == departament_item ){	
									ok = 0;
						}
						}
						if(ok == 1){
							Department depart = magazin.getDepartement(item.getDep());
							Customer c = magazin.getCustomer(custom_name);
							depart.removeObserver(c);
						}
					}
				}
			}
			
			if ( get_event.compareTo("delProduct") == 0 ){
				int item_id = Integer.parseInt(st.nextToken());
				Department dep = magazin.getItemDep(item_id);
				Item item = magazin.getItem(item_id);
				magazin.getItemDep(item_id).removeItem(item);
				
				//dupa ce s-a sters un produs se sterge si din wishList uri si opdate observeri
				ArrayList<Customer> c = dep.getObservers();
				for (int k = 0 ; k < c.size(); k++){
					WishList wish = c.get(k).getWish();
					for (int j = 0; j<wish.size(); j++){
						if (wish.getItem(j).getID() == item_id){
							wish.remove(j);
						}
					}
					int ok = 1;
					for (int j = 0; j < wish.size(); j++){
						if (wish.getItem(j).getDep() == dep.getID())
							ok = 0;
					}
					if(ok == 1){
						dep.removeObserver(c.get(k));
					}
				}
				c = dep.getCustomers();
				for (int k = 0 ; k < c.size(); k++){
					WishList wish = c.get(k).getWish();
					for (int j = 0; j<wish.size(); j++){
						if (wish.getItem(j).getID() == item_id){
							wish.remove(j);
						}
					}
					int ok = 1;
					for (int j = 0; j < wish.size(); j++){
						if (wish.getItem(j).getDep() == dep.getID())
							ok = 0;
					}
					if(ok == 1){
						dep.removeObserver(c.get(k));
					}
				}
				
				//stergere din Shoppingcart
				c = dep.getCustomers();
				for (int k = 0; k <c.size(); k++){
					ShoppingCart cart = c.get(k).getCart();
					for (int j = 0; j < cart.size(); j++){
						if (cart.getItem(j).getID() == item_id){
							double bg = cart.getItem(j).getPret();
							bg = cart.getBuget() + bg;
							cart.remove(j);
							cart.setBuget(bg);
						}
					}
				}
		
				//se trimite notificare observerilor
				Notification notification = new Notification(new Date(), Notification.NotificationType.REMOVE, dep.getID(), item_id);
				dep.notifyAllObservers(notification);
				
			} 
			//returneaza produsele din ShoppingCartul/WishListul unui client
			if ( get_event.compareTo("getItems") == 0 ){ 
				ArrayList<Item> items = new ArrayList<Item>();
				String cart_wish = st.nextToken();
				if ( cart_wish.compareTo("WishList") == 0){
					String custom_name = st.nextToken();
					for (int k =0; k<magazin.getCustomer(custom_name).getWish().size(); k++){
						if (magazin.getCustomer(custom_name).getWish().getItem(k) != null)
							items.add(magazin.getCustomer(custom_name).getWish().getItem(k));
					}
					System.out.println(magazin.getCustomer(custom_name).getWish().toString());
					
				}
				if ( cart_wish.compareTo("ShoppingCart") == 0){
					String custom_name = st.nextToken();
					for (int k =0; k<magazin.getCustomer(custom_name).getCart().size(); k++){
						items.add(magazin.getCustomer(custom_name).getCart().getItem(k));
					}
					System.out.println(magazin.getCustomer(custom_name).getCart().toString());	
				}		
			}
			//modifica pretul unui anumit produs
			if (get_event.compareTo("modifyProduct") == 0){
				int dep_id = Integer.parseInt(st.nextToken());
				int item_id = Integer.parseInt(st.nextToken());
				double price = Double.parseDouble(st.nextToken());
				Department departament = magazin.getDepartement(dep_id);
				double old_price = departament.getItem(item_id).getPret();
				departament.getItem(item_id).setPrice(price);
				// modific si in ShoppingCart-urile clientilor
				ArrayList<Customer> c = departament.getCustomers();
				for (int k = 0; k<c.size(); k++){
					Customer cust = c.get(k);
					for (int j = 0; j < cust.getCart().size(); j++)
						if (cust.getCart().getItem(j).getID() == item_id ){
							if (old_price < price ){
								if (cust.getCart().getBuget() < (price-old_price) )
									cust.getCart().remove(j);
								else
									cust.getCart().setBuget(cust.getCart().getBuget() - (price-old_price) );
							}
							else{
								cust.getCart().setBuget(cust.getCart().getBuget() + (old_price - price ) );
							}
						}
				}
				//trimite notificari observerilor
				Notification notification = new Notification(new Date(), Notification.NotificationType.MODIFY, dep_id, item_id);
				departament.notifyAllObservers(notification);
			}
			//intoarce pretul total al produselor dintr-un cos sau lista de dorinte
			if (get_event.compareTo("getTotal") == 0){
				double total = 0;
				String cart_wish = st.nextToken();
				if ( cart_wish.compareTo("WishList") == 0){
					String custom_name = st.nextToken();
					total = magazin.getCustomer(custom_name).getWish().getTotalPrice();
					}
				if ( cart_wish.compareTo("ShoppingCart") == 0){
					String custom_name = st.nextToken();
					total = magazin.getCustomer(custom_name).getCart().getTotalPrice();
					}
				System.out.printf("%.2f\n", total);
			}
			//intoarce observerii unui departament
			if ( get_event.compareTo("getObservers") == 0 ){
				int dep_id = Integer.parseInt(st.nextToken());
				ArrayList<Customer> obs = magazin.getDepartement(dep_id).getObservers();
				System.out.print("[");
				int k;
				if ( obs.size() == 0){
					System.out.println("]");
				}
				else{
					for(k=0; k<obs.size()-1; k++)
						System.out.print(obs.get(k).getName()+ ", ");
					System.out.println(obs.get(k).getName()+"]");
				}
			}
			
			if ( get_event.compareTo("accept") == 0 ){
				int dep_id = Integer.parseInt(st.nextToken());
				String custom_name = st.nextToken();
				
				//modifica conform accept toate produsele clientului custom_name din departamentul respectiv
				Customer c = magazin.getCustomer(custom_name);
				Department d = magazin.getDepartement(dep_id);
				d.accept(c.getCart());
			}
				
			//intoarce un anumit produs din wishlist ul unui client tinand cont de strategia lui
			if ( get_event.compareTo("getItem") == 0 ){ 
				String custom_name = st.nextToken();
				// tinem cont de strategie 
				String strategy = magazin.getCustomer(custom_name).getStrategy();
				Item deSters;
				
				deSters = magazin.getCustomer(custom_name).getWish().executeStrategy();
				System.out.println(deSters.toString());
				
				//se sterge produsul din wishList
				magazin.getCustomer(custom_name).getWish().remove(deSters);
				//daca nu se poate adauga in ShoppingCart, se adauga in WishList
				if ( magazin.getCustomer(custom_name).getCart().add(deSters) == false){
					magazin.getCustomer(custom_name).getWish().add(deSters);
					magazin.getCustomer(custom_name).getWish().setLastAdded(deSters);
				}
				else{
					magazin.getCustomer(custom_name).getCart().setBuget(magazin.getCustomer(custom_name).getCart().getBuget()- deSters.getPret());
				}
				
				//se verifica daca clientul mai e observerul acestui departament
				int ok = 1;
				int depart = deSters.getDep();
				for(int k = 0; k < magazin.getCustomer(custom_name).getWish().size(); k++){
					if ( (magazin.getCustomer(custom_name).getWish().getItem(k) !=null ) && magazin.getCustomer(custom_name).getWish().getItem(k).getDep() == depart )
						ok = 0;
				}
				
				if (ok == 1){
					magazin.getDepartement(depart).removeObserver(magazin.getCustomer(custom_name));
				}
			}	
			//intoarce notificarile unui client
			if ( get_event.compareTo("getNotifications") == 0 ){
				String custom_name = st.nextToken();
				System.out.println(magazin.getCustomer(custom_name).getNotifications().toString());
			}
		}		
	}
}
