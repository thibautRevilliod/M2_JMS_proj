package jms;
/**
 * Redistribution and use of this software and associated documentation
 * ("Software"), with or without modification, are permitted provided
 * that the following conditions are met:
 *
 * 1. Redistributions of source code must retain copyright
 *    statements and notices.  Redistributions must also contain a
 *    copy of this document.
 *
 * 2. Redistributions in binary form must reproduce the
 *    above copyright notice, this list of conditions and the
 *    following disclaimer in the documentation and/or other
 *    materials provided with the distribution.
 *
 * 3. The name "Exolab" must not be used to endorse or promote
 *    products derived from this Software without prior written
 *    permission of Exoffice Technologies.  For written permission,
 *    please contact jima@intalio.com.
 *
 * 4. Products derived from this Software may not be called "Exolab"
 *    nor may "Exolab" appear in their names without prior written
 *    permission of Exoffice Technologies. Exolab is a registered
 *    trademark of Exoffice Technologies.
 *
 * 5. Due credit should be given to the Exolab Project
 *    (http://www.exolab.org/).
 *
 * THIS SOFTWARE IS PROVIDED BY EXOFFICE TECHNOLOGIES AND CONTRIBUTORS
 * ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL
 * EXOFFICE TECHNOLOGIES OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Copyright 2004-2005 (C) Exoffice Technologies Inc. All Rights Reserved.
 *
 * $Id: Receiver.java,v 1.2 2005/11/18 03:28:01 tanderson Exp $
 */

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Destination;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.MessageConsumer;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;

import metier.MessageGazouilli;
import metier.MessageAbonnement;
import metier.MessageConnexion;
import metier.MessageDeconnexion;
import metier.MessageInscription;
import metier.MessageListeAbonnement;
import metier.MessageListeMessageDunProfil;
import metier.MessageListeProfil;
import metier.MessageNbGazouilliUnProfil;
import metier.ProfilType;
import bdd.JmsJDBC;


/**
 * Synchronous <code>MessageConsumer</code> example.
 *
 * @author <a href="mailto:tma@netspace.net.au">Tim Anderson</a>
 * @version $Revision: 1.2 $ $Date: 2005/11/18 03:28:01 $
 */
public class Receiver {

    /**
     * Main line.
     *
     * @param args command line arguments
     */
	//constantes
		private static final String FILEGESTPROFILS = "fileGestProfils";
	
	private static Context context = null;
	private static ConnectionFactory factory = null;
	private static Connection connection = null;
	private static String factoryName = "ConnectionFactory";
	private static String destName = null;
	private static Destination dest = null;
	private static int count = 20;
	private static Session session = null;
	private static MessageConsumer receiver = null;
	private static MessageProducer sender = null;
	
	private static ArrayList<String> listePseudoConnecte = new ArrayList();
	
	private static JmsJDBC bdd;
    
