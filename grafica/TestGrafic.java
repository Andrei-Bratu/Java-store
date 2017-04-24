package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import customer.Customer;
import department.BookDepartment;
import department.Department;
import department.MusicDepartment;
import department.SoftwareDepartment;
import department.VideoDepartment;
import tema_magazin.Item;
import tema_magazin.Store;
public class TestGrafic extends JFrame {
	private JButton load;
	private JButton ext;
	public TestGrafic ()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(300, 150));
		setLayout( new GridLayout(3,2, 10, 10));
		setLocationRelativeTo(null);
		add( new JLabel("Online Store", SwingConstants.CENTER));
		load = new JButton("Load");
		load.addActionListener(new listen());
		add(load);
		ext = new JButton("Exit");
		ext.addActionListener(new listenExit());
		add(ext);
		show();
		pack();
	}



	public static void main(String[] args) {
		TestGrafic t = new TestGrafic();

	}
	class listenExit implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        	dispose();
        }
    }
	class listen implements ActionListener {
        public void actionPerformed(ActionEvent ev) {

    		FileReader in = null;
    		BufferedReader l = null;
    		StringTokenizer t = null;
    		Item item = null;
    		Customer c = null;
    		Store s1 = null;
    		Department d = null; 
    		try
    		{
    			in = new FileReader("store.txt");
    			l = new BufferedReader(in);
    			String s = l.readLine();
    			s1 =s1.getInstance();
    			s1.setName(s);
    			s = l.readLine();
    			while (s  != null)
    			{
    				t = new StringTokenizer(s, ";");
    				
    				String name = t.nextToken();
    				if (name.equals("BookDepartment"))
    				{
    					d = new BookDepartment(Integer.parseInt(t.nextToken()), name);
    					s1.addDepartment(d);
    				}
    				else if (name.equals("MusicDepartment"))
    				{
    					d = new MusicDepartment(Integer.parseInt(t.nextToken()), name);
    					s1.addDepartment(d);
    				}
    				else if (name.equals("VideoDepartment"))
    				{
    					d = new VideoDepartment(Integer.parseInt(t.nextToken()), name);
    					s1.addDepartment(d);					
    				}
    				else if (name.equals("SoftwareDepartment"))
    				{
    					d = new SoftwareDepartment(Integer.parseInt(t.nextToken()), name);
    					s1.addDepartment(d);
    				}
    				int n = Integer.parseInt(l.readLine());
    				for (int i = 0; i < n; i++)
    				{
    					t = new StringTokenizer(l.readLine(),";");
    					item = new Item(t.nextToken(), Integer.parseInt(t.nextToken()), Double.parseDouble(t.nextToken()));
    					d.addItem(item);
    				}
    				s = l.readLine();
    			}
    			
    		}
    		catch (FileNotFoundException e)
    		{
    			e.printStackTrace();
    		}
    		catch (IOException e)
    		{
    			e.printStackTrace();
    		}
    		finally
    		{
    			try
    			{
    				if (in != null)
    					in.close();
    				if (l != null)
    					l.close();
    			}
    			catch (IOException e)
    			{
    				e.printStackTrace();
    			}
    		}

    		try
    		{
    			in = new FileReader("customers.txt");
    			l = new BufferedReader(in);
    			int n, i;

    			n = Integer.parseInt(l.readLine());
    			for (i = 0; i < n; i++)
    			{
    				t = new StringTokenizer(l.readLine(),";");
    				c = new Customer(t.nextToken(), Double.parseDouble(t.nextToken()), t.nextToken());
    				s1.enter(c);
    			
    			}
    			
    		}
    		catch (FileNotFoundException e)
    		{
    			e.printStackTrace();
    		}
    		catch (IOException e)
    		{
    			e.printStackTrace();
    		}
    		finally
    		{
    			try
    			{
    				if (in != null)
    					in.close();
    				if (l != null)
    					l.close();
    			}
    			catch (IOException e)
    			{
    				e.printStackTrace();
    			}
    		}
    		dispose();
    		Menu a = new Menu(s1);
        }
    }

}
