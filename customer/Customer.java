package customer;

import java.util.Vector;

import shopping.ShoppingCart;
import shopping.WishList;
import tema_magazin.Item;
import tema_magazin.Notification;

public class Customer implements Observer {
	private String name;
	private ShoppingCart cart;
	private WishList list;
	private Vector<Notification> notif;
	
	public String getName() {
		return name;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	public WishList getList() {
		return list;
	}

	public ShoppingCart getCart() {
		return cart;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Customer (String n, double b, String s)
	{
		name = n;
		cart = new ShoppingCart(b);
		list = new WishList(s);
		notif = new Vector<>();
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public void update(Notification n) {
		notif.add(n);
		Item it = new Item("", n.getIdprod(), 1.0);
		if (n.getType().equals(Notification.NotificationType.REMOVE))
			list.remove(it);
	}

	public Vector<Notification> getNotif() {
		return notif;
	}

}
