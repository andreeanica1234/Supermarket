import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Interfata_grafica extends JFrame{
	
	public JPanel p;
	static Store store;
	
	public static void main (String[] args) throws IOException{
		//deschide o fereastra
		new Interfata_grafica();
	}

	public Interfata_grafica() throws IOException{
		
		Store store = Store.getInstance();
		//citirea din fisier
		FileReader file = new FileReader("store.txt");
		BufferedReader br = new BufferedReader(file);
		String line;
		line = br.readLine();
		store.setName(line);
		
		while ( (line = br.readLine()) != null ){ 		//citeste pana se termina fisierul
			StringTokenizer st = new StringTokenizer(line, ";");
			if ( st.countTokens() == 2 ){ //=>nume departament
				String name_dep = st.nextToken();
				int id_dep =Integer.parseInt(st.nextToken());
				
				//creez departamentul
				if ( name_dep.compareTo("BookDepartment") == 0 ){
					BookDepartment department = new BookDepartment(name_dep , id_dep);
					store.addDepartment(department);					
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
					store.addDepartment(department);					
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
					store.addDepartment(department);					
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
					store.addDepartment(department);					
					line = br.readLine();
					int n = Integer.parseInt(line); // numarul de item-uri din departament
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
			store.enter(customer);
		}
	
		this.setTitle(store.getName());
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(600, 600));
		p = new JPanel();
		p.setBackground(Color.pink);
		p.setPreferredSize(getMaximumSize());
		
		JButton buton = new JButton();
		buton.setPreferredSize(new Dimension(500, 300));
		buton.setFont(new Font("Times New Roman", Font.BOLD, 40));
		buton.setText("Welcome to "+ store.getName());
		buton.setForeground(Color.pink);
		buton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Creez butoanele pentru client/manager si le adaug unui nou panel
				buton.setVisible(false);
				buton.setEnabled(false);
				JButton client = new JButton();
				client.setText("I'm a client");
				client.setForeground(Color.pink);
				client.setFont(new Font("Helvetica", Font.BOLD, 40));
				client.setPreferredSize(new Dimension(450,150));
				client.addActionListener(new ActionListener(){
					//cand se apasa butonul se deschide o fereastra noua, destinata clientilor
					@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						SecondFrameClienti x = new SecondFrameClienti(store);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				});
				
				JButton boss = new JButton();
				boss.setText("I'm a manager");
				boss.setFont(new Font("Comis Sans MS", Font.BOLD, 40));
				boss.setForeground(Color.PINK);
				boss.setPreferredSize(new Dimension(450,150));
				boss.addActionListener(new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						//cand se apasa butonul se deschide o fereastra noua, destinata managerului
						// TODO Auto-generated method stub
						try {
							SecondFrameManager x = new SecondFrameManager(store);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}			
					}
				});
				p.add(client, BorderLayout.PAGE_START);
				p.add(boss, BorderLayout.PAGE_END);
		}	
	});
		
		p.add(buton, BorderLayout.CENTER);
		this.add(p, BorderLayout.CENTER);
		JPanel aux_p = new JPanel();
		aux_p.setBackground(Color.pink);
		aux_p.setPreferredSize(new Dimension(500,100));
		this.add(aux_p, BorderLayout.NORTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		show();
		pack();
		//fereastra va aparea in centrul ecranului
		this.setLocationRelativeTo(null);
	}		
}