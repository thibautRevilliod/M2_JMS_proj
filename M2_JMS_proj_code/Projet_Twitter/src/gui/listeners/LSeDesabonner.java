package gui.listeners;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import jms.SenderTwitter;
import gui.vues.VueAbonnement;
import gui.vues.VueSuivre;




public class LSeDesabonner implements ActionListener
{

	private VueAbonnement vS;

	public LSeDesabonner (VueAbonnement _vS)
	{
		this.vS=_vS;
	}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("je me desabonne");
		String message;
		if(null != vS.getPseudoSuiviSelected() && ! vS.getPseudoSuiviSelected().isEmpty())
		{
			SenderTwitter.suppAbonnement(vS.getPseudoSuiviSelected(), gui.main.main.profilConnecte.getPSEUDO());
			message = SenderTwitter.getMessageRetour();
			JOptionPane.showMessageDialog(vS, message, "Nouvel abonnement", JOptionPane.INFORMATION_MESSAGE);
			if(message.contains("OK")) {
				this.vS.setVisible(false);
				
				//Le code ci-dessous permet de retourner la liste des personnes abonnées à l'utilisateur			
				SenderTwitter.listeAbonne(gui.main.main.profilConnecte.getPSEUDO());
				String message2 = SenderTwitter.getMessageRetour();	
				gui.main.main.profilsAbonnes = SenderTwitter.getListeAbonnement();
	
						
			//Le code ci-dessous permet de retourner la liste des personnes suivies par l'utilisateur
				SenderTwitter.listeSuivi(gui.main.main.profilConnecte.getPSEUDO());
				message2 = SenderTwitter.getMessageRetour();		
				gui.main.main.profilsSuivis = SenderTwitter.getListeSuivi();
				System.out.println("OK je me suis désabonnée");
				VueAbonnement abo = new VueAbonnement();
				abo.setVisible(true);
				
				
			}
		}
		else
		{
			message="Vous devez sélectionner un profil afin de vous désabonnner";
			JOptionPane.showMessageDialog(vS, message, "Erreur Inscription", JOptionPane.WARNING_MESSAGE);
			
		}
	}
}



