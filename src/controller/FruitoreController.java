package controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import action.AzioneRichiestaPrestito;
import action.AzioneRichiestaProroga;
import controller.entitycontroller.PrestitoEntityController;
import entity.Fruitore;
import model.cittadino.FruitoreModel;
import mylib.ConstantsRisorsa;
import mylib.Data;
import mylib.MyMenu;
import view.cittadino.FruitoreView;


public class FruitoreController  extends CreatoreMenu {

	MyMenu m = crea();
	private boolean uscita = false;
	
	private LoginController mainManager;
	private FruitoreModel fruitoreModel;
	private FruitoreView fruitoreView;


	public FruitoreController(LoginController mainManager){//MAIN MANAGER BRUTTA DIPENDENZA
		this.mainManager = mainManager;
		this.fruitoreModel = new FruitoreModel();
		this.fruitoreView = new FruitoreView();
	}

	public void init(Fruitore f){
		do{
			switch(m.scegli()){
			case 1:
				rinnovoFruitore(f);
				break;
			case 2:
				richiestaPrestito(f);
				break;
			case 3:
				prorogaPrestito(f);
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
				stampaPrestitiAttuali(f);
				break;
			case 9:
				this.stampaStoricoPrestiti(f); 
				break;
			case 0:
				uscita = true;
				break;
			}
		}while(uscita != true);
	}

	public void aggiungi() { //CREAZIONE FRUITORE 
		Fruitore f = fruitoreView.creaFruitoreInput();
		if(f!=null){
			fruitoreModel.aggiungiFruitore(f);
			fruitoreView.stampaIdFruitore(f.getId()); //STAMPA CREDENZIALI
		}
		else{
			fruitoreView.stampaNoFruitore();
		}
	}
	
	public void login(){ //CHIAMA IL MENU DI FRUITORE
		String id = fruitoreView.inserisciIdInput();
		Fruitore f = fruitoreModel.controlloPresenzaFruitore(id);
		if(f == null) {
			fruitoreView.stampaFruitoreNonTrovato();
		} else {
			if(!fruitoreModel.controlloScadenza(f)) {			
				init(f);
			} else {
				fruitoreView.stampaFruitoreScaduto();
			}
		}
	}

	
	public void rinnovoFruitore(Fruitore f) { //extract method di qui per metodo rinnovoFruitore
		if(fruitoreModel.controlloScadenza(f)) { //EXTRACT METHOD + MOVE METHOD
			fruitoreView.stampaFruitoreScaduto();
		} 
		else {
			LocalDateTime tempo = fruitoreModel.tempoRinnovoFruitore(f);
			if( tempo == null ) {
				fruitoreModel.rinnovoFruitore(f);
				fruitoreView.stampaIscrizioneRinnovata();
			} 
			else {
				fruitoreView.stampaRinnovoFruitoreTraPoco(Data.convertoData(tempo));
			}
		}
	}
	
	public void richiestaPrestito(Fruitore f) {
		AzioneRichiestaPrestito a = new AzioneRichiestaPrestito(f, mainManager);
		a.rifatto();
	}


	public void prorogaPrestito(Fruitore f){
		AzioneRichiestaProroga p = new AzioneRichiestaProroga(f, mainManager.getPrestitoLibroController(),
																mainManager.getPrestitoFilmController());
		p.proroga(LocalDateTime.now());
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
	
	
	//METODO CHE NON POTEVA ANDARE IN FRUITORE MODEL /VIEW PERCHE' MISCHIA VIEW/MODEL DEL
	//FRUITORE-> E' PER OPERATORE
	
		public void stampaFruitoriAttuali() {
			fruitoreModel.controlloScadenza(LocalDateTime.now());
			fruitoreView.stampaTuttiFruitori(fruitoreModel.getFruitori());
		}
	
	//PER OPERATORE
		public void stampaStoricoFruitori(){
			fruitoreView.stampaTuttiFruitori(fruitoreModel.getStoricoFruitori());
		}
		
		
		//MESSO IN CONTROLLER PERCHE' MISCHA VIEW /MODEL DEL FRUITORE
		//METODO PER OPERATORE
		public void stampaNumPrestitiPerFruitore(){
			HashMap <String,Integer> a = fruitoreModel.prestitiPerFruitore();
			if(a.size() == 0){
				fruitoreView.stampaZeroFruitori();
			}
			else{
				fruitoreView.stampaPrestitiPerFruitore(a);
			}
		}

	
	public void stampaPrestitiAttuali(Fruitore f){//STAMPA PRESTITI  ATTUALI PER FRUITORE SPECIFICO
		PrestitoEntityController pec = null;
		for(String s : ConstantsRisorsa.TIPOLOGIE_RISORSE) {
			pec = mainManager.getPrestitoControllerByKey(s);
			pec.controlloScadenzaTuttiPrestitiFruitore(f.getPrestitiPerTipo(s, f.getPrestiti()));
			pec.stampaPrestiti(f.getPrestitiPerTipo(s, f.getPrestiti()));
		}
		
//		mainManager.getPrestitoLibroController().controlloScadenzaTuttiPrestitiFruitore(f.getPrestitiPerTipo(ConstantsRisorsa.LIBRO, f.getPrestiti()));
//		mainManager.getPrestitoLibroController().stampaPrestiti(f.getPrestitiPerTipo(ConstantsRisorsa.LIBRO, f.getPrestiti()));
//		mainManager.getPrestitoFilmController().controlloScadenzaTuttiPrestitiFruitore(f.getPrestitiPerTipo(ConstantsRisorsa.FILM, f.getPrestiti()));
//		mainManager.getPrestitoFilmController().stampaPrestiti(f.getPrestitiPerTipo(ConstantsRisorsa.FILM, f.getPrestiti()));
	}


	public void stampaStoricoPrestiti(Fruitore f){
		mainManager.getPrestitoLibroController().stampaPrestiti(f.getPrestitiPerTipo(ConstantsRisorsa.LIBRO, f.getPrestitiStoriciMiei()));
		mainManager.getPrestitoFilmController().stampaPrestiti(f.getPrestitiPerTipo(ConstantsRisorsa.FILM, f.getPrestitiStoriciMiei()));

	}
	
	
	public void stampaPrestitiAttualiTuttiFruitori(){
		for(Fruitore f: fruitoreModel.getFruitori()){
			stampaPrestitiAttuali(f);
		}
	}
	//STORICO PRESTITI SEMPRE PER OPERATORE PER FARE INTERRROGAZIONI
	public void stampaPrestitiStoriciFruitori(){
		for(Fruitore f: fruitoreModel.getStoricoFruitori()){
			stampaStoricoPrestiti(f);
		}
	}

	@Override
	protected String titolo() {
		String titolo = "MENU FRUITORE";
		return titolo;
	}

	@Override
	protected String[] opzioni() {
		String opzioni[] = {
				"Rinnovo iscrizione",
				"Richiedi prestito",
				"Rinnovo prestito",
				"Ricerca risorsa per titolo",
				"Ricerca risorsa per genere",
				"Ricerca risorsa per autore",
				"Ricerca risorsa per regista",
				"Stampa miei prestiti",
				"Stampa prestiti storici miei"};
		return opzioni;
	}
	



	
	
}
