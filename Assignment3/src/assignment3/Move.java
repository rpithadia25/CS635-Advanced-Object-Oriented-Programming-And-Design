package assignment3;


public class Move implements Command {
	
	private int distance;
	InterpretMove move;
	
	public Move(int distance) {
		this.distance = distance;
	}
	
	@Override
	public void interpret(Turtle context) {
		//context.move(distance);
		move =  new InterpretMove(distance);
		context.setDistance(distance);
		move.interpret(context);
	}
}
