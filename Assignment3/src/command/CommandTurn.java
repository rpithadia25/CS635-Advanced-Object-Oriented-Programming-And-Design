package command;

import visitor.TurtleVisitor;
import interpreter.ExpressionTurn;

public class CommandTurn extends Command {

	protected int degrees;

	public CommandTurn(int degrees) {
		expression = new ExpressionTurn(degrees);
		this.degrees = degrees;
	}

	@Override
	public void accept(TurtleVisitor visitor) {
		visitor.visit(this);
	}

}
