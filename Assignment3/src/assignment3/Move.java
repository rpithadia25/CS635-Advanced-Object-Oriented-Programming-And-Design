package assignment3;

public class Move implements Expression {

	Turtle turtle;
	
	public Move(String input) {
		turtle = new Turtle();
		turtle.setDistance(Integer.parseInt(input)); 
		turtle.setInput(input);
	}
	
	@Override
	public void interpret(Turtle context) {
		if(context.isPenUp()) {
			double radians = Math.toRadians(context.getDegrees());
			int distance = turtle.getDistance();
			double deltaX = Math.cos(radians) * distance;
			double deltaY = Math.sin(radians) * distance;
			double x = roundToTwoDigits(deltaX + context.getCurrentLocation().getX());
			double y = roundToTwoDigits(deltaY + context.getCurrentLocation().getY());
			context.currentLocation.setLocation(x,y);
			context.setDistance(distance + context.getDistance());
		} else {
			throw new UnsupportedOperationException("Turtle cannot draw right now.");
		}
		
	}
	
	private double roundToTwoDigits(Double value) {
		return Math.round(value * 100.0) / 100.0;
	}
}
