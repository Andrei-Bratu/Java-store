package graficaWl;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import customer.Customer;
import grafica.ManageWl;
import tema_magazin.Item;
import tema_magazin.Store;

public class afisWl  extends JFrame {
	private JList<Item> list;
	private JPanel pan = new JPanel();
	private JButton back;
	private Store s;
	private Customer c;
	public afisWl (Store s, Customer c){
		this.s = s;
		this.c = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 200));
		setLayout( new GridLayout(2,0, 10, 10));
		setLocationRelativeTo(null);
		list = new JList<>(c.getList().getItems());
		list.setSelectionMode ( ListSelectionModel.SINGLE_INTERVAL_SELECTION) ;
		list.setVisibleRowCount (1) ;
		JScrollPane  listScroller = new JScrollPane ( list ) ;
		listScroller.setPreferredSize (new Dimension (200, 60));
		add(listScroller);
		pan.setPreferredSize(new Dimension(100, 100));
		back = new JButton("Back");
		back.addActionListener(new listenBack());
		pan.add(back);
		add(pan);
		show();
		pack();
		
	}
	
	class listenBack implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	ManageWl a = new ManageWl(s, c);
        }
    }
}
