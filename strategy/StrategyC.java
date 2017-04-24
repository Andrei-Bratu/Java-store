package strategy;

import shopping.WishList;
import tema_magazin.Item;

public class StrategyC implements Strategy {

	@Override
	public Item execute(WishList wL) {
		// TODO Auto-generated method stub
		Item it = wL.getLst();
		wL.remove(wL.getLst());
		return it;
	}

}
