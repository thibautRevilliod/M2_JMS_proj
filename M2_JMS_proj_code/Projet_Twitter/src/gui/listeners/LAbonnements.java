package gui.listeners;


import gui.vues.VueAbonnement;
import gui.vues.VueFildActu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
		
		//TODO : Le code ci-dessous permet de retourner la liste des abonnements			
			//listeAbonne
			SenderTwitter.listeAbonne(gui.main.main.profilConnecte.getPSEUDO());
			String message = SenderTwitter.getMessageRetour();	
			gui.main.main.profilsAbonnes = SenderTwitter.getListeAbonnement();

			
					
		//TODO : Le code ci-dessous permet de retourner la liste des suiveurs
			//listeSuiveurs
			SenderTwitter.listeSuivi(gui.main.main.profilConnecte.getPSEUDO());
			message = SenderTwitter.getMessageRetour();		
			gui.main.main.profilsSuivis = SenderTwitter.getListeSuivi();
			
			
			
			VueAbonnement va = new VueAbonnement();
			va.setVisible(true);
			va.setLocation(800, 300);
		//TODO : Le code ci-dessous permet de retourner le nombre de gazouilli d'un profil
		//	SenderTwitter.nbGazouilliDunProfil("Toto");
			//message = SenderTwitter.getMessageRetour();		
			//JOptionPane.showMessageDialog(va, message, "Information nombre de Gazouilli", JOptionPane.INFORMATION_MESSAGE);
	}
}



