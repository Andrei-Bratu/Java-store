package shopping;

import department.BookDepartment;
import department.MusicDepartment;
import department.SoftwareDepartment;
import department.VideoDepartment;
import tema_magazin.Item;

public class ShoppingCart extends ItemList implements Visitor {
	private double buget;
	
	public double getBuget() {
		return buget;
		
	}

	public void setBuget(double buget) {
		this.buget = buget;
	}

	public ShoppingCart (double b){
		buget = b;
	}
	public  boolean  add ( Item  element ) {
	
		if (element.getPrice() < buget)
		{
			buget -= element.getPrice();
			super.add(element);
			return true;
		}
		else
			return false;
	}
	public  boolean  remove ( Item  element ) {
	
		if (element != null)
		{
			buget += element.getPrice();
			return super.remove(element);
		}
		else
			return false;
	}

	@Override
	public void visit(BookDepartment b) {
		b.accept(this);
	}

	@Override
	public void visit(MusicDepartment m) {
		m.accept(this);
	}

	@Override
	public void visit(SoftwareDepartment s) {
		s.accept(this);
	}

	@Override
	public void visit(VideoDepartment v) {
		v.accept(this);
	}

}
