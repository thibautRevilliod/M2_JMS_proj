package gui.listeners;


import gui.vues.VueConnexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class LConnexion implements ActionListener
{

	private VueConnexion vc;

	public LConnexion (VueConnexion _vc)
	{
		this.vc=_vc;
	}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("OK j'ai cliqué sur connecter");
			
	}
}



