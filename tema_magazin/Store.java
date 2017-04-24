package tema_magazin;

import java.util.Vector;

import customer.Customer;
import department.Department;
import shopping.ShoppingCart;

public class Store {
	private String name;
	private Vector<Department> dept = null;
	private Vector<Customer> clienti = null;
	private static Store instance = new Store();
	
	public Store (){
		name = null;
		dept = new Vector<Department>();
		clienti = new Vector<Customer>();
	}
	public static Store getInstance ()
	{
		return instance;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void enter (Customer c)
	{
		clienti.add(c);
	}
	public void exit (Customer c)
	{
		clienti.remove(c);
	}
	public ShoppingCart getShoppingCart (double b)
	{
		return new ShoppingCart(b);
	}
	public Vector<Customer> getCustomers ()
	{
		return clienti;
	}
	public Vector<Department> getDepartments ()
	{
		return dept;
	}
	public void addDepartment (Department d)
	{
		dept.add(d);
	}
	public Department getDepartment (int id)
	{
		for (int i = 0; i < dept.size(); i++)
		{
			if (dept.get(i).getId() == id)
				return dept.get(i);
		}
		return null;
	}
	public Customer getCustomer (String nam)
	{
		for (int i = 0; i < clienti.size(); i++)
		{
			if (clienti.get(i).getName().equals(nam))
				return clienti.get(i);
		}
		return null;
	}
	public Item getItem (int id)
	{
		for (int i = 0; i < dept.size(); i++)
		{
			if ( dept.get(i).getItem(id) != null)
				return dept.get(i).getItem(id);
		}
		return null;
	}
	public Department getItemDept (int id)
	{
		for (int i = 0; i < dept.size(); i++)
		{
			if ( dept.get(i).getItem(id) != null)
				return dept.get(i);
		}
		return null;
	}
	

}
