import java.util.Scanner;

public class View {
	
	private static int numberOfTickets, mainPrize;
	private static Scanner scanner;

	
	public static int askForNumberOfTickets(){
		
		scanner = new Scanner(System.in);
		
		while (true) {
			
			try{
				System.out.println("Enter number of tickets you wish to send: ");
				numberOfTickets = scanner.nextInt();
				if (numberOfTickets > 5000000){
					System.out.println("Too many tickets. Enter number below 5 000 000");
					continue;
				} else {
				return numberOfTickets;
				}
				
			} catch (java.util.InputMismatchException e){
				System.out.println("Error. Incorrect number.");
				scanner.next();
			} 
		}
	}
	
	public static int askForMainPrize(){
		
		scanner = new Scanner(System.in);
		
		while (true) {
			
			try{
				System.out.println("Enter amount of main prize in LOTTO: ");
				mainPrize = scanner.nextInt();
				return mainPrize;
				
			} catch (java.util.InputMismatchException e){
				System.out.println("Error. Incorrect number.");
				scanner.next();
			} 
		}
	}
	
	public int getnumberOfTickets(){
		return numberOfTickets;
	}
	
	public int getMainPrize(){
		return mainPrize;
	}
	
}
