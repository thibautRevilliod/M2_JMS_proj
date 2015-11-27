package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import metier.MessageGazouilli;
import metier.ProfilType;
import jms.SenderTwitter;


public class JmsJDBC {
		
	Connection conn;
	
	public JmsJDBC(String nomBD) {
		try {
		    Class.forName("org.h2.Driver");
		    conn = DriverManager.getConnection("jdbc:h2:"+nomBD+";IGNORECASE=TRUE", "sa", "");
		    // on cree un objet Statement qui va permettre l'execution des requetes
	        Statement s = conn.createStatement();
			
        	s.execute("create table IF NOT EXISTS PROFIL  ( " +
        			" idPROFIL INT NOT NULL PRIMARY KEY, " +
        			" PSEUDO VARCHAR( 500 ) UNIQUE, " +
        			" MDP VARCHAR( 500 ) , " +
        			" NOM VARCHAR( 500 ) , " +
					" PRENOM VARCHAR( 500 ) , " +
					" VILLE VARCHAR( 500 ) )");

        	s.execute("create table IF NOT EXISTS GAZOUILLI  ( " +
        			" idGAZOUILLI INT NOT NULL PRIMARY KEY, " +
        			" CONTENU VARCHAR( 500 ) , " +
					" DATEHEURE TIMESTAMP , " +
        			" VILLE VARCHAR( 500 ) , " +
					" EMETTEUR INT , " +
					" GEOLOCALISATION BOOLEAN , " +
					" FOREIGN KEY (EMETTEUR) REFERENCES PROFIL(idPROFIL))");
			
//        	s.execute("create table IF NOT EXISTS ABONNEMENTS  ( " +
//        			" idProfil1MENTS INT NOT NULL PRIMARY KEY, " +
//					" DATEHEURE TIMESTAMP )");
	        
        	s.execute("create table IF NOT EXISTS ABONNEMENTS  ( " +
        			" idProfilSuiviPar1 INT NOT NULL, " +
					" idProfil1 INT NOT NULL, " +
					" DATEHEURE TIMESTAMP, " +
					" PRIMARY KEY (idProfilSuiviPar1, idProfil1) , " +
					" FOREIGN KEY (idProfilSuiviPar1) REFERENCES PROFIL(idPROFIL) , " +
					" FOREIGN KEY (idProfil1) REFERENCES PROFIL(idPROFIL))");
	        
//        	s.execute("create table IF NOT EXISTS SUIVI  ( " +
//        			" idProfilSuiviPar1 INT NOT NULL, " +
//					" idPROFIL INT NOT NULL, " +
//					" DATEHEURE TIMESTAMP, " +
//					" PRIMARY KEY (idProfilSuiviPar1, idPROFIL) , " +
//					" FOREIGN KEY (idProfilSuiviPar1) REFERENCES PROFIL(idPROFIL) , " +
//					" FOREIGN KEY (idPROFIL) REFERENCES PROFIL(idPROFIL))");
			
		} catch(Exception e) {
			// il y a eu une erreur
			e.printStackTrace();
		}
	}
	
	public static boolean clearBDD(String nomBD) {
		boolean res = true;
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:"+nomBD+";IGNORECASE=TRUE", "sa", "");
			Statement s = conn.createStatement();
			ResultSet rs = conn.getMetaData().getTables(null, null, "GAZOUILLI", null);
	        if (rs.next()) {
	        	// la table existe
	        	s.execute("drop table GAZOUILLI");
	        	res = true;
	        } else {	
	        	res = false;
	        }
	        
	        rs = conn.getMetaData().getTables(null, null, "ABONNEMENTS", null);
	        if (rs.next()) {
	        	// la table existe
	        	s.execute("drop table ABONNEMENTS");
	        	res = true;
	        } else {	
	        	res = false;
	        }
	        
//	        rs = conn.getMetaData().getTables(null, null, "SUIVI", null);
//	        if (rs.next()) {
//	        	// la table existe
//	        	s.execute("drop table SUIVI");
//	        	res = true;
//	        } else {	
//	        	res = false;
//	        }
	        
	        rs = conn.getMetaData().getTables(null, null, "PROFIL", null);
	        if (rs.next()) {
	        	// la table existe
	        	s.execute("drop table PROFIL");
	        	res = true;
	        } else {	
	        	res = false;
	        }
	        
//	        rs = conn.getMetaData().getTables(null, null, "ABONNEMENTS", null);
//	        if (rs.next()) {
//	        	// la table existe
//	        	s.execute("drop table ABONNEMENTS");
//	        	res = true;
//	        } else {	
//	        	res = false;
//	        }
	        
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = false;
		}
		
