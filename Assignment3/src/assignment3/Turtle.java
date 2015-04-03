package assignment3;

import java.awt.geom.Point2D;

public class Turtle {
	
	Point2D currentLocation;
	int degrees;
	String input;
	int distance;
	boolean isPenUp = false;
	
	public Turtle() {
		this.currentLocation = new Point2D.Double(0,0);
	}
	
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Point2D getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Point2D currentLocation) {
		this.currentLocation = currentLocation;
	}

	public int getDegrees() {
		return degrees;
	}

	public void setDegrees(int degrees) {
		this.degrees = degrees;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	void move(int distance) {
		Expression e = new Move(String.valueOf(distance));
		e.interpret(this);
	}
	
	void turn(int degrees) {
		Expression e = new Turn(String.valueOf(degrees));
		e.interpret(this);
	}
	
	void penUp() {
		this.isPenUp = true;
	}
	
	void penDown() {
		this.isPenUp = false;
	}
	
	boolean isPenUp() {
		return true;
	}
	
//	int direction() {
//		return 1;
//	}
	
	@Override
	public String toString() {
		return currentLocation.getX() + " " + currentLocation.getY();
	}
}
