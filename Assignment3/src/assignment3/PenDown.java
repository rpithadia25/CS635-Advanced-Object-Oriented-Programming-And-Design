package assignment3;

public class PenDown implements Command{

	@Override
	public void interpret(Turtle context) {
		context.penDown();
	}
}
