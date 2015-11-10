package gui.listeners;


import gui.vues.VueAbonnement;
import gui.vues.VueFildActu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class LAbonnements implements ActionListener
{

	private VueFildActu vue;

	public LAbonnements (VueFildActu vueFildActu)
	{
		this.vue=vueFildActu;
	}

	public void actionPerformed(ActionEvent e)
	{
		VueAbonnement va = new VueAbonnement();
		va.setVisible(true);
		va.setLocation(800, 300);
			
	}
}



