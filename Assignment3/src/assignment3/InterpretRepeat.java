package assignment3;


public class InterpretRepeat extends Interpreter{

	protected int times;
	Turtle turtle;
	
	public InterpretRepeat(int times) {
		this.times = times;
	}
	
	@Override
	void interpret(Turtle context) {
		turtle.setCurrentLocation(context.getCurrentLocation());
	}
}
