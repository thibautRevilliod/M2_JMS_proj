package jms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import bdd.JmsJDBC;
import metier.MessageEnregistrementParam;
import metier.MessageGazouilli;
import metier.MessageAbonnement;
import metier.MessageConnexion;
import metier.MessageDeconnexion;
import metier.MessageInscription;
import metier.MessageListeAbonnement;
import metier.MessageListeMessageDunProfil;
import metier.MessageListeProfil;
import metier.MessageNbGazouilliUnProfil;
import metier.MessagePopulateProfil;
import metier.ProfilAConsulter;
import metier.ProfilType;

public class SenderTwitter {	
	
	//constantes
			public static final  String TOPICMESSAGETWITTER = "messagesProjetTwitter";
			private static final String FILEGESTPROFILS = "fileGestProfils";
			
	private static Context context = null;
    private static ConnectionFactory factory = null;
    private static Connection connection = null;
    private static String factoryName = "ConnectionFactory";
    private static String destName = null;
    private static Destination dest = null;
    private static Session session = null;
    private static MessageProducer sender = null;
    private static MessageConsumer consumer = null;
    private static BufferedReader waiter = null;
    
    private static String messageRetour;
    //liste abonnement est utilisé par le thread comme filtre pour le topic
    
    //Liste des personnes auxquelles on est abonné
    private static ArrayList<String> listeAbonnement = new ArrayList<String>();
    //Liste des personnes qui nous suivent.
    private static ArrayList<String> listeSuivi = new ArrayList<String>();
    
    
    private static ArrayList<MessageGazouilli> listeGazouilliDunAbonnement = new ArrayList<MessageGazouilli>();
    private static ArrayList<MessageGazouilli> listeGazouilliDesAbonnements = new ArrayList<MessageGazouilli>();
    private static Thread threadConnexion;
	private static MonRunnable runnableConnexion;
	private static int nbGazouilliUnProfil;
	private static ArrayList<ProfilType> listeProfil = new ArrayList<ProfilType>();
	private static ProfilType profilConnecte;
	private static ProfilAConsulter profilAConsulter;
	
    
	
    public static ProfilAConsulter getProfilAConsulter() {
		return profilAConsulter;
	}

	public static ProfilType getProfilConnecte() {
		return profilConnecte;
	}

	public static void setProfilConnecte(ProfilType profilConnecte) {
		SenderTwitter.profilConnecte = profilConnecte;
	}

	public static ArrayList<ProfilType> getListeProfil() {
		return listeProfil;
	}

	public static void setListeProfil(ArrayList<ProfilType> listeProfil) {
		SenderTwitter.listeProfil = listeProfil;
	}

	public static int getNbGazouilliUnProfil() {
		return nbGazouilliUnProfil;
	}

	public static void setNbGazouilliUnProfil(int nbGazouilliUnProfil) {
		SenderTwitter.nbGazouilliUnProfil = nbGazouilliUnProfil;
	}

	public static ArrayList<MessageGazouilli> getListeGazouilliDunAbonnement() {
		return listeGazouilliDunAbonnement;
	}

	public static void setListeGazouilliDunAbonnement(
			ArrayList<MessageGazouilli> listeGazouilliDunAbonnement) {
		SenderTwitter.listeGazouilliDunAbonnement = listeGazouilliDunAbonnement;
	}

	public static ArrayList<MessageGazouilli> getListeGazouilliDesAbonnements() {
		return listeGazouilliDesAbonnements;
	}

	public static void setListeGazouilliDesAbonnements(
			ArrayList<MessageGazouilli> listeGazouilliDesAbonnements) {
		SenderTwitter.listeGazouilliDesAbonnements = listeGazouilliDesAbonnements;
	}

	public static ArrayList<String> getListeAbonnement() {
		return listeAbonnement;
	}

	public static void setListeAbonnement(ArrayList<String> listeAbonnement) {
		SenderTwitter.listeAbonnement = listeAbonnement;
	}

	public static ArrayList<String> getListeSuivi() {
		return listeSuivi;
	}

