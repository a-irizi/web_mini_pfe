package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Filiere;
import tools.DBInteraction;

public class FiliereDAO {

	public static List<Filiere> getAllFilieres() {
		List<Filiere> le = new ArrayList<Filiere>();

		DBInteraction.connect();
		String query = "SELECT * FROM Filieres";
		
		
		ResultSet rs = DBInteraction.query(query);
		int nr = DBInteraction.getNumberOfRows(rs);
		// if the result set is empty, or an sql error occurred, then return null
		if (nr == 0 || nr == -1 || nr == -2) {
			System.out.println(query);
			return null;
		}
		
		Filiere fil = new Filiere();
		try {
			while (rs.next()) {
					fil.setFiliere_id(rs.getInt("filiere_id"));
					fil.setFiliere(rs.getString("filiere"));
					fil.setDescription(rs.getString("description"));
				le.add(fil);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		DBInteraction.disconnect();		
		
		return le;
	}

	public static Filiere checkFiliere(int filiere_id) {
		Filiere fil = new Filiere();
		
		DBInteraction.connect();
		
		String query = "select * from "
				   + "Filieres "
				   + "where filiere_id = "+filiere_id
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
			fil.setFiliere_id(rs.getInt("filiere_id"));
			fil.setFiliere(rs.getString("filiere"));
			fil.setDescription(rs.getString("description"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		DBInteraction.disconnect();		
		
		return fil;
	}

	public static Filiere checkFiliere(String filiere){
		Filiere fil = new Filiere();
	
		DBInteraction.connect();
		
		String query = "select * from "
				   + "Filieres "
				   + "where filiere = '"+filiere+"'"		
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
			fil.setFiliere_id(rs.getInt("filiere_id"));
			fil.setFiliere(rs.getString("filiere"));
			fil.setDescription(rs.getString("description"));
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}

			
		DBInteraction.disconnect();		
		
		return fil;
	}

//	public static boolean addEtudiant(Filiere etudiant) {	
//
//		// if admin already axists in the database
//		// then return false
//		if (checkStudent(etudiant.getLogin()) != null) {
//			return false;
//		}
//				
//		DBInteraction.connect();
//		
//		System.out.println("etudiant.getFiliere()->addEtudiant()->EtudiantDAO = "+etudiant.getFiliere());
//
//		String update = "insert into Etudiants (nom, prenom, login, password, filiere_id) "
//						+ "values ('"+etudiant.getNom()+"',"
//						+ " '"+etudiant.getPrenom()+"',"
//						+ "	'"+etudiant.getLogin()+"',"
//						+ "	'"+etudiant.getPassword()+"',"
//						+ "	'"+filiere.getFiliere_id()+"')"
//						+ ";";
//		System.out.println("Here is the addEtudiant query:");
//		System.out.println(update);
//		System.out.println("etudiant.getFiliere() = "+etudiant.getFiliere());
//		// if an sql error occured then return null
//		if (DBInteraction.update(update) == 0) {
//			return false;
//		}
//
//		// the variable admin does not hage the id attribute
//		// fetch all the attributes from the database inluding the id
//		Etudiant temp = checkStudent(etudiant.getLogin());
//		// fill the admin id attributes that you fetched from the database
//		etudiant.setEtudiant_id(temp.getEtudiant_id());
//	
//		
//		DBInteraction.disconnect();
//		
//		return true;
//	}

	public static boolean updateFiliere(Filiere fil) {		
		DBInteraction.connect();
		
		
		// if filiere does not exist return false
		if (FiliereDAO.checkFiliere(fil.getFiliere_id()) == null) {
			return false;
		}

		String update = "update Filieres "
				 + "set "
				 + "	filiere = '"+fil.getFiliere()+"', "
				 + "    description = '"+fil.getDescription()+"' "
				 
				 + "where "
				 + "	etudiant_id = "+fil.getFiliere_id()
				 + ";";

		// if an sql error occured then return false
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();
		
		return true;
	}
	
	public static boolean deleteFiliere(int filiere_id) {
		DBInteraction.connect();

		String update = "DELETE FROM Filieres "
				      + "where "
				      + "	filiere_id = "+filiere_id
				      + ";";
		// if an sql error occured then return null
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();
		
		return true;
	}

}
