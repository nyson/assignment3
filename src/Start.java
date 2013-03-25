import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.text.*;
import java.util.Date;

public class Start extends JFrame {
	
	// don't know, eclipse forced me hand
	private static final long serialVersionUID = -8523424458968940482L;
	
// Graphical elements to be used
	//Menus
	JMenuBar menuBar = new JMenuBar();
	JMenu registerMenu = new JMenu("Registerhantering"),
		manageAccountsMenu = new JMenu("Kontohantering"),
		helpMenu = new JMenu("Hjälp");
	JMenuItem newPersonMenuItem = new JMenuItem("Ny kontoinnehavare"),
		newAccountMenuItem = new JMenuItem("Nytt konto"),
		depositWithdrawMenuItem = new JMenuItem("Insättning/Uttag"),
		transferMenuItem = new JMenuItem("Överföring"),
		statementMenuItem = new JMenuItem("Kontoutdrag"),
		aboutMenuItem = new JMenuItem("Om");
		
	//Panels
	WelcomePanel connectDBPanel = new WelcomePanel();
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
			if (e.getSource() == newPersonMenuItem)
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
			activePanel.update();
			activePanel.setVisible(true);	// show the selected panel
		} // end of inner class
	}; // End of Listener for menus
	
	
	// Dimensions
	Dimension windowSize = new Dimension(450, 250);
	Dimension spacer =  new Dimension(430,20);
	
	//About message
	String aboutMessage = "Denna kan bytas sen till en hur" +
			" fflasshig som helst.\n\n Skapad av:\n" +
			" Jonatha\n Oskar\n Magnus";	

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
		setJMenuBar(menuBar);
		menuBar.add(registerMenu);
		menuBar.add(manageAccountsMenu);
		menuBar.add(helpMenu);
		// menuBar.setHelpMenu(helpMenu); // JAVA: not yet implemented
		registerMenu.add(newPersonMenuItem);
		registerMenu.add(newAccountMenuItem);
		manageAccountsMenu.add(depositWithdrawMenuItem);
		manageAccountsMenu.add(transferMenuItem);
		manageAccountsMenu.add(statementMenuItem);
		helpMenu.add(aboutMenuItem);

		// Connect the buttons to the buttonListener
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

		Start werasBetalService = new Start();
		werasBetalService.setVisible(true);
		System.out.println("Weras betalservice is running...");
		werasBetalService.deadline();
	}
	
	void deadline(){
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
		try {
			Date deadline = formatter.parse("20130325 08:00:00");
			int diff = (int) (deadline.getTime() - new Date().getTime());
			System.out.println("deadline = t - " + (diff/1000)+" s");
		}catch(Exception e){}
	}
}