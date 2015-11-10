package gui.listeners;


import gui.vues.VueFildActu;
import gui.vues.VueParametres;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class LParametres implements ActionListener
{

	private VueFildActu vF;

	public LParametres (VueFildActu _vF)
	{
		this.vF=_vF;
	}

	public void actionPerformed(ActionEvent e)
	{
		VueParametres vP = new VueParametres();
		vP.setVisible(true);
		vP.setLocation(800, 300);
	}
}



