package metier;

import java.io.Serializable;

public class MessageNbGazouilliUnProfil implements Serializable{
	private String pseudo;
	private int nbGazouilli;
	private String messageRetour;
	
	public MessageNbGazouilliUnProfil(String pseudo, int nbGazouilli,
			String messageRetour) {
		super();
		this.pseudo = pseudo;
		this.nbGazouilli = nbGazouilli;
		this.messageRetour = messageRetour;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int getNbGazouilli() {
		return nbGazouilli;
	}

	public void setNbGazouilli(int nbGazouilli) {
		this.nbGazouilli = nbGazouilli;
	}

	public String getMessageRetour() {
		return messageRetour;
	}

	public void setMessageRetour(String messageRetour) {
		this.messageRetour = messageRetour;
	}

	@Override
	public String toString() {
		return "MessageNbGazouilliUnProfil [pseudo=" + pseudo
				+ ", nbGazouilli=" + nbGazouilli + ", messageRetour="
				+ messageRetour + "]";
	}
	
	
}
