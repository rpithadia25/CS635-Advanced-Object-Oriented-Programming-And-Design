package command;

import visitor.TurtleVisitor;
import interpreter.ExpressionPenUp;

public class CommandPenUp extends Command {

	public CommandPenUp() {
		expression = new ExpressionPenUp();
	}

	@Override
	public void accept(TurtleVisitor visitor) {
		visitor.visit(this);
	}

}
