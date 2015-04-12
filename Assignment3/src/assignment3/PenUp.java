package assignment3;


public class PenUp implements Command {

	Interpreter penUp;
	@Override
	public void interpret(Turtle context) {
		penUp = new InterpretPenUp();
		penUp.interpret(context);
	}

}
