package department;

import java.util.Vector;

import customer.Customer;
import shopping.ShoppingCart;
import tema_magazin.Item;

public class VideoDepartment extends Department {

	
	public VideoDepartment(int i, String n) {
		nume = n;
		id = i;
		items = new Vector<Item>();
		clientic = new Vector<Customer>();
	}

	@Override
	public void accept(ShoppingCart s) {
		double max = items.get(0).getPrice();
		double tot = 0;
		for (int i = 0; i < items.size(); i++)
		{
			if (items.get(i).getPrice() > max)
				max = items.get(i).getPrice();
			if (s.contains(items.get(i)))
				tot += items.get(i).getPrice();
		}
		if (tot > max)
		{
			for (int i = 0; i < items.size(); i++)
			{
				if (s.contains(items.get(i)))
				{
					Item aux = s.getItem(s.indexOf(items.get(i)));
					s.remove(aux);
					double a = aux.getPrice();
					aux.setPrice(a - 3*a/20);
					s.add(aux);
				}
			}
		}
		s.setBuget(s.getBuget() + tot/20);
		
	}
}
