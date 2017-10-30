import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

// Storing all the generated random points in a list
public class DataStore {
	private static ArrayList<Point> listOfPoints = new ArrayList();
	private static int numberOfExperiments;
	private static int newApproximation;
	private static ReentrantLock theLock = new ReentrantLock();

	public static void addPoint(Point input) {
		theLock.lock();
		if (numberOfExperiments > 0){
			listOfPoints.add(input);
			numberOfExperiments--;
		}
		theLock.unlock();
	}
	
	public static int getListSize() {
		return listOfPoints.size();
	}
	
	public static void printList() {
		for (int i = 0; i < listOfPoints.size(); i++) {
			listOfPoints.get(i).printCoordinate();
		}
		System.out.println("This list has: " + listOfPoints.size());
	}
	
	public static Point getPoint(int index) {
		return listOfPoints.get(index);
	}
	
	public static void setNumberOfExperiments(int input) {
		numberOfExperiments = input;
	}
	
	public static void setNewApproximation(int input) {
		newApproximation = input;
	}
	
	public static int checkNumberOfExperiments() {
		return numberOfExperiments;
	}

}
