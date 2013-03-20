import java.sql.*;

public class DBtest {
	public static void main(String[] argv) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:wera.sqlite");
			Statement s = c.createStatement();			
			ResultSet rs = s.executeQuery("SELECT * FROM gjordatrans");
			
			System.out.println("ID\t| Kontonummer\t| Typ\t| Belopp\t"
						+ "| OCR");
			
			
			for(int index = 0; rs.next(); index++) {
				System.out.println(//rs.getString(0) + "\t| "
						rs.getString(1) + " \t| "
						+ rs.getString(2) + "\t| "
						+ rs.getString(3) + "\t| "
						+ rs.getString(4));
			}
				
			System.out.println("Ingen stress!");	
		} catch (SQLException e) {
			System.out.println("SQL error!");
			e.printStackTrace();
				
		}catch (ClassNotFoundException cnfe) {
			System.err.println("Couldn't find driver class:");
			cnfe.printStackTrace();
		}
		
			
		

	}
}
