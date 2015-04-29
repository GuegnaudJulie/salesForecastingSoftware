library(DBI)
library(RMySQL)

#con<-dbConnect(RMySQL::MySQL(),user="root", password="",dbname="galette_broons", host="localhost")
con<-dbConnect(RMySQL::MySQL(),user="user_14010287", password="toor",dbname="base_14010287", host="anteros.istic.univ-rennes1.fr")
clients<-dbGetQuery(con,"SELECT code_client from Profil")
produits<-dbGetQuery(con,"SELECT code_produit from Produit")

for(i in 1:length(clients$code_client))
	{
		for(j in 1:length(produits$code_produit))
		{         
			cl<-clients$code_client[i]
			pr<-produits$code_produit[j]
			req<- paste0("SELECT quantite from QuantiteReelle where reel_produit_code_produit like '",pr,"' and reel_profil_code_client like '",cl,"'")
			#Récuperation des quantités
			quantiter<-dbGetQuery(con,req)
			#Fonction de prevision
			nouvelle<-ts(quantiter$quantite,frequency=3)
			liss_triple<-HoltWinters(nouvelle) 
			pred=predict(liss_triple,n.ahead=3)
			#Fonction d'insertion
			reqId<-paste0("SELECT profil_tournee_id from Profil_Tournee where profil_tournee_code_client like '",cl,"'")
			getId<-dbGetQuery(con, reqId)
			id<-getId$profil_tournee_id[1]
			#daysdb<-dbListFields(con, 'Tournee')
			reqdays<-paste0("SELECT lundi, mardi, mercredi, jeudi, vendredi, samedi, dimanche from Tournee where id like '",id,"'")  
			days<-dbGetQuery(con, reqdays)
			l<-list()

			for(a in 1:7)
			{
				if(days[a]==1)
				{
					l<-c(l,list(colnames(days[a])))
				}
			}

			#getTournee<-dbGetQuery(con, "SELECT  from Tournee where id like'",id,"'")
			lastdate1<-dbGetQuery(con, paste0("SELECT date FROM QuantiteReelle where reel_profil_code_client like '",cl,"' ORDER BY date DESC LIMIT 1"))
			lastdate2<-lastdate1$date[1]
			lastdate<-weekdays(as.Date(lastdate2))
		
			#Test gamme PE/LS
			#if(produit$produit_gamme_code_gamme = 'PE')

if(lastdate==l[1]){
	joursprévus1<-as.Date(lastdate2)+2	
	joursprévus2<-as.Date(lastdate2)+4
	joursprévus3<-as.Date(lastdate2)+7
}
if(lastdate==l[2]){
	joursprévus1<-as.Date(lastdate2)+2	
	joursprévus2<-as.Date(lastdate2)+5
	joursprévus3<-as.Date(lastdate2)+7
}
if(lastdate==l[3]){
	joursprévus1<-as.Date(lastdate2)+3	
	joursprévus2<-as.Date(lastdate2)+5
	joursprévus3<-as.Date(lastdate2)+7
}	

			d<-list()
			d<-c(joursprévus1,joursprévus2,joursprévus3)

			for(k in 1:length(pred))
			{
				p<-pred[k]
				dd<-d[k]
				query <- paste0("INSERT INTO Prevision(quantite, date, prevision_produit_code_produit, prevision_profil_code_client) VALUES(",p, ",", "'", dd, "'", ",", "'",pr, "'", ",", "'", cl, "'", ")")
				dbGetQuery(con, query)
			}
		}
}

#Fermeture des connections BD ouvertes
all_cons <- dbListConnections(MySQL())
for(con in all_cons)
{
	dbDisconnect(con)
}