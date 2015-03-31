package fr.galettedebroons.model.selectBase;

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
	
	public Tournee recuperationTournee(String nom){
		Tournee tournee = manager_.createQuery("select t from Tournee t where nom LIKE :nom", Tournee.class)
				.setParameter("nom", nom)
				.getSingleResult();
		
		return tournee;
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
}
