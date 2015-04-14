package assignment3;

public class ExpressionRepeat extends Expression {

	protected int times;
	Turtle turtle;

	public ExpressionRepeat(int times) {
		this.times = times;
	}

	@Override
	void interpret(Turtle context) {
		turtle.setCurrentLocation(context.getCurrentLocation());
	}
}
