package gui.listeners;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.vues.VueAbonnement;
import gui.vues.VueSuivre;




public class LSuivre implements ActionListener
{

	private VueAbonnement vA;

	public LSuivre (VueAbonnement _vA)
	{
		this.vA=_vA;
	}

	public void actionPerformed(ActionEvent e)
	{
		VueSuivre vP = new VueSuivre();
		vP.setVisible(true);
		vP.setLocation(800, 300);
	}
}
