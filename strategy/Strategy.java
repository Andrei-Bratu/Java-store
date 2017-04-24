package strategy;

import shopping.WishList;
import tema_magazin.Item;

public interface Strategy {
	public Item execute (WishList wL);

}
