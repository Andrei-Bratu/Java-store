package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import tema_magazin.Item;
import tema_magazin.Store;

public class Afis  extends JFrame {
	private JList<Item> list;
	private JPanel pan = new JPanel();
	private JButton alf;
	private JButton asc;
	private JButton desc;
	private JButton back;
	private Store s;
	Vector<Item> v = new Vector<>();
	public Afis (Store s){
		this.s = s;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1000, 500));
		setLocationRelativeTo(null);
		setLayout( new GridLayout(2,0, 10, 10));
		for (int i = 1; i < s.getDepartments().size(); i++)
			v.addAll(s.getDepartment(i).getItems());
		list = new JList<>(v);
		list.setSelectionMode ( ListSelectionModel.SINGLE_INTERVAL_SELECTION) ;
		list.setVisibleRowCount (1) ;
		JScrollPane  listScroller = new JScrollPane ( list ) ;
		listScroller.setPreferredSize (new Dimension (200, 60));
		add(listScroller);
		pan.setPreferredSize(new Dimension(100, 100));
		alf = new JButton("Alphabetic");
		alf.addActionListener(new listen());
		pan.add(alf);
		asc = new JButton("Ascending");
		asc.addActionListener(new listenAsc());
		pan.add(asc);
		desc = new JButton("Descending");
		desc.addActionListener(new listenDesc());
		pan.add(desc);
		back = new JButton("Back");
		back.addActionListener(new listenBack());
		pan.add(back);
		add(pan);
		show();
		pack();
		
	}
	class listen implements ActionListener {
		mycmp c = new mycmp();
        public void actionPerformed(ActionEvent ev) {
        	repaint();
        	Collections.sort(v, c);
        	list.setListData(v);
        }
    }
	class listenAsc implements ActionListener {
		mycmpinc c = new mycmpinc();
        public void actionPerformed(ActionEvent ev) {

        	repaint();
        	Collections.sort(v, c);
        	list.setListData(v);
        }
    }
	class listenDesc implements ActionListener {
		mycmpdec c = new mycmpdec();
        public void actionPerformed(ActionEvent ev) {
        	repaint();
        	Collections.sort(v, c);
        	list.setListData(v);
        }
    }
	class listenBack implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        	Admin a = new Admin(s);
        }
    }
	public class mycmp implements Comparator<Item>{
	
		@Override
		public int compare(Item o1, Item o2) {
				return o1.getName().compareTo(o2.getName());
		}
		
	}
	public class mycmpinc implements Comparator<Item>{
	
		@Override
		public int compare(Item o1, Item o2) {
			if (o1.getPrice() < o2.getPrice())
				return -1;
			else
				return 1;
		}
		
	}
	public class mycmpdec implements Comparator<Item>{
	
		@Override
		public int compare(Item o1, Item o2) {
			if (o1.getPrice() < o2.getPrice())
				return 1;
			else
				return -1;
		}
		
	}
}
