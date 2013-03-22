import java.sql.SQLException;
import java.util.*;

public class testDBUsage {
	public static void main(String[] args) {
		System.out.println("Hello!");
		Persons pers = new Persons();
		Accounts accs = new Accounts();
		try {
			System.out.println("Fetches accounts...");
			System.out.println(Account.toStringHeader());
			for(Account a : accs.getAccounts()) {
				System.out.println(a);
			}
			
			System.out.println("\nFetches persons...");
			System.out.println(Person.toStringHeader());
			for(Person p : pers.getPersons()) {
				System.out.println(p);
			}
			
			Account a = accs.getAccountByAccountNo("121223");
			System.out.println(a);
			a.deposit(200);
			System.out.println(a);
			try {
				a.withdraw(200);
			} catch (Account.NotEnoughMineralsException e) {
				System.out.println("Not enough minerals!");
			}
			
						
		} catch (NoSuchRowException e) {
			System.out.println(e.getMessage());
		
		} catch (SQLException e) {
			System.out.println("SQL error: " + e.getLocalizedMessage());
			e.printStackTrace();
		} catch (Account.BadAccountTypeException e) {
			e.printStackTrace();
		}
		
	}
}
