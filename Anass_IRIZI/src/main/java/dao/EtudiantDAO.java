package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Etudiant;
import models.Filiere;
import tools.DBInteraction;

public class EtudiantDAO {
	public static List<Etudiant> getAllStudents() {
		List<Etudiant> le = new ArrayList<Etudiant>();

		DBInteraction.connect();
		String query = "SELECT * FROM Etudiants_Infos_Complets";
		
		
		ResultSet rs = DBInteraction.query(query);
		int nr = DBInteraction.getNumberOfRows(rs);
		// if the result set is empty, or an sql error occurred, then return null
		if (nr == 0 || nr == -1 || nr == -2) {
			System.out.println(query);
			return null;
		}

		Etudiant etud = new Etudiant();
		try {
			while (rs.next()) {
					etud.setEtudiant_id(rs.getInt("etudiant_id"));
					etud.setNom(rs.getString("nom"));
					etud.setPrenom(rs.getString("prenom"));
					etud.setLogin(rs.getString("login"));
					etud.setPassword(rs.getString("password"));
					etud.setFiliere(rs.getString("filiere"));

				le.add(etud);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		DBInteraction.disconnect();		
		
		return le;
	}

	public static Etudiant checkStudent(int etudiant_id) {
		Etudiant et = new Etudiant();
		
		DBInteraction.connect();
		
		String query = "select * from "
				   + "Etudiants_Infos_Complets "
				   + "where etudiant_id = "+etudiant_id
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
			et.setEtudiant_id(rs.getInt("etudiant_id"));
			et.setNom(rs.getString("nom"));
			et.setPrenom(rs.getString("prenom"));
			et.setLogin(rs.getString("login"));
			et.setPassword(rs.getString("password"));
			et.setFiliere(rs.getString("filiere"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
		DBInteraction.disconnect();		
		
		return et;
	}

	public static Etudiant checkStudent(String login){
		Etudiant et = new Etudiant();
		
		DBInteraction.connect();
		
		String query = "select * from "
				   + "Etudiants_Infos_Complets "
				   + "where login = '"+login+"' "		
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
			et.setEtudiant_id(rs.getInt("etudiant_id"));
			et.setNom(rs.getString("nom"));
			et.setPrenom(rs.getString("prenom"));
			et.setLogin(rs.getString("login"));
			et.setPassword(rs.getString("password"));
			et.setFiliere(rs.getString("filiere"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		DBInteraction.disconnect();		
		
		return et;
	}

	public static Etudiant checkStudent(String login, String password){
		Etudiant et = new Etudiant();
		
		DBInteraction.connect();
		
		String query = "select * from "
				   + "Etudiants_Infos_Complets "
				   + "where login = '"+login+"' "		
				   + "and password = '"+password+"' "
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
			et.setEtudiant_id(rs.getInt("etudiant_id"));
			et.setNom(rs.getString("nom"));
			et.setPrenom(rs.getString("prenom"));
			et.setLogin(rs.getString("login"));
			et.setPassword(rs.getString("password"));
			et.setFiliere(rs.getString("filiere"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		DBInteraction.disconnect();		
		
		return et;
	}

	public static boolean addEtudiant(Etudiant etudiant) {	

		// if admin already axists in the database
		// then return false
		if (checkStudent(etudiant.getLogin()) != null) {
			return false;
		}
//		System.out.println("etudiant.getFiliere()->addEtudiant()->EtudiantDAO = "+etudiant.getFiliere());
		Filiere filiere = FiliereDAO.checkFiliere(etudiant.getFiliere());
		
		DBInteraction.connect();
		

		String update = "insert into Etudiants (nom, prenom, login, password, filiere_id) "
						+ "values ('"+etudiant.getNom()+"',"
						+ " '"+etudiant.getPrenom()+"',"
						+ "	'"+etudiant.getLogin()+"',"
						+ "	'"+etudiant.getPassword()+"',"
						+ "	'"+filiere.getFiliere_id()+"')"
						+ ";";
//		System.out.println("Here is the addEtudiant query:");
//		System.out.println(update);
//		System.out.println("etudiant.getFiliere() = "+etudiant.getFiliere());
		// if an sql error occured then return null
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		// the variable admin does not hage the id attribute
		// fetch all the attributes from the database inluding the id
		Etudiant temp = checkStudent(etudiant.getLogin());
		// fill the admin id attributes that you fetched from the database
		etudiant.setEtudiant_id(temp.getEtudiant_id());
	
		
		DBInteraction.disconnect();
		
		return true;
	}

	public static boolean updateStudent(Etudiant etud) {		
		DBInteraction.connect();
		
		// if filiere does not exist return false
		if (FiliereDAO.checkFiliere(etud.getFiliere()) == null) {
			return false;
		}

		int fid = FiliereDAO.checkFiliere(etud.getFiliere()).getFiliere_id();
		String update = "update etudiants "
				 + "set"
				 + "	nom = '"+etud.getNom()+"', "
				 + "        prenom = '"+etud.getPrenom()+"', "
				 + "        login = '"+etud.getLogin()+"', "
				 + "        password = '"+etud.getPassword()+"', "
				 + "        filiere_id = '"+fid+"' "
				 + "where "
				 + "	etudiant_id = "+etud.getEtudiant_id()
				 + ";";
		// if an sql error occured then return null
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();
		
		return true;
	}
	
	public static boolean deleteStudent(int etudiant_id) {
		DBInteraction.connect();

		String update = "DELETE FROM etudiants "
				      + "where "
				      + "	etudiant_id = "+etudiant_id
				      + ";";
		// if an sql error occured then return null
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();
		
		return true;
	}

}