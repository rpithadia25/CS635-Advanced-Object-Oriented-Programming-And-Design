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
		if(name != null)
			this.name = name;
		else
			throw new IllegalArgumentException();
	}

	public String getRedId() {
		return redId;
	}

	public void setRedId(String redId) {
		if(redId != null)
			this.redId = redId;
		else
			throw new IllegalArgumentException();
	}

	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	@Override
	public String toString() {
		return "Name: " + name.toString() + ", RedId: " + redId.toString() + ", GPA: " + gpa;
	}

}
