package interpreter;

import assignment3.Turtle;

public class ExpressionPenUp extends Expression {

	@Override
	public void interpret(Turtle context) {
		context.penUp();
	}

}
