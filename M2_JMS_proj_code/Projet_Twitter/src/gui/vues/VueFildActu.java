package gui.vues;

import javax.swing.JFrame;

import gui.listeners.LAbonnements;
import gui.listeners.LFermerFildActu;
import gui.listeners.LGazouiller;
import gui.listeners.LParametres;
import gui.listeners.LRafraichir;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class VueFildActu extends JFrame
{
	private JTextArea area;
	private JScrollPane scroll;
	private Timer timer;
	private LRafraichir timerAction;
	
	public VueFildActu() {
		setTitle("Fil d'actualit\u00E9s");
//		setSize(473, 453);
//		setLocation(400, 300);
//		getContentPane().setLayout(null);
		
		JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(5, 5));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 1, 5, 5));
        
        String textAreaContent = "";
		
		for (int i=0; i<gui.main.main.gazouillis.size();i++){
			textAreaContent += gui.main.main.gazouillis.get(i).toString() + "\n"; 
		}
        
        area = new JTextArea(10, 10);
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
        
//        JButton btnRafraichir = new JButton("Rafraichir");
		timerAction = new LRafraichir (this);
		
		
		
		JLabel lblMonPseudo = new JLabel(gui.main.main.profilConnecte.getPSEUDO());
		topPanel.add(lblMonPseudo);
		
		JButton btnGazouiller = new JButton("Gazouiller");
		topPanel.add(btnGazouiller);
		
		JButton btnParamtres = new JButton("Parametres");
		bottomPanel.add(btnParamtres);

		JButton btnAbonnements = new JButton("Abonnements");
		bottomPanel.add(btnAbonnements);
		
		JButton btnQuitter = new JButton("Se Deconnecter");
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
		
		
		
//		String textAreaContent = "";
//		
//		for (int i=0; i<gui.main.main.gazouillis.size();i++){
//			textAreaContent += gui.main.main.gazouillis.get(i).toString() + "\n"; 
//		}
//		
//		area = new JTextArea(textAreaContent,8,12);
//		area.setEditable(false);
//		area.setLineWrap(true);
//		area.setWrapStyleWord(true);
//		area.setBounds(10, 52, 419, 269);
//		//JScrollPane spane = new JScrollPane(area);
//		
//		JScrollPane scroll = new JScrollPane (area, 
//				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		
//		getContentPane().add(area);
//		
//		
//		
//		JButton btnGazouiller = new JButton("Gazouiller");
//		btnGazouiller.setBounds(314, 16, 115, 29);
//		getContentPane().add(btnGazouiller);
//		
//		JButton btnRafraichir = new JButton("Rafraichir");
//		btnRafraichir.setBounds(159, 16, 115, 29);
//		getContentPane().add(btnRafraichir);
//		
//		JButton btnParamtres = new JButton("Parametres");
//		btnParamtres.setBounds(159, 350, 115, 29);
//		getContentPane().add(btnParamtres);
//		
//		JButton btnQuitter = new JButton("Se Deconnecter");
//		btnQuitter.setBounds(282, 350, 154, 29);
//		getContentPane().add(btnQuitter);
//		
//		JButton btnAbonnements = new JButton("Abonnements");
//		btnAbonnements.setBounds(15, 350, 135, 29);
//		getContentPane().add(btnAbonnements);
//		
//		JLabel lblMonPseudo = new JLabel(gui.main.main.profilConnecte.getPSEUDO());
//		lblMonPseudo.setBounds(15, 20, 135, 20);
//		getContentPane().add(lblMonPseudo);
//	
//		// Abonnements :
		btnAbonnements.addActionListener(new LAbonnements(this));
		btnGazouiller.addActionListener(new LGazouiller (this));
//		btnRafraichir.addActionListener(new LRafraichir (this));
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
	
	
	
}
