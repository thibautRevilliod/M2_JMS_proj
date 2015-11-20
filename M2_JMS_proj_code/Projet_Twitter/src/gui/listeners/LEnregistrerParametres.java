package gui.listeners;

import gui.vues.VueParametres;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import jms.SenderTwitter;




public class LEnregistrerParametres implements ActionListener
{

	private VueParametres vue;

	public LEnregistrerParametres (VueParametres _vue)
	{
		this.vue=_vue;
	}

	public void actionPerformed(ActionEvent e)
	{
		String message;
		System.out.println(vue.getChampsMdp().getText()+" "+vue.getChampsNom().getText()+" "+vue.getChampsPrenom().getText()+" "+vue.getChampsVille().getText());
		if(vue.getChampsMdp() != null && vue.getChampsNom() != null && vue.getChampsPrenom() != null && vue.getChampsVille() != null)
		{
			if(!vue.getChampsMdp().getText().equals("") && !vue.getChampsNom().getText().equals("") && !vue.getChampsPrenom().getText().equals("") && !vue.getChampsVille().getText().equals(""))
			{
				SenderTwitter.miseAJourProfil(gui.main.main.profilConnecte.getPSEUDO(), vue.getChampsMdp().getText(), vue.getChampsNom().getText(), vue.getChampsPrenom().getText(), vue.getChampsVille().getText());
				message = SenderTwitter.getMessageRetour();
				JOptionPane.showMessageDialog(vue, message, "Information mise � jour profil", JOptionPane.INFORMATION_MESSAGE);
				this.vue.setVisible(false); // fermeture fen�tre des param�tres profil
			}
			else
			{
				message = "Vous devez remplir tous les champs";
				JOptionPane.showMessageDialog(vue, message, "Erreur mise � jour profil", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		else
		{
			message = "Vous devez remplir tous les champs";
			JOptionPane.showMessageDialog(vue, message, "Erreur mise � jour profil", JOptionPane.WARNING_MESSAGE);
		}
		
		
		
		System.out.println("OK j'ai cliqu� sur ENREGISTRER");
			
	}
}



