/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.galettedebroons.controller;

import fr.galettedebroons.domain.Livraison;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.domain.Temporaire;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 *
 * @author Oumoul
 */
public class RemplissageLivraison {
    private EntityManager manager_;
private EntityManagerFactory factory_;
public RemplissageLivraison(){
    factory_ = Persistence.createEntityManagerFactory("chargement");
		manager_ = factory_.createEntityManager();
}        
 //Creation d'une vue si ta table temporaire contient quelque chose et calcul des quatités effectivement vendues.

public void remplissage(){   
    EntityTransaction tx = manager_.getTransaction(); 
    // Tri sur les données de la table temporaire.
    String req="select t from Temporaire t order by t.date";
    Query q=manager_.createQuery(req);
   List<Temporaire> temp = (ArrayList<Temporaire>)q.getResultList();   
    for(Temporaire t:temp){
       //Récupération de certaines valeurs de la table: le BL,le code_profil, le code_Produit et la quantité livrée.
               
        //Test pour voir si on récupere bien les bonnes valeurs.
                System.out.print(t.getBon_livraison()+" "); 
                System.out.print(t.getDate()+" ");
                 System.out.print(t.getCode_profil()+" "); 
                 System.out.print(t.getNom_client()+" ");
                 System.out.print(t.getCode_produit()+" ");
                 System.out.print(t.getQuantite_livree()+" ");
                 System.out.print(t.getQuantite_reprise());
                 System.out.println();
                 //Récuperation des informations pour le calcul de la quantité effective.
                 String bl=t.getBon_livraison();
                 String code_profil=t.getCode_profil();
                 String code_produit=t.getCode_produit();
                 int ql=t.getQuantite_livree();
                java.sql.Date d1;
                 //Teste sur le type du produit pour pouvoir ajouter la durée de vie et récupérer la date de reprise et la quantité reprise.
                 if(code_produit.indexOf('P')!=-1){
                     System.out.println(code_produit + " contient P");
                    d1= ajoutjours(t.getDate(), 2);System.out.println("Date "+d1);
                    int j=0;
                    while(j<temp.size()){                        
                        if(d1.compareTo(temp.get(j).getDate())==0){
                          String code_cli=temp.get(j).getCode_profil(); 
                           String code_prod=temp.get(j).getCode_produit();
                           System.out.println("ceci est "+code_cli+" "+code_prod);
                           if(code_profil == null ? code_cli == null : code_profil.equals(code_cli)){                                
                                if(code_produit == null ? code_prod == null : code_produit.equals(code_prod)){
                                  Livraison l=new Livraison(); 
                                  //Pour la colonne bon de livraison
                                  String b=bl+","+temp.get(j).getBon_livraison();
                                  l.setBon_livraison(b);
                                  //Pour la colonne profil
                                  Query re=manager_.createQuery("select p from Profil p where p.code_client = :cdp");
                                  re.setParameter("cdp", code_profil);
                                  Profil pro=(Profil)re.getSingleResult();
                                  l.setLivraison_profil(pro);
                                  
                                  //Pour la colonne produit
                                  Query re1=manager_.createQuery("select prod from Produit prod where prod.code_produit = :prc");
                                  re1.setParameter("prc", code_produit);
                                  Produit prod=(Produit)re1.getSingleResult();
                                  l.getLivraison_produit().add(prod);
                                  
                                  //Pour la colonne date
                                  l.setDate_livraison(d1);
                                  
                                  //Pour la colonne quantité livrée
                                  l.setQte_livraison(ql);
                                  
                                  //Pour la colonne quantité effectivement vendue.
                                  int qte=ql-temp.get(j).getQuantite_reprise();
                                  l.setQte_eff_vendue(qte);
                                  manager_.persist(l);                                 
                                  
                                }
                               
                           }
                           
                           
                           
                        }
                        
                        j++;
                    }
                 }                 
                /* else{
                   System.out.println(code_produit + " est un produit de type LS");  
                    d1= ajoutjours(t.getDate(), 5);System.out.println("Date "+d1);  
                 } */ 
                         
                         
                         
                         
                         
    }       
  
        
   
}
        
public java.sql.Date ajoutjours(java.sql.Date d, int nbrjours){
   GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(d);
        gc.add(GregorianCalendar.DAY_OF_MONTH, nbrjours);
        java.sql.Date sqlDate = new java.sql.Date(gc.getTimeInMillis()); 
   return sqlDate;
}




  
 	  
 }         
        
        
        
        

