package command;

import visitor.TurtleVisitor;
import interpreter.Expression;
import assignment3.Turtle;

public abstract class Command {

	protected Expression expression;

	public void interpret(Turtle context) {
		expression.interpret(context);
	}

	public abstract void accept(TurtleVisitor visitor);

}