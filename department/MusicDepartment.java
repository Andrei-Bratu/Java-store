package department;

import java.util.Vector;

import customer.Customer;
import shopping.ShoppingCart;
import tema_magazin.Item;

public class MusicDepartment extends Department {
	
	public MusicDepartment(int i, String n) {
		nume = n;
		id = i;
		items = new Vector<Item>();
		clientic = new Vector<Customer>();
	}

	@Override
	public void accept(ShoppingCart s) {
		double tot = 0;
		for (int i = 0; i < items.size(); i++)
		{
			if (s.contains(items.get(i)))
			{
				tot += s.getItem(s.indexOf(items.get(i))).getPrice();
			}
		}	
		s.setBuget(s.getBuget() + tot/10);
	}

}
