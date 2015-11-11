package metier;

import java.io.Serializable;

public class MessageAbonnement implements Serializable {
	private String pseudoSuivi;
	private String pseudoAbonne;

	public MessageAbonnement(String pPseudoSuivi, String pPseudoAbonne) {
		super();
		this.pseudoSuivi = pPseudoSuivi;
		this.pseudoAbonne = pPseudoAbonne;
	}

	public String getPseudoSuivi() {
		return pseudoSuivi;
	}

	public void setPseudoSuivi(String pseudoSuivi) {
		this.pseudoSuivi = pseudoSuivi;
	}

	public String getPseudoAbonne() {
		return pseudoAbonne;
	}

	public void setPseudoAbonne(String pseudoAbonne) {
		this.pseudoAbonne = pseudoAbonne;
	}

	@Override
	public String toString() {
		return "MessageAbonnement [pseudoSuivi=" + pseudoSuivi
				+ ", pseudoAbonne=" + pseudoAbonne + "]";
	}

	
	
	
}
