package gui.listeners;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import jms.SenderTwitter;
import gui.vues.VueSuivre;




public class LSeDesabonner implements ActionListener
{

	private VueSuivre vS;

	public LSeDesabonner (VueSuivre _vS)
	{
		this.vS=_vS;
	}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("je me desabonne");
		
		//TODO : cr�er un bouton supprimer abonnement et mettre le code ci-dessous
		SenderTwitter.suppAbonnement("PseudoTutu", "Toto");
		
		//String message = SenderTwitter.getMessageRetour();
		String message = SenderTwitter.getMessageRetour();
		
		//TODO : Enregistrer le pseudo du profil connect� jusqu'� sa d�connexion
		// il va �tre utilis� pour appeler les m�thodes du SenderTwitter
		
		JOptionPane.showMessageDialog(vS, message, "Information Abonnement", JOptionPane.INFORMATION_MESSAGE);
	}
}



