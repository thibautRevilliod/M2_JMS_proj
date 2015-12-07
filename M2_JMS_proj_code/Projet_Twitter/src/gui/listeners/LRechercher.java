package gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gui.vues.VueAbonnement;
import gui.vues.VueConsulter;
import gui.vues.VueFildActu;
import jms.SenderTwitter;

public class LRechercher implements ActionListener {

	private VueFildActu vF;
	
	public LRechercher(VueFildActu vF) {
		this.vF=vF;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String message;
		if(null!= vF.getSelectedItemListRecherche()&& !vF.getSelectedItemListRecherche().isEmpty())
		{
			SenderTwitter.populateProfilAConsulter(vF.getSelectedItemListRecherche());
			message = SenderTwitter.getMessageRetour();
			if(message.contains("OK")) {
				VueConsulter consult = new VueConsulter(this.vF, SenderTwitter.getProfilAConsulter());
				
				consult.setVisible(true);
				
				System.out.println("OK consultation");
			}
			else {
				JOptionPane.showMessageDialog(vF, message, "Erreur Consultation", JOptionPane.WARNING_MESSAGE);
			}
		}			
		else {
			message="Vous devez remplir le champ de texte ou s�lectionner un pseudo afin d'afficher le profil d'un gazouilleur";
			JOptionPane.showMessageDialog(vF, message, "Erreur Inscription", JOptionPane.WARNING_MESSAGE);
		}
	}
}
