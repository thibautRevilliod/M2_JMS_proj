package metier;

import java.io.Serializable;
import java.sql.Timestamp;

public class MessageGazouilli implements Serializable {
	private String contenu;
	private String ville;
	private String pseudoEmetteur;
	private Timestamp dateHeure;
	private boolean estGeolocalise;
	private String messageRetour;
	
	public MessageGazouilli(String contenu, String ville,
			String pseudoEmetteur, Timestamp dateHeure, boolean estGeolocalise,
			String messageRetour) {
		super();
		this.contenu = contenu;
		this.ville = ville;
		this.pseudoEmetteur = pseudoEmetteur;
		this.dateHeure = dateHeure;
		this.estGeolocalise = estGeolocalise;
		this.messageRetour = messageRetour;
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

	public Timestamp getDateHeure() {
		return dateHeure;
	}

	public void setDateHeure(Timestamp dateHeure) {
		this.dateHeure = dateHeure;
	}

	public boolean isEstGeolocalise() {
		return estGeolocalise;
	}

	public void setEstGeolocalise(boolean estGeolocalise) {
		this.estGeolocalise = estGeolocalise;
	}

	public String getMessageRetour() {
		return messageRetour;
	}

	public void setMessageRetour(String messageRetour) {
		this.messageRetour = messageRetour;
	}

	@Override
	public String toString() {
		return "MessageGazouilli [contenu=" + contenu + ", ville=" + ville
				+ ", pseudoEmetteur=" + pseudoEmetteur + ", dateHeure="
				+ dateHeure + ", estGeolocalise=" + estGeolocalise
				+ ", messageRetour=" + messageRetour + "]";
	}
	
	

}
