package command;

import assignment3.Context;
import visitor.TurtleVisitor;
import interpreter.ExpressionEnd;

public class CommandEnd extends Command {

	public CommandEnd() {
		expression = new ExpressionEnd();
	}

	@Override
	public void accept(TurtleVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void undo(Context context) {
		
	}

}
