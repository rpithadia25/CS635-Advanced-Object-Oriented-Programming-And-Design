package assignment2;

public class Student {

	private SortStrategy strategy;
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
	
	public void setStrategy(SortStrategy strategy) {
		this.strategy = strategy;
	}
	
	@Override
	public String toString() {
		return name.toString();
	}
	
//	@Override
//	public int compareTo(Object o) {
//		Student student = (Student) o;
//		this.strategy = student.strategy;
//		if(strategy.getClass() == (SortByName.class)){
//			return this.name.compareToIgnoreCase(student.name);
//		} else if (strategy.getClass() == (SortByGpa.class)){
//			return 0;
//		} else {
//			return this.redId.compareToIgnoreCase(student.redId);
//		}
//	}

}
