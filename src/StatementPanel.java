import javax.swing.*;
import javax.swing.border.*;
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
	JLabel accountLabel = new JLabel("Konto");
	String[] lista = {"ett","två"};
	JComboBox<String> accountComboBox = new JComboBox<String>(lista);
	JButton showStatementButton = new JButton("Visa");
	
	// Reusable objects for design settings
	Dimension panelSize = new Dimension(300,100);
	Border labelBorder = new EtchedBorder();
	
	// Listener for this panels buttons
		ActionListener buttonListener = new ActionListener() {
			// Creates a inner class
			public void actionPerformed(ActionEvent e) {

				// Which button was pressed?
				if (e.getSource() == showStatementButton){ // showStatement
					// Create the statement window
					String chosenAccountNr = accountComboBox.getSelectedItem()
							.toString();
					try {Account chosenAccount = new Accounts()
							.getAccountByAccountNo(chosenAccountNr);
						StatementFrame statementFrame = 
							new StatementFrame(chosenAccount);
						statementFrame.setVisible(true);
					}catch(Exception e2){}
					// show the statement frame
					// OBS! BEHÖVER LADDAS MED NYA DATA FÖRST!
				}				
			} // end of inner class
		}; // End of Listener for this panels buttons

	
	/**
	 * The panel where you can add new persons to the database
	 */
	public StatementPanel() {
	// Set the start settings for all components
		
		// Attach ActionListeners
		showStatementButton.addActionListener(buttonListener);
		
		// Design (border styles) 
		accountLabel.setBorder(labelBorder);
		
		// Add components so the LayoutmManager can distribute them
		setLayout(new GridLayout(1,3)); // Use flow strategy to place components
		add(accountLabel); add(accountComboBox); 
		add(showStatementButton);
		
		// Give this pane a border with a title
		setBorder(new TitledBorder("Välj konto"));
		//setMaximumSize(panelSize);
		setVisible(false); // Start hidden
	}

	void update(){
		accountComboBox.removeAllItems();
		try {
			for(Account a: (new Accounts()).getAccounts()) {
				accountComboBox.addItem(a.getAccountNo());
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "SQL-fel!", "Fel!",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
