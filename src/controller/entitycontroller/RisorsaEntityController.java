package controller.entitycontroller;


import java.util.List;
import entity.Risorsa;
import model.risorsa.RisorsaModel;
import view.risorsa.RisorsaView;

public class RisorsaEntityController {
	private RisorsaView risorsaView;
	private RisorsaModel risorsaModel;
	
	public RisorsaEntityController(RisorsaView risorsaView, RisorsaModel risorsaModel) {
		this.risorsaView = risorsaView;
		this.risorsaModel = risorsaModel;
	}
	
	public void crea() {
		Risorsa r = null;
		r = risorsaView.crea();
		this.aggiungiRisorsa(r);
	}
	
	//POTEVO RICHIAMARE DIRETTAMENTE IN CREA RISORSAMODEL.AGGIUNGIRISORSA(L) 
	//CREATO APPOSITAMENTE COSI' PER I TEST.
	public void aggiungiRisorsa(Risorsa l) {
		risorsaModel.aggiungiRisorsa(l);
	}
	
	//METODO RIMUOVI COSTITUITO DALLA SELEZIONE, ELIMINA E STAMPA -> QUINDI NEL CONTROLLER
//	public void rimuovi(){ 
//		if(!risorse().isEmpty()){
//			Risorsa r = selezionaRisorsa();
//			risorsaModel.eliminaRisorsa(r);
//		}
//		else{
//			risorsaView.stampaErroreArchivio();
//		}
//	}
//	
	public Risorsa selezionaRisorsa(){ //SELEZIONARISORSA CONTROLLA GIA' SE L'ARCHIVIOE ' VUOTO
		return risorsaView.selezionaRisorsa(risorsaModel.getArchivio());
	}
	
	//VERIFICA CHE L'ARCHIVIO NON E' VUOTO
	//RIS = SELEZIONA RISORSA 
	//E POI RIMUOVI
	//COSI METODO TESTABILE
		public void rimuoviRisorsa(Risorsa ris){
			if(ris!=null){
				risorsaModel.eliminaRisorsa(ris);
			}
			else{
				risorsaView.stampaErroreArchivio();
			}
		}

//	public Risorsa selezionaRisorsaConFiltro(String nome, List<Risorsa>archivio){
//		if(!archivio.isEmpty()){
//			List <Risorsa> risorseFiltrati =  filtraRisorsa(nome, archivio);
//			return risorsaView.selezionaRisorsa(risorseFiltrati);
//		}
//		else{
//			return null;
//		}
//	}
//	
//	private List<Risorsa> filtraRisorsa(String nome, List<Risorsa>archivio){
//		List <Risorsa> risorseFiltrati = new ArrayList<Risorsa>();
//		for(Risorsa r: archivio){
//			if(r.getTipo().equalsIgnoreCase(nome)){
//				risorseFiltrati.add(r);
//			}
//		}
//		return risorseFiltrati;
//	}

	
	//UTLIZZATO NELL'OPERATORE CONTROLLER
	public void stampaArchivio(){ //METODI CREATI PER EVITARE FRAGILITA' DI PROGETTO CIOE' TROPPI GET
		risorsaView.stampaTutti(risorsaModel.getArchivio());
	}
	
	public void stampaStoricoArchivio(){
		risorsaView.stampaTutti(risorsaModel.getArchivioStoricoRisorse());
	}
	
	public void stampaRisorseTrovate(List <Risorsa> risorseTrovate){ //POTREI INSERIRLO GIA' NELLE RICERCHE SOTTO
		risorsaView.stampaRisorseTrovate(risorseTrovate);
	}

	public List<Risorsa> ricercaTitolo(String inputTitolo){ //TESTATO
		return risorsaModel.ricercaPerTitolo(inputTitolo);
	}
	
	
	public List<Risorsa> ricercaGenere(String inputGenere){ //TESTATO
		return risorsaModel.ricercaPerGenere(inputGenere);	
	}
	
//	SPOSTATI NEGLI APPOSITI CONTROLLER
//	public List<Risorsa> ricercaAutore(String inputAutore){
//	return ((LibroModel) risorsaModel).ricercaLibroAutore(inputAutore);
//	
//}
//	
//	public List<Risorsa> ricercaRegista(String inputRegista){
//	return ((FilmModel) risorsaModel).ricercaFilmRegista(inputRegista);
	
//}
	
	//POTREI TOGLIERLO E RICHIAMARE SOLO METODO MA CREATO PER EVITARE TROPPI GET.
	//IN AZIONE RICHIESTA PRESTITO  CONTROLLER.GETRISORSAMODEL.GETARCHIVIO.SIZE AL 
	//POSTO DI CONTROLLER.CONTROLLOSIZEARCHIVIO()
	public int controlloSizeArchivio() {
		return risorsaModel.getArchivio().size();
	}
	
	//UTILE PER RICHIAMARE QUELLI DELLE CLASSI DERIVATE ATTRAVERSO IL POLIMORFISMO
	//UTILIZZATO NELLA CLASSE AZIONE RICHIESTA PRESTITO
	public int getNumMaxPrestiti() {
		return 1;
	}

	public RisorsaModel getRisorsaModel() {
		return risorsaModel;
	}

	public RisorsaView getRisorsaView() {
		return risorsaView;
	}

	
}
