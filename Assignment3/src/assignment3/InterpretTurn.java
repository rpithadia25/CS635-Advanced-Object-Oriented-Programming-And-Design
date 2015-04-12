package assignment3;


public class InterpretTurn extends Interpreter {

	private int degrees;
	
	public InterpretTurn(int degrees) {
		this.degrees = degrees;
	}
	
	@Override
	void interpret(Turtle context) {
		context.turn(degrees);
	}
}
