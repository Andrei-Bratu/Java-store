package strategy;


import shopping.WishList;
import tema_magazin.Item;

public class StrategyB implements Strategy {

	@Override
	public Item execute(WishList wL) {
		// TODO Auto-generated method stub
		return wL.remove(0);
	}

}
