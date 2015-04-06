package fr.galettedebroons.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produit {
	
	private String code_produit;
	private String nom_produit;
	private String presentation_produit;
	private Gamme produit_gamme;
	private int qte_lot;
	private String nature_produit;
	private List<Livraison> livraison_produit;
	private List<MargeLivraison> marge_produit;
	private List<Prevision> prevision_produit;
	private List<QuantiteReelle> reel_produit;
	
	public Produit(){}
	
	public Produit(String code_produit, String nature_produit, String nom_produit, String presentation_produit, Gamme code_gamme, int qte_lot) {
		this.code_produit = code_produit;
		this.nature_produit = nature_produit;
		this.nom_produit = nom_produit;
		this.presentation_produit = presentation_produit;
		this.produit_gamme = code_gamme;
		this.qte_lot = qte_lot;
	}
	
	public Produit(String code_produit, String nature_produit, String nom_produit, String presentation_produit, Gamme code_gamme, int qte_lot, 
			List<Livraison> livraison_produit, List<Prevision> prevision_produit, List<QuantiteReelle> reel_produit) {
		this.code_produit = code_produit;
		this.nature_produit = nature_produit;
		this.nom_produit = nom_produit;
		this.presentation_produit = presentation_produit;
		this.produit_gamme = code_gamme;
		this.qte_lot = qte_lot;
		this.livraison_produit = livraison_produit;
		this.prevision_produit = prevision_produit;
		this.reel_produit = reel_produit;
	}
	
	@Id
	public String getCode_produit() {
		return code_produit;
	}
	
	public void setCode_produit(String code_produit) {
		this.code_produit = code_produit;
	}
	
	public String getNom_produit() {
		return nom_produit;
	}
	
	public void setNom_produit(String nom_produit) {
		this.nom_produit = nom_produit;
	}
	
	public String getPresentation_produit() {
		return presentation_produit;
	}
	
	public void setPresentation_produit(String presentation_produit) {
		this.presentation_produit = presentation_produit;
	}
	
	@ManyToOne
	public Gamme getProduit_gamme() {
		return produit_gamme;
	}
	
	public void setProduit_gamme(Gamme code_gamme) {
		this.produit_gamme = code_gamme;
	}
	
	public int getQte_lot() {
		return qte_lot;
	}
	
	public void setQte_lot(int qte_lot) {
		this.qte_lot = qte_lot;
	}
	
	public String getNatureProduit() {
		return nature_produit;
	}
	
	public void setNatureProduit(String nature_produit) {
		this.nature_produit = nature_produit;
	}
	
	@OneToMany(mappedBy="livraison_produit", cascade=CascadeType.PERSIST)
	public List<Livraison> getLivraison_produit() {
		return livraison_produit;
	}
	
	public void setLivraison_produit(List<Livraison> livraison_produit) {
		this.livraison_produit = livraison_produit;
	}
	
	public void addLivraison(Livraison livraison){
		livraison_produit.add(livraison);
	}
	
	@OneToMany(mappedBy="marge_produit", cascade=CascadeType.PERSIST)
	public List<MargeLivraison> getMarge_produit() {
		return marge_produit;
	}

	public void setMarge_produit(List<MargeLivraison> marge_produit) {
		this.marge_produit = marge_produit;
	}
	
	public void addMarge(MargeLivraison marge){
		marge_produit.add(marge);
	}

	@OneToMany(mappedBy="prevision_produit", cascade=CascadeType.PERSIST)
	public List<Prevision> getPrevision_produit() {
		return prevision_produit;
	}

	public void setPrevision_produit(List<Prevision> prevision_produit) {
		this.prevision_produit = prevision_produit;
	}
	
	public void addPrevision(Prevision previson){
		prevision_produit.add(previson);
	}

	@OneToMany(mappedBy="reel_produit", cascade=CascadeType.PERSIST)
	public List<QuantiteReelle> getReel_produit() {
		return reel_produit;
	}

	public void setReel_produit(List<QuantiteReelle> reel_produit) {
		this.reel_produit = reel_produit;
	}
	
	public void addReel(QuantiteReelle reel){
		reel_produit.add(reel);
	}
}
