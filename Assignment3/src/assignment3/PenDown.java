package assignment3;

public class PenDown extends Command {

	public PenDown() {
		expression = new ExpressionPenDown();
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
