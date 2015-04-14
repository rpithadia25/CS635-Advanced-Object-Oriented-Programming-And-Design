package assignment3;

public class End extends Command {

	public End() {
		expression = new ExpressionEnd();
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
