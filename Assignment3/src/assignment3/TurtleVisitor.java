package assignment3;

import java.util.ArrayList;

public class TurtleVisitor extends Visitor {

	public ArrayList<Command> getCommands() {
		return listOfCommands;
	}

	@Override
	public void visit(Move move) {
		listOfCommands.add(move);
	}

	@Override
	public void visit(Turn turn) {
		listOfCommands.add(turn);
	}

	@Override
	public void visit(Repeat repeat) {
		listOfCommands.add(repeat);
	}

	@Override
	public void visit(PenUp penUp) {
		listOfCommands.add(penUp);
	}

	@Override
	public void visit(PenDown penDown) {
		listOfCommands.add(penDown);
	}

	@Override
	public void visit(End end) {
		listOfCommands.add(end);
	}
}