	public static void setListeSuivi(ArrayList<String> listeSuivi) {
		SenderTwitter.listeSuivi = listeSuivi;
	}

//	public static ArrayList<String> getListeFiltreProfil() {
//		return listeFiltreProfil;
//	}
//
//	public static void setListeFiltreProfil(ArrayList<String> listeFiltreProfil) {
//		SenderTwitter.listeFiltreProfil = listeFiltreProfil;
//	}

	public static String getMessageRetour() {
		return messageRetour;
	}

	public static void setMessageRetour(String messageRetour) {
		SenderTwitter.messageRetour = messageRetour;
	}

//	public static HashMap<String, ArrayList<String>> getListeDesAbnnementsParProfil() {
//		return listeDesAbnnementsParProfil;
//	}
//	
//	public static void removeListeDesAbnnementsParProfil(String pPseudo) {
//		listeDesAbnnementsParProfil.remove(pPseudo);
//	}
//
//	public static void setListeDesAbnnementsParProfil(
//			HashMap<String, ArrayList<String>> listeDesAbnnementsParProfil) {
//		SenderTwitter.listeDesAbnnementsParProfil = listeDesAbnnementsParProfil;
//	}

	public static void initialize() throws NamingException, JMSException
    {
    	// create the JNDI initial context.
        context = new InitialContext();

        // look up the ConnectionFactory
        factory = (ConnectionFactory) context.lookup(factoryName);

        // look up the Destination
        dest = (Destination) context.lookup(destName);

        // create the connection
        connection = factory.createConnection();

        // create the session
        session = connection.createSession(
            false, Session.AUTO_ACKNOWLEDGE);

        // create the sender
        sender = session.createProducer(dest);

        // start the connection, to enable message sends
        connection.start();
    }
    
	public static void inscription(String pseudo, String motDePasse, String nom, String prenom, String ville)
	{
        destName = FILEGESTPROFILS;

        try {
            
        	initialize();
        	
        	TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);

        	MessageInscription messageInscription = new MessageInscription(pseudo, motDePasse, nom, prenom, ville);
        	ObjectMessage objectMessage = session.createObjectMessage(messageInscription);
        	objectMessage.setJMSReplyTo(temporaryQueue);
        	objectMessage.setJMSType("inscription");
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messageInscription.toString());
        	
        	Message receivedMessage = consumer.receive();//TODO cf 3000 en paramètre
        	setMessageRetour(receivedMessage.toString());
        	System.out.println("received message : " + receivedMessage);
            
          
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
	
	public static void connexion(String pseudo, String motDePasse)
	{
        destName = FILEGESTPROFILS;

        try {
            
        	initialize();            
        	
    		TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);

        	MessageConnexion messageConnexion = new MessageConnexion(pseudo, motDePasse, "", "");
        	ObjectMessage objectMessage = session.createObjectMessage(messageConnexion);
        	objectMessage.setJMSReplyTo(temporaryQueue);
        	objectMessage.setJMSType("connexion");
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messageConnexion.toString());
        	
        	
        	Message receivedMessage = consumer.receive();
        	ObjectMessage objectMessageRetour = (ObjectMessage) receivedMessage;
        	MessageConnexion messageConnexionRetour = (MessageConnexion) objectMessageRetour.getObject();
        	setMessageRetour(messageConnexionRetour.getMessageRetour().toString());
        	System.out.println("received message : " + messageConnexionRetour.toString());
        	
        	if(messageConnexionRetour.getMessageRetour().toString().equals("Connexion OK"))
        	{	        	
        		//mise à jour de la liste des abonnés
	            listeAbonne(pseudo);
	            listeSuivi(pseudo);
        		//abonnement au topic messagesGeo
		        	//lance le thread d'écoute du Topic
	            	MonRunnable.setTest(true);
	            	runnableConnexion = new MonRunnable(pseudo, messageConnexionRetour.getVille());
		        	threadConnexion = new Thread(runnableConnexion);
		        	threadConnexion.start();
        	}
        	
        	setMessageRetour(messageConnexionRetour.getMessageRetour().toString());
          
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
	
