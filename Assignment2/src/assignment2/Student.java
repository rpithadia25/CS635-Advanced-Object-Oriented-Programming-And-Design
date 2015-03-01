package assignment2;

public class Student {

	private String name;
	private String redId;
	private double gpa;
	
	public Student(String name, String redId, double gpa) {
		this.name = name;
		this.redId = redId;
		this.gpa = gpa;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRedId() {
		return redId;
	}
	public void setRedId(String redId) {
		this.redId = redId;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double d) {
		this.gpa = d;
	}
	
	@Override
	public String toString() {
		return name.toString();
	}

}
