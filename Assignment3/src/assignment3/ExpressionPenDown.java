package assignment3;

public class ExpressionPenDown extends Expression {

	@Override
	void interpret(Turtle context) {
		context.penDown();
	}
}
