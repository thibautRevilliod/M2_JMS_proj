package gui.vues;

import javax.swing.JFrame;

import gui.listeners.LFermerConsulter;
import metier.ProfilAConsulter;
import java.awt.ScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;

public class VueConsulter extends JFrame
{

	public VueConsulter(ProfilAConsulter profil) {
		setTitle("Consulter un profil");
		setSize(553, 580);
		setLocation(400, 300);
		getContentPane().setLayout(null);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(10, 154, 506, 314);
		getContentPane().add(scrollPane);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(401, 479, 115, 29);
		getContentPane().add(btnQuitter);
		
		JLabel lblMonPseudo = new JLabel(profil.getPSEUDO());
		lblMonPseudo.setBounds(205, 16, 135, 20);
		getContentPane().add(lblMonPseudo);
		
		JLabel lblNombreDeGazouillis = new JLabel("Nombre de gazouillis :");
		lblNombreDeGazouillis.setBounds(25, 56, 171, 20);
		getContentPane().add(lblNombreDeGazouillis);
		
		JLabel lblSuiveurs = new JLabel("Nombre de suiveurs : ");
		lblSuiveurs.setBounds(25, 86, 171, 20);
		getContentPane().add(lblSuiveurs);
		
		JLabel lblNombreDeSuivis = new JLabel("Nombre de suivis :");
		lblNombreDeSuivis.setBounds(25, 113, 171, 20);
		getContentPane().add(lblNombreDeSuivis);
		
		JLabel lblNbgaz = new JLabel(String.valueOf(profil.getNbGazouillis()));
		lblNbgaz.setBounds(205, 56, 69, 20);
		getContentPane().add(lblNbgaz);
		
		JLabel lblNbSuiveurs = new JLabel(String.valueOf(profil.getNbSuiveurs()));
		lblNbSuiveurs.setBounds(205, 86, 115, 20);
		getContentPane().add(lblNbSuiveurs);
		
		JLabel lblNbSuivis = new JLabel(String.valueOf(profil.getNbSuivis()));
		lblNbSuivis.setBounds(205, 113, 115, 20);
		getContentPane().add(lblNbSuivis);
		btnQuitter.addActionListener(new LFermerConsulter (this));
		this.addWindowListener(new LFermerConsulter (this));
	}
}
