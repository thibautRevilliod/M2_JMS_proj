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
		String message;
		System.out.println("OK j'ai cliqué sur m'inscrire");
		System.out.println(vi.getPseudo().getText()+" "+vi.getPassword().getText()+" "+vi.getNom().getText()+" "+vi.getPrenom().getText()+" "+vi.getVille().getText());
		if (null != vi.getPseudo()&& null != vi.getPassword()&& null!= vi.getNom() && null!= vi.getPrenom()&& null!= vi.getVille())
		{
			if (! vi.getPseudo().getText().equals("") && ! vi.getPassword().equals("") && ! vi.getNom().getText().equals("") && ! vi.getPrenom().getText().equals("") && ! vi.getVille().getText().equals(""))
				{
					SenderTwitter.inscription(vi.getPseudo().getText(), vi.getPassword().getText(), vi.getNom().getText(), vi.getPrenom().getText(), vi.getVille().getText());
					message = SenderTwitter.getMessageRetour();
					JOptionPane.showMessageDialog(vi, message, "Information Inscription", JOptionPane.INFORMATION_MESSAGE);
				}
			else 
			{
				message="Vous devez remplir les 5 champs : pseudo, mot de passe, nom, prenom et ville.";
				JOptionPane.showMessageDialog(vi, message, "Erreur Inscription", JOptionPane.WARNING_MESSAGE);
			}
		}
		else 
		{
			message="Vous devez remplir les 5 champs : pseudo, mot de passe, nom, prenom et ville.";
			JOptionPane.showMessageDialog(vi, message, "Erreur Inscription", JOptionPane.WARNING_MESSAGE);
		}
	}
}



