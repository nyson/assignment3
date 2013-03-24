import java.util.*;
import java.sql.*;

public class Persons {
	/**
	 * Creates a person in the database
	 * 
	 * @param name Name of the person 
	 * @param city City of the person
	 * @param addr Adress of the person
	 * @param zip Zip code of the person
	 * @return True on success
	 * @throws SQLException
	 */
	public boolean add(String name, String city, String addr, int zip) 
			throws SQLException{
		

		PreparedStatement ps 
			= DB.prepareStatement("SELECT name FROM person WHERE name = ?");
		ps.setString(1, name);
		ResultSet r = ps.executeQuery();
		
		if(r.next()){
			throw new SQLException("The account already exists!");			
		}
		
		ps.close(); r.close();
		
		ps = DB.prepareStatement
				("INSERT INTO person (name, gatuadress, postnr, stad)"
				+ "VALUES (?, ?, ?, ?)");
		ps.setString(1, name);
		ps.setString(2, addr);
		ps.setInt(3, zip);
		ps.setString(4, city);
		
		int updated = ps.executeUpdate();
		
		ps.close();

		return updated > 0;
	}
	
	public boolean exists(String name) throws SQLException{
		try {
			getPersonByName(name);
			return true;
		} catch (NoSuchRowException e) {
			return false;
		}
		
		
	}
	
	public Person getPersonByName(String name) 
			throws SQLException, NoSuchRowException{
		String query = "SELECT * FROM person WHERE name = ?";
		
		PreparedStatement pstate = DB.prepareStatement(query); 
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
