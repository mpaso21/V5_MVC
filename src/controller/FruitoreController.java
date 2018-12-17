package controller;

import java.time.LocalDateTime;

import action.AzioneRichiestaPrestito;
import action.AzioneRichiestaProroga;
import controller.entitycontroller.PrestitoEntityController;
import controller.entitycontroller.RisorsaEntityController;
import entity.Fruitore;
import entity.Prestito;
import entity.Risorsa;
import model.FruitoreModel;
import model.PrestitoModel;
import mylib.Constants;
import mylib.Data;
import mylib.InputDati;
import mylib.MyMenu;
import view.FruitoreView;
import view.PrestitoFilmView;
import view.PrestitoLibroView;

public class FruitoreController implements Controller {


	private String titolo = "OPZIONI DI LOGIN";
	private String opzioni[] = {
			"Rinnovo fruitore",
			"Richiedi prestito",
			"Rinnovo prestito",
			"Ricerca risorsa per titolo",
			"Ricerca risorsa per genere",
			"Ricerca risorsa per autore",
			"Ricerca risorsa per regista",
			"Stampa miei prestiti"};
	private MyMenu m = new MyMenu(titolo, opzioni);
	private boolean uscita = false;
	private LoginController mainManager;
	private FruitoreModel fruitoreModel;
	private FruitoreView fruitoreView;
	private RisorsaEntityController libroController;
	private RisorsaEntityController filmController;
	private PrestitoEntityController prestitoLibroController;
	private PrestitoEntityController prestitoFilmController;
	

	public FruitoreController(LoginController mainManager){
		this.mainManager = mainManager;
		this.fruitoreModel = new FruitoreModel();
		this.fruitoreView = new FruitoreView();
		this.libroController =mainManager.getLibroController();
		this.filmController = mainManager.getFilmController();
		
		this.prestitoLibroController = new PrestitoEntityController(new PrestitoLibroView(), new PrestitoModel());
		this.prestitoFilmController = new PrestitoEntityController(new PrestitoFilmView(), new PrestitoModel());
	}

	public void init(Fruitore f){
		do{
			switch(m.scegli()){
			case 1:
				rinnovoFruitore(f);
				break;
			case 2:
				this.richiestaPrestito(f);
				break;
			case 3:
				this.prorogaPrestito(f);
				break;
			case 4:
				ricercaPerTitolo();
				break;
			case 5:
				ricercaPerGenere();
				break;
			case 6:
				ricercaPerAutore();
				break;
			case 7:
				ricercaPerRegista();
				break;
			case 8:
				stampaPrestiti(f);
				break;
			case 0:
				uscita = true;
				break;
			}
		}while(uscita != true);
	}

	public void login(){
		String id = this.fruitoreView.inserisciIdInput();
		Fruitore f = this.fruitoreModel.controlloPresenzaFruitore(id);
		if(f == null) {
			this.fruitoreView.stampaFruitoreNonTrovato();
		} else {//altrimenti se esiste può fare tutte le sue opzioni
			if(!this.fruitoreModel.controlloScadenza(f)) {
				init(f);
			} else {
				fruitoreView.stampaFruitoreScaduto();
			}
		}
	}

	public void aggiungi() { //creazione fruitore
		Fruitore f = this.fruitoreView.creaFruitoreInput();
		if(f!=null){
			this.fruitoreModel.aggiungiFruitore(f);
			fruitoreView.stampaIdFruitore(f.getId()); //STAMPA CREDENZIALI
		}
		else{
			fruitoreView.stampaNoFruitore();
		}
		
	}
		
		public void stampaFruitori() {
			fruitoreModel.controlloScadenza(LocalDateTime.now());
			this.fruitoreView.stampaTuttiFruitori(this.fruitoreModel.getFruitori());
		}
		
	public void rinnovoFruitore(Fruitore f) { //extract method di qui per metodo rinnovoFruitore
		if(this.fruitoreModel.controlloScadenza(f)) {
			fruitoreView.stampaFruitoreScaduto();
		} else {
			LocalDateTime tempo = this.fruitoreModel.tempoRinnovoFruitore(f);
			if( tempo == null ) {
				this.fruitoreModel.rinnovoFruitore(f);
			} else {
				fruitoreView.stampaRinnovoFruitoreTraPoco(Data.convertoData(tempo));
			}
		}
	}
	
	public void ricercaPerTitolo(){
		mainManager.getOperatore().ricercaTitolo();
	}
	
	public void ricercaPerGenere(){
		mainManager.getOperatore().ricercaGenere();
	}
	
	public void ricercaPerAutore(){
		mainManager.getOperatore().ricercaAutore();
	}
	
	public void ricercaPerRegista(){
		mainManager.getOperatore().ricercaRegista();
	}
	
	public void richiestaPrestito(Fruitore f) {
		AzioneRichiestaPrestito a = new AzioneRichiestaPrestito(f, libroController, filmController, prestitoLibroController, prestitoFilmController);
		a.azione();
	}


	public void prorogaPrestito(Fruitore f){
		AzioneRichiestaProroga p = new AzioneRichiestaProroga(f, prestitoLibroController);
		p.proroga(LocalDateTime.now());
	}
	
	public void stampaPrestiti(Fruitore f){//CANATO NON E' INIZIALIZZATO L'ARRAY DI PRESTITI DI FRUITORE?
		prestitoLibroController.controlloScadenzaTuttiPrestitiFruitore(f.getPrestitiPerTipo(Constants.LIBRO));
		prestitoLibroController.stampaPrestiti(f.getPrestitiPerTipo(Constants.LIBRO));
		prestitoFilmController.controlloScadenzaTuttiPrestitiFruitore(f.getPrestitiPerTipo(Constants.FILM));
		prestitoFilmController.stampaPrestiti(f.getPrestitiPerTipo(Constants.FILM));
	}

	
}
