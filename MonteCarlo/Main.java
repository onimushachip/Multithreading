/* Lam Nguyen
 * CS 435
 * Project 4
 */

public class Main {

	public static void main(String[] args) {
		int numberOfExperiments = 1;
		int newApproximation = 1; // Number of experiments to create a new approximation
		int numberOfSimulationThread = 1;
		if (args.length < 3) {
			System.out.println("Please enter three parameters.");
		}
		else {
			numberOfExperiments = Integer.parseInt(args[1]);
			newApproximation = Integer.parseInt(args[2]);
			numberOfSimulationThread = Integer.parseInt(args[0]);
		}
		
		Printing testPrint = new Printing(newApproximation, numberOfExperiments);
		DataStore.setNumberOfExperiments(numberOfExperiments);
		DataStore.setNewApproximation(newApproximation);
		
		//Creating simulation threads
		Simulation[] arrayFrameSimulation = new Simulation[numberOfSimulationThread];
		Thread[] arrayThreadSimulation = new Thread[numberOfSimulationThread];
		
		for (int i = 0; i < arrayFrameSimulation.length; i++) {
			arrayFrameSimulation[i] = new Simulation();
			arrayThreadSimulation[i] = new Thread(arrayFrameSimulation[i]);
			arrayThreadSimulation[i].start();
		}
		Thread printingThread = new Thread(testPrint);
		
		for (int j = 0; j < arrayThreadSimulation.length; j++) {
			try {
				arrayThreadSimulation[j].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		printingThread.start();

		try {
			printingThread.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
