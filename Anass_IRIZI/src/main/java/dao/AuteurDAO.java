package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Auteur;
import tools.DBInteraction;

public class AuteurDAO {
	public static List<Auteur> getAllAuteurs() {
		List<Auteur> ll = new ArrayList<Auteur>();

		DBInteraction.connect();
		String query = "SELECT * FROM Auteurs";
		
		
		ResultSet rs = DBInteraction.query(query);
		// if the result set is empty, or an sql error occurred, then return null
		if (DBInteraction.getNumberOfRows(rs) == 0 || DBInteraction.getNumberOfRows(rs) == -1) {
			return null;
		}
		
		Auteur aut = new Auteur();
		try {
			while (rs.next()) {
					aut.setAuteur_id(rs.getInt("auteur_id"));
					aut.setNom(rs.getString("nom"));
					aut.setPrenom(rs.getString("prenom"));
					aut.setNationalite(rs.getString("nationalite"));

				ll.add(aut);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		DBInteraction.disconnect();		
		
		return ll;
	}

	public static Auteur checkAuteur(int auteur_id) {
		Auteur aut = new Auteur();
		
		DBInteraction.connect();
		
		String query = "select * from "
				   + "Auteurs "
				   + "where auteur_id = "+auteur_id
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
			aut.setAuteur_id(rs.getInt("auteur_id"));
			aut.setNom(rs.getString("nom"));
			aut.setPrenom(rs.getString("prenom"));
			aut.setNationalite(rs.getString("nationalite"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		DBInteraction.disconnect();		
		
		return aut;
	}

	public static Auteur checkAuteur(String nom, String prenom){
		Auteur aut = new Auteur();
	
		DBInteraction.connect();
		
		String query = "select * from "
				   	+ "Auteurs "
				   	+ "where nom = '"+nom+"' "
			   		+ "and prenom = '"+prenom+"'"		
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
			aut.setAuteur_id(rs.getInt("auteur_id"));
			aut.setNom(rs.getString("nom"));
			aut.setPrenom(rs.getString("prenom"));
			aut.setNationalite(rs.getString("nationalite"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		DBInteraction.disconnect();		
		
		return aut;
	}

	public static boolean addAuteur(Auteur auteur) {	
		
		// if admin already axists in the database
		// then return false
		if (checkAuteur(auteur.getNom(), auteur.getPrenom()) != null) {
			return false;
		}
		
		DBInteraction.connect();

		String update = "insert into Auteurs (nom, prenom, nationalite) "
						+ "values ('"+auteur.getNom()+"',"
						+ "			'"+auteur.getPrenom()+"',"
						+ "			'"+auteur.getNationalite()+"')"
						
						+ ";";
		
		// the variable categorie does not hage the id attribute
		// fetch all the attributes from the database inluding the id
		Auteur temp = checkAuteur(auteur.getNom(), auteur.getPrenom());
		// fill the categorie id attributes that you fetched from the database
		auteur.setAuteur_id(temp.getAuteur_id());
	
		// if an sql error occured then return null
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();
		
		return true;
	}

	
	public static boolean deleteAuteur(int auteur_id) {
		DBInteraction.connect();

		String update = "DELETE FROM Auteurs "
				      + "where "
				      + "	auteur_id = "+auteur_id
				      + ";";
		// if an sql error occured then return null
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();
		
		return true;
	}
}