		return res;
	}
	
	public int creerProfil(String ppseudo, String pmdp, String pnom, String pprenom, String pville) {
		int id = -1;
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("select idPROFIL from PROFIL WHERE PSEUDO = '"+ppseudo+"'");
			if (!rs.next())
			{
				//récupère le dernier ID
				rs = s.executeQuery("select MAX(idPROFIL) from PROFIL");
	        	if (rs.next())
	        	{
	        		id = rs.getInt(1)+1;
		        	s.executeUpdate("insert into PROFIL (idPROFIL,PSEUDO,MDP,NOM,PRENOM,VILLE) values ("+id+", '"+ppseudo+"', '"+pmdp+"', '"+pnom+"','"+pprenom+"','"+pville+"')");
		        } 
				else
		        {
					id = 1;
		        	s.executeUpdate("insert into PROFIL (idPROFIL,PSEUDO,MDP,NOM,PRENOM,VILLE) values ('1', '"+ppseudo+"', '"+pmdp+"', '"+pnom+"','"+pprenom+"','"+pville+"')");
		        }
			}
			else
			{
				id = -2; // le cas ou le pseudo existe déjà
			}
	        return id;
		} catch (SQLException e) {
			e.printStackTrace();
			id = -1;
			return id;
		}
		
		
	}
	
	// si OK, retourne la ville de l'utilisateur, sinon retourne null
	public String verificationIDMDP(String ppseudo, String pmdp) {
		String res = null;
		try {
			Statement s = conn.createStatement();
        	//vérification du mdp et du pseudo
			ResultSet rs = s.executeQuery("SELECT ville FROM PROFIL WHERE PSEUDO = '"+ppseudo+"' AND MDP = '"+pmdp+"'");
        	if (rs.next())
        	{
        		res = rs.getString(1);
    		} 
	        
	        return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
	}
	
	// mise à jour de la BD pour le profil déconnecté
	public int deconnexionProfil(String ppseudo) {
		int id = -1;
		try {
			Statement s = conn.createStatement();
        	//vérification du mdp et du pseudo
			ResultSet rs = s.executeQuery("SELECT IDPROFIL FROM PROFIL WHERE PSEUDO = '"+ppseudo+"'");
        	if (rs.next())
        	{
        		id = rs.getInt(1);
        		//mise à jour de la colonne connecte
        		s.executeUpdate("UPDATE PROFIL SET CONNECTE = null WHERE PSEUDO = '"+ppseudo+"'");
    		} 
			else
	        {
				id = -1;
	        }
	        
	        return id;
		} catch (SQLException e) {
			e.printStackTrace();
			id = -1;
			return id;
		}
	}
	
	// mise à jour dans la BD des champs pour le profil connecté
	public int miseAJourProfil(String ppseudo, String pmdp, String pnom, String pprenom, String pville) {
		int id = -1;
		try {
			Statement s = conn.createStatement();
        	//vérification du pseudo
			ResultSet rs = s.executeQuery("SELECT IDPROFIL FROM PROFIL WHERE PSEUDO = '"+ppseudo+"'");
        	if (rs.next())
        	{
        		id = rs.getInt(1);
        		//mise à jour de tous les champs concernant le profil
        		s.executeUpdate("UPDATE PROFIL SET MDP = '" + pmdp +
    			"',NOM = '" + pnom +
				"',PRENOM = '" + pprenom +
				"',VILLE = '" + pville +
				"' WHERE PSEUDO = '"+ppseudo+"'");
    		} 
			else
	        {
				id = -1;
	        }
	        
	        return id;
		} catch (SQLException e) {
			e.printStackTrace();
			id = -1;
			return id;
		}
	}
	
	// si OK, retourne les informations du profil, sinon retourne null
//	public ProfilType informationProfil(String pid) {
//		ProfilType profilType = new ProfilType();
//		try {
//			Statement s = conn.createStatement();
//        	//récupère l'ID de l'utilisateur
//			ResultSet rs = s.executeQuery("SELECT PSEUDO, NOM, PRENOM, VILLE FROM PROFIL WHERE IDPROFIL = '"+pid+"'");
//        	if (rs.next())
//        	{
//        		profilType.setPSEUDO(rs.getString(1));
//        		profilType.setNOM(rs.getString(2));
//        		profilType.setPRENOM(rs.getString(3));
//        		profilType.setVILLE(rs.getString(4));
//	        } 
//			else
//	        {
//				profilType = null;
//	        }
//	        
//	        return profilType;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			profilType = null;
//			return profilType;
//		}
//	}
	
	// si OK, retourne les informations du profil, sinon retourne null
		public ProfilType informationProfil(String pseudo) {
			ProfilType profilType = new ProfilType();
			try {
				Statement s = conn.createStatement();
	        	//récupère l'ID de l'utilisateur
				ResultSet rs = s.executeQuery("SELECT PSEUDO, MDP, NOM, PRENOM, VILLE FROM PROFIL WHERE PSEUDO = '"+pseudo+"'");
	        	if (rs.next())
	        	{
	        		profilType.setPSEUDO(rs.getString(1));
	        		profilType.setMDP(rs.getString(2));
	        		profilType.setNOM(rs.getString(3));
	        		profilType.setPRENOM(rs.getString(4));
	        		profilType.setVILLE(rs.getString(5));
		        } 
				else
		        {
					profilType = null;
		        }
		        
		        return profilType;
			} catch (SQLException e) {
				e.printStackTrace();
				profilType = null;
				return profilType;
			}
		}
	
	//retourne la ville du Gazouilli ou null
	public String creerGazouilli(String pcontenu, String pPseudoEmetteur, Timestamp ptime, boolean pGeolocalisation) {
		String res = null;
		int id = 0;
		try {
			Statement s = conn.createStatement();
        	//récupère le dernier ID
			ResultSet rs = s.executeQuery("select MAX(idGAZOUILLI) from GAZOUILLI");
        	if (rs.next())
        	{
        		id = rs.getInt(1)+1;
        		s.executeUpdate("insert into GAZOUILLI values ('"+id+"', '"+pcontenu+"','"+ptime+"',(SELECT VILLE FROM PROFIL WHERE PSEUDO = '"+pPseudoEmetteur+"'),(SELECT idProfil FROM PROFIL WHERE pseudo = '"+pPseudoEmetteur+"'), '"+ pGeolocalisation +"')");
        	}
        	else
        	{
        		id = 1;
        		s.executeUpdate("insert into GAZOUILLI values ('1', '"+pcontenu+"','"+ptime+"',(SELECT VILLE FROM PROFIL WHERE PSEUDO = '"+pPseudoEmetteur+"'),(SELECT idProfil FROM PROFIL WHERE pseudo = '"+pPseudoEmetteur+"'), '"+ pGeolocalisation +"')");
        	}
        	
        	rs = s.executeQuery("select VILLE from GAZOUILLI WHERE idGAZOUILLI = '"+id+"'");
        	if (rs.next())
        	{
        		res = rs.getString(1);
        	}
        	
        	return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
	}
	
	public int creerAbonnement(String pPseudoIdProfilSuiviPar1, String pPseudoIdProfil1) {
		int id = -1;
		try {
			Statement s = conn.createStatement();
        	//récupère le dernier ID
			ResultSet rs = s.executeQuery("select idProfilSuiviPar1, idProfil1 from ABONNEMENTS WHERE idProfilSuiviPar1 = (SELECT idProfil FROM PROFIL WHERE pseudo = '"+pPseudoIdProfilSuiviPar1+"') AND idProfil1 = (SELECT idProfil FROM PROFIL WHERE pseudo = '"+pPseudoIdProfil1+"')");
        	if (!rs.next())
        	{
        		s.executeUpdate("insert into ABONNEMENTS values ((SELECT idProfil FROM PROFIL WHERE pseudo = '"+pPseudoIdProfilSuiviPar1+"'), (SELECT idProfil FROM PROFIL WHERE pseudo = '"+pPseudoIdProfil1+"'),CURRENT_TIMESTAMP())");
        		id = 0;
        	}
        	else
        	{
        		// Erreur : l'abonnement existe déjà
        		id = -2;
        	}
        	return id;
		} catch (SQLException e) {
			e.printStackTrace();
			id = -1;
			return id;
		}
	}
	
	public int supprimerAbonnement(String pPseudoIdProfilSuiviPar1, String pPseudoIdProfil1) {
		int id = -1;
		try {
			Statement s = conn.createStatement();
        	//récupère le dernier ID
			ResultSet rs = s.executeQuery("select idProfilSuiviPar1, idProfil1 from ABONNEMENTS WHERE idProfilSuiviPar1 = (SELECT idProfil FROM PROFIL WHERE pseudo = '"+pPseudoIdProfilSuiviPar1+"') AND idProfil1 = (SELECT idProfil FROM PROFIL WHERE pseudo = '"+pPseudoIdProfil1+"')");
        	if (rs.next())
        	{
        		s.executeUpdate("DELETE FROM ABONNEMENTS WHERE idProfilSuiviPar1 = (SELECT idProfil FROM PROFIL WHERE pseudo = '"+pPseudoIdProfilSuiviPar1+"') AND idProfil1 = (SELECT idProfil FROM PROFIL WHERE pseudo = '"+pPseudoIdProfil1+"')");
        		id = 0;
        	}
        	else
        	{
        		// Erreur : l'abonnement n'existe pas
        		id = -2;
        	}
        	return id;
		} catch (SQLException e) {
			e.printStackTrace();
			id = -1;
			return id;
		}
	}
	
	//liste des abonnements du profil passé en paramètre
	public ArrayList<String> listeAbonne(String pIdProfil1) {
		ArrayList<String> res = new ArrayList<String>();
		
		try {
			Statement s = conn.createStatement();
        	//récupère le dernier ID
			ResultSet rs = s.executeQuery("select PROFIL.PSEUDO from ABONNEMENTS, PROFIL WHERE ABONNEMENTS.idProfil1 = PROFIL.idProfil AND idProfilSuiviPar1 = (SELECT idProfil FROM PROFIL WHERE pseudo = '"+pIdProfil1+"')");

    		while(rs.next())
    		{
    			res.add(rs.getString(1));
    		}
    		
        	return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
	}
	
	//liste des suivis du profil passé en paramètre
	public ArrayList<String> listeSuivi(String pPseudoIdProfilSuiviPar1) {
		ArrayList<String> res = new ArrayList<String>();
		
		try {
			Statement s = conn.createStatement();
        	//récupère le dernier ID
			ResultSet rs = s.executeQuery("select PROFIL.PSEUDO from ABONNEMENTS, PROFIL WHERE ABONNEMENTS.idProfilSuiviPar1 = PROFIL.idProfil AND idProfil1 = (SELECT idProfil FROM PROFIL WHERE pseudo = '"+pPseudoIdProfilSuiviPar1+"')");
			
    		while(rs.next())
    		{
    			res.add(rs.getString(1));
    		}
    		
        	return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
	}
	
	//liste des gazouilli du profil passé en paramètre
	public ArrayList<MessageGazouilli> listeGazouilli(String pPseudo) {
		ArrayList<MessageGazouilli> res = new ArrayList<MessageGazouilli>();
		MessageGazouilli gazouilli;
		
		try {
			Statement s = conn.createStatement();
        	//récupère le dernier ID
			ResultSet rs = s.executeQuery("select GAZOUILLI.CONTENU, GAZOUILLI.VILLE, (SELECT PSEUDO FROM profil WHERE  idprofil = GAZOUILLI.emetteur), GAZOUILLI.DATEHEURE, GAZOUILLI.GEOLOCALISATION "
					+ "FROM GAZOUILLI, PROFIL "
					+ "WHERE GAZOUILLI.EMETTEUR= PROFIL.idProfil AND GAZOUILLI.EMETTEUR= (SELECT idProfil FROM PROFIL WHERE pseudo = '"+pPseudo+"')");

    		while(rs.next())
    		{
    			gazouilli = new MessageGazouilli(rs.getString(1),rs.getString(2),rs.getString(3), rs.getTimestamp(4), rs.getBoolean(5), "");
    			res.add(gazouilli);
    		}
    		
        	return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
	}
	
	//nombre de gazouilli du profil passé en paramètre
	public int nbGazouilliPourUnProfil(String pPseudo) {
		int id = -1;
		
		try {
			Statement s = conn.createStatement();
        	//récupère le dernier ID
			ResultSet rs = s.executeQuery("select COUNT(*) "
					+ "FROM GAZOUILLI, PROFIL "
					+ "WHERE GAZOUILLI.EMETTEUR= PROFIL.idProfil AND GAZOUILLI.EMETTEUR= (SELECT idProfil FROM PROFIL WHERE pseudo = '"+pPseudo+"')");

			if (rs.next())
        	{
				id = rs.getInt(1);
        	}
    		
        	return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return id;
		}
	}
	
	//liste des gazouilli des abonnements du profil passé en paramètre
	public ArrayList<MessageGazouilli> listeGazouilliAbonnements(String pPseudoIdProfil1) {
		ArrayList<MessageGazouilli> res = new ArrayList<MessageGazouilli>();
		MessageGazouilli gazouilli;
		
		try {
			Statement s = conn.createStatement();
        	//récupère les gazouillis sans geolocalisation
			ResultSet rs = s.executeQuery("SELECT GAZOUILLI.CONTENU, GAZOUILLI.VILLE, (SELECT PSEUDO FROM profil WHERE idprofil = GAZOUILLI.emetteur), GAZOUILLI.DATEHEURE, GAZOUILLI.GEOLOCALISATION "
					+ " FROM GAZOUILLI, ABONNEMENTS "
					+ " WHERE GAZOUILLI.EMETTEUR = ABONNEMENTS.IDPROFILSUIVIPAR1 "
					+ " AND ABONNEMENTS.IDPROFIL1 = (SELECT IDPROFIL FROM PROFIL WHERE PSEUDO = '"+pPseudoIdProfil1+"') "
					+ " AND GAZOUILLI.GEOLOCALISATION = 'FALSE'");

    		while(rs.next())
    		{
    			gazouilli = new MessageGazouilli(rs.getString(1),rs.getString(2),rs.getString(3), rs.getTimestamp(4), rs.getBoolean(5), "");
    			res.add(gazouilli);
    		}
    		
    		rs = s.executeQuery("SELECT GAZOUILLI.CONTENU, GAZOUILLI.VILLE, (SELECT PSEUDO FROM profil WHERE idprofil = GAZOUILLI.emetteur), GAZOUILLI.DATEHEURE, GAZOUILLI.GEOLOCALISATION "
    				+ " FROM GAZOUILLI, ABONNEMENTS "
    				+ " WHERE GAZOUILLI.EMETTEUR = ABONNEMENTS.IDPROFILSUIVIPAR1 "
    				+ " AND ABONNEMENTS.IDPROFIL1 = (SELECT IDPROFIL FROM PROFIL WHERE PSEUDO = '"+pPseudoIdProfil1+"') "
    				+ " AND GAZOUILLI.GEOLOCALISATION = 'TRUE' "
    				+ " AND GAZOUILLI.VILLE = (SELECT VILLE FROM PROFIL WHERE PSEUDO = '"+pPseudoIdProfil1+"')");

    		while(rs.next())
    		{
    			gazouilli = new MessageGazouilli(rs.getString(1),rs.getString(2),rs.getString(3), rs.getTimestamp(4), rs.getBoolean(5), "");
    			res.add(gazouilli);
    		}
    		
        	return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
	}

	//liste des profils dans la BD
	public ArrayList<ProfilType> listeProfil() {
		ArrayList<ProfilType> res = new ArrayList<ProfilType>();
		ProfilType profil;
		
		try {
			Statement s = conn.createStatement();
        	//récupère le dernier ID
			ResultSet rs = s.executeQuery("select PSEUDO, NOM, PRENOM, VILLE  from PROFIL");

    		while(rs.next())
    		{
    			profil = new ProfilType(rs.getString(1), "", rs.getString(2), rs.getString(3), rs.getString(4));
    			res.add(profil);
    		}
    		
        	return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
	}
	
	public void fermer() throws Exception {		
		try {
			conn.close();
		} catch(Exception ex) {
			// il y a eu une erreur
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		JmsJDBC.clearBDD("JMS");
		JmsJDBC bdd = new JmsJDBC("JMS");
		
		System.out.println(" Création des profils : ");
			System.out.println("Profil n°: " + bdd.creerProfil("PseudoToto", "mdp", "NomToto", "PrenomToto", "Toulouse"));
			System.out.println("Profil n°: " +bdd.creerProfil("PseudoTutu", "mdp", "NomTutu", "PrenomTutu", "Marseille"));
			System.out.println("Profil n°: " +bdd.creerProfil("Toto", "123", "NomToto", "PrenomToto", "Rodez"));
			System.out.println(" --> OK");
		System.out.println(" Création des Gazouilli : ");
			//récupère la dateHeure
	    	Date date= new Date();
			Timestamp time = new Timestamp(date.getTime());
			System.out.println("gazouilli n°: " + bdd.creerGazouilli("Bonjour contenu", "PseudoToto", time, false));
			System.out.println("gazouilli n°: " + bdd.creerGazouilli("Bonjour contenu geoloc", "PseudoToto", time, true));
			System.out.println("gazouilli n°: " + bdd.creerGazouilli("Bonjour contenu2", "PseudoTutu", time, false));
			System.out.println("gazouilli n°: " + bdd.creerGazouilli("Bonjour contenu3", "Toto", time, false));
			System.out.println(" --> OK");
		
		System.out.println("--> Vérification mdp : " + bdd.verificationIDMDP("PseudoToto", "mdp"));
		System.out.println("--> Information profil : ");
			ProfilType profil = bdd.informationProfil("PseudoToto");
			System.out.println("     " + profil.getPSEUDO() + " " + profil.getNOM() + " " + profil.getPRENOM() + " " + profil.getVILLE());
		
		System.out.println("--> Création Abonnement : " + bdd.creerAbonnement("PseudoToto", "PseudoTutu"));
		System.out.println("--> Création Abonnement : " + bdd.creerAbonnement("PseudoTutu", "PseudoToto"));
		
		
		System.out.println("--> Liste Abonne : ");
			System.out.print("     ");
			for(int i = 0; i < bdd.listeAbonne("PseudoTutu").size(); i++)
			{
				System.out.print(bdd.listeAbonne("PseudoTutu").get(i) + " ");
			}
			System.out.println("");
			
		System.out.println("--> Liste Suivi : ");
			System.out.print("     ");
			for(int i = 0; i < bdd.listeSuivi("PseudoTutu").size(); i++)
			{
				System.out.print(bdd.listeSuivi("PseudoTutu").get(i) + " ");
			}
			System.out.println("");
			
		System.out.println("--> Supprimer Abonnement : " + bdd.supprimerAbonnement("PseudoToto", "PseudoTutu"));
		
		System.out.println("--> Création Abonnement : " + bdd.creerAbonnement("PseudoTutu", "PseudoToto"));
		
		System.out.println("--> Création Abonnement : " + bdd.creerAbonnement("PseudoToto", "PseudoTutu"));
		
		System.out.println("--> Liste gazouilli de PseudoToto : " +bdd.listeGazouilli("PseudoToto").toString());

		System.out.println("--> Liste gazouilli des abonnements de PseudoTutu : " +bdd.listeGazouilliAbonnements("PseudoTutu").toString());
		
		System.out.println("--> Nombre de gazouilli de PseudoToto : " +bdd.nbGazouilliPourUnProfil("PseudoToto"));
		
		System.out.println("--> Liste des profils : " +bdd.listeProfil().get(0).toString());

		

		//Pour ouvrir H2 il faut pointer sur le bon dossier:
		//C:\Users\landt\Dropbox\USB\MIAGE\M2\S9\Intergiciels pour la répartition\Projet_JMS\M2_JMS_proj\M2_JMS_proj_code\Projet_Twitter
		
		bdd.fermer();		
	}

}

