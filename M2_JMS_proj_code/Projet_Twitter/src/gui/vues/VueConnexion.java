package gui.vues;

import gui.listeners.*;


import javax.swing.*;


public class VueConnexion extends JFrame
{
	
	private JTextField pseudo;
		private JPasswordField passwordField;

		private JButton btnValider;

		private JButton btnQuitter;
	

	public VueConnexion ()
	{
		setTitle("Connexion");
		setSize(450, 300);
		setLocation(400, 300);
		
		JLabel lblIdentifiant = new JLabel("Identifiant :");
		lblIdentifiant.setBounds(35, 39, 98, 20);
		getContentPane().add(lblIdentifiant);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setBounds(35, 104, 110, 20);
		getContentPane().add(lblMotDePasse);
		
		pseudo = new JTextField();
		pseudo.setBounds(166, 36, 146, 26);
		getContentPane().add(pseudo);
		pseudo.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(166, 101, 146, 26);
		getContentPane().add(passwordField);
		
		btnValider = new JButton("Valider");
		btnValider.setBounds(67, 183, 115, 29);
		getContentPane().add(btnValider);
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(229, 183, 115, 29);
		getContentPane().add(btnQuitter);
		
		
		
		// Abonnements :
		btnValider.addActionListener(new LConnexion (this));
		btnQuitter.addActionListener(new LFermer (this));
		this.addWindowListener(new LFermer (this));
		getContentPane().setLayout(null);
				
				
	}


	public JTextField getPseudo() {
		return pseudo;
	}


	public JTextField getPasswordField() {
		return passwordField;
	}
	
}
