import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;


public class Simulator {
	
	int numberOfTickets;
	int threes;
	int fours;
	int fives;
	int sixes;
	int sevens;
	double earnigs;
	
	public void simulateLotto(int numberOfTickets){
		
		Drawing output = new Drawing();
		int winningNumbers[] = output.getDrawingResult();
		
		LottoTicket[] sentTickets = new LottoTicket[numberOfTickets];

		System.out.println("LOTTO Winning Numbers: " + Arrays.toString(winningNumbers));
		System.out.println("Please wait for simulation results. \nIt can take a while depending on number of tickets sent.");
		
//Sent tickets
		for(int i=0; i<numberOfTickets; i++){
			sentTickets[i] = new LottoTicket("Ticket no:" + (i+1));


//Checking if ticket is paid
			for(int j=0;j<6;j++){
				
				if( ArrayUtils.contains(sentTickets[i].getPickedNumbers(), winningNumbers[j])){
					sentTickets[i].numberOfGuessedNumbers++;	
				} 
			} //2nd for loop
			
//Wyœwietlanie wyników			
//			System.out.print(sentTickets[i].getTicketID() + " ");			
//			System.out.println(Arrays.toString(sentTickets[i].getPickedNumbers())+ " trafi³ " +sentTickets[i].numberOfGuessedNumbers + " liczby");	

			
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
				sixes++;
			}
		}
		
//Earnigns netto
		earnigs=threes*24 + fours*138.10 + fives*3512.70 + sixes*20000000- numberOfTickets*3;
		System.out.println("Number of sent tickets " + numberOfTickets);
		System.out.println("Total money spent on tickets " + (numberOfTickets*3));
		System.out.println("Total money won " + (threes*24 + fours*138.10 + fives*3512.70 + sixes*20000000));
		System.out.println("Net money won: " + earnigs);
		System.out.println("Number of Threes: " + threes);
		System.out.println("Number of Fours: " + fours);
		System.out.println("Number of Fives: " + fives);
		System.out.println("Number of Sixes: " + sixes);

	}
	


}
