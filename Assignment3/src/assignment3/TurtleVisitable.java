package assignment3;

import java.util.ArrayList;

public interface TurtleVisitable {
	public ArrayList<Command> accept(TurtleVisitor turtleVisitor);
}
