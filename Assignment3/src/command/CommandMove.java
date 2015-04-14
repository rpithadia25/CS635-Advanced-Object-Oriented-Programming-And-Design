package command;

import visitor.Visitor;
import interpreter.ExpressionMove;

public class CommandMove extends Command {

	protected int distance;

	public CommandMove(int distance) {
		expression = new ExpressionMove(distance);
		this.distance = distance;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
