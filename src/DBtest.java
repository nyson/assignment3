import java.sql.*;

public class DBtest {
	public static void main(String[] argv) {
		try { 
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException cnfe) {
			System.err.println("Couldn't find driver class:");
			cnfe.printStackTrace();
			System.exit(1);
		}
		
		System.out.println("Allt OK");
	}
}
