import java.util.*;
import java.sql.*;

public class Persons {
	public ArrayList<Person> getPersons() throws SQLException{
		ArrayList<Person> pers = new ArrayList<Person>();
		
		ResultSet rs = DB.getInstance()
				.getStatement()
				.executeQuery("SELECT * FROM person");
		
		while(rs.next()) {
			pers.add(new Person(rs.getString(1), 
						rs.getString(2), 
						rs.getInt(3),
						rs.getString(4)));
		}
		
		return pers;
	}
}
