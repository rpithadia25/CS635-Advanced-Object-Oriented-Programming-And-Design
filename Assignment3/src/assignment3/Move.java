package assignment3;

public class Move extends Command {

	protected int distance;

	public Move(int distance) {		
		expression = new ExpressionMove(distance);
		this.distance = distance;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
