package jms;

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
import metier.MessageGazouilli;
import metier.MessageAbonnement;
import metier.MessageConnexion;
import metier.MessageDeconnexion;
import metier.MessageInscription;
import metier.MessageListeAbonnement;

public class SenderTwitter {	
	
	private static Context context = null;
    private static ConnectionFactory factory = null;
    private static Connection connection = null;
    private static String factoryName = "ConnectionFactory";
    private static String destName = null;
    private static Destination dest = null;
    private static Session session = null;
    private static MessageProducer sender = null;
    private static MessageConsumer consumer = null;
    
    private static String messageRetour;
    private static String[] listeAbonnementMessageRetour;
    //TODO: Comment on gère les sessions ? Une liste de session d'abonnement ?
    private static TopicSubscriber topicSubscriber;
	
    public static String getMessageRetour() {
		return messageRetour;
	}

	public static void setMessageRetour(String messageRetour) {
		SenderTwitter.messageRetour = messageRetour;
	}

	public static String[] getListeAbonnementMessageRetour() {
		return listeAbonnementMessageRetour;
	}

	public static void setListeAbonnementMessageRetour(
			String[] listeAbonnementMessageRetour) {
		SenderTwitter.listeAbonnementMessageRetour = listeAbonnementMessageRetour;
	}

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
        destName = "fileGestProfils";

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
        destName = "fileGestProfils";

        try {
            
        	initialize();            
        	
    		TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);

