package tema_magazin;

import java.text.DecimalFormat;

public class Item {
	private String name;
	private int id;
	private double price;
	private static DecimalFormat df2 = new DecimalFormat(".00");
	public Item (String n, int i, double p)
	{
		name = n;
		id = i;
		price = p;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return name + ";" + id + ";" + df2.format(price);
	}
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public double getPrice() {
		return price;
	}

}
