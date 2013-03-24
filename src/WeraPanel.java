import javax.swing.JPanel;

/**
 * This class is an abstract class which extends JPanel.
 * Classes that are extends this class will become JPanels
 * which are forced to include this abstract class' methods 
 */

/**
 * @author Magnus
 *
 */
public abstract class WeraPanel extends JPanel {
	private static final long serialVersionUID = 1L; // Eclipse told me to add

	
	// These methods must be implemented if extended by WeraPanel
	abstract void handleAnswer(String answer);
	
	
}