	public static void initializeQueues() throws NamingException, JMSException
	{
		// create the JNDI initial context
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

        // create the receiver
        receiver = session.createConsumer(dest);

        // start the connection, to enable message receipt
        connection.start();
	}	

	
//	public static void receptionGazouilli()
//	{
//        destName = "fileGestProfils";
//sss
//        try {
//            
//        	initialize();
//
//            for (int i = 0; i < count; ++i) {
//                Message message = receiver.receive();
//                if (message instanceof ObjectMessage) {
//                	
//                    ObjectMessage objectMessage = (ObjectMessage) message;
//                    MessageGazouilli gazouilli = (MessageGazouilli) objectMessage.getObject();
//                	
//                    System.out.println("Received: " + gazouilli.toString());
//                    
//                 // instanciation de la BD 
//                    JmsJDBC bdd = new JmsJDBC("JMS");
//                    bdd.creerGazouilli(gazouilli.getContenu(), gazouilli.getVille(), gazouilli.getIdEmetteur());
//                    
//                } else if (message != null) {
//                    System.out.println("Received non text message");
//                }
//            }
//        } catch (JMSException exception) {
//            exception.printStackTrace();
//        } catch (NamingException exception) {
//            exception.printStackTrace();
//        } finally {
//            // close the context
//            if (context != null) {
//                try {
//                    context.close();
//                } catch (NamingException exception) {
//                    exception.printStackTrace();
//                }
//            }
//
//            // close the connection
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (JMSException exception) {
//                    exception.printStackTrace();
//                }
//            }
//            
//            //réponse dans la file temporaire
//        }
//    }
	
	public static void receptionFileGestProfils()
	{
        destName = Receiver.FILEGESTPROFILS;

        try {
            
        	initializeQueues();

            for (int i = 0; i < count; ++i) {
                Message message = receiver.receive();
                if (message instanceof ObjectMessage) {
                	if (message.getJMSType().equals("inscription"))
                	{
                		receptionInscription(message);
                	
                	} else if (message.getJMSType().equals("connexion"))
                	{
                		receptionConnexion(message);
                	} else if (message.getJMSType().equals("deconnexion"))
                	{
                		receptionDeconnexion(message);
                	} else if (message.getJMSType().equals("creerAbonnement"))
                	{
                		receptionCreerAbonnement(message);
                	} else if (message.getJMSType().equals("suppAbonnement"))
                	{
                		receptionSuppAbonnement(message);
                	} else if (message.getJMSType().equals("listeAbonne"))
                	{
                		receptionListeAbonne(message);
                	} else if (message.getJMSType().equals("listeSuivi"))
                	{
                		receptionListeSuivi(message);
                	} else if (message.getJMSType().equals("creerGazouilli"))
                	{
                		receptionGazouilli(message);
                	} else if (message.getJMSType().equals("listeGazouilli"))
                	{
                		receptionListeGazouilliDunAbonnement(message);
                	} else if (message.getJMSType().equals("listeGazouilliDesAbonnements"))
                	{
                		receptionListeGazouilliDesAbonnements(message);
                	} else if (message.getJMSType().equals("nbGazouilliUnProfil"))
                	{
                		receptionNbGazouilliDunProfil(message);
                	} else if (message.getJMSType().equals("listeProfil"))
                	{
                		receptionListeDesProfils(message);
                	}
                	
                	
                    
                } else if (message != null) {
                    System.out.println("Received non text message");
                }
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
	
	public static void receptionInscription(Message message) throws JMSException
	{
		int retourCreerProfil;
		TextMessage replyMessage;
		
		ObjectMessage objectMessage = (ObjectMessage) message;
        MessageInscription messageInscription = (MessageInscription) objectMessage.getObject();
    	
        System.out.println("Received: " + messageInscription.toString());
        
        Destination temporaryQueue = objectMessage.getJMSReplyTo();
        System.out.println("[Receiver] temporary queue : " + temporaryQueue.toString());
        
        
        // enregistrement du profil en BD 
        retourCreerProfil = bdd.creerProfil(messageInscription.getPseudo(), messageInscription.getMotDePasse(), messageInscription.getNom(), messageInscription.getPrenom(), messageInscription.getVille());
        
        if(retourCreerProfil == -1)//inscription KO en BD
        {
        	replyMessage = session.createTextMessage("Inscription KO");
        }
        else if (retourCreerProfil == -2)//inscription KO en BD car le pseudo existe déjà
        {
        	replyMessage = session.createTextMessage("Inscription KO : Le pseudo existe déjà");
        }
        else
        {
        	replyMessage = session.createTextMessage("Inscription OK");
        }
    
    
    
        // create the sender
        sender = session.createProducer(temporaryQueue);
        
        sender.send(replyMessage);
        
        System.out.println("envoi de replyMessage : " + replyMessage);                    
	}
	
	public static void receptionConnexion(Message message) throws JMSException
	{
		String retourConnexionProfil;
		ObjectMessage replyMessage;
		
		ObjectMessage objectMessage = (ObjectMessage) message;
        MessageConnexion messageConnexion = (MessageConnexion) objectMessage.getObject();
    	
        System.out.println("Received: " + messageConnexion.toString());
        
        Destination temporaryQueue = objectMessage.getJMSReplyTo();
        System.out.println("[Receiver] temporary queue : " + temporaryQueue.toString());
        
        MessageConnexion messageConnexionRetour = new MessageConnexion(messageConnexion.getPseudo(),messageConnexion.getMotDePasse(),"","");
        
        if(!listePseudoConnecte.contains(messageConnexion.getPseudo()))
        {
	        // test de l'existance du profil en BD 
	        retourConnexionProfil = bdd.verificationIDMDP(messageConnexion.getPseudo(), messageConnexion.getMotDePasse());
	        
	        if(retourConnexionProfil == null)//connexion KO en BD
	        {
	        	messageConnexionRetour.setMessageRetour("Connexion KO : Pseudo ou mot de passe invalide");
	        }
	        else
	        {
	        	listePseudoConnecte.add(messageConnexion.getPseudo());
	        	messageConnexionRetour.setMessageRetour("Connexion OK");
	        	messageConnexionRetour.setVille(retourConnexionProfil);
	        }
        }else
        {
        	messageConnexionRetour.setMessageRetour("Connexion KO : Profil déjà connecté");
        }
        
        replyMessage = session.createObjectMessage(messageConnexionRetour);
    
        // create the senders
        sender = session.createProducer(temporaryQueue);
        
        sender.send(replyMessage);
        
        System.out.println("envoi de replyMessage : " + replyMessage);                    
	}
	
	public static void receptionDeconnexion(Message message) throws JMSException
	{
		TextMessage replyMessage;
		
		ObjectMessage objectMessage = (ObjectMessage) message;
        MessageDeconnexion messageDeconnexion = (MessageDeconnexion) objectMessage.getObject();
    	
        System.out.println("Received: " + messageDeconnexion.toString());
        
        Destination temporaryQueue = objectMessage.getJMSReplyTo();
        System.out.println("[Receiver] temporary queue : " + temporaryQueue.toString());
               
        //suppression dans listePseudoConnecte, le pseudo qui se déconnecte
        if(listePseudoConnecte.contains(messageDeconnexion.getPseudo()))
        {
        	listePseudoConnecte.remove(messageDeconnexion.getPseudo());
        	replyMessage = session.createTextMessage("Deconnexion OK");
        }else
        {
        	replyMessage = session.createTextMessage("Deconnexion KO");
        }
        
        // create the sender
        sender = session.createProducer(temporaryQueue);
        
        sender.send(replyMessage);
        
        System.out.println("envoi de replyMessage : " + replyMessage);                    
	}
	
	public static void receptionCreerAbonnement(Message message) throws JMSException
	{
		TextMessage replyMessage;
		int retourReceptionAbonnement;
		
		ObjectMessage objectMessage = (ObjectMessage) message;
        MessageAbonnement messageAbonnement = (MessageAbonnement) objectMessage.getObject();
    	
        System.out.println("Received: " + messageAbonnement.toString());
        
        Destination temporaryQueue = objectMessage.getJMSReplyTo();
        System.out.println("[Receiver] temporary queue : " + temporaryQueue.toString());
        
        //inscription dans la BD de l'abonnement
        retourReceptionAbonnement = bdd.creerAbonnement(messageAbonnement.getPseudoIdProfilSuiviPar1(), messageAbonnement.getPseudoIdProfil1());
        
        if(retourReceptionAbonnement == -1)//création KO en BD
        {
        	replyMessage = session.createTextMessage("Abonnement avec '" + messageAbonnement.getPseudoIdProfilSuiviPar1() +"' KO");
        }
        else if(retourReceptionAbonnement == -2)
        {
        	//abonnement existe déjà
        	replyMessage = session.createTextMessage("Abonnement avec '" + messageAbonnement.getPseudoIdProfilSuiviPar1() +"' KO : L'abonnement existe déjà");
        }else
        {
        	//TODO : Doit on stocker dans une liste, les abonnements ou on regarde tout le temps dans la BD ?
        	replyMessage = session.createTextMessage("Abonnement avec '" + messageAbonnement.getPseudoIdProfilSuiviPar1() +"' OK");
        }
        
        // create the sender
        sender = session.createProducer(temporaryQueue);
        
        sender.send(replyMessage);
        
        System.out.println("envoi de replyMessage : " + replyMessage);                    
	}
	
	public static void receptionSuppAbonnement(Message message) throws JMSException
	{
		TextMessage replyMessage;
		int retourReceptionAbonnement;
		
		ObjectMessage objectMessage = (ObjectMessage) message;
        MessageAbonnement messageAbonnement = (MessageAbonnement) objectMessage.getObject();
    	
        System.out.println("Received: " + messageAbonnement.toString());
        
        Destination temporaryQueue = objectMessage.getJMSReplyTo();
        System.out.println("[Receiver] temporary queue : " + temporaryQueue.toString());
        
        //inscription dans la BD de l'abonnement
        retourReceptionAbonnement = bdd.supprimerAbonnement(messageAbonnement.getPseudoIdProfilSuiviPar1(), messageAbonnement.getPseudoIdProfil1());
        
        if(retourReceptionAbonnement == -1)//suppression KO en BD
        {
        	replyMessage = session.createTextMessage("Suppression de l'abonnement avec '" + messageAbonnement.getPseudoIdProfilSuiviPar1() +"' KO");
        }
        else if(retourReceptionAbonnement == -2)
        {
        	//abonnement n'existe pas
        	replyMessage = session.createTextMessage("Suppression de l'abonnement avec '" + messageAbonnement.getPseudoIdProfilSuiviPar1() +"' KO : L'abonnement n'existe pas");
        }else
        {
        	//TODO : Doit on stocker dans une liste, les abonnements ou on regarde tout le temps dans la BD ?
        	replyMessage = session.createTextMessage("Suppression de l'abonnement avec '" + messageAbonnement.getPseudoIdProfilSuiviPar1() +"' OK");
        }
        
        // create the sender
        sender = session.createProducer(temporaryQueue);
        
        sender.send(replyMessage);
        
        System.out.println("envoi de replyMessage : " + replyMessage);                    
	}
	
	public static void receptionListeAbonne(Message message) throws JMSException
	{
		ObjectMessage replyMessage;
		
		ObjectMessage objectMessage = (ObjectMessage) message;
        MessageListeAbonnement messageListeAbonnement = (MessageListeAbonnement) objectMessage.getObject();
    	
        System.out.println("Received: " + messageListeAbonnement.toString());
        
        Destination temporaryQueue = objectMessage.getJMSReplyTo();
        System.out.println("[Receiver] temporary queue : " + temporaryQueue.toString());
        
        //récupération de la liste des abonnes
        ArrayList<String> listeAbonne = bdd.listeAbonne(messageListeAbonnement.getPseudoIdProfil1());
        MessageListeAbonnement messageListeAbonnementRetour = new MessageListeAbonnement(messageListeAbonnement.getPseudoIdProfil1(),listeAbonne,"");
        
        if(messageListeAbonnementRetour.getListeAbonnement() == null)//aucun abonne existe
        {
        	messageListeAbonnementRetour.setMessageRetour("Liste abonne KO : aucun abonne existe");
        }
        else
        {
        	//TODO : Doit on stocker dans une liste, les abonnements ou on regarde tout le temps dans la BD ?
        	messageListeAbonnementRetour.setMessageRetour("Liste des abonnes OK");
        }
        
        replyMessage = session.createObjectMessage(messageListeAbonnementRetour);
        
        // create the sender
        sender = session.createProducer(temporaryQueue);
        
        sender.send(replyMessage);
        
        System.out.println("envoi de replyMessage : " + messageListeAbonnementRetour.toString());                    
	}
	
	public static void receptionListeSuivi(Message message) throws JMSException
	{
		ObjectMessage replyMessage;
		
		ObjectMessage objectMessage = (ObjectMessage) message;
        MessageListeAbonnement messageListeAbonnement = (MessageListeAbonnement) objectMessage.getObject();
    	
        System.out.println("Received: " + messageListeAbonnement.toString());
        
        Destination temporaryQueue = objectMessage.getJMSReplyTo();
        System.out.println("[Receiver] temporary queue : " + temporaryQueue.toString());
        
        //récupération de la liste des suivis
        ArrayList<String> listeSuivi = bdd.listeSuivi(messageListeAbonnement.getPseudoIdProfil1());
        MessageListeAbonnement messageListeAbonnementRetour = new MessageListeAbonnement(messageListeAbonnement.getPseudoIdProfil1(),listeSuivi,"");
        
        if(messageListeAbonnementRetour.getListeAbonnement() == null)//aucun abonne existe
        {
        	messageListeAbonnementRetour.setMessageRetour("Liste suiveur KO : aucun suiveur existe");
        }
        else
        {
        	//TODO : Doit on stocker dans une liste, les abonnements ou on regarde tout le temps dans la BD ?
        	messageListeAbonnementRetour.setMessageRetour("Liste des suiveurs OK");
        }
        
        replyMessage = session.createObjectMessage(messageListeAbonnementRetour);
        
        // create the sender
        sender = session.createProducer(temporaryQueue);
        
        sender.send(replyMessage);
        
        System.out.println("envoi de replyMessage : " + messageListeAbonnementRetour.toString());                    
	}
	
	public static void receptionGazouilli(Message message) throws JMSException
	{		
		ObjectMessage replyMessage;
		String retourReceptionGazouilli;
		
		ObjectMessage objectMessage = (ObjectMessage) message;
        MessageGazouilli messageGazouilli = (MessageGazouilli) objectMessage.getObject();
    	
        System.out.println("Received: " + messageGazouilli.toString());
        
        Destination temporaryQueue = objectMessage.getJMSReplyTo();
        System.out.println("[Receiver] temporary queue : " + temporaryQueue.toString());
        
        // inscription dans la BD du gazouilli
        retourReceptionGazouilli = bdd.creerGazouilli(messageGazouilli.getContenu(), messageGazouilli.getPseudoEmetteur(), messageGazouilli.getDateHeure(), messageGazouilli.isEstGeolocalise());
        
        MessageGazouilli messageGazouilliRetour = new MessageGazouilli(messageGazouilli.getContenu(), retourReceptionGazouilli, messageGazouilli.getPseudoEmetteur(), messageGazouilli.getDateHeure(), messageGazouilli.isEstGeolocalise(), "");
        
        if(retourReceptionGazouilli == null)//creation KO en BD
        {
        	messageGazouilliRetour.setMessageRetour("Creation du Gazouilli KO");
        }
        else
        {
        	//TODO : Doit on stocker dans une liste, les abonnements ou on regarde tout le temps dans la BD ?
        	messageGazouilliRetour.setMessageRetour("Creation du Gazouilli OK");
        }
        
        replyMessage = session.createObjectMessage(messageGazouilliRetour);
        
        // create the sender
        sender = session.createProducer(temporaryQueue);
        
        sender.send(replyMessage);
        
        System.out.println("envoi de replyMessage : " + messageGazouilliRetour.toString());                    
	}
	
	public static void receptionListeGazouilliDunAbonnement(Message message) throws JMSException
	{
		ObjectMessage replyMessage;
		
		ObjectMessage objectMessage = (ObjectMessage) message;
        MessageListeMessageDunProfil messageListeMessageDunProfil = (MessageListeMessageDunProfil) objectMessage.getObject();
    	
        System.out.println("Received: " + messageListeMessageDunProfil.toString());
        
        Destination temporaryQueue = objectMessage.getJMSReplyTo();
        System.out.println("[Receiver] temporary queue : " + temporaryQueue.toString());
        
        //récupération de la liste des suivis
        ArrayList<MessageGazouilli> listeSuivi = bdd.listeGazouilli(messageListeMessageDunProfil.getPseudo());
        MessageListeMessageDunProfil messageListeMessageDunProfilRetour = new MessageListeMessageDunProfil(messageListeMessageDunProfil.getPseudo(),listeSuivi,"");
        
        if(messageListeMessageDunProfilRetour.getListeGazouilli() == null)//aucun abonne existe
        {
        	messageListeMessageDunProfilRetour.setMessageRetour("Liste gazouilli KO");
        }
        else
        {
        	//TODO : Doit on stocker dans une liste, les abonnements ou on regarde tout le temps dans la BD ?
        	messageListeMessageDunProfilRetour.setMessageRetour("Liste gazouilli OK");
        }
        
        replyMessage = session.createObjectMessage(messageListeMessageDunProfilRetour);
        
        // create the sender
        sender = session.createProducer(temporaryQueue);
        
        sender.send(replyMessage);
        
        System.out.println("envoi de replyMessage : " + messageListeMessageDunProfilRetour.toString());                    
	}
	
	public static void receptionListeGazouilliDesAbonnements(Message message) throws JMSException
	{
		ObjectMessage replyMessage;
		
		ObjectMessage objectMessage = (ObjectMessage) message;
        MessageListeMessageDunProfil messageListeMessageDesAbonnements = (MessageListeMessageDunProfil) objectMessage.getObject();
    	
        System.out.println("Received: " + messageListeMessageDesAbonnements.toString());
        
        Destination temporaryQueue = objectMessage.getJMSReplyTo();
        System.out.println("[Receiver] temporary queue : " + temporaryQueue.toString());
        
        //récupération de la liste des Gazouillis
        ArrayList<MessageGazouilli> listeGazouilli = bdd.listeGazouilliAbonnements(messageListeMessageDesAbonnements.getPseudo());
        MessageListeMessageDunProfil messageListeMessageDesAbonnementsRetour = new MessageListeMessageDunProfil(messageListeMessageDesAbonnements.getPseudo(),listeGazouilli,"");
        
        if(messageListeMessageDesAbonnementsRetour.getListeGazouilli() == null)//aucun abonne existe
        {
        	messageListeMessageDesAbonnementsRetour.setMessageRetour("Liste gazouilli des abonnements KO");
        }
        else
        {
        	//TODO : Doit on stocker dans une liste, les abonnements ou on regarde tout le temps dans la BD ?
        	messageListeMessageDesAbonnementsRetour.setMessageRetour("Liste gazouilli des abonnements OK");
        }
        
        replyMessage = session.createObjectMessage(messageListeMessageDesAbonnementsRetour);
        
        // create the sender
        sender = session.createProducer(temporaryQueue);
        
        sender.send(replyMessage);
        
        System.out.println("envoi de replyMessage : " + messageListeMessageDesAbonnementsRetour.toString());                  
	}
	
	public static void receptionNbGazouilliDunProfil(Message message) throws JMSException
	{
		ObjectMessage replyMessage;
		
		ObjectMessage objectMessage = (ObjectMessage) message;
        MessageNbGazouilliUnProfil messageNbGazouilliUnProfil = (MessageNbGazouilliUnProfil) objectMessage.getObject();
    	
        System.out.println("Received: " + messageNbGazouilliUnProfil.toString());
        
        Destination temporaryQueue = objectMessage.getJMSReplyTo();
        System.out.println("[Receiver] temporary queue : " + temporaryQueue.toString());
        
        //récupération du nombre de gazouilli
        int nbGazouilliRetour = bdd.nbGazouilliPourUnProfil(messageNbGazouilliUnProfil.getPseudo());
        
        if(nbGazouilliRetour == -1)//erreur BD
        {
        	messageNbGazouilliUnProfil.setMessageRetour("Nombre de gazouilli d'un profil KO");
        }
        else
        {
        	messageNbGazouilliUnProfil.setMessageRetour("Nombre de gazouilli d'un profil OK");
        }
        
        replyMessage = session.createObjectMessage(messageNbGazouilliUnProfil);
        
        // create the sender
        sender = session.createProducer(temporaryQueue);
        
        sender.send(replyMessage);
        
        System.out.println("envoi de replyMessage : " + messageNbGazouilliUnProfil.toString());                  
	}
	
	public static void receptionListeDesProfils(Message message) throws JMSException
	{
		ObjectMessage replyMessage;
		
		ObjectMessage objectMessage = (ObjectMessage) message;
        MessageListeProfil messageListeProfil = (MessageListeProfil) objectMessage.getObject();
    	
        System.out.println("Received: " + messageListeProfil.toString());
        
        Destination temporaryQueue = objectMessage.getJMSReplyTo();
        System.out.println("[Receiver] temporary queue : " + temporaryQueue.toString());
        
        //récupération de la liste des profils
        ArrayList<ProfilType> listeDesProfils = bdd.listeProfil();
        MessageListeProfil messageListeProfilRetour = new MessageListeProfil(listeDesProfils, "");
        
        if(messageListeProfilRetour.getListeProfil() == null)//aucun profil existe
        {
        	messageListeProfilRetour.setMessageRetour("Liste des profils KO");
        }
        else
        {
        	messageListeProfilRetour.setMessageRetour("Liste des profils OK");
        }
        
        replyMessage = session.createObjectMessage(messageListeProfilRetour);
        
        // create the sender
        sender = session.createProducer(temporaryQueue);
        
        sender.send(replyMessage);
        
        System.out.println("envoi de replyMessage : " + messageListeProfilRetour.toString());                  
	}
	
	
	public static void main(String[] args) {
		bdd = new JmsJDBC("JMS");
		receptionFileGestProfils();
	}
	
//	public static void main(String[] args) {
//        Context context = null;
//        ConnectionFactory factory = null;
//        Connection connection = null;
//        String factoryName = "ConnectionFactory";
//        String destName = null;
//        Destination dest = null;
//        int count = 1;
//        Session session = null;
//        MessageConsumer receiver = null;
//
//        if (args.length < 1 || args.length > 2) {
//            System.out.println("usage: Receiver <destination> [count]");
//            System.exit(1);
//        }
//
//        destName = args[0];
//        if (args.length == 2) {
//            count = Integer.parseInt(args[1]);
//        }
//
//        try {
//            // create the JNDI initial context
//            context = new InitialContext();
//
//            // look up the ConnectionFactory
//            factory = (ConnectionFactory) context.lookup(factoryName);
//
//            // look up the Destination
//            dest = (Destination) context.lookup(destName);
//
//            // create the connection
//            connection = factory.createConnection();
//
//            // create the session
//            session = connection.createSession(
//                false, Session.AUTO_ACKNOWLEDGE);
//
//            // create the receiver
//            receiver = session.createConsumer(dest);
//
//            // start the connection, to enable message receipt
//            connection.start();
//
//            for (int i = 0; i < count; ++i) {
//                Message message = receiver.receive();
//                if (message instanceof TextMessage) {
//                    TextMessage text = (TextMessage) message;
//                    System.out.println("Received: " + text.getText());
//                } else if (message != null) {
//                    System.out.println("Received non text message");
//                }
//            }
//        } catch (JMSException exception) {
//            exception.printStackTrace();
//        } catch (NamingException exception) {
//            exception.printStackTrace();
//        } finally {
//            // close the context
//            if (context != null) {
//                try {
//                    context.close();
//                } catch (NamingException exception) {
//                    exception.printStackTrace();
//                }
//            }
//
//            // close the connection
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (JMSException exception) {
//                    exception.printStackTrace();
//                }
//            }
//        }
//    }
}
