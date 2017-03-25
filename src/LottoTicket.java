import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

public class LottoTicket{
	
	String ticketID;
	int numberOfGuesses=6;
	int numberOfGuessedNumbers = 0;
	int[] chosenNumbers = new int[numberOfGuesses];
	
	
	public void setNumberOfGuesses(int numberOfGuesses){
		this.numberOfGuesses = numberOfGuesses;
	}

	public int[] pickNumbers() {
		for (int i=0; i<this.numberOfGuesses; i++){
			boolean isNumberOccupied = true;
			do {
				int pickedNumber = (int) (Math.random()*49)+1;
				if (ArrayUtils.contains(chosenNumbers, pickedNumber) ){
				isNumberOccupied = true;
				} else {
				chosenNumbers[i] = pickedNumber;
				isNumberOccupied = false;
				}
			} while (isNumberOccupied == true);
		}
		Arrays.sort(chosenNumbers);
		return chosenNumbers;
	}
	
	public String getTicketID() {
		return this.ticketID;
	}
	
	public int[] getPickedNumbers() {
		return chosenNumbers;
	}
	
	public LottoTicket(String ticketID){
		this.ticketID = ticketID;
		pickNumbers();
	}
	
}
