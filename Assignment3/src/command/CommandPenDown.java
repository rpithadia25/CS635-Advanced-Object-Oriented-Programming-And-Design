package command;

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

}
