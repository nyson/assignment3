import java.sql.SQLException;
import java.util.*;

public class testDBUsage {
	public static void main(String[] args) {
		System.out.println("Hello!");
		Persons pers = new Persons();
		
		try {
			System.out.println(pers.getPersonByName("Sylvester"));
			System.out.println(pers.getPersonByName("Yosemite Hitler"));

		} catch (NoSuchRowException e) {
			System.out.println(e.getMessage());
		
		} catch (SQLException e) {
			System.out.println("SQL error: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		
	}
}
