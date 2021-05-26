
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import models.Auteur;
import models.Livre;
import tools.DBInteraction;

public class LivreDAO {

	
	public static List<Auteur> getBookAuthors(int livre_id) {
		List<Auteur> auts = new ArrayList<Auteur>();
		DBInteraction.connect();
		
		// create a select statement that will fetch the id
		// of the author(s) of a specific book
		String q = "SELECT "
				+ "    alc.auteur_id "
				+ "FROM "
				+ "    Auteur_livre_Connections as alc "
				+ "WHERE "
				+ "    alc.livre_id = "+livre_id
				+ ";";

		ResultSet rs = DBInteraction.query(q);
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

		Auteur aut = new Auteur();

		try {
			while(rs.next()) {
				aut = AuteurDAO.checkAuteur(rs.getInt("auteur_id"));
				auts.add(aut);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		DBInteraction.disconnect();
		
		return auts;
	}

	public static boolean setBookAuthors(int livre_id, List<Auteur> auteurs) {
		DBInteraction.connect();

		String update = null;
		for (Auteur auteur : auteurs)
		{
			update = "insert into Auteur_livre_Connections (livre_id, auteur_id) "
					+ "values ("+livre_id+", "+auteur.getAuteur_id()+");";
		}
		
		DBInteraction.disconnect();
		return true;
	}
	
	public static List<Livre> getAllLivres() {
		List<Livre> ll = new ArrayList<Livre>();

		DBInteraction.connect();
		String query = "SELECT * FROM Livres_Infos_Complets";
		
		
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
		
		Livre liv = new Livre();
		;
		try {
			while (rs.next()) {
					liv.setLivre_id(rs.getInt("livre_id"));
					liv.setTitre(rs.getString("titre"));
					liv.setCategorie(CategorieDAO.checkCategorie(rs.getInt("categorie_id")).getCategorie());
					liv.setLangue(LangueDAO.checkLangue(rs.getString("langue_id")).getLangue());
					liv.setAuteurs(getBookAuthors(rs.getInt("livre_id")));
					liv.setNmbr_pages(rs.getInt("nmbr_page"));
					liv.setNmbr_copies(rs.getInt("nmbr_copies"));
					liv.setNmbr_copies_empruntes(rs.getInt("nmbr_copies_empruntes"));
				ll.add(liv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		DBInteraction.disconnect();		
		
		return ll;
	}

	public static Livre checkLivre(int livre_id) {
		Livre liv = new Livre();
		
		DBInteraction.connect();
		
		String query = "select * from "
				   + "Livres_Infos_Complets "
				   + "where livre_id = "+livre_id
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
			liv.setLivre_id(rs.getInt("livre_id"));
			liv.setTitre(rs.getString("titre"));
			liv.setCategorie(CategorieDAO.checkCategorie(rs.getInt("categorie_id")).getCategorie());
			liv.setLangue(LangueDAO.checkLangue(rs.getString("langue_id")).getLangue());
			liv.setAuteurs(getBookAuthors(rs.getInt("livre_id")));
			liv.setNmbr_pages(rs.getInt("nmbr_page"));
			liv.setNmbr_copies(rs.getInt("nmbr_copies"));
			liv.setNmbr_copies_empruntes(rs.getInt("nmbr_copies_empruntes"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		DBInteraction.disconnect();		
		
		return liv;
	}

	public static Livre checkLivre(String titre){
		Livre liv = new Livre();
	
		DBInteraction.connect();
		
		String query = "select * from "
				   + "Livres "
				   + "where livre = '"+titre+"'"		
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
			liv.setLivre_id(rs.getInt("livre_id"));
			liv.setTitre(rs.getString("titre"));
			liv.setCategorie(CategorieDAO.checkCategorie(rs.getInt("categorie_id")).getCategorie());
			liv.setLangue(LangueDAO.checkLangue(rs.getString("langue_id")).getLangue());
			liv.setAuteurs(getBookAuthors(rs.getInt("livre_id")));
			liv.setNmbr_pages(rs.getInt("nmbr_page"));
			liv.setNmbr_copies(rs.getInt("nmbr_copies"));
			liv.setNmbr_copies_empruntes(rs.getInt("nmbr_copies_empruntes"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		DBInteraction.disconnect();		
		
		return liv;
	}

	public static boolean addLivre(Livre livre) {	
		
		// if admin already axists in the database
		// then return false
		if (checkLivre(livre.getTitre()) != null) {
			return false;
		}
		
		DBInteraction.connect();

		String update = "insert into Livres (titre, langue_id, nmbr_page, nmbr_copies, categorie_id) "
						+ "values ('"+livre.getTitre()+"', "
						+ "			"+LangueDAO.checkLangue(livre.getLangue()).getLangue_id()+", "
						+ "			"+livre.getNmbr_pages()+", "
						+ "			"+livre.getNmbr_copies()+", "
						+ "			"+CategorieDAO.checkCategorie(livre.getCategorie()).getCategorie_id()+");";

		// the variable categorie does not hage the id attribute
		// fetch all the attributes from the database inluding the id
		Livre temp = checkLivre(livre.getTitre());
		// fill the categorie id attributes that you fetched from the database
		livre.setLivre_id(temp.getLivre_id());
		
		// establish the relationship between this book and its authors
		setBookAuthors(livre.getLivre_id(), livre.getAuteurs());

		// if an sql error occured then return null
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();
		
		return true;
	}

	public static boolean updateLivre(Livre livre) {
		DBInteraction.connect();

		// if Livre does not exist return false
		if (LivreDAO.checkLivre(livre.getLivre_id()) == null) {
			return false;
		}

		String update = "update Livres "
				 + "set "
				 + "	titre = '"+livre.getTitre()+"', "
				 + "    langue_id = "+LangueDAO.checkLangue(livre.getLangue()).getLangue_id()+", "
				 + "    nmbr_page = "+livre.getNmbr_pages()+", "
				 + "    nmbr_copies = "+livre.getNmbr_copies()+", "
				 + "    categorie_id = "+CategorieDAO.checkCategorie(livre.getCategorie()).getCategorie_id()+", "
				 + "where "
				 + "	livre_id = "+livre.getLivre_id()
				 + ";";

		// if an sql error occured then return false
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();
		
		return true;
	}
	
	public static boolean deleteLivre(int livre_id) {
		DBInteraction.connect();

		String update = "DELETE FROM Livres "
				      + "where "
				      + "	livre_id = "+livre_id
				      + ";";
		// if an sql error occured then return null
		if (DBInteraction.update(update) == 0) {
			return false;
		}

		DBInteraction.disconnect();
		
		return true;
	}

}
