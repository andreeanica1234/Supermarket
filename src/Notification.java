import java.util.Date;

public class Notification {
	private Date date;
	enum NotificationType{ADD, REMOVE, MODIFY}
	private int ID_dep;
	private int ID_prod;
	private NotificationType n;
	
	public Notification(Date date, NotificationType n,int ID_dep, int ID_prod){
		this.date = date;
		this.ID_dep = ID_dep;
		this.ID_prod = ID_prod;
		this.n = n; 
	}
	
	public String toString(){
		return this.n+";"+this.ID_prod+";"+this.ID_dep;
	}	
}
