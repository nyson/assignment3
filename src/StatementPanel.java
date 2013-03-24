/* READ THIS!!
 * All this file does is
 * A: Being a CPanel WITHIN the main CFrame i.e. Start.java
 * B: Handles the input from the user, filling a form
 * C: Saving the values in some variables on the press of a button
 * When that button is pressed Start.java 
 * 		1. reads these values
 * 		2. querys the Bank object
 * 		3. and returns an answer to this CPanel
 * D: Displays the answer to the user (and clears the form)
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

/**
 * @author Magnus
 *
 */
public class StatementPanel extends WeraPanel {
	private static final long serialVersionUID = 1L; // just to shut eclipse up
	
	// Variables
	String account = "";
	Accounts accounts = null;

	// Graphical elements to be used
	JLabel accountLabel = new JLabel("Konto");
	String[] lista = {"ett","två"};
	JList<String> accountList = new JList<String>(lista);
	JButton showStatementButton = new JButton("Visa");
	JButton clearButton = new JButton("Rensa");
	
	// Create the statement window
	StatementFrame statementFrame = new StatementFrame();
	
	// Reusable objects for design settings
	Dimension panelSize = new Dimension(300,100);
	Border labelBorder = new EtchedBorder();
	
	// Listener for this panels buttons
		ActionListener buttonListener = new ActionListener() {
			// Creates a inner class
			public void actionPerformed(ActionEvent e) {

				// Which button was pressed?
				if (e.getSource() == showStatementButton){ // showStatement
					// show the statement frame
					// OBS! BEHÖVER LADDAS MED NYA DATA FÖRST!
					statementFrame.setVisible(true);
				}
				else if (e.getSource() == clearButton) // clear
			    	 System.out.println("Remove me, i'm not worthy!!");
				
			} // end of inner class
		}; // End of Listener for this panels buttons

	
	/**
	 * The panel where you can add new persons to the database
	 */
	public StatementPanel() {
	// Set the start settings for all components
		
		// Attach ActionListeners
		showStatementButton.addActionListener(buttonListener);
		clearButton.addActionListener(buttonListener);
		
		// Design (border styles) 
		accountLabel.setBorder(labelBorder);
		
		// Add components so the LayoutmManager can distribute them
		setLayout(new GridLayout(2,2)); // Use flow strategy to place components
		add(accountLabel); add(accountList); 
		add(showStatementButton);	add(clearButton);
		
		// Give this pane a border with a title
		setBorder(new TitledBorder("Välj konto"));
		//setMaximumSize(panelSize);
		setVisible(false); // Start hidden
		
	}

	/**
	 * Gives the user some feed back
	 * @param answer
	 */
	void handleAnswer(String answer){
		JOptionPane.showMessageDialog(getParent(), answer);
	}
}
