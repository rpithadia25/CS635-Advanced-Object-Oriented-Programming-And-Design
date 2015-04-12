package assignment3;


public class InterpretMove extends Interpreter{

	private int distance;
	
	public InterpretMove(int distance) {
		this.distance = distance;
	}
	
	@Override
	public void interpret(Turtle context) {
		context.move(distance);
	}
}
