package gui.listeners;


import gui.vues.VueInscription;
import jms.SenderTwitter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class LInscription implements ActionListener
{

	private VueInscription vi;

	public LInscription (VueInscription _vi)
	{
		this.vi=_vi;
	}

	public void actionPerformed(ActionEvent e)
	{
		//TODO : Gérer la Validation
		System.out.println("OK j'ai cliqué sur m'inscrire");
		System.out.println(vi.getPseudo().getText()+" "+vi.getPassword().getText()+" "+vi.getNom().getText()+" "+vi.getPrenom().getText()+" "+vi.getVille().getText());
		
		SenderTwitter.inscription(vi.getPseudo().getText(), vi.getPassword().getText(), vi.getNom().getText(), vi.getPrenom().getText(), vi.getVille().getText());
		
		String message = SenderTwitter.getMessageRetour();

		JOptionPane.showMessageDialog(vi, message, "Information Inscription", JOptionPane.INFORMATION_MESSAGE);
	}
}



