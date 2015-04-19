package interpreter;

import assignment3.Context;

public class ExpressionPenUp extends Expression {

	@Override
	public void interpret(Context context) {
		context.penUp();
	}

}
