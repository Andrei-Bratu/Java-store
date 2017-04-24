package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import customer.Customer;
import graficaSc.EditSc;
import graficaSc.afisSc;
import shopping.ShoppingCart;
import tema_magazin.Item;
import tema_magazin.Store;

public class ManageSc extends JFrame {
	private static DecimalFormat df2 = new DecimalFormat(".00");
	private JList<Item> list;
	private JPanel pan = new JPanel();
	private JButton afis;
	private JButton mod;
	private JButton snd;
	private JButton back;
	private JTextField tot;
	private JTextField bu;
	private Store s;
	private Customer c;
	public ManageSc (Store s, Customer c){
		this.s = s;
		this.c = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400, 150));
		setLocationRelativeTo(null);
		setLayout( new GridLayout(4,0, 10, 10));
		pan.setLayout(new GridLayout(2,2, 10, 10));
		pan.add(new JLabel("Total :"));
		tot = new JTextField();
		tot.setEnabled(false);
		tot.setText(df2.format(c.getCart().getTotalPrice()) + "");
		pan.add(tot);
		pan.add(new JLabel("Budget left :"));
		bu = new JTextField();
		bu.setEnabled(false);
		bu.setText(df2.format(c.getCart().getBuget()) + "");
		pan.add(bu);
		add(pan);
		add(new JLabel(""));
		afis = new JButton("Print");
		afis.addActionListener(new listen());
		add(afis);
		mod = new JButton("Add / Delete");
		mod.addActionListener(new listenMod());
		add(mod);
		snd = new JButton("Send order");
		snd.addActionListener(new listenSnd());
		if (c.getCart().getTotalPrice() != 0)
			snd.setEnabled(true);
		else
			snd.setEnabled(false);
		add(snd);
		back = new JButton("Back");
		back.addActionListener(new listenBack());
		add(back);
		show();
		pack();
	}
	class listen implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	afisSc a = new afisSc(s, c);
        }
    }
	class listenMod implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	EditSc a = new EditSc(s, c);
        }
    }
	class listenSnd implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	c.setCart(new ShoppingCart(c.getCart().getBuget()));
    		tot.setText(df2.format(c.getCart().getTotalPrice()) + "");
    		snd.setEnabled(false);
        }
    }
	class listenBack implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	Menu a = new Menu(s);
        }
    }
}