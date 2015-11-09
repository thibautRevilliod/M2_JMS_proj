package metier;

import java.io.Serializable;

public class MessageDeconnexion implements Serializable {
	
	private String pseudo;

	public MessageDeconnexion(String pseudo) {
		super();
		this.pseudo = pseudo;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String toString() {
		return "MessageDeconnexion [pseudo=" + pseudo + "]";
	}
	
	

}
