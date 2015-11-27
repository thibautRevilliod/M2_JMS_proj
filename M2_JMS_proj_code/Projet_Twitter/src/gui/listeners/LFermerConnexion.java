package gui.listeners;

import gui.vues.VueConnexion;
import gui.vues.VueMenuDepart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;



public class LFermerConnexion implements ActionListener, WindowListener 
{

		
	private VueConnexion vue;
		
	
	public LFermerConnexion (VueConnexion pvue)
	{
			this.vue=pvue;
	}
	
	
	public void actionPerformed(ActionEvent arg0) 
	{
		int reponse = JOptionPane.showConfirmDialog(this.vue, "Voulez-vous r�ellemment fermer la fen�tre ?", "Quitter ?", JOptionPane.YES_NO_OPTION);
		
		
		if (reponse == JOptionPane.YES_OPTION) 
		{
			this.vue.setVisible(false);
			VueMenuDepart menu = new VueMenuDepart();
			menu.setVisible(true);
		}
		
	}
		
	

	
	public void windowClosing(WindowEvent arg0) 
	{
		
			this.vue.setVisible(false);
			VueMenuDepart menu = new VueMenuDepart();
			menu.setVisible(true);
		
	}
	
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {} 
	
}
	
	
	
	
	
