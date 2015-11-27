package gui.vues;

import gui.listeners.LConsulter;
import gui.listeners.LFermerSuivre;
import gui.listeners.LSeDesabonner;
import gui.listeners.LValiderSuivre;
import metier.ProfilType;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class VueSuivre extends JFrame{
		
	private JLabel lblNewLabel;
	private JTextField recherche;
	JComboBox<String> listRecherche;
	

	public VueSuivre ()
	{
		
		this.setTitle("Rechercher et s'abonner");
		this.setLocationRelativeTo(null);
		this.setSize(554,230);
		this.setResizable(false);
		this.setLocation(800, 300);
	
		this.addWindowListener(new LFermerSuivre (this));
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Par pseudo :");
		lblNewLabel.setBounds(38, 44, 203, 20);
		getContentPane().add(lblNewLabel);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(418, 145, 115, 29);
		getContentPane().add(btnQuitter);
		
		
		recherche = new JTextField();
		recherche.setBounds(270, 41, 263, 26);
		getContentPane().add(recherche);
		recherche.setColumns(10);
		
		listRecherche = new JComboBox<String>();
		for (int i=0; i<gui.main.main.tousLesProfils.size();i++)
		{
			ProfilType pT = gui.main.main.tousLesProfils.get(i);
			if ( ! pT.getPSEUDO().equals(gui.main.main.profilConnecte.getPSEUDO()))
			{
				if(! gui.main.main.profilsSuivis.contains(pT.getPSEUDO())){
					listRecherche.addItem(pT.getPSEUDO());
				}
			}
			
		}
		listRecherche.setBounds(272, 86, 261, 26);
		getContentPane().add(listRecherche);
		
		JLabel lblDansLaListe = new JLabel("Dans la liste :");
		lblDansLaListe.setBounds(48, 89, 130, 20);
		getContentPane().add(lblDansLaListe);
		
		JButton btnSabonner = new JButton("S'abonner");
		btnSabonner.setBounds(145, 145, 103, 29);
		getContentPane().add(btnSabonner);
		
		JButton btnConsulter = new JButton("Consulter");
		btnConsulter.setBounds(15, 145, 115, 29);
		getContentPane().add(btnConsulter);

		btnSabonner.addActionListener(new LValiderSuivre(this));
		btnConsulter.addActionListener(new LConsulter(this));
		btnQuitter.addActionListener(new LFermerSuivre (this));
		this.addWindowListener(new LFermerSuivre (this));
	}


	public JTextField getRecherche() {
		return recherche;
	}


	public void setRecherche(JTextField recherche) {
		this.recherche = recherche;
	}


	public String getSelectedItemListRecherche() {
		return listRecherche.getSelectedItem().toString();
	}
}
