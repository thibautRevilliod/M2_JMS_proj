package metier;

import java.io.Serializable;

public class Gazouilli implements Serializable {
	
	private int idGazouilli;
	private String contenu;
	private String date;
	private String heure;
	private String ville;
	private int idEmetteur;
	
	

	public Gazouilli(int idGazouilli, String contenu, String date, String heure, String ville,
			int idEmetteur) {
		this.idGazouilli = idGazouilli;
		this.contenu = contenu;
		this.date = date;
		this.heure = heure;
		this.ville = ville;
		this.idEmetteur = idEmetteur;
	}
	
	public Gazouilli(String contenu, String date, String heure, String ville,
			int idEmetteur) {
		this.idGazouilli = idGazouilli;
		this.contenu = contenu;
		this.date = date;
		this.heure = heure;
		this.ville = ville;
		this.idEmetteur = idEmetteur;
	}



	public int getIdGazouilli() {
		return idGazouilli;
	}



	public void setIdGazouilli(int idGazouilli) {
		this.idGazouilli = idGazouilli;
	}



	public String getContenu() {
		return contenu;
	}



	public void setContenu(String contenu) {
		this.contenu = contenu;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getHeure() {
		return heure;
	}



	public void setHeure(String heure) {
		this.heure = heure;
	}



	public String getVille() {
		return ville;
	}



	public void setVille(String ville) {
		this.ville = ville;
	}



	public int getIdEmetteur() {
		return idEmetteur;
	}



	public void setIdEmetteur(int idEmetteur) {
		this.idEmetteur = idEmetteur;
	}
	
	

}
