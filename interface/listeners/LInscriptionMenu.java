package listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vues.VueInscription;
import vues.VueMenuDepart;

public class LInscriptionMenu implements ActionListener
{

	private VueMenuDepart vm;

	public LInscriptionMenu (VueMenuDepart vueMenuDepart)
	{
		this.vm=vueMenuDepart;
	}

	public void actionPerformed(ActionEvent e)
	{
		VueInscription vI = new VueInscription();
		vI.setVisible(true);
	}
}


