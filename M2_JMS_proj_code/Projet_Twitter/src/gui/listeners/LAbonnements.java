package gui.listeners;


import gui.vues.VueAbonnement;
import gui.vues.VueFildActu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import jms.SenderTwitter;




public class LAbonnements implements ActionListener
{

	private VueFildActu vue;

	public LAbonnements (VueFildActu vueFildActu)
	{
		this.vue=vueFildActu;
	}

	public void actionPerformed(ActionEvent e)
	{
		VueAbonnement va = new VueAbonnement();
		va.setVisible(true);
		va.setLocation(800, 300);
		
		//TODO : Le code ci-dessous permet de retourner la liste des abonnements
			SenderTwitter.listeAbonne("PseudoToto");
			
			String message = SenderTwitter.getMessageRetour();
			String[] listeAbonne = SenderTwitter.getListeAbonnementMessageRetour();
			
			//TODO : Enregistrer le pseudo du profil connecté jusqu'à sa déconnexion
			// il va être utilisé pour appeler les méthodes du SenderTwitter
			if(listeAbonne == null)
			{
				// cas ou il n'y a pas d'abonne
				JOptionPane.showMessageDialog(va, message , "Information Liste Abonne", JOptionPane.INFORMATION_MESSAGE);
			}else
			{
				// ne pas oublier d'ajouter la boucle pour affiher toute la liste
				JOptionPane.showMessageDialog(va, message + " " + listeAbonne.toString(), "Information Liste Abonne", JOptionPane.INFORMATION_MESSAGE);
			}
			
		//TODO : Le code ci-dessous permet de retourner la liste des suiveurs
			SenderTwitter.listeSuivi("PseudoTutu");
			
			message = SenderTwitter.getMessageRetour();
			String[] listeSuivi = SenderTwitter.getListeAbonnementMessageRetour();
			
			//TODO : Enregistrer le pseudo du profil connecté jusqu'à sa déconnexion
			// il va être utilisé pour appeler les méthodes du SenderTwitter
			if(listeSuivi == null)
			{
				// cas ou il n'y a pas de suiveur
				JOptionPane.showMessageDialog(va, message , "Information Liste Suiveur", JOptionPane.INFORMATION_MESSAGE);
			}else
			{
				// ne pas oublier d'ajouter la boucle pour affiher toute la liste
				JOptionPane.showMessageDialog(va, message + " " + listeSuivi.toString(), "Information Liste Suiveur", JOptionPane.INFORMATION_MESSAGE);
			}
			
	}
}