	public static void populateProfilConnecte(String pseudo)
	{
        destName = FILEGESTPROFILS;

        try {
            
        	initialize();            
        	
    		TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);

        	MessagePopulateProfil messagePopulateProfil = new MessagePopulateProfil(pseudo);
        	ObjectMessage objectMessage = session.createObjectMessage(messagePopulateProfil);
        	objectMessage.setJMSReplyTo(temporaryQueue);
        	objectMessage.setJMSType("populateProfil");
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messagePopulateProfil.toString());
        	
        	Message receivedMessage = consumer.receive();
        	ObjectMessage objectMessageRetour = (ObjectMessage) receivedMessage;
        	profilConnecte = (ProfilType) objectMessageRetour.getObject();
        	setMessageRetour(profilConnecte.getMessageRetour().toString());
        	System.out.println("received message : " + profilConnecte.toString());
        	
          
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
	
	public static void populateProfilAConsulter(String pseudo)
	{
        destName = FILEGESTPROFILS;

        try {
            
        	initialize();            
        	
    		TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);

        	MessagePopulateProfil messagePopulateProfil = new MessagePopulateProfil(pseudo);
        	ObjectMessage objectMessage = session.createObjectMessage(messagePopulateProfil);
        	objectMessage.setJMSReplyTo(temporaryQueue);
        	objectMessage.setJMSType("populateProfilToConsult");
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messagePopulateProfil.toString());
        	
        	Message receivedMessage = consumer.receive();
        	ObjectMessage objectMessageRetour = (ObjectMessage) receivedMessage;
        	profilAConsulter= (ProfilAConsulter) objectMessageRetour.getObject();
        	setMessageRetour(profilAConsulter.getMessageRetour().toString());
        	System.out.println("received message : " + profilAConsulter.toString());
        	
          
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
	
	public static void deconnexion(String pseudo)
	{
        destName = FILEGESTPROFILS;

        try {
            
        	initialize();

    		TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);

        	MessageDeconnexion messageDeconnexion = new MessageDeconnexion(pseudo);
        	ObjectMessage objectMessage = session.createObjectMessage(messageDeconnexion);
        	objectMessage.setJMSReplyTo(temporaryQueue);
        	objectMessage.setJMSType("deconnexion");
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messageDeconnexion.toString());
        	
        	Message receivedMessage = consumer.receive();
        	setMessageRetour(receivedMessage.toString());
        	System.out.println("received message : " + receivedMessage);
        	
        	if(receivedMessage.toString().equals("Deconnexion OK"))
        	{	        	
        		//desinscription au topic
	        	MonRunnable.setTest(false);
	        	//remove the profil on the list profil
	        	listeAbonnement.clear();
        	}
        	
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
	
	//TODO : ne pas faire de update si aucun champ n'est modifié
	public static void miseAJourProfil(String pseudo, String mdp, String nom, String prenom, String ville)
	{
        destName = FILEGESTPROFILS;

        try {
            
        	initialize();

    		TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);

        	MessageEnregistrementParam messageDeconnexion = new MessageEnregistrementParam(pseudo, mdp, nom, prenom, ville);
        	ObjectMessage objectMessage = session.createObjectMessage(messageDeconnexion);
        	objectMessage.setJMSReplyTo(temporaryQueue);
        	objectMessage.setJMSType("enregistrementParam");
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messageDeconnexion.toString());
        	
        	Message receivedMessage = consumer.receive();
        	setMessageRetour(receivedMessage.toString());
        	System.out.println("received message : " + receivedMessage);
        
        	
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
	
	public static void creerAbonnement(String pPseudoIdProfilSuiviPar1, String pPseudoIdProfil1)
	{
        destName = FILEGESTPROFILS;

        try {
            
        	initialize();            
        	
    		TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);

        	MessageAbonnement messageAbonnement = new MessageAbonnement(pPseudoIdProfilSuiviPar1, pPseudoIdProfil1);
        	ObjectMessage objectMessage = session.createObjectMessage(messageAbonnement);
        	objectMessage.setJMSReplyTo(temporaryQueue);
        	objectMessage.setJMSType("creerAbonnement");
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messageAbonnement.toString());
        	
        	Message receivedMessage = consumer.receive();
        	setMessageRetour(receivedMessage.toString());
        	System.out.println("received message : " + receivedMessage);
        	
