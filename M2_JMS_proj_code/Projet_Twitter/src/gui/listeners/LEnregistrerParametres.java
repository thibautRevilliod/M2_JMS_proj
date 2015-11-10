package gui.listeners;

import gui.vues.VueParametres;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class LEnregistrerParametres implements ActionListener
{

	private VueParametres vue;

	public LEnregistrerParametres (VueParametres _vue)
	{
		this.vue=_vue;
	}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("OK j'ai cliqué sur ENREGISTRER");
			
	}
}



