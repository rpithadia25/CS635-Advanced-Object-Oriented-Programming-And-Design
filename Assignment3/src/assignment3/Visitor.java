package assignment3;

import java.util.ArrayList;

public abstract class Visitor {
	public ArrayList<Command> listOfCommands = new ArrayList<Command>();

	public abstract void visit(Move move);

	public abstract void visit(Turn turn);

	public abstract void visit(Repeat repeat);

	public abstract void visit(PenUp penUp);

	public abstract void visit(PenDown penDown);

	public abstract void visit(End end);
}
