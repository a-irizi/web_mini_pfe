package models;

public class Filiere {
	private int filiere_id;
	private String filiere;
	private String description;
	
	public Filiere() {
		this.filiere_id = -1;
		this.filiere = null;
		this.description = null;
	}
	
	public Filiere(int filiere_id, String filiere, String description) {
		this.filiere_id = filiere_id;
		this.filiere = filiere;
		this.description = description;
	}
	
	public int getFiliere_id() {
		return filiere_id;
	}
	public void setFiliere_id(int filiere_id) {
		this.filiere_id = filiere_id;
	}
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
