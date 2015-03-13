package fr.galettedebroons.controller;

/* import */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import fr.galettedebroons.domain.*;


/**
 * Classe permettant la lecture d'un fichier d'extension .xls, .ods, .csv ou .txt
 * Celui devra contenir les champs suivants : 
 ***** Bon de livraison
 ***** Date
 ***** Code client
 ***** Nom client
 ***** Code produit
 ***** Quantité
 * 
 * @author  Melissa Poher 
 * @author Oumoul Sy
 * @author Julie Guegnaud
 * @version 1.0
 * @since   2015-03-10
 */
public class LectureFichier {
	
	private static EntityManager manager_;
	
	private static String nomDocument_;
	private static Date date_;
	private static String codeClient_;
	private static String nomClient_;
	private static String codeArticle_;
	private static int quantite_;
	private static EntityTransaction tx;
	//Livraison l=new Livraison();
	
	
	//private static Logger logger = Logger.getLogger(Lecture_Fichier_Excel.class);
	public LectureFichier(){}
	
	/**
	 * Récupère l'extention d'un fichier et renvoie le fichier sur la bonne méthode de traitement
	 * @param file est le fichier contenant les données à insérer en base 
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @return Rien
	 */
	public void ouverture_fichier(String nomFichier) throws InvalidFormatException, IOException{
		
		File file = new File(nomFichier);
		
		// Vérification du fichier (CSV/xlsx/ods)
		if (nomFichier.contains(".xlsx"))
			lectureExcel(file);
		else if (nomFichier.contains(".ods"))
			lectureCalc(file);
		else if (nomFichier.contains(".csv") || nomFichier.contains(".txt"))
			lectureCsv(nomFichier);
		
		// Si table temporaire non vide alors on renvoie des formulaires et on alimente la BD
	}
	
