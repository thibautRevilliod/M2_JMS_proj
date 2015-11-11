package metier;

import java.io.Serializable;

public class MessageDeconnexion implements Serializable {
	
	private String idProfil;

	public MessageDeconnexion(String pseudo) {
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
		return "MessageDeconnexion [pseudo=" + idProfil + "]";
	}
	
	

}
