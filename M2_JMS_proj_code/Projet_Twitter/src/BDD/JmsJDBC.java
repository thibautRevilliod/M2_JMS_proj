package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


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
        			" PSEUDO VARCHAR( 500 ) , " +
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
			
        	s.execute("create table IF NOT EXISTS ABONNEMENTS  ( " +
        			" idABONNEMENTS INT NOT NULL PRIMARY KEY, " +
					" DATEHEURE TIMESTAMP )");
	        
        	s.execute("create table IF NOT EXISTS ABONNE  ( " +
        			" idABONNE INT NOT NULL, " +
					" idPROFIL INT NOT NULL, " +
					" PRIMARY KEY (idABONNE, idPROFIL) , " +
					" FOREIGN KEY (idABONNE) REFERENCES ABONNEMENTS(idABONNEMENTS) , " +
					" FOREIGN KEY (idPROFIL) REFERENCES PROFIL(idPROFIL))");
	        
        	s.execute("create table IF NOT EXISTS SUIVI  ( " +
        			" idSUIVI INT NOT NULL, " +
					" idPROFIL INT NOT NULL, " +
					" PRIMARY KEY (idSUIVI, idPROFIL) , " +
					" FOREIGN KEY (idSUIVI) REFERENCES ABONNEMENTS(idABONNEMENTS) , " +
					" FOREIGN KEY (idPROFIL) REFERENCES PROFIL(idPROFIL))");
			
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
	        
	        rs = conn.getMetaData().getTables(null, null, "ABONNE", null);
	        if (rs.next()) {
	        	// la table existe
	        	s.execute("drop table ABONNE");
	        	res = true;
	        } else {	
	        	res = false;
	        }
	        
	        rs = conn.getMetaData().getTables(null, null, "SUIVI", null);
	        if (rs.next()) {
	        	// la table existe
	        	s.execute("drop table SUIVI");
	        	res = true;
	        } else {	
	        	res = false;
	        }
	        
	        rs = conn.getMetaData().getTables(null, null, "PROFIL", null);
	        if (rs.next()) {
	        	// la table existe
	        	s.execute("drop table PROFIL");
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
	
	public int creerProfil(String ppseudo, String pnom, String pprenom, String pville) {
		int id = -1;
		try {
			Statement s = conn.createStatement();
        	//récupère le dernier ID
			ResultSet rs = s.executeQuery("select MAX(idPROFIL) from PROFIL");
        	if (rs.next())
        	{
        		id = rs.getInt(1)+1;
	        	s.executeUpdate("insert into PROFIL values ("+id+", '"+ppseudo+"', '"+pnom+"','"+pprenom+"','"+pville+"')");
	        } 
			else
	        {
				id = 1;
	        	s.executeUpdate("insert into PROFIL values ('1', '"+ppseudo+"', '"+pnom+"','"+pprenom+"','"+pville+"')");
	        }
	        
	        return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return id;
		}
		
		
	}
	
	public int creerGazouilli(String pcontenu, String pville, int pemetteur) {
		int id = -1;
		try {
			Statement s = conn.createStatement();
        	//récupère le dernier ID
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
			return id;
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
		//JmsJDBC.clearBDD("JMS");
		JmsJDBC bdd = new JmsJDBC("JMS");
		
		System.out.println(" Création des profils : ");
		System.out.println("Profil n°: " + bdd.creerProfil("PseudoToto", "NomToto", "PrenomToto", "Toulouse"));
		System.out.println("Profil n°: " +bdd.creerProfil("PseudoTutu", "NomTutu", "PrenomTutu", "Paris"));
		System.out.println(" --> OK");
		System.out.println(" Création des Gazouilli : ");
		System.out.println("gazouilli n°: " + bdd.creerGazouilli("Bonjour contenu", "Toulouse", 1));
		System.out.println("gazouilli n°: " + bdd.creerGazouilli("Bonjour contenu2", "Toulouse2", 1));
		System.out.println(" --> OK");
		
//		banque.creerCompte("Bobby", 1000);
//		System.out.println(" Compte Bobby : "+banque.position("Bobby"));
//		banque.creerCompte("Bob", 1000);
//		System.out.println(" Compte Bob : "+banque.position("Bob"));
//		banque.ajouter("Bobby", 100);
//		banque.retirer("Bobby", 150);
//		System.out.println(" Compte Bobby : "+banque.position("Bobby"));

		//Pour ouvrir H2 il faut pointer sur le bon dossier:
		//C:\Users\landt\Dropbox\USB\MIAGE\M2\S9\Intergiciels pour la répartition\Projet_JMS\M2_JMS_proj\M2_JMS_proj_code\Projet_Twitter
		
		bdd.fermer();		
	}

}

