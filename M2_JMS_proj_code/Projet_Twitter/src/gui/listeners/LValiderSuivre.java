package gui.listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gui.vues.VueAbonnement;
import gui.vues.VueSuivre;
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
		String message;
		if(null != vi.getRecherche().getText() && ! vi.getRecherche().getText().isEmpty())
		{
			System.out.println("LE CHAMPS DE RECHERCHE N'EST NI NULL, NI VIDE");
			SenderTwitter.creerAbonnement(vi.getRecherche().getText(), gui.main.main.profilConnecte.getPSEUDO());
			message = SenderTwitter.getMessageRetour();
			JOptionPane.showMessageDialog(vi, message, "Nouvel abonnement", JOptionPane.INFORMATION_MESSAGE);
			if(message.contains("OK")) {
				vi.dispose();
				VueAbonnement vueAbo = new VueAbonnement();
				vueAbo.setVisible(true);
				
				JOptionPane.showMessageDialog(vi, message, "Information Abonnement", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("OK je me suis abonnée");
			}
		}
		else
		{
			System.out.println("LE CHAMPS DE RECHERCHE EST  NULL OU VIDE");
			if(null!= vi.getSelectedItemListRecherche()&& !vi.getSelectedItemListRecherche().isEmpty())
			{
				System.out.println("LE SELECTED ITEM N'EST NI NULL, NI VIDE");
				SenderTwitter.creerAbonnement(vi.getSelectedItemListRecherche(),gui.main.main.profilConnecte.getPSEUDO());
				message = SenderTwitter.getMessageRetour();
				JOptionPane.showMessageDialog(vi, message, "Nouvel abonnement", JOptionPane.INFORMATION_MESSAGE);
				if(message.contains("OK")) {
					vi.dispose();
					VueAbonnement vueAbo = new VueAbonnement();
					vueAbo.setVisible(true);
					
					System.out.println("OK je me suis abonnée");
				}
			}			
			else {
				message="Vous devez remplir le champ de texte ou sélectionner un profil afin de vous abonner";
				JOptionPane.showMessageDialog(vi, message, "Erreur Inscription", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}



