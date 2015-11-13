package gui.vues;

import gui.listeners.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VueMenuDepart extends JFrame
{
	
	
	
	
	public VueMenuDepart ()
	{
		
		this.setTitle("Menu");
		this.setLocationRelativeTo(null);
		this.setSize(394,191);
		this.setResizable(false);
		getContentPane().setLayout(null);
		
		JButton btnInscription = new JButton("Inscription");
		btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnInscription.setBounds(15, 74, 115, 29);
		getContentPane().add(btnInscription);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.setBounds(137, 74, 115, 29);
		getContentPane().add(btnConnexion);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(258, 74, 115, 29);
		getContentPane().add(btnQuitter);
		
		JLabel lblQueVoulezVous = new JLabel("Que voulez vous faire ? ");
		lblQueVoulezVous.setBounds(36, 38, 265, 20);
		getContentPane().add(lblQueVoulezVous);
		
		// Abonnements :
		btnInscription.addActionListener(new LInscriptionMenu (this));    
		btnConnexion.addActionListener(new LConnexionMenu (this));    
		btnQuitter.addActionListener(new LFermerMenu (this));
		this.addWindowListener(new LFermerMenu (this));
				
				
	}	
	

	


	
	
	
}
