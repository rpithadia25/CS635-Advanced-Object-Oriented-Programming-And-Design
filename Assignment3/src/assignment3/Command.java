package assignment3;

public abstract class Command {

	protected Expression expression;

	public void interpret(Turtle context) {
		expression.interpret(context);
	}

	public abstract void accept(Visitor visitor);

}