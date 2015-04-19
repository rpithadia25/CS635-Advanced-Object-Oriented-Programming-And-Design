package visitor;

import java.util.ArrayList;

import command.Command;
import command.CommandEnd;
import command.CommandMove;
import command.CommandPenDown;
import command.CommandPenUp;
import command.CommandRepeat;
import command.CommandTurn;

public abstract class TurtleVisitor {
	public ArrayList<Command> listOfCommands = new ArrayList<Command>();

	public abstract void visit(CommandMove move);

	public abstract void visit(CommandTurn turn);

	public abstract void visit(CommandRepeat repeat);

	public abstract void visit(CommandPenUp penUp);

	public abstract void visit(CommandPenDown penDown);

	public abstract void visit(CommandEnd end);
}
