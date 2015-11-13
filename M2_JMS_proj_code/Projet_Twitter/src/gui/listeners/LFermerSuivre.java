package gui.listeners;

import gui.vues.VueSuivre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;



public class LFermerSuivre implements ActionListener, WindowListener 
{

		
	private VueSuivre vue;
		
	
	public LFermerSuivre (VueSuivre pvue)
	{
			this.vue=pvue;
	}
	
	
	public void actionPerformed(ActionEvent arg0) 
	{
		int reponse = JOptionPane.showConfirmDialog(this.vue, "Voulez-vous réellemment fermer la fenêtre ?", "Quitter ?", JOptionPane.YES_NO_OPTION);
		
		
		if (reponse == JOptionPane.YES_OPTION) 
		{
			this.vue.setVisible(false);
		}
		
	}
		
	

	
	public void windowClosing(WindowEvent arg0) 
	{
		int reponse = JOptionPane.showConfirmDialog(this.vue, "Voulez réellemment fermer la fenêtre ?", "Quitter ?", JOptionPane.YES_NO_OPTION);
					
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
	
}
	
	
	
	
	
