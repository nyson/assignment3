import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;
import java.awt.*;


/**
 * @author Magnus
 *
 */
public class ConnectDBPanel extends JPanel {

	// Graphical elements to be used 
	JLabel choiceLabel = new JLabel("Namn");
	JTextField choiceField = new JTextField(10);
	JLabel choiceStatusLabel = new JLabel("Saknas");
	JButton addButton = new JButton("Lägg till");
	JButton clearButton = new JButton("Rensa");

	// Reusable objects for settings
	Dimension panelSize = new Dimension(300,100);
	
	/**
	 * Show the display where you can add persons to tha database
	 */
	public ConnectDBPanel() {

		// Design issues
		choiceLabel.setBorder(new EtchedBorder());
		
		// Add components so the LayoutmManager can distribute them
		setLayout(new GridLayout(2,3)); // Use flow strategy to place components
		add(choiceLabel); add(choiceField); add(choiceStatusLabel);
		add(addButton);	add(clearButton);
		
		// Give this pane a border with a title
		setBorder(new TitledBorder("Anslut till databas"));
		//setMaximumSize(panelSize);
		setVisible(false); // Start hidden
		
	}

	/**
	 * @param arg0
	 */
	public ConnectDBPanel(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ConnectDBPanel(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ConnectDBPanel(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
