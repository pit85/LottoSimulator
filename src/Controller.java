import java.util.Scanner;

public class Controller {
	
	Scanner scannSimulateAgain;
	
	public void startSimulation(){
		
		scannSimulateAgain = new Scanner(System.in);
		String answerDoYouWantToSimulate;
		
		boolean continueSimulation = true;
		
		while(continueSimulation){
			
			View theView = new View();
			View.askForNumberOfTickets();
			View.askForMainPrize();
			Simulator theSimulation = new Simulator();
			theSimulation.simulateLotto(theView.getnumberOfTickets(), theView.getMainPrize());
			
			System.out.println("Do you want to simulate again? (Y/N)");
			answerDoYouWantToSimulate = scannSimulateAgain.nextLine();
			if (!"y".equals(answerDoYouWantToSimulate.toLowerCase())){
				System.out.println("Simulation stopped");
				break;
			}
			
		}
		
	}

}
