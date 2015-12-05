package gui.listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gui.vues.VueAbonnement;
import gui.vues.VueSuivre;
import jms.SenderTwitter;

public class LValiderSuivre implements ActionListener
{

	private VueSuivre vi;
	private JFrame jFrameLiee;

	public LValiderSuivre (VueSuivre vueSuivre, JFrame jf)
	{
		this.vi=vueSuivre;
		this.jFrameLiee=jf;
	}

	public void actionPerformed(ActionEvent e)
	{
		String message;
		if(null!= vi.getSelectedItemListRecherche()&& !vi.getSelectedItemListRecherche().isEmpty())
		{
			System.out.println("LE SELECTED ITEM N'EST NI NULL, NI VIDE");
			SenderTwitter.creerAbonnement(vi.getSelectedItemListRecherche(),gui.main.main.profilConnecte.getPSEUDO());
			message = SenderTwitter.getMessageRetour();
			JOptionPane.showMessageDialog(vi, message, "Nouvel abonnement", JOptionPane.INFORMATION_MESSAGE);
			if(message.contains("OK")) {
				vi.setVisible(false);
				VueAbonnement vueAbo = new VueAbonnement(this.jFrameLiee);
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



