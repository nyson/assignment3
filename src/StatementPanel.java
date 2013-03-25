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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.awt.*;
import java.sql.SQLException;

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
	JLabel accountLabel = new JLabel("Konton");
	JLabel personLabel = new JLabel("Kontoinnehavare");
	JLabel transactionLabel = new JLabel("Transaktioner");
	
	JRadioButton accountRadio = new JRadioButton("Konton");
	JRadioButton personRadio = new JRadioButton("Personer");
	JRadioButton transactionRadio = new JRadioButton("Transaktioner");
	
	JButton showStatementButton = new JButton("Visa");
	JTable presentationTable;



	// Create the statement window
	StatementFrame statementFrame = new StatementFrame();

	// Reusable objects for design settings
	Dimension panelSize = new Dimension(300,400);
	Border labelBorder = new EtchedBorder();

	// Listener for this panels buttons
	ActionListener buttonListener = new ActionListener() {
		// Creates a inner class
		public void actionPerformed(ActionEvent e) {

			// Which button was pressed?
			if (e.getSource() == showStatementButton){ // showStatement
				// show the statement frame
				// OBS! BEHÖVER LADDAS MED NYA DATA FÖRST!
				// statementFrame.setVisible(true);
			}

		} // end of inner class
	}; // End of Listener for this panels buttons
	ChangeListener radioListener = new ChangeListener() {
	    public void stateChanged(ChangeEvent e) {
	    	TableType t;
	    	if(accountRadio.isSelected())
	    		t = TableType.ACCOUNTS;
	    	else if(personRadio.isSelected())
	    		t = TableType.PERSONS;
	    	else 
	    		t = TableType.TRANSACTIONS;
	    	
	    	try {
	    		updateTables(t);
	    	} catch (SQLException ex) {
	    		JOptionPane.showMessageDialog(null, ex.getMessage(), "SQL-fel!",
	    			JOptionPane.ERROR_MESSAGE);
	    	}
	    }
	};

	private enum TableType {TRANSACTIONS, PERSONS, ACCOUNTS};
	
	private DefaultTableModel updateTables(TableType type) throws SQLException{
		DefaultTableModel model;
		String[] accountHeader 
			= {"Kontonummer", "Saldo", "Kontotyp", "Innehavare"};
		String[] personHeader
			= {"Namn", "Adress", "Postnummer", "Stad"};
		String[] transactionHeader
			= {"Konto", "Belopp", "Typ"};
		
		
		switch(type) {
		default:
		case ACCOUNTS:
			model = new DefaultTableModel(accountHeader, 0);

			for(Account a : (new Accounts()).getAccounts()) {
				Object[] row = new Object[4];
				row[0] = a.getAccountNo(); 
				row[1] = Double.toString(a.getAccountBalance());
				row[2] = Account.typeToString(a.getAccountType());
				row[3] = a.getAccountHolder();
				
				model.addRow(row);
			}
			break;
			
		case TRANSACTIONS:
			model = new DefaultTableModel(transactionHeader, 0);
			for(Transaction t : (new Accounts()).getTransactions()){
				Object[] row = new Object[3];

				
				row[0] = t.getAccount(); 
				row[1] = Double.toString(t.getAmount());
				row[2] = t.getType() == Transaction.Type.DEPOSIT 
					? "Insättning" : "Uttag";
				
				model.addRow(row);
			}
			break;
			
		case PERSONS:
			model = new DefaultTableModel(personHeader, 0);
			
			for(Person p : (new Persons()).getPersons()) {
				Object[] row = new Object[4];
				
				row[0] = p.getName(); 
				row[1] = p.getAddress();
				row[2] = Integer.toString(p.getZip());
				row[3] = p.getCity();
				
				model.addRow(row);
			}		
			break;
		}
		
		return model; 
	}
	
	/**
	 * The panel where you can add new persons to the database
	 */
	public StatementPanel() {
		// Set the start settings for all components

		
		
		
		
		// Attach ActionListeners
		showStatementButton.addActionListener(buttonListener);
//		clearButton.addActionListener(buttonListener);

		// Design (border styles) 
		accountLabel.setBorder(labelBorder);

		try {
			presentationTable = new JTable(updateTables(TableType.ACCOUNTS)); 
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, "SQL-fel!");
		}
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL; c.ipadx = 0;
		
			
		c.weightx = 0.5; c.anchor = GridBagConstraints.CENTER; c.gridx = 0;
		c.gridy = 0;
		add(accountRadio, c); c.gridx = 1;
		add(personRadio, c); c.gridx = 2;
		add(transactionRadio, c);
		
		c.gridy = 2;
		c.gridx = 0;
		c.gridwidth = 3;
		add(new JScrollPane(presentationTable), c);
		

		
		/*// Add components so the LayoutmManager can distribute them
		setLayout(new GridLayout(3,1)); // Use flow strategy to place components
		add(accountLabel); add(accountList); 
		add(showStatementButton);	add(clearButton);
		 */
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
