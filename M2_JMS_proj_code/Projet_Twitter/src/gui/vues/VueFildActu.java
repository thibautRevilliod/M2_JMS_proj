package gui.vues;

import javax.swing.JFrame;

import gui.listeners.LAbonnements;
import gui.listeners.LFermerFildActu;
import gui.listeners.LGazouiller;
import gui.listeners.LParametres;

import java.awt.ScrollPane;
import javax.swing.JButton;

public class VueFildActu extends JFrame

{
	public VueFildActu() {
		setTitle("Fil d'actualit\u00E9s");
		setSize(450, 450);
		setLocation(400, 300);
		getContentPane().setLayout(null);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(10, 52, 408, 269);
		getContentPane().add(scrollPane);
		
		JButton btnGazouiller = new JButton("Gazouiller");
		btnGazouiller.setBounds(153, 16, 115, 29);
		getContentPane().add(btnGazouiller);
		
		JButton btnParamtres = new JButton("Parametres");
		btnParamtres.setBounds(153, 350, 115, 29);
		getContentPane().add(btnParamtres);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(282, 350, 115, 29);
		getContentPane().add(btnQuitter);
		
		JButton btnAbonnements = new JButton("Abonnements");
		btnAbonnements.setBounds(15, 350, 115, 29);
		getContentPane().add(btnAbonnements);
	
		// Abonnements :
		btnAbonnements.addActionListener(new LAbonnements(this));
		btnGazouiller.addActionListener(new LGazouiller (this)); 
		btnParamtres.addActionListener(new LParametres (this)); 
		btnQuitter.addActionListener(new LFermerFildActu (this));
		this.addWindowListener(new LFermerFildActu (this));
	}
}
