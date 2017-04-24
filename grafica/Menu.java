package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import tema_magazin.Store;

public class Menu extends JFrame {
	private JButton item;
	private JButton shc;
	private JButton wsl;
	private Store s;
	private JButton back;
	public Menu (Store s){
		this.s = s;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400, 250));
		setLocationRelativeTo(null);
		setLayout( new GridLayout(4,0, 10, 10));
		item = new JButton("Manage items");
		item.addActionListener(new listen());
		add(item); 
		shc = new JButton("Manage ShoppingCart");
		shc.addActionListener(new listenSc());
		add(shc); 
		wsl = new JButton("Manage WishList");
		wsl.addActionListener(new listenWl());
		add(wsl); 
		back = new JButton("Back");
		back.addActionListener(new listenBack());
		add(back);
		show();
		pack();
		
	}
	class listen implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	Admin a = new Admin(s);
        }
    }
	class listenSc implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	SelC a = new SelC(s, "s");
        }
    }
	class listenWl implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	SelC a = new SelC(s, "w");
        }
    }

	class listenBack implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	s = null;
        	TestGrafic t = new TestGrafic();
        }
    }
}
