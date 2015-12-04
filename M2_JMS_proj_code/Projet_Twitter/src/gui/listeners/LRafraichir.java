package gui.listeners;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JTextArea;

import metier.MessageGazouilli;
import jms.SenderTwitter;
import gui.vues.VueFildActu;
import gui.vues.VueGazouiller;




public class LRafraichir implements ActionListener
{

	private VueFildActu vF;

	public LRafraichir (VueFildActu _vF)
	{
		this.vF=_vF;
	}

	public void actionPerformed(ActionEvent e)
	{
		
		for (int i=0; i<gui.main.main.gazouillisSession.size();i++){
			vF.getArea().append(gui.main.main.gazouillisSession.get(i).toString()+"\n--\n");
			gui.main.main.gazouillisSession.remove(i);
			System.out.println("NouveauMessage");
		}
	}
}



