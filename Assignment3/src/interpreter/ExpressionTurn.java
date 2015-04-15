package interpreter;

import assignment3.Context;

public class ExpressionTurn extends Expression {

	private int degrees;

	public ExpressionTurn(int degrees) {
		this.degrees = degrees;
	}

	@Override
	public void interpret(Context context) {
		context.turn(degrees);
	}

}
