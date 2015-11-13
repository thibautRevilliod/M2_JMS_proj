package metier;

import java.io.Serializable;

public class MessageAbonnement implements Serializable {
	private String pseudoSuivi;
	private String pseudoAbonne;

	public MessageAbonnement(String pPseudoIdProfilSuiviPar1, String pPseudoIdProfil1) {
		super();
		this.pseudoSuivi = pPseudoIdProfilSuiviPar1;
		this.pseudoAbonne = pPseudoIdProfil1;
	}

	public String getPseudoIdProfilSuiviPar1() {
		return pseudoSuivi;
	}

	public void setPseudoSuivi(String pseudoSuivi) {
		this.pseudoSuivi = pseudoSuivi;
	}

	public String getPseudoIdProfil1() {
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
