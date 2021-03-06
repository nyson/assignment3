
import java.util.*;
import java.sql.*;


public class Accounts {
	/**
	 * Adds a new account to the database
	 * 
	 * @param holder Holder of the account, needs to already be in the database
	 * @param number Account number
	 * @param balance Account balance
	 * @param type Account type
	 * @return true on success
	 * @throws SQLException
	 */
	public boolean addAccount(String holder, String number, double balance, 
			Account.Type type) throws SQLException{
		
		PreparedStatement ps = DB.prepareStatement
				("SELECT name FROM person WHERE name = ?");
		
		ps.setString(1, holder);
		ResultSet r = ps.executeQuery();
		
		if(!r.next()) {
			throw new SQLException("The owner of the account does not exist!");
		}
		
		ps.close(); r.close();
		
		ps = DB.prepareStatement("SELECT kontonr FROM konto WHERE kontonr = ?");
		ps.setString(1, number);
		r = ps.executeQuery();
		
		if(r.next()){
			throw new SQLException("The account already exists!");			
		}
		
		ps.close(); r.close();
		
		ps = DB.prepareStatement("INSERT INTO konto (kontonr, kontotyp, namn, saldo)"
				+ "VALUES (?, ?, ?, ?)");
		ps.setString(1, number);
		ps.setString(2, Account.typeToString(type));
		ps.setString(3, holder);
		ps.setDouble(4, balance);
		
		int updated = ps.executeUpdate();
		
		ps.close(); r.close();

		return updated > 0;
	}
	
	/**
	 * Fetches an arraylist of all the accounts in the application
	 *  
	 * @return All accounts in the system
	 * @throws SQLException Bad SQL or DB Connectivity
	 */
	public ArrayList<Account> getAccounts() throws SQLException{
		ArrayList<Account> accounts = new ArrayList<Account>();
		Statement state = DB.getInstance()
				.getStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM konto");
		

		while(rs.next()) {
			try {
				accounts.add(new Account(rs.getString(1), 
							rs.getString(2), 
							rs.getString(3),
							rs.getDouble(4)));
			} catch (Account.BadAccountTypeException e) {
				System.out.println(e.getMessage());
			}
		}
		
		
		rs.close();
		state.close();
		return accounts;
	}
	
	
	/**
	 * Checks if account exists and returns true or false
	 * 
	 * @param accountNo Account number to check
	 * @return true on existing account number else false
	 * @throws SQLException
	 */
	public boolean accountExists(String accountNo)
			throws SQLException{
		PreparedStatement s = DB.prepareStatement
			("SELECT kontonr FROM konto WHERE kontonr = ?");
		
		s.setString(1, accountNo);
		
		ResultSet r = s.executeQuery();
		
		return r.next();
	}
	
	/**
	 * Transfers money from an account to another
	 * 
	 * @param a Account to withdraw from
	 * @param amount
	 * @param b Account to deposit to
	 * @throws NotEnoughMineralsException
	 * @throws SQLException
	 */
	public void transfer(String a, double amount, String b) 
			throws NotEnoughMineralsException, SQLException{
		
		getAccountByAccountNo(a).withdraw(amount);
		getAccountByAccountNo(b).deposit(amount);
	}
	
	/**
	 * Fetches a list of transactions belonging to a specific account
	 * 
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Transaction> getTransactionsByAccount(String account)
			throws SQLException{
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		String query 
			= "SELECT kontonr, typ, belopp, OCRmeddelande FROM gjordatrans "
				+ "WHERE kontonr = ?";
		PreparedStatement st = DB.prepareStatement(query);
		st.setString(1, account);		
		
		ResultSet transult = st.executeQuery();
		
		while(transult.next()) {
			Transaction.Type t;
			switch(transult.getString(2)){
			case "ins":
				t = Transaction.Type.DEPOSIT;
				break;
				
			default:
			case "utt":
				t = Transaction.Type.WITHDRAWAL;
				break;
			}
			try {
				transactions.add(new Transaction(
						transult.getString(1), 
						t, 
						transult.getDouble(3), 
						transult.getString(4)));
			} catch (Transaction.InvalidOCRException e) {
				System.out.println("Invalid OCR: " + e);
			}
		}
		
		return transactions;		
	}
	
	/**
	 * Fetches a list of all transactions in the database 
	 * 
	 * @return a list of all transactions
	 * @throws SQLException
	 */
	public ArrayList<Transaction> getTransactions()
			throws SQLException{
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
		Statement st = DB.getInstance().getStatement();
		ResultSet transult = st.executeQuery
				("SELECT kontonr, typ, belopp, OCRmeddelande FROM gjordatrans");
		
		while(transult.next()) {
			Transaction.Type t;
			switch(transult.getString(2)){
			case "ins":
				t = Transaction.Type.DEPOSIT;
				break;
				
			default:
			case "utt":
				t = Transaction.Type.WITHDRAWAL;
				break;
			}
			try {
				transactions.add(new Transaction(
						transult.getString(1), 
						t, 
						transult.getDouble(3), 
						transult.getString(4)));
			} catch (Transaction.InvalidOCRException e) {
				System.out.println("Invalid OCR: " + e);
			}
		}
		
		return transactions;
	}
	
	/**
	 * Fetches an account by account number
	 * @param accountno Account number
	 * @return An account instance from the db
	 * @throws SQLException
	 * @throws NoSuchRowException
	 */
	public Account getAccountByAccountNo(String accountno)
		throws SQLException, NoSuchRowException {
		String query = "SELECT * FROM konto WHERE kontonr = ?";
		
		PreparedStatement statement = DB.prepareStatement(query); 
		statement.setString(1, accountno);
		
		ResultSet rsa = statement.executeQuery();
		
		if(!rsa.next())
			throw new NoSuchRowException
				("No Account with key '" + accountno + "'!");
		
		Account.Type t;
		switch(rsa.getString(2)) {
		case "spar":
			t = Account.Type.SAVINGS;
			break;
			
		default:
		case "loen":
			t = Account.Type.WAGE;
			break;
			
		}
	
		
		Account account = new Account(rsa.getString(1), t,
			rsa.getString(3), rsa.getDouble(4));
	
		rsa.close();
		statement.close();
		
		return account;
	}
}
