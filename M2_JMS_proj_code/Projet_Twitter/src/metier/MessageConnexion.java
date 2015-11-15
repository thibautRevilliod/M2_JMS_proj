package metier;

import java.io.Serializable;

public class MessageConnexion implements Serializable {
	
	private String pseudo;
	private String motDePasse;
	private String ville;
	private String messageRetour;
	
	public MessageConnexion(String pseudo, String motDePasse, String ville, String messageRetour) {
		super();
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.ville = ville;
		this.messageRetour = messageRetour;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMessageRetour() {
		return messageRetour;
	}

	public void setMessageRetour(String messageRetour) {
		this.messageRetour = messageRetour;
	}

	@Override
	public String toString() {
		return "MessageConnexion [pseudo=" + pseudo + ", motDePasse="
				+ motDePasse + ", ville=" + ville + ", messageRetour="
				+ messageRetour + "]";
	}
	
	
	
	

}
