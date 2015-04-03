package assignment3;

import java.awt.geom.Point2D;

public class Context {

	private String input;
	private Point2D location;
	
	public Context(String input) {
		this.input = input;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public Point2D getLocation() {
		return location;
	}

	public void setLocation(Point2D location) {
		this.location = location;
	}
	
}
