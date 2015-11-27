package metier;

import java.io.Serializable;

public class ProfilAConsulter implements Serializable{
	private String PSEUDO;
	private String NOM;
	private String PRENOM;
	private String VILLE;
	private int nbGazouillis;
	private int nbSuiveurs;
	private int nbSuivis;
	private String messageRetour;
	
	
	public ProfilAConsulter() {
		PSEUDO = "";
		NOM = "";
		PRENOM = "";
		VILLE = "";
		nbGazouillis=0;
		nbSuiveurs=0;
		nbSuivis=0;
	}

	public ProfilAConsulter(String pSEUDO, String nOM, String pRENOM, String vILLE, int nbG, int nbA, int nbS ) {
		super();
		PSEUDO = pSEUDO;
		NOM = nOM;
		PRENOM = pRENOM;
		VILLE = vILLE;
		nbGazouillis=nbG;
		nbSuiveurs=nbA;
		nbSuivis=nbS;
	}

	public String getPSEUDO() {
		return PSEUDO;
	}

	public void setPSEUDO(String pSEUDO) {
		PSEUDO = pSEUDO;
	}

	public String getNOM() {
		return NOM;
	}

	public void setNOM(String nOM) {
		NOM = nOM;
	}

	public String getPRENOM() {
		return PRENOM;
	}

	public void setPRENOM(String pRENOM) {
		PRENOM = pRENOM;
	}

	public String getVILLE() {
		return VILLE;
	}

	public void setVILLE(String vILLE) {
		VILLE = vILLE;
	}	
	
	public int getNbGazouillis() {
		return nbGazouillis;
	}

	public void setNbGazouillis(int nbGazouillis) {
		this.nbGazouillis = nbGazouillis;
	}

	public int getNbSuiveurs() {
		return nbSuiveurs;
	}

	public void setNbSuiveurs(int nbSuiveurs) {
		this.nbSuiveurs = nbSuiveurs;
	}

	public int getNbSuivis() {
		return nbSuivis;
	}

	public void setNbSuivis(int nbSuivis) {
		this.nbSuivis = nbSuivis;
	}

	public String getMessageRetour() {
		return messageRetour;
	}

	public void setMessageRetour(String messageRetour) {
		this.messageRetour = messageRetour;
	}

	@Override
	public String toString() {
		return "ProfilType [PSEUDO=" + PSEUDO + ", NOM=" + NOM + ", PRENOM="
				+ PRENOM + ", VILLE=" + VILLE
				+ ", nbGazouillis=" +nbGazouillis+", nbSuiveurs=" 
				+ nbSuiveurs + ", nbSuivis=" + nbSuivis +", messageRetour=" + messageRetour + "]";
	}
}

	
	
	