        	if(receivedMessage.toString().equals("Abonnement avec '" + messageAbonnement.getPseudoIdProfilSuiviPar1() +"' OK"))
        	{
        	// ajout de l'utilisateur suivi dans la liste d'abonnement
        	listeSuivi.add(pPseudoIdProfilSuiviPar1);
        	}
            
          
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
	
	public static void suppAbonnement(String pPseudoIdProfilSuiviPar1, String pPseudoIdProfil1)
	{
        destName = FILEGESTPROFILS;

        try {
            
        	initialize();            
        	
    		TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);

        	MessageAbonnement messageAbonnement = new MessageAbonnement(pPseudoIdProfilSuiviPar1, pPseudoIdProfil1);
        	ObjectMessage objectMessage = session.createObjectMessage(messageAbonnement);
        	objectMessage.setJMSReplyTo(temporaryQueue);
        	objectMessage.setJMSType("suppAbonnement");
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messageAbonnement.toString());
        	
        	Message receivedMessage = consumer.receive();
        	setMessageRetour(receivedMessage.toString());
        	System.out.println("received message : " + receivedMessage);
     
        	//suppression dans l'arraylist
    		listeAbonnement.remove(pPseudoIdProfilSuiviPar1);
          
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
	
	public static void listeAbonne(String pPseudoIdProfil1)
	{		
		destName = FILEGESTPROFILS;

        try {
            
        	initialize();            
        	
    		TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);
        	
        	MessageListeAbonnement messageListeAbonnement = new MessageListeAbonnement(pPseudoIdProfil1, null, "");
        	ObjectMessage objectMessage = session.createObjectMessage(messageListeAbonnement);
        	objectMessage.setJMSReplyTo(temporaryQueue);
        	objectMessage.setJMSType("listeAbonne");
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messageListeAbonnement.toString());
        	
        	Message receivedMessage = consumer.receive();
        	ObjectMessage objectMessageRetour = (ObjectMessage) receivedMessage;
        	MessageListeAbonnement messageListeAbonnementRetour = (MessageListeAbonnement) objectMessageRetour.getObject();
        	setMessageRetour(messageListeAbonnementRetour.getMessageRetour().toString());
        	setListeAbonnement(messageListeAbonnementRetour.getListeAbonnement());
        	System.out.println("received message : " + messageListeAbonnementRetour.toString());
          
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
	
	public static void listeSuivi(String pPseudoIdProfilSuiviPar1)
	{
        destName = FILEGESTPROFILS;

        try {
            
        	initialize();            
        	
    		TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);
        	
        	MessageListeAbonnement messageListeAbonnement = new MessageListeAbonnement(pPseudoIdProfilSuiviPar1, null, "");
        	ObjectMessage objectMessage = session.createObjectMessage(messageListeAbonnement);
        	objectMessage.setJMSReplyTo(temporaryQueue);
        	objectMessage.setJMSType("listeSuivi");
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messageListeAbonnement.toString());
        	
        	Message receivedMessage = consumer.receive();
        	ObjectMessage objectMessageRetour = (ObjectMessage) receivedMessage;
        	MessageListeAbonnement messageListeAbonnementRetour = (MessageListeAbonnement) objectMessageRetour.getObject();
        	setMessageRetour(messageListeAbonnementRetour.getMessageRetour().toString());
        	setListeSuivi(messageListeAbonnementRetour.getListeAbonnement());
        	System.out.println("received message : " + messageListeAbonnementRetour.getMessageRetour());
          
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
	
