import java.util.ListIterator;

public class MusicDepartment extends Department{
	public MusicDepartment(){
		super();
	}
	
	public MusicDepartment(String name, int id){
		super(name, id);
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}
}
