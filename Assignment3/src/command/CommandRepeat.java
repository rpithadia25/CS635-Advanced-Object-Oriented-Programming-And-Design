package command;

import assignment3.Context;
import visitor.TurtleVisitor;
import interpreter.ExpressionRepeat;

public class CommandRepeat extends Command {

	public int times;

	public CommandRepeat(int times) {
		expression = new ExpressionRepeat(times);
		this.times = times;
	}

	@Override
	public void accept(TurtleVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void undo(Context context) {
		
	}

}
