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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SecondFrameClienti extends JFrame {
	JPanel p;
	Customer customer_selected;
	Store store;
	
	public void CloseFrame(){
		super.dispose();
	}
	
	public SecondFrameClienti(Store store) throws IOException{
		//fereastra in care se autentifica clientul
		this.setVisible(true);
		this.setTitle(store.getName());
		this.setPreferredSize(new Dimension(600,600));
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
		p_titlu.setPreferredSize(new Dimension(600, 60));
		p_titlu.setBackground(Color.PINK);
		p_titlu.setForeground(Color.pink);
		this.add(p_titlu, BorderLayout.NORTH);
		
		
		JLabel titlu = new JLabel("       Select customer:");
		titlu.setFont(new Font("Comis Sans MS", Font.PLAIN, 20));
		titlu.setBackground(Color.LIGHT_GRAY);
		titlu.setForeground(Color.BLACK);
		titlu.setPreferredSize(new Dimension(200,100));
		titlu.setOpaque(false);
		p_titlu.add(titlu, BorderLayout.WEST);
		p.setLayout(new GridLayout(3,3));
		p.setBackground(Color.pink);
		p.setPreferredSize(new Dimension(600,200));
		this.add(p, BorderLayout.SOUTH);
		
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBackground(Color.LIGHT_GRAY);
		scroll.setPreferredSize(new Dimension(400,100));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 4;
		gbc_scrollPane.gridy = 3;
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		//se creeaza lista de clienti
		JList lista;
		DefaultListModel x = new DefaultListModel();
		Iterator it = store.getCustomers().iterator();
		while (it.hasNext()){
			Customer cu = (Customer) it.next();
			x.addElement(cu);
		}
		lista = new JList(x);
		lista.setFont(new Font("Comis Sans MS", Font.BOLD, 20));
		scroll.setViewportView(lista);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scroll.setVisible(true);
		this.add(scroll, BorderLayout.CENTER);
		lista.addListSelectionListener(new ListSelectionListener() {
				@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
			if (!arg0.getValueIsAdjusting()){
					customer_selected = (Customer) lista.getSelectedValue();
				}
			}
	    });
		
		JButton select = new JButton();
		select.setText("Select");
		select.setPreferredSize(new Dimension(200, 80));
		select.setForeground(Color.pink);
		select.setBorder(BorderFactory.createLineBorder(Color.red));
		select.setFont(new Font("Comis Sans MS", Font.BOLD, 30));
		JPanel p_jos = new JPanel(new BorderLayout());
		p_jos.setPreferredSize(new Dimension(100, 100));
		p_jos.setBackground(Color.PINK);
		p_jos.setForeground(Color.pink);
		p_jos.add(select, BorderLayout.SOUTH);
		this.add(p_jos, BorderLayout.SOUTH);
		select.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//se intra intr-o noua fereatra destinata clientilor
				Customer customer = (Customer) lista.getSelectedValue();
				ThirdFrameClient thirdFrameClient = new ThirdFrameClient(store, customer_selected);
			}
		});
		
		//butonul de iesire
		JButton exit = new JButton();
		exit.setText("I'm done");
		exit.setPreferredSize(new Dimension(100, 50));
		exit.setForeground(Color.pink);
		exit.setBorder(BorderFactory.createLineBorder(Color.red));
		exit.setFont(new Font("Comis Sans MS", Font.BOLD, 20));
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//se deschide o noua fereastra in care te intreaba daca esti sigur ca vrei sa parasesti fereastra veche
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
			
		JPanel p_exit = new JPanel(new BorderLayout());
		p_exit.add(exit, BorderLayout.EAST);
		p_titlu.add(p_exit, BorderLayout.EAST);
		show();
		pack();
		this.setLocationRelativeTo(null);
	}
}
