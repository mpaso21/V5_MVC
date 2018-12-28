package controller.entitycontroller;


import java.util.ArrayList;
import java.util.List;
import entity.Risorsa;
import model.risorsa.FilmModel;
import model.risorsa.LibroModel;
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
	
	public void aggiungiRisorsa(Risorsa l) {
		risorsaModel.aggiungiRisorsa(l);
	}
	
	public Risorsa selezionaRisorsa(){
		return risorsaView.selezionaRisorsa(risorse());
	}
	
	public Risorsa selezionaRisorsaConFiltro(String nome, List<Risorsa>archivio){
		if(!archivio.isEmpty()){
			List <Risorsa> risorseFiltrati =  filtraRisorsa(nome, archivio);
			return risorsaView.selezionaRisorsa(risorseFiltrati);
		}
		else{
			return null;
		}
	}
	
	public List<Risorsa> filtraRisorsa(String nome, List<Risorsa>archivio){
		List <Risorsa> risorseFiltrati = new ArrayList<Risorsa>();
		for(Risorsa r: archivio){
			if(r.getTipo().equalsIgnoreCase(nome)){
				risorseFiltrati.add(r);
			}
		}
		return risorseFiltrati;
	}


	public void rimuovi(){
		if(!risorse().isEmpty()){
			Risorsa r = selezionaRisorsa();
			risorsaModel.eliminaRisorsa(r);
		}
		else{
			risorsaView.stampaErroreArchivio();
		}
	}
	
	public void stampaArchivio(){ //METODI CREATI PER EVITARE FRAGILITA' DI PROGETTO CIOE' TROPPI GET
		risorsaView.stampaTutti(risorse());
	}
	
	public void stampaStoricoArchivio(){
		risorsaView.stampaTutti(risorsaModel.getArchivioStoricoRisorse());
	}
	
	public void stampaRisorseTrovate(List <Risorsa> risorseTrovate){ //POTREI INSERIRLO GIA' NELLE RICERCHE SOTTO
		risorsaView.stampaRisorseTrovate(risorseTrovate);
	}

	public List<Risorsa> ricercaTitolo(String inputTitolo){
		return risorsaModel.ricercaPerTitolo(inputTitolo);
	}
	
	
	public List<Risorsa> ricercaGenere(String inputGenere){
		return risorsaModel.ricercaPerGenere(inputGenere);
		
	}
//	
//	public List<Risorsa> ricercaAutore(String inputAutore){
//	return ((LibroModel) risorsaModel).ricercaLibroAutore(inputAutore);
//	
//}
//	
//	public List<Risorsa> ricercaRegista(String inputRegista){
//	return ((FilmModel) risorsaModel).ricercaFilmRegista(inputRegista);
	
//}
	public int controlloSizeArchivio() {//POTREI TOGLIERLO E RICHIAMARE SOLO METODO
		return risorsaModel.getArchivio().size();
	}
	public RisorsaModel getRisorsaModel() {
		return risorsaModel;
	}

	public RisorsaView getRisorsaView() {
		return risorsaView;
	}
	public List<Risorsa> risorse(){ //CREATO QUI PERCHE' RICHIAMATO TANTE VOLTE
		return risorsaModel.getArchivio();
	}
	
	
	//mi serve solo per richiamare quelli delle classi specifiche con il polimorfismo
	public int getNumMaxPrestiti() {
		return 1;
	}

	

	
}
