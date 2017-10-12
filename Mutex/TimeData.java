import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;



public class TimeData {
	private static ArrayList<Integer> firstTime = new ArrayList<Integer>();
	private static ArrayList<Integer> secondTime = new ArrayList<Integer>();
	private static ArrayList<Integer> thirdTime = new ArrayList<Integer>();
	private static boolean commonTimeExists = false;
	
public static void getData (String fileDirectory) {
	Scanner dataFile = null;
	int arraySize = 0;
	int currentArray = 0;
	
	try {
		dataFile = new Scanner(new File(fileDirectory));
//		System.out.println("open ok"); //Debug comment
	}
	catch (Exception e) {
		System.out.println("cannot open file");
	}
	
	//Getting data from an external file
	while (dataFile.hasNextInt()) {
		if (arraySize == 0) {
			arraySize = dataFile.nextInt();
			currentArray++;
		}
		if (currentArray == 1) {
			firstTime.add(dataFile.nextInt());
			arraySize--;
		}		
		if (currentArray == 2) {
			secondTime.add(dataFile.nextInt());
			arraySize--;
		}
		if (currentArray == 3) {
			if (arraySize != 0) {
				thirdTime.add(dataFile.nextInt());
				arraySize--;
			}
		}
	}
	
	dataFile.close();
}



public static int getFirstTime (int inputIndex) {
	return firstTime.get(inputIndex);
}

public static int getSecondTime (int inputIndex) {
	return secondTime.get(inputIndex);
}

public static int getThirdTime (int inputIndex) {
	return thirdTime.get(inputIndex);
}

public static int getFirstArraySize () {
	return firstTime.size();
}

public static int getSecondArraySize () {
	return secondTime.size();
}

public static int getThirdArraySize () {
	return thirdTime.size();
}

public static void setCommonTimeExists () {
	commonTimeExists = true;
}

public static boolean getCommonTimeExists () {
	return commonTimeExists;
}

}
