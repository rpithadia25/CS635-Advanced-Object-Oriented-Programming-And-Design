package command;

import visitor.Visitor;
import interpreter.ExpressionRepeat;

public class CommandRepeat extends Command {

	public int times;

	public CommandRepeat(int times) {
		expression = new ExpressionRepeat(times);
		this.times = times;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
