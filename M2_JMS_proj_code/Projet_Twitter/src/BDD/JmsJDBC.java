package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


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
					" FOREIGN KEY (EMETTEUR) REFERENCES PROFIL(idPROFIL))");
			
//        	s.execute("create table IF NOT EXISTS ABONNEMENTS  ( " +
//        			" idABONNEMENTS INT NOT NULL PRIMARY KEY, " +
//					" DATEHEURE TIMESTAMP )");
	        
        	s.execute("create table IF NOT EXISTS ABONNEMENTS  ( " +
        			" idSUIVI INT NOT NULL, " +
					" idABONNE INT NOT NULL, " +
					" DATEHEURE TIMESTAMP, " +
					" PRIMARY KEY (idSUIVI, idABONNE) , " +
					" FOREIGN KEY (idSUIVI) REFERENCES PROFIL(idPROFIL) , " +
					" FOREIGN KEY (idABONNE) REFERENCES PROFIL(idPROFIL))");
	        
//        	s.execute("create table IF NOT EXISTS SUIVI  ( " +
//        			" idSUIVI INT NOT NULL, " +
//					" idPROFIL INT NOT NULL, " +
//					" DATEHEURE TIMESTAMP, " +
//					" PRIMARY KEY (idSUIVI, idPROFIL) , " +
//					" FOREIGN KEY (idSUIVI) REFERENCES PROFIL(idPROFIL) , " +
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
				//r�cup�re le dernier ID
				rs = s.executeQuery("select MAX(idPROFIL) from PROFIL");
	        	if (rs.next())
	        	{
	        		id = rs.getInt(1)+1;
		        	s.executeUpdate("insert into PROFIL values ("+id+", '"+ppseudo+"', '"+pmdp+"', '"+pnom+"','"+pprenom+"','"+pville+"')");
		        } 
				else
		        {
					id = 1;
		        	s.executeUpdate("insert into PROFIL values ('1', '"+ppseudo+"', '"+pmdp+"', '"+pnom+"','"+pprenom+"','"+pville+"')");
		        }
			}
			else
			{
				id = -2; // le cas ou le pseudo existe d�j�
			}
	        return id;
		} catch (SQLException e) {
			e.printStackTrace();
			id = -1;
			return id;
		}
		
		
	}
	
	// si OK, retourne l'ID de l'utilisateur, sinon retourne -1
	public int verificationIDMDP(String ppseudo, String pmdp) {
		int id = -1;
		try {
			Statement s = conn.createStatement();
        	//r�cup�re l'ID de l'utilisateur
			ResultSet rs = s.executeQuery("SELECT IDPROFIL FROM PROFIL WHERE PSEUDO = '"+ppseudo+"' AND MDP = '"+pmdp+"'");
        	if (rs.next())
        	{
        		id = rs.getInt(1);
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
	public String[] informationProfil(String pid) {
		String[] res = new String[4];
		try {
			Statement s = conn.createStatement();
        	//r�cup�re l'ID de l'utilisateur
			ResultSet rs = s.executeQuery("SELECT PSEUDO, NOM, PRENOM, VILLE FROM PROFIL WHERE IDPROFIL = '"+pid+"'");
        	if (rs.next())
        	{
        		res[0] = rs.getString(1);
        		res[1] = rs.getString(2);
        		res[2] = rs.getString(3);
        		res[3] = rs.getString(4);
	        } 
			else
	        {
				res = null;
	        }
	        
	        return res;
		} catch (SQLException e) {
			e.printStackTrace();
			res = null;
			return res;
		}
	}
	
	public int creerGazouilli(String pcontenu, String pville, int pemetteur) {
		int id = -1;
		try {
			Statement s = conn.createStatement();
        	//r�cup�re le dernier ID
			ResultSet rs = s.executeQuery("select MAX(idGAZOUILLI) from GAZOUILLI");
        	if (rs.next())
        	{
        		id = rs.getInt(1)+1;
        		s.executeUpdate("insert into GAZOUILLI values ('"+id+"', '"+pcontenu+"',CURRENT_TIMESTAMP(),'"+pville+"','"+pemetteur+"')");
        	}
        	else
        	{
        		id = 1;
        		s.executeUpdate("insert into GAZOUILLI values ('1', '"+pcontenu+"',CURRENT_TIMESTAMP(),'"+pville+"','"+pemetteur+"')");
        	}
        	return id;
		} catch (SQLException e) {
			e.printStackTrace();
			id = -1;
			return id;
		}
	}
	
	public int creerAbonnement(String ppseudoSuivi, String ppseudoAbonne) {
		int id = -1;
		try {
			Statement s = conn.createStatement();
        	//r�cup�re le dernier ID
			ResultSet rs = s.executeQuery("select idSUIVI, idABONNE from ABONNEMENTS WHERE idSUIVI = (SELECT idProfil FROM PROFIL WHERE pseudo = '"+ppseudoSuivi+"') AND idABONNE = (SELECT idProfil FROM PROFIL WHERE pseudo = '"+ppseudoAbonne+"')");
        	if (!rs.next())
        	{
        		s.executeUpdate("insert into ABONNEMENTS values ((SELECT idProfil FROM PROFIL WHERE pseudo = '"+ppseudoSuivi+"'), (SELECT idProfil FROM PROFIL WHERE pseudo = '"+ppseudoAbonne+"'),CURRENT_TIMESTAMP())");
        		id = 0;
        	}
        	else
        	{
        		// Erreur : l'abonnement existe d�j�
        		id = -2;
        	}
        	return id;
		} catch (SQLException e) {
			e.printStackTrace();
			id = -1;
			return id;
		}
	}
	
	public int supprimerAbonnement(String ppseudoSuivi, String ppseudoAbonne) {
		int id = -1;
		try {
			Statement s = conn.createStatement();
        	//r�cup�re le dernier ID
			ResultSet rs = s.executeQuery("select idSUIVI, idABONNE from ABONNEMENTS WHERE idSUIVI = (SELECT idProfil FROM PROFIL WHERE pseudo = '"+ppseudoSuivi+"') AND idABONNE = (SELECT idProfil FROM PROFIL WHERE pseudo = '"+ppseudoAbonne+"')");
        	if (rs.next())
        	{
        		s.executeUpdate("DELETE FROM ABONNEMENTS WHERE idSUIVI = (SELECT idProfil FROM PROFIL WHERE pseudo = '"+ppseudoSuivi+"') AND idABONNE = (SELECT idProfil FROM PROFIL WHERE pseudo = '"+ppseudoAbonne+"')");
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
	
	//liste des abonnements du profil pass� en param�tre
	public String[] listeAbonne(String pPseudoAbonne) {
		String[] res = null;
		boolean resOK = false;
		
		try {
			Statement s = conn.createStatement();
        	//r�cup�re le dernier ID
			ResultSet rs = s.executeQuery("select PROFIL.PSEUDO from ABONNEMENTS, PROFIL WHERE ABONNEMENTS.idSuivi = PROFIL.idProfil AND idAbonne = (SELECT idProfil FROM PROFIL WHERE pseudo = '"+pPseudoAbonne+"')");
        	Vector<String> vector = new Vector<String>();

    		while(rs.next())
    		{
    			resOK = true;
    			vector.add(rs.getString(1));
    		}
    		
    		if(resOK)
    		{
    			res = new String[vector.size()];
    			for (int i = 0; i < vector.size(); i++)
    			{
    				res[i]= vector.get(i);
    			}
        	}
        	else
        	{
        		// Erreur : auncun abonnement existe
        		// return null
        	}
        	return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
	}
	
	//liste des suivis du profil pass� en param�tre
	public String[] listeSuivi(String pPseudoSuivi) {
		String[] res = null;
		boolean resOK = false;
		
		try {
			Statement s = conn.createStatement();
        	//r�cup�re le dernier ID
			ResultSet rs = s.executeQuery("select PROFIL.PSEUDO from ABONNEMENTS, PROFIL WHERE ABONNEMENTS.idABONNE = PROFIL.idProfil AND IDSUIVI = (SELECT idProfil FROM PROFIL WHERE pseudo = '"+pPseudoSuivi+"')");
        	Vector<String> vector = new Vector<String>();

    		while(rs.next())
    		{
    			resOK = true;
    			vector.add(rs.getString(1));
    		}
    		
    		if(resOK)
    		{
    			res = new String[vector.size()];
    			for (int i = 0; i < vector.size(); i++)
    			{
    				res[i]= vector.get(i);
    			}
        	}
        	else
        	{
        		// Erreur : auncun abonnement existe
        		// return null
        	}
        	return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
	}

//	public boolean ajouter(String id, double somme) {	
//		try {
//			Statement s = conn.createStatement();
//			ResultSet rs = s.executeQuery("select solde from BANQUE where id = '"+id+"'");
//	        if (rs.next()) {
//	        	double solde = rs.getDouble("solde");
//	        	solde+=somme;
//	        	if (s.executeUpdate("update BANQUE set solde="+solde+",dateDerniereOperation=CURRENT_TIMESTAMP() where id = '"+id+"'")==1)
//					return true;
//	        	else
//	        		return false;
//	        } else {
//	        	return false;
//	        }
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}	
//	}
	
//	public boolean retirer(String id, double somme) {
//		try {
//			Statement s = conn.createStatement();
//			ResultSet rs = s.executeQuery("select solde from BANQUE where id = '"+id+"'");
//	        if (rs.next()) {
//	        	double solde = rs.getDouble("solde");
//	        	solde-=somme;
//	        	if (s.executeUpdate("update BANQUE set solde="+solde+",dateDerniereOperation=CURRENT_TIMESTAMP() where id = '"+id+"'")==1)
//					return true;
//	        	else
//	        		return false;
//	        } else {
//	        	return false;
//	        }
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}	
//	}

//	public Position position(String id) {
//		try {
//			Statement s = conn.createStatement();
//			ResultSet rs = s.executeQuery("select solde,dateDerniereOperation from BANQUE where id = '"+id+"'");
//	        if (rs.next()) {
//	        	double solde = rs.getDouble("solde");
//	        	Date date = rs.getTimestamp("dateDerniereOperation");
//	        	Position p = new Position(solde);
//	        	p.setDerniereOperation(date);
//	        	return p;
//	        } else {
//	        	return null;
//	        }
//		} catch(Exception ex) {
//			// il y a eu une erreur
//			ex.printStackTrace();
//			return null;
//		}
//	}
	
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
		
		System.out.println(" Cr�ation des profils : ");
			System.out.println("Profil n�: " + bdd.creerProfil("PseudoToto", "mdp", "NomToto", "PrenomToto", "Toulouse"));
			System.out.println("Profil n�: " +bdd.creerProfil("PseudoTutu", "mdp", "NomTutu", "PrenomTutu", "Paris"));
			System.out.println(" --> OK");
		System.out.println(" Cr�ation des Gazouilli : ");
			System.out.println("gazouilli n�: " + bdd.creerGazouilli("Bonjour contenu", "Toulouse", 1));
			System.out.println("gazouilli n�: " + bdd.creerGazouilli("Bonjour contenu2", "Toulouse2", 1));
			System.out.println(" --> OK");
		
		System.out.println("--> V�rification mdp : " + bdd.verificationIDMDP("PseudoToto", "mdp"));
		System.out.println("--> Information profil : ");
			String[] var_res = bdd.informationProfil("1");
			System.out.println("     " + var_res[0] + " " + var_res[1] + " " + var_res[2] + " " + var_res[3]);
		
		System.out.println("--> Cr�ation Abonnement : " + bdd.creerAbonnement("PseudoToto", "PseudoTutu"));
		
		System.out.println("--> Liste Abonne : ");
			System.out.print("     ");
			for(int i = 0; i < bdd.listeAbonne("PseudoTutu").length; i++)
			{
				System.out.print(bdd.listeAbonne("PseudoTutu")[i] + " ");
			}
			System.out.println("");
			
		System.out.println("--> Liste Suivi : ");
			System.out.print("     ");
			for(int i = 0; i < bdd.listeSuivi("PseudoToto").length; i++)
			{
				System.out.print(bdd.listeSuivi("PseudoToto")[i] + " ");
			}
			System.out.println("");
			
		System.out.println("--> Supprimer Abonnement : " + bdd.supprimerAbonnement("PseudoToto", "PseudoTutu"));
		
		System.out.println("--> Cr�ation Abonnement : " + bdd.creerAbonnement("PseudoTutu", "PseudoToto"));
		
//		banque.creerCompte("Bobby", 1000);
//		System.out.println(" Compte Bobby : "+banque.position("Bobby"));
//		banque.creerCompte("Bob", 1000);
//		System.out.println(" Compte Bob : "+banque.position("Bob"));
//		banque.ajouter("Bobby", 100);
//		banque.retirer("Bobby", 150);
//		System.out.println(" Compte Bobby : "+banque.position("Bobby"));

		//Pour ouvrir H2 il faut pointer sur le bon dossier:
		//C:\Users\landt\Dropbox\USB\MIAGE\M2\S9\Intergiciels pour la r�partition\Projet_JMS\M2_JMS_proj\M2_JMS_proj_code\Projet_Twitter
		
		bdd.fermer();		
	}

}

