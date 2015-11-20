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
		
		SenderTwitter.creerAbonnement("PseudoTutu", gui.main.main.profilConnecte.getPSEUDO());
		
		String message = SenderTwitter.getMessageRetour();
		
		
		JOptionPane.showMessageDialog(vi, message, "Information Abonnement", JOptionPane.INFORMATION_MESSAGE);
	}
}



