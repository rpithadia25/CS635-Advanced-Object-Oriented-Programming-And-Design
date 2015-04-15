package command;

import visitor.TurtleVisitor;
import interpreter.Expression;
import assignment3.Context;

public abstract class Command {

	protected Expression expression;
	protected Context previousContext;

	public void interpret(Context context) {
		previousContext = context;
		expression.interpret(context);
	}

	public abstract void accept(TurtleVisitor visitor);
	
	public abstract void undo(Context context);

}