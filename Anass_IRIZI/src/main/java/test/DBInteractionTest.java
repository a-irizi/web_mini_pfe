package test;

import java.sql.ResultSet;
import java.sql.SQLException;

import tools.DBInteraction;

public class DBInteractionTest {

	public static void main(String[] args) {
		DBInteraction.connect();
		String query = "select * from Filieres";
		
		ResultSet rs = DBInteraction.query(query);
		int nr = DBInteraction.getNumberOfRows(rs);
		System.out.println("number of rows = " + nr);
		
		try {
			while (rs.next()) {
				System.out.println("*********************************************************");
				System.out.println("  filire_id = " + rs.getInt("filiere_id"));
				System.out.println("     filire = " + rs.getString("filiere"));
				System.out.println("description = " + rs.getString("description"));
				System.out.println("*********************************************************");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBInteraction.disconnect();
	}

}
