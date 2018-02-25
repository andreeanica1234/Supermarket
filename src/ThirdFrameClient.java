import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ThirdFrameClient extends JFrame {
	//fereastra disponibila fiecarui client, din care alege daca isi vizualizeaza ShoppingCart ul sau WishList ul
	JPanel p;

	public void CloseFrame(){
		super.dispose();
	}
	
	public ThirdFrameClient(Store store, Customer customer){
		this.setTitle(customer.getName());
		this.setVisible(true);
		this.setPreferredSize(new Dimension(600,600));
		p = new JPanel();
		
		JPanel p_titlu = new JPanel(new BorderLayout());
		p_titlu.setPreferredSize(new Dimension(200, 100));
		p_titlu.setBackground(Color.PINK);
		p_titlu.setForeground(Color.pink);
		this.add(p_titlu, BorderLayout.NORTH);
		
		
		JLabel titlu = new JLabel(" Hello "+customer.getName() +"!");
		titlu.setFont(new Font("", Font.PLAIN, 30));
		titlu.setBackground(Color.LIGHT_GRAY);
		titlu.setForeground(Color.BLACK);
		titlu.setPreferredSize(new Dimension(200,100));
		titlu.setOpaque(false);
		p_titlu.add(titlu, BorderLayout.CENTER);
		p.setLayout(new GridLayout(3,3));
		p.setBackground(Color.pink);
		p.setPreferredSize(new Dimension(600,200));
		this.add(p, BorderLayout.CENTER);
		
		JButton exit = new JButton();
		exit.setText("I'm done");
		exit.setPreferredSize(new Dimension(10, 30));
		exit.setForeground(Color.pink);
		exit.setBorder(BorderFactory.createLineBorder(Color.red));
		exit.setFont(new Font("Comis Sans MS", Font.BOLD, 20));
		
		exit.addActionListener(new ActionListener(){
			//iesirea din fereastra
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
		
		JPanel p_exit = new JPanel(new BorderLayout());
		p_exit.setPreferredSize(new Dimension(40,40));
		this.add(p_exit, BorderLayout.SOUTH);
		p_exit.add(exit, BorderLayout.CENTER);
		
		JButton ShoppingCart = new JButton();
		ShoppingCart.setText("ShoppingCart");
		ShoppingCart.setForeground(Color.pink);
		ShoppingCart.setFont(new Font("", Font.BOLD, 40));
		ShoppingCart.setPreferredSize(new Dimension(450,150));
		ShoppingCart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//se deschide fereastra de ShoppingCart
				ShoppingCartFrame shoppCart = new ShoppingCartFrame(store, customer);
			}
		});
		JButton WishList = new JButton();
		WishList.setText("WishList");
		WishList.setFont(new Font("", Font.BOLD, 40));
		WishList.setForeground(Color.PINK);
		WishList.setPreferredSize(new Dimension(450,150));
		WishList.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//se deschide fereastra de WishList
				WishListFrame wishList = new WishListFrame(store, customer);
			}
		});
		p.add(ShoppingCart, BorderLayout.NORTH);
		p.add(WishList, BorderLayout.CENTER);

		this.pack();		
		this.setLocationRelativeTo(null);	
	}
}
