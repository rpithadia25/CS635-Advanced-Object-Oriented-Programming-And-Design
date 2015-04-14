package command;

import visitor.Visitor;
import interpreter.ExpressionTurn;

public class CommandTurn extends Command {

	protected int degrees;

	public CommandTurn(int degrees) {
		expression = new ExpressionTurn(degrees);
		this.degrees = degrees;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
