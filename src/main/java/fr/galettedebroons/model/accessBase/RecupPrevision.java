package fr.galettedebroons.model.accessBase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Prevision;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.main.Main;

public class RecupPrevision {

	private EntityManager manager_;
	private Main main_;
	
	public RecupPrevision(Main main){
		manager_ = main.getManager();
		main_ = main;
	}
	
	public Prevision prevision(Profil profil, Produit prod, Date date){
		Prevision prev = manager_.createQuery("select p from Prevision p where date LIKE :date AND " +
				"prevision_profil LIKE :profil AND " +
				"prevision_produit LIKE :produit", Prevision.class)
				.setParameter("profil", profil)
				.setParameter("produit", prod)
				.setParameter("date", date)
				.getSingleResult();
		
		return prev;
	}
	
	/**
	 * Recupere somme des produits à livrer pour la vue QteALivrerTotal
	 */
	public List<Object[]> recupSommeProduit(java.util.Date dateDebut, java.util.Date dateFin){
		List<Object[]> listePrevision = null;
		
		listePrevision = manager_.createQuery("select p.quantite, p.prevision_produit.code_produit "
				+ "from Prevision p where p.date BETWEEN :dateDeb and :dateFi", Object[].class)
				.setParameter("dateDeb", dateDebut)
				.setParameter("dateFi", dateFin)
				.getResultList();
		System.out.println("Ma liste de qte à livrer par produit : " +listePrevision);
		return listePrevision;
	}

	public List<Object[]> recupPrevTournee(String nomTournee, java.util.Date data){
		RecupClientProfil rcp = new RecupClientProfil(main_);
		
		String[] listeTournee = null;
		List<Object[]> listeprev = new ArrayList<Object[]>();
		// recuperer liste des profil des tournees
		listeTournee = rcp.recuperationProfilTournee(nomTournee);
		
		// parcours de chaque profil et recupère prévision
		for(int i=0; i<listeTournee.length; i++){
			listeprev.addAll(manager_.createQuery("select p.quantite, p.prevision_profil.code_client, "
					+ "p.prevision_produit.code_produit from Prevision p where p.date LIKE :date "
					+ "and p.prevision_profil.code_client LIKE :profil"
					, Object[].class)
					.setParameter("date", data)
					.setParameter("profil", listeTournee[i])
					.getResultList());
		}
		return listeprev;
	}
	
	public List<Prevision> recuperationPrevision(java.util.Date data, String tournee){
		RecupClientProfil rcp = new RecupClientProfil(main_);
		
		String[] listProfilTournee = null;
		// recupere liste des profils qui appartiennent à la tournee passé en parametre
		// table tournee
		listProfilTournee = rcp.recuperationProfilTournee(tournee);
		List<Object[]> prev = null;
		List<Prevision> listPrev = new ArrayList<Prevision>();
		
		for(int i=0; i<listProfilTournee.length; i++){
			prev = manager_.createQuery("select p.quantite, p.prevision_profil, "
					+ "p.prevision_produit from Prevision p where p.date LIKE :date ", Object[].class)
					.setParameter("date", data)
					.getResultList();
		}
			
		return listPrev;
	}

	public int countNbProfilPrevision(){
		long count = 0;
		int res = 0;
		count = manager_.createQuery("select count(distinct prevision_profil) as c "
				+"FROM Prevision", Long.class)
				.getSingleResult();
		res = (int) count;
		return res;
	}
	
	public int countNbProduitPrevision(){
		long count = 0;
		int res = 0;
		count = manager_.createQuery("select count(distinct prevision_produit) as c "
				+"FROM Prevision", Long.class)
				.getSingleResult();
		res = (int) count;
		return res;
	}
	
}
