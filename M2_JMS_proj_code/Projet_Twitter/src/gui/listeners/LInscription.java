package gui.listeners;


import gui.vues.VueInscription;
import jms.SenderTwitter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class LInscription implements ActionListener
{

	private VueInscription vi;

	public LInscription (VueInscription _vi)
	{
		this.vi=_vi;
	}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("OK j'ai cliqué sur m'inscrire");
		
		SenderTwitter.inscription("Toto", "123", "Nom", "Prenom", "Ville");
		
		String message = "!celui qu'on reçoit";
		/**
		 * TODO : Ajouter : SenderTwitter.getMessage()...
		 * Puis ouvrir la pop-up
		 * */
		JOptionPane.showMessageDialog(vi, message, "Information Inscription", JOptionPane.INFORMATION_MESSAGE);
	}
}



