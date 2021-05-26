package models;

public class Etudiant {

	private int etudiant_id;
	private String nom;
	private String prenom;
	private String login;
	private String password;
	private String filiere;
	
	public Etudiant() {
		this.etudiant_id = -1;
		this.nom = null;
		this.prenom = null;
		this.login = null;
		this.password = null;
		this.filiere = null;
	}
	
	public Etudiant(int etudiant_id, String nom, String prenom, String login, String password, String filiere) {
		this.etudiant_id = etudiant_id;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
		this.filiere = filiere.toLowerCase();
	}

	public int getEtudiant_id() {
		return etudiant_id;
	}
	public void setEtudiant_id(int etudiant_id) {
		this.etudiant_id = etudiant_id;
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere.toLowerCase();
	}
	
	@Override
	public String toString() {
		return "Etudiant [etudiant_id=" + etudiant_id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login
				+ ", password=" + password + ", filiere=" + filiere + "]";
	}
}
