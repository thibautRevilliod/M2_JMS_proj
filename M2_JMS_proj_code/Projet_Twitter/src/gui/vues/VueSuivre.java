package gui.vues;

import gui.listeners.LFermerSuivre;
import gui.listeners.LValiderSuivre;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VueSuivre extends JFrame{
		
	private JLabel lblNewLabel;
	private JTextField textField;
	

	public VueSuivre ()
	{
		
		this.setTitle("Rechercher et s'abonner");
		this.setLocationRelativeTo(null);
		this.setSize(515,230);
		this.setResizable(false);
		this.setLocation(50, 50);
	
		this.addWindowListener(new LFermerSuivre (this));
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Rechercher un gazouilleur :");
		lblNewLabel.setBounds(38, 44, 203, 20);
		getContentPane().add(lblNewLabel);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(289, 106, 115, 29);
		getContentPane().add(btnQuitter);
		
		JButton btnSabonner = new JButton("Valider");
		btnSabonner.setBounds(107, 106, 115, 29);
		getContentPane().add(btnSabonner);
		
		textField = new JTextField();
		textField.setBounds(270, 41, 146, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		btnSabonner.addActionListener(new LValiderSuivre(this));
		btnQuitter.addActionListener(new LFermerSuivre (this));
		this.addWindowListener(new LFermerSuivre (this));
	}	
}
