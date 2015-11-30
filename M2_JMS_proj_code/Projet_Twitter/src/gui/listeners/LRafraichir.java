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
//		vF.repaint();
//		vF.getArea().updateUI();
//		vF.getArea().revalidate();
//		vF.getArea().validate();
//		vF.getArea().update(vF.getArea().getGraphics());
//		vF.getArea().setText(vF.getArea().getText());
//		System.out.println("[LRafraichir] ap --> gazouillis : " + gui.main.main.gazouillis.toString());
//		vF.getArea().revalidate();
//		vF.revalidate();
		
		
//		System.out.println("gazouillis session : "+gui.main.main.gazouillisSession.toString());
		for (int i=0; i<gui.main.main.gazouillisSession.size();i++){
//			gui.main.main.gazouillis.add(gui.main.main.gazouillisSession.get(i));
			vF.getArea().append(gui.main.main.gazouillisSession.get(i).toString());
			gui.main.main.gazouillisSession.remove(i);
		}
		
		
	}
}



