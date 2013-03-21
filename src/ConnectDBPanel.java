import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;
import java.awt.*;


/**
 * @author Magnus
 *
 */
public class ConnectDBPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	// Graphical elements to be used 
	JLabel choiceLabel = new JLabel("Namn");
	JTextField choiceField = new JTextField(10);
	JLabel choiceStatusLabel = new JLabel("Saknas");
	JButton addButton = new JButton("Lägg till");
	JButton clearButton = new JButton("Rensa");

	// Reusable objects for settings
	Dimension panelSize = new Dimension(300,100);
	
	/**Constructor
	 * The panel where you can add persons to the database
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
}
