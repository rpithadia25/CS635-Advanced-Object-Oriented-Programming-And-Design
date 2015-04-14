package command;

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

}
