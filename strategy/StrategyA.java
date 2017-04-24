package strategy;

import java.util.Vector;

import shopping.WishList;
import tema_magazin.Item;

public class StrategyA implements Strategy {

	@Override
	public Item execute(WishList wL) {
		// TODO Auto-generated method stub
		Vector<Item> v = wL.getItems();
		double min = v.get(0).getPrice();
		int ind = 0;
		for (int i = 1; i < v.size(); i++)
			if (v.get(i).getPrice() < min)
			{
				min = v.get(i).getPrice();
				ind = i;
			}
		return wL.remove(ind);
	}
	
}
