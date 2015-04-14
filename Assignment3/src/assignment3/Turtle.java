package assignment3;

import java.awt.geom.Point2D;

public class Turtle {

	private Point2D currentLocation;
	private int degrees;
	private String input;
	private int distance;
	private boolean isPenUp = false;

	public Turtle() {
		this.currentLocation = new Point2D.Double(0, 0);
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
		if (!isPenUp) {
			double radians = Math.toRadians(this.getDegrees());
			double deltaX = Math.cos(radians) * distance;
			double deltaY = Math.sin(radians) * distance;
			double x = roundToTwoDigits(deltaX
					+ this.getCurrentLocation().getX());
			double y = roundToTwoDigits(deltaY
					+ this.getCurrentLocation().getY());
			this.currentLocation.setLocation(x, y);
			this.setDistance(distance + this.getDistance());
		} else {
			throw new UnsupportedOperationException(
					"Turtle cannot draw right now.");
		}
	}

	private double roundToTwoDigits(Double value) {
		return Math.round(value * 100.0) / 100.0;
	}

	void turn(int degrees) {
		this.setDegrees(this.getDegrees() + degrees);
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

	//TODO
	int direction() {
		return 1;
	}

	@Override
	public String toString() {
		return currentLocation.getX() + " " + currentLocation.getY();
	}
}
