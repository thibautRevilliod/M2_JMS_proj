package gui.vues;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import gui.listeners.LFermerSuivre;
import gui.listeners.LValiderSuivre;
import metier.ProfilType;

public class VueSuivre extends JFrame{
		
	private JLabel lblNewLabel;
	private Java2sAutoComboBox champRecherche;
	private ArrayList<String> profils = new ArrayList<String>();

	public VueSuivre ()
	{
		
		this.setTitle("S'abonner");
		this.setLocationRelativeTo(null);
		this.setSize(412,196);
		this.setResizable(false);
		this.setLocation(800, 300);
	
		this.addWindowListener(new LFermerSuivre (this));
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Pseudo :");
		lblNewLabel.setBounds(35, 44, 206, 20);
		getContentPane().add(lblNewLabel);
		
		JButton btnQuitter = new JButton("Annuler");
		btnQuitter.setBounds(231, 106, 115, 29);
		getContentPane().add(btnQuitter);
		
		JButton btnSabonner = new JButton("S'abonner");
		btnSabonner.setBounds(63, 106, 103, 29);
		getContentPane().add(btnSabonner);
		
		//On récupère le pseudo de tous les profils et on enlève : ceux auxquels on est abonné et l'utilisateur courant
		profils.add("");
		for (int i=0; i<gui.main.main.tousLesProfils.size();i++)
		{
			ProfilType pT = gui.main.main.tousLesProfils.get(i);
			if ( ! pT.getPSEUDO().toUpperCase().equals(gui.main.main.profilConnecte.getPSEUDO().toUpperCase()))
			{
				if(! gui.main.main.profilsSuivis.contains(pT.getPSEUDO())){
					profils.add(pT.getPSEUDO());
				}
			}
			
		}
		champRecherche = new Java2sAutoComboBox(profils);
        champRecherche.setLocation(145, 41);
        champRecherche.setSize(215, 26);

        champRecherche.getEditor().selectAll();
        champRecherche.setName("someComboBox");
        champRecherche.setDataList(profils);
        getContentPane().add(champRecherche);
       
		btnSabonner.addActionListener(new LValiderSuivre(this));
		btnQuitter.addActionListener(new LFermerSuivre (this));
	}

	public String getSelectedItemListRecherche() {
		return champRecherche.getSelectedItem().toString();
	}
}
