package gui.listeners;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gui.vues.VueAbonnement;
import gui.vues.VueSuivre;
import jms.SenderTwitter;




public class LSuivre implements ActionListener
{

	private VueAbonnement vA;

	public LSuivre (VueAbonnement _vA)
	{
		this.vA=_vA;
	}

	public void actionPerformed(ActionEvent e)
	{
		SenderTwitter.listeDesProfils();
		String message = SenderTwitter.getMessageRetour();
		JOptionPane.showMessageDialog(vA, message, "Information Liste des profils", JOptionPane.INFORMATION_MESSAGE);
		gui.main.main.tousLesProfils = SenderTwitter.getListeProfil();

		
		
		VueSuivre vP = new VueSuivre();
		vP.setVisible(true);
		vP.setLocation(800, 300);
		this.vA.dispose();
		
		
	}
}
