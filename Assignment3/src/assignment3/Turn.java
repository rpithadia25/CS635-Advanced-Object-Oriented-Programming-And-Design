package assignment3;

public class Turn implements Command{

	int degrees;
	
	public Turn(int degrees) {
		this.degrees = degrees;
	}
	
	@Override
	public void interpret(Turtle context) {
		context.turn(degrees);
		//context.setDegrees(context.getDegrees() + turtle.getDegrees());
	}
}
