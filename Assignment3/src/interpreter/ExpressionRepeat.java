package interpreter;

import assignment3.Context;
import assignment3.Turtle;

public class ExpressionRepeat extends Expression {

	public int times;
	Turtle turtle;

	public ExpressionRepeat(int times) {
		this.times = times;
	}

	@Override
	public void interpret(Context context) {
		turtle.setCurrentLocation(context.getCurrentLocation());
	}

}
