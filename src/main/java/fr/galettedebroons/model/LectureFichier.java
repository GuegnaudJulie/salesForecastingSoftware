package fr.galettedebroons.model;

/* import */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import fr.galettedebroons.controller.ControllerFichier;
import fr.galettedebroons.main.Main;

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
 * @author	Oumoul Sy
 * @author	Julie Guegnaud
 * @version 1.0
 * @since   2015-03-10
 */
public class LectureFichier {

	private Main main_;
	
	public LectureFichier(Main main){
		main_ = main;
	}
	
	/**
	 * Recupere l'extention d'un fichier et renvoie le fichier sur la bonne methode de traitement
	 * 
	 * @author	Julie Guegnaud
	 * @since	13/03/2015
	 * @param	file est le fichier contenant les donnees à inserer en base 
	 * @throws	InvalidFormatException
	 * @throws	IOException
	 */
	public void ouverture_fichier(String nomFichier) throws InvalidFormatException, IOException{
		
		File file = new File(nomFichier);
		
		if (nomFichier.contains(".xlsx") || nomFichier.contains(".xls"))
			lectureExcel(file);
		else if (nomFichier.contains(".ods"))
			lectureCalc(file);
		else if (nomFichier.contains(".csv") || nomFichier.contains(".txt"))
			lectureCsv(nomFichier);
	}
	
	/**
	 * Recuperation des donnees d'un fichier excel
	 * 
	 * @author	Melissa Poher 
	 * @author	Julie Guegnaud
	 * @since	13/03/2015
	 * @param	file fichier .xls et .xlsx
	 * @throws	InvalidFormatException
	 * @throws	IOException
	 */
	public void lectureExcel(File file) throws InvalidFormatException, IOException{	
		String[] tabChaine = new String[6];
		
		Workbook workbook = WorkbookFactory.create(file);
		final Sheet sheet = workbook.getSheetAt(0);
		
		// Lecture du fichier commence a la ligne 2 car titre ligne 0 et entete du tableau a la ligne 1
		int index = 1;
		Row row = sheet.getRow(index);
		//System.out.println("Valeur de row :" +row);
		int bwaa = 0;
		
		while (row != null){
			if (bwaa != 0){
				for (int i = 0; i<tabChaine.length; i++){
					tabChaine[i] = row.getCell(i).toString();
					
					if (tabChaine[i].matches("^[0-9]+,[0-9]+$")){
						int ind = tabChaine[i].indexOf(",");
						tabChaine[i] = tabChaine[i].substring(0, ind);
					}
					else if (tabChaine[i].matches("^[0-9]+\\.[0-9]+$")){
						int ind = tabChaine[i].indexOf(".");
						tabChaine[i] = tabChaine[i].substring(0, ind);
					}
					
				}
				remplissageTemp(tabChaine);
			}
			row = sheet.getRow(index++);
			bwaa++;
		}
	}
	
	/**
	 * Récupération des données d'un fichier open source
	 * 
	 * @author	Julie Guegnaud
	 * @since	13/03/2015
	 * @param	file fichier .ods
	 * @throws	IOException 
	 */
	public void lectureCalc(File file) throws IOException{
		org.jopendocument.dom.spreadsheet.Sheet sheet;
		sheet = SpreadSheet.createFromFile(file).getSheet(0);
		
		int nColCount = sheet.getColumnCount();
		int nRowCount = sheet.getRowCount();
		String[] donnees = new String[6];
		
		MutableCell<SpreadSheet> cell;
		for(int nRowIndex = 0; nRowIndex < nRowCount; nRowIndex++){
			if (nRowIndex > 0){
				for(int nColIndex = 0 ;nColIndex < nColCount; nColIndex++){
					cell = sheet.getCellAt(nColIndex, nRowIndex);
					donnees[nColIndex] = cell.getTextValue();
					
					if (donnees[nColIndex].matches("^[0-9]+,[0-9]+$")){
						int ind = donnees[nColIndex].indexOf(",");
						donnees[nColIndex] = donnees[nColIndex].substring(0, ind);
					}
					else if (donnees[nColIndex].matches("^[0-9]+\\.[0-9]+$")){
						int ind = donnees[nColIndex].indexOf(".");
						donnees[nColIndex] = donnees[nColIndex].substring(0, ind);
					}
				}
				remplissageTemp(donnees);
			}
		}
	}
	
	/**
	 * Récupération des données d'un fichier texte
	 * 
	 * @author	Julie Guegnaud
	 * @since	13/03/2015
	 * @param	file fichier .csv ou .txt
	 */
	public void lectureCsv(String nomFichier) throws IOException{
		BufferedReader file = new BufferedReader(new FileReader(nomFichier));
		String chaine;
		
		file.readLine();
		while((chaine = file.readLine())!= null)
		{
			chaine = chaine.replaceAll("\"","");
			String[] tabChaine = chaine.split(";");
			
			remplissageTemp(tabChaine);
		}
		file.close();
	}
	
	/**
	 * Fonction de recuperation du code erreur de la ligne et envoie des donnees a une fonction permettant l'ajout des donnees dans la table Temporaire
	 * 
	 * @author	Julie Guegnaud
	 * @since	13/03/2015
	 * @param	donnees donnees du fichier
	 */
	public void remplissageTemp(String[] donnees){
		ControllerFichier cf = new ControllerFichier(main_);
		int present = cf.verification(donnees);
		
		RangerDonneeTemporaire donneeFichier = new RangerDonneeTemporaire(main_);
		if(present != -1){
			if (present == 0)
				//Toutes les donnees existent
				donneeFichier.ajout(donnees, "");
			else if (present == 1)
				//client inexistant
				donneeFichier.ajout(donnees, "C");
			else if (present == 2)
				//produit inexistant
				donneeFichier.ajout(donnees, "P");
			else if (present == 3)
				//client et produit inexistant
				donneeFichier.ajout(donnees, "CP");
		}
	}
	
}
