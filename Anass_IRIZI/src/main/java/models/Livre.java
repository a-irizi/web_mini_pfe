package models;

import java.util.List;

public class Livre {
	private int livre_id;
	private String titre;
	private String categorie;
	private String langue;
	private List<Auteur> auteurs;
	private int nmbr_pages;
	private int nmbr_copies;
	private int nmbr_copies_empruntes;

	public Livre() {
		this.livre_id = -1;
		this.titre = null;
		this.langue = null;
		this.auteurs = null;
		this.nmbr_pages = -1;
		this.nmbr_copies = -1;
		this.categorie = null;
	}

	public Livre(int livre_id, String titre, String langue, List<Auteur> auteurs, int nmbr_page, int nmbr_copie,
			String category) {
		this.livre_id = livre_id;
		this.titre = titre;
		this.langue = langue;
		this.auteurs = auteurs;
		this.nmbr_pages = nmbr_page;
		this.nmbr_copies = nmbr_copie;
		this.categorie = category;
	}



	public int getLivre_id() {
		return livre_id;
	}
	public void setLivre_id(int liver_id) {
		this.livre_id = liver_id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getLangue() {
		return langue;
	}
	public void setLangue(String langue) {
		this.langue = langue;
	}
	public List<Auteur> getAuteurs() {
		return auteurs;
	}

	public void setAuteurs(List<Auteur> auteurs) {
		this.auteurs = auteurs;
	}
	public int getNmbr_pages() {
		return nmbr_pages;
	}
	public void setNmbr_pages(int nmbr_page) {
		this.nmbr_pages = nmbr_page;
	}
	public int getNmbr_copies() {
		return nmbr_copies;
	}
	public void setNmbr_copies(int nmbr_copie) {
		this.nmbr_copies = nmbr_copie;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String category) {
		this.categorie = category;
	}

	public int getNmbr_copies_empruntes() {
		return nmbr_copies_empruntes;
	}

	public void setNmbr_copies_empruntes(int nmbr_copies_reserves) {
		this.nmbr_copies_empruntes = nmbr_copies_reserves;
	}

}
