import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;
import java.awt.*;

/**
 * 
 */

/**
 * @author Magnus
 *
 */
public class PersonPanel extends JPanel {

	// Graphical elements to be used 
	JLabel nameLabel = new JLabel("Namn");
	JLabel streetLabel = new JLabel("Gatuadress");
	JLabel postnoLabel = new JLabel("Postnummer");
	JLabel cityLabel = new JLabel("Stad");
	JTextField nameField = new JTextField(10);
	JTextField streetField = new JTextField(10);
	JTextField postnoField = new JTextField(10);
	JTextField cityField = new JTextField(10);
	JLabel nameStatusLabel = new JLabel("Saknas");
	JLabel streetStatusLabel = new JLabel("Saknas");
	JLabel postnoStatusLabel = new JLabel("Saknas");
	JLabel cityStatusLabel = new JLabel("Saknas");
	JButton addButton = new JButton("Lägg till");
	JButton clearButton = new JButton("Rensa");

	// Reusable objects for settings
	Dimension panelSize = new Dimension(300,100);
	
	/**
	 * Show the display where you can add persons to tha database
	 */
	public PersonPanel() {

		// Design issues
		nameLabel.setBorder(new EtchedBorder());
		streetLabel.setBorder(new EtchedBorder());
		postnoLabel.setBorder(new EtchedBorder());
		cityLabel.setBorder(new EtchedBorder());
		
		// Add components so the LayoutmManager can distribute them
		setLayout(new GridLayout(5,3)); // Use flow strategy to place components
		add(nameLabel); add(nameField); add(nameStatusLabel);
		add(streetLabel); add(streetField); add(streetStatusLabel);
		add(postnoLabel); add(postnoField); add(postnoStatusLabel);
		add(cityLabel); add(cityField); add(cityStatusLabel);
		add(addButton);	add(clearButton);
		
		// Give this pane a border with a title
		setBorder(new TitledBorder("Lägg till ny kontoinnehavare"));
		//setMaximumSize(panelSize);
		setVisible(false); // Start hidden
		
	}

	/**
	 * @param arg0
	 */
	public PersonPanel(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public PersonPanel(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public PersonPanel(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
