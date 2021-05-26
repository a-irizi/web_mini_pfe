package models;

public class Langue {
	private int langue_id;
	private String langue;
	
	public Langue() {
		this.langue_id = -1;
		this.langue = null;
	}
	
	public Langue(int langue_id, String langue) {
		this.langue_id = langue_id;
		this.langue = langue;
	}
	
	public int getLangue_id() {
		return langue_id;
	}
	public void setLangue_id(int langue_id) {
		this.langue_id = langue_id;
	}
	public String getLangue() {
		return langue;
	}
	public void setLangue(String langue) {
		this.langue = langue;
	}
}
