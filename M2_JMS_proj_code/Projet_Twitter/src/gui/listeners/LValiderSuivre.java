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
		System.out.println("OK je me suis abonn�e");
		
		SenderTwitter.creerAbonnement("PseudoTutu", "Toto");
		
		String message = SenderTwitter.getMessageRetour();
		
		//TODO : Enregistrer le pseudo du profil connect� jusqu'� sa d�connexion
		// il va �tre utilis� pour appeler les m�thodes du SenderTwitter
		
		JOptionPane.showMessageDialog(vi, message, "Information Abonnement", JOptionPane.INFORMATION_MESSAGE);
	}
}



