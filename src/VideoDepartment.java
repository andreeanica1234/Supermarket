import java.util.ArrayList;
import java.util.ListIterator;

public class VideoDepartment extends Department{
	
	public VideoDepartment(){
		super();
	}
	
	public VideoDepartment(String name, int id){
		super(name, id);
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

}
