package assignment3;


public class PenDown implements Command {

	Interpreter penDown;
	@Override
	public void interpret(Turtle context) {
		penDown = new InterpretPenDown();
		penDown.interpret(context);
	}
}
