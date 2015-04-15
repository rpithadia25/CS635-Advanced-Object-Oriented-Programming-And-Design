package command;

import assignment3.Context;
import visitor.TurtleVisitor;
import interpreter.ExpressionPenDown;

public class CommandPenDown extends Command {

	public CommandPenDown() {
		expression = new ExpressionPenDown();
	}

	@Override
	public void accept(TurtleVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void undo(Context context) {
		context = previousContext;
		context.penDown();
	}

}
