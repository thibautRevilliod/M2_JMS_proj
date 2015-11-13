package gui.vues;

import gui.listeners.LConsulter;
import gui.listeners.LFermerSuivre;
import gui.listeners.LSeDesabonner;
import gui.listeners.LValiderSuivre;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class VueSuivre extends JFrame{
		
	private JLabel lblNewLabel;
	private JTextField textField;
	

	public VueSuivre ()
	{
		
		this.setTitle("Rechercher et s'abonner");
		this.setLocationRelativeTo(null);
		this.setSize(554,230);
		this.setResizable(false);
		this.setLocation(50, 50);
	
		this.addWindowListener(new LFermerSuivre (this));
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Par pseudo :");
		lblNewLabel.setBounds(38, 44, 203, 20);
		getContentPane().add(lblNewLabel);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(418, 145, 115, 29);
		getContentPane().add(btnQuitter);
		
		JButton btnSeDesabonner = new JButton("Se desabonner");
		btnSeDesabonner.setBounds(260, 145, 143, 29);
		getContentPane().add(btnSeDesabonner);
		
		textField = new JTextField();
		textField.setBounds(270, 41, 146, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(272, 86, 36, 26);
		getContentPane().add(comboBox);
		
		JLabel lblDansLaListe = new JLabel("Dans la liste :");
		lblDansLaListe.setBounds(48, 89, 130, 20);
		getContentPane().add(lblDansLaListe);
		
		JButton btnSabonner = new JButton("S'abonner");
		btnSabonner.setBounds(145, 145, 103, 29);
		getContentPane().add(btnSabonner);
		
		JButton btnConsulter = new JButton("Consulter");
		btnConsulter.setBounds(15, 145, 115, 29);
		getContentPane().add(btnConsulter);
		
		btnSeDesabonner.addActionListener(new LSeDesabonner(this));
		btnSabonner.addActionListener(new LValiderSuivre(this));
		btnConsulter.addActionListener(new LConsulter(this));
		btnQuitter.addActionListener(new LFermerSuivre (this));
		this.addWindowListener(new LFermerSuivre (this));
	}	
}
