package listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vues.VueConnexion;
import vues.VueInscription;
import vues.VueMenuDepart;

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
	}
}


