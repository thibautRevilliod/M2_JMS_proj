package vues;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

import javax.swing.*;

import listeners.*;


public class VueConnexion extends JFrame
{
	
	private JPanel p0;
	
	private JPanel p1;
		private JTextField barreId;
		private JLabel labelIdentifiant;
		
	private JPanel p2;
		private JTextField barreMdp ;
		private JLabel labelMdp;
		private JLabel sautDeLigne ;

	private JPanel p3;
		private JButton ok;
		private JButton quitter;
	

	public VueConnexion ()
	{
		
		this.setTitle("Connexion");
		this.setLocationRelativeTo(null);
		this.setSize(400,150);
		this.setResizable(false);
		
		this.sautDeLigne = new JLabel (" ");
		

		// création et placement des composants
		

		// les boutons :
		
		
		this.ok = new JButton ("OK");
		this.ok.setPreferredSize(new Dimension (100,25));
		
		this.quitter = new JButton ("Quitter");
		this.quitter.setPreferredSize(new Dimension (100,25));
		
		
		// les barres de saisie: 
		
		this.barreId = new JTextField (20);
		this.barreMdp = new JTextField (20);
		
		// les labels :
		
		this.labelIdentifiant = new JLabel("Entrez votre identifiant : ");
		this.labelMdp = new JLabel("Entrez votre mot de passe : ");
	    
	    // *****************Organisation des composants : ******************
	  
	   
		
	  this.p1= new JPanel ();
		   this.p1.setLayout(new BorderLayout());
		   this.p1.add(sautDeLigne,BorderLayout.NORTH);
		   this.p1.add(this.labelIdentifiant,BorderLayout.WEST); 
		   this.p1.add(this.barreId,BorderLayout.EAST);
		   this.p1.add(sautDeLigne,BorderLayout.SOUTH);
		  
  
	  this.p2= new JPanel ();
		   this.p2.setLayout(new BorderLayout());
		   this.p2.add(sautDeLigne,BorderLayout.NORTH);
		   this.p2.add(this.labelMdp,BorderLayout.WEST); 
		   this.p2.add(this.barreMdp,BorderLayout.EAST);
		   this.p2.add(sautDeLigne,BorderLayout.SOUTH);
		 

			

	   this.p0= new JPanel ();
		   this.p0.add(p1,BorderLayout.NORTH);

		   this.p0.add(p2,BorderLayout.SOUTH);
		   this.add(this.p0,BorderLayout.CENTER);
		   
		this.p3=new JPanel ();	
			this.p3.add(this.ok, BorderLayout.WEST);	 
			this.p3.add(this.quitter,BorderLayout.EAST);				
			this.add(this.p3,BorderLayout.SOUTH);	
			

				
			
		// Abonnements :
		this.ok.addActionListener(new LConnexion (this));
		this.quitter.addActionListener(new LFermer (this));
		this.addWindowListener(new LFermer (this));
				
				
	}	

	
}
