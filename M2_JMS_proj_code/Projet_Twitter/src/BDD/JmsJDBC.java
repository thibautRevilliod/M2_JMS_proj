package BDD;

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
        			" idPROFIL VARCHAR( 500 ) NOT NULL PRIMARY KEY, " +
        			" NOM VARCHAR( 500 ) , " +
					" PRENOM VARCHAR( 500 ) , " +
					" VILLE VARCHAR( 500 ) )");

        	s.execute("create table IF NOT EXISTS GAZOUILLI  ( " +
        			" idGAZOUILLI VARCHAR( 500 ) NOT NULL PRIMARY KEY, " +
        			" CONTENU VARCHAR( 500 ) , " +
					" DATEHEURE TIMESTAMP , " +
        			" VILLE VARCHAR( 500 ) , " +
					" EMETTEUR VARCHAR( 500 ) , " +
					" FOREIGN KEY (EMETTEUR) REFERENCES PROFIL(idPROFIL))");
			
        	s.execute("create table IF NOT EXISTS ABONNEMENTS  ( " +
        			" idABONNEMENTS VARCHAR( 500 ) NOT NULL PRIMARY KEY, " +
					" DATEHEURE TIMESTAMP )");
	        
        	s.execute("create table IF NOT EXISTS ABONNE  ( " +
        			" idABONNE VARCHAR( 500 ) NOT NULL, " +
					" idPROFIL VARCHAR( 500 ) NOT NULL, " +
					" PRIMARY KEY (idABONNE, idPROFIL) , " +
					" FOREIGN KEY (idABONNE) REFERENCES ABONNEMENTS(idABONNEMENTS) , " +
					" FOREIGN KEY (idPROFIL) REFERENCES PROFIL(idPROFIL))");
	        
        	s.execute("create table IF NOT EXISTS SUIVI  ( " +
        			" idSUIVI VARCHAR( 500 ) NOT NULL, " +
					" idPROFIL VARCHAR( 500 ) NOT NULL, " +
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
	
	public boolean creerProfil(String pid, String pnom, String pprenom, String pville) {
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("select idPROFIL from PROFIL where idPROFIL = '"+pid+"'");
	        if (rs.next()) {
	        	// un compte existe deja avec cet id
	        	return false;
	        } else {
	        	s.executeUpdate("insert into PROFIL values ('"+pid+"', '"+pnom+"','"+pprenom+"','"+pville+"')");
	        	return true;
	        }
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public boolean creerGazouilli(String pid, String pcontenu, String pville, String pemetteur) {
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("select idGAZOUILLI from GAZOUILLI where idGAZOUILLI = '"+pid+"'");
	        if (rs.next()) {
	        	// un compte existe deja avec cet id
	        	return false;
	        } else {
	        	s.executeUpdate("insert into GAZOUILLI values ('"+pid+"', '"+pcontenu+"',CURRENT_TIMESTAMP(),'"+pville+"','"+pemetteur+"')");
	        	return true;
	        }
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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
		bdd.creerProfil("1", "NomToto", "PrenomToto", "Toulouse");
		bdd.creerProfil("2", "NomTutu", "PrenomTutu", "Paris");
		System.out.println(" --> OK");
		System.out.println(" Création des Gazouilli : ");
		bdd.creerGazouilli("1", "Bonjour contenu", "Toulouse", "1");
		System.out.println(" --> OK");
		
//		banque.creerCompte("Bobby", 1000);
//		System.out.println(" Compte Bobby : "+banque.position("Bobby"));
//		banque.creerCompte("Bob", 1000);
//		System.out.println(" Compte Bob : "+banque.position("Bob"));
//		banque.ajouter("Bobby", 100);
//		banque.retirer("Bobby", 150);
//		System.out.println(" Compte Bobby : "+banque.position("Bobby"));

		bdd.fermer();		
	}

}

