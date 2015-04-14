package interpreter;

import assignment3.Turtle;

public class ExpressionPenDown extends Expression {

	@Override
	public void interpret(Turtle context) {
		context.penDown();
	}

}
