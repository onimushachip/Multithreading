/* CS 435
 * Project 2 
 * Lam Nguyen
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	
	public static void main(String[] args) {
		Scanner dataFile = null;
		ArrayList<String> lineList = new ArrayList<>();
		ArrayList<LineData> lineDataList = new ArrayList<>();
		String line = "";
		String prefix = "";
		String minMax = "";
		String suffix = "" ;
		int min = 0;
		int max = 0;
		int numberOfGaps = 0;
		if (args.length < 4) {
			System.out.println("Please enter the directory to your data file, prefix, MinMax, suffix");
		}
		else {
			prefix = args[1];
			minMax = args[2];
			suffix = args[3];
			//Getting data from an external file
			try {
				dataFile = new Scanner(new File(args[0]));
			}
			catch (Exception e) {
				System.out.println("cannot open file");
				System.out.println(args[0]);
			}
			//Parsing min and max from command line argument
			if (minMax.contains("..")) {
				min = Integer.parseInt(minMax.substring(0, minMax.indexOf("..")));
				max = Integer.parseInt(minMax.substring(minMax.indexOf("..") + 2, minMax.length()));
			}
			else {
				System.out.println("Please input the valid format for Min and Max");
				min = 0;
				max = 0;
			}
			numberOfGaps = max - min + 1;
			
			//Getting each line in the external file as input
			while (dataFile.hasNext()) {
				line = dataFile.nextLine();
					lineList.add(line);
					line = "";
			}
			
			//Storing all of the file in a list of objects that have corresponding info in each one
			for (int i = 0; i < lineList.size(); i++) {
				lineDataList.add(new LineData(lineList.get(i), i));
			}
		}
		

		//Create a frame and give it to each thread
		TestSequence[] arrayFrame = new TestSequence[numberOfGaps];
		Thread[] arrayThread = new Thread[numberOfGaps];
		
		for (int i = 0; i < arrayFrame.length; i++) {
			arrayFrame[i] = new TestSequence(prefix, suffix, lineDataList, i + min);
			arrayThread[i] = new Thread(arrayFrame[i]);
			arrayThread[i].start();
		}
		
		for (int j = 0; j < arrayThread.length; j++) {
			try {
				arrayThread[j].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//Output the results with the original line
		for (int i = 0; i < lineDataList.size(); i++) {
			System.out.print(i + ", ");
			System.out.print(lineDataList.get(i).getMatches() + " matches");
			System.out.print(" " + lineDataList.get(i).getOriginalLine() + "\n");
		}
	}
	

}
