package gui.vues;

import javax.swing.JFrame;

import gui.listeners.LFermerConsulter;
import jms.SenderTwitter;
import metier.MessageGazouilli;
import metier.ProfilAConsulter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dialog;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VueConsulter extends JDialog
{
	private JTextArea area;
	private JScrollPane scroll;
	private ProfilAConsulter pAC;
	private ArrayList<MessageGazouilli> listeMessage;
	
	public VueConsulter(JFrame jf, ProfilAConsulter profil) {
		super(jf,Dialog.ModalityType.DOCUMENT_MODAL);
		this.pAC=profil;
		setTitle("Consulter un profil");
		setSize(553, 580);
		setLocation(400, 300);
		getContentPane().setLayout(null);
		this.setResizable(false);
		String textAreaContent = "";
		
		try{
			SenderTwitter.listeGazouilliDunAbonnements(pAC.getPSEUDO());
			String message = SenderTwitter.getMessageRetour();
			listeMessage = SenderTwitter.getListeGazouilliDunAbonnement();
			if(null != listeMessage){
				for (int i=0; i<listeMessage.size();i++){
					MessageGazouilli tmp = listeMessage.get(i);
					textAreaContent += tmp.toString() + "\n"; 
					textAreaContent +="--\n";
				}
			}
		}catch(Exception e){
			
		}
		
		area = new JTextArea(10, 10);
		area.setFont(new Font("Monospaced", Font.PLAIN, 18));
		area.setEditable(false);
        area.setWrapStyleWord(true);
        area.setLineWrap(true);    
        area.setText(textAreaContent);


        scroll = new JScrollPane();        
        scroll.setBorder(
            BorderFactory.createTitledBorder("Messages écrits par "+this.pAC.getPSEUDO()));
        scroll.setViewportView(area);
        scroll.setBounds(10, 154, 506, 314);
		
		getContentPane().add(scroll);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnQuitter.setBounds(401, 479, 115, 29);
		getContentPane().add(btnQuitter);
		
		JLabel lblMonPseudo = new JLabel(profil.getPSEUDO());
		lblMonPseudo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonPseudo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblMonPseudo.setBounds(169, 16, 213, 20);
		getContentPane().add(lblMonPseudo);
		
		JLabel lblNombreDeGazouillis = new JLabel("Nombre de gazouillis :");
		lblNombreDeGazouillis.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombreDeGazouillis.setBounds(25, 56, 205, 20);
		getContentPane().add(lblNombreDeGazouillis);
		
		JLabel lblSuiveurs = new JLabel("Nombre de suiveurs : ");
		lblSuiveurs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSuiveurs.setBounds(25, 86, 205, 20);
		getContentPane().add(lblSuiveurs);
		
		JLabel lblNombreDeSuivis = new JLabel("Nombre de suivis :");
		lblNombreDeSuivis.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombreDeSuivis.setBounds(25, 113, 205, 20);
		getContentPane().add(lblNombreDeSuivis);
		
		JLabel lblNbgaz = new JLabel(String.valueOf(profil.getNbGazouillis()));
		lblNbgaz.setBounds(220, 56, 113, 20);
		getContentPane().add(lblNbgaz);
		
		JLabel lblNbSuiveurs = new JLabel(String.valueOf(profil.getNbSuiveurs()));
		lblNbSuiveurs.setBounds(220, 86, 113, 20);
		getContentPane().add(lblNbSuiveurs);
		
		JLabel lblNbSuivis = new JLabel(String.valueOf(profil.getNbSuivis()));
		lblNbSuivis.setBounds(220, 113, 113, 20);
		getContentPane().add(lblNbSuivis);
		btnQuitter.addActionListener(new LFermerConsulter (this));
		
	}
	public ArrayList<MessageGazouilli> getListeMessage() {
		return listeMessage;
	}
	public void setListeMessage(ArrayList<MessageGazouilli> listeMessage) {
		this.listeMessage = listeMessage;
	}
	public JTextArea getArea() {
		return area;
	}
	public void setArea(JTextArea area) {
		this.area = area;
	}
}
