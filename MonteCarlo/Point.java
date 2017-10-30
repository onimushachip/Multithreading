
public class Point {
	private double vertical;
	private double horizontal;
	private boolean insideCircle; // Checking if the point is inside the circle
	
	// Storing the coordinate of a random point
	public Point (double inputVertical, double inputHorizontal) {
		vertical = inputVertical;
		horizontal = inputHorizontal;
	}
	
	public void printCoordinate() {
		System.out.println();
		System.out.print(vertical + " ");
		System.out.print(horizontal);
		if (insideCircle) {
			System.out.print(" Interior");
		}
		else {
			System.out.print(" Exterior");
		}
	}
	
	public void checkInside() {
		insideCircle = true;
	}
	
	public double getVertical() {
		return vertical;
	}
	
	public double getHorizontal() {
		return horizontal;
	}
	
	public boolean getPositionCheck() {
		return insideCircle;
	}
	
}
