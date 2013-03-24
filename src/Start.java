import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Start extends JFrame {
	
	// don't know, eclipse forced me hand
	private static final long serialVersionUID = -8523424458968940482L;
	
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
	 *  Listener for menus
	 */
	ActionListener startActionListener = new ActionListener() {
		// Creates an inner class
		public void actionPerformed(ActionEvent e) {

			activePanel.setVisible(false);	// hide the active panel

			// select a new panel
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
				JOptionPane.showMessageDialog(rootPane, aboutMessage, "Om" +
						" Weras betalservice", JOptionPane.INFORMATION_MESSAGE);
			}// end of if() statement			
			activePanel.setVisible(true);	// show the selected panel
		} // end of inner class
	}; // End of Listener for menus
	
	
	// Other class wide settings that can be reused
	String aboutMessage = "Denna kan bytas sen till en hur" +
			" fflasshig som helst.\n\n Skapad av:\n" +
			" Jonatha\n Oskar\n Magnus";
	Dimension windowSize = new Dimension(500, 350);
	Dimension spacer =  new Dimension(450,20);
	

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
		
	// Add panels, buttons and so on..
		// A spacer before the active panel
		add(new Box.Filler(spacer, spacer, spacer)); 
		// Add the panels
		add(connectDBPanel);add(personPanel);add(accountPanel);
		add(depositWithdrawPanel);add(transferPanel);add(statementPanel);
		// A spacer after before the active panel
		add(new Box.Filler(spacer, spacer, spacer)); 

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
}