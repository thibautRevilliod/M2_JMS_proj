package gui.vues;

import gui.listeners.LFermerAbonnement;
import gui.listeners.LSuivre;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

public class VueAbonnement extends JFrame{
		
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	

	public VueAbonnement ()
	{
		
		this.setTitle("Abonnements");
		this.setLocationRelativeTo(null);
		this.setSize(515,345);
		this.setResizable(false);
		this.setLocation(50, 50);
	
		this.addWindowListener(new LFermerAbonnement (this));
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Suiveurs :");
		lblNewLabel.setBounds(38, 44, 101, 20);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Suivis :");
		lblNewLabel_1.setBounds(290, 44, 69, 20);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(290, 260, 115, 29);
		getContentPane().add(btnQuitter);
		
		JList list = new JList();
		list.setBounds(35, 79, 184, 151);
		getContentPane().add(list);
		
		JList list_1 = new JList();
		list_1.setBounds(290, 80, 184, 151);
		getContentPane().add(list_1);
		
		JButton btnSabonner = new JButton("S'abonner");
		btnSabonner.setBounds(119, 260, 115, 29);
		getContentPane().add(btnSabonner);
		
		btnSabonner.addActionListener(new LSuivre(this));
		btnQuitter.addActionListener(new LFermerAbonnement (this));
		this.addWindowListener(new LFermerAbonnement (this));
	}	
}
