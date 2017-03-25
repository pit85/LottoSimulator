import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import org.apache.commons.lang3.ArrayUtils;

public class Simulator {
	
	int numberOfTickets, threes, fours, fives, sixes, sevens;

	Locale currentLocale;
	NumberFormat numberFormatter;
	
	final static double COSTOFTICKET = 3;
	final static double THREESTOTALNUMBER = 24;
	final static double FOURSTOTALNUMBER = 138.10;
	final static double FIVESTOTALNUMBER = 3512.70;
	
	double totalCostOfTickets, earnigs, revenue, mainPrize;

	
	public void simulateLotto(int numberOfTickets, int mainPrize){
		
		currentLocale = new Locale("pl_PL");
		numberFormatter = NumberFormat.getNumberInstance(currentLocale);
		
		Drawing output = new Drawing();
		int winningNumbers[] = output.getDrawingResult();
		
		LottoTicket[] sentTickets = new LottoTicket[numberOfTickets];
		
		if(numberOfTickets>100000){
			System.out.println("LOTTO Winning Numbers: " + Arrays.toString(winningNumbers));
			System.out.println("Please wait for simulation results. \nIt can take a while depending on number of tickets you sent.");
		}

//Sent tickets
		for(int i=0; i<numberOfTickets; i++){
			sentTickets[i] = new LottoTicket("Ticket no:" + (i+1));


//Checking if ticket is paid
			for(int j=0;j<6;j++){
				
				if( ArrayUtils.contains(sentTickets[i].getPickedNumbers(), winningNumbers[j])){
					sentTickets[i].numberOfGuessedNumbers++;	
				} 
			} //2nd for loop
			
		} //1st for loop
		
//number of winning coupons
		for (int i=0; i<numberOfTickets; i++){
			if(sentTickets[i].numberOfGuessedNumbers==3){
				threes++;
			}
			if(sentTickets[i].numberOfGuessedNumbers==4){
				fours++;
			}
			if(sentTickets[i].numberOfGuessedNumbers==5){
				fives++;
			}
			if(sentTickets[i].numberOfGuessedNumbers==6){
				sixes = 1; //it is possible to win only once
			}
		}
		
//Earnigns netto
		totalCostOfTickets = numberOfTickets * COSTOFTICKET;
		earnigs = threes * THREESTOTALNUMBER + fours * FOURSTOTALNUMBER + fives * FIVESTOTALNUMBER + sixes * mainPrize- totalCostOfTickets;
		revenue = threes * THREESTOTALNUMBER + fours * FOURSTOTALNUMBER + fives * FIVESTOTALNUMBER + sixes * mainPrize;

		System.out.println("Number of tickets sent: " + numberOfTickets + ". Price of one ticket: 3 PLN.");
		System.out.println("Total money spent on tickets: " + numberFormatter.format(totalCostOfTickets) + " PLN");
		System.out.println("Total money won: " + numberFormatter.format(revenue) + " PLN");
		System.out.println("Net money won: " + numberFormatter.format(earnigs) + " PLN");
		System.out.println("Number of Threes: " + threes);
		System.out.println("Number of Fours: " + fours);
		System.out.println("Number of Fives: " + fives);
		System.out.println("Number of Sixes: " + sixes);

	}
	


}
