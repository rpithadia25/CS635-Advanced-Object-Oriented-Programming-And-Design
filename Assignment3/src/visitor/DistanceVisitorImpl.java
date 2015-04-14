package visitor;

import command.CommandMove;

public class DistanceVisitorImpl extends DistanceVisitor {

	@Override
	public int visit(CommandMove move) {
		distanceCovered += move.getDistance();
		return distanceCovered;
	}

	public int getDistanceCovered() {
		return distanceCovered;
	}
	
}
