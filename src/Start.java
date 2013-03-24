import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Start extends JFrame {
	
	// don't know, eclipse forced me hand
	private static final long serialVersionUID = -8523424458968940482L;
	
	// The Bank object that handles the SQL database
	Bank werasBank = new Bank();
	
	// Graphical elements to be used
	//Menus
	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("Arkiv"),
		registerMenu = new JMenu("Registerhantering"),
		manageAccountsMenu = new JMenu("Kontohantering"),
		helpMenu = new JMenu("Hjälp");
	JMenuItem connectDBMenuItem = new JMenuItem("Anslut databas"),
		newPersonMenuItem = new JMenuItem("Ny kontoinnehavare"),
		newAccountMenuItem = new JMenuItem("Nytt konto"),
		depositWithdrawMenuItem = new JMenuItem("Insättning/Uttag"),
		transferMenuItem = new JMenuItem("Överföring"),
		statementMenuItem = new JMenuItem("Kontoutdrag"),
		aboutMenuItem = new JMenuItem("Om");
	
	//Test code
	JLabel testLabel = new JLabel("Testfield (lies in Start panel)");
	JTextField testTextField = new JTextField(20);
	
	//Panels
	ConnectDBPanel connectDBPanel = new ConnectDBPanel();
	PersonPanel personPanel = new PersonPanel();
	AccountPanel accountPanel = new AccountPanel();
	DepositWithdrawPanel depositWithdrawPanel = new DepositWithdrawPanel();
	TransferPanel transferPanel = new TransferPanel();
	StatementPanel statementPanel = new StatementPanel();
	 
	// This sets the active panel at start !!!
	WeraPanel activePanel = connectDBPanel; // the Connect to database panel
	
	/**
	 *  Listener for all buttons (and menus which are buttons)
	 */
	ActionListener startActionListener = new ActionListener() {
		// Creates an inner class
		public void actionPerformed(ActionEvent e) {

			activePanel.setVisible(false);	// hide the active panel

			testTextField.setText(e.getSource().toString());
			// select a new panel?
			if (e.getSource() == connectDBMenuItem)
		    	 activePanel = connectDBPanel;
			else if (e.getSource() == newPersonMenuItem)
				activePanel = personPanel;
			else if (e.getSource() == newAccountMenuItem)
				activePanel = accountPanel;
			else if (e.getSource() == depositWithdrawMenuItem)
				activePanel = depositWithdrawPanel;
			else if (e.getSource() == transferMenuItem)
				activePanel = transferPanel;
			else if (e.getSource() == statementMenuItem)
				activePanel = statementPanel;
			else if (e.getSource() == aboutMenuItem){
				String aboutMessage = "Denna kan bytas sen till en hur" +
						" fflasshig som helst.\n\n Skapad av:\n" +
						" Jonatha\n Oskar\n Magnus";
				JOptionPane.showMessageDialog(rootPane, aboutMessage, "Om" +
						" Weras betalservice", JOptionPane.INFORMATION_MESSAGE);
			}// end of if() statement			
			activePanel.setVisible(true);	// show the selected panel
		} // end of inner class
	}; // End of Listener for all buttons
	
	/**
	 * Listener for the add button in personPanel
	 */ 
 	ActionListener panelActionListener = new ActionListener() {
 		public void actionPerformed(ActionEvent e) {
 			String queryString = "queryString not collected";
 			
 			if  (e.getSource() == connectDBPanel.addButton){
 				
 			}
 			
 			if  (e.getSource() == personPanel.addButton){
					queryString = personPanel.name + ";" +
							  personPanel.street + ";" +
							  personPanel.postno + ";" +
							  personPanel.city;
 			}
 			if  (e.getSource() == accountPanel.addButton){
 				
 			}
 			if  (e.getSource() == depositWithdrawPanel.performButton){
 				
 			}
 			if  (e.getSource() == transferPanel.addButton){
 				
 			}
 			if  (e.getSource() == statementPanel.showStatementButton){
 				
 			}
 		 			
 			activePanel.handleAnswer(queryBank(queryString));
 		} // end of inner class
 	}; // end of Listener for add button in personPanel

	// Other class wide settings that can be reused
	Dimension windowSize = new Dimension(500, 350);
	Dimension panelSize = new Dimension(300,100);
	

	/**
	 * Constructor of Start.java
	 */
	public Start() {
		
		// Set main window settings
		setTitle("Weras betalservice");
		setPreferredSize(windowSize);pack(); // Window size
		setLayout(new FlowLayout()); // Use flow/grid/box strategy to place components
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		try { // Removes the java look and feel for buttons etc.
			// Nimbus
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus" +
			//		".NimbusLookAndFeel");
			// Windows
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows" +
					".WindowsLookAndFeel");			
			SwingUtilities.updateComponentTreeUI(this);
		}catch(Exception ex){}

		// Add menus
		menuBar.add(fileMenu); 
		menuBar.add(registerMenu);
		menuBar.add(manageAccountsMenu);
		// menuBar.setHelpMenu(helpMenu); // JAVA: not yet implemented
		menuBar.add(helpMenu);
		fileMenu.add(connectDBMenuItem);
		registerMenu.add(newPersonMenuItem);
		registerMenu.add(newAccountMenuItem);
		manageAccountsMenu.add(depositWithdrawMenuItem);
		manageAccountsMenu.add(transferMenuItem);
		manageAccountsMenu.add(statementMenuItem);
		helpMenu.add(aboutMenuItem);
		setJMenuBar(menuBar);

		// Connect the buttons to the buttonListener
		connectDBMenuItem.addActionListener(startActionListener);
		newPersonMenuItem.addActionListener(startActionListener);
		newAccountMenuItem.addActionListener(startActionListener);
		depositWithdrawMenuItem.addActionListener(startActionListener);
		transferMenuItem.addActionListener(startActionListener);
		statementMenuItem.addActionListener(startActionListener);
		aboutMenuItem.addActionListener(startActionListener);
		
		// Connect action listeners to the sub panels
    	connectDBPanel.addButton.addActionListener(panelActionListener);
    	personPanel.addButton.addActionListener(panelActionListener);
    	accountPanel.addButton.addActionListener(panelActionListener);
    	depositWithdrawPanel.performButton.addActionListener(panelActionListener);
    	transferPanel.addButton.addActionListener(panelActionListener);
    	statementPanel.showStatementButton.addActionListener(panelActionListener);
				
		// Add panels, buttons and so on..
		// A spacer before the active panel
		add(new Box.Filler(new Dimension(450,20), new Dimension(450,20),
				new Dimension(200,20))); 
		// Add the panels
		add(connectDBPanel);add(personPanel);add(accountPanel);
		add(depositWithdrawPanel);add(transferPanel);add(statementPanel);
		// A spacer after before the active panel
		add(new Box.Filler(new Dimension(450,20), new Dimension(450,20),
				new Dimension(450,20))); 

/*TEST CODE*/add(testLabel);add(testTextField); // Test field  

		// we prefer NOT to pack(); here
		// Show the active panel
		activePanel.setVisible(true);  
	}

	
	/**
	 * main method (since we start this file from the OS)
	 * Envokes our Start-class 
	 * @param args
	 */
	public static void main(String[] args) {

		Start e1 = new Start();
		e1.setVisible(true);
		System.out.println("Weras betalservice is running...");
	}
	
	/**
	 * Sends a query to a Bank object and returns an answer String
	 * @param query
	 */
	String queryBank(String query){
		
		String answer = werasBank.query(query);
		return answer;
	}
	

	
}