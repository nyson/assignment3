import java.sql.*;

/**
 * Handles Accounts and SQL update connections
 * @author Jonathan Skårstedt
 * @author Magnus Duberg
 * @author Oskar Linder Pålsgård
 *
 */
public class Account {
	public enum Type {SAVINGS, WAGE};
	
	/**
	 * Exception for bad account types, ie not in AccountType range
	 * 
	 * @author Jonathan Skårstedt
	 * @author Oskar Linder Pålsgård
	 * @author Magnus Duberg
	 */
	public class BadAccountTypeException extends Exception {
		static final long serialVersionUID = 13043594;
		public BadAccountTypeException(String message) {
			super(message);
		}
	}
	
	/**
	 * Transforms an AccountType to String
	 * @param t AccountType to transform
	 * @return String representation of said account
	 */
	public static String typeToString(Type t) {
		switch(t) {
		case SAVINGS:
			return "spar";
		case WAGE:
		default: 
			return "loen";			
		}
	}
	// Private attributes
	private String key; // when pushing updates
	private String number;
	private Type type;
	private String holder;
	private double balance;
	
	// Constructor
	/**
	 * @param accNr Account Number
	 * @param accType Account Type
	 * @param accHolder Account Holder
	 * @param accBalance Account Balance
	 * @throws BadAccountTypeException
	 */
	public Account(String accNr, String accType, String accHolder, 
			double accBalance) throws BadAccountTypeException {
		
		switch(accType) {
			case "spar":
				type = Type.SAVINGS;
				break;
			
			case "loen":
				type = Type.WAGE;
				break;
				
			default:
				throw new BadAccountTypeException
					("'"+accType+"' isn't a valid account type!");
		}
		
		number = accNr;
		holder = accHolder; 
		balance = accBalance;
		key = number;
	}
	
	/**
	 * Creates an account by type instead of inferenceing it
	 * 
	 * @param accNr Account Number
	 * @param accType Account Type
	 * @param accHolder Account Holder
	 * @param accBalance Account Balance
	 */
	public Account(String accNr, Type accType, String accHolder,
			double accBalance) {

		type = accType;
		number = accNr;
		holder = accHolder; 
		balance = accBalance;
		key = number;
	}	
	
	// Getters
	public String getAccountNo() {return number;}
	public Type getAccountType() {return type;}
	public String getAccountHolder() {return holder;}
	public double getAccountBalance() {return balance;}
	// Setters
	public void setAccountNo(String accountNo) 
		{this.number = accountNo;}
	public void setAccountType(Type accountType) {
		this.type = accountType;}
	public void setAccountHolder(String accountHolder) {
		this.holder = accountHolder;}
	public void setAccountBalance(double accountBalance) {
		this.balance = accountBalance;}

	/**
	 * Pushes out changes to database
	 * 
	 * @return True on success
	 * @throws SQLException
	 */
	public boolean push() throws SQLException {
		String query = 
			"UPDATE konto "
				+ "SET kontonr=?, "
				+ "kontotyp=?, "
				+ "namn=?, "
				+ "saldo=? " 
			+ "WHERE kontonr=?"; 
		
		PreparedStatement push = DB.prepareStatement(query);
		push.setString(1, number);
		push.setString(2, typeToString(type));
		push.setString(3, holder);
		push.setDouble(4, balance);
		push.setString(5, key);
		
		int updated = push.executeUpdate();
		key = number;
		
		push.close();
		
		return updated > 0;
	}
	
	
	/**
	 * Deposits set amount to account and saves it into SQL
	 * @param amount Amount to deposit
	 * @return New account balance
	 * @throws SQLException
	 */
	public double deposit(double amount) throws SQLException {
		balance += amount;


		PreparedStatement s = DB.prepareStatement
			("UPDATE konto SET saldo = ? WHERE kontonr = ?");
		
		s.setDouble(1, balance);
		s.setString(2, key);
		
		s.executeUpdate();
		
		Transaction t = new Transaction(key, Transaction.Type.DEPOSIT, amount);
		t.register();
		
		return balance;				
	}
	
	/**
	 * Withdraws amount from account
	 * @param amount Amount to withdrwas
	 * @return New account balance 
	 * @throws NotEnoughMineralsException on lack of funds
	 */
	public double withdraw(double amount) 
			throws NotEnoughMineralsException, SQLException {
		if(balance - amount < 0)
			throw new NotEnoughMineralsException
				("You don't have that much in the bank!");
		
		PreparedStatement s = DB.prepareStatement
				("UPDATE konto SET saldo = ? WHERE kontonr = ?");
			
		s.setDouble(1, balance);
		s.setString(2, key);
		
		s.executeUpdate();
		
		Transaction t = new Transaction(key, Transaction.Type.WITHDRAWAL, amount);
		t.register();
					
		
		balance -= amount;
		return balance;
	}

	
	/**
	 * Creates a header for console output of an account object
	 * @return Formatted string header
	 */
	public static String toStringHeader(){
		String format = "%-15s | %15s | %10s | %10s ";
		
		return String.format(format, "Number", "Type", "Holder", "Balance");
	}
	
	public String toString(){
		String format = "%-15s | %15s | %10s | %10s ";
		return String.format(format, number, typeToString(type), 
				holder, balance);
	}
}
