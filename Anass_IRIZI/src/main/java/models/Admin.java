package models;

public class Admin {
	private int admin_id;
	private String nom;
	private String prenom;
	private String login;
	private String password;
	
	public Admin() {
		this.admin_id = -1;
		this.nom = null;
		this.prenom = null;
		this.login = null;
		this.password = null;
	}
	
	public Admin(int admin_id, String nom, String prenom, String login, String password) {
		this.admin_id = admin_id;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
	}

	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
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
}
