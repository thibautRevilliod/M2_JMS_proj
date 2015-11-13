package gui.listeners;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.vues.VueSuivre;




public class LSeDesabonner implements ActionListener
{

	private VueSuivre vS;

	public LSeDesabonner (VueSuivre _vS)
	{
		this.vS=_vS;
	}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("je me desabonne");
			
	}
}