	public static void listeGazouilliDesAbonnements(String pPseudoIdProfil1)
	{
		destName = FILEGESTPROFILS;

        try {
        	
        	initialize();
        	
        	TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	ObjectMessage objectMessage;
        	Message receivedMessage;
        	ObjectMessage objectMessageRetour;
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);
        	
        	
            	MessageListeMessageDunProfil messageListeMessageDunProfil = new MessageListeMessageDunProfil(pPseudoIdProfil1, listeGazouilliDunAbonnement, "");
            	objectMessage = session.createObjectMessage(messageListeMessageDunProfil);
            	objectMessage.setJMSReplyTo(temporaryQueue);
            	objectMessage.setJMSType("listeGazouilliDesAbonnements");
            	sender.send(objectMessage);
            	System.out.println("Sent: " + messageListeMessageDunProfil.toString());
            	
            	receivedMessage = consumer.receive();
            	objectMessageRetour = (ObjectMessage) receivedMessage;
            	MessageListeMessageDunProfil messageListeMessageDunProfilRetour = (MessageListeMessageDunProfil) objectMessageRetour.getObject();
            	listeGazouilliDesAbonnements = messageListeMessageDunProfilRetour.getListeGazouilli();	   
            	System.out.println("received message : " + messageListeMessageDunProfilRetour.toString());
            	setMessageRetour(messageListeMessageDunProfilRetour.getMessageRetour());
          
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }

    }
	
	public static void listeGazouilliDunAbonnements(String pPesudoAbonne)
	{
        destName = FILEGESTPROFILS;

        try {
        	
        	initialize();
        	
        	TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	ObjectMessage objectMessage;
        	Message receivedMessage;
        	ObjectMessage objectMessageRetour;
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);
        	
        	
            	MessageListeMessageDunProfil messageListeMessageDunProfil = new MessageListeMessageDunProfil(pPesudoAbonne, listeGazouilliDunAbonnement, "");
            	objectMessage = session.createObjectMessage(messageListeMessageDunProfil);
            	objectMessage.setJMSReplyTo(temporaryQueue);
            	objectMessage.setJMSType("listeGazouilli");
            	sender.send(objectMessage);
            	System.out.println("Sent: " + messageListeMessageDunProfil.toString());
            	
            	receivedMessage = consumer.receive();
            	objectMessageRetour = (ObjectMessage) receivedMessage;
            	MessageListeMessageDunProfil messageListeMessageDunProfilRetour = (MessageListeMessageDunProfil) objectMessageRetour.getObject();
            	listeGazouilliDunAbonnement = messageListeMessageDunProfilRetour.getListeGazouilli();	   
            	System.out.println("received message : " + messageListeMessageDunProfilRetour.toString());
        	
          
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
	
	public static void creerGazouilliTopic(String pContenu, String pPseudoEmetteur, boolean pGeolocalisation)
	{
        //destName = "messagesNonGeo";
        destName = FILEGESTPROFILS;
        
        try {
        		
        		initialize();
            	
            	TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
            	
            	// create the consumer
            	consumer = session.createConsumer(temporaryQueue);
            	
            	//récupère la dateHeure
            	Date date= new Date();
        		Timestamp time = new Timestamp(date.getTime());
        		
            	MessageGazouilli messageGazouilli = new MessageGazouilli(pContenu, "", pPseudoEmetteur,time, pGeolocalisation,"");
            	ObjectMessage objectMessage = session.createObjectMessage(messageGazouilli);
            	objectMessage.setJMSReplyTo(temporaryQueue);
            	objectMessage.setJMSType("creerGazouilli");
            	sender.send(objectMessage);
            	System.out.println("Sent: " + messageGazouilli.toString());
            	
            	Message receivedMessage = consumer.receive();
            	ObjectMessage objectMessageRetour = (ObjectMessage) receivedMessage;
            	MessageGazouilli messageGazouilliRetour = (MessageGazouilli) objectMessageRetour.getObject();
            	setMessageRetour(messageGazouilliRetour.getMessageRetour().toString());
            	System.out.println("received message : " + messageGazouilliRetour.toString());
            	
            	//dans le topic
            	destName = SenderTwitter.TOPICMESSAGETWITTER;
            	initialize();
            	objectMessageRetour.setJMSType(pPseudoEmetteur);
        		sender.send(objectMessageRetour);
            	System.out.println("Sent Topic: " + messageGazouilliRetour.toString());
                      
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
	
	public static void nbGazouilliDunProfil(String pPseudo)
	{
        destName = FILEGESTPROFILS;

        try {
            
        	initialize();            
        	
    		TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);

        	MessageNbGazouilliUnProfil messageNbGazouilliUnProfil = new MessageNbGazouilliUnProfil(pPseudo, 0, "");
        	ObjectMessage objectMessage = session.createObjectMessage(messageNbGazouilliUnProfil);
        	objectMessage.setJMSReplyTo(temporaryQueue);
        	objectMessage.setJMSType("nbGazouilliUnProfil");
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messageNbGazouilliUnProfil.toString());
        	
        	Message receivedMessage = consumer.receive();
        	ObjectMessage objectMessageRetour = (ObjectMessage) receivedMessage;
        	MessageNbGazouilliUnProfil messageNbGazouilliUnProfilRetour = (MessageNbGazouilliUnProfil) objectMessageRetour.getObject();
        	setMessageRetour(messageNbGazouilliUnProfilRetour.getMessageRetour());
        	setNbGazouilliUnProfil(messageNbGazouilliUnProfilRetour.getNbGazouilli());
        	System.out.println("received message : " + messageNbGazouilliUnProfilRetour.toString());
        
          
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
	
	public static void listeDesProfils()
	{
        destName = FILEGESTPROFILS;

        try {
            
        	initialize();            
        	
    		TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);

        	MessageListeProfil messageListeProfil = new MessageListeProfil(null, "");
        	ObjectMessage objectMessage = session.createObjectMessage(messageListeProfil);
        	objectMessage.setJMSReplyTo(temporaryQueue);
        	objectMessage.setJMSType("listeProfil");
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messageListeProfil.toString());
        	
        	Message receivedMessage = consumer.receive();
        	ObjectMessage objectMessageRetour = (ObjectMessage) receivedMessage;
        	MessageListeProfil messageListeProfilRetour = (MessageListeProfil) objectMessageRetour.getObject();
        	setMessageRetour(messageListeProfilRetour.getMessageRetour());
        	setListeProfil(messageListeProfilRetour.getListeProfil());
        	System.out.println("received message : " + messageListeProfilRetour.toString());
        
          
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
	
	
	
	public static void main(String[] args) {
        Context context = null;
        ConnectionFactory factory = null;
        Connection connection = null;
        String factoryName = "ConnectionFactory";
        String destName = null;
        Destination dest = null;
        int count = 1;
        Session session = null;
        MessageProducer sender = null;
        String text = "Message ";

        if (args.length < 1 || args.length > 2) {
            System.out.println("usage: Sender <destination> [count]");
            System.exit(1);
        }

        destName = args[0];
        if (args.length == 2) {
            count = Integer.parseInt(args[1]);
        }

        try {
            // create the JNDI initial context.
            context = new InitialContext();

            // look up the ConnectionFactory
            factory = (ConnectionFactory) context.lookup(factoryName);

            // look up the Destination
            dest = (Destination) context.lookup(destName);

            // create the connection
            connection = factory.createConnection();

            // create the session
            session = connection.createSession(
                false, Session.AUTO_ACKNOWLEDGE);

            // create the sender
            sender = session.createProducer(dest);

            // start the connection, to enable message sends
            connection.start();

           /* for (int i = 0; i < count; ++i) {
                TextMessage message = session.createTextMessage();
                message.setText(text + (i + 1));
                sender.send(message);
                System.out.println("Sent: " + message.getText()); */
            
//            TitreBoursier t = new TitreBoursier("GOOG", "Google Inc.", 656.99f, 2.33f);
//            ObjectMessage om = session.createObjectMessage(t);
//            om.setJMSType("T1");
//            sender.send(om);
//            System.out.println("Sent: " + t.getMnemonique());
//            
//            TitreBoursier t2 = new TitreBoursier("APPL", "Apple Inc.", 111.189f, -0.43f);
//            ObjectMessage om2 = session.createObjectMessage(t2);
//            om2.setJMSType("T2");
//            sender.send(om2);
//            System.out.println("Sent: " + t2.getMnemonique());
            
            String vContenu = "Bonjour contenu";
            String vDate = "";
            String vHeure = "";
            String vVille = "Toulouse";
            int vIdEmetteur = 1;
            
            // instanciation de la BD 
//            JmsJDBC bdd = new JmsJDBC("JMS");
//            
//            bdd.creerGazouilli(vContenu, vVille, vIdEmetteur);
//            
//            Gazouilli gazouilli = new Gazouilli(vvContenu, vDate, vHeure, vVille, vEmetteur);
            
            
          
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

}
