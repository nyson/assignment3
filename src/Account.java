
public class Account {
	public enum Type {SAVINGS, WAGE};
	
	/**
	 * Exception for bad account types, ie not in AccountType range
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
	 * Exception for lack of funds in the account
	 * @author Jonathan Skårstedt
	 * @author Oskar Linder Pålsgård
	 * @author Magnus Duberg
	 */
	public class NotEnoughMineralsException extends Exception {
		static final long serialVersionUID = 13945013;
		public NotEnoughMineralsException(String message) {
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
	 * Deposits set amount to account
	 * @param amount Amount to deposit
	 * @return New account balance
	 */
	public double deposit(double amount){
		balance += amount;
		return balance;
	}
	
	/**
	 * Withdraws amount from account
	 * @param amount Amount to withdrwas
	 * @return New account balance 
	 * @throws NotEnoughMineralsException on lack of funds
	 */
	public double withdraw(double amount) throws NotEnoughMineralsException {
		if(balance - amount < 0)
			throw new NotEnoughMineralsException
				("You don't have that much in the bank!");
		
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
