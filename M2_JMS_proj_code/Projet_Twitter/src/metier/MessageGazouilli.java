package metier;

import java.io.Serializable;

public class MessageGazouilli implements Serializable {
	private String contenu;
	private String ville;
	private String pseudoEmetteur;
	
	

	public MessageGazouilli(String contenu, String ville,String pseudoEmetteur) {
		this.contenu = contenu;
		this.ville = ville;
		this.pseudoEmetteur = pseudoEmetteur;
	}



	public String getContenu() {
		return contenu;
	}



	public void setContenu(String contenu) {
		this.contenu = contenu;
	}



	public String getVille() {
		return ville;
	}



	public void setVille(String ville) {
		this.ville = ville;
	}



	public String getPseudoEmetteur() {
		return pseudoEmetteur;
	}



	public void setPseudoEmetteur(String pseudoEmetteur) {
		this.pseudoEmetteur = pseudoEmetteur;
	}



	@Override
	public String toString() {
		return "Gazouilli [contenu=" + contenu + ", ville=" + ville
				+ ", pseudoEmetteur=" + pseudoEmetteur + "]";
	}

	
	
	

}
