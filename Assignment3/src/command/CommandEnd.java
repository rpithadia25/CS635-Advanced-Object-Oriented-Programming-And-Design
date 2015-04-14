package command;

import visitor.Visitor;
import interpreter.ExpressionEnd;

public class CommandEnd extends Command {

	public CommandEnd() {
		expression = new ExpressionEnd();
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
