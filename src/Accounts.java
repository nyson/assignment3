
import java.util.*;
import java.sql.*;


public class Accounts {
	/**
	 * Adds a new account to the database
	 * 
	 * @param holder Name of the holder, and key to the person table
	 * @param number Account number. Must be unique
	 * @param balance Account balance
	 * @param type
	 * @return
	 * @throws SQLException
	 */
	public boolean add(String holder, String number, double balance, 
			Account.Type type) 
			throws SQLException, NotEnoughMineralsException{
				
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
	 * Fetches an account by account number
	 * @param accountno Account number
	 * @return An account instance from the db
	 * @throws SQLException
	 * @throws NoSuchRowException
	 */
	public Account getAccountByAccountNo(String accountno)
		throws SQLException, NoSuchRowException, 
			Account.BadAccountTypeException{
		String query = "SELECT * FROM konto WHERE kontonr = ?";
		
		PreparedStatement statement = DB.prepareStatement(query); 
		statement.setString(1, accountno);
		
		ResultSet rsa = statement.executeQuery();
		
		if(!rsa.next())
			throw new NoSuchRowException
				("No Account with key '" + accountno + "'!");
		
		Account account = new Account(rsa.getString(1), rsa.getString(2),
				rsa.getString(3), rsa.getDouble(4));
		
		rsa.close();
		statement.close();
		
		return account;
	}
}
