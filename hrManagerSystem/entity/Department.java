package 王逸群.hrManagerSystem.entity;

public class Department {
	private String departNameString;
	private int id;
	
	

	public Department(String departNameString, int id) {
		super();
		this.departNameString = departNameString;
		this.id = id;
	}
	
	public Department(int id,String departNameString) {
		super();
		this.departNameString = departNameString;
		this.id = id;
	}

	@Override
	public String toString() {
		return departNameString;
	}

	public int getDepartId() {

		return id;
	}

}
