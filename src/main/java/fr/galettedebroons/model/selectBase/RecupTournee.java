package fr.galettedebroons.model.selectBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.domain.Tournee;
import fr.galettedebroons.main.Main;

public class RecupTournee {

	public EntityManager manager_;
	
	public RecupTournee(Main main){
		manager_ = main.getManager();
	}
	
	public List<Tournee> listTournee(){
		List<Tournee> tournee = manager_.createQuery("select t from Tournee t", Tournee.class).getResultList();
		
		return tournee;
	}
	
	public Tournee recuperationTournee(String nom){
		Tournee tournee = manager_.createQuery("select t from Tournee t where nom LIKE :nom", Tournee.class)
				.setParameter("nom", nom)
				.getSingleResult();
		
		return tournee;
	}
	
	public boolean tourneePresente(String nom){
		boolean present;
		try{
			Tournee tournee = manager_.createQuery("select t from Tournee t where nom LIKE :nom", Tournee.class)
					.setParameter("nom", nom)
					.getSingleResult();
			present = true;
		} catch(Exception e) {
			present = false;
		}
		
		return present;
	}
	
	public String[] recuperationJoursTournee(Profil profil){
		String[] joursTournee = new String[7];
		
		//recuperation des tournee
		List<Tournee> listTournee = manager_.createQuery("select t from Tournee t", Tournee.class)
				.getResultList();
		
		int indice = 0;
		for (Tournee t : listTournee){
			if (t.getProfil_tournee().contains(profil)){
				if (t.isLundi()){
					joursTournee[indice] = "lundi";
					indice ++;
				}
				if (t.isMardi()){
					joursTournee[indice] = "mardi";
					indice ++;
				}
				if (t.isMercredi()){
					joursTournee[indice] = "mercredi";
					indice ++;
				}
				if (t.isJeudi()){
					joursTournee[indice] = "jeudi";
					indice ++;
				}
				if (t.isVendredi()){
					joursTournee[indice] = "vendredi";
					indice ++;
				}
				if (t.isSamedi()){
					joursTournee[indice] = "samedi";
					indice ++;
				}
				if (t.isDimanche()){
					joursTournee[indice] = "dimanche";
					indice ++;
				}
			}
		}
		
		return joursTournee;
	}

	public List<Profil> recuperationProfilTournee(String tournee){
		@SuppressWarnings("unchecked")
		Collection<Profil> list = manager_.createQuery("select t.profil_tournee "
				+ "FROM Tournee t WHERE t.nom LIKE :tournee", Collection.class)
				.setParameter("tournee", tournee)
				.getSingleResult();
		
		List<Profil> listProfil = new ArrayList<Profil>();
		
		for (Profil p : list){
			listProfil.add(p);
		}
		
		return listProfil;
	}
}
