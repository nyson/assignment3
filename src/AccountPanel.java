import javax.swing.*;
import java.util.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

/**
 * @author Magnus, Jonathan, Oskar
 *
 */
public class AccountPanel extends WeraPanel {
	private static final long serialVersionUID = 1L;

	// Variables
	String accountNumber;
	String holder;
	Account.Type type;
	double balance;	

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
	JLabel holderStatusLabel = new JLabel("");
	JLabel typeStatusLabel = new JLabel("");
	JLabel balanceStatusLabel = new JLabel("Saknas");
	
	JButton addButton = new JButton("Lägg till");
	JButton clearButton = new JButton("Rensa");
	

	// Reusable objects for design settings
	Dimension panelSize = new Dimension(300,100);
	Border labelBorder = new EtchedBorder();
	

	
	KeyListener updateListener = new KeyListener() {
        public void keyTyped(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {}
        
        public void keyReleased(KeyEvent e) {
        	printStatus();
        }
	};
    
	// Listener for this panels buttons
	ActionListener buttonListener = new ActionListener() {
		// create inner class
		public void actionPerformed(ActionEvent e) {

			// which button was pressed?
			// check that right values are inserted
			if (e.getSource() == addButton){
				// Check user input
			
				try {
					checkUserInput();
					accountNumber = numberField.getText();
					balance = Double.parseDouble(balanceField.getText());
					if(typeBox.getSelectedItem() == "Sparkonto")
						type = Account.Type.SAVINGS;
					else
						type = Account.Type.WAGE;
					
					holder = holderBox.getSelectedItem().toString();
					
					Accounts accs = new Accounts();
					
					accs.addAccount(holder, accountNumber, balance, type);
					
				} catch(BadUserInputException ex) {
					JOptionPane.showMessageDialog(
						null,
						"Korrigera trasiga fält och pröva sedan igen!\n"
						+ ex.getMessage(),	
						"Fel inmatning", JOptionPane.ERROR_MESSAGE);
					
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
			else if (e.getSource() == clearButton) {
				numberField.setText("");
				balanceField.setText("0.00");
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

		// attach keylistener
		numberField.addKeyListener(updateListener);
		balanceField.addKeyListener(updateListener);
		
		// Design (border styles) 
		holderLabel.setBorder(labelBorder);
		numberLabel.setBorder(labelBorder);
		typeLabel.setBorder(labelBorder);
		balanceLabel.setBorder(labelBorder);
		
		// Add components so the LayoutmManager can distribute them
		// setLayout(new GridLayout(5,3))
		setLayout(new GridLayout(5,3)); // Use flow strategy to place components
		
	
		add(numberLabel); add(numberField); add(numberStatusLabel);
		add(balanceLabel); add(balanceField); add(balanceStatusLabel);
		add(typeLabel); add(typeBox); add(typeStatusLabel);
		add(holderLabel); add(holderBox); add(holderStatusLabel);
		add(addButton); add(clearButton);
		
		// populate type and holders
		String[] types = {"Sparkonto", "Lönekonto"};
		for(String t : types) {
			typeBox.addItem(t);
		}
				
		// Give this pane a border with a title
		setBorder(new TitledBorder("Lägg till nytt konto"));
		setVisible(false); // Start hidden
	}

	/**
	 * checks user input
	 */
	void checkUserInput() throws BadUserInputException,
		SQLException{
		if(numberField.getText().isEmpty() 
			|| balanceField.getText().isEmpty())
			throw new BadUserInputException("Du har tomma fält!");
				
		Accounts a = new Accounts();
		if(a.accountExists(numberField.getText()))
			throw new BadUserInputException("Kontonumret existerar redan!");

	}
	
	void printStatus() {
		if(numberField.getText().isEmpty());
			numberStatusLabel.setText("Saknas");
		numberStatusLabel.setText("");
		balanceStatusLabel.setText("");

	
		Accounts a = new Accounts();
		try {
			if(a.accountExists(numberField.getText()))
				numberStatusLabel.setText("Upptaget!");
		} catch (SQLException e){
			numberStatusLabel.setText("SQL-fel!");
		}
		
			
		if(balanceField.getText().isEmpty())
			balanceStatusLabel.setText("Saknas");
		
		try {
			Double.parseDouble(balanceField.getText());
		} catch (NumberFormatException e) {
			balanceStatusLabel.setText("Ej en siffra");
		}
	}
	
	void update(){
		numberField.setText("");
	 	balanceField.setText("0.00");
	 	holderBox.removeAllItems();
		try {
			ArrayList<Person> persons = (new Persons()).getPersons() ;
			for(Person p : persons) {
				holderBox.addItem(p.getName());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(
					getParent(),
					"SQL-fel!\n" + e.getMessage(),	
					"Trasig SQL", JOptionPane.ERROR_MESSAGE);			
		}
	}
}
