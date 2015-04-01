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
import fr.galettedebroons.main.Main;
import fr.galettedebroons.model.selectBase.RecupGamme;
import fr.galettedebroons.model.selectBase.RecupLivraison;
import fr.galettedebroons.model.selectBase.RecupTournee;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * @author	Julie Guegnaud
 * @since	31/03/2015
 */

public class RemplissageLivraison {
	
	private EntityManager manager_;
	private Main main_;
	private RecuperationDonnees rd_;
	private RecupLivraison rl_;
	private RecupTournee rt_;
	private ModificationDonnees md_;
	
	public RemplissageLivraison(Main main){
		manager_ = main.getManager();
		main_ = main;
	}     
	
	/**
	 * Remplissage de la table livraison
	 */
	public void remplissage(){
		
		rd_ = new RecuperationDonnees(main_);
		md_ = new ModificationDonnees(main_);
		rl_ = new RecupLivraison(main_);
		rt_ = new RecupTournee(main_);
		
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
			String[] joursTournee = rt_.recuperationJoursTournee(profil);
			
			//Jours de la semaine de la livraison actuelle
			String jours = joursSemaine(date);
			
			//On verifie que le jours de la livraison été prévu
			boolean prevu = false;
			for(int i = 0; i<joursTournee.length; i++){
				if (joursTournee[i] == jours)
					prevu = true;
			}
			
			//Recuperation duree entre un dépot et une reprise
			RecupGamme rg = new RecupGamme(main_);
			int nbJoursValidite = rg.recuperationValiditeGamme(produit.getProduit_gamme());
			
			//Recuperation de la quatite precedement livree
			int precLivr = sommeQteLivrPrecedente(date, nbJoursValidite, produit, profil);
			
			//On vérifie que la quantité reprise est correct par rapport à la livraison
			if (verifQte(precLivr, qr)){
				if (prevu){
					//On verifie que la donnee n est pas deja presente dans la base
					Livraison livr = rl_.recuperationLivraison(bl, date, code_produit);
					
					if (livr == null){
						ajoutLivraison(bl, profil, produit, date, ql, qr);
					}
					else{
						majLivraison(livr, ql, qr);
					}
				}
				else{
					//Recuperation de la livraison précédente
					Livraison lprec = precedenteLivraison(date, produit, profil);
					if (lprec != null){
						majLivraison(lprec, ql, qr);
					}
					else{
						ajoutLivraison(bl, profil, produit, date, ql, qr);
					}	
				}
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
	public Date ajoutJours(Date d, int nbrjours){
		return new Date(d.getTime() + (nbrjours * (24*3600*1000)));
	}
	
	/**
	 * Fonction qui verifie que la quantite livree et la quantite reprise sont coherentes
	 * 
	 * @return true si c'est coherent
	 */
	public boolean verifQte(int qtePlus, int qteMoins){
		return (qtePlus >= qteMoins);
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
		
		int joursSemaine = calendar.get(Calendar.DAY_OF_WEEK);
		/* 1=dimanche / 2=lundi / 3=mardi / 4=mercredi / 5=jeudi / 6=vendredi / 7=samedi */
		
		String jours = "";
		switch (joursSemaine){
			case 1:
				jours = "dimanche";
				break;
			case 2:
				jours = "lundi";
				break;
			case 3:
				jours = "mardi";
				break;
			case 4:
				jours = "mercredi";
				break;
			case 5:
				jours = "jeudi";
				break;
			case 6:
				jours = "vendredi";
				break;
			case 7:
				jours = "samedi";
				break;
		}
		
		return jours;
	}
	
	/**
	 * Recupération de la livraison précédentes
	 * 
	 * @param date : date de la livraison à ajouter
	 * @param prod : produit de la livraison à ajouter
	 * @param profil : profil du client lié à la livraison à ajouter
	 * @return la derniere livraison concernant le produit et le client avant la date indiquée
	 */
	public Livraison precedenteLivraison(Date date, Produit prod, Profil profil){
		Livraison precLivraison = rl_.recupLivraisonPrec(prod, profil, date);
		return precLivraison;
	}
	
	/**
	 * Somme des quantites livrées entre le jour d'une livraison et le jour de la reprise
	 * 
	 * @param date : date de la reprise
	 * @param duree : duree de validite d'un produit chez un client
	 * @param prod : produit associé à la livraison
	 * @param profil : profil du client associé à la livraison
	 * @return la somme des quantites livrées précédement
	 */
	public int sommeQteLivrPrecedente(Date date, int duree, Produit prod, Profil profil){
		Date dateLivr = ajoutJours(date, -duree);
		List<Livraison> list = rl_.recupLivraisonPrec(prod, profil, date, dateLivr);
		
		int sommeLivr = 0;
		for (Livraison l : list){
			sommeLivr += l.getQte_livraison();
		}
		
		return sommeLivr;
	}
	
	/**
	 * Insertion de la livraison en base de donnee
	 * 
	 * @param bl : bon de livraison
	 * @param profil : profil du client associé à la livraison
	 * @param produit : produit associé à la livraison
	 * @param date : date associé à la livraison
	 * @param ql : quantité livrée
	 * @param qr : quantité reprise
	 */
	public void ajoutLivraison(String bl, Profil profil, Produit produit, Date date, int ql, int qr){
		EntityTransaction tx = main_.getTransaction();
		tx.begin();
		
		// Création de la livraison
		Livraison livr = new Livraison(bl, produit, profil, date, ql, qr);
		livr.setLivraison_profil(profil);
		manager_.persist(livr);
		tx.commit();
		
		//liaison à produit
		List<Livraison> listProd = new ArrayList<Livraison>();
		try{
			listProd = produit.getLivraison_produit();
		} catch (Exception e){
			produit.setLivraison_produit(listProd);
		}
		produit.addLivraison(livr);
		
		//liaison à profil
		List<Livraison> listProf = new ArrayList<Livraison>();
		try{
			listProf = profil.getLivraison_profil();
		} catch (Exception e){
			profil.setLivraison_profil(listProf);
		}
		profil.addLivraison(livr);
		
	}
	
	/**
	 * Mise à jour d'une ligne de la table Livraison
	 * 
	 * @param livr : la livraison à mettre à jour
	 * @param ql : quantité livrée supplémentaire
	 * @param qr : quantité reprise supplémentaire
	 */
	private void majLivraison(Livraison livr, int ql, int qr) {
		int qteLivr = 0;
		int qteRepris = 0;
		
		if (ql == 0)
			qteLivr = livr.getQte_livraison();
		else
			qteLivr = ql;
		
		if (qr == 0)
			qteRepris = livr.getQte_livraison();
		else
			qteRepris = qr;
		
		md_.updateLivraison(livr, qteLivr, qteRepris);
	}

}
