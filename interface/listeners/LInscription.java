package listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vues.VueInscription;

public class LInscription implements ActionListener
{

	private VueInscription vi;

	public LInscription (VueInscription _vi)
	{
		this.vi=_vi;
	}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("OK j'ai cliqué sur m'inscrire");
			
	}
}



