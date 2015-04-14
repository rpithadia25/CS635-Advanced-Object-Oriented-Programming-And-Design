package command;

import visitor.DistanceVisitor;
import visitor.TurtleVisitor;
import interpreter.ExpressionMove;

public class CommandMove extends Command {

	protected int distance;

	public CommandMove(int distance) {
		expression = new ExpressionMove(distance);
		this.distance = distance;
	}

	@Override
	public void accept(TurtleVisitor visitor) {
		visitor.visit(this);
	}
	
	public int acceptDistanceCovered(DistanceVisitor visitor) {
		return visitor.visit(this);
	}
	
	public int getDistance() {
		return distance;
	}
	
}
