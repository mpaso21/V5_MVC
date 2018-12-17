package controller.entitycontroller;

import java.util.ArrayList;
import java.util.List;

import entity.Libro;
import entity.Risorsa;
import model.FilmModel;
import model.FruitoreModel;
import model.LibroModel;
import model.RisorsaModel;
import mylib.Constants;
import view.FruitoreView;
import view.RisorsaView;

public class RisorsaEntityController {
	private RisorsaView risorsaView;
	private RisorsaModel risorsaModel;
	
	public RisorsaEntityController(RisorsaView risorsaView, RisorsaModel risorsaModel) {
		this.risorsaView = risorsaView;
		this.risorsaModel = risorsaModel;
	}
	
	public void aggiungiRisorsa(Risorsa l) {
		this.risorsaModel.aggiungiRisorsa(l);
	}
	
	public int controlloSizeArchivio(){
		return risorsaModel.controlloSizeArchivio();
	}
	public List<Risorsa> risorse(){
		return this.risorsaModel.getArchivio();
	}
	
	public void crea() {
		Risorsa r = null;
		r = risorsaView.crea();
		this.aggiungiRisorsa(r);
	}
	
	public void stampaArchivio(){
		risorsaView.stampaTutti(risorse());
	}
	
	public void stampaRisorseTrovate(List <Risorsa> risorseTrovate){
		risorsaView.stampaTutti(risorseTrovate);
	}

	public Risorsa selezionaRisorsa(){
		Risorsa r = risorsaView.selezionaRisorsa(risorse());
		return r;
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

	public RisorsaModel getRisorsaModel() {
		return risorsaModel;
	}

	public void setRisorsaModel(RisorsaModel risorsaModel) {
		this.risorsaModel = risorsaModel;
	}

	public RisorsaView getRisorsaView() {
		return risorsaView;
	}

	public void setRisorsaView(RisorsaView risorsaView) {
		this.risorsaView = risorsaView;
	}

	
	public List<Risorsa> ricercaTitolo(String inputTitolo){
		return risorsaModel.ricercaPerTitolo(inputTitolo);
		
	}
	
	public List<Risorsa> ricercaGenere(String inputGenere){
		return risorsaModel.ricercaPerGenere(inputGenere);
		
	}
	
	public List<Risorsa> ricercaAutore(String inputAutore){
		return ((LibroModel) risorsaModel).ricercaLibroAutore(inputAutore);
		
	}
	
	public List<Risorsa> ricercaRegista(String inputRegista){
		return ((FilmModel) risorsaModel).ricercaFilmRegista(inputRegista);
		
	}
	
	//DA MODIFICARE STAMPA CATEGORIA PERCHE HO INSERITO STRINGA CATEGORIA IN OGNI RISORSA
	//TOGLIERE STAMPA CATEGORIA DA FILM VIEW E LIBRO VIEW

	
}
