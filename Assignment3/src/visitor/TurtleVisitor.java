package visitor;

import java.util.ArrayList;

import command.Command;
import command.CommandEnd;
import command.CommandMove;
import command.CommandPenDown;
import command.CommandPenUp;
import command.CommandRepeat;
import command.CommandTurn;

public class TurtleVisitor extends Visitor {

	public ArrayList<Command> getCommands() {
		return listOfCommands;
	}

	@Override
	public void visit(CommandMove move) {
		listOfCommands.add(move);
	}

	@Override
	public void visit(CommandTurn turn) {
		listOfCommands.add(turn);
	}

	@Override
	public void visit(CommandRepeat repeat) {
		listOfCommands.add(repeat);
	}

	@Override
	public void visit(CommandPenUp penUp) {
		listOfCommands.add(penUp);
	}

	@Override
	public void visit(CommandPenDown penDown) {
		listOfCommands.add(penDown);
	}

	@Override
	public void visit(CommandEnd end) {
		listOfCommands.add(end);
	}
}
