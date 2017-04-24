package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tema_magazin.Item;
import tema_magazin.Store;

public class Add  extends JFrame {
		private JPanel pan = new JPanel();
		private JTextField warn;
		private JTextField id;
		private JTextField depid;
		private JTextField name;
		private JTextField prc;
		private JButton add;
		private JButton back;
		private Store s;
		Vector<Item> v = new Vector<>();
		public Add (Store s){
			this.s = s;
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setMinimumSize(new Dimension(300, 150));
			setLayout( new GridLayout(1,0, 10, 10));
			setLocationRelativeTo(null);
			pan.setLayout(new GridLayout(6,2, 10, 10));
			pan.add( new JLabel("Nume :"));
			name = new JTextField(15);
			pan.add(name);
			pan.add( new JLabel("ID :"));
			id = new JTextField(15);
			pan.add(id);
			pan.add( new JLabel("ID Dep. :"));
			depid = new JTextField(15);
			pan.add(depid);
			pan.add( new JLabel("Pret :"));
			prc = new JTextField(15);
			pan.add(prc);
			add = new JButton("Add item");
			add.addActionListener(new listenAdd());
			pan.add(add); 
			back = new JButton("Back");
			back.addActionListener(new listenBack());
			pan.add(back); 
			pan.add( new JLabel("Notification :"));
			warn = new JTextField(15);
			warn.setEditable(false);
			pan.add(warn);
			add(pan);
			show();
			pack();
			
		}
		
		class listenAdd implements ActionListener {
	        public void actionPerformed(ActionEvent ev) {
	        	if (prc.getText().isEmpty() || name.getText().isEmpty() || id.getText().isEmpty() || depid.getText().isEmpty())
	        	{
	        		JOptionPane.showMessageDialog(null, "Fill all fields!");
	        		return;
	        	}
	        	int depId = Integer.parseInt(depid.getText());
	        	int Id = Integer.parseInt(id.getText());
	        	Item it = new Item(name.getText(), Id, Double.parseDouble(prc.getText()));
	        	if (s.getDepartment(depId).hasItem(it))
	        		JOptionPane.showMessageDialog(null, "ID already in use!");
	        	else
	        	{
		        	s.getDepartment(depId).addItem(it);
		        	prc.setText("");
		        	name.setText("");
		        	id.setText("");
		        	depid.setText("");
		        	warn.setText("Item added");
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
