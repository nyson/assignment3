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
public class AccountPanel extends JPanel {
	
	String name = "Test Person";
	String street = "Test gata";
	int postno = 1234;
	String city = "Testaden";
	Person personToAdd = new Person(name, street, postno, city);

	private static final long serialVersionUID = 1L;
	// Graphical elements to be used 
	JLabel nameLabel = new JLabel("Namn");
	JLabel streetLabel = new JLabel("Gatuadress");
	JLabel zipLabel = new JLabel("Postnummer");
	JLabel cityLabel = new JLabel("Stad");
	JTextField nameField = new JTextField(10);
	JTextField streetField = new JTextField(10);
	JTextField zipField = new JTextField(10);
	JTextField cityField = new JTextField(10);
	JLabel nameStatusLabel = new JLabel("Saknas");
	JLabel streetStatusLabel = new JLabel("Saknas");
	JLabel zipStatusLabel = new JLabel("Saknas");
	JLabel cityStatusLabel = new JLabel("Saknas");
	JButton addButton = new JButton("Lägg till");
	JButton clearButton = new JButton("Rensa");
	

	// Reusable objects for design settings
	Dimension panelSize = new Dimension(300,100);
	Border labelBorder = new EtchedBorder();
	
	// Listener for this panels buttons
		ActionListener buttonListener = new ActionListener() {
			// skapar inre klass (actionlistener är ett interface)
			public void actionPerformed(ActionEvent e) {

				// which button was pressed?
				// check that right values are inserted
				if (e.getSource() == addButton){
					// Check user input
					checkUserInput();
				}
				else if (e.getSource() == clearButton)
			    	 streetField.setText("Clear");
				
			} // end of inner class
		}; // End of Listener for all buttons

	
	/**
	 * The panel where you can add new persons to the database
	 */
	public AccountPanel() {
	// Set the start settings for all components
		
		// Attach ActionListeners
		addButton.addActionListener(buttonListener);
		clearButton.addActionListener(buttonListener);
		
		// Design (border styles) 
		nameLabel.setBorder(labelBorder);
		streetLabel.setBorder(labelBorder);
		zipLabel.setBorder(labelBorder);
		cityLabel.setBorder(labelBorder);
		
		// Add components so the LayoutmManager can distribute them
		setLayout(new GridLayout(5,3)); // Use flow strategy to place components
		add(nameLabel); add(nameField); add(nameStatusLabel);
		add(streetLabel); add(streetField); add(streetStatusLabel);
		add(zipLabel); add(zipField); add(zipStatusLabel);
		add(cityLabel); add(cityField); add(cityStatusLabel);
		add(addButton);	add(clearButton);
		
		// Give this pane a border with a title
		setBorder(new TitledBorder("Lägg till nytt konto"));
		//setMaximumSize(panelSize);
		setVisible(false); // Start hidden
		
	}

	/**
	 * checks user input
	 */
	void checkUserInput(){
		if (nameField.getText() == "")
			nameStatusLabel.setText("obligatoriskt");
		if (streetField.getText() == "")
			nameStatusLabel.setText("obligatoriskt");
		if (zipField.getText() == "")
			nameStatusLabel.setText("obligatoriskt");
		if (cityField.getText() == "")
			nameStatusLabel.setText("obligatoriskt");
	}
	/**
	 * Gives the user some feed back
	 * @param answer
	 */
	void handleAnswer(String answer){
		JOptionPane.showMessageDialog(getParent(), answer);
	}

}
