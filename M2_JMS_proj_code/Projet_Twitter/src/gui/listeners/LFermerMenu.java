package gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import gui.vues.VueMenuDepart;
import jms.SenderTwitter;



public class LFermerMenu implements ActionListener, WindowListener 
{

		
	private VueMenuDepart vue;
		
	
	public LFermerMenu (VueMenuDepart pvue)
	{
			this.vue=pvue;
	}
	
	
	public void actionPerformed(ActionEvent arg0) 
	{
		int reponse = JOptionPane.showConfirmDialog(this.vue, "Voulez-vous réellemment fermer la fenêtre ?", "Quitter ?", JOptionPane.YES_NO_OPTION);
		
		
		if (reponse == JOptionPane.YES_OPTION) 
		{
			if (null != gui.main.main.profilConnecte)
			{
				SenderTwitter.deconnexion(gui.main.main.profilConnecte.getPSEUDO());
				gui.main.main.profilConnecte = null;
			}			
			System.exit(0);
		}
		
		
	}

	
	
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}


	//@Override
	public void windowClosing(WindowEvent arg0) {
		int reponse = JOptionPane.showConfirmDialog(this.vue, "Voulez réellemment fermer la fenêtre ?", "Quitter ?", JOptionPane.YES_NO_OPTION);
		if (null != gui.main.main.profilConnecte)
		{
			SenderTwitter.deconnexion(gui.main.main.profilConnecte.getPSEUDO());
			gui.main.main.profilConnecte = null;
		}	
		
		if (reponse == JOptionPane.YES_OPTION) 
		{
			System.exit(0);
		}
		
	} 
	
}
	
	
	
	
	
