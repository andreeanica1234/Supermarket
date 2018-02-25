import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;

public class BookDepartment extends Department {
	
	public BookDepartment(){
		super();
	}
	
	public BookDepartment(String name, int id){
		super(name, id);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		
	}
}
