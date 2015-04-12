package assignment3;

public class PenUp implements Command {

	@Override
	public void interpret(Turtle context) {
		context.penUp();
	}

}
