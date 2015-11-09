package jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import bdd.JmsJDBC;
import metier.Gazouilli;
import metier.MessageInscription;

public class ServeurTwitter {
	
	public static void inscription(String pseudo, String motDePasse, String nom, String prenom, String ville)
	{
        Context context = null;
        ConnectionFactory factory = null;
        Connection connection = null;
        String factoryName = "ConnectionFactory";
        String destName = null;
        Destination dest = null;
        Session session = null;
        MessageProducer sender = null;

        destName = "fileGestProfils";


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
 

        	MessageInscription messageInscription = new MessageInscription(pseudo, motDePasse, nom, prenom, ville);
        	ObjectMessage objectMessage = session.createObjectMessage(messageInscription);
        	sender.send(objectMessage);
        	System.out.println("Sent: " + messageInscription.toString());
            
          
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
	
	
	public static void creerGazouilliTopic()
	{
        Context context = null;
        ConnectionFactory factory = null;
        Connection connection = null;
        String factoryName = "ConnectionFactory";
        String destName = null;
        Destination dest = null;
        Session session = null;
        MessageProducer sender = null;
        String text = "Message ";

        if (args.length < 1 || args.length > 2) {
            System.out.println("usage: Sender <destination> [count]");
            System.exit(1);
        }

        destName = "messagesNonGeo";
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
            
            String vContenu = "Bonjour contenu";
            String vDate = "";
            String vHeure = "";
            String vVille = "Toulouse";
            int vIdEmetteur = 1;
            
            while(true) {
            	Gazouilli gazouilli = new Gazouilli(vContenu, vDate, vHeure, vVille, vIdEmetteur);
            	gazouilli.
            }

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
            
            
            
            // instanciation de la BD 
            JmsJDBC bdd = new JmsJDBC("JMS");
            
            bdd.creerGazouilli(vContenu, vVille, vIdEmetteur);
            
            
            
            
          
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
            JmsJDBC bdd = new JmsJDBC("JMS");
            
            bdd.creerGazouilli(vContenu, vVille, vIdEmetteur);
            
            Gazouilli gazouilli = new Gazouilli(vvContenu, vDate, vHeure, vVille, vEmetteur);
            
            
          
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
