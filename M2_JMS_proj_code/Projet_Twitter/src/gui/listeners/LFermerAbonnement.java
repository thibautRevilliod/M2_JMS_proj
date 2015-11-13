package gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import gui.vues.VueAbonnement;



public class LFermerAbonnement implements ActionListener, WindowListener 
{

		
	private VueAbonnement vue;
		
	
	public LFermerAbonnement (VueAbonnement vue)
	{
			this.vue=vue;
	}
	
	
	public void actionPerformed(ActionEvent arg0) 
	{
		int reponse = JOptionPane.showConfirmDialog(this.vue, "Voulez-vous réellemment fermer la fenêtre ?", "Quitter ?", JOptionPane.YES_NO_OPTION);
		
		
		if (reponse == JOptionPane.YES_OPTION) 
		{
			this.vue.setVisible(false);
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
		int reponse = JOptionPane.showConfirmDialog(this.vue, "Voulez-vous réellemment fermer la fenêtre ?", "Quitter ?", JOptionPane.YES_NO_OPTION);
		
		
		if (reponse == JOptionPane.YES_OPTION) 
		{
			this.vue.setVisible(false);
		}
		
	} 
	
}
	
	
	
	
	
