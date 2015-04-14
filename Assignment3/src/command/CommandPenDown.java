package command;

import visitor.Visitor;
import interpreter.ExpressionPenDown;

public class CommandPenDown extends Command {

	public CommandPenDown() {
		expression = new ExpressionPenDown();
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
