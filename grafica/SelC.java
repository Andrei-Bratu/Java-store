package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import customer.Customer;
import tema_magazin.Store;

public class SelC extends JFrame{
	private JList<Customer> list;
	private JPanel pan = new JPanel();
	private JButton sel;
	private JButton back;
	private Store s;
	private String type;
	public SelC (Store s, String t){
		this.s = s;
		type = t;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500, 200));
		setLocationRelativeTo(null);
		setLayout( new GridLayout(3,0, 10, 10));
		list = new JList<>(s.getCustomers());
		list.setSelectionMode ( ListSelectionModel.SINGLE_INTERVAL_SELECTION) ;
		list.setVisibleRowCount (1) ;
		list.addListSelectionListener(new listenl());
		JScrollPane  listScroller = new JScrollPane ( list ) ;
		listScroller.setPreferredSize (new Dimension (200, 60));
		add(new JLabel("Select Customer:", SwingConstants.CENTER));
		add(listScroller);
		pan.setPreferredSize(new Dimension(100, 100));
		sel = new JButton("Select");
		sel.addActionListener(new listen());
		sel.setEnabled(false);
		pan.add(sel);
		back = new JButton("Back");
		back.addActionListener(new listenBack());
		pan.add(back);
		add(pan);
		show();
		pack();
		
	}

	class listenl implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			if (list.isSelectionEmpty())
				return;
			sel.setEnabled(true);
		}
		
	}
	class listen implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	if (list.isSelectionEmpty())
        	{
        		return;
        	}
        	dispose();
        	if (type.equals("s"))
        	{
        		ManageSc a = new ManageSc(s, list.getSelectedValue());
        	}
        	else
        	{
        		ManageWl a = new ManageWl(s, list.getSelectedValue());
        	}
        }
        
    }
	class listenBack implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	Menu a = new Menu(s);
        }
    }
}
