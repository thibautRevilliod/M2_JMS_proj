package gui.vues;

import gui.listeners.LConnexion;
import gui.listeners.LEnvoyerMessage;
import gui.listeners.LFermer;
import gui.listeners.LFermerGazouiller;
import gui.listeners.LFermerInscription;
import gui.listeners.LInscription;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class VueGazouiller extends JFrame{
		
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	

	public VueGazouiller ()
	{
		
		this.setTitle("Gazouiller");
		this.setLocationRelativeTo(null);
		this.setSize(515,345);
		this.setResizable(false);
		this.setLocation(50, 50);
	
		this.addWindowListener(new LFermerGazouiller (this));
		this.addWindowListener(new LFermerGazouiller (this));
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Destinataire");
		lblNewLabel.setBounds(35, 27, 101, 20);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Message");
		lblNewLabel_1.setBounds(35, 95, 69, 20);
		getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(169, 24, 36, 26);
		getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(169, 92, 261, 124);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnEnvoyer = new JButton("Envoyer");
		btnEnvoyer.setBounds(94, 247, 115, 29);
		getContentPane().add(btnEnvoyer);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(259, 247, 115, 29);
		getContentPane().add(btnQuitter);
					
		
		// Abonnements :
		btnEnvoyer.addActionListener(new LEnvoyerMessage(this));    // A COMPLETER AVEC LA BDD
		btnQuitter.addActionListener(new LFermerGazouiller (this));
		this.addWindowListener(new LFermerGazouiller (this));
	}	
}
