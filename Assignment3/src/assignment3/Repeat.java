package assignment3;

public class Repeat implements Expression{

	Turtle turtle;
	int times;
	
	public Repeat(int times) {
		this.times = times;
	}
	
	@Override
	public void interpret(Turtle context) {
		turtle.setCurrentLocation(context.getCurrentLocation());
	}
	
}
