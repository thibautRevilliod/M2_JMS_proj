package metier;

import java.io.Serializable;
import java.util.ArrayList;

public class MessageListeMessageDunProfil implements Serializable{
	private String pseudo;
	private ArrayList<MessageGazouilli> listeGazouilli;
	private String messageRetour;
	
	
	
	public MessageListeMessageDunProfil(String pseudo,
			ArrayList<MessageGazouilli> listeGazouilli, String messageRetour) {
		super();
		this.pseudo = pseudo;
		this.listeGazouilli = listeGazouilli;
		this.messageRetour = messageRetour;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public ArrayList<MessageGazouilli> getListeGazouilli() {
		return listeGazouilli;
	}
	public void setListeGazouilli(ArrayList<MessageGazouilli> listeGazouilli) {
		this.listeGazouilli = listeGazouilli;
	}
	public String getMessageRetour() {
		return messageRetour;
	}
	public void setMessageRetour(String messageRetour) {
		this.messageRetour = messageRetour;
	}
	@Override
	public String toString() {
		return "MessageListeMessageDunProfil [pseudo=" + pseudo
				+ ", listeGazouilli=" + listeGazouilli + ", messageRetour="
				+ messageRetour + "]";
	}
	
	
}
