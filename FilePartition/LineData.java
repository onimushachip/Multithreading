import java.util.concurrent.locks.ReentrantLock;


public class LineData {
	private int lineNumber;
	private int matches;
	private String originalLine;
	private ReentrantLock theLock;
	
	public LineData (String initialLine, int inputLineNumber) {
		originalLine = initialLine;
		lineNumber = inputLineNumber;
		theLock = new ReentrantLock();
	}
	
	public String getOriginalLine() {
		return originalLine;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	
	public int getMatches() {
		return matches;
	}
	
	public void updateMatches() {
		theLock.lock();
		matches += 1;
		theLock.unlock();
	}

}
