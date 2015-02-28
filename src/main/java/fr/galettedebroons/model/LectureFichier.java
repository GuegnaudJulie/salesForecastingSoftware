package fr.galettedebroons.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;




//import org.odftoolkit.odfdom.pkg.OdfFileDom;
import org.w3c.dom.Node; 
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

//import org.odftoolkit.odfdom.doc.OdfDocument;
//import org.odftoolkit.odfdom.pkg.OdfFileDom;
import org.w3c.dom.NodeList;

import fr.galettedebroons.domain.Client;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.view.AjoutClientForm;
import fr.galettedebroons.view.AjoutPForm;
import fr.galettedebroons.view.FormulaireGalette;

//import com.sun.istack.internal.logging.Logger;


public class LectureFichier {
	
	private static EntityManager manager_;
	
	private static String nomDocument_;
	private static Date date_;
	private static String codeClient_;
	private static String nomClient_;
	private static String codeArticle_;
	private static int quantite_;
	private static EntityTransaction tx;
	
	//private static Logger logger = Logger.getLogger(Lecture_Fichier_Excel.class);
	
	
	public LectureFichier(){}
	
	/**
	 * open and read excel file
	 * @throws InvalidFormatException
	 * @throws IOException 
	 */
	public void ouverture_fichier(String nomFichier) throws InvalidFormatException, IOException{
		
		File file = new File(nomFichier);
		
		// add to database with read values
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("example");
    	EntityManager manager = factory.createEntityManager();
    	this.setManager(manager);
    	
    	tx = manager.getTransaction();
    	tx.begin();
		
		/* Vérification du fichier (CSV/xlsx/ods) */
		if (nomFichier.contains(".xlsx"))
			lectureExcel(file);
		else if (nomFichier.contains(".ods"))
			lectureCalc(file);
		else if (nomFichier.contains(".csv"))
			lectureCsv(nomFichier);
		
    	//tx.commit();
	}
			
	public void lectureExcel(File file) throws InvalidFormatException{	
		System.out.println("HELLO !!!");
	    try {
	    	final Workbook workbook = WorkbookFactory.create(file);
	        System.out.println("J'ai trouve mon fichier !!!");
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
	        	// convert row to string exception when cell is a int
	        	row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
	        	codeArticle_ = (String) row.getCell(4).getStringCellValue();
	        	quantite_ = (int) row.getCell(5).getNumericCellValue();
	        	
	        	System.out.println("Le nom du document : " +nomDocument_);
	        	System.out.println("La date du document : " +date_);
	        	System.out.println("Le code du client : " +codeClient_);
	        	System.out.println("Le nom du client : " +nomClient_);
	        	System.out.println("Le code de l'article : " +codeArticle_);
	        	System.out.println("La quantite : " +quantite_);
	        	
	        	
	        	try{
	        		insertLigneClient();
	        	}catch(Exception e){
	        		e.printStackTrace();
	        	}  	

	        	
	            row = sheet.getRow(index++);
	        }
	        
	    } catch(FileNotFoundException fnf){
	    	fnf.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void lectureCalc(File file){
		/*
		// Load the ODF document from the path
		OdfDocument odfDoc = OdfDocument.loadDocument("C:\\example.ods");

		// Get the content as DOM tree
		OdfFileDom odfContent = odfDoc.getContentDom();
		// System.out.println( "odfContent = " + odfContent.toString());

		// Initialize XPath
		XPath xpath = odfDoc.getXPath();

		// Issue XPath query to select first cell in each row
		NodeList nodeList = (NodeList) xpath.evaluate("//table:table-row/table:table-cell[1]",
		odfContent, XPathConstants.NODESET);

		// Print textual content of each cell in the nodeset
		for (int i = 0; i < nodeList.getLength(); i++) {
		Node cell = nodeList.item(i);
		System.out.println(cell.getTextContent());
		}
		*/
	}
	
	public void lectureCsv(String nomFichier) throws IOException{
		try
		{
		   BufferedReader file = new BufferedReader(new FileReader(nomFichier));
		   String chaine;
		   int i = 1;
		 
		   while((chaine = file.readLine())!= null)
		   {
		      if(i > 1)
		      {
		         String[] tabChaine = chaine.split(";");
		         //Tu effectues tes traitements avec les données contenues dans le tableau
		         //La première information se trouve à l'indice 0
		      }
		      i++;
		   }
		   file.close();                 
		}
		catch (FileNotFoundException e)
		{
		   System.out.println("Le fichier est introuvable !");
		}
	}
	
	public static void insertLigneClient(){
		// Verification si le client n'est pas dans la base
		@SuppressWarnings("unchecked")
		List<Client> client = manager_.createQuery("select c from Client c where c.code_client LIKE :codeClient ").setParameter("codeClient", codeClient_).setMaxResults(10).getResultList();
		
		// Verif que le produit est bien en base
		@SuppressWarnings("unchecked")
		List<Produit> produit = manager_.createQuery("select p from Produit p where p.code_produit LIKE :codeProduit ").setParameter("codeProduit", codeArticle_).setMaxResults(10).getResultList();
		System.out.println("LA taille de ma liste de produit : " +produit.size());
		
		if (client.size() == 0){
			// Afficher la fenetre créer un client
			// Vous devez renseigner les champs du nouveau client
			System.out.println("LE CLIENT N'EXISTE PAS !!!!!!");
			AjoutClientForm nouveauClient = new AjoutClientForm(nomClient_);
			nouveauClient.setVisible(true);
			
		}
		if (produit.size() == 0){
			System.out.println("Le client n'existe pas !!!");
			AjoutPForm nouveauProd = new AjoutPForm(codeArticle_);
			nouveauProd.setVisible(true);
			
		}
		// J'ajoute ma ligne de livraison
		// Livraison_Effective lf = new Livraison_Effective(nomDocument_, date_, codeClient_, nomClient_, codeArticle_,quantite_);
		
		
	}
	
	
	public static void createClient(String code_client, String enseigne_client, String profil){
		// Ajout du client en base
		System.out.println("Ajout du nouveau client");
		System.out.println("mon code client " +code_client);
		System.out.println("mon enseigne client " +enseigne_client);
		//Client c = new Client();
		Client c = new Client(code_client, enseigne_client, null);
		System.out.println("Mon objetc client " +c.getCode_client());
		manager_.persist(c);
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
