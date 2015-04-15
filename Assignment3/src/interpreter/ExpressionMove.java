package interpreter;

import assignment3.Context;

public class ExpressionMove extends Expression {

	private int distance;

	public ExpressionMove(int distance) {
		this.distance = distance;
	}

	@Override
	public void interpret(Context context) {
		context.move(distance);
	}

}
