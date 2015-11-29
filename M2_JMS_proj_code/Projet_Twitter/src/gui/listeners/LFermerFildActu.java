package gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import jms.SenderTwitter;
import gui.vues.VueFildActu;
import gui.vues.VueMenuDepart;



public class LFermerFildActu implements ActionListener, WindowListener 
{

		
	private VueFildActu vue;
		
	
	public LFermerFildActu (VueFildActu pvue)
	{
			this.vue=pvue;
	}
	
	
	public void actionPerformed(ActionEvent arg0) 
	{
		int reponse = JOptionPane.showConfirmDialog(this.vue, "Voulez-vous r�ellemment fermer la fen�tre ?", "Quitter ?", JOptionPane.YES_NO_OPTION);
		
		
		if (reponse == JOptionPane.YES_OPTION) 
		{
			try{
			SenderTwitter.deconnexion(gui.main.main.profilConnecte.getPSEUDO());
			gui.main.main.profilConnecte = null;
			String message = SenderTwitter.getMessageRetour();
			JOptionPane.showMessageDialog(vue, message, "Information Deconnexion", JOptionPane.INFORMATION_MESSAGE);
			}catch(Exception e){
				
			}
			
		
			
			this.vue.setVisible(false);
			VueMenuDepart menu = new VueMenuDepart();
			menu.setVisible(true);
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
		try{
			SenderTwitter.deconnexion(gui.main.main.profilConnecte.getPSEUDO());
			gui.main.main.profilConnecte = null;
			String message = SenderTwitter.getMessageRetour();
			JOptionPane.showMessageDialog(vue, message, "Information Deconnexion", JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception e){
			
		}
			
			VueMenuDepart menu = new VueMenuDepart();
			menu.setVisible(true);
	} 
	
}
	
	
	
	
	
