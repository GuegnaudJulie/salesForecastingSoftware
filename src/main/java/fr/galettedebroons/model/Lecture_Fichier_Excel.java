package fr.galettedebroons.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import fr.galettedebroons.view.FormulaireGalette;

//import com.sun.istack.internal.logging.Logger;


public class Lecture_Fichier_Excel {
	private static String nomDocument_;
	private static Date date_;
	private static String codeClient_;
	private static String nomClient_;
	private static String codeArticle_;
	private static int quantite_;
	
	//private static Logger logger = Logger.getLogger(Lecture_Fichier_Excel.class);
	
	
	/**
	 * open and read excel file
	 * @throws InvalidFormatException
	 */
	public void ouverture_fichier() throws InvalidFormatException {
	//public static void main(String[] args) throws InvalidFormatException{	
		System.out.println("HELLO !!!");
	    try {
	    	new FormulaireGalette().setVisible(true);
	    	File file = new File(("C:\\exemple1.xlsx"));
	    	
	        final Workbook workbook = WorkbookFactory.create(file);
	        System.out.println("J'ai trouvé mon fichier !!!");
	        final Sheet sheet = workbook.getSheetAt(0);
	        
	        // Lecture du fichier commence a la ligne 1 car titre ligne 0
	        int index = 1;
	        Row row = sheet.getRow(index++);
	        
	        if(row == null){
	        	//logger.info("Le fichier excel est vide");
	        }

	        System.out.println("Valeur de row :" +row);
	        while (row != null) {
	        	System.out.println("Ligne non null");
	        	nomDocument_ = row.getCell(0).getStringCellValue();
	        	date_ = row.getCell(1).getDateCellValue();
	        	codeClient_ = row.getCell(2).getStringCellValue();
	        	nomClient_ = row.getCell(3).getStringCellValue();
	        	codeArticle_ = row.getCell(4).getStringCellValue();
	        	quantite_ = (int) row.getCell(5).getNumericCellValue();
	        	
	        	System.out.println("Le nom du document : " +nomDocument_);
	        	System.out.println("La date du document : " +date_);
	        	System.out.println("Le code du client : " +codeClient_);
	        	System.out.println("Le nom du client : " +nomClient_);
	        	System.out.println("Le code de l'article : " +codeArticle_);
	        	System.out.println("La quantite : " +quantite_);
	        	
	            row = sheet.getRow(index++);
	        }
	
	    } catch(FileNotFoundException fnf){
	    	fnf.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
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
}
