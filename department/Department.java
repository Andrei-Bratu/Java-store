package department;

import java.util.Vector;

import customer.Customer;
import shopping.ShoppingCart;
import tema_magazin.Item;
import tema_magazin.Notification;

public abstract class Department implements Subject {
	public String nume;
	public Vector<Item> items;
	public Vector<Customer> clientic;
	public Vector<Customer> obs = new Vector<>();
	public int id;

	public Department ()
	{
	}
	
	public void enter (Customer c){
		clientic.add(c);
	}
	@Override
	public String toString() {
		return  nume + " - id=" + id ;
	}

	public void exit (Customer c){
		clientic.remove(c);
	}
	public Vector<Customer> getCustomers (){
		return clientic;
	}
	public Vector<Customer> getObservers (){
		return obs;
	}
	public int getId (){
		return id;
	}
	public void addItem (Item i){
		items.add(i);
	}
	public void removeItem (Item i){
		items.remove(i);
	}
	public Vector<Item> getItems (){
		return items;
	}
	public boolean hasItem (Item it)
	{
		for (int i=0; i < items.size(); i++)
			if (items.get(i).getId() == it.getId())
				return true;
		return false;
	}
	public Item getItem (int id){
		for (int i = 0; i < items.size(); i++)
		{
			if (items.get(i).getId() == id)
				return items.get(i);
		}
		return null;
	}
	public void addObserver (Customer c){
		obs.add(c);
	}
	public void removeObserver (Customer c){
		obs.remove(c);
	}
	public void notifyAllObservers (Notification n){
		for (int i = 0; i < obs.size(); i++)
		{
			obs.get(i).update(n);
		}
	}
	/* functie pentru modificarea obiectelor aflate deja in shoppingcart sau in wishlist */
	public void modifIt (Item it, Item n)
	{
		Item aux;
		for (int i = 0; i < obs.size(); i++)
		{
			if (obs.get(i).getCart().contains(it))
			{
				aux = obs.get(i).getCart().remove(obs.get(i).getCart().indexOf(it));
				aux.setPrice(n.getPrice());
				obs.get(i).getCart().add(aux);
			}
			if (obs.get(i).getList().contains(it))
			{
				aux = obs.get(i).getList().remove(obs.get(i).getList().indexOf(it));
				aux.setPrice(n.getPrice());
				obs.get(i).getList().add(aux);
			}
		}
	}
	public abstract void accept (ShoppingCart s);
	/* functie pentru stergerea obiectelor aflate in shoppingcart sau in wishlist*/
	public void prodDel (Item it)
	{
		for (int i = 0; i < clientic.size(); i++)
		{
			if (clientic.get(i).getCart().contains(it))
			{
				clientic.get(i).getCart().setBuget(clientic.get(i).getCart().getBuget() + it.getPrice());
				clientic.get(i).getCart().remove(it);
			}
		}
		for (int i = 0; i < obs.size(); i++)
		{
			if (obs.get(i).getList().contains(it))
			{
				obs.get(i).getList().remove(it);
			}
		}
	}
	/* functie ce verifica daca in wishlist mai exista obiecte din acest departament */
	public boolean verify (Customer c)
	{
		int sem = 0;
		for (int i = 0; i < items.size(); i++)
		{
			if (c.getList().contains(items.get(i)))
				sem = 1;
		}
		if (sem == 0)
			return false;
		return true;
	}
	
}
