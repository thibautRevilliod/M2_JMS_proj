package metier;

import java.io.Serializable;

public class MessageInscription implements Serializable {
	
	private String pseudo;
	private String motDePasse;
	private String nom;
	private String prenom;
	private String ville;
	
	public MessageInscription(String pseudo, String motDePasse, String nom,
			String prenom, String ville) {
		super();
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
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

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String toString() {
		return "MessageInscription [pseudo=" + pseudo + ", motDePasse="
				+ motDePasse + ", nom=" + nom + ", prenom=" + prenom
				+ ", ville=" + ville + "]";
	}
	
	

}
