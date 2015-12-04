package gui.listeners;


import gui.vues.VueGazouiller;
import gui.vues.VueInscription;
import gui.vues.VueMenuDepart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.JOptionPane;

import jms.SenderTwitter;

public class LEnvoyerMessage implements ActionListener
{

	private VueGazouiller vg;

	public LEnvoyerMessage (VueGazouiller vue)
	{
		this.vg=vue;
	}

	public void actionPerformed(ActionEvent e)
	{
		boolean estGeolocalise = false;
		String contenuGazouilli;

    	for (Enumeration<AbstractButton> buttons = vg.getGroup().getElements(); buttons.hasMoreElements();) 
    	{
            AbstractButton radioButton = buttons.nextElement();
            if (radioButton.isSelected()) 
            {
            	estGeolocalise = radioButton.getName().equals("Oui") ? true : false; 
            }
        }
    	
    	contenuGazouilli = vg.getTextArea().getText();
    	contenuGazouilli = contenuGazouilli.replaceAll("'", "\'");

		System.out.println("OK j'ai cliqué sur envoyer le message");

		SenderTwitter.creerGazouilliTopic(contenuGazouilli, gui.main.main.profilConnecte.getPSEUDO(), estGeolocalise);
		String message = SenderTwitter.getMessageRetour();
		JOptionPane.showMessageDialog(vg, message, "Information envoyer message", JOptionPane.INFORMATION_MESSAGE);
		if (message.contains("OK")){
			this.vg.setVisible(false);
		}
	}
}


