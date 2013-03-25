//import java.awt.GraphicsConfiguration;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A bank statement
 *
 * @author Magnus, Jonathan, Oskar
 *
 */
public class StatementFrame extends JFrame {
	private static final long serialVersionUID = 1L; // some bull shit eclipse yapps about
	
	Dimension windowSize = new Dimension(600, 400);
	
	JPanel details = new JPanel(new GridLayout(2,8));
	JLabel accountNrLabel = new JLabel("Konto: ");
	JLabel accountNr = new JLabel();
	JLabel accountTypeLabel = new JLabel(" Typ: ");
	JLabel accountType = new JLabel();
	JLabel accountHolderLabel = new JLabel("  Kontoinnehavare: ");
	JLabel accountHolder = new JLabel();
	JLabel accountBalanceLabel = new JLabel("  Saldo: ");
	JLabel accountBalance = new JLabel();
	
	JLabel nameLabel = new JLabel(); 
	JLabel streetLabel = new JLabel();
	JLabel zipLabel = new JLabel();
	JLabel cityLabel = new JLabel();
	
	/**
	 * @throws HeadlessException
	 */
	public StatementFrame(Account account) throws HeadlessException {
		
		//
		setTitle("Weras betalservice");
		setPreferredSize(windowSize);pack(); // Window size
		setLayout(new FlowLayout()); // Use flow/grid/box strategy to place components
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		
		//
		add(details);
		
		details.add(accountNrLabel);
		details.add(accountNr);
		details.add(accountTypeLabel);
		details.add(accountType);
		details.add(accountHolderLabel);
		details.add(accountHolder);
		details.add(accountBalanceLabel);
		details.add(accountBalance);

		details.add(nameLabel); 
		details.add(streetLabel);
		details.add(zipLabel);
		details.add(cityLabel);
		
		accountNr.setText(account.getAccountNo());
		accountType.setText(Account.typeToString((account.getAccountType())));
		accountHolder.setText(account.getAccountHolder());
		accountBalance.setText(String.valueOf(account.getAccountBalance()));
		
		//

		try {
			Person person = new Persons().getPersonByName(accountHolder.getText());
			nameLabel.setText("\n\n" + person.getName());
			streetLabel.setText("Bor: " + person.getAddress());
			zipLabel.setText(", " + person.getZip()); 
			cityLabel.setText(", " + person.getCity());
		}catch(Exception e){}
	}
}
