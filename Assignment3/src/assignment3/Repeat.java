package assignment3;


public class Repeat implements Command{

	protected int times;
	Turtle turtle;
	InterpretRepeat repeat;
	
	public Repeat(int times) {
		this.times = times;
	}
	
	@Override
	public void interpret(Turtle context) {
		
		repeat.interpret(context);
		//turtle.setCurrentLocation(context.getCurrentLocation());
	}
	
}
