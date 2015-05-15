package fr.galettedebroons.model.accessBase;

import java.util.List;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.main.Main;

public class RecupProduit {

	public EntityManager manager_;
	
	public RecupProduit(Main main){
		manager_ = main.getManager();
	}
	
	public List<Produit> listTousProduit(){
		List<Produit> prod = manager_.createQuery("select p FROM Produit p", Produit.class).getResultList();
		
		return prod;
	}
	
	public Produit recupProduit(String code_produit) {
		Produit prod =  manager_.createQuery("select p from Produit p WHERE " +
				"code_produit LIKE :produit", Produit.class)
				.setParameter("produit", code_produit)
				.getSingleResult();
		
		return prod;
	}
	
	public String[] recuperationProduit(){
		List<String> listProduit = manager_.createQuery("select p.code_produit " +
				"from Produit p ", String.class).getResultList();
		String[] produit = new String[listProduit.size()];
		int i = 0;
		for (String g : listProduit){
			produit[i] = g;
			i++;
		}		
		return produit;
	}
	
	public List<Object[]> recuperationCodeProduit(){
		List<Object[]> produit = manager_.createQuery("select p.code_produit, p.nom_produit " +
				"from Produit p ", Object[].class).getResultList();
		return produit;
	}
	
	public Produit recuperationProduitComp(String codeProd){
		System.out.println("mon produit : " +codeProd);
		Produit produit = manager_.createQuery("select p from Produit p where code_produit LIKE :cp ", Produit.class)
				.setParameter("cp", codeProd)
				.getSingleResult();
		return produit;
	}

}
