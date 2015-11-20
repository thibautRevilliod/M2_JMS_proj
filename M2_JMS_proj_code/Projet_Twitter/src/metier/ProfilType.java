package metier;

import java.io.Serializable;

public class ProfilType implements Serializable{
	private String PSEUDO;
	private String NOM;
	private String PRENOM;
	private String MDP;
	private String VILLE;
	private String messageRetour;
	
	
	public ProfilType() {
		PSEUDO = "";
		MDP = "";
		NOM = "";
		PRENOM = "";
		VILLE = "";
	}

	public ProfilType(String pSEUDO, String mDP, String nOM, String pRENOM, String vILLE) {
		super();
		PSEUDO = pSEUDO;
		MDP = mDP;
		NOM = nOM;
		PRENOM = pRENOM;
		VILLE = vILLE;
		
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

	public String getMDP() {
		return MDP;
	}

	public void setMDP(String mDP) {
		MDP = mDP;
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

	public String getMessageRetour() {
		return messageRetour;
	}

	public void setMessageRetour(String messageRetour) {
		this.messageRetour = messageRetour;
	}

	@Override
	public String toString() {
		return "ProfilType [PSEUDO=" + PSEUDO + ", NOM=" + NOM + ", PRENOM="
				+ PRENOM + ", MDP=" + MDP + ", VILLE=" + VILLE
				+ ", messageRetour=" + messageRetour + "]";
	}

	
	
	
}
