package gui.vues;

import gui.listeners.*;


import javax.swing.*;
import java.awt.Font;


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
		this.setResizable(false);
		JLabel lblIdentifiant = new JLabel("Pseudo :");
		lblIdentifiant.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIdentifiant.setBounds(35, 39, 98, 20);
		getContentPane().add(lblIdentifiant);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMotDePasse.setBounds(35, 104, 147, 20);
		getContentPane().add(lblMotDePasse);
		
		pseudo = new JTextField();
		pseudo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pseudo.setBounds(224, 39, 166, 26);
		getContentPane().add(pseudo);
		pseudo.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(224, 104, 166, 26);
		getContentPane().add(passwordField);
		
		btnValider = new JButton("Valider");
		btnValider.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnValider.setBounds(67, 183, 115, 29);
		getContentPane().add(btnValider);
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnQuitter.setBounds(229, 183, 115, 29);
		getContentPane().add(btnQuitter);
		
		
		
		// Abonnements :
		btnValider.addActionListener(new LConnexion (this));
		btnQuitter.addActionListener(new LFermerConnexion (this));
		this.addWindowListener(new LFermerConnexion (this));
		getContentPane().setLayout(null);
				
				
	}


	public JTextField getPseudo() {
		return pseudo;
	}


	public JTextField getPasswordField() {
		return passwordField;
	}
	
}
