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
public class TransferPanel extends WeraPanel {
	
	private static final long serialVersionUID = 1L;
	// Graphical elements to be used 
	JLabel fromAccountLabel = new JLabel("Från konto");
	JLabel toAcountLabel = new JLabel("Till konto");
	JLabel amountLabel = new JLabel("Summa");
	JComboBox<String> fromAccountComboBox = new JComboBox<String>();
	JComboBox<String> toAccountComboBox = new JComboBox<String>();
	JTextField amountField = new JTextField(10);
	JLabel fromAccountStatusLabel = new JLabel("");
	JLabel toAccountStatusLabel = new JLabel("");
	JLabel amountStatusLabel = new JLabel("");
	JButton transferButton = new JButton("");
	JButton updateButton = new JButton("");
	

	// Reusable objects for design settings
	Dimension panelSize = new Dimension(300,100);
	Border labelBorder = new EtchedBorder();
	
	// Listener for this panels buttons
		ActionListener buttonListener = new ActionListener() {
			// skapar inre klass (actionlistener är ett interface)
			public void actionPerformed(ActionEvent e) {

				// which button was pressed?
				if (e.getSource() == transferButton){
/*					name = fromAccountComboBox.getText();
					street = toAccountComboBox.getText();
					postno = Integer.parseInt(amountField.getText());
					city = cityField.getText();
*/				}
				else if (e.getSource() == updateButton){
			    	// toAccountComboBox.("Clear");
				}
			} // end of inner class
		}; // End of Listener for all buttons

	
	/**
	 * The panel where you can add new persons to the database
	 */
	public TransferPanel() {
	// Set the start settings for all components
		
		// Attach ActionListeners
		transferButton.addActionListener(buttonListener);
		updateButton.addActionListener(buttonListener);
		
		// Design (border styles) 
		fromAccountLabel.setBorder(labelBorder);
		toAcountLabel.setBorder(labelBorder);
		amountLabel.setBorder(labelBorder);
		
		// Add components so the LayoutmManager can distribute them
		setLayout(new GridLayout(5,3)); // Use flow strategy to place components
		add(fromAccountLabel); add(fromAccountComboBox); add(fromAccountStatusLabel);
		add(toAcountLabel); add(toAccountComboBox); add(toAccountStatusLabel);
		add(amountLabel); add(amountField); add(amountStatusLabel);
		add(transferButton);	add(updateButton);
		
		// Give this pane a border with a title
		setBorder(new TitledBorder("Lägg till ny överföring"));
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
