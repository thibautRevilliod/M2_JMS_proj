package gui.vues;

import javax.swing.JFrame;

import gui.listeners.LAbonnements;
import gui.listeners.LFermerFildActu;
import gui.listeners.LGazouiller;
import gui.listeners.LParametres;

import java.awt.ScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;

public class VueFildActu extends JFrame

{
	public VueFildActu() {
		setTitle("Fil d'actualit\u00E9s");
		setSize(473, 453);
		setLocation(400, 300);
		getContentPane().setLayout(null);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(10, 52, 419, 269);
		getContentPane().add(scrollPane);
		
		JButton btnGazouiller = new JButton("Gazouiller");
		btnGazouiller.setBounds(314, 16, 115, 29);
		getContentPane().add(btnGazouiller);
		
		JButton btnParamtres = new JButton("Parametres");
		btnParamtres.setBounds(159, 350, 115, 29);
		getContentPane().add(btnParamtres);
		
		JButton btnQuitter = new JButton("Se Deconnecter");
		btnQuitter.setBounds(282, 350, 154, 29);
		getContentPane().add(btnQuitter);
		
		JButton btnAbonnements = new JButton("Abonnements");
		btnAbonnements.setBounds(15, 350, 135, 29);
		getContentPane().add(btnAbonnements);
		
		JLabel lblMonPseudo = new JLabel(gui.main.main.profilConnecte.getPSEUDO());
		lblMonPseudo.setBounds(15, 20, 135, 20);
		getContentPane().add(lblMonPseudo);
	
		// Abonnements :
		btnAbonnements.addActionListener(new LAbonnements(this));
		btnGazouiller.addActionListener(new LGazouiller (this)); 
		btnParamtres.addActionListener(new LParametres (this)); 
		btnQuitter.addActionListener(new LFermerFildActu (this));
		this.addWindowListener(new LFermerFildActu (this));
	}
}
