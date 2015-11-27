package gui.vues;

import gui.listeners.LFermerInscription;
import gui.listeners.LInscription;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VueInscription extends JFrame{
		

		private JLabel lblEntrezVotrePseudo;
		private JLabel lblEntrezVotreMot;
		private JLabel lblEntrezVotreNom;
		private JLabel lblEntrezVotrePrnom;
		private JLabel lblEntrezVotreVille;
		private JTextField pseudo;
		private JPasswordField password;
		private JTextField nom;
		private JTextField prenom;
		private JTextField ville;
	

	public VueInscription ()
	{
		
		this.setTitle("Inscription");
		this.setLocationRelativeTo(null);
		this.setSize(515,345);
		this.setResizable(false);
		setLocation(800, 300);
	
		this.addWindowListener(new LFermerInscription (this));
		getContentPane().setLayout(null);
		
		lblEntrezVotrePseudo = new JLabel("Entrez votre pseudo :");
		lblEntrezVotrePseudo.setBounds(48, 42, 211, 20);
		getContentPane().add(lblEntrezVotrePseudo);
		
		lblEntrezVotreMot = new JLabel("Entrez votre mot de passe :");
		lblEntrezVotreMot.setBounds(48, 78, 211, 20);
		getContentPane().add(lblEntrezVotreMot);
		
		lblEntrezVotreNom = new JLabel("Entrez votre nom :");
		lblEntrezVotreNom.setBounds(48, 114, 211, 20);
		getContentPane().add(lblEntrezVotreNom);
		
		lblEntrezVotrePrnom = new JLabel("Entrez votre prenom :");
		lblEntrezVotrePrnom.setBounds(48, 150, 211, 20);
		getContentPane().add(lblEntrezVotrePrnom);
		
		lblEntrezVotreVille = new JLabel("Entrez votre ville :");
		lblEntrezVotreVille.setBounds(48, 183, 211, 20);
		getContentPane().add(lblEntrezVotreVille);
		
		pseudo = new JTextField();
		pseudo.setBounds(274, 39, 146, 26);
		getContentPane().add(pseudo);
		pseudo.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(274, 75, 146, 26);
		getContentPane().add(password);
		password.setColumns(10);
		
		nom = new JTextField();
		nom.setBounds(274, 111, 146, 26);
		getContentPane().add(nom);
		nom.setColumns(10);
		
		prenom = new JTextField();
		prenom.setBounds(274, 147, 146, 26);
		getContentPane().add(prenom);
		prenom.setColumns(10);
		
		ville = new JTextField();
		ville.setBounds(274, 183, 146, 26);
		getContentPane().add(ville);
		ville.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(144, 249, 115, 29);
		getContentPane().add(btnValider);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(296, 249, 115, 29);
		getContentPane().add(btnQuitter);
		
		
		// Abonnements :
		btnValider.addActionListener(new LInscription (this));    // A COMPLETER AVEC LA BDD
		btnQuitter.addActionListener(new LFermerInscription (this));
		//this.addWindowListener(new LFermerInscription (this));
					
		
		
	}	
	
	//Getters and Setters
	public JTextField getPseudo() {
		return pseudo;
	}


	public void setPseudo(JTextField pseudo) {
		this.pseudo = pseudo;
	}


	public JTextField getPassword() {
		return password;
	}


	public void setPassword(JPasswordField password) {
		this.password = password;
	}


	public JTextField getNom() {
		return nom;
	}


	public void setNom(JTextField nom) {
		this.nom = nom;
	}


	public JTextField getPrenom() {
		return prenom;
	}


	public void setPrenom(JTextField prenom) {
		this.prenom = prenom;
	}


	public JTextField getVille() {
		return ville;
	}


	public void setVille(JTextField ville) {
		this.ville = ville;
	}
}
