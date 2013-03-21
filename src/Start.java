import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Start extends JFrame {
	
// Graphical elements to be used
	//Menues
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
	
	//Testshit
	JButton b1 = new JButton("Tänd");
	JButton b2 = new JButton("Släck");
	JTextField txt = new JTextField(10);
	
	//Panels
	ConnectDBPanel connectDBPanel = new ConnectDBPanel();
	PersonPanel personPanel = new PersonPanel();
	
	// This sets the active panel at start !!!
	JPanel activePanel = connectDBPanel; // the Connect to database panel
	
// Settings that can be reused
	Dimension windowSize = new Dimension(500, 350);
	Dimension panelSize = new Dimension(300,100);
	
	
// Listener for all buttons (and menues which are buttons)
	ActionListener buttonListener = new ActionListener() {
		// skapar inre klass (actionlistener är ett interface)
		public void actionPerformed(ActionEvent e) {

			activePanel.setVisible(false);	// hide the active panel

			txt.setText(e.getSource().toString());
			// select a new panel?
			if (e.getSource() == newPersonMenuItem)
				activePanel = personPanel;
			else if (e.getSource() == connectDBMenuItem)
		    	 activePanel = connectDBPanel;
			
			activePanel.setVisible(true);	// show the selected panel
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
		// Connect the buttons to the buttonlistener
		b1.addActionListener(buttonListener);
		b2.addActionListener(buttonListener);
		connectDBMenuItem.addActionListener(buttonListener);
		newPersonMenuItem.addActionListener(buttonListener);
		newAccountMenuItem.addActionListener(buttonListener);
		depositWithdrawalMenuItem.addActionListener(buttonListener);
		transferMenuItem.addActionListener(buttonListener);
		statementMenuItem.addActionListener(buttonListener);
		aboutMenuItem.addActionListener(buttonListener);

		setTitle("Weras betalservice");
		setPreferredSize(windowSize);pack(); // Window size
		setLayout(new FlowLayout()); // Use flow strategy to place components
		
		// Add menues
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
		
		// Add test shit
		add(b1); add(b2); add(txt);
		
		// Add panels that we then can choose from
		add(connectDBPanel);
    	add(personPanel);
    	
    	// show the active panel and the the main window
    	activePanel.setVisible(true);
    	//pack(); 
    	setVisible(true); 
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * main method (since we start this file from the OS)
	 * Envokes our Start-class 
	 * @param args
	 */
	public static void main(String[] args) {
		Start e1 = new Start();
	}
}