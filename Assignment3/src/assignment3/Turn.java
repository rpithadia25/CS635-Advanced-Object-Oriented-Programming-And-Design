package assignment3;

public class Turn extends Command {

	protected int degrees;

	public Turn(int degrees) {
		expression = new ExpressionTurn(degrees);
		this.degrees = degrees;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
