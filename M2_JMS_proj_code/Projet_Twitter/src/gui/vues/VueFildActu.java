package gui.vues;

import javax.swing.JFrame;

import gui.listeners.LAbonnements;
import gui.listeners.LFermerFildActu;
import gui.listeners.LGazouiller;
import gui.listeners.LParametres;
import gui.listeners.LRechercher;
import metier.ProfilType;

import java.awt.Dimension;
import java.awt.ScrollPane;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class VueFildActu extends JFrame
{
	private Java2sAutoComboBox champRecherche;
	private ArrayList<String> profils = new ArrayList<String>();
	public VueFildActu() {
		setTitle("Gazouillons - Fil d'actualit\u00E9s");
		setSize(550, 577);
		setLocation(400, 300);
		getContentPane().setLayout(null);
		
//		ScrollPane scrollPane = new ScrollPane();
//		scrollPane.setBounds(10, 52, 419, 269);
//		getContentPane().add(scrollPane);
		
//		JList<String> list = new JList<String>();
//		//On transforme l'arrayList des profils abonnes en String[] pour l'afficher dans l'interface.
//		String[] listGazouillis = new String[gui.main.main.gazouillis.size()];
//		for (int i=0; i<gui.main.main.gazouillis.size();i++){
//			listGazouillis[i]=gui.main.main.gazouillis.get(i).toString();
//		}
//		list.setListData(listGazouillis);
//		list.setBounds(10, 52, 419, 269);
//		
//		JScrollPane listScroller = new JScrollPane(list);
//		listScroller.setPreferredSize(new Dimension(250, 80));
//		
//		getContentPane().add(list);
		
		profils.add("");
		for (int i=0; i<gui.main.main.tousLesProfils.size();i++)
		{
			ProfilType pT = gui.main.main.tousLesProfils.get(i);
			if ( ! pT.getPSEUDO().equals(gui.main.main.profilConnecte.getPSEUDO()))
			{
				profils.add(pT.getPSEUDO());
			}
		}
		champRecherche = new Java2sAutoComboBox(profils);
        champRecherche.setLocation(212, 19);
        champRecherche.setSize(215, 26);

        champRecherche.getEditor().selectAll();
        champRecherche.setName("someComboBox");
        champRecherche.setDataList(profils);
        getContentPane().add(champRecherche);
		
		String textAreaContent = "";
		
		for (int i=0; i<gui.main.main.gazouillis.size();i++){
			textAreaContent += gui.main.main.gazouillis.get(i).toString() + "\n"; 
		}
		
		JTextArea area = new JTextArea(textAreaContent,8,12);
		area.setEditable(false);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setBounds(10, 108, 503, 352);
		//JScrollPane spane = new JScrollPane(area);
		getContentPane().add(area);
		
		
		
		JButton btnGazouiller = new JButton("Gazouiller");
		btnGazouiller.setBounds(393, 72, 120, 29);
		getContentPane().add(btnGazouiller);
		
		JButton btnParamtres = new JButton("Parametres");
		btnParamtres.setBounds(197, 476, 115, 29);
		getContentPane().add(btnParamtres);
		
		JButton btnQuitter = new JButton("Se Deconnecter");
		btnQuitter.setBounds(354, 476, 154, 29);
		getContentPane().add(btnQuitter);
		
		JButton btnAbonnements = new JButton("Abonnements");
		btnAbonnements.setBounds(20, 476, 135, 29);
		getContentPane().add(btnAbonnements);
		
		JLabel lblMonPseudo = new JLabel(gui.main.main.profilConnecte.getPSEUDO());
		lblMonPseudo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMonPseudo.setBounds(10, 72, 135, 20);
		getContentPane().add(lblMonPseudo);
		
		JLabel lblNewLabel = new JLabel("Rechercher un Gazouilleur");
		lblNewLabel.setBounds(15, 19, 196, 26);
		getContentPane().add(lblNewLabel);
		
		JButton btnRechercher = new JButton("Voir");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRechercher.setBounds(442, 18, 71, 29);
		getContentPane().add(btnRechercher);
	
		// Abonnements :
		btnRechercher.addActionListener(new LRechercher(this));
		btnAbonnements.addActionListener(new LAbonnements(this));
		btnGazouiller.addActionListener(new LGazouiller (this)); 
		btnParamtres.addActionListener(new LParametres (this)); 
		btnQuitter.addActionListener(new LFermerFildActu (this));
		this.addWindowListener(new LFermerFildActu (this));
	}
	public String getSelectedItemListRecherche() {
		return champRecherche.getSelectedItem().toString();
	}
}
