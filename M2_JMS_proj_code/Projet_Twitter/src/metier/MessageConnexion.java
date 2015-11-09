package metier;

import java.io.Serializable;

public class MessageConnexion implements Serializable {
	
	private String pseudo;
	private String motDePasse;
	
	public MessageConnexion(String idProfil, String motDePasse) {
		super();
		this.pseudo = idProfil;
		this.motDePasse = motDePasse;
	}

	public String getIdProfil() {
		return pseudo;
	}

	public void setIdProfil(String idProfil) {
		this.pseudo = idProfil;
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
