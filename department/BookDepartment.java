package department;

import java.util.Vector;

import customer.Customer;
import shopping.ShoppingCart;
import tema_magazin.Item;

public class BookDepartment extends Department {
	
	public BookDepartment(int i, String n) {
		nume = n;
		id = i;
		items = new Vector<Item>();
		clientic = new Vector<Customer>();
	}

	@Override
	public void accept(ShoppingCart s) {
		for (int i = 0; i < items.size(); i++)
		{
			if (s.contains(items.get(i)))
			{
				Item aux = s.getItem(s.indexOf(items.get(i)));
				s.remove(aux);
				double a = aux.getPrice();
				aux.setPrice(a - a/10);
				s.add(aux);
			}
		}
	}

}
