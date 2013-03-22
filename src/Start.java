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
	JMenu fileMenu = new JMenu("Arkiv");
	JMenu registerMenu = new JMenu("Registerhantering");
	JMenu manageAccountsMenu = new JMenu("Kontohantering");
	JMenu helpMenu = new JMenu("Hjälp");
	JMenuItem connectDBMenuItem = new JMenuItem("Anslut databas");
	JMenuItem newPersonMenuItem = new JMenuItem("Ny kontoinnehavare");
	JMenuItem newAccountMenuItem = new JMenuItem("Nytt konto");
	JMenuItem depositWithdrawalMenuItem = new JMenuItem("Insättning/Uttag");
	JMenuItem transferMenuItem = new JMenuItem("Överföring");
	JMenuItem statementMenuItem = new JMenuItem("Kontoutdrag");
	JMenuItem aboutMenuItem = new JMenuItem("Om");
	
	//Test code
	JLabel testLabel = new JLabel("Testfield (lies in Start panel)");
	JTextField testTextField = new JTextField(20);
	
	//Panels
	ConnectDBPanel connectDBPanel = new ConnectDBPanel();
	PersonPanel personPanel = new PersonPanel();
	
	// This sets the active panel at start !!!
	JPanel activePanel = connectDBPanel; // the Connect to database panel
	
	// Settings that can be reused
	Dimension windowSize = new Dimension(500, 350);
	Dimension panelSize = new Dimension(300,100);
	
	// Listener for personPanel 
	// ActionListener personPanelListener = personPanel.getListeners(listenerType);
	
	/**
	 *  Listener for all buttons (and menus which are buttons)
	 */
	ActionListener buttonListener = new ActionListener() {
		// Creates an inner class
		public void actionPerformed(ActionEvent e) {

			activePanel.setVisible(false);	// hide the active panel

			testTextField.setText(e.getSource().toString());
			// select a new panel?
			if (e.getSource() == newPersonMenuItem)
				activePanel = personPanel;
			else if (e.getSource() == connectDBMenuItem)
		    	 activePanel = connectDBPanel;
			
			activePanel.setVisible(true);	// show the selected panel
		} // end of inner class
	}; // End of Listener for all buttons
	
	
	
	/**
	 * Listener for the add button in personPanel
	 */ 
	 	ActionListener personAddButtonListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String queryString = personPanel.name + ";" +
								  personPanel.street + ";" +
								  personPanel.postno + ";" +
								  personPanel.city;
			System.out.print(queryBank(queryString));
	} // end of inner class
}; // End of Listener for all buttons



	/**
	 * Constructor. This must be called in order to be created
	 * If this file is started from os it has contain a main()-method
	 * so that this constructor can be activated
	 */
	public Start() {
		
	// Removes the java look and feel for buttons etc. 
		try { 
			// Nimbus
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus" +
			//		".NimbusLookAndFeel");
			// Windows
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows" +
					".WindowsLookAndFeel");			
			
			SwingUtilities.updateComponentTreeUI(this);
		}catch(Exception ex){}

	// Set the start settings for all components
		// Connect the buttons to the buttonListener
		connectDBMenuItem.addActionListener(buttonListener);
		newPersonMenuItem.addActionListener(buttonListener);
		newAccountMenuItem.addActionListener(buttonListener);
		depositWithdrawalMenuItem.addActionListener(buttonListener);
		transferMenuItem.addActionListener(buttonListener);
		statementMenuItem.addActionListener(buttonListener);
		aboutMenuItem.addActionListener(buttonListener);
		// Connect action listeners to the sub panels
    	personPanel.addButton.addActionListener(personAddButtonListener);
    	connectDBPanel.addButton.addActionListener(personAddButtonListener);
		
		// Add menus
		menuBar.add(fileMenu); 
		menuBar.add(registerMenu);
		menuBar.add(manageAccountsMenu);
		// menuBar.setHelpMenu(helpMenu); // JAVA: not yet implemented
		menuBar.add(helpMenu);
		fileMenu.add(connectDBMenuItem);
		registerMenu.add(newPersonMenuItem);
		registerMenu.add(newAccountMenuItem);
		manageAccountsMenu.add(depositWithdrawalMenuItem);
		manageAccountsMenu.add(transferMenuItem);
		manageAccountsMenu.add(statementMenuItem);
		helpMenu.add(aboutMenuItem);
		setJMenuBar(menuBar);
		
		setTitle("Weras betalservice");
		setPreferredSize(windowSize);pack(); // Window size
		setLayout(new FlowLayout()); // Use flow/grid/box strategy to place components
		// Add panels, buttons and so on..
		add(new Box.Filler(new Dimension(200,20), new Dimension(200,20),
				new Dimension(200,20))); // Spacer
		add(connectDBPanel);add(personPanel);
		add(new Box.Filler(new Dimension(450,20), new Dimension(450,20),
				new Dimension(450,20))); // Spacer
/*TEST*/add(testLabel);add(testTextField); // Test field  
    	activePanel.setVisible(true); // Show the active panel
    	//pack(); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
	
	void testMethod(){
		System.out.println("anropat från sub pane");
	}
	
	
	
}