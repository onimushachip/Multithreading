/*
 * 
 */

public class main {

	public static void main(String[] args) {
		
		if (args.length == 0) {
			System.out.println("Please enter the directory to your data file.");
		}
		else {
			//Getting data from an external file
			TimeData.getData(args[0]);
		}
		
		
		//Create a frame and give it to each thread
		SimpleThread[] arrayFrame = new SimpleThread[TimeData.getFirstArraySize()];
		Thread[] arrayThread = new Thread[TimeData.getFirstArraySize()];
		
		for (int i = 0; i < TimeData.getFirstArraySize(); i++) {
			arrayFrame[i] = new SimpleThread(TimeData.getFirstTime(i));
			arrayThread[i] = new Thread(arrayFrame[i]);
			arrayThread[i].start();
		}
		
		for (int j = 0; j < TimeData.getFirstArraySize(); j++) {
			try {
				arrayThread[j].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if (TimeData.getCommonTimeExists() == false) {
			System.out.println ("There is no common meeting time.");
		}
	}

}
