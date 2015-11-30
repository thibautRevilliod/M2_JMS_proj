package jms;

import java.util.ArrayList;
import java.util.HashMap;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import metier.MessageGazouilli;

public class MonRunnable implements Runnable {
	
	private InitialContext context;
	private ConnectionFactory factory;
	private Destination dest;
	private TopicSubscriber topicSubscriber;
	private Connection connection;
	private Session session;
	private MessageProducer sender;
	private String factoryName = "ConnectionFactory";
	private String destName = null;
	private String pseudo;
	private String ville;
	private ArrayList<String> listeDesAbonnementsProfil = new ArrayList<String>();
	private static boolean test = true;
	
	public static boolean isTest() {
		return test;
	}

	public static void setTest(boolean test) {
		MonRunnable.test = test;
	}

	public MonRunnable(String pPseudo, String pVille)
	{
		this.pseudo = pPseudo;
		this.ville = pVille;
	}
	
	public void run()
	{
		try{
			initialize();
			
			ObjectMessage objectMessage = null;
			MessageGazouilli messageGazouilli = null;
	        while(test) {
	        	//TODO: vérification dans le topic toute les 100 milliseconde ok ?
	        	Message message = topicSubscriber.receive(100);
	        	listeDesAbonnementsProfil = SenderTwitter.getListeAbonnement();
	        	if((listeDesAbonnementsProfil != null) && (message != null))
	        	{
	        		if(listeDesAbonnementsProfil.size() > 0)
		        	{	
	        			objectMessage = (ObjectMessage) message;
		                messageGazouilli = (MessageGazouilli) objectMessage.getObject();
		                if(messageGazouilli.isEstGeolocalise())
		        		{
		        			if((listeDesAbonnementsProfil.contains(message.getJMSType())) && (ville.equals(messageGazouilli.getVille())))
		        			{
		        				//cas avec la geolocalisation
		        				gui.main.main.gazouillisSession.add(messageGazouilli);
				                System.out.println("Gazouilli : " + messageGazouilli.toString());
		        			}
		        			
		        		}else if(listeDesAbonnementsProfil.contains(message.getJMSType()))
			        	{
			        		//cas sans geolocalisation
		        			gui.main.main.gazouillisSession.add(messageGazouilli);
			                System.out.println("Gazouilli : " + messageGazouilli.toString());
			                System.out.println(("Test : " + gui.main.main.gazouillisSession.toString()));
			                //retourner dans l'interface le résultat
			        	}
		        	}
	        	}
	        }
        
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // close the context
            if (context != null) {
                try {
                	topicSubscriber.close();
                	session.unsubscribe(pseudo);
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                } catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
//		int nb =0;
//		while(true)
//		{
//			try {
//				nb++;
//				Thread.sleep(3000);
//				System.out.println("test : " + nb);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
	}
	
	public void initialize() throws NamingException, JMSException
    {
		String topicMessagesGeo = SenderTwitter.TOPICMESSAGETWITTER;
		
    	// create the JNDI initial context.
        context = new InitialContext();

        // look up the ConnectionFactory
        factory = (ConnectionFactory) context.lookup(factoryName);

        // look up the Destination
       // dest = (Destination) context.lookup(destName);
        Topic topic = (Topic) context.lookup(topicMessagesGeo); 

        // create the connection
        connection = factory.createConnection();

        // create the session
        session = connection.createSession(
            false, Session.AUTO_ACKNOWLEDGE);

        // création de l’abonné persistant (
        topicSubscriber = session.createDurableSubscriber(topic, pseudo);  
        
        // démarre la connexion. Si l’abonné existait déjà on va recevoir les messages
        // en attente dès ce moment
        connection.start();
    }
	
	
}
