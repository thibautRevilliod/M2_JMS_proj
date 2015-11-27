package gui.vues;

import gui.listeners.LConnexion;
import gui.listeners.LEnregistrerParametres;
import gui.listeners.LFermerConnexion;
import gui.listeners.LFermerInscription;
import gui.listeners.LFermerParametres;
import gui.listeners.LInscription;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VueParametres extends JFrame{
		
	private JPanel panelInscription; 
		private JLabel lblEntrezVotrePseudo;
		private JLabel lblEntrezVotreMot;
		private JLabel lblEntrezVotreNom;
		private JLabel lblEntrezVotrePrnom;
		private JLabel lblEntrezVotreVille;
		private JLabel lblPseudo;
		private JTextField champsMdp;
		private JTextField champsNom;
		private JTextField champsPrenom;
		private JTextField champsVille;
	

	


	public VueParametres ()
	{
		
		this.setTitle("Param\u00E8tres");
		this.setLocationRelativeTo(null);
		this.setSize(515,345);
		this.setResizable(false);
		this.setLocation(800, 300);
	
		this.addWindowListener(new LFermerParametres (this));
		getContentPane().setLayout(null);
		
		lblEntrezVotrePseudo = new JLabel("Entrez votre pseudo :");
		lblEntrezVotrePseudo.setBounds(48, 42, 211, 20);
		getContentPane().add(lblEntrezVotrePseudo);
		
		lblPseudo = new JLabel(gui.main.main.profilConnecte.getPSEUDO());
		lblPseudo.setBounds(274, 39, 146, 26);
		getContentPane().add(lblPseudo);
		
		lblEntrezVotreMot = new JLabel("Entrez votre mot de passe :");
		lblEntrezVotreMot.setBounds(48, 78, 211, 20);
		getContentPane().add(lblEntrezVotreMot);
		
		lblEntrezVotreNom = new JLabel("Entrez votre nom :");
		lblEntrezVotreNom.setBounds(48, 114, 211, 20);
		getContentPane().add(lblEntrezVotreNom);
		
		lblEntrezVotrePrnom = new JLabel("Entrez votre pr\u00E9nom :");
		lblEntrezVotrePrnom.setBounds(48, 150, 211, 20);
		getContentPane().add(lblEntrezVotrePrnom);
		
		lblEntrezVotreVille = new JLabel("Entrez votre ville :");
		lblEntrezVotreVille.setBounds(48, 183, 211, 20);
		getContentPane().add(lblEntrezVotreVille);
		
		champsMdp = new JTextField(gui.main.main.profilConnecte.getMDP());
		champsMdp.setBounds(274, 75, 146, 26);
		getContentPane().add(champsMdp);
		champsMdp.setColumns(10);
		
		champsNom = new JTextField(gui.main.main.profilConnecte.getNOM());
		champsNom.setBounds(274, 111, 146, 26);
		getContentPane().add(champsNom);
		champsNom.setColumns(10);
		
		champsPrenom = new JTextField(gui.main.main.profilConnecte.getPRENOM());
		champsPrenom.setBounds(274, 147, 146, 26);
		getContentPane().add(champsPrenom);
		champsPrenom.setColumns(10);
		
		champsVille = new JTextField(gui.main.main.profilConnecte.getVILLE());
		champsVille.setBounds(274, 183, 146, 26);
		getContentPane().add(champsVille);
		champsVille.setColumns(10);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setBounds(144, 249, 115, 29);
		getContentPane().add(btnEnregistrer);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(296, 249, 115, 29);
		getContentPane().add(btnQuitter);
		
		
		// Abonnements :
		btnEnregistrer.addActionListener(new LEnregistrerParametres (this));    // A COMPLETER AVEC LA BDD
		btnQuitter.addActionListener(new LFermerParametres (this));
		this.addWindowListener(new LFermerParametres (this));
				
				
	}	
	
	public JTextField getChampsMdp() {
		return champsMdp;
	}


	public JTextField getChampsNom() {
		return champsNom;
	}


	public JTextField getChampsPrenom() {
		return champsPrenom;
	}


	public JTextField getChampsVille() {
		return champsVille;
	}
}
