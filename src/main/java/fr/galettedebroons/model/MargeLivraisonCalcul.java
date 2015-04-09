package fr.galettedebroons.model;

import java.sql.Date;
import java.util.List;

import fr.galettedebroons.domain.MargeLivraison;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.domain.QuantiteReelle;
import fr.galettedebroons.main.Main;
import fr.galettedebroons.model.selectBase.RecupClientProfil;
import fr.galettedebroons.model.selectBase.RecupMargeLivraison;
import fr.galettedebroons.model.selectBase.RecupProduit;
import fr.galettedebroons.model.selectBase.RecupQuantiteReelle;

public class MargeLivraisonCalcul {

	private Main main_;
	private RecupClientProfil rcl_;
	private RecupProduit rp_;
	private RecupQuantiteReelle rqr_;
	private RecupMargeLivraison rml_;
	
	public MargeLivraisonCalcul(Main main){
		this.main_ = main;
		this.rcl_ = new RecupClientProfil(main_);
		this.rp_ = new RecupProduit(main_);
		this.rqr_ = new RecupQuantiteReelle(main_);
		this.rml_ = new RecupMargeLivraison(main_);
	}
	
	public void calculMarge(){
		Date date = new Date(System.currentTimeMillis());
		
		List<Profil> listProfil = rcl_.recuperationToutProfil();
		List<Produit> listProd = rp_.listTousProduit();
		
		for (Profil profil : listProfil){
			for (Produit produit : listProd){
				//Recuperation de la derniere QteRelle
				try{
					QuantiteReelle qr = rqr_.recuperationPrecQR(profil, produit, date);
					
					Double taux;
					if (qr.isRupture_stock())
						taux = 0.25;
					else
						taux = 0.15;
					
					try{
						MargeLivraison ml = rml_.recuperationML(profil, produit);
						if (ml.isEditable())
							ml.setTaux_reprise(taux);
					} catch (Exception e){
						main_.getTransaction().begin();
						MargeLivraison ml = new MargeLivraison(taux, profil, produit, true);
						main_.getManager().persist(ml);
						main_.getTransaction().commit();
					}
				} catch (Exception e){}
			}
		}
	}
	
}
