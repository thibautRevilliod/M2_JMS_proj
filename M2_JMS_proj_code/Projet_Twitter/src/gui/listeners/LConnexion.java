package gui.listeners;


import gui.vues.VueConnexion;
import gui.vues.VueFildActu;

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
		System.out.println("OK j'ai cliqu� sur connecter");
		System.out.println(vc.getPseudo().getText()+" "+vc.getPasswordField().getText());
		if (null != vc.getPseudo()&& null != vc.getPasswordField()){
			if (! vc.getPseudo().getText().equals("") && ! vc.getPasswordField().getText().equals("") ){
					SenderTwitter.connexion(vc.getPseudo().getText(), vc.getPasswordField().getText());
					message = SenderTwitter.getMessageRetour();
					JOptionPane.showMessageDialog(vc, message, "Information Connexion", JOptionPane.INFORMATION_MESSAGE);
					if (message.contains("OK")){
						//Enregistrement du pseudo du profil connect� jusqu'� sa d�connexion
						// il va �tre utilis� pour appeler les m�thodes du SenderTwitter
						gui.main.main.pseudoConnecte = vc.getPseudo().getText();
						//fermeture fen�tre de connexion
						this.vc.setVisible(false);
						//ouverture de la vue Fil d'actu
						VueFildActu vf = new VueFildActu();
						vf.setVisible(true);
					}
					
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
		
		//TODO: D�placer le code ci-dessous dans le fil de l'actualit�
		//liste des gazouillis de tout les abonn�s d'un profil
			SenderTwitter.listeGazouilliDesAbonnements(gui.main.main.pseudoConnecte);
			message = SenderTwitter.getMessageRetour();
			//TODO A g�rer : SenderTwitter.getListeGazouilliDesAbonnements();
			JOptionPane.showMessageDialog(vc, message, "Information Liste Gazouilli des Abonnements", JOptionPane.INFORMATION_MESSAGE);
		
		//TODO: D�placer le code ci-dessous o� on a besoin de la liste des profils
		//liste des profils de la BD
			SenderTwitter.listeDesProfils();
			message = SenderTwitter.getMessageRetour();
			JOptionPane.showMessageDialog(vc, message, "Information Liste des profils", JOptionPane.INFORMATION_MESSAGE);
	}
}



