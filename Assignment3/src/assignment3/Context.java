package assignment3;

import java.awt.geom.Point2D;

public interface Context {
	void move(int distance);

	void turn(int degrees);

	void penUp();

	void penDown();
	
	boolean isPenUp();
	
	int direction();
	
	Point2D getCurrentLocation();
	
	void setDistance(int input);
	
	int getDistance();
	
	void setDirection(int input);
	
	int getDirection();
}
