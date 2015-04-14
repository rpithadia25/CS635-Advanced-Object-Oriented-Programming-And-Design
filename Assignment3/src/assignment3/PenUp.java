package assignment3;

public class PenUp extends Command {

	public PenUp() {
		expression = new ExpressionPenUp();
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