        	MessageConnexion messageConnexion = new MessageConnexion(pseudo, motDePasse);
        	ObjectMessage objectMessage = session.createObjectMessage(messageConnexion);
        	objectMessage.setJMSReplyTo(temporaryQueue);
        	objectMessage.setJMSType("connexion");
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messageConnexion.toString());
        	
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
	
	public static void deconnexion(String pseudo)
	{
        destName = "fileGestProfils";

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
	
	public static void creerAbonnement(String pPseudoSuivi, String pPseudoAbonne)
	{
        destName = "fileGestProfils";

        try {
            
        	initialize();            
        	
    		TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);

        	MessageAbonnement messageAbonnement = new MessageAbonnement(pPseudoSuivi, pPseudoAbonne);
        	ObjectMessage objectMessage = session.createObjectMessage(messageAbonnement);
        	objectMessage.setJMSReplyTo(temporaryQueue);
        	objectMessage.setJMSType("creerAbonnement");
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messageAbonnement.toString());
        	
        	Message receivedMessage = consumer.receive();
        	setMessageRetour(receivedMessage.toString());
        	System.out.println("received message : " + receivedMessage);
        	
        	//abonnement au topic
        	String topicName = "messagesNonGeo";
            Topic topic = (Topic) context.lookup(topicName); 
            // création de l’abonné persistant (
            topicSubscriber = session.createDurableSubscriber(topic, pPseudoAbonne);
            // démarre la connexion. Si l’abonné existait déjà on va recevoir les messages
            // en attente dès ce moment
            connection.start();
           
            //TODO: enlever le boolean test et lancer la boucle dans un nouveau thread
            boolean test = true;
            while(test == true) {
            	Message message = topicSubscriber.receive();
            	if(message.getJMSType().equals(pPseudoSuivi))
            	{
            		objectMessage = (ObjectMessage) message;
                    MessageGazouilli messageGazouilli = (MessageGazouilli) objectMessage.getObject();
	                System.out.println("Gazouilli : " + messageGazouilli.toString());
	                test = false;
            	}
            }
            
//            Thread t = new Thread(
//                    new Runnable() {
//        				public void run(Message message) {
//        					 boolean test = true;
//        					 while(test == true) {
//        			         
//        							try {
//        								message = topicSubscriber.receive();
//        							} catch (JMSException e1) {
//        								// TODO Auto-generated catch block
//        								e1.printStackTrace();
//        							}
//        			            	try {
//        								if(message.getJMSType().equals("Toto"))
//        								{
//        									ObjectMessage objectMessage = (ObjectMessage) message;
//        								    MessageGazouilli messageGazouilli = (MessageGazouilli) objectMessage.getObject();
//        								    System.out.println("Gazouilli : " + messageGazouilli.toString());
//        								    test = false;
//        								}
//        							} catch (JMSException e) {
//        								// TODO Auto-generated catch block
//        								e.printStackTrace();
//        							}
//        			            }
//        				}
//
//        				public void run() {
//        					// TODO Auto-generated method stub
//        					
//        				}
//        			});
//                    t.start();
          
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
	
	public static void suppAbonnement(String pPseudoSuivi, String pPseudoAbonne)
	{
        destName = "fileGestProfils";

        try {
            
        	initialize();            
        	
    		TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);

        	MessageAbonnement messageAbonnement = new MessageAbonnement(pPseudoSuivi, pPseudoAbonne);
        	ObjectMessage objectMessage = session.createObjectMessage(messageAbonnement);
        	objectMessage.setJMSReplyTo(temporaryQueue);
        	objectMessage.setJMSType("suppAbonnement");
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messageAbonnement.toString());
        	
        	Message receivedMessage = consumer.receive();
        	setMessageRetour(receivedMessage.toString());
        	System.out.println("received message : " + receivedMessage);
        	
        	//desinscription au topic
        	String topicName = "messagesNonGeo";
            Topic topic = (Topic) context.lookup(topicName); 
            topicSubscriber.close();
        	session.unsubscribe(pPseudoAbonne);
          
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
	
	public static void listeAbonne(String pPseudoAbonne)
	{
        destName = "fileGestProfils";
        String[] listeAbonnement = null;

        try {
            
        	initialize();            
        	
    		TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);
        	
        	MessageListeAbonnement messageListeAbonnement = new MessageListeAbonnement(pPseudoAbonne, listeAbonnement, "");
        	ObjectMessage objectMessage = session.createObjectMessage(messageListeAbonnement);
        	objectMessage.setJMSReplyTo(temporaryQueue);
        	objectMessage.setJMSType("listeAbonne");
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messageListeAbonnement.toString());
        	
        	Message receivedMessage = consumer.receive();
        	ObjectMessage objectMessageRetour = (ObjectMessage) receivedMessage;
        	MessageListeAbonnement messageListeAbonnementRetour = (MessageListeAbonnement) objectMessageRetour.getObject();
        	setMessageRetour(messageListeAbonnementRetour.getMessageRetour().toString());
        	setListeAbonnementMessageRetour(messageListeAbonnementRetour.getListeAbonnement());
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
	
	public static void listeSuivi(String pPseudoSuivi)
	{
        destName = "fileGestProfils";
        String[] listeAbonnement = null;

        try {
            
        	initialize();            
        	
    		TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
        	
        	// create the consumer
        	consumer = session.createConsumer(temporaryQueue);
        	
        	MessageListeAbonnement messageListeAbonnement = new MessageListeAbonnement(pPseudoSuivi, listeAbonnement, "");
        	ObjectMessage objectMessage = session.createObjectMessage(messageListeAbonnement);
        	objectMessage.setJMSReplyTo(temporaryQueue);
        	objectMessage.setJMSType("listeSuivi");
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messageListeAbonnement.toString());
        	
        	Message receivedMessage = consumer.receive();
        	ObjectMessage objectMessageRetour = (ObjectMessage) receivedMessage;
        	MessageListeAbonnement messageListeAbonnementRetour = (MessageListeAbonnement) objectMessageRetour.getObject();
        	setMessageRetour(messageListeAbonnementRetour.getMessageRetour().toString());
        	setListeAbonnementMessageRetour(messageListeAbonnementRetour.getListeAbonnement());
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
	
	public static void creerGazouilliTopic(String pContenu, String pVille, String pPseudoEmetteur)
	{
        //destName = "messagesNonGeo";
        destName = "fileGestProfils";
        
        try {
        		
        		initialize();
            	
            	TemporaryQueue temporaryQueue = session.createTemporaryQueue(); 
            	
            	// create the consumer
            	consumer = session.createConsumer(temporaryQueue);

            	MessageGazouilli messageGazouilli = new MessageGazouilli(pContenu, pVille, pPseudoEmetteur);
            	ObjectMessage objectMessage = session.createObjectMessage(messageGazouilli);
            	objectMessage.setJMSReplyTo(temporaryQueue);
            	objectMessage.setJMSType("creerGazouilli");
            	sender.send(objectMessage);
            	System.out.println("Sent: " + messageGazouilli.toString());
            	
            	Message receivedMessage = consumer.receive();
            	setMessageRetour(receivedMessage.toString());
            	System.out.println("received message : " + receivedMessage);
            	
            	
            	
            	//dans le topic
            	destName = "messagesNonGeo";
            	initialize();
            	objectMessage.setJMSType(pPseudoEmetteur);
            	sender.send(objectMessage);
            	System.out.println("Sent: " + messageGazouilli.toString());
                      
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