	/**
	 * Récupération des données d'un fichier excel
	 * @param file fichier .xls
	 * @throws InvalidFormatException
	 * @return rien
	 */
	public void lectureExcel(File file) throws InvalidFormatException{	
		
	    try {
	    	final Workbook workbook = WorkbookFactory.create(file);	        
	        final Sheet sheet = workbook.getSheetAt(0);	        
	        // Lecture du fichier commence a la ligne 1 car titre ligne 0
	        int index = 1;
	        Row row = sheet.getRow(index++);	        
	        if(row == null){}

	        System.out.println("Valeur de row :" +row);
	        while (row != null) {
	        	
	        	nomDocument_ = row.getCell(0).getStringCellValue();
	        	date_ = row.getCell(1).getDateCellValue();
	        	System.out.println(date_);
	        	codeClient_ = row.getCell(2).getStringCellValue();
	        	nomClient_ = row.getCell(3).getStringCellValue();
	        	
	        	// convert row to string exception when cell is a int
	        	row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
	        	codeArticle_ = (String) row.getCell(4).getStringCellValue();
	        	quantite_ = (int) row.getCell(5).getNumericCellValue();
	        	try{
	        		Produit p = manager_.find(Produit.class, codeArticle_);
	        	} catch (Exception e){
	        		System.out.println("le produit n'existe pas");
	        	}
	        	
	        	/*
	        	try{
	        		l.setBon_livraison(nomDocument_);
	            	l.setLivraison_profil(codeClient_);
	            	l.getLivraison_produit().add(p);
	            	l.setDate_livraison(date_);  
	            	if(quantite_<=0){
	            		l.setQte_reprise(quantite_);
	            	}
	            	else{
	            		l.setQte_livraison(quantite_);
	            	}   
	            	manager_.persist(l);
	        		
	        	}catch(Exception e){
	        		e.printStackTrace();
	        	}  	
	        	*/
	        	
	            row = sheet.getRow(index++);
	        }
	        
	    } catch(FileNotFoundException fnf){
	    	fnf.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Récupération des données d'un fichier open source
	 * @param file fichier .ods
	 * @return rien
	 */
	public void lectureCalc(File file){
		org.jopendocument.dom.spreadsheet.Sheet sheet;
		try{
			//Getting the 0th sheet for manipulation| pass sheet name as string
			sheet = SpreadSheet.createFromFile(file).getSheet(0);

			//Get row count and column count
			int nColCount = sheet.getColumnCount();
			int nRowCount = sheet.getRowCount();

			System.out.println("Rows :"+nRowCount);
			System.out.println("Cols :"+nColCount);

			//Iterating through each row of the selected sheet
			MutableCell cell = null;
			for(int nRowIndex = 0; nRowIndex < nRowCount; nRowIndex++){
				System.out.println("Je suis dans la première boucle for");
				
				//Iterating through each column
				for(int nColIndex = 0 ;nColIndex < nColCount; nColIndex++){
					cell = sheet.getCellAt(nColIndex, nRowIndex);
					System.out.print(cell.getValue()+ " ");
				}
				System.out.println();
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Récupération des données d'un fichier texte
	 * 
	 * @param file fichier .csv ou .txt
	 * @return rien
	 */
	public void lectureCsv(String nomFichier) throws IOException{
		try
		{
		   BufferedReader file = new BufferedReader(new FileReader(nomFichier));
		   String chaine;
		   int present;
		   RangerDonneeFichier donneeFichier = new RangerDonneeFichier();
		   
		   file.readLine();
		   while((chaine = file.readLine())!= null)
		   {
			   chaine = chaine.replaceAll("\"","");
			   String[] tabChaine = chaine.split(";");
			   
			   //Tu effectues tes traitements avec les données contenues dans le tableau
			   //La première information se trouve à l'indice 0

			   present = donneeFichier.verification(tabChaine);
			   
			   if(present != -1){
				   if (present == 0)
					   //Toutes les donnees existent
					   donneeFichier.ajout(tabChaine, "");
				   else if (present == 1)
					   //client inexistant
					   donneeFichier.ajout(tabChaine, "C");
				   else if (present == 2)
					   //produit inexistant
					   donneeFichier.ajout(tabChaine, "P");
				   else if (present == 3)
					   //client et produit inexistant
					   donneeFichier.ajout(tabChaine, "CP");
			   }
		   }
		   file.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Le fichier est introuvable !");
		}
	}
	
/*	public static void createClient(String code_client, String enseigne_client, String profil){
		// Ajout du client en base
		System.out.println("Ajout du nouveau client");
		System.out.println("mon code client " +code_client);
		System.out.println("mon enseigne client " +enseigne_client);
		
		Client c = new Client(code_client, enseigne_client, null);
		System.out.println("Mon objetc client " +c.getCode_client());
		manager_.persist(c);
		tx.commit();
	}*/
	
	public static void createProduit(String nomProduit, String typeProduit, String dureeValidite){
		// Ajout du client en base
		System.out.println("Ajout du nouveau produit");
		System.out.println("mon nom produit " +nomProduit);
		System.out.println("mon type produit " +typeProduit);
		System.out.println("ma duree validite " +dureeValidite);
		
		//String code_produit, String nom_produit,
		//String presentation_produit, Gamme code_gamme, int qte_lot
		//Produit p = new Produit(code_client, enseigne_client, null);
		//System.out.println("Mon objetc client " +c.getCode_client());
		//manager_.persist(c);
		System.out.println("Mon client est créé");
		tx.commit();
	}
	
	public String getNom_document() {
		return nomDocument_;
	}
	public void setNom_document(String nom_document) {
		this.nomDocument_ = nom_document;
	}
	public Date getDate() {
		return date_;
	}
	public void setDate(Date date) {
		this.date_ = date;
	}
	public String getCode_client() {
		return codeClient_;
	}
	public void setCode_client(String code_client) {
		this.codeClient_ = code_client;
	}
	public String getNom_client() {
		return nomClient_;
	}
	public void setNom_client(String nom_client) {
		this.nomClient_ = nom_client;
	}
	public String getCode_article() {
		return codeArticle_;
	}
	public void setCode_article(String code_article) {
		this.codeArticle_ = code_article;
	}
	public int getQuantite() {
		return quantite_;
	}
	public void setQuantite(int quantite) {
		this.quantite_ = quantite;
	}
	public EntityManager getManager(){
		return manager_;
	}
	public void setManager(EntityManager manager){
		manager_ = manager;
	}
}
