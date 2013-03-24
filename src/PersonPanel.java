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
import java.sql.SQLException;

/**
 * @author Magnus
 *
 */
public class PersonPanel extends WeraPanel {

	String name = "";
	String street = "";
	int zip = 0;
	String city = "";
	Person personToAdd = new Person(name, street, zip, city);

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
	KeyListener updateListener = new KeyListener() {
        public void keyTyped(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {}
        
        public void keyReleased(KeyEvent e) {
        	updateStatusLabels();
        }
	};

	// Listener for this panels buttons
	ActionListener buttonListener = new ActionListener() {
		// creates an inner class
		public void actionPerformed(ActionEvent e) {

			// which button was pressed?
			if (e.getSource() == addButton){
				try {
					checkUserInput();

					name = nameField.getText();
					street = streetField.getText();
					zip = Integer.parseInt(zipField.getText());
					city = cityField.getText();

					Persons pers = new Persons();
					pers.add(name, city, street, zip);

					JOptionPane.showMessageDialog(
							null, name + " lades in i databasen utan problem!", 
							"Namn inmatades", 
							JOptionPane.INFORMATION_MESSAGE);

				} catch (BadUserInputException ex){
					JOptionPane.showMessageDialog(
							null, ex.getMessage(), "Felaktig inmatning", 
							JOptionPane.ERROR_MESSAGE);

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(
							null,
							"Du misslyckades med att mata in ett nummer!\n"
									+ ex.getMessage(),	
									"Fel inmatning", JOptionPane.ERROR_MESSAGE);

				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(
							null,
							"SQL-fel!\n" + ex.getMessage(),	
							"Trasig SQL", JOptionPane.ERROR_MESSAGE);

				}

			}
			else if (e.getSource() == clearButton)
				streetField.setText("Clear");

		} // end of inner class
	}; // End of Listener for all buttons


	/**
	 * The panel where you can add new persons to the database
	 */
	public PersonPanel() {
		// Set the start settings for all components

		// Attach ActionListeners
		addButton.addActionListener(buttonListener);
		clearButton.addActionListener(buttonListener);

		nameField.addKeyListener(updateListener);
		zipField.addKeyListener(updateListener);
		streetField.addKeyListener(updateListener);
		cityField.addKeyListener(updateListener);
		
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
		setBorder(new TitledBorder("Lägg till ny kontoinnehavare"));
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

	private void updateStatusLabels(){
		nameStatusLabel.setText("");
		zipStatusLabel.setText("");
		cityStatusLabel.setText("");
		streetStatusLabel.setText("");
		
		try {
			if(nameField.getText().isEmpty())
				nameStatusLabel.setText("Saknas");
			else if((new Persons()).exists(nameField.getText())){
				nameStatusLabel.setText("Namnet finns redan!");
			} else {

			}				 
		} catch (SQLException e) {
			nameStatusLabel.setText("SQL-fel!");			
		}

		if(streetField.getText().isEmpty())
			streetStatusLabel.setText("Saknas");

		if(zipField.getText().isEmpty())
			zipStatusLabel.setText("Saknas");
		else {
			try {
				Integer.parseInt(zipField.getText());
			} catch (NumberFormatException e) {
				zipStatusLabel.setText("Detta är inte ett heltal!");
			}
		}


		if(cityField.getText().isEmpty())
			cityStatusLabel.setText("Saknas"); 

	}

	private void checkUserInput() throws BadUserInputException,
	SQLException{
		if(nameField.getText().isEmpty() 
				|| streetField.getText().isEmpty()
				|| zipField.getText().isEmpty() 
				|| cityField.getText().isEmpty())
			throw new BadUserInputException("Du har tomma fält!");


		if((new Persons()).exists(nameField.getText()))
			throw new BadUserInputException
			("Personen existerar redan i databasen!");

		try {
			Integer.parseInt(zipField.getText());
		} catch (NumberFormatException e) {
			throw new BadUserInputException("Postnumret är inte ett heltal!");
		}
	}	
}



















