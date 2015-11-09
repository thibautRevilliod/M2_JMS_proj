package gui.vues;

import gui.listeners.LConnexion;
import gui.listeners.LFermer;
import gui.listeners.LFermerInscription;
import gui.listeners.LInscription;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VueInscription extends JFrame{
		
	private JPanel panelInscription; 
		private JLabel lblEntrezVotrePseudo;
		private JLabel lblEntrezVotreMot;
		private JLabel lblEntrezVotreNom;
		private JLabel lblEntrezVotrePrnom;
		private JLabel lblEntrezVotreVille;
		private JTextField textField;
		private JTextField textField_1;
		private JTextField textField_2;
		private JTextField textField_3;
		private JTextField textField_4;
	

	public VueInscription ()
	{
		
		this.setTitle("Inscription");
		this.setLocationRelativeTo(null);
		this.setSize(515,345);
		this.setResizable(false);
		this.setLocation(50, 50);
	
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
		
		lblEntrezVotrePrnom = new JLabel("Entrez votre pr\u00E9nom :");
		lblEntrezVotrePrnom.setBounds(48, 150, 211, 20);
		getContentPane().add(lblEntrezVotrePrnom);
		
		lblEntrezVotreVille = new JLabel("Entrez votre ville :");
		lblEntrezVotreVille.setBounds(48, 183, 211, 20);
		getContentPane().add(lblEntrezVotreVille);
		
		textField = new JTextField();
		textField.setBounds(274, 39, 146, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(274, 75, 146, 26);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(274, 111, 146, 26);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(274, 147, 146, 26);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(274, 183, 146, 26);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(144, 249, 115, 29);
		getContentPane().add(btnValider);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(296, 249, 115, 29);
		getContentPane().add(btnQuitter);
		
		
		// Abonnements :
		btnValider.addActionListener(new LInscription (this));    // A COMPLETER AVEC LA BDD
		btnQuitter.addActionListener(new LFermerInscription (this));
		this.addWindowListener(new LFermerInscription (this));
					
				
	}	
}
