package assignment3;

public class Repeat extends Command {

	protected int times;

	public Repeat(int times) {
		expression = new ExpressionRepeat(times);
		this.times = times;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
