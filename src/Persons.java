import java.util.*;
import java.sql.*;

public class Persons {
	public Person getPersonByName(String name) 
			throws SQLException, NoSuchRowException{
		String query = "SELECT * FROM person WHERE name = ?";
		
		PreparedStatement pstate = DB.getConnection().prepareStatement(query); 
		pstate.setString(1, name);
		
		ResultSet pers = pstate.executeQuery();
		
		if(!pers.next())
			throw new NoSuchRowException("No person with key '" + name + "'!");
		
		Person result = new Person(pers.getString(1), pers.getString(2),
				pers.getInt(3), pers.getString(4));
		
		pers.close();
		pstate.close();
		
		return result;
	}
	
	public ArrayList<Person> getPersons() throws SQLException{
		ArrayList<Person> pers = new ArrayList<Person>();
		
		Statement persons = DB.getInstance()
				.getStatement(); 
		ResultSet rs = 
				persons.executeQuery("SELECT * FROM person");
		
		while(rs.next()) {
			pers.add(new Person(rs.getString(1), 
						rs.getString(2), 
						rs.getInt(3),
						rs.getString(4)));
		}
		
		rs.close();
		persons.close();
		
		return pers;
	}
}
