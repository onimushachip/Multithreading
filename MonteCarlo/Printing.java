public class Printing implements Runnable {
	private int newApproximation; // Number of experiments to create a new approximation
	private int numberOfExperiments;
	private double pi;
	private int currentExperiment = 0;
	private int numberOfPointsInside = 0;
	private int numberOfPointsOutside = 0;
	
	public Printing(int inputNewApproximation, int inputNoOfExperiments) {
		newApproximation = inputNewApproximation;
		numberOfExperiments = inputNoOfExperiments;
	}
	
	public void checkApproximation() {
		// Scanning the whole list of random points and calculate the pi		
		if ((currentExperiment + newApproximation) < numberOfExperiments) {
			for (int i = currentExperiment; i < currentExperiment + newApproximation; i++) {
				if (DataStore.getPoint(i).getPositionCheck()) {
					numberOfPointsInside++;
				}
				else {
					numberOfPointsOutside++;
				}
			}
			pi = (((double)(numberOfPointsInside))/((double)(numberOfPointsInside + numberOfPointsOutside)))*(4);
			currentExperiment += newApproximation;
			System.out.println(currentExperiment + ": " + String.format("%.9f", pi));
		}
		else {
			for (int i = currentExperiment; i < numberOfExperiments; i++) {
				if (DataStore.getPoint(i).getPositionCheck()) {
					numberOfPointsInside++;
				}
				else {
					numberOfPointsOutside++;
				}
			}
			pi = (((double)(numberOfPointsInside))/((double)(numberOfPointsInside + numberOfPointsOutside)))*(4);
			currentExperiment = numberOfExperiments;
			System.out.println(currentExperiment + ": " + String.format("%.9f", pi));
		}
	}

	public void run() {
		while (currentExperiment < numberOfExperiments) {
			checkApproximation();
		}
		
	}

}
