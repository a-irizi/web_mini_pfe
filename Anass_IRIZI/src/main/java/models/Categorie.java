package models;

public class Categorie {
	private int categorie_id;
	private String categorie;
	
	public Categorie() {
		this.categorie_id = -1;
		this.categorie = null;
	}
	
	public Categorie(int categorie_id, String categorie) {
		this.categorie_id = categorie_id;
		this.categorie = categorie;
	}

	public int getCategorie_id() {
		return categorie_id;
	}
	public void setCategorie_id(int categorie_id) {
		this.categorie_id = categorie_id;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
}
