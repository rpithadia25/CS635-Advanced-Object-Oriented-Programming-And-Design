package visitor;

import command.CommandMove;

public abstract class DistanceVisitor {
	protected int distanceCovered;

	public abstract int visit(CommandMove move);
}
