/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.galettedebroons.model;

import fr.galettedebroons.domain.Gamme;
import fr.galettedebroons.domain.Livraison;
import fr.galettedebroons.domain.Prevision;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.domain.Temporaire;
import fr.galettedebroons.domain.Tournee;
import fr.galettedebroons.main.Main;
import fr.galettedebroons.model.selectBase.RecupGamme;
import fr.galettedebroons.model.selectBase.RecupLivraison;
import fr.galettedebroons.model.selectBase.RecupPrevision;
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
	private RecupPrevision rprev_;
	private ModificationDonnees md_;
	
	public RemplissageLivraison(Main main){
		manager_ = main.getManager();
		main_ = main;
	}     
	
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

	public void remplissage2(){
		rd_ = new RecuperationDonnees(main_);
		md_ = new ModificationDonnees(main_);
		rl_ = new RecupLivraison(main_);
		rt_ = new RecupTournee(main_);
		rprev_ = new RecupPrevision(main_);
		
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
			
			//recuperation de la gamme du produit
			Gamme gamme = produit.getProduit_gamme();
			
			//recuperation des jours de livraison normaux
			Tournee tournee = profil.getProfil_tournee();
			String[] joursTournee = rt_.recuperationJoursTournee(profil);
			
			//Jours de la semaine de la livraison actuelle
			String jours = joursSemaine(date);
			
			//On verifie que le jours de la livraison été prévu
			boolean prevu = false;
			for(int i = 0; i<joursTournee.length; i++){
				if (joursTournee[i] == jours)
					prevu = true;
			}
			
			if (gamme.getCode_gamme().equals("PE")){
				if(prevu){
					Date dateLivrPrec = ajoutJours(date, -2);
					List<Livraison> listPrecLivr = rl_.recupLivraisonPrec(produit, profil, date, dateLivrPrec);
					int qteLivPrec = 0;
					for (Livraison l : listPrecLivr){
						qteLivPrec += l.getQte_livraison();
					}
					
					if(verifQte(qteLivPrec, qr)){
						Livraison livr = rl_.recuperationLivraison(bl, date, code_produit);
						
						if (livr == null){
							ajoutLivraison(bl, profil, produit, date, ql, qr);
							ajoutPrevision((qteLivPrec-qr), profil, produit, dateLivrPrec);
						}
						else{
							majLivraison(livr, ql, qr);
							Prevision prev;
							try{
								prev = rprev_.prevision(profil, produit, dateLivrPrec) ;
								majPrevision(prev, qteLivPrec-qr);
							} catch (Exception e){}
								
						}
					}
				}
				else{
					//Recuperation de la livraison précédente
					Livraison lprec = precedenteLivraison(date, produit, profil);
					if (lprec != null){
						majLivraison(lprec, lprec.getQte_livraison() + ql, 0);
					}
					else{
						ajoutLivraison(bl, profil, produit, date, ql, 0);
					}
				}
			}
			
			else if (gamme.getCode_gamme().equals("LS")){
				//Recuperation des jours de la tournée
				if (tournee.isLundi() && tournee.isMercredi() && tournee.isVendredi()){
					//On vérifie qu'elle été prévue
					if (prevu){
						if (jours.equals("lundi")){
							//On récupère les invendu du vendredi prec (=>-3) et du mercredi (=>-5)
							Date dateVendredi = ajoutJours(date, -3);
							Date dateMercredi = ajoutJours(date, -5);
							Livraison livrVendredi = rl_.recupLivraison(produit, profil, dateVendredi);
							Livraison livrMercredi = rl_.recupLivraison(produit, profil, dateMercredi);
							
							//On fait la somme des qte livré
							int sommeLivr = 0;
							if (livrVendredi != null && livrMercredi != null)
								sommeLivr = livrVendredi.getQte_livraison() + livrMercredi.getQte_livraison();
							else if (livrVendredi != null)
								sommeLivr = livrVendredi.getQte_livraison();
							else if	(livrMercredi != null)
								sommeLivr = livrMercredi.getQte_livraison();
								
							//On vérifie qu'il n'y a pas d'erreur sur les quantites
							if(verifQte(sommeLivr, qr)){
								int qteRDivise = (int) qr/2;
								
								Livraison livr = rl_.recuperationLivraison(bl, date, code_produit);
								
								if (livr == null){
									ajoutLivraison(bl, profil, produit, date, ql, qr);
									
									if (livrVendredi != null)
										ajoutPrevision((sommeLivr-qteRDivise), profil, produit, livrVendredi.getDate_livraison());
									if (livrMercredi != null)
										ajoutPrevision((sommeLivr-qteRDivise), profil, produit, livrMercredi.getDate_livraison());
								}
								else{
									majLivraison(livr, ql, qr);
									
									Prevision prevVendredi;
									try{
										prevVendredi = rprev_.prevision(profil, produit, dateVendredi) ;
										majPrevision(prevVendredi, sommeLivr-qteRDivise);
									} catch (Exception e){}
									
									Prevision prevMercredi;
									try{
										prevMercredi = rprev_.prevision(profil, produit, dateMercredi) ;
										majPrevision(prevMercredi, sommeLivr-qteRDivise);
									} catch (Exception e){}
									
								}
							}
						}
						else if (jours.equals("mercredi")){
							//On récupère les invendu du vendredi prec => -5j
							Date dateVendredi = ajoutJours(date, -5);
							Livraison livrVendredi = rl_.recupLivraison(produit, profil, dateVendredi);
							
							int qteLivr = 0;
							if (livrVendredi != null)
								qteLivr = livrVendredi.getQte_livraison();
							
							//On vérifie qu'il n'y a pas d'erreur sur les quantites
							if(verifQte(qteLivr, qr)){
								Livraison livr = rl_.recuperationLivraison(bl, date, code_produit);
								
								if (livr == null){
									ajoutLivraison(bl, profil, produit, date, ql, qr);
									if(livrVendredi != null)
										ajoutPrevision((qteLivr-qr), profil, produit, livrVendredi.getDate_livraison());
								}
								else{
									majLivraison(livr, ql, qr);
									
									Prevision prevVendredi;
									try{
										prevVendredi = rprev_.prevision(profil, produit, dateVendredi) ;
										majPrevision(prevVendredi, qteLivr-qr);
									} catch (Exception e){}
								}
							}
						}
						else if (jours.equals("vendredi")){
							//On récupère les invendu du lundi prec => -4j
							Date dateLundi = ajoutJours(date, -4);
							Livraison livrLundi = rl_.recupLivraison(produit, profil, dateLundi);
							
							int qteLivr = 0;
							if (livrLundi != null)
								qteLivr = livrLundi.getQte_livraison();
								
							//On vérifie qu'il n'y a pas d'erreur sur les quantites
							if(verifQte(qteLivr, qr)){
								//Si le bl n'existe pas déjà, on le créer
								Livraison livr = rl_.recuperationLivraison(bl, date, code_produit);
								
								if (livr != null){
									ajoutLivraison(bl, profil, produit, date, ql, qr);
									if (livrLundi != null)
										ajoutPrevision((qteLivr-qr), profil, produit, livrLundi.getDate_livraison());
								}
								else{
									majLivraison(livr, ql, qr);
									
									Prevision prevLundi;
									try{
										prevLundi = rprev_.prevision(profil, produit, dateLundi) ;
										majPrevision(prevLundi, qteLivr-qr);
									} catch (Exception e){}
								}
							}
						}
					}
					else{
						//si livraison pas prévu, on rajoute à la précédente livraison
						Livraison lprec = precedenteLivraison(date, produit, profil);
						if (lprec != null){
							majLivraison(lprec, lprec.getQte_livraison() + ql, 0);
						}
						else{
							ajoutLivraison(bl, profil, produit, date, ql, 0);
						}
					}
				}
				else if (tournee.isMardi() && tournee.isVendredi()){
					//On vérifie qu'elle été prévue
					if (prevu){
						if (jours.equals("mardi")){
							//On récupère les invendu du Mardi prec (=>-7) et du vendredi (=>-4)
							Date dateMardiPrec = ajoutJours(date, -7);
							Date dateVendredi = ajoutJours(date, -4);
							Livraison livrMardiPrec = rl_.recupLivraison(produit, profil, dateMardiPrec);
							Livraison livrVendredi = rl_.recupLivraison(produit, profil, dateVendredi);
							
							//On fait la somme des qte livré
							int sommeLivr = 0;
							if (livrVendredi != null && livrMardiPrec != null)
								sommeLivr = livrVendredi.getQte_livraison() + livrMardiPrec.getQte_livraison();
							else if (livrVendredi != null)
								sommeLivr = livrVendredi.getQte_livraison();
							else if	(livrMardiPrec != null)
								sommeLivr = livrMardiPrec.getQte_livraison();
							
							//On vérifie qu'il n'y a pas d'erreur sur les quantites
							if(verifQte(sommeLivr, qr)){
								int qteRDivise = (int) qr/2;
								
								Livraison livr = rl_.recuperationLivraison(bl, date, code_produit);
								
								if (livr == null){
									ajoutLivraison(bl, profil, produit, date, ql, qr);

									if (livrMardiPrec != null)
										ajoutPrevision((sommeLivr-qteRDivise), profil, produit, livrMardiPrec.getDate_livraison());
									if (livrVendredi != null)
										ajoutPrevision((sommeLivr-qteRDivise), profil, produit, livrVendredi.getDate_livraison());
								}
								else{
									majLivraison(livr, ql, qr);
									
									Prevision prevVendredi;
									try{
										prevVendredi = rprev_.prevision(profil, produit, dateVendredi) ;
										majPrevision(prevVendredi, sommeLivr-qteRDivise);
									} catch (Exception e){}
									
									Prevision prevMardi;
									try{
										prevMardi = rprev_.prevision(profil, produit, dateMardiPrec) ;
										majPrevision(prevMardi, sommeLivr-qteRDivise);
									} catch (Exception e){}
								}
							}
						}
					}
					else{
						//si livraison pas prévu, on rajoute à la précédente livraison
						Livraison lprec = precedenteLivraison(date, produit, profil);
						if (lprec != null){
							majLivraison(lprec, lprec.getQte_livraison() + ql, 0);
						}
						else{
							ajoutLivraison(bl, profil, produit, date, ql, 0);
						}
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
	 * 
	 * @param annee
	 * @return	la liste des jours fériés
	 */
	public List<Date> getJourFeries(int annee) {
		List<Date> datesFeries = new ArrayList<Date>();

		// Jour de l'an
		GregorianCalendar jourAn = new GregorianCalendar(annee, 0, 1);
		datesFeries.add(new Date(jourAn.getTimeInMillis()));

		// Lundi de pacques
		GregorianCalendar pacques = calculLundiPacques(annee);
		datesFeries.add(new Date(pacques.getTimeInMillis()));

		// Fete du travail
		GregorianCalendar premierMai = new GregorianCalendar(annee, 4, 1);
		datesFeries.add(new Date(premierMai.getTimeInMillis()));

		// 8 mai
		GregorianCalendar huitMai = new GregorianCalendar(annee, 4, 8);
		datesFeries.add(new Date(huitMai.getTimeInMillis()));

		// Ascension (= pâques + 38 jours)
		GregorianCalendar ascension = new GregorianCalendar(annee,
				pacques.get(GregorianCalendar.MONTH),
				pacques.get(GregorianCalendar.DAY_OF_MONTH));
		ascension.add(GregorianCalendar.DAY_OF_MONTH, 38);
		datesFeries.add(new Date(ascension.getTimeInMillis()));

		// Pentecôte (= pâques + 49 jours)
		GregorianCalendar pentecote = new GregorianCalendar(annee,
				pacques.get(GregorianCalendar.MONTH),
				pacques.get(GregorianCalendar.DAY_OF_MONTH));
		pentecote.add(GregorianCalendar.DAY_OF_MONTH, 49);
		datesFeries.add(new Date(pentecote.getTimeInMillis()));

		// Fête Nationale
		GregorianCalendar quatorzeJuillet = new GregorianCalendar(annee, 6, 14);
		datesFeries.add(new Date(quatorzeJuillet.getTimeInMillis()));

		// Assomption
		GregorianCalendar assomption = new GregorianCalendar(annee, 7, 15);
		datesFeries.add(new Date(assomption.getTimeInMillis()));

		// La Toussaint
		GregorianCalendar toussaint = new GregorianCalendar(annee, 10, 1);
		datesFeries.add(new Date(toussaint.getTimeInMillis()));

		// L'Armistice
		GregorianCalendar armistice = new GregorianCalendar(annee, 10, 11);
		datesFeries.add(new Date(armistice.getTimeInMillis()));

		// Noël
		GregorianCalendar noel = new GregorianCalendar(annee, 11, 25);
		datesFeries.add(new Date(noel.getTimeInMillis()));

		return datesFeries;
	}
	
	public GregorianCalendar calculLundiPacques(int annee) {
		int a = annee / 100;
		int b = annee % 100;
		int c = (3 * (a + 25)) / 4;
		int d = (3 * (a + 25)) % 4;
		int e = (8 * (a + 11)) / 25;
		int f = (5 * a + b) % 19;
		int g = (19 * f + c - e) % 30;
		int h = (f + 11 * g) / 319;
		int j = (60 * (5 - d) + b) / 4;
		int k = (60 * (5 - d) + b) % 4;
		int m = (2 * j - k - g + h) % 7;
		int n = (g - h + m + 114) / 31;
		int p = (g - h + m + 114) % 31;
		int jour = p + 1;
		int mois = n;

		GregorianCalendar date = new GregorianCalendar(annee, mois - 1, jour);
		date.add(GregorianCalendar.DAY_OF_MONTH, 1);
		return date;
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
		try{
			produit.addLivraison(livr);
		} catch (Exception e){
			List<Livraison> listProd = new ArrayList<Livraison>();
			listProd.add(livr);
			produit.setLivraison_produit(listProd);
		}
		
		//liaison à profil
		try{
			profil.addLivraison(livr);
		} catch (Exception e){
			List<Livraison> listProf = new ArrayList<Livraison>();
			listProf.add(livr);
			profil.setLivraison_profil(listProf);
		}
		
		
	}
	
	private void ajoutPrevision(int i, Profil profil, Produit produit, Date dateLivrPrec) {
		EntityTransaction tx = main_.getTransaction();
		tx.begin();
		
		// Création de la livraison
		Prevision prev = new Prevision(i, profil, produit, dateLivrPrec);
		manager_.persist(prev);
		tx.commit();
		
		//liaison à produit
		try{
			produit.addPrevision(prev);
		} catch (Exception e){
			List<Prevision> listPrev = new ArrayList<Prevision>();
			listPrev.add(prev);
			produit.setPrevision_produit(listPrev);
		}
		
		//liaison à profil
		try{
			profil.addPrevision(prev);
		} catch (Exception e){
			List<Prevision> listPrev = new ArrayList<Prevision>();
			listPrev.add(prev);
			profil.setPrevision_profil(listPrev);
		}
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
	
	private void majPrevision(Prevision prev, int qte) {
		prev.setQuantite(qte);
	}

}