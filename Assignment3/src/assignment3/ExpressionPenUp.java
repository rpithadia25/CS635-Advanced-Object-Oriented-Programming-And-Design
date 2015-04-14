package assignment3;

public class ExpressionPenUp extends Expression {

	@Override
	void interpret(Turtle context) {
		context.penUp();
	}
}
