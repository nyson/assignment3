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
import java.util.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

/**
 * @author Magnus
 *
 */
public class AccountPanel extends WeraPanel {
	
	String name = "Test Person";
	String street = "Test gata";
	int postno = 1234;
	String city = "Testaden";
	Person personToAdd = new Person(name, street, postno, city);
	
	String accountNumber;
	String holder;
	Account.Type type;
	double Saldo;

	private static final long serialVersionUID = 1L;
	// Graphical elements to be used 
	JLabel numberLabel = new JLabel("Kontonummer");
	JLabel holderLabel = new JLabel("Kontoägare");
	JLabel typeLabel = new JLabel("Kontotyp");
	JLabel balanceLabel = new JLabel("Saldo");
	
	
	JTextField numberField = new JTextField(10);
	JComboBox<String> holderBox = new JComboBox<String>();
	JComboBox<String> typeBox = new JComboBox<String>();
	JTextField balanceField = new JTextField(10);

	JLabel numberStatusLabel = new JLabel("Saknas");
	JLabel holderStatusLabel = new JLabel("Saknas");
	JLabel typeStatusLabel = new JLabel("Saknas");
	JLabel balanceStatusLabel = new JLabel("Saknas");
	
	JButton addButton = new JButton("Lägg till");
	JButton clearButton = new JButton("Rensa");
	

	// Reusable objects for design settings
	Dimension panelSize = new Dimension(300,100);
	Border labelBorder = new EtchedBorder();
	
	class BadUserInputException extends Exception{
		private static final long serialVersionUID = 4681301276310180115L;
		
	}
	
	// Listener for this panels buttons
	ActionListener buttonListener = new ActionListener() {
		// skapar inre klass (actionlistener är ett interface)
		public void actionPerformed(ActionEvent e) {

			// which button was pressed?
			// check that right values are inserted
			if (e.getSource() == addButton){
				// Check user input
				checkUserInput();
				
				try {
					accountNumber = numberField.getText();
					
/*					type = typeBox.getSelectedIndex();
					zip = Integer.parseInt(zipField.getText());
					city = cityField.getText();
					
					Persons pers = new Persons();
					pers.add(name, city, street, zip);
	*/				
				} catch (NumberFormatException ex) {

					JOptionPane.showMessageDialog(
							null,
							"Du misslyckades med att mata in ett nummer!\n"
							+ ex.getMessage(),	
							"Fel inmatning", JOptionPane.ERROR_MESSAGE);
/*				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(
							null,
							"SQL-fel!\n" + ex.getMessage(),	
							"Trasig SQL", JOptionPane.ERROR_MESSAGE);
					
*/				}					
			}
			else if (e.getSource() == clearButton) {
				numberField.setText("");
			}
				
			
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
		holderLabel.setBorder(labelBorder);
		numberLabel.setBorder(labelBorder);
		typeLabel.setBorder(labelBorder);
		balanceLabel.setBorder(labelBorder);
		
		// Add components so the LayoutmManager can distribute them
		setLayout(new GridLayout(5,3)); // Use flow strategy to place components
		add(numberLabel); add(numberField); add(numberStatusLabel);
		add(balanceLabel); add(balanceField); add(balanceStatusLabel);
		add(typeLabel); add(typeBox); add(typeStatusLabel);
		add(holderLabel); add(holderBox); add(holderStatusLabel);
		add(addButton);	add(clearButton);
		
		// populate type and holders
		String[] types = {"Sparkonto", "Lönekonto"};
		for(String t : types) {
			typeBox.addItem(t);
		}
		
		try {
			ArrayList<Person> persons = (new Persons()).getPersons() ;
			for(Person p : persons) {
				holderBox.addItem();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(
					null,
					"SQL-fel!\n" + e.getMessage(),	
					"Trasig SQL", JOptionPane.ERROR_MESSAGE);			
		}
		
		
		
		// Give this pane a border with a title
		setBorder(new TitledBorder("Lägg till nytt konto"));
		//setMaximumSize(panelSize);
		setVisible(false); // Start hidden
		
	}

	/**
	 * checks user input
	 */
	void checkUserInput(){
/*		if (nameField.getText() == "")
			nameStatusLabel.setText("obligatoriskt");
		if (streetField.getText() == "")
			nameStatusLabel.setText("obligatoriskt");
		if (zipField.getText() == "")
			nameStatusLabel.setText("obligatoriskt");
		if (cityField.getText() == "")
			nameStatusLabel.setText("obligatoriskt");
*/	}
	/**
	 * Gives the user some feed back
	 * @param answer
	 */
	void handleAnswer(String answer){
		JOptionPane.showMessageDialog(getParent(), answer);
	}

}
