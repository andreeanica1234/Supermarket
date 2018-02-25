import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//fereastra de WishList (asemenea ferestrei ShoppingCart)
public class WishListFrame extends JFrame{
	Item item_selected;
	Item item_added;
	
	public void CloseFrame(){
		super.dispose();
	}
	
	public WishListFrame(Store store, Customer customer){
		this.setVisible(true);
		this.setTitle(customer.getName()+"'s WishList");
		this.setPreferredSize(new Dimension(1000,1000));
		
		JPanel p_top = new JPanel(new BorderLayout());
		this.add(p_top);
		p_top.setPreferredSize(new Dimension(180,80));
		p_top.setBackground(Color.pink);
		
		JLabel titlu = new JLabel("           Items:");
		titlu.setFont(new Font("", Font.BOLD, 25));
		titlu.setBackground(Color.LIGHT_GRAY);
		titlu.setForeground(Color.BLACK);
		titlu.setPreferredSize(new Dimension(180,80));
		titlu.setOpaque(false);
		p_top.add(titlu, BorderLayout.WEST);
		this.add(p_top, BorderLayout.NORTH);
		
		JButton exit1 = new JButton();
		exit1.setText("I'm done");
		exit1.setPreferredSize(new Dimension(150, 60));
		exit1.setForeground(Color.pink);
		exit1.setBorder(BorderFactory.createLineBorder(Color.red));
		exit1.setFont(new Font("Comis Sans MS", Font.BOLD, 20));
		p_top.add(exit1, BorderLayout.EAST);
		
		exit1.addActionListener(new ActionListener(){
			
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
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						q.dispose();
					}
					
				});
				q.pack();
				q.setLocationRelativeTo(null);
			}
		});
		
		JPanel centru = new JPanel();
		this.add(centru, BorderLayout.CENTER);
		centru.setBackground(Color.pink);
		centru.setPreferredSize(new Dimension(600,600));
		JPanel jos = new JPanel();
		this.add(jos, BorderLayout.SOUTH);
		jos.setBackground(Color.PINK);
		
		//panel in care se gasesc field si buton pentru afisarea pretului total al wishList ului
		JPanel buget = new JPanel(new GridLayout(0,1));
		JButton bg = new JButton();
		bg.setText("Get Total Price");
		bg.setPreferredSize(new Dimension(100, 50));
		bg.setForeground(Color.pink);
		bg.setBorder(BorderFactory.createLineBorder(Color.red));
		bg.setFont(new Font("", Font.BOLD, 15));
		JTextField bug = new JTextField(40);
		bug.setFont(new Font("", Font.BOLD, 20));
		bug.setPreferredSize(new Dimension(30,30));
		buget.add(bg, BorderLayout.WEST);
		buget.add(bug, BorderLayout.EAST);
		centru.add(buget, BorderLayout.SOUTH);
		bg.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				double b = customer.getWish().getTotalPrice();
				bug.setText(Double.toString(b));
			}
		});
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBackground(Color.LIGHT_GRAY);
		scroll.setPreferredSize(new Dimension(600,600));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 4;
		gbc_scrollPane.gridy = 3;
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		//lista WIshList
		JList lista_wl;
		DefaultListModel wl= new DefaultListModel();
		for (int i = 0; i < customer.getWish().size(); i++){
			wl.addElement(customer.getWish().getItem(i));;
		}
		lista_wl = new JList(wl);
		lista_wl.setFont(new Font("", Font.BOLD, 20));
		scroll.setViewportView(lista_wl);
		lista_wl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista_wl.setBorder(BorderFactory.createLineBorder(Color.red));
		lista_wl.setSelectionBackground(Color.pink);
		lista_wl.setSelectionForeground(Color.red);
		scroll.setVisible(true);
		centru.add(scroll, BorderLayout.CENTER);
		lista_wl.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (!arg0.getValueIsAdjusting()){
					item_selected = (Item) lista_wl.getSelectedValue();
				}
			}
        });
		//butane de detele si de vizualizare a tuturor produselor
		JButton delete = new JButton();
		delete.setText("Delete from Cart");
		delete.setPreferredSize(new Dimension(200, 80));
		delete.setForeground(Color.pink);
		delete.setBorder(BorderFactory.createLineBorder(Color.red));
		delete.setFont(new Font("", Font.BOLD, 20));
		jos.add(delete, BorderLayout.WEST);
		
		JButton see_prod = new JButton();
		see_prod.setText("See All Products");
		see_prod.setPreferredSize(new Dimension(200, 80));
		see_prod.setForeground(Color.pink);
		see_prod.setBorder(BorderFactory.createLineBorder(Color.red));
		see_prod.setFont(new Font("", Font.BOLD, 20));		
		see_prod.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//se deschide fereastra asemenea ferestrei dedicate managerului, insa fara opiunile de adaugare produs in magazin sau stergere din magazin
				JFrame j = new JFrame();
				j.setTitle(store.getName());
				j.setPreferredSize(new Dimension(1000,1000));
				JPanel p = new JPanel();
				
				JPanel p_dr = new JPanel();
				p_dr.setBackground(Color.pink);
				p_dr.setPreferredSize(new Dimension(60, 600));
				j.add(p_dr, BorderLayout.EAST);
				
				JPanel p_st = new JPanel();
				p_st.setPreferredSize(new Dimension(60, 600));
				p_st.setBackground(Color.pink);
				j.add(p_st, BorderLayout.WEST);
				
				JPanel p_titlu = new JPanel(new BorderLayout());
				p_titlu.setPreferredSize(new Dimension(600, 100));
				p_titlu.setBackground(Color.PINK);
				p_titlu.setForeground(Color.pink);
				j.add(p_titlu, BorderLayout.NORTH);
				
				
				JLabel titlu = new JLabel("           Items:");
				titlu.setFont(new Font("", Font.BOLD, 25));
				titlu.setBackground(Color.LIGHT_GRAY);
				titlu.setForeground(Color.BLACK);
				titlu.setPreferredSize(new Dimension(180,80));
				titlu.setOpaque(false);
				p_titlu.add(titlu, BorderLayout.WEST);
				
				JPanel p_jos = new JPanel(new BorderLayout());
				p_jos.setPreferredSize(new Dimension(100, 80));
				
				JButton exit = new JButton();
				exit.setText("I'm done");
				exit.setPreferredSize(new Dimension(150, 60));
				exit.setForeground(Color.pink);
				exit.setBorder(BorderFactory.createLineBorder(Color.red));
				exit.setFont(new Font("Comis Sans MS", Font.BOLD, 20));
				
				p_titlu.add(exit, BorderLayout.EAST);
				
				JButton add = new JButton();
				add.setText("Add to Cart");
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
		
						
				p_jos.setBackground(Color.PINK);
				p_jos.setForeground(Color.pink);
				
				
				p.setLayout(new GridLayout(3,3));
				p.setBackground(Color.pink);
				p.setPreferredSize(new Dimension(400,200));
				j.add(p, BorderLayout.SOUTH);
				JScrollPane scroll = new JScrollPane();
				scroll.setBackground(Color.LIGHT_GRAY);
				scroll.setPreferredSize(new Dimension(400,100));
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 4;
				gbc_scrollPane.gridy = 3;
			
				scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				JList lista;
				DefaultListModel x = new DefaultListModel();
				for (int i = 0; i < store.getItems().size(); i++){
					x.addElement(store.getItems().get(i));
				}
				lista = new JList(x);
				lista.setFont(new Font("", Font.BOLD, 20));
				scroll.setViewportView(lista);
				lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				lista.setBorder(BorderFactory.createLineBorder(Color.red));
				lista.setSelectionBackground(Color.pink);
				lista.setSelectionForeground(Color.red);
				scroll.setVisible(true);
				j.add(scroll, BorderLayout.CENTER);
				lista.addListSelectionListener(new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent arg0) {
							// TODO Auto-generated method stub
							if (!arg0.getValueIsAdjusting()){
								item_selected = (Item) lista.getSelectedValue();
							}
						}
			        });
				
				add.addActionListener(new ActionListener(){
					//adauga un element in wishlist
					@Override
					public void actionPerformed(ActionEvent arg0) {
						//daca elementul nu exista deja, se adauga in lista
						if (!store.getCustomer(customer.getName()).getWish().contains(item_selected)){
								store.getCustomer(customer.getName()).addWish(item_selected);
								wl.addElement(item_selected);
								lista_wl.getModel();
								//adaugare la observers si customers 
								if (store.getDepartement(item_selected.getDep()).containsObserver(customer) == false)
									store.getDepartement(item_selected.getDep()).addObserver(customer);
								//se actualizeaza field ul destinat pretului total la woshlist ului
								bug.setText(Double.toString(customer.getWish().getTotalPrice()));
						}	
						else{
							//daca exista deja elementul in lista, se trimite un avertisment
							JFrame q = new JFrame("Oh no");
							q.setPreferredSize(new Dimension(400,130));
							JPanel p1 = new JPanel();
							p1.setPreferredSize(getMaximumSize());
							JLabel l = new JLabel("You already got this product!");
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
							p1.add(ok, BorderLayout.WEST);
							ok.addActionListener(new ActionListener(){
								@Override
								public void actionPerformed(ActionEvent arg0) {
									// TODO Auto-generated method stub
									q.dispose();
								}	
							});
							q.pack();
							q.setLocationRelativeTo(null);
						}
					}
				});
				
				sort.addActionListener(new ActionListener(){
					//sortarea elementelor din lista in functie de nume/pret
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JFrame  f = new JFrame("Sort");
						f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								Comparator<Item> c = new MyComparator2();
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
						
						sn.addActionListener(new ActionListener(){
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
					//butonul si fereastra de iesire
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
								j.dispose();
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
				
				p_jos.add(add, BorderLayout.WEST);
				p_jos.add(sort, BorderLayout.EAST);
				j.add(p_jos, BorderLayout.SOUTH);

				show();
				j.pack();
				j.setLocationRelativeTo(null);
				j.setVisible(true);
			}
		});
		jos.add(see_prod, BorderLayout.NORTH);
		
		delete.addActionListener(new ActionListener(){
			//stergere element din lista
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
					// sterge itemul cu nr item_id in ShoppingCart-ul clientului custom_name
					int dep = item_selected.getDep();
					store.getCustomer(customer.getName()).getWish().remove(item_selected);
					//actualizare a pretului total a wishlist ului
					bug.setText(Double.toString(store.getCustomer(customer.getName()).getWish().getTotalPrice()));
					wl.removeElement(item_selected);
					lista_wl.getModel();
					int ok=1;
					for (int i= 0 ; i< customer.getWish().size(); i++){
						if (dep == customer.getWish().getItem(i).getDep())
							ok = 0;
					}
					if (ok == 1)
						store.getDepartement(dep).getObservers().remove(customer);	
				}
		});
		
		this.pack();
		this.setLocationRelativeTo(null);
	}
}
