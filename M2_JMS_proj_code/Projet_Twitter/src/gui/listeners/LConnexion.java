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
		String message;
		System.out.println("OK j'ai cliqué sur connecter");
		System.out.println(vc.getPseudo().getText()+" "+vc.getPasswordField().getText());
		if (null != vc.getPseudo()&& null != vc.getPasswordField()){
			if (! vc.getPseudo().getText().equals("") && ! vc.getPasswordField().getText().equals("") ){
					SenderTwitter.connexion(vc.getPseudo().getText(), vc.getPasswordField().getText());
					message = SenderTwitter.getMessageRetour();
					JOptionPane.showMessageDialog(vc, message, "Information Connexion", JOptionPane.INFORMATION_MESSAGE);
					//TODO : Enregistrer le pseudo du profil connecté jusqu'à sa déconnexion
					// il va être utilisé pour appeler les méthodes du SenderTwitter
			}
			else {
				message = "Vous devez remplir les 2 champs : pseudo et mot de passe";
				JOptionPane.showMessageDialog(vc, message, "Erreur Connexion", JOptionPane.WARNING_MESSAGE);
			}
		}
		else {
			message = "Vous devez remplir les 2 champs : pseudo et mot de passe";
			JOptionPane.showMessageDialog(vc, message, "Erreur Connexion", JOptionPane.WARNING_MESSAGE);
		}		
	}
}



