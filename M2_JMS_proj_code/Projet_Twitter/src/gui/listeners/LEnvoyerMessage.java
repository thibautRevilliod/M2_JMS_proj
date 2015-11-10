package gui.listeners;


import gui.vues.VueGazouiller;
import gui.vues.VueInscription;
import gui.vues.VueMenuDepart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LEnvoyerMessage implements ActionListener
{

	private VueGazouiller vg;

	public LEnvoyerMessage (VueGazouiller vue)
	{
		this.vg=vue;
	}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("OK j'ai cliqué sur envoyer le message");
		
	}
}


