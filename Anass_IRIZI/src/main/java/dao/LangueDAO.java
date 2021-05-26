package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Langue;
import tools.DBInteraction;

public class LangueDAO {
	public static List<Langue> getAllLangues() {
		List<Langue> ll = new ArrayList<Langue>();

		DBInteraction.connect();
		String query = "SELECT * FROM Langues";
		
		
		ResultSet rs = DBInteraction.query(query);
		int nr = DBInteraction.getNumberOfRows(rs);
		// if the result set is empty, or an sql error occurred, then return null
		if (nr == 0 || nr == -1 || nr == -2) {
			return null;
		}

		Langue lan = new Langue();
		try {
			while (rs.next()) {
					lan.setLangue_id(rs.getInt("langue_id"));
					lan.setLangue(rs.getString("langue"));
				ll.add(lan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		DBInteraction.disconnect();		
		
		return ll;
	}

	public static Langue checkLangue(int langue_id) {
		Langue lan = new Langue();
		
		DBInteraction.connect();
		
		String query = "select * from"
				   + "Langues"
				   + "where langue_id = "+langue_id
				   + ";";
		
		ResultSet rs = DBInteraction.query(query);
		int nr = DBInteraction.getNumberOfRows(rs);
		// if the result set is empty, or an sql error occurred, then return null
		if (nr == 0 || nr == -1 || nr == -2) {
			return null;
		}
		
		try {
			rs.next();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}

		try {
			lan.setLangue_id(rs.getInt("langue_id"));
			lan.setLangue(rs.getString("langue"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		DBInteraction.disconnect();		
		
		return lan;
	}

	public static Langue checkLangue(String langue){
		Langue lan = new Langue();
	
		DBInteraction.connect();
		
		String query = "select * from"
				   + "Langues"
				   + "where langue = '"+langue+"'"		
				   + ";";
		
		ResultSet rs = DBInteraction.query(query);
		int nr = DBInteraction.getNumberOfRows(rs);
		// if the result set is empty, or an sql error occurred, then return null
		if (nr == 0 || nr == -1 || nr == -2) {
			return null;
		}
		
		try {
			rs.next();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}

		try {
			lan.setLangue_id(rs.getInt("langue_id"));
			lan.setLangue(rs.getString("langue"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		DBInteraction.disconnect();		
		
		return lan;
	}

	public static boolean addLangue(Langue langue) {	
		
		// if admin already axists in the database
		// then return false
		if (checkLangue(langue.getLangue()) != null) {
			return false;
		}
		
		DBInteraction.connect();

		String update = "insert into Langues (langue)"
						+ "values ('"+langue.getLangue()+"')"
						+ ";";

		// the variable categorie does not hage the id attribute
		// fetch all the attributes from the database inluding the id
		Langue temp = checkLangue(langue.getLangue());
		// fill the categorie id attributes that you fetched from the database
		langue.setLangue_id(temp.getLangue_id());

		// if an sql error occured then return null
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();
		
		return true;
	}

	public static boolean updateLangue(Langue lan) {		
		DBInteraction.connect();
		
		
		// if langue does not exist return false
		if (LangueDAO.checkLangue(lan.getLangue_id()) == null) {
			return false;
		}

		String update = "update Langues "
				 + "set "
				 + "	langue = '"+lan.getLangue()+"' "				 
				 + "where "
				 + "	etudiant_id = "+lan.getLangue_id()
				 + ";";

		// if an sql error occured then return false
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();
		
		return true;
	}
	
	public static boolean deleteLangue(int langue_id) {
		DBInteraction.connect();

		String update = "DELETE FROM Langues "
				      + "where "
				      + "	langue_id = "+langue_id
				      + ";";
		// if an sql error occured then return null
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();
		
		return true;
	}

}