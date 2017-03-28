
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.IllegalFormatException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class UserView extends JFrame {
	
	final static int maxGap = 20;
	private String[] labels = {" Nagroda g³ówna (mln): ",  " Liczba losów na loterii: "};
	private int numPairs = labels.length;
	private JPanel buttonsPanel, inputsPanel , resultsPanel;
	private JButton startSimulationButton, clearButton;
	private JTextArea JTextAreaResultsOfSimulation;
	private JTextField[] textFields = new JTextField[numPairs];;
	private int simulationID = 0;
	
    public UserView(String name) {
        super(name);
        setResizable(true);
    }

	private void addComponentsToPane(final Container pane){	
		
//		inputsPanel
		inputsPanel = new JPanel();
		inputsPanel.setLayout( new SpringLayout() );
		inputsPanel.setBorder( new EmptyBorder(5, 5, 5, 5) );
		
		
//      Create and populate inputsPanel with JLabels and JText.
        for (int i = 0; i < numPairs; i++) {
            JLabel label = new JLabel(labels[i], JLabel.TRAILING);
            inputsPanel.add(label);
            textFields[i] = new JTextField(10);
            label.setLabelFor(textFields[i]);
            inputsPanel.add(textFields[i]);
        }
        
        SpringUtilities.makeCompactGrid(inputsPanel,
                                        numPairs, 2, //rows, cols
                                        6, 6,        //initX, initY
                                        6, 6);       //xPad, yPad
		
//		Results Panel
		resultsPanel = new JPanel();
		resultsPanel.setLayout( new BoxLayout(resultsPanel, BoxLayout.Y_AXIS) );
		resultsPanel.setBorder( new EmptyBorder(5, 5, 5, 5) );
		JTextAreaResultsOfSimulation = new JTextArea(" Wynik symulacji: \n", 20, 20);
		resultsPanel.add(JTextAreaResultsOfSimulation);

		
//		buttonsPanel
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout( new GridLayout(1, 2, 5, 5 ) );
		buttonsPanel.setBorder( new EmptyBorder(5, 5, 5, 5) );
		
		startSimulationButton = new JButton("Symulacja");
		clearButton = new JButton("Reset");
		
//		Action listeners
		ListenForButton lForButton = new ListenForButton();
		startSimulationButton.addActionListener(lForButton);
		clearButton.addActionListener(lForButton);
		
		buttonsPanel.add(startSimulationButton);
		buttonsPanel.add(clearButton);
		
        pane.add(inputsPanel, BorderLayout.NORTH);
        pane.add(resultsPanel, BorderLayout.CENTER);
        pane.add(buttonsPanel, BorderLayout.SOUTH);

	}

    static void createAndShowGUI() {
        //Create and set up the window.
    	UserView frame = new UserView("Symulator LOTTO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set up the content pane.
        frame.addComponentsToPane( frame.getContentPane() );
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }	
    
    private class ListenForButton implements ActionListener{
    	
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == startSimulationButton){
				
				try{
		            int mainPrize = ( Integer.parseInt( textFields[0].getText() ) ) * 1000000;
		            int numberOfTickets = Integer.parseInt( textFields[1].getText() );

		            
//		            Alert due to 3 reasons: 
//		            	1. Too big number of tickets may cause stock overflow. 
//		            	2. Number of tickets can't be below 1
//		            	3. Main Prize can't be below 1
		            if (numberOfTickets > 1000000 ||  numberOfTickets< 1 || mainPrize< 1 || mainPrize < 2000000)
		            {
		            	JOptionPane.showMessageDialog(JTextAreaResultsOfSimulation, "WprowadŸ liczbê losów od 1 od 1 000 000 oraz nagrodê g³ówn¹ równ¹ conajmniej 2 mln.", "Niepoprawne wartoœci", JOptionPane.WARNING_MESSAGE);
		            } else {
		            	simulationID += 1;
			            Simulator theSimulation = new Simulator(simulationID, numberOfTickets, mainPrize);
			            theSimulation.getSimulationResults();

			            JTextAreaResultsOfSimulation.setText( theSimulation.getSimulationResults() );
		            }
		            

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(JTextAreaResultsOfSimulation, "WprowadŸ poprawne liczby.", "B³êdny typ danych", JOptionPane.WARNING_MESSAGE);
				} catch (IllegalFormatException e2) {
					JOptionPane.showMessageDialog(JTextAreaResultsOfSimulation, "WprowadŸ poprawne liczby.", "B³êdny typ danych", JOptionPane.WARNING_MESSAGE);
				}
				
			}
			if(e.getSource() == clearButton){
				JTextAreaResultsOfSimulation.setText(" Wynik symulacji: \n");
		        for (int i = 0; i < numPairs; i++) {
		        	textFields[i].setText("");
		        }

			}
			
		}
		
    }
    
}
