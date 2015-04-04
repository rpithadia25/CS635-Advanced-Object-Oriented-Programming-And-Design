package assignment3;

public class Turn implements Command{

	Turtle turtle;
	
	public Turn(String input) {
		turtle = new Turtle();
		turtle.setDegrees(Integer.parseInt(input));
		turtle.setInput(input);
	}
	
	@Override
	public void interpret(Turtle context) {
		context.setDegrees(context.getDegrees() + turtle.getDegrees());
	}
}
