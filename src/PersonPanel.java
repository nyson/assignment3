import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * @author Magnus, Jonathan, Oskar
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
	JLabel nameStatusLabel = new JLabel("Obligatoriskt");
	JLabel streetStatusLabel = new JLabel("Obligatoriskt");
	JLabel zipStatusLabel = new JLabel("Obligatoriskt");
	JLabel cityStatusLabel = new JLabel("Obligatoriskt");
	JButton addButton = new JButton("Lägg till");
	JButton clearButton = new JButton("Rensa");

	// Reusable objects for design settings
	Dimension panelSize = new Dimension(300,100);
	Border labelBorder = new EtchedBorder();

	// Key listener
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
							getParent(), name + " lades in i databasen utan problem!", 
							"Namn inmatades", 
							JOptionPane.INFORMATION_MESSAGE);

				} catch (BadUserInputException ex){
					JOptionPane.showMessageDialog(
							getParent(), ex.getMessage(), "Felaktig inmatning", 
							JOptionPane.ERROR_MESSAGE);

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(
							getParent(),
							"Du misslyckades med att mata in ett nummer!\n"
									+ ex.getMessage(),	
									"Fel inmatning", JOptionPane.ERROR_MESSAGE);

				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(
							getParent(),
							"SQL-fel!\n" + ex.getMessage(),	
							"Trasig SQL", JOptionPane.ERROR_MESSAGE);

				}

			}
			else if (e.getSource() == clearButton) {
				update();
				updateStatusLabels();
			}
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
	void update(){
		nameField.setText("");
		zipField.setText("");
		streetField.setText("");
		cityField.setText("");
	}

	private void updateStatusLabels(){
		nameStatusLabel.setText("");
		zipStatusLabel.setText("");
		cityStatusLabel.setText("");
		streetStatusLabel.setText("");
		
		try {
			if(nameField.getText().isEmpty())
				nameStatusLabel.setText("Obligatoriskt");
			else if((new Persons()).exists(nameField.getText())){
				nameStatusLabel.setText("Namnet finns redan!");
			} else {

			}				 
		} catch (SQLException e) {
			nameStatusLabel.setText("SQL-fel!");			
		}

		if(streetField.getText().isEmpty())
			streetStatusLabel.setText("Obligatoriskt");

		if(zipField.getText().isEmpty())
			zipStatusLabel.setText("Obligatoriskt");
		else {
			try {
				Integer.parseInt(zipField.getText());
			} catch (NumberFormatException e) {
				zipStatusLabel.setText("Detta är inte ett heltal!");
			}
		}


		if(cityField.getText().isEmpty())
			cityStatusLabel.setText("Obligatoriskt"); 

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



















