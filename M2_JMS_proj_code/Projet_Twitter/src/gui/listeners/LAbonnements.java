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
		VueAbonnement va = new VueAbonnement();
		va.setVisible(true);
		va.setLocation(800, 300);
		
		//TODO : Le code ci-dessous permet de retourner la liste des abonnements			
			//listeAbonne
			SenderTwitter.listeAbonne("Toto");
			String message = SenderTwitter.getMessageRetour();		
			JOptionPane.showMessageDialog(va, message, "Information liste Abonne", JOptionPane.INFORMATION_MESSAGE);
					
		//TODO : Le code ci-dessous permet de retourner la liste des suiveurs
			//listeAbonne
			SenderTwitter.listeSuivi("Toto");
			message = SenderTwitter.getMessageRetour();		
			JOptionPane.showMessageDialog(va, message, "Information liste Suivi", JOptionPane.INFORMATION_MESSAGE);
		//TODO : Le code ci-dessous permet de retourner le nombre de gazouilli d'un profil
			SenderTwitter.nbGazouilliDunProfil("Toto");
			message = SenderTwitter.getMessageRetour();		
			JOptionPane.showMessageDialog(va, message, "Information nombre de Gazouilli", JOptionPane.INFORMATION_MESSAGE);
	}
}



