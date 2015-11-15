package gui.main;
import gui.vues.*;

import java.*;

import bdd.JmsJDBC; 

public class main {
	

	public static void main(String[] args) 
	{
		
		VueConnexion vc;
		VueInscription vi;
		VueMenuDepart vd;
		VueFildActu vf;
		
	
//		// Pour ouvrir la connexion 
//		
//		vc = new VueConnexion ();
//		vc.setVisible (true);
//		vc.setLocation(800, 300);
//		
//		// Pour ouvrir l'inscription 
//		
//		vi = new VueInscription ();
//		vi.setVisible (true);
//		vi.setLocation(800, 300);
		
		// pour ouvrir le menu 		
		
		vd = new VueMenuDepart();
		vd.setVisible(true);
		vd.setLocation(850, 300);
		
		// provisoire : pour ouvrir le fil d'actualité : A LIER UNE FOIS LA CONNEXION ETABLIE
		
		vf = new VueFildActu();
		vf.setVisible(true);
		
		
	}

}
