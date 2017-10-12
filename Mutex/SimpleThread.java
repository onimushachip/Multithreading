
public class SimpleThread implements Runnable {
	private int demandTime;
	
	public SimpleThread (int inputNumber) {
		demandTime = inputNumber;
	}
	
	public SimpleThread () {
		demandTime = 0;
	}
	
	public void setDemandNumber (int newNumber) {
		demandTime = newNumber;
	}
	
	public void run () {
		for (int i = 0; i < TimeData.getSecondArraySize(); i++) {
			if (demandTime == TimeData.getSecondTime(i)) {
				for (int j = 0; j < TimeData.getThirdArraySize(); j++) {
					if (demandTime == TimeData.getThirdTime(j)) {
						System.out.println (demandTime + " is a common meeting time.");
						TimeData.setCommonTimeExists();
					}
				}
			}
		}
	}
	
	
}
