package models;

public class Auteur {
	private int auteur_id;
	private String nom;
	private String prenom;
	private String nationalite;
	
	public Auteur() {
		this.auteur_id = -1;
		this.nom = null;
		this.prenom = null;
		this.nationalite = null;
	}
	
	public Auteur(int auteur_id, String nom, String prenom, String nationalite) {
		this.auteur_id = auteur_id;
		this.nom = nom;
		this.prenom = prenom;
		this.nationalite = nationalite;
	}
	
	public int getAuteur_id() {
		return auteur_id;
	}
	public void setAuteur_id(int auteur_id) {
		this.auteur_id = auteur_id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

}
