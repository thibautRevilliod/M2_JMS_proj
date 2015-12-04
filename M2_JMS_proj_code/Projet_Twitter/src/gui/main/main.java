package gui.main;
import gui.vues.*;
import metier.MessageGazouilli;
import metier.ProfilType;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class main {
	

	public static ProfilType profilConnecte = new ProfilType();
	public static ArrayList<String> profilsAbonnes;
	public static ArrayList<String> profilsSuivis;
	public static ArrayList<ProfilType> tousLesProfils;
	public static ArrayList<MessageGazouilli> gazouillis;
	public static ArrayList<MessageGazouilli> gazouillisSession;


	
	public static void main(String[] args) 
	{
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		VueMenuDepart vd;		
	
		// pour ouvrir le menu 		
		
		vd = new VueMenuDepart();
		vd.setVisible(true);
		vd.setLocation(850, 300);
		
		
		
	}

}
