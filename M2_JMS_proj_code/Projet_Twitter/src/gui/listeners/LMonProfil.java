package gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gui.vues.VueConsulter;
import gui.vues.VueFildActu;
import jms.SenderTwitter;

public class LMonProfil implements ActionListener {

	private VueFildActu vF;
	
	public LMonProfil(VueFildActu vF) {
		this.vF=vF;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String message;
		SenderTwitter.populateProfilAConsulter(gui.main.main.profilConnecte.getPSEUDO());
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
}
