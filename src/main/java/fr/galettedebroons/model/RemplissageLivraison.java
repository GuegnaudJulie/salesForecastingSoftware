package fr.galettedebroons.model;

import fr.galettedebroons.domain.Gamme;
import fr.galettedebroons.domain.Livraison;
import fr.galettedebroons.domain.Prevision;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.domain.QuantiteReelle;
import fr.galettedebroons.domain.Temporaire;
import fr.galettedebroons.domain.Tournee;
import fr.galettedebroons.main.Main;
import fr.galettedebroons.model.selectBase.RecupGamme;
import fr.galettedebroons.model.selectBase.RecupLivraison;
import fr.galettedebroons.model.selectBase.RecupPrevision;
import fr.galettedebroons.model.selectBase.RecupQuantiteReelle;
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
 * @since	05/04/2015
 */

public class RemplissageLivraison {
	
	private EntityManager manager_;
	private Main main_;
	private RecuperationDonnees rd_;
	private RecupLivraison rl_;
	private RecupTournee rt_;
	private ModificationDonnees md_;
	private RecupQuantiteReelle rqr_;
	
	public RemplissageLivraison(Main main){
		manager_ = main.getManager();
		main_ = main;
	}
	
	public void remplissage(){
		rd_ = new RecuperationDonnees(main_);
		md_ = new ModificationDonnees(main_);
		rl_ = new RecupLivraison(main_);
		rt_ = new RecupTournee(main_);
		rqr_ = new RecupQuantiteReelle(main_);
		
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
			
			//Vérification de l'existence de la livraison
			Livraison livr;
			try {
				livr = rl_.recuperationLivraison(bl, date, code_produit);
			} catch (Exception e){
				livr = null;
			}
			
			boolean modif = true;
			if (livr == null)
				ajoutLivraison(bl, profil, produit, date, ql, qr);
			else if (livr != null && (livr.getQte_livraison() != ql || livr.getQte_reprise() != qr))
				majLivraison(livr, ql, qr);
			else
				modif = false;
			
			if (modif){
				//recuperation de la gamme du produit
				Gamme gamme = produit.getProduit_gamme();
				
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
				
				//On vérifie qu'il n'y a pas de jours fériés dans cette semaine
				boolean semaineNonFerie = true;
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				int semaineLivr = calendar.get(Calendar.WEEK_OF_YEAR);
				List<Integer> listSemaineFeries = getSemaineFeries(calendar.YEAR);
				if (listSemaineFeries.contains(semaineLivr))
					semaineNonFerie = false;
				
				Date date1 = null;
				Date date2 = null;
				if (gamme.getCode_gamme().equals("PE")){
					date1 = ajoutJours(date, -3);
					date2 = ajoutJours(date, -2);
				}
				else if (gamme.getCode_gamme().equals("LS")){
					date1 = ajoutJours(date, -5);
					date2 = ajoutJours(date, -4);
				}
				QuantiteReelle qrPrec;
				try {
					qrPrec = rqr_.recuperationQR(profil, produit, date1, date2);
				} catch (Exception e) {
					qrPrec = null;
				}
				
				Livraison livrPrec;
				int qteLivr = 0;
				boolean rupture = false;
				try{
					livrPrec = rl_.recupLivraisonPrec(produit, profil, date1, date2);
					qteLivr = livrPrec.getQte_livraison();
					if ((qteLivr - qr) == 0)
						rupture = true;
					
				} catch (Exception e){
					livrPrec = null;
				}
				
				if (prevu && semaineNonFerie){
					Date dateLivrPrec = date;
					if (livrPrec != null)
						dateLivrPrec = livrPrec.getDate_livraison();
						
					if(verifQte(qteLivr, qr)){
						//On ajoute dans la table quantiteReelle
						if (qrPrec == null)
							ajoutQuantiteReelle((qteLivr-qr), profil, produit, dateLivrPrec, rupture);
						else
							majQuantiteReelles(qrPrec, (qteLivr-qr), rupture);
					}
				}
				// si la livraison n'est pas prévu, on l'ajoute à la livraison précédente
				else if (!prevu && semaineNonFerie){
					//Recuperation de la livraison précédente
					if (qrPrec != null)
						majQuantiteReelles(qrPrec, qrPrec.getQuantite(), rupture);
					else
						ajoutQuantiteReelle(ql, profil, produit, date, rupture);
				}	
			} // fin if (crea || modif)
		} // fin for
	}
	
	private void majQuantiteReelles(QuantiteReelle qrPrec, int i, boolean rupture) {
		qrPrec.setQuantite(i);
		qrPrec.setRupture_stock(rupture);
	}

	private void ajoutQuantiteReelle(int i, Profil profil, Produit produit, Date dateLivrPrec, boolean rupture) {
		main_.getTransaction().begin();
		
		QuantiteReelle qr = new QuantiteReelle(i, profil, produit, dateLivrPrec, rupture);
		main_.getManager().persist(qr);
		
		profil.addReel(qr);
		produit.addReel(qr);
		
		main_.getTransaction().commit();
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
	 * @return	la liste des semaines avec un jour férié
	 */
	public List<Integer> getSemaineFeries(int annee) {
		List<Integer> semainesFeries = new ArrayList<Integer>();

		// Jour de l'an
		GregorianCalendar jourAn = new GregorianCalendar(annee, 0, 1);
		int semaineJourAn = jourAn.get(Calendar.WEEK_OF_YEAR);
		semainesFeries.add(semaineJourAn);

		// Lundi de pacques
		GregorianCalendar pacques = calculLundiPacques(annee);
		int semainePacques = pacques.get(Calendar.WEEK_OF_YEAR);
		semainesFeries.add(semainePacques);

		// Fete du travail
		GregorianCalendar premierMai = new GregorianCalendar(annee, 4, 1);
		int semainePremierMai = premierMai.get(Calendar.WEEK_OF_YEAR);
		semainesFeries.add(semainePremierMai);

		// 8 mai
		GregorianCalendar huitMai = new GregorianCalendar(annee, 4, 8);
		int semaineHuitMai = huitMai.get(Calendar.WEEK_OF_YEAR);
		semainesFeries.add(semaineHuitMai);

		// Ascension (= pâques + 38 jours)
		GregorianCalendar ascension = new GregorianCalendar(annee,
				pacques.get(GregorianCalendar.MONTH),
				pacques.get(GregorianCalendar.DAY_OF_MONTH));
		ascension.add(GregorianCalendar.DAY_OF_MONTH, 38);
		int semaineAscension = ascension.get(Calendar.WEEK_OF_YEAR);
		semainesFeries.add(semaineAscension);

		// Pentecôte (= pâques + 49 jours)
		GregorianCalendar pentecote = new GregorianCalendar(annee,
				pacques.get(GregorianCalendar.MONTH),
				pacques.get(GregorianCalendar.DAY_OF_MONTH));
		pentecote.add(GregorianCalendar.DAY_OF_MONTH, 49);
		int semainePentecote = pentecote.get(Calendar.WEEK_OF_YEAR);
		semainesFeries.add(semainePentecote);

		// Fête Nationale
		GregorianCalendar quatorzeJuillet = new GregorianCalendar(annee, 6, 14);
		int semaineQuatorzeJuillet = quatorzeJuillet.get(Calendar.WEEK_OF_YEAR);
		semainesFeries.add(semaineQuatorzeJuillet);

		// Assomption
		GregorianCalendar assomption = new GregorianCalendar(annee, 7, 15);
		int semaineAssomption = assomption.get(Calendar.WEEK_OF_YEAR);
		semainesFeries.add(semaineAssomption);

		// La Toussaint
		GregorianCalendar toussaint = new GregorianCalendar(annee, 10, 1);
		int semaineToussaint = toussaint.get(Calendar.WEEK_OF_YEAR);
		semainesFeries.add(semaineToussaint);

		// L'Armistice
		GregorianCalendar armistice = new GregorianCalendar(annee, 10, 11);
		int semaineArmistice = armistice.get(Calendar.WEEK_OF_YEAR);
		semainesFeries.add(semaineArmistice);

		// Noël
		GregorianCalendar noel = new GregorianCalendar(annee, 11, 25);
		int semaineNoel = noel.get(Calendar.WEEK_OF_YEAR);
		semainesFeries.add(semaineNoel);

		return semainesFeries;
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
		
		produit.addLivraison(livr);
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