package gui.listeners;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.vues.VueFildActu;
import gui.vues.VueGazouiller;




public class LGazouiller implements ActionListener
{

	private VueFildActu vF;

	public LGazouiller (VueFildActu _vF)
	{
		this.vF=_vF;
	}

	public void actionPerformed(ActionEvent e)
	{
		VueGazouiller ve = new VueGazouiller();
		ve.setVisible(true);
		ve.setLocation(800, 300);
			
	}
}



