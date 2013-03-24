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

import java.awt.event.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * @author T66M
 *
 */
public class DepositWithdrawPanel extends WeraPanel {

	String account = "";
	double amount;
	Transaction.Type transactionType;
	//Setting withdrawl or deposit object
	//Trans tracc = new Trans(account, amount);


	private static final long serialVersionUID = 1L;
	// Graphical elements to be used 
	JLabel accountLabel = new JLabel("Account");
	JLabel amountLabel = new JLabel("Amount");

	JComboBox<String> accountBox = new JComboBox<String>();
	JTextField amountField = new JTextField(10);

	JLabel accountStatusLabel = new JLabel("");
	JLabel amountStatusLabel = new JLabel("Saknas");
	JLabel hidden = new JLabel(" ");
	JButton performButton = new JButton("Genomför");
	JButton clearButton = new JButton("Rensa");

	ButtonGroup oper = new ButtonGroup();
	private JRadioButton depositRadio = new JRadioButton("Deposit"),
			withdrawRadio = new JRadioButton("Withdrawl", true);

	ChangeListener radioListener = new ChangeListener() {
	    public void stateChanged(ChangeEvent e) {
	    	updateStatusLabels();
	    }
	};
	
	
	KeyListener updateListener = new KeyListener() {
        public void keyTyped(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {}
        
        public void keyReleased(KeyEvent e) {
        	updateStatusLabels();
        }
	};

	// Reusable objects for design settings
	Dimension panelSize = new Dimension(300,100);
	Border labelBorder = new EtchedBorder();

	// Listener for this panels buttons
	ActionListener buttonListener = new ActionListener() {
		// skapar inre klass (actionlistener är ett interface)
		public void actionPerformed(ActionEvent e) {
			//Radio Button Selected;
			// which button was pressed?
			if (e.getSource() == performButton){
				try {
					checkUserInput();
					account = accountBox.getSelectedItem().toString();
					amount = Double.parseDouble(amountField.getText());
					
					Account transferee = (new Accounts())
							.getAccountByAccountNo(account);
					if (depositRadio.isSelected()) {
						transferee.deposit(amount);
					} else if (withdrawRadio.isSelected()) {
						transferee.withdraw(amount);
					}
					
					
					
					
				}catch (BadUserInputException ex) {
					JOptionPane.showMessageDialog(null,	
							"Du har gjort en dålig inmatning!",
							"Inputfel!", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null,	ex.getMessage(),
							"SQL-fel!", JOptionPane.ERROR_MESSAGE);
				} catch (NotEnoughMineralsException ex) {
					JOptionPane.showMessageDialog(null,	ex.getMessage(),
							"Pengar saknas!", JOptionPane.ERROR_MESSAGE);
				}


			} else if (e.getSource() == clearButton) {
				amountField.setText("0.00");
			}
		} // end of inner class
	}; // End of Listener for all buttons


	/**
	 * The panel where you can add new persons to the database
	 */
	public DepositWithdrawPanel() {
		// Set the start settings for all components

		// Attach ActionListeners
		performButton.addActionListener(buttonListener);
		clearButton.addActionListener(buttonListener);

		amountField.addKeyListener(updateListener);
		
		depositRadio.addChangeListener(radioListener);
		withdrawRadio.addChangeListener(radioListener);
		
		accountBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        updateStatusLabels();
		    }
		});
			
		// Design (border styles) 
		accountLabel.setBorder(labelBorder);
		amountLabel.setBorder(labelBorder);
		//oper.setBorder(labelBorder);
		oper.add(depositRadio); oper.add(withdrawRadio);
		// Add components so the LayoutmManager can distribute them
		setLayout(new GridLayout(4,3)); // Use flow strategy to place components
		try {
			for(Account a: (new Accounts()).getAccounts()) {
				accountBox.addItem(a.getAccountNo());
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "SQL-fel!", "Fel!",
					JOptionPane.ERROR_MESSAGE);
		}

		add(accountLabel); add(accountBox); add(accountStatusLabel);
		add(amountLabel); add(amountField); add(amountStatusLabel);
		add(hidden); add(performButton); add(clearButton); add(hidden);
		add(depositRadio); add(withdrawRadio);

		// Give this pane a border with a title
		setBorder(new TitledBorder("Lägg till ny insättning/uttag"));
		//setMaximumSize(panelSize);
		setVisible(false); // Start hidden

	}

	private void updateStatusLabels(){
		amountStatusLabel.setText("");
		
		if(amountField.getText().isEmpty())
			amountStatusLabel.setText("Saknas");

		try {
			double amount = Double.parseDouble(amountField.getText());
			Account a = (new Accounts())
					.getAccountByAccountNo
						(accountBox.getSelectedItem().toString());
			if(withdrawRadio.isSelected() && a.getAccountBalance() < amount) {
				amountStatusLabel
					.setText("Belopp saknas!");
			}
		} catch (NumberFormatException e) {
			amountStatusLabel.setText("Inte ett tal!");
			
		} catch (NoSuchRowException e) {
			// ignored
			
		} catch (SQLException e) {
			// ignored
		}
		
		
	}

	private void checkUserInput() throws BadUserInputException {
		if(amountField.getText().isEmpty()) 
			throw new BadUserInputException("Du har tomma fält!");

		try {
			Double.parseDouble(amountField.getText());
		} catch (NumberFormatException e) {
			throw new BadUserInputException("Du måste skriva ett tal!");
		}
	}	


	/**
	 * Gives the user some feed back
	 * @param answer
	 */
	void handleAnswer(String answer){
		JOptionPane.showMessageDialog(getParent(), answer);
	}
}

/* Det du får in här är "account", "amount" och withdraw
 * withdraw är en boolean som skall visa om det är insättning eller uttag
 */