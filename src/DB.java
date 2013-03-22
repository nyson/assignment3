import java.sql.*;

/**
 * Singleton for handling our DB connection
 * @author Jonathan Skårstedt
 * @author Oskar Linder Pålsgård
 * @author Magnus Duberg
 *
 */
public class DB {
	private static Connection conn;
	private static boolean connected;
	private static DB instance = new DB();
	
	
	private DB (){
		open();		
	}
	
	public static DB getInstance(){
		return instance;		
	} 
	
	public static Connection getConnection(){
		return conn;		
	};
	
	private void open() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:wera.sqlite");

		} catch (SQLException e) {
			System.out.println("SQL error!");
			e.printStackTrace();
				
		} catch (ClassNotFoundException cnfe) {
			System.err.println("Couldn't find driver:");
			cnfe.printStackTrace();
			
		}
	}
	
	public Statement getStatement() throws SQLException{
		return conn.createStatement();
	}
	
}
