package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import tema_magazin.Store;


public class Admin  extends JFrame {
	private JButton afisare;
	private JButton add;
	private JButton mod;
	private JButton back;
	private Store s;
	public Admin (Store s){
		this.s = s;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400, 200));
		setLocationRelativeTo(null);
		setLayout( new GridLayout(4,0, 10, 10));
		afisare = new JButton("Print items");
		afisare.addActionListener(new listen());
		add(afisare); 
		add = new JButton("Add items");
		add.addActionListener(new listenAdd());
		add(add); 
		mod = new JButton("Modify / Delete items");
		mod.addActionListener(new listenMod());
		add(mod);  
		back = new JButton("Back");
		back.addActionListener(new listenBack());
		add(back); 
		show();
		pack();
		
	}
	class listen implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	Afis a = new Afis(s);
        }
    }
	class listenAdd implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	Add a = new Add(s);
        }
    }
	class listenMod implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	Modify a = new Modify(s);
        }
    }
	class listenBack implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	Menu a = new Menu(s);
        }
    }

}
