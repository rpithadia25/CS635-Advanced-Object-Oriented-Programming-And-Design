package assignment3;

public class Move implements Command {
	
	private int distance;
	
	public Move(int distance) {
		this.distance = distance;
	}
	
	@Override
	public void interpret(Turtle context) {
		context.move(distance);

//		if(context.isPenUp()) {
//			double radians = Math.toRadians(context.getDegrees());
//			int distance = turtle.getDistance();
//			double deltaX = Math.cos(radians) * distance;
//			double deltaY = Math.sin(radians) * distance;
//			double x = roundToTwoDigits(deltaX + context.getCurrentLocation().getX());
//			double y = roundToTwoDigits(deltaY + context.getCurrentLocation().getY());
//			context.getCurrentLocation().setLocation(x,y);
//			context.setDistance(distance + context.getDistance());
//		} else {
//			throw new UnsupportedOperationException("Turtle cannot draw right now.");
//		}
		
	}
	
//	private double roundToTwoDigits(Double value) {
//		return Math.round(value * 100.0) / 100.0;
//	}
}
