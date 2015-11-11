package gui.listeners;


import gui.vues.VueConnexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import jms.SenderTwitter;




public class LConnexion implements ActionListener
{

	private VueConnexion vc;

	public LConnexion (VueConnexion _vc)
	{
		this.vc=_vc;
	}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("OK j'ai cliqu� sur connecter");
		SenderTwitter.connexion("Toto", "123");
		
		String message = SenderTwitter.getMessageRetour();
		
		//TODO : Enregistrer le pseudo du profil connect� jusqu'� sa d�connexion
		// il va �tre utilis� pour appeler les m�thodes du SenderTwitter
		
		JOptionPane.showMessageDialog(vc, message, "Information Connexion", JOptionPane.INFORMATION_MESSAGE);
		
	}
}



