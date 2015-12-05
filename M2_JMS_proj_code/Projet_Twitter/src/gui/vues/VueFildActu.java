package gui.vues;

import javax.swing.JFrame;

import gui.listeners.LAbonnements;
import gui.listeners.LFermerFildActu;
import gui.listeners.LGazouiller;
import gui.listeners.LMonProfil;
import gui.listeners.LParametres;
import gui.listeners.LRafraichir;
import gui.listeners.LRechercher;
import metier.ProfilType;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class VueFildActu extends JFrame
{
	private JTextArea area;
	private JScrollPane scroll;
	private Timer timer;
	private LRafraichir timerAction;
	private Java2sAutoComboBox champRecherche;
	private ArrayList<String> profils = new ArrayList<String>();
	
	public VueFildActu() {
		setTitle("Fil d'actualit\u00E9s");
		setLocation(400, 300);
		
		JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(5, 5));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 1, 5, 5));
        
        profils.add("");
		for (int i=0; i<gui.main.main.tousLesProfils.size();i++)
		{
			ProfilType pT = gui.main.main.tousLesProfils.get(i);
			if ( ! pT.getPSEUDO().toUpperCase().equals(gui.main.main.profilConnecte.getPSEUDO().toUpperCase()))
			{
				profils.add(pT.getPSEUDO());
			}
		}
        
        String textAreaContent = "";
		
		for (int i=0; i<gui.main.main.gazouillis.size();i++){
			textAreaContent += gui.main.main.gazouillis.get(i).toString() + "\n"; 
			textAreaContent +="--\n";
		}
        
        area = new JTextArea(10, 10);
        area.setFont(new Font("Monospaced", Font.PLAIN, 18));
        area.setEditable(false);
        area.setWrapStyleWord(true);
        area.setLineWrap(true);    
        area.setText(textAreaContent);

        scroll = new JScrollPane();        
        scroll.setBorder(
            BorderFactory.createTitledBorder("Messages"));
        scroll.setViewportView(area);
        
        centerPanel.add(scroll);
        
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        
		timerAction = new LRafraichir (this);
		
		JPanel panelInterne = new JPanel();
		panelInterne.setLayout(new BorderLayout(3, 3));
		
		JPanel panelRecherche = new JPanel();		
		
		JLabel lblNewLabel = new JLabel("Rechercher un Gazouilleur");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelRecherche.add(lblNewLabel);
		
		champRecherche = new Java2sAutoComboBox(profils);
		champRecherche.setFont(new Font("Tahoma", Font.PLAIN, 18));
        champRecherche.getEditor().selectAll();
        champRecherche.setName("someComboBox");
        champRecherche.setDataList(profils);
        panelRecherche.add(champRecherche);
		
		JButton btnRechercher = new JButton("Voir");
		btnRechercher.setFont(new Font("Tahoma", Font.PLAIN, 18));		
		panelRecherche.add(btnRechercher);
		
		panelInterne.add(panelRecherche,BorderLayout.NORTH);
		
				
		JPanel panelGazouiller = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelGazouiller.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
								
		JButton btnGazouiller = new JButton("Gazouiller");
		btnGazouiller.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelGazouiller.add(btnGazouiller, BorderLayout.LINE_END);
		
		panelInterne.add(panelGazouiller);
		
		JPanel panelProfil = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelProfil.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);		
		
		JLabel lblMonPseudo = new JLabel(gui.main.main.profilConnecte.getPSEUDO());
		lblMonPseudo.setFont(new Font("Tahoma", Font.BOLD, 24));
		panelProfil.add(lblMonPseudo);
		panelInterne.add(panelProfil,BorderLayout.LINE_START);
		
		JPanel monProfil = new JPanel();

		FlowLayout flowLayout2 = (FlowLayout) monProfil.getLayout();
		flowLayout2.setAlignment(FlowLayout.LEFT);
		flowLayout.setAlignment(FlowLayout.LEFT);		
		
		JButton btnMonProfil = new JButton("Mon Profil");
		btnMonProfil.setHorizontalAlignment(SwingConstants.LEFT);
		btnMonProfil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		monProfil.add(btnMonProfil);
		panelInterne.add(monProfil, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_1 = new JLabel("");
		monProfil.add(lblNewLabel_1);
				
		topPanel.add(panelInterne);
	
		JButton btnParamtres = new JButton("Parametres");
		btnParamtres.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bottomPanel.add(btnParamtres);

		JButton btnAbonnements = new JButton("Abonnements");
		btnAbonnements.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bottomPanel.add(btnAbonnements);
		
		JButton btnQuitter = new JButton("Se Deconnecter");
		btnQuitter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bottomPanel.add(btnQuitter);
		
		
		contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.PAGE_END);
        contentPane.add(topPanel, BorderLayout.PAGE_START);
        
        setContentPane(contentPane);
        pack();
        setLocationByPlatform(true);
        setVisible(true);
        
        timer = new Timer(1000, timerAction);
        timer.start();
		btnAbonnements.addActionListener(new LAbonnements(this));
		btnMonProfil.addActionListener(new LMonProfil(this));
		btnRechercher.addActionListener(new LRechercher(this));
		btnGazouiller.addActionListener(new LGazouiller (this));
		btnParamtres.addActionListener(new LParametres (this)); 
		btnQuitter.addActionListener(new LFermerFildActu (this));
		this.addWindowListener(new LFermerFildActu (this));
	}

	public JTextArea getArea() {
		return area;
	}

	public void setArea(JTextArea area) {
		this.area = area;
	}
	
	public String getSelectedItemListRecherche() {
		return champRecherche.getSelectedItem().toString();
	}

}
