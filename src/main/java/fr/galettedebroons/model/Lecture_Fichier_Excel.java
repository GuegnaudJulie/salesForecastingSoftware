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
	private static String nom_document;
	private static Date date;
	private static String code_client;
	private static String nom_client;
	private static String code_article;
	private static int quantite;
	
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
	        	nom_document = row.getCell(0).getStringCellValue();
	        	date = row.getCell(1).getDateCellValue();
	        	code_client = row.getCell(2).getStringCellValue();
	        	nom_client = row.getCell(3).getStringCellValue();
	        	code_article = row.getCell(4).getStringCellValue();
	        	quantite = (int) row.getCell(5).getNumericCellValue();
	        	
	        	System.out.println("Le nom du document : " +nom_document);
	        	System.out.println("La date du document : " +date);
	        	System.out.println("Le code du client : " +code_client);
	        	System.out.println("Le nom du client : " +nom_client);
	        	System.out.println("Le code de l'article : " +code_article);
	        	System.out.println("La quantite : " +quantite);
	        	
	            row = sheet.getRow(index++);
	        }
	
	    } catch(FileNotFoundException fnf){
	    	fnf.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	public String getNom_document() {
		return nom_document;
	}
	public void setNom_document(String nom_document) {
		this.nom_document = nom_document;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCode_client() {
		return code_client;
	}
	public void setCode_client(String code_client) {
		this.code_client = code_client;
	}
	public String getNom_client() {
		return nom_client;
	}
	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}
	public String getCode_article() {
		return code_article;
	}
	public void setCode_article(String code_article) {
		this.code_article = code_article;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
}
