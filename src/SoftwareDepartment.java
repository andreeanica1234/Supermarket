import java.util.ListIterator;

public class SoftwareDepartment extends Department{
	
	public SoftwareDepartment(){
		super();
	}
	
	public SoftwareDepartment(String name, int id){
		super(name, id);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
