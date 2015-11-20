package metier;

import java.io.Serializable;

public class MessagePopulateProfil implements Serializable {
	
	private String idProfil;

	public MessagePopulateProfil(String pseudo) {
		super();
		this.idProfil = pseudo;
	}

	public String getPseudo() {
		return idProfil;
	}

	public void setPseudo(String pseudo) {
		this.idProfil = pseudo;
	}

	public String toString() {
		return "MessagePopulateProfil [pseudo=" + idProfil + "]";
	}
	
	

}
