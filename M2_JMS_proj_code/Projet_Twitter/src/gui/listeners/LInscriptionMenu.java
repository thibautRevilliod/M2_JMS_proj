package gui.listeners;


import gui.vues.VueInscription;
import gui.vues.VueMenuDepart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LInscriptionMenu implements ActionListener
{

	private VueMenuDepart vm;

	public LInscriptionMenu (VueMenuDepart vueMenuDepart)
	{
		this.vm=vueMenuDepart;
	}

	public void actionPerformed(ActionEvent e)
	{
		VueInscription vI = new VueInscription();
		vI.setVisible(true);
		vI.setLocation(800, 300);
	}
}


