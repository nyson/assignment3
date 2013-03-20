import java.sql.SQLException;
import java.util.*;

public class testDBUsage {
	public static void main(String[] args) {
		System.out.println("Hello!");
		Persons pers = new Persons();
		
		try {
			ArrayList<Person> everyhopa = pers.getPersons();
			for(Person p: everyhopa) {
				System.out.println(p);
			}
		} catch (SQLException e) {
			System.out.println("SQL error: ");
			e.getStackTrace();
		}
		
	}
}
