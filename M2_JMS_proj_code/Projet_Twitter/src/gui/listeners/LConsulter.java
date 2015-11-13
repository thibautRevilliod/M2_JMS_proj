package gui.listeners;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.vues.VueConsulter;
import gui.vues.VueSuivre;




public class LConsulter implements ActionListener
{

	private VueSuivre vs;

	public LConsulter (VueSuivre _vs)
	{
		this.vs=_vs;
	}

	public void actionPerformed(ActionEvent e)
	{
		VueConsulter ve = new VueConsulter();
		ve.setVisible(true);
		
			
	}
}



