package metier;

import java.io.Serializable;

public class ProfilType implements Serializable{
	private String PSEUDO;
	private String NOM;
	private String PRENOM;
	private String VILLE;
	
	public ProfilType(String pSEUDO, String nOM, String pRENOM, String vILLE) {
		super();
		PSEUDO = pSEUDO;
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

	public void setPRENOM(String pRENOM) {
		PRENOM = pRENOM;
	}

	public String getVILLE() {
		return VILLE;
	}

	public void setVILLE(String vILLE) {
		VILLE = vILLE;
	}

	@Override
	public String toString() {
		return "ProfilType [PSEUDO=" + PSEUDO + ", NOM=" + NOM + ", PRENOM="
				+ PRENOM + ", VILLE=" + VILLE + "]";
	}
	
	
}
