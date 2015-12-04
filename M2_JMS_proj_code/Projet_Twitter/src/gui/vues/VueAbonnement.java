package gui.vues;

import gui.listeners.LFermerAbonnement;
import gui.listeners.LSeDesabonner;
import gui.listeners.LSuivre;

import java.awt.Dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;

public class VueAbonnement extends JFrame{
		
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JList<String> listPseudoSuivis;
	

	public VueAbonnement ()
	{	this.setTitle("Abonnements");
		this.setLocationRelativeTo(null);
		this.setSize(515,432);
		this.setResizable(false);
		this.setLocation(800, 300);
		this.setResizable(false);
		this.addWindowListener(new LFermerAbonnement (this));
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Suiveurs :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(38, 44, 101, 20);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Suivis :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(290, 44, 69, 20);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnQuitter.setBounds(351, 347, 115, 29);
		getContentPane().add(btnQuitter);
		
		JList<String> list = new JList<String>();
		list.setFont(new Font("Tahoma", Font.PLAIN, 18));
		//On transforme l'arrayList des profils abonnes en String[] pour l'afficher dans l'interface.
		String[] listAbonnes = new String[gui.main.main.profilsAbonnes.size()];
		for (int i=0; i<gui.main.main.profilsAbonnes.size();i++){
			listAbonnes[i]=gui.main.main.profilsAbonnes.get(i);
		}
		list.setListData(listAbonnes);
		list.setBounds(15, 79, 225, 196);
		getContentPane().add(list);
		
		listPseudoSuivis = new JList<String>();
		listPseudoSuivis.setFont(new Font("Tahoma", Font.PLAIN, 18));
		//On transforme l'arrayList des profils suivis en String[] pour l'afficher dans l'interface.
		String[] listSuivis = new String[gui.main.main.profilsSuivis.size()];
		for (int i=0; i<gui.main.main.profilsSuivis.size();i++){
			listSuivis[i]=gui.main.main.profilsSuivis.get(i);
			System.out.println("listSuivis[i]"+listSuivis[i]);
		}
		listPseudoSuivis.setListData(listSuivis);
		listPseudoSuivis.setBounds(269, 81, 225, 195);
		getContentPane().add(listPseudoSuivis);
		
		JButton btnSabonner = new JButton("Suivre un gazouilleur");
		btnSabonner.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSabonner.setBounds(38, 347, 237, 29);
		getContentPane().add(btnSabonner);
		
		JButton btnSeDesabonner = new JButton("Se desabonner");
		btnSeDesabonner.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSeDesabonner.setBounds(290, 282, 184, 29);
		getContentPane().add(btnSeDesabonner);
		
		btnSeDesabonner.addActionListener(new LSeDesabonner(this));
		btnSabonner.addActionListener(new LSuivre(this));
		btnQuitter.addActionListener(new LFermerAbonnement (this));
	}	

	public String getPseudoSuiviSelected() {
		return listPseudoSuivis.getSelectedValue();
	}

}
