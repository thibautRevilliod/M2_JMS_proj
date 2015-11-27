package gui.listeners;


import gui.vues.VueConnexion;
import gui.vues.VueInscription;
import gui.vues.VueMenuDepart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LConnexionMenu implements ActionListener
{

	private VueMenuDepart vm;

	public LConnexionMenu (VueMenuDepart vueMenuDepart)
	{
		this.vm=vueMenuDepart;
	}

	public void actionPerformed(ActionEvent e)
	{
		VueConnexion vc = new VueConnexion();
		vc.setVisible(true);
		vc.setLocation(800, 300);
		this.vm.setVisible(false);
	}
}


