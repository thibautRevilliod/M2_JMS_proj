package metier;

import java.io.Serializable;

public class MessageConnexion implements Serializable {
	
	private String pseudo;
	private String motDePasse;
	
	public MessageConnexion(String pseudo, String motDePasse) {
		super();
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
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

	public String toString() {
		return "MessageConnexion [pseudo=" + pseudo + ", motDePasse="
				+ motDePasse + "]";
	}
	
	

}
