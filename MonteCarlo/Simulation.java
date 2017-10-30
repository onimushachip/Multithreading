import java.util.Random; 

public class Simulation implements Runnable {

	// Generate a random point and add it to a shared list
	public Point generateRandomPoint() {
		double min = 0;
		double max = 1;
		Random r = new Random();
		double randomVeritical = min + (max - min) * r.nextDouble();
		double randomHorizontal = min + (max - min) * r.nextDouble();
		Point randomPoint = new Point(randomVeritical, randomHorizontal);
		determineInterior(randomPoint);
		return randomPoint;
	}
	
	private void determineInterior(Point inputPoint) {
		Point center = new Point(0.5, 0.5);
			double verticalDifference = inputPoint.getVertical() - center.getVertical();
			double horizontalDifference = inputPoint.getHorizontal() - center.getVertical();
			//Calculate the distance of a random point to the circle's center
			double  verticalSquare = Math.pow(verticalDifference, 2.0);
			double horizontalSquare = Math.pow(horizontalDifference, 2.0);
			double distance = Math.sqrt(verticalSquare + horizontalSquare);
			if (distance < 0.5) {
				inputPoint.checkInside();
			}
	}
	
	public void run () {
		while (DataStore.checkNumberOfExperiments() > 0) {
			DataStore.addPoint(generateRandomPoint());
		}
	}
	
	
}
