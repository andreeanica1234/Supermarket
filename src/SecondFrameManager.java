import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SecondFrameManager extends JFrame {
	JPanel p;
	Item item_selected;
	Store store;
	Item item_added;
	
	public void CloseFrame(){
		super.dispose();
	}
	
	public SecondFrameManager(Store store) throws IOException{
		//fereastra destinata managerului
		this.setVisible(true);
		this.setTitle(store.getName());
		this.setPreferredSize(new Dimension(1000,1000));
		p = new JPanel();
		
		JPanel p_dr = new JPanel();
		p_dr.setBackground(Color.pink);
		p_dr.setPreferredSize(new Dimension(60, 600));
		this.add(p_dr, BorderLayout.EAST);
		
		JPanel p_st = new JPanel();
		p_st.setPreferredSize(new Dimension(60, 600));
		p_st.setBackground(Color.pink);
		this.add(p_st, BorderLayout.WEST);
		
		JPanel p_titlu = new JPanel(new BorderLayout());
		p_titlu.setPreferredSize(new Dimension(600, 100));
		p_titlu.setBackground(Color.PINK);
		p_titlu.setForeground(Color.pink);
		this.add(p_titlu, BorderLayout.NORTH);
		
		JLabel titlu = new JLabel("           Items:");
		titlu.setFont(new Font("Comis Sans MS", Font.BOLD, 25));
		titlu.setBackground(Color.LIGHT_GRAY);
		titlu.setForeground(Color.BLACK);
		titlu.setPreferredSize(new Dimension(180,80));
		titlu.setOpaque(false);
		p_titlu.add(titlu, BorderLayout.WEST);
		
		//se creeaza butoane pentru: stergere, iesire, adaugare, modificare, sortare
		JButton delete = new JButton();
		delete.setText("Delete Item");
		delete.setPreferredSize(new Dimension(200, 50));
		delete.setForeground(Color.PINK);
		delete.setBorder(BorderFactory.createLineBorder(Color.red));
		delete.setFont(new Font("Comis Sans MS", Font.BOLD, 20));
		JPanel p_jos = new JPanel(new BorderLayout());
		p_jos.setPreferredSize(new Dimension(100, 80));
		
		JButton exit = new JButton();
		exit.setText("I'm done");
		exit.setPreferredSize(new Dimension(150, 10));
		exit.setForeground(Color.pink);
		exit.setBorder(BorderFactory.createLineBorder(Color.red));
		exit.setFont(new Font("Comis Sans MS", Font.BOLD, 20));
		p_titlu.add(exit, BorderLayout.EAST);
		
		JButton add = new JButton();
		add.setText("Add Item");
		add.setPreferredSize(new Dimension(200, 50));
		add.setForeground(Color.pink);
		add.setBorder(BorderFactory.createLineBorder(Color.red));
		add.setFont(new Font("Comis Sans MS", Font.BOLD, 20));
		
		JButton sort = new JButton();
		sort.setText("Sort");
		sort.setPreferredSize(new Dimension(300, 80));
		sort.setForeground(Color.pink);
		sort.setBorder(BorderFactory.createLineBorder(Color.red));
		sort.setFont(new Font("Comis Sans MS", Font.BOLD, 20));
		
		JButton modify = new JButton();
		modify.setText("Modify");
		modify.setPreferredSize(new Dimension(280, 80));
		modify.setForeground(Color.pink);
		modify.setBorder(BorderFactory.createLineBorder(Color.red));
		modify.setFont(new Font("Comis Sans MS", Font.BOLD, 20));
				
		p_jos.setBackground(Color.PINK);
		p_jos.setForeground(Color.pink);
		
		p_jos.add(delete, BorderLayout.EAST);
		p_jos.add(add, BorderLayout.WEST);
		
		//adaug in centru un panel, in care pun sort si modify
		JPanel aux = new JPanel(new BorderLayout());
		aux.setPreferredSize(new Dimension(100, 180));
		p_jos.add(aux, BorderLayout.CENTER);
		aux.add(sort, BorderLayout.WEST);
		aux.add(modify, BorderLayout.EAST);
		
		p.setLayout(new GridLayout(3,3));
		p.setBackground(Color.pink);
		p.setPreferredSize(new Dimension(400,200));
		this.add(p, BorderLayout.SOUTH);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBackground(Color.LIGHT_GRAY);
		scroll.setPreferredSize(new Dimension(400,100));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 4;
		gbc_scrollPane.gridy = 3;
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		//creez lista de produse din magazin
		JList lista;
		DefaultListModel x = new DefaultListModel();
		for (int i = 0; i < store.getItems().size(); i++){
			x.addElement(store.getItems().get(i));
		}
		lista = new JList(x);
		lista.setFont(new Font("Comis Sans MS", Font.BOLD, 20));
		scroll.setViewportView(lista);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.setBorder(BorderFactory.createLineBorder(Color.red));
		lista.setSelectionBackground(Color.pink);
		lista.setSelectionForeground(Color.red);
		scroll.setVisible(true);
		this.add(scroll, BorderLayout.CENTER);
		lista.addListSelectionListener(new ListSelectionListener() {
				//retine item-ul selectat
				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					// TODO Auto-generated method stub
					if (!arg0.getValueIsAdjusting()){
						item_selected = (Item) lista.getSelectedValue();
					}
				}
	        });

		delete.addActionListener(new ActionListener(){
			//butonul de stergere
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//daca nu e selectat niciun produs, nu se intampla nimic
				if (item_selected == null)
					return;
				int item_id = item_selected.getID();
				Department dep = store.getDepartement(item_selected.getDep());
				Item item = item_selected;
				store.getItemDep(item_id).removeItem(item);
				
				//dupa ce s-a sters un produs se sterge si din wishList uri + update observeri
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
							dep.getCustomers().get(k).getCart().remove(j);
							store.getCustomer(c.get(k).getName()).getCart().setBuget(bg);
						}
					}
				}
				//elementul se sterge nu doar din baza de date a magazinului, ci si din lista care afiseaza
				x.removeElement(item_selected);
				lista.getModel();
			}
		});
		
		add.addActionListener(new ActionListener(){
			//butonu de adaugare a unui element
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//deschide fereastra noua, de unde se citesc datele noului produs 
				JFrame f = new JFrame("Add Product");
				JTextField name = new JTextField(35);
				JTextField di = new JTextField(20);
				JTextField ii = new JTextField(20);
				JTextField pr = new JTextField(20);
				
				JPanel p = new JPanel(new BorderLayout());
				f.setContentPane(p);
				
				JPanel p1 = new JPanel(new GridLayout(0,1));
				JPanel p2 = new JPanel(new GridLayout(0,1));
				p1.setBackground(Color.pink);
				p1.setFont(new Font("", Font.PLAIN, 20));
				p.add(p1, BorderLayout.WEST);
				p.add(p2, BorderLayout.CENTER);
				
				p1.add(new JLabel("Product name:"));
				p2.add(name);
				p1.add(new JLabel("Department id:"));
				p2.add(di);
				p1.add(new JLabel("Product id:"));
				p2.add(ii);
				p1.add(new JLabel("Price"));
				p2.add(pr);
				JButton add = new JButton("Add");
				add.setForeground(Color.pink);
				add.setFont(new Font("", Font.BOLD, 20));
				
				add.addActionListener(new ActionListener(){
					//se face adaugarea propriu zisa in bazele magazinului
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						String item_name = name.getText();
						int dep_id = Integer.parseInt(di.getText());
						int item_id = Integer.parseInt(ii.getText());
						double price = Double.parseDouble(pr.getText());
						
						item_added = new Item(item_name, item_id, price); 
						if (store.containsItem(item_id) == true){
							//daca prodului exista deja, se deschide o noua fereastra in care managerul este anuntat de acest lucru
							JFrame q = new JFrame("Error");
							q.setPreferredSize(new Dimension(150,150));
							JPanel p1 = new JPanel();
							p1.setPreferredSize(getMaximumSize());
							JLabel l = new JLabel("This item already exists!");
							l.setBackground(Color.pink);
							p1.setBackground(Color.PINK);
							p1.setForeground(Color.pink);
							p1.setFont(new Font("", Font.BOLD, 30));
							p1.add(l);
							q.add(p1);
							q.setContentPane(p1);
							q.setVisible(true);
							JButton ok = new JButton("Ok");
							ok.setForeground(Color.PINK);
							ok.setFont(new Font("", Font.BOLD, 20));
							p1.add(ok);
							ok.addActionListener(new ActionListener(){
								@Override
								public void actionPerformed(ActionEvent arg0) {
									// TODO Auto-generated method stub
									q.dispose();
									f.dispose();
								}
							});
							q.pack();
							q.setLocationRelativeTo(null);
						}
						//daca elementul nu exista deja, este adaugat si se trimite notificare clientilor
						else{
							item_added.setDep(dep_id);
							store.getDepartement(dep_id).addItem(item_added);
							Notification notification = new Notification(new Date(), Notification.NotificationType.ADD, dep_id, item_id);
							store.getDepartement(dep_id).notifyAllObservers(notification);
							x.addElement(item_added);
							lista.getModel();
							add.setEnabled(false);
							f.dispose();
						}
					}
				});
				
				p.add(add, BorderLayout.SOUTH);
				f.setVisible(true);
				f.pack();
				f.setLocationRelativeTo(null);
			}
		});
		
		sort.addActionListener(new ActionListener(){
			//cand se apasa butonul de sortare, se deschide o noua fereastra de unde poate fi ales modul de sortare (dupa nume, pret etc)
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame  f = new JFrame("Sort");
				JPanel p = new JPanel(new BorderLayout());
				f.setContentPane(p);
				JButton sn = new JButton("Sort by name");
				sn.setPreferredSize(new Dimension(500,50));
				JButton spc = new JButton("Sort by price: Low to High");
				spc.setPreferredSize(new Dimension(50,50));
				JButton spd = new JButton("Sort by price: High to Low");
				spd.setPreferredSize(new Dimension(50,50));
				p.add(sn, BorderLayout.NORTH);
				p.add(spc, BorderLayout.CENTER);
				p.add(spd, BorderLayout.SOUTH);
				f.setVisible(true);
				f.pack();
				f.setLocationRelativeTo(null);
				
				spc.addActionListener(new ActionListener(){
					//sortare crescatoare dupa pret
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						f.dispose();
						Comparator<Item> c = new MyComparator2();
						ArrayList<Item> y = new ArrayList<Item>();
						for (int i = 0; i<x.size(); i++)
							y.add((Item) x.get(i));
						y.sort(c);
						x.removeAllElements();
						for (int i = 0; i<y.size(); i++){
							x.addElement(y.get(i));
						}
					}
				});
				
				sn.addActionListener(new ActionListener(){
					//sortare dupa nume
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						Comparator<Item> c = new MyComparator1();
						f.dispose();
						ArrayList<Item> y = new ArrayList<Item>();
						for (int i = 0; i<x.size(); i++)
							y.add((Item) x.get(i));
						y.sort(c);
						x.removeAllElements();
						for (int i = 0; i<y.size(); i++){
							x.addElement(y.get(i));
						}
					}	
				});
				
				spd.addActionListener(new ActionListener(){
					//sortare descrescatoare dupa pret
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Comparator<Item> c = new MyComparator3 ();
						f.dispose();
						ArrayList<Item> y = new ArrayList<Item>();
						for (int i = 0; i<x.size(); i++)
							y.add((Item) x.get(i));
						y.sort(c);
						x.removeAllElements();
						for (int i = 0; i<y.size(); i++){
							x.addElement(y.get(i));
						}
					}
				});
			}
		});
		
		exit.addActionListener(new ActionListener(){
			//fereastra de iesire
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame q = new JFrame("Oh no");
				q.setPreferredSize(new Dimension(400,130));
				JPanel p1 = new JPanel();
				p1.setPreferredSize(getMaximumSize());
				JLabel l = new JLabel("Are you sure?");
				l.setBackground(Color.pink);
				p1.setBackground(Color.PINK);
				p1.setForeground(Color.pink);
				p1.setFont(new Font("", Font.BOLD, 30));
				p1.add(l);
				q.add(p1);
				q.setContentPane(p1);
				q.setVisible(true);
				JButton yes = new JButton("Yes");
				yes.setForeground(Color.PINK);
				yes.setFont(new Font("", Font.BOLD, 20));
				p1.add(yes, BorderLayout.WEST);
				JButton no = new JButton("No");
				no.setForeground(Color.PINK);
				no.setFont(new Font("", Font.BOLD, 20));
				p1.add(no, BorderLayout.EAST);
				yes.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						q.dispose();
						CloseFrame();
					}
				});
				
				no.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						q.dispose();
					}	
				});
				
				q.pack();
				q.setLocationRelativeTo(null);
			}
		});
		
		modify.addActionListener(new ActionListener(){
			//butonul de modificare a unui produs
			//se deschide fereastra noua in care se pun datele produsului de modificat si noul pret
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame  f = new JFrame("Modify Product");
				JTextField di = new JTextField(20);
				JTextField ii = new JTextField(20);
				JTextField pr = new JTextField(20);
				
				JPanel p = new JPanel(new BorderLayout());
				f.setContentPane(p);
				
				JPanel p1 = new JPanel(new GridLayout(0,1));
				JPanel p2 = new JPanel(new GridLayout(0,1));
				p1.setBackground(Color.pink);
				p1.setFont(new Font("", Font.PLAIN, 20));
				p.add(p1, BorderLayout.WEST);
				p.add(p2, BorderLayout.CENTER);
				
				p1.add(new JLabel("Department id:"));
				p2.add(di);
				p1.add(new JLabel("Product id:"));
				p2.add(ii);
				p1.add(new JLabel("New price"));
				p2.add(pr);
				JButton modify1 = new JButton("Modify");
				modify1.setPreferredSize(new Dimension(100,80));
				modify1.setForeground(Color.pink);
				modify1.setFont(new Font("", Font.BOLD, 20));
				
				modify1.addActionListener(new ActionListener(){
					@Override
					//se modifica in bazele magazinului pretul produsului
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						int dep_id = Integer.parseInt(di.getText());
						int item_id = Integer.parseInt(ii.getText());
						double price = Double.parseDouble(pr.getText());
						String name = store.getDepartement(dep_id).getItem(item_id).getNume();
						Item old_item = store.getDepartement(dep_id).getItem(item_id);
						item_added = new Item(name, item_id, price); 
						Department departament = store.getDepartement(dep_id);
						double old_price = departament.getItem(item_id).getPret();
						departament.getItem(item_id).setPrice(price);
						// modific si in ShoppingCart-urile clientilor
						ArrayList<Customer> c = store.getDepartement(dep_id).getCustomers();
						for (int k = 0; k<c.size(); k++){
							Customer cust = c.get(k);
							for (int j = 0; j < cust.getCart().size(); j++){
								if (cust.getCart().getItem(j).getID() == item_id ){
									//daca noul pret este mai mare 
									if (old_price < price ){
										//cazul in care noul pret este prea mare iar produsul trebuie sa dispara din cosul de cumparaturi al clientului
										if (cust.getCart().getBuget() < (price-old_price) ){
											cust.getCart().remove(j);
											cust.getCart().setBuget(cust.getCart().getBuget()+old_price); 		//dupa ce s-a sters elementul; se actualizeaza bugetul
										}
										else
											cust.getCart().setBuget(cust.getCart().getBuget() - (price-old_price) );
										}
									
									//daca noul pret este mi mic, doar se restituie diferenta de bani
									else{
										cust.getCart().setBuget(cust.getCart().getBuget() + (old_price - price ) );
									}
								}
							}
						}
						//se trimit notificari observerilor
						Notification notification = new Notification(new Date(), Notification.NotificationType.MODIFY, dep_id, item_id);
						departament.notifyAllObservers(notification);
						modify1.setEnabled(false);
						x.removeElement(old_item);
						x.addElement(item_added);
						lista.getModel();
						f.setVisible(false);
						f.dispose();
					}
				});
				
				p.add(modify1, BorderLayout.SOUTH);
				f.setVisible(true);
				f.pack();
				f.setLocationRelativeTo(null);
			}
		});
	
		this.add(p_jos, BorderLayout.SOUTH);
		show();
		pack();
		this.setLocationRelativeTo(null);
	}
	//comparatorii pentru sortare
	class MyComparator1 implements Comparator<Item>{

		public int compare(Item o1, Item o2) {
			return o1.getNume().compareTo(o2.getNume());
		}
	}
	
	class MyComparator2 implements Comparator<Item>{
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
	class MyComparator3 implements Comparator<Item>{
		public int compare(Item o1, Item o2) {
			if ( o1.getPret()-o2.getPret() == 0)
				return o2.getNume().compareTo(o1.getNume());
			else
				if ( o1.getPret()-o2.getPret() < 0 )
					return 1;
				else
					return -1;
		}
	}
	
}
