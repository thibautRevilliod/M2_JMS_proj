package gui.vues;

import gui.listeners.LFermerAbonnement;
import gui.listeners.LSeDesabonner;
import gui.listeners.LSuivre;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

public class VueAbonnement extends JFrame{
		
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JList<String> listPseudoSuivis;
	

	public VueAbonnement ()
	{
		
		this.setTitle("Abonnements");
		this.setLocationRelativeTo(null);
		this.setSize(515,432);
		this.setResizable(false);
		this.setLocation(800, 300);
	
		this.addWindowListener(new LFermerAbonnement (this));
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Suiveurs :");
		lblNewLabel.setBounds(38, 44, 101, 20);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Suivis :");
		lblNewLabel_1.setBounds(290, 44, 69, 20);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(290, 347, 115, 29);
		getContentPane().add(btnQuitter);
		
		JList<String> list = new JList<String>();
		//On transforme l'arrayList des profils abonnes en String[] pour l'afficher dans l'interface.
		String[] listAbonnes = new String[gui.main.main.profilsAbonnes.size()];
		for (int i=0; i<gui.main.main.profilsAbonnes.size();i++){
			listAbonnes[i]=gui.main.main.profilsAbonnes.get(i);
		}
		list.setListData(listAbonnes);
		list.setBounds(35, 79, 184, 196);
		getContentPane().add(list);
		
		listPseudoSuivis = new JList<String>();
		//On transforme l'arrayList des profils suivis en String[] pour l'afficher dans l'interface.
		String[] listSuivis = new String[gui.main.main.profilsSuivis.size()];
		for (int i=0; i<gui.main.main.profilsSuivis.size();i++){
			listSuivis[i]=gui.main.main.profilsSuivis.get(i);
			System.out.println("listSuivis[i]"+listSuivis[i]);
		}
		listPseudoSuivis.setListData(listSuivis);
		listPseudoSuivis.setBounds(290, 80, 184, 195);
		getContentPane().add(listPseudoSuivis);
		
		JButton btnSabonner = new JButton("Gérer les abonnements");
		btnSabonner.setBounds(80, 347, 195, 29);
		getContentPane().add(btnSabonner);
		
		JButton btnSeDesabonner = new JButton("Se desabonner");
		btnSeDesabonner.setBounds(314, 282, 143, 29);
		getContentPane().add(btnSeDesabonner);
		
		btnSeDesabonner.addActionListener(new LSeDesabonner(this));
		btnSabonner.addActionListener(new LSuivre(this));
		btnQuitter.addActionListener(new LFermerAbonnement (this));
		this.addWindowListener(new LFermerAbonnement (this));
	}	

	public String getPseudoSuiviSelected() {
		return listPseudoSuivis.getSelectedValue();
	}

}
