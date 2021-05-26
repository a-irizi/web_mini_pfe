package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Categorie;
import tools.DBInteraction;

public class CategorieDAO {

	public static List<Categorie> getAllCategories() {
		List<Categorie> lc = new ArrayList<Categorie>();

		DBInteraction.connect();
		String query = "SELECT * FROM Categories";
		
		
		ResultSet rs = DBInteraction.query(query);
		int nr = DBInteraction.getNumberOfRows(rs);
		// if the result set is empty, or an sql error occurred, then return null
		if (nr == 0 || nr == -1 || nr == -2) {
			System.out.println(query);
			return null;
		}
		
		Categorie cat = new Categorie();
		try {
			while (rs.next()) {
					cat.setCategorie_id(rs.getInt("categorie_id"));
					cat.setCategorie(rs.getString("categorie"));
				lc.add(cat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		DBInteraction.disconnect();		
		
		return lc;
	}

	public static Categorie checkCategorie(int categorie_id) {
		Categorie cat = new Categorie();
		
		DBInteraction.connect();
		
		String query = "select * from "
				   + "Categories "
				   + "where categorie_id = "+categorie_id
				   + ";";
		
		ResultSet rs = DBInteraction.query(query);
		int nr = DBInteraction.getNumberOfRows(rs);
		// if the result set is empty, or an sql error occurred, then return null
		if (nr == 0 || nr == -1 || nr == -2) {
			System.out.println(query);
			return null;
		}
		
		try {
			rs.next();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}

		try {
			cat.setCategorie_id(rs.getInt("categorie_id"));
			cat.setCategorie(rs.getString("categorie"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		DBInteraction.disconnect();		
		
		return cat;
	}

	public static Categorie checkCategorie(String categorie){
		Categorie cat = new Categorie();
	
		DBInteraction.connect();
		
		String query = "select * from "
				   + "Categories "
				   + "where categorie = '"+categorie+"'"		
				   + ";";
		
		ResultSet rs = DBInteraction.query(query);
		int nr = DBInteraction.getNumberOfRows(rs);
		// if the result set is empty, or an sql error occurred, then return null
		if (nr == 0 || nr == -1 || nr == -2) {
			System.out.println(query);
			return null;
		}
		
		try {
			rs.next();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
		try {
			cat.setCategorie_id(rs.getInt("categorie_id"));
			cat.setCategorie(rs.getString("categorie"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		DBInteraction.disconnect();		
		
		return cat;
	}

	public static boolean addCategorie(Categorie categorie) {	
		
		// if admin already axists in the database
		// then return false
		if (checkCategorie(categorie.getCategorie_id()) != null) {
			return false;
		}
		
		DBInteraction.connect();

		String update = "insert into Admins (categorie) "
						+ "values ('"+categorie.getCategorie()+"')"
						+ ";";
		
		// the variable categorie does not hage the id attribute
		// fetch all the attributes from the database inluding the id
		Categorie temp = checkCategorie(categorie.getCategorie());
		// fill the categorie id attributes that you fetched from the database
		categorie.setCategorie_id(temp.getCategorie_id());
		
		// if an sql error occured then return null
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();
		
		return true;
	}

	public static boolean updateCategorie(Categorie cat) {		
		DBInteraction.connect();
		
		
		// if categorie does not exist return false
		if (CategorieDAO.checkCategorie(cat.getCategorie_id()) == null) {
			return false;
		}

		String update = "update Categories "
				 + "set"
				 + "	categorie = '"+cat.getCategorie()+"' "				 
				 + "where "
				 + "	etudiant_id = "+cat.getCategorie_id()
				 + ";";

		// if an sql error occured then return false
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();

		return true;
	}
	
	public static boolean deleteCategorie(int categorie_id) {
		DBInteraction.connect();

		String update = "DELETE FROM Categories "
				      + "where "
				      + "	categorie_id = "+categorie_id
				      + ";";
		// if an sql error occured then return null
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();
		
		return true;
	}

}
