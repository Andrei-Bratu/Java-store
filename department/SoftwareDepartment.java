package department;

import java.util.Vector;

import customer.Customer;
import shopping.ShoppingCart;
import tema_magazin.Item;

public class SoftwareDepartment extends Department {

	
	public SoftwareDepartment(int i, String n) {
		nume = n;
		id = i;
		items = new Vector<Item>();
		clientic = new Vector<Customer>();
	}

	@Override
	public void accept(ShoppingCart s) {
		double min = items.get(0).getPrice();
		for (int i = 1; i < items.size(); i++)
		{
			if (items.get(i).getPrice() < min)
				min = items.get(i).getPrice();
		}
		if (s.getBuget() < min)
		{
			for (int i = 0; i < items.size(); i++)
			{
				if (s.contains(items.get(i)))
				{
					Item aux = s.getItem(s.indexOf(items.get(i)));
					s.remove(aux);
					double a = aux.getPrice();
					aux.setPrice(a - a/5);
					s.add(aux);
				}
			}
		}
		
		
	}
}
