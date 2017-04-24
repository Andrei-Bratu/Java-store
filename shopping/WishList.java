package shopping;

import java.util.Comparator;

import strategy.*;
import tema_magazin.Item;

public class WishList extends ItemList{
	mycmp c = new mycmp();
	private Strategy strat;
	
	public  WishList() {
		super.c = c;
	}
	public  WishList(String s) {
		super.c = c;
		switch (s) {
		case "A":
			strat = new StrategyA();
			break;
		case "B":
			strat = new StrategyB();
			
			break;
		case "C":
			strat = new StrategyC();
			
			break;

		default:
			break;
		}
	}
	public Item executeStrategy ()
	{
		return strat.execute(this);
	}
	
	public class mycmp implements Comparator<Item>{
	
		@Override
		public int compare(Item o1, Item o2) {
				return o1.getName().compareTo(o2.getName());
		}
		
	}

}
