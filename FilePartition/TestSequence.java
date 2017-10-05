import java.util.ArrayList;


public class TestSequence implements Runnable {
	private static String prefix;
	private static String suffix;
	private ArrayList<LineData> lineDataList;
	private int numberOfGaps;
	
	public TestSequence (String prefixInput,
							String suffixInput,
							ArrayList<LineData> lineListInput,
							int numberOfGapsInput) {
		prefix = prefixInput;
		suffix = suffixInput;
		lineDataList = lineListInput;
		numberOfGaps = numberOfGapsInput + prefix.length();
	}


	@Override
	//The main method to check the entire file for each thread
	public void run() {
		for (int indexOfLine = 0; indexOfLine < lineDataList.size(); indexOfLine++) {
			String inputCopy = lineDataList.get(indexOfLine).getOriginalLine(); //Getting a copy a line
			String checkedInput = ""; //Initiate a line reflecting the status of it after checked
			int numberOfCharacters = numberOfGaps; //Number of gaps between prefix and suffix
			checkedInput = inputCopy;
			//Check to see if the line matches the given LGS
			while (checkedInput.contains(prefix) && checkedInput.contains(suffix)) {
				int indexOfPrefix = checkedInput.indexOf(prefix);
				int indexOfSuffix = checkedInput.indexOf(suffix);
				//Checking the situation when prefix and suffix are the same
				if (prefix.equals(suffix)) {
					try {
						if (checkedInput.substring(indexOfPrefix + numberOfCharacters, 
								indexOfPrefix + numberOfCharacters + suffix.length()).equals(suffix)){
							lineDataList.get(indexOfLine).updateMatches(); //Increment the matches in LineData object
							for (int i = indexOfPrefix; i < (indexOfPrefix + prefix.length()); i++) {
								checkedInput = checkedInput.substring(0, i) + " " + checkedInput.substring(i + 1, inputCopy.length());
							}		
						}
						else {
							for (int i = indexOfPrefix; i < (indexOfPrefix + prefix.length()); i++) {
								checkedInput = checkedInput.substring(0, i) + " " + checkedInput.substring(i + 1, inputCopy.length());
							}		
						}
					}
					catch (Exception e) {
						break;
					}
				}
				//Checking the line when prefix and suffix are different
				else {
					//Increment the matches if the difference of prefix and suffix index corresponding to numberOfGaps
					if (numberOfCharacters == (indexOfSuffix - indexOfPrefix)) {
						lineDataList.get(indexOfLine).updateMatches(); //
						for (int i = indexOfPrefix; i < (indexOfPrefix + prefix.length()); i++) {
							checkedInput = checkedInput.substring(0, i) + " " + checkedInput.substring(i + 1, inputCopy.length());
						}
						for (int i = indexOfSuffix; i < (indexOfSuffix + suffix.length()); i++) {
							checkedInput = checkedInput.substring(0, i) + " " + checkedInput.substring(i + 1, inputCopy.length());
						}
					//Check the situation when the prefix appears multiple times continuously
					}
					else {
						try {
							while (checkedInput.contains(prefix)) {
								indexOfPrefix = checkedInput.indexOf(prefix);
								if (checkedInput.substring(indexOfPrefix + numberOfCharacters, 
										indexOfPrefix + numberOfCharacters + suffix.length()).equals(suffix)){
									lineDataList.get(indexOfLine).updateMatches(); //
									for (int i = indexOfPrefix; i < (indexOfPrefix + prefix.length()); i++) {
										checkedInput = checkedInput.substring(0, i) + " " + checkedInput.substring(i + 1, inputCopy.length());
									}
								}
								else {
									for (int i = indexOfPrefix; i < (indexOfPrefix + prefix.length()); i++) {
										checkedInput = checkedInput.substring(0, i) + " " + checkedInput.substring(i + 1, inputCopy.length());
									}
								}
							}
						}
						catch (Exception e) {
							//Continues to work on
						}
						for (int i = indexOfSuffix; i < (indexOfSuffix + suffix.length()); i++) {
							checkedInput = checkedInput.substring(0, i) + " " + checkedInput.substring(i + 1, inputCopy.length());
						}
					}
				}
			}
		}
	}
}
