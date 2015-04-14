package command;

import visitor.Visitor;
import interpreter.ExpressionPenUp;

public class CommandPenUp extends Command {

	public CommandPenUp() {
		expression = new ExpressionPenUp();
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
