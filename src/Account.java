
public class Account {

	// Private attributes
	private int accountNo;
	private String accountType, accountHolder;
	private double accountBalance;
	
	// Constructor
	public Account(int accNr, String accType,
			String accHolder, double accBalance){
		accountNo = accNr; accountType = accType;
		accountHolder = accHolder; accountBalance = accBalance;
	}
	
	// Getters
	public int getAccountNo() {return accountNo;}
	public String getAccountType() {return accountType;}
	public String getAccountHolder() {return accountHolder;}
	public double getAccountBalance() {return accountBalance;}
	// Setters
	public void setAccountNo(int accountNo) 
		{this.accountNo = accountNo;}
	public void setAccountType(String accountType) {
		this.accountType = accountType;}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;}
}
