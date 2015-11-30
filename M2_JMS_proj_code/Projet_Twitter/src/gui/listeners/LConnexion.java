package gui.listeners;


import gui.vues.VueConnexion;
import gui.vues.VueFildActu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import metier.ProfilType;
import jms.SenderTwitter;
import metier.ProfilType;




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
					//JOptionPane.showMessageDialog(vc, message, "Information Connexion", JOptionPane.INFORMATION_MESSAGE);
					if (message.contains("OK")){
						//Enregistrement du pseudo du profil connecté jusqu'à sa déconnexion
						// il va être utilisé pour appeler les méthodes du SenderTwitter
				
						gui.main.main.profilConnecte = new ProfilType();
						gui.main.main.profilConnecte.setPSEUDO(vc.getPseudo().getText());
						
						//liste des gazouillis de tout les abonnés d'un profil
						SenderTwitter.listeGazouilliDesAbonnements(gui.main.main.profilConnecte.getPSEUDO());
						message = SenderTwitter.getMessageRetour();
						//TODO A gérer : SenderTwitter.getListeGazouilliDesAbonnements();
						gui.main.main.gazouillis = SenderTwitter.getListeGazouilliDesAbonnements();
						gui.main.main.gazouillisSession = new ArrayList<>();
						//JOptionPane.showMessageDialog(vc, message, "Information Liste Gazouilli des Abonnements", JOptionPane.INFORMATION_MESSAGE);

						
						//fermeture fenêtre de connexion
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
		

		//TODO: Déplacer le code ci-dessous dans le fil de l'actualité
		//liste des gazouillis de tout les abonnés d'un profil
			SenderTwitter.listeGazouilliDesAbonnements(gui.main.main.profilConnecte.getPSEUDO());
			message = SenderTwitter.getMessageRetour();
			//TODO A gérer : SenderTwitter.getListeGazouilliDesAbonnements();
			//JOptionPane.showMessageDialog(vc, message, "Information Liste Gazouilli des Abonnements", JOptionPane.INFORMATION_MESSAGE);

	}
}



