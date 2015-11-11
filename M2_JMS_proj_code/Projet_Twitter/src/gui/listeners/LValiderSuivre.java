package gui.listeners;


import gui.vues.VueInscription;
import gui.vues.VueSuivre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import jms.SenderTwitter;

public class LValiderSuivre implements ActionListener
{

	private VueSuivre vi;

	public LValiderSuivre (VueSuivre vueSuivre)
	{
		this.vi=vueSuivre;
	}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("OK je me suis abonnée");
		
		SenderTwitter.creerAbonnement("Toto", "PseudoTutu");
		
		String message = SenderTwitter.getMessageRetour();
		
		//TODO : Enregistrer le pseudo du profil connecté jusqu'à sa déconnexion
		// il va être utilisé pour appeler les méthodes du SenderTwitter
		
		JOptionPane.showMessageDialog(vi, message, "Information Abonnement", JOptionPane.INFORMATION_MESSAGE);
		
		//TODO : créer un bouton supprimer abonnement et mettre le code ci-dessous
		SenderTwitter.suppAbonnement("Toto", "PseudoTutu");
		
		//String message = SenderTwitter.getMessageRetour();
		message = SenderTwitter.getMessageRetour();
		
		//TODO : Enregistrer le pseudo du profil connecté jusqu'à sa déconnexion
		// il va être utilisé pour appeler les méthodes du SenderTwitter
		
		JOptionPane.showMessageDialog(vi, message, "Information Abonnement", JOptionPane.INFORMATION_MESSAGE);
	}
}



