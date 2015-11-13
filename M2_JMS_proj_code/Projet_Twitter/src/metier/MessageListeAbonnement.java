package metier;

import java.io.Serializable;
import java.util.Arrays;

public class MessageListeAbonnement implements Serializable {
	private String pseudo;
	private String[] listeAbonnement;
	private String messageRetour;

	public MessageListeAbonnement(String pPseudo, String[] pListeAbonnement, String pmessageRetour) {
		super();
		this.pseudo = pPseudo;
		this.listeAbonnement = pListeAbonnement;
		this.messageRetour = pmessageRetour;
	}

	public String getPseudoIdProfil1() {
		return pseudo;
	}

	public void setPseudoAbonne(String pPseudoIdProfil1) {
		this.pseudo = pPseudoIdProfil1;
	}

	public String[] getListeAbonnement() {
		return listeAbonnement;
	}

	public void setListeAbonnement(String[] listeAbonnement) {
		this.listeAbonnement = listeAbonnement;
	}

	public String getMessageRetour() {
		return messageRetour;
	}

	public void setMessageRetour(String messageRetour) {
		this.messageRetour = messageRetour;
	}

	@Override
	public String toString() {
		return "MessageListeAbonnement [pseudo=" + pseudo
				+ ", listeAbonnement=" + Arrays.toString(listeAbonnement)
				+ ", messageRetour=" + messageRetour + "]";
	}

	
	
}
