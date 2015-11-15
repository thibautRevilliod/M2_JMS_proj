package metier;

import java.io.Serializable;
import java.util.ArrayList;

public class MessageListeProfil implements Serializable{
	private ArrayList<ProfilType> listeProfil;
	private String messageRetour;
	
	public MessageListeProfil(ArrayList<ProfilType> listeProfil,
			String messageRetour) {
		super();
		this.listeProfil = listeProfil;
		this.messageRetour = messageRetour;
	}

	public ArrayList<ProfilType> getListeProfil() {
		return listeProfil;
	}

	public void setListeProfil(ArrayList<ProfilType> listeProfil) {
		this.listeProfil = listeProfil;
	}

	public String getMessageRetour() {
		return messageRetour;
	}

	public void setMessageRetour(String messageRetour) {
		this.messageRetour = messageRetour;
	}

	@Override
	public String toString() {
		return "MessageListeProfil [listeProfil=" + listeProfil
				+ ", messageRetour=" + messageRetour + "]";
	}
	
	
}
