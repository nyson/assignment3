/* READ THIS !!
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
public class ConnectDBPanel extends WeraPanel {

	private static final long serialVersionUID = 1L;
	// Graphical elements to be used 
	JLabel choiceLabel = new JLabel("Namn");
	JTextField choiceField = new JTextField(10);
	JLabel choiceStatusLabel = new JLabel("Saknas");
	JButton addButton = new JButton("Lägg till");
	JButton clearButton = new JButton("Rensa");

	// Reusable objects for settings
	Dimension panelSize = new Dimension(300,100);
	
	/**
	 *  Listeners
	 */
	ActionListener buttonListener = new ActionListener() {
		// Creates an inner class
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == clearButton){
				choiceField.setText("");
			}
		} // end of inner class
	}; // End of buttonListener

	
	
	/**Constructor
	 * The panel where you can add persons to the database
	 */
	public ConnectDBPanel() {
		
		// Attach Listeners
		addButton.addActionListener(buttonListener);
		clearButton.addActionListener(buttonListener);
		
		// Design issues
		choiceLabel.setBorder(new EtchedBorder());
		
		// Add components so the LayoutmManager can distribute them
		setLayout(new GridLayout(2,3)); // Use flow strategy to place components
		add(choiceLabel); add(choiceField); add(choiceStatusLabel);
		add(addButton);	add(clearButton);
		
		// Give this pane a border with a title
		setBorder(new TitledBorder("Anslut till databas"));
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
