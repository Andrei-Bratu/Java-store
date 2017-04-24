package tema_magazin;

import java.util.Date;


public class Notification {
	public static enum NotificationType {
	ADD, REMOVE, MODIFY
}
	private NotificationType type;
	private Date d;
	private int iddep;
	private int idprod;
	
	
	public Notification( NotificationType s, int iddep, int idprod) {
		type = s;
		this.iddep = iddep;
		this.idprod = idprod;
		d = new Date();
	}
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
	public int getIddep() {
		return iddep;
	}
	public void setIddep(int iddep) {
		this.iddep = iddep;
	}
	public int getIdprod() {
		return idprod;
	}
	public void setIdprod(int idprod) {
		this.idprod = idprod;
	}
	public NotificationType getType() {
		return type;
	}
	public void setType(NotificationType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return type.toString() + ";" + idprod + ";" +  iddep;
	}

}
