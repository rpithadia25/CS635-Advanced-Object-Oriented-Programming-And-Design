package interpreter;

import assignment3.Context;

public class ExpressionPenDown extends Expression {

	@Override
	public void interpret(Context context) {
		context.penDown();
	}

}
