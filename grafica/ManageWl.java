package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import customer.Customer;
import graficaWl.EditWl;
import graficaWl.afisWl;
import tema_magazin.Store;

public class ManageWl extends JFrame {
	private JButton afis;
	private JButton mod;
	private JButton back;
	private Store s;
	private Customer c;
	public ManageWl (Store s, Customer c){
		this.s = s;
		this.c = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400, 150));
		setLocationRelativeTo(null);
		setLayout( new GridLayout(3,0, 10, 10));
		afis = new JButton("Print");
		afis.addActionListener(new listen());
		add(afis);
		mod = new JButton("Add / Delete");
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
        	afisWl a = new afisWl(s, c);
        }
    }
	class listenMod implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	EditWl a = new EditWl(s, c);
        }
    }
	class listenBack implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	Menu a = new Menu(s);
        }
    }
}