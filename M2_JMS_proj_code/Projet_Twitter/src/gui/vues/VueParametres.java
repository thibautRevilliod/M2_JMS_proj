package gui.vues;

import gui.listeners.LConnexion;
import gui.listeners.LEnregistrerParametres;
import gui.listeners.LFermerConnexion;
import gui.listeners.LFermerInscription;
import gui.listeners.LFermerParametres;
import gui.listeners.LInscription;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;

public class VueParametres extends JDialog {
		
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
	

	


	public VueParametres (JFrame jf) {
		super(jf,Dialog.ModalityType.DOCUMENT_MODAL);
		this.setTitle("Param\u00E8tres");
		this.setLocationRelativeTo(null);
		this.setSize(515,345);
		this.setResizable(false);
		this.setLocation(800, 300);
	
		this.addWindowListener(new LFermerParametres (this));
		getContentPane().setLayout(null);
		
		lblEntrezVotrePseudo = new JLabel("Entrez votre pseudo :");
		lblEntrezVotrePseudo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEntrezVotrePseudo.setBounds(26, 48, 233, 20);
		getContentPane().add(lblEntrezVotrePseudo);
		
		lblPseudo = new JLabel(gui.main.main.profilConnecte.getPSEUDO());
		lblPseudo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPseudo.setBounds(274, 48, 186, 26);
		getContentPane().add(lblPseudo);
		
		lblEntrezVotreMot = new JLabel("Entrez votre mot de passe :");
		lblEntrezVotreMot.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEntrezVotreMot.setBounds(26, 84, 233, 20);
		getContentPane().add(lblEntrezVotreMot);
		
		lblEntrezVotreNom = new JLabel("Entrez votre nom :");
		lblEntrezVotreNom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEntrezVotreNom.setBounds(26, 120, 233, 20);
		getContentPane().add(lblEntrezVotreNom);
		
		lblEntrezVotrePrnom = new JLabel("Entrez votre pr\u00E9nom :");
		lblEntrezVotrePrnom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEntrezVotrePrnom.setBounds(26, 156, 233, 20);
		getContentPane().add(lblEntrezVotrePrnom);
		
		lblEntrezVotreVille = new JLabel("Entrez votre ville :");
		lblEntrezVotreVille.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEntrezVotreVille.setBounds(26, 189, 233, 20);
		getContentPane().add(lblEntrezVotreVille);
		
		champsMdp = new JTextField(gui.main.main.profilConnecte.getMDP());
		champsMdp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		champsMdp.setBounds(274, 81, 186, 26);
		getContentPane().add(champsMdp);
		champsMdp.setColumns(10);
		
		champsNom = new JTextField(gui.main.main.profilConnecte.getNOM());
		champsNom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		champsNom.setBounds(274, 117, 186, 26);
		getContentPane().add(champsNom);
		champsNom.setColumns(10);
		
		champsPrenom = new JTextField(gui.main.main.profilConnecte.getPRENOM());
		champsPrenom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		champsPrenom.setBounds(274, 153, 186, 26);
		getContentPane().add(champsPrenom);
		champsPrenom.setColumns(10);
		
		champsVille = new JTextField(gui.main.main.profilConnecte.getVILLE());
		champsVille.setFont(new Font("Tahoma", Font.PLAIN, 18));
		champsVille.setBounds(274, 186, 186, 26);
		getContentPane().add(champsVille);
		champsVille.setColumns(10);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEnregistrer.setBounds(88, 249, 139, 29);
		getContentPane().add(btnEnregistrer);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
