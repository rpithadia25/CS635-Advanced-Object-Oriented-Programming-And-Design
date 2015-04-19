package visitor;

import java.util.ArrayList;

import command.Command;

public interface TurtleVisitable {
	public ArrayList<Command> accept(TurtleVisitorImpl turtleVisitor);
}
