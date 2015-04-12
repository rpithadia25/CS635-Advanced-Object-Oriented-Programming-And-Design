package assignment3;


public class Turn implements Command{

	protected int degrees;
	InterpretTurn turn;
	
	public Turn(int degrees) {
		this.degrees = degrees;
	}
	
	@Override
	public void interpret(Turtle context) {
		turn = new InterpretTurn(degrees);
		turn.interpret(context);
		//context.turn(degrees);
		
		
		
		
		//context.setDegrees(context.getDegrees() + turtle.getDegrees());
	}
}
