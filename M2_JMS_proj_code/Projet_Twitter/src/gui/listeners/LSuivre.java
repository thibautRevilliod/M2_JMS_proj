package gui.listeners;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gui.vues.VueAbonnement;
import gui.vues.VueSuivre;
import jms.SenderTwitter;




public class LSuivre implements ActionListener
{

	private VueAbonnement vA;
	private JFrame jFrameLiee;


	public LSuivre (VueAbonnement _vA, JFrame jf)
	{
		this.vA=_vA;
		this.jFrameLiee=jf;
	}

	public void actionPerformed(ActionEvent e)
	{
		SenderTwitter.listeDesProfils();
		String message = SenderTwitter.getMessageRetour();
		gui.main.main.tousLesProfils = SenderTwitter.getListeProfil();

		
		
		VueSuivre vP = new VueSuivre(this.jFrameLiee);
		vP.setVisible(true);
		vP.setLocation(800, 300);
		this.vA.setVisible(false);
		
		
	}
}
