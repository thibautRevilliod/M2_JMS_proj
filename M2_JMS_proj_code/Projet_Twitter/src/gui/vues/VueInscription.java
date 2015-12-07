package gui.vues;

import gui.listeners.LFermerInscription;
import gui.listeners.LInscription;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;

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
		lblEntrezVotrePseudo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEntrezVotrePseudo.setBounds(29, 48, 241, 20);
		getContentPane().add(lblEntrezVotrePseudo);
		
		lblEntrezVotreMot = new JLabel("Entrez votre mot de passe :");
		lblEntrezVotreMot.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEntrezVotreMot.setBounds(29, 84, 241, 20);
		getContentPane().add(lblEntrezVotreMot);
		
		lblEntrezVotreNom = new JLabel("Entrez votre nom :");
		lblEntrezVotreNom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEntrezVotreNom.setBounds(29, 120, 241, 20);
		getContentPane().add(lblEntrezVotreNom);
		
		lblEntrezVotrePrnom = new JLabel("Entrez votre prenom :");
		lblEntrezVotrePrnom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEntrezVotrePrnom.setBounds(29, 156, 241, 20);
		getContentPane().add(lblEntrezVotrePrnom);
		
		lblEntrezVotreVille = new JLabel("Entrez votre ville :");
		lblEntrezVotreVille.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEntrezVotreVille.setBounds(29, 189, 241, 20);
		getContentPane().add(lblEntrezVotreVille);
		
		pseudo = new JTextField();
		pseudo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pseudo.setBounds(296, 48, 175, 26);
		getContentPane().add(pseudo);
		pseudo.setColumns(10);
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 18));
		password.setBounds(296, 84, 175, 26);
		getContentPane().add(password);
		password.setColumns(10);
		
		nom = new JTextField();
		nom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nom.setBounds(296, 120, 175, 26);
		getContentPane().add(nom);
		nom.setColumns(10);
		
		prenom = new JTextField();
		prenom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		prenom.setBounds(296, 156, 175, 26);
		getContentPane().add(prenom);
		prenom.setColumns(10);
		
		ville = new JTextField();
		ville.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ville.setBounds(296, 192, 175, 26);
		getContentPane().add(ville);
		ville.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnValider.setBounds(144, 249, 115, 29);
		getContentPane().add(btnValider);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
