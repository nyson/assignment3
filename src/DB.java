import java.sql.*;
public class DB {
	private static Connection conn;
	//private static boolean connected;
	private static DB instance = new DB();
	
	
	private DB (){
		open();		
	}
	
	public static DB getInstance(){
		return instance;		
	} 
	
	private void open() {
		//connected = false;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager
					.getConnection("jdbc:sqlite:wera.sqlite");
			//connected = true;

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
