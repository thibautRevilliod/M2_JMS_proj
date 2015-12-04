package gui.listeners;


import gui.vues.VueFildActu;
import gui.vues.VueParametres;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jms.SenderTwitter;
import metier.ProfilType;




public class LParametres implements ActionListener
{

	private VueFildActu vF;

	public LParametres (VueFildActu _vF)
	{
		this.vF=_vF;
	}

	public void actionPerformed(ActionEvent e)
	{
		String message;
		
		SenderTwitter.populateProfilConnecte(gui.main.main.profilConnecte.getPSEUDO());
		message = SenderTwitter.getMessageRetour();

		gui.main.main.profilConnecte = SenderTwitter.getProfilConnecte();

		
		VueParametres vP = new VueParametres(this.vF);
		vP.setVisible(true);
		vP.setLocation(800, 300);
	}
}



