package tema_magazin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

import customer.Customer;
import department.*;
import tema_magazin.Notification.NotificationType;

public class Test {
	private static DecimalFormat df2 = new DecimalFormat(".00");

	public static void main(String[] args) {
		FileReader in = null;
		BufferedReader l = null;
		StringTokenizer t = null;
		Item item = null;
		Customer c = null;
		Store s1 = null;
		Department d = null; 
		BufferedWriter out = null;
		
		try
		{
			in = new FileReader("store.txt");
			l = new BufferedReader(in);
			String s = l.readLine();
			s1 =Store.getInstance();
			s1.setName(s);
			s = l.readLine();
			/* se citeste numele magazinului si se creeaza un obiect pentru magazin */
			while (s  != null)
			{
				/* se citesc linie cu linie departamentele si obiectele lor */
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
			/* se citesc clientii magazinului */
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
		try
		{
			in = new FileReader("events.txt");
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("result.txt"), "utf-8"));
			l = new BufferedReader(in);
			String name = null;		// variabila pentru numele instructiunii
			int id, depid;			// id-ul unui produs, id-ul unui departament
			double prc;				// pretul unui produs
			String cust;			// numele clientului
			String sw;				// variabila pentru citire / compareare inre shoppingcart si wishlist
			Notification not = null;	// variabila pentru notificari
			int n, i, s = 0;
			/* se citesc evenimentele lini cu linie si se executa instructiunile aferente */
			n = Integer.parseInt(l.readLine());
			for (i = 0; i < n; i++)
			{
				t = new StringTokenizer(l.readLine(),";");
				name = t.nextToken();
				switch (name) {
					case "addItem":
						id = Integer.parseInt(t.nextToken());
						sw = t.nextToken();
						cust = t.nextToken();
						item = s1.getItem(id);
						if(sw.equals("ShoppingCart"))
						{
							s1.getCustomer(cust).getCart().add(item);
							if (!s1.getItemDept(id).clientic.contains(s1.getCustomer(cust)))
								s1.getItemDept(id).enter(s1.getCustomer(cust));
						}
						else
						{
							s1.getCustomer(cust).getList().add(item);
							if (!s1.getItemDept(id).obs.contains(s1.getCustomer(cust)))
								s1.getItemDept(id).addObserver(s1.getCustomer(cust));
						}
						
						break;
					case "delItem":
						id = Integer.parseInt(t.nextToken());
						sw = t.nextToken();
						cust = t.nextToken();
						item = s1.getItem(id);
						if(sw.equals("ShoppingCart"))
						{
							s1.getCustomer(cust).getCart().remove(item);
							
						}
						else
						{
							s1.getCustomer(cust).getList().remove(item);
							if (s1.getItemDept(id).verify(s1.getCustomer(cust)) == false)
								s1.getItemDept(id).removeObserver(s1.getCustomer(cust));
						}
						
						break;
					case "addProduct":
						depid = Integer.parseInt(t.nextToken());
						id = Integer.parseInt(t.nextToken());
						prc = Double.parseDouble(t.nextToken());
						cust = t.nextToken();
						item = new Item(cust, id, prc);
						s1.getDepartment(depid).addItem(item);
						not = new Notification(NotificationType.ADD,depid, id);
						s1.getDepartment(depid).notifyAllObservers(not);
						
						break;
					case "modifyProduct":
						depid = Integer.parseInt(t.nextToken());
						id = Integer.parseInt(t.nextToken());
						prc = Double.parseDouble(t.nextToken());
						item = s1.getDepartment(depid).getItem(id);
						Item ite = new Item(item.getName(), item.getId(), prc);
						not = new Notification(NotificationType.MODIFY,depid, id);
						s1.getDepartment(depid).notifyAllObservers(not);
						s1.getDepartment(depid).modifIt(item, ite);
						s1.getDepartment(depid).getItem(id).setPrice(prc);
						
						break;
					case "delProduct":
						id = Integer.parseInt(t.nextToken());
						item = s1.getItem(id);
						depid = s1.getItemDept(id).getId();
						s1.getItemDept(id).removeItem(item);
						not = new Notification(NotificationType.REMOVE,depid, id);
						s1.getDepartment(depid).notifyAllObservers(not);
						for (int j = 0; j < s1.getCustomers().size(); j++)
							if (s1.getDepartment(depid).verify(s1.getCustomers().get(j)) == false)
								s1.getDepartment(depid).removeObserver(s1.getCustomers().get(j));
						
						break;
					case "getItem":
						cust = t.nextToken();
						Item it = s1.getCustomer(cust).getList().executeStrategy();
						s1.getCustomer(cust).getCart().add(it);
						if (s1.getItemDept(it.getId()).verify(s1.getCustomer(cust)) == false)
							s1.getItemDept(it.getId()).removeObserver(s1.getCustomer(cust));
							
						if (s == 1)
							out.newLine();
						out.write(it.toString());
						s = 1;
						
						break;
					case "getItems":
						sw = t.nextToken();
						cust = t.nextToken();
						if(sw.equals("ShoppingCart"))
						{
							if (s == 1)
								out.newLine();
							out.write(s1.getCustomer(cust).getCart().getItems().toString());
							s = 1;
						}
						else
						{
							if (s == 1)
								out.newLine();
							out.write(s1.getCustomer(cust).getList().getItems().toString());
							s = 1;
						}
						
						
						break;
					case "getTotal":
						sw = t.nextToken();
						cust = t.nextToken();
						if(sw.equals("ShoppingCart"))
						{
							if (s == 1)
								out.newLine();
							out.write(df2.format(s1.getCustomer(cust).getCart().getTotalPrice()));
							s = 1;
						}
						else
						{
							if (s == 1)
								out.newLine();
							out.write(df2.format(s1.getCustomer(cust).getList().getTotalPrice()));
							s = 1;
						}
						
						break;
					case "accept":
						id = Integer.parseInt(t.nextToken());
						cust = t.nextToken();
						s1.getDepartment(id).accept(s1.getCustomer(cust).getCart());
						
						
						break;
					case "getObservers":
						id = Integer.parseInt(t.nextToken());
						if (s == 1)
							out.newLine();
						out.write(s1.getDepartment(id).getObservers().toString());
						s = 1;
						
						break;
					case "getNotifications":
						cust = t.nextToken();
						if (s == 1)
							out.newLine();
						out.write(s1.getCustomer(cust).getNotif().toString());
						s = 1;
						
						break;
	
					default:
						break;
				}
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
				if (out != null)
					out.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		
	}

}
