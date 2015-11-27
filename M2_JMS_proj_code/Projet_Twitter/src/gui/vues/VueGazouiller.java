package gui.vues;

import gui.listeners.LConnexion;
import gui.listeners.LEnvoyerMessage;
import gui.listeners.LFermerConnexion;
import gui.listeners.LFermerGazouiller;
import gui.listeners.LFermerInscription;
import gui.listeners.LInscription;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

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
		this.setLocation(800, 300);
	
		this.addWindowListener(new LFermerGazouiller (this));
		this.addWindowListener(new LFermerGazouiller (this));
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("G\u00E9olocaliser ?");
		lblNewLabel.setBounds(35, 27, 101, 20);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Message");
		lblNewLabel_1.setBounds(35, 95, 69, 20);
		getContentPane().add(lblNewLabel_1);
		
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
				
		JRadioButton rdbtnOui = new JRadioButton("Oui");
		rdbtnOui.setBounds(169, 23, 91, 29);
				
		JRadioButton rdbtnNon = new JRadioButton("Non");
		rdbtnNon.setBounds(280, 23, 76, 29);
		
        ButtonGroup group = new ButtonGroup();
        
        group.add(rdbtnOui);
        add(rdbtnOui);
        
        group.add(rdbtnNon);
        add(rdbtnNon);
		
		// Abonnements :
		btnEnvoyer.addActionListener(new LEnvoyerMessage(this));    // A COMPLETER AVEC LA BDD
		btnQuitter.addActionListener(new LFermerGazouiller (this));
		this.addWindowListener(new LFermerGazouiller (this));
	}	
}
