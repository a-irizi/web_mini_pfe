package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Admin;
import tools.DBInteraction;

public class AdminDAO {

	public static List<Admin> getAllAdmin() {
		List<Admin> la = new ArrayList<Admin>();

		DBInteraction.connect();
		String query = "SELECT * FROM Admins";
		
		
		ResultSet rs = DBInteraction.query(query);
		int nr = DBInteraction.getNumberOfRows(rs);
		// if the result set is empty, or an sql error occurred, then return null
		if (nr == 0 || nr == -1 || nr == -2) {
			System.out.println(query);
			return null;
		}

		Admin adm = new Admin();
		try {
			while (rs.next()) {
				adm.setAdmin_id(rs.getInt("admin_id"));
				adm.setNom(rs.getString("nom"));
				adm.setPrenom(rs.getString("prenom"));
				adm.setLogin(rs.getString("login"));
				adm.setPassword(rs.getString("password"));
				
				la.add(adm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		DBInteraction.disconnect();		
		
		return la;
	}

	public static Admin checkAdmin(int admin_id) {
		Admin et = new Admin();
		
		DBInteraction.connect();
		
		String query = "select * from "
				   + "Admins "
				   + "where admin_id = "+admin_id
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
			et.setAdmin_id(rs.getInt("admin_id"));
			et.setNom(rs.getString("nom"));
			et.setPrenom(rs.getString("prenom"));
			et.setLogin(rs.getString("login"));
			et.setPassword(rs.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
		DBInteraction.disconnect();		
		
		return et;
	}

	public static Admin checkAdmin(String login){
		Admin et = new Admin();
		
		DBInteraction.connect();
		
		String query = "select * from "
				   + "Admins "
				   + "where login = '"+login+"'"		
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
			et.setAdmin_id(rs.getInt("admin_id"));
			et.setNom(rs.getString("nom"));
			et.setPrenom(rs.getString("prenom"));
			et.setLogin(rs.getString("login"));
			et.setPassword(rs.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		DBInteraction.disconnect();		
		
		return et;
	}

	public static Admin checkAdmin(String login, String password){
		Admin et = new Admin();
		
		DBInteraction.connect();
		
		String query = "select * from "
				   + "Admins "
				   + "where login = '"+login+"'"		
				   + "and password = '"+password+"'"
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
			et.setAdmin_id(rs.getInt("admin_id"));
			et.setNom(rs.getString("nom"));
			et.setPrenom(rs.getString("prenom"));
			et.setLogin(rs.getString("login"));
			et.setPassword(rs.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		DBInteraction.disconnect();		
		
		return et;
	}

	public static boolean addAdmin(Admin admin) {	
		
		// if admin already axists in the database
		// then return false
		if (checkAdmin(admin.getLogin()) != null) {
			return false;
		}
		
		DBInteraction.connect();

		String update = "insert into Admins (nom, prenom, login, password) "
						+ "values ('"+admin.getNom()+"',"
						+ "			'"+admin.getPrenom()+"',"
						+ "			'"+admin.getLogin()+"',"
						+ "			'"+admin.getPassword()+"',"
						+ "			'"+admin.getPrenom()+"')"
						+ ";";
		
		// the variable admin does not hage the id attribute
		// fetch all the attributes from the database inluding the id
		Admin temp = checkAdmin(admin.getLogin());
		// fill the admin id attributes that you fetched from the database
		admin.setAdmin_id(temp.getAdmin_id());
	
		// if an sql error occured then return null
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();
		
		return true;
	}

	public static boolean updateAdmin(Admin adm) {		
		DBInteraction.connect();

		String update = "update admins "
				 + "set"
				 + "	nom = '"+adm.getNom()+"', "
				 + "        prenom = '"+adm.getPrenom()+"', "
				 + "        login = '"+adm.getLogin()+"', "
				 + "        password = '"+adm.getPassword()+"', "
				 + "where"
				 + "	admin_id = "+adm.getAdmin_id()
				 + ";";
		// if an sql error occured then return null
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();
		
		return true;
	}
	
	public static boolean deleteAdmin(int admin_id) {
		DBInteraction.connect();

		String update = "DELETE FROM admins "
				      + "where"
				      + "	admin_id = "+admin_id
				      + ";";
		// if an sql error occured then return null
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();
		
		return true;
	}

}
