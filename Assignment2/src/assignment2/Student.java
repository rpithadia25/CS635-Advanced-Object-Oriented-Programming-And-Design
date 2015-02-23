package assignment2;

public class Student implements Comparable{

	private String name;
	private String redId;
	private double gpa;
	
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
	
	@Override
	public int compareTo(Object o) {
		Student student = (Student) o;
		return this.name.compareTo(student.name);
	}

}
