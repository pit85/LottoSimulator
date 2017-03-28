import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

public class LottoTicket{
	
	String ticketID;
	final int NUMBEROFGUESSES = 6;
	private int numberOfGuessedNumbers = 0;
	private int[] pickedNumbers = new int[NUMBEROFGUESSES];
	
//	Constructor class which picks LOTTO Numbers
	LottoTicket(String ticketID){
		this.ticketID = ticketID;
		setPickedNumbers();
	}
	
	public int[] getPickedNumbers() {
		return pickedNumbers;
	}

	public int[] setPickedNumbers() {
		for (int i=0; i<NUMBEROFGUESSES; i++){
			boolean isNumberOccupied = true;
			do {
				int pickedNumber = (int) (Math.random()*49 + 1);
				if (ArrayUtils.contains(pickedNumbers, pickedNumber) ){
				isNumberOccupied = true;
				} else {
					pickedNumbers[i] = pickedNumber;
				isNumberOccupied = false;
				}
			} while (isNumberOccupied == true);
		}
		Arrays.sort(pickedNumbers);
		return pickedNumbers;
	}
	
	public String getTicketID() {
		return this.ticketID;
	}
	
	public void setTicketID(String ticketID) {
		this.ticketID = ticketID;
	}
	
	public int getNumberOfGuessedNumbers() {
		return numberOfGuessedNumbers;
	}

	public void setNumberOfGuessedNumbers(int numberOfGuessedNumbers) {
		this.numberOfGuessedNumbers = numberOfGuessedNumbers;
	}
	
}
