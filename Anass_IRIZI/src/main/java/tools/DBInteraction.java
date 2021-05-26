package tools;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBInteraction {
	static private String url;
	static private Connection con;
	static private Statement stmt;
	
	public static void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		url = "jdbc:mysql://localhost:3306/Bibliotheque";
		try {
			con = DriverManager.getConnection(url, "root", "root");
		} catch (SQLException e) {
			System.out.println("Error in istablishing the connection in the "
							   +"connect method of the DBInteraction class");
			e.printStackTrace();
		}
		
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			System.out.println("Error in creating the statement in the "
					   +"connect method of the DBInteraction class");
			e.printStackTrace();
		}		
	}
	
	public static int update(String updateStatement) {
		int nbr = 0;
		
		try {
			nbr = stmt.executeUpdate(updateStatement);
		} catch (SQLException e) {
			System.out.println("Error in executing an update in the "
					   +"update method of the DBInteraction class");
			e.printStackTrace();
			
			return 0;
		}
		
		return nbr;
	}
	
	public static ResultSet query(String queryStatement) {
		ResultSet rs = null;
		
		try {
			rs = stmt.executeQuery(queryStatement);
		} catch (SQLException e) {
			System.out.println("Error in executing a query in the "
					   +"query method of the DBInteraction class");
			System.out.println("Here is the query statement:");
			System.out.println(queryStatement);
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public static int getNumberOfRows(ResultSet rs) {
		
		if (rs == null) {
			System.out.println("rs == null->getNumberOfRows()->DBInteraction: -1 will be returned");
			return -2;
		}
		try {
			// rs.last() -> jump to the last row
			// if rs.last() returns false, then the result set is empty
			if (!rs.last()) {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Sql Error->rs.last()->getNumberOfRows()->DBInteraction: -1 will be returned");
			return -1;
		}
		
		// rs.getRow() returns the number of the current row
		// since the current row is the last row, it will return
		// the number of rows in the result set
		int i = 0;
		try {
			i = rs.getRow();
		} catch (SQLException e) {
			System.out.println("Sql Error->rs.getRow()->getNumberOfRows()->DBInteraction: -1 will be returned");
			e.printStackTrace();
			return -1;
		}

		// jump back to the initial row position (before the 
		// first row)
		try {
			rs.beforeFirst();
		} catch (SQLException e) {
			System.out.println("Sql Error->rs.beforeFirst()->getNumberOfRows()->DBInteraction: -1 will be returned");
			e.printStackTrace();
			return -1;
		}
		
		return i;
	}
	
	public static void disconnect() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Error in closing the connection in the "
					   +"disconnect method of the DBInteraction class");
			e.printStackTrace();
		}
	}

/*
	public static void main(String[] args) {
		System.out.println("salamo");
		connect();
		//	Changes to the database will be fetched only if a commit was executed
		ResultSet rs = query("select * from admins where admin_id = 44");
//		try {
//			if (!rs.next()) {
//				System.out.println("rs is empty");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			while (rs.next()) {
					System.out.println("salamo2");
					System.out.println("id = "+rs.getInt(1));
					System.out.println("nom = "+rs.getString(2));
					System.out.println("prenom = "+rs.getString(3));
					System.out.println("login = "+rs.getString(4));
					System.out.println("password = "+rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error 2");
		}
	}
*/

}
