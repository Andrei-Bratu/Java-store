package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tema_magazin.Item;
import tema_magazin.Store;

public class Modify extends JFrame {
	private JList<Item> list;
	private JPanel pan = new JPanel();
	private JTextField warn;
	private JTextField prc;
	private JButton mod;
	private JButton del;
	private JButton back;
	private Store s;
	Vector<Item> v = new Vector<>();
	public Modify (Store s){
		this.s = s;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(450, 350));
		setLocationRelativeTo(null);
		setLayout( new GridLayout(1,0, 10, 10));
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
		pan.add( new JLabel("Pret nou:"));
		prc = new JTextField(15);
		prc.setEnabled(false);
		pan.add(prc);
		mod = new JButton("Modify item");
		mod.addActionListener(new listenMod());
		mod.setEnabled(false);
		pan.add(mod); 
		del = new JButton("Delete item");
		del.addActionListener(new listenDel());
		del.setEnabled(false);
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
				mod.setEnabled(false);
				del.setEnabled(false);
				prc.setEnabled(false);
				return;
			}
			mod.setEnabled(true);
			del.setEnabled(true);
			prc.setEnabled(true);
		}
		
	}
	class listenMod implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	if (prc.getText().isEmpty() )
        		JOptionPane.showMessageDialog(null, "Set the price!");
        	else
        	{
	        	double pr = Double.parseDouble(prc.getText());
	        	Item it = list.getSelectedValue();
	        	if (s.getItemDept(it.getId()).hasItem(it))
	        	{
		        		s.getItemDept(it.getId()).getItem(it.getId()).setPrice(pr);
		        		warn.setText("Price modified!");
			        	prc.setText("");
			        	v = new Vector<>();
			    		for (int i = 1; i < s.getDepartments().size(); i++)
			    			v.addAll(s.getDepartment(i).getItems());
			        	list.setListData(v);
	        	}
        		else
        			warn.setText("Item does not exist");
        	}
        }
    }class listenDel implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	Item it = list.getSelectedValue();
        	if (s.getItemDept(it.getId()).hasItem(it))
        	{
        		s.getItemDept(it.getId()).prodDel(it);
        		s.getItemDept(it.getId()).removeItem(it);
        		warn.setText("Item deleted!");
	        	prc.setText("");
	        	v = new Vector<>();
	    		for (int i = 1; i < s.getDepartments().size(); i++)
	    			v.addAll(s.getDepartment(i).getItems());
	        	list.setListData(v);
        	}
        	else
        	{
	        	warn.setText("Item does not exist!");
        	}
        }
    }
	class listenBack implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	Admin a = new Admin(s);
        }
    }
}
