/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.galettedebroons.model;

import fr.galettedebroons.domain.Livraison;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.domain.Temporaire;
import fr.galettedebroons.test.Main;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author	Oumoul
 * @author	Julie Guegnaud
 */

public class RemplissageLivraison {
	
	private EntityManager manager_;
	private Main main_;
	private RecuperationDonnees rd_;
	private ModificationDonnees md_;
	
	public RemplissageLivraison(Main main){
		manager_ = main.getManager();
		main_ = main;
	}     
	
	//Creation d'une vue si ta table temporaire contient quelque chose et calcul des quatites effectivement vendues.
	public void remplissage(){
		rd_ = new RecuperationDonnees(main_);
		md_ = new ModificationDonnees(main_);
		
		// Tri sur les donnees de la table temporaire
		List<Temporaire> temp = manager_.createQuery("select t from Temporaire t order by t.date ASC", Temporaire.class).getResultList();   
		
		for(Temporaire t : temp){
			
			//récuperation des informations
			String bl = t.getBon_livraison();
			String code_profil = t.getCode_profil();
			String code_produit = t.getCode_produit();
			int ql = t.getQuantite_livree();
			int qr = t.getQuantite_reprise();
			Date date = t.getDate();
			
			//recuperation du profil du client
			Profil profil = rd_.recupProfil(code_profil);
			
			//recuperation du produit de la livraison
			Produit produit = rd_.recupProduit(code_produit);
			
			//recuperation des jours de livraison normaux
			String[] joursLivraison = rd_.recupJoursLivraison(profil);
			
			//Jours de la semaine de la livraison actuelle
			String jours = joursSemaine(date);
			
			//On verifie que le jours de la livraison été prévu
			boolean prevu = false;
			for(int i = 0; i<joursLivraison.length; i++)
				if (joursLivraison[i] == jours)
					prevu = true;
			
			//Recuperation de la livraison précédente pour vérifier le nombre de reprise
			Livraison lprec = precedenteLivraison(date, produit, profil);
			int precLivr = lprec.getQte_livraison();
			
			//On effectue les traitements
			if (prevu){
				if (verifQte(precLivr, qr)){
					//On verifie que la donnee n est pas deja presente dans la base
					Livraison livr = rd_.recuperationLivraison(bl, date, code_produit);
					
					if (livr == null)
						ajoutLivraison(bl, profil, produit, date, ql, qr);
					else
						majLivraison(livr, ql, qr);
				}
			}
			else{
				Livraison lprec2 = precedenteLivraison(lprec.getDate_livraison(), produit, profil);
				int qteLivr = lprec2.getQte_livraison() + ql;
				int qteRepris = lprec.getQte_reprise() + qr;
				
				if (verifQte(qteLivr, qteRepris))
					md_.updateLivraison(lprec, qteLivr, qteRepris);
			}
		}
	}
	

	/**
	 * Fonction de decalage de la date
	 * 
	 * @param d : une date
	 * @param nbrjours : le nombre de jours à rajouter à une date
	 * @return la date precedente decaler au nombre de jours voulu
	 */
	public Date ajoutjours(Date d, int nbrjours){
		return new Date(d.getTime() + (nbrjours * (24*3600*1000)));
	}
	
	
	/**
	 * Fonction qui verifie que la quantite livree et la quantite reprise sont coherentes
	 * 
	 * @return true si c'est coherent
	 */
	public boolean verifQte(int qtePlus, int qteMoins){
		return (qtePlus > qteMoins);
	}
	
	
	/**
	 * renvoie le jours de la semaine correspondant à une certaine date
	 * 
	 * @param date
	 * @return le jours de la semaine (String)
	 */
	public String joursSemaine(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int joursSemaine = calendar.get(calendar.DAY_OF_WEEK);
		/* 1=dimanche / 2=lundi / 3=mardi / 4=mercredi / 5=jeudi / 6=vendredi / 7=samedi */
		
		String jours = "";
		switch (joursSemaine){
			case 1:
				jours = "dimanche";
			case 2:
				jours = "lundi";
			case 3:
				jours = "mardi";
			case 4:
				jours = "mercredi";
			case 5:
				jours = "jeudi";
			case 6:
				jours = "vendredi";
			case 7:
				jours = "samedi";
		}
		
		return jours;
	}
	
	
	public Livraison precedenteLivraison(Date date, Produit prod, Profil profil){
		Livraison precLivraison = rd_.recupLivraisonPrec(prod, profil, date);
		return precLivraison;
	}
	
	
	
	public void ajoutLivraison(String bl, Profil profil, Produit produit, Date date, int ql, int qr){
		EntityTransaction tx = manager_.getTransaction();
		tx.begin();
		
		Livraison livr = new Livraison(bl, produit, date, ql, qr);
		
		manager_.persist(livr);
		tx.commit();
		
	}
	
	private void majLivraison(Livraison livr, int ql, int qr) {
		int qteLivr = livr.getQte_livraison() + ql;
		int qteRepris = livr.getQte_reprise() + qr;
		if (verifQte(qteLivr, qteRepris))
			md_.updateLivraison(livr, qteLivr, qteRepris);
	}

}
