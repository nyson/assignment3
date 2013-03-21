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
	
	String name = "";
	String street = "";
	String postno = "";
	String city = "";
	//Person person = new Person();

	private static final long serialVersionUID = 1L;
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
	

	// Reusable objects for design settings
	Dimension panelSize = new Dimension(300,100);
	Border labelBorder = new EtchedBorder();
	
	// Listener for this panels buttons
		ActionListener buttonListener = new ActionListener() {
			// skapar inre klass (actionlistener är ett interface)
			public void actionPerformed(ActionEvent e) {

	
				
				// which button?
				if (e.getSource() == addButton)
					streetField.setText("Add");
					name = nameField;
					street = streetField;
					postno = postoField;
					city = cityField;
					
				else if (e.getSource() == clearButton)
			    	 streetField.setText("Clear");
				
			} // end of inner class
		}; // End of Listener for all buttons

	
	/**
	 * The panel where you can add new persons to the database
	 */
	public PersonPanel() {
	// Set the start settings for all components
		
		// Attach ActionListeners
		addButton.addActionListener(buttonListener);
		clearButton.addActionListener(buttonListener);
		
		// Design (border styles) 
		nameLabel.setBorder(labelBorder);
		streetLabel.setBorder(labelBorder);
		postnoLabel.setBorder(labelBorder);
		cityLabel.setBorder(labelBorder);
		
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
	 * Returns this panels actionlistener
	 * @return
	 */
	ActionListener getActionListener(){
		return buttonListener;
	}
}
