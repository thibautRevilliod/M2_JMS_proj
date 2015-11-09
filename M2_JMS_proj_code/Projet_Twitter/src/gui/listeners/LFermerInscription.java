package gui.listeners;

import gui.vues.VueConnexion;
import gui.vues.VueInscription;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;



public class LFermerInscription implements ActionListener, WindowListener 
{

		
	private VueInscription vue;
		
	
	public LFermerInscription (VueInscription pvue)
	{
			this.vue=pvue;
	}
	
	
	public void actionPerformed(ActionEvent arg0) 
	{
		int reponse = JOptionPane.showConfirmDialog(this.vue, "Voulez-vous r�ellemment fermer la fen�tre ?", "Quitter ?", JOptionPane.YES_NO_OPTION);
		
		
		if (reponse == JOptionPane.YES_OPTION) 
		{
			this.vue.setVisible(false);
		}
		
	}

	
	public void windowClosing(WindowEvent arg0) 
	{
		int reponse = JOptionPane.showConfirmDialog(this.vue, "Voulez r�ellemment fermer la fen�tre ?", "Quitter ?", JOptionPane.YES_NO_OPTION);
					
		if (reponse == JOptionPane.YES_OPTION) 
		{
			System.exit(0);
		}
		
	}
	
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {} 
	
}
	
	
	
	
	
