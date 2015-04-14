package assignment3;

public class ExpressionMove extends Expression {

	private int distance;

	public ExpressionMove(int distance) {
		this.distance = distance;
	}

	@Override
	public void interpret(Turtle context) {
		context.move(distance);
	}
}
