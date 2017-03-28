import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import org.apache.commons.lang3.ArrayUtils;

public class Simulator {

	private Locale currentLocale;
	private NumberFormat numberFormatter;
	private int threes, fours, fives, sixes;
	private double totalCostOfTickets, earnigs, revenue;
	final static double COSTOFTICKET = 3;
	final static double PRIZEFORTHREES = 24;
	final static double PRIZEFORFOUR = 138.10;
	final static double PRIZEFORFIVES = 3512.70;
	private String simulationResults = "";		
	private String stringEnding;
	private int[] winningNumbers;
	
//	Constructor - sets simulation results
	Simulator(int simulationID, int numberOfTickets, int mainPrize){
		
		setSimulationResults(simulationID, numberOfTickets, mainPrize);
		
	}
	
	public String getSimulationResults(){
		return simulationResults;
	}

	public void setSimulationResults(int simulationID, int numberOfTickets, int mainPrize){
		
//		Integer formating
		currentLocale = new Locale("pl_PL");
		numberFormatter = NumberFormat.getNumberInstance(currentLocale);
		
//		Perform simulation
		simulateLotto(numberOfTickets);
		
//		Evaluating earnigns netto, costs, revenue
		totalCostOfTickets = numberOfTickets * COSTOFTICKET;
		earnigs = threes * PRIZEFORTHREES + fours * PRIZEFORFOUR + fives * PRIZEFORFIVES + sixes * mainPrize- totalCostOfTickets;
		revenue = threes * PRIZEFORTHREES + fours * PRIZEFORFOUR + fives * PRIZEFORFIVES + sixes * mainPrize;
		
//		Simulation result printed in Text Area
		simulationResults = 
				"Symulacja nr: " + simulationID + "\n" 
				+ "Nagroda g³ówna: " + numberFormatter.format(mainPrize) + "\nLiczba wys³anych losów: " + numberFormatter.format(numberOfTickets) + "\n"
				+ "Szczêœliwe numery to : " + Arrays.toString(winningNumbers) + "\n"
				+ "£¹cznie wydano na losy: " + numberFormatter.format(totalCostOfTickets) + " PLN\n"
				+ "£¹czna wygrana z kuponów: " + numberFormatter.format(revenue) + " PLN\n"
				+ "Zysk: " + numberFormatter.format(earnigs) + " PLN\n"
				+ "Trafiono: \n"
					+ threes + " trój" + getStringEnding(threes) + "\n" 
					+ fours + " czwór" + getStringEnding(fours) + "\n" 
					+ fives + " pi¹t" + getStringEnding(fives) + "\n" 
					+ sixes + " szóst" + getStringEnding(sixes) + "\n" ;
		
		if (sixes>0) {
			simulationResults = simulationResults +  "Gratulacje trafi³eœ szóstkê!";
		} else {
			simulationResults = simulationResults + "Powodzenia przy kolejnym losowaniu.";
		}

	}
	
//	Polish endings of numbers
	private String getStringEnding(int number){
		if (number == 1) {
			stringEnding = "ka";
		} else if (number >= 2 && number <=4 ){
			stringEnding = "ki";
		} else {
			stringEnding = "ek";
		}
		return stringEnding;
	}
	
	private void simulateLotto(int numberOfTickets){
		
//		Drawing winning numbers
		Drawing output = new Drawing();
		int winningNumbers[] = output.getDrawingResult();
		
//  	Sending tickets
		LottoTicket[] sentTickets = new LottoTicket[numberOfTickets];
		for(int i=0; i<numberOfTickets; i++){
			sentTickets[i] = new LottoTicket("Ticket no:" + (i+1));

//		Checking if ticket won one of the prizes
			for(int j=0;j<6;j++){
				
				if( ArrayUtils.contains(sentTickets[i].getPickedNumbers(), winningNumbers[j])){
					sentTickets[i].setNumberOfGuessedNumbers(sentTickets[i].getNumberOfGuessedNumbers() + 1);	
				} 
				
			} //2nd for loop
			
		} //1st for loop
		
//		Counting number of winning coupons
		for (int i=0; i<numberOfTickets; i++){
			if(sentTickets[i].getNumberOfGuessedNumbers()==3){
				threes++;
			}
			if(sentTickets[i].getNumberOfGuessedNumbers()==4){
				fours++;
			}
			if(sentTickets[i].getNumberOfGuessedNumbers()==5){
				fives++;
			}
			if(sentTickets[i].getNumberOfGuessedNumbers()==6){
				sixes = 1; //it is possible to win main prize only once
			}
		}
		
		
	}

}
