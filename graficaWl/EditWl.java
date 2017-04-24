package graficaWl;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import customer.Customer;
import grafica.ManageWl;
import tema_magazin.Item;
import tema_magazin.Store;

public class EditWl extends JFrame {
	private JList<Item> list;
	private JPanel pan = new JPanel();
	private JTextField warn;
	private JButton add;
	private JButton del;
	private JButton back;
	private Store s;
	private Customer c;
	Vector<Item> v = new Vector<>();
	public EditWl (Store s, Customer c){
		this.s = s;
		this.c = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500, 150));
		setLocationRelativeTo(null);
		setLayout( new GridLayout(2,0, 10, 10));
		for (int i = 1; i < s.getDepartments().size(); i++)
			v.addAll(s.getDepartment(i).getItems());
		list = new JList<>(v);
		list.setSelectionMode ( ListSelectionModel.SINGLE_INTERVAL_SELECTION) ;
		list.setVisibleRowCount (1) ;
		list.addListSelectionListener(new listenl());
		JScrollPane  listScroller = new JScrollPane ( list ) ;
		listScroller.setPreferredSize (new Dimension (200, 60));
		add(listScroller);
		pan.setLayout(new GridLayout(7,2, 10, 10));
		add = new JButton("Add item");
		add.addActionListener(new listenAdd());
		pan.add(add); 
		del = new JButton("Delete item");
		del.addActionListener(new listenDel());
		pan.add(del); 
		back = new JButton("Back");
		back.addActionListener(new listenBack());
		pan.add(back); 
		pan.add(new JLabel(""));
		pan.add( new JLabel("Notification :"));
		warn = new JTextField(15);
		warn.setEditable(false);
		pan.add(warn);
		add(pan);
		show();
		pack();
		
	}
	class listenl implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			if (list.isSelectionEmpty())
			{
				add.setEnabled(false);
				del.setEnabled(false);
				return;
			}
			add.setEnabled(true);
			del.setEnabled(true);
		}
		
	}
	
	class listenAdd implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	Item it = list.getSelectedValue();
        	if (c.getList().contains(it))
        	{
	        	warn.setText("Item already in WishList!");
        	}
        	else
        	{
        		s.getItemDept(it.getId()).addObserver(c);
        		c.getList().add(it);
        		warn.setText("Item added!");
        	}
        }
    }class listenDel implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	Item it = list.getSelectedValue();
        	if (c.getList().contains(it))
        	{
        		c.getList().remove(it);
        		warn.setText("Item deleted!");
        	}
        	else
        	{
	        	warn.setText("Item is not in WishList!");
        	}
        }
    }
	class listenBack implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	ManageWl a = new ManageWl(s, c);
        }
    }
}
