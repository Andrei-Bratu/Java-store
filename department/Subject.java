package department;

import customer.Customer;
import tema_magazin.Notification;

public interface Subject {
	
	public void addObserver (Customer c);
	public void removeObserver (Customer c);
	public void notifyAllObservers (Notification n);
	

}
