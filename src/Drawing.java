
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

public class Drawing {

	int[] winningNumbers = new int[6];
	
	public int[] getDrawingResult() {
	for(int i=0;i<6;i++){
		boolean isNumberOccupied = true;
		do {
		int drawedNumber = (int) (Math.random()*49)+1;
		if (ArrayUtils.contains(winningNumbers, drawedNumber) ){
			isNumberOccupied = true;
		} else {
			winningNumbers[i] = drawedNumber;
			isNumberOccupied = false;
		}
		} while (isNumberOccupied == true);
	}
	Arrays.sort(winningNumbers);
	return winningNumbers;
	}

}
