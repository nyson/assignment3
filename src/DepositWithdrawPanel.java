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
 * @author T66M
 *
 */
public class DepositWithdrawPanel extends WeraPanel {
	
	String account = "";
	String amount = "";
	Boolean withdraw = false;
	//Setting withdrawl or deposit object
	//Trans tracc = new Trans(account, amount);
	
	
	private static final long serialVersionUID = 1L;
	// Graphical elements to be used 
	JLabel accountLabel = new JLabel("Account");
	JLabel amountLabel = new JLabel("Amount");
	JTextField accountField = new JTextField(10);
	JTextField amountField = new JTextField(10);
	JLabel accountStatusLabel = new JLabel("Saknas");
	JLabel amountStatusLabel = new JLabel("Saknas");
	JLabel hidden = new JLabel(" ");
	JButton performButton = new JButton("Genomför");
	JButton clearButton = new JButton("Rensa");
	
	ButtonGroup oper = new ButtonGroup();
	private JRadioButton r1 = new JRadioButton("Deposit"),
						r2 = new JRadioButton("Withdrawl", true);
	
	

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
					account = accountField.getText();
					amount = amountField.getText();
					if (r1.isSelected()) {
						withdraw = false;
					} else if (r2.isSelected()) {
						withdraw = true;
				}
				} else if (e.getSource() == clearButton) {
			    	 amountField.setText("Clear");
					 accountField.setText("Clear");
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
		
		// Design (border styles) 
		accountLabel.setBorder(labelBorder);
		amountLabel.setBorder(labelBorder);
		//oper.setBorder(labelBorder);
		oper.add(r1); oper.add(r2);
		// Add components so the LayoutmManager can distribute them
		setLayout(new GridLayout(4,3)); // Use flow strategy to place components
		

		add(accountLabel); add(accountField); add(accountStatusLabel);
		add(amountLabel); add(amountField); add(amountStatusLabel);
		add(hidden); add(performButton); add(clearButton); add(hidden);
		add(r1); add(r2);
		
		// Give this pane a border with a title
		setBorder(new TitledBorder("Lägg till ny insättning/uttag"));
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

/* Det du får in här är "account", "amount" och withdraw
 * withdraw är en boolean som skall visa om det är insättning eller uttag
 */