package interpreter;

import assignment3.Turtle;

public class ExpressionTurn extends Expression {

	private int degrees;

	public ExpressionTurn(int degrees) {
		this.degrees = degrees;
	}

	@Override
	public void interpret(Turtle context) {
		context.turn(degrees);
	}

}
