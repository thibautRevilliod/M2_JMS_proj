package gui.vues;

import javax.swing.JFrame;

import gui.listeners.LAbonnements;
import gui.listeners.LFermerFildActu;
import gui.listeners.LGazouiller;
import gui.listeners.LParametres;

import java.awt.Dimension;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VueFildActu extends JFrame

{
	public VueFildActu() {
		setTitle("Fil d'actualit\u00E9s");
		setSize(473, 453);
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
		
		
		
		String textAreaContent = "";
		
		for (int i=0; i<gui.main.main.gazouillis.size();i++){
			textAreaContent += gui.main.main.gazouillis.get(i).toString() + "\n"; 
		}
		
		JTextArea area = new JTextArea(textAreaContent,8,12);
		area.setEditable(false);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setBounds(10, 52, 419, 269);
		//JScrollPane spane = new JScrollPane(area);
		getContentPane().add(area);
		
		
		
		JButton btnGazouiller = new JButton("Gazouiller");
		btnGazouiller.setBounds(314, 16, 115, 29);
		getContentPane().add(btnGazouiller);
		
		JButton btnParamtres = new JButton("Parametres");
		btnParamtres.setBounds(159, 350, 115, 29);
		getContentPane().add(btnParamtres);
		
		JButton btnQuitter = new JButton("Se Deconnecter");
		btnQuitter.setBounds(282, 350, 154, 29);
		getContentPane().add(btnQuitter);
		
		JButton btnAbonnements = new JButton("Abonnements");
		btnAbonnements.setBounds(15, 350, 135, 29);
		getContentPane().add(btnAbonnements);
		
		JLabel lblMonPseudo = new JLabel(gui.main.main.profilConnecte.getPSEUDO());
		lblMonPseudo.setBounds(15, 20, 135, 20);
		getContentPane().add(lblMonPseudo);
	
		// Abonnements :
		btnAbonnements.addActionListener(new LAbonnements(this));
		btnGazouiller.addActionListener(new LGazouiller (this)); 
		btnParamtres.addActionListener(new LParametres (this)); 
		btnQuitter.addActionListener(new LFermerFildActu (this));
		this.addWindowListener(new LFermerFildActu (this));
	}
}
