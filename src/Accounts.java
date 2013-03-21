
import java.util.*;
import java.sql.*;


public class Accounts {
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Account> getAccounts() throws SQLException{
		
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		ResultSet rs = DB.getInstance()
				.getStatement()
				.executeQuery("SELECT * FROM konto");
		
		while(rs.next()) {
			accounts.add(new Account(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3),
						rs.getDouble(4)));
		}
		
		return accounts;
	}
}
