import java.sql.*;

/**
 * Class for handling a 
 * @author nyson
 *
 */
public class Person {
	private String key;
	private String name, addr, city;
	private int zip;
	
	public Person(String pName, String pAddr, int pZip, String pCity) {
		name = pName; addr = pAddr; zip = pZip; city = pCity;
		key = pName;
	}
	
	public boolean push() throws SQLException {
		String query = 
			"UPDATE person "
				+ "SET name=?, "
				+ "gatuadress=?, "
				+ "postnummer=?, "
				+ "stad=? " 
			+ "WHERE name=?"; 
		
		PreparedStatement push = DB.getConnection().prepareStatement(query);
		push.setString(1, name);
		push.setString(2, addr);
		push.setInt(3, zip);
		push.setString(4, city);
		push.setString(5, key);
		int updated = push.executeUpdate();
		
		push.close();
		
		return updated > 0;
	}
	
	public String toString(){
		return "Name: " + name + "\n\tAdress: " + addr 
				+ "\n\tPostNr: " + zip + "\n\tCity: " + city;
		
	}
}
