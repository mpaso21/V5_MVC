package controller;

import java.time.LocalDateTime;
import java.util.Map;
import action.AzioneRichiestaPrestito;
import action.AzioneRichiestaProroga;
import action.AzioneRichiestaRinnovoIscrizione;
import entity.Fruitore;
import model.cittadino.FruitoreModel;
import mylib.MyMenu;
import view.cittadino.FruitoreView;


public class FruitoreController  extends CreatoreMenu {

	MyMenu menuFruitore = crea();
	private boolean uscita = false;
	
	private Controller mainManager;
	private FruitoreModel fruitoreModel;
	private FruitoreView fruitoreView;


	public FruitoreController(Controller mainManager){
		this.mainManager = mainManager;
		this.fruitoreModel = new FruitoreModel();
		this.fruitoreView = new FruitoreView();
	}

	public void init(Fruitore f){
		do{
			switch(menuFruitore.scegli()){
			case 1:
				rinnovoIscrizione(f);
				break;
			case 2:
				richiestaPrestito(f);
				break;
			case 3:
				prorogaPrestito(f);
				break;
			case 4:
				mainManager.getOperatore().ricercaTitolo();
				break;
			case 5:
				mainManager.getOperatore().ricercaGenere();
				break;
			case 6:
				mainManager.getOperatore().ricercaAutore();
				break;
			case 7:
				mainManager.getOperatore().ricercaRegista();
				break;
			case 0:
				uscita = true;
				break;
			}
		}while(uscita != true);
	}

	public void aggiungi() { //ISCRIZIONE FRUITORE 
		Fruitore f = fruitoreView.creaFruitoreInput();
		if(f!=null){
			String id;
			do {
				id = fruitoreView.inserisciIdInput();
				
			} while(fruitoreModel.controlloIdGiaEsistente(id) && fruitoreView.messaggioIdGiaPresente() );
			
			f.setId(id);
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

	
	public void rinnovoIscrizione(Fruitore f) {
		AzioneRichiestaRinnovoIscrizione rinnovo = new AzioneRichiestaRinnovoIscrizione(f, mainManager);
		rinnovo.rinnovo(f);
		
		//EXTRACT METHOD RISPETTO AL PROGETTO PRECEDENTE
		//MESSO IN CONTROLLER PERCHE' MISCHIA VIEW E MODEL
		
		//POI HO DECISO DI INSERIRLO IN UNA APPOSITA CLASSE CHE SI OCCUPA DEL RINNOVO DI ISCRIZIONI
		//DI FRUITORI
//		if(fruitoreModel.controlloScadenza(f)) { 
//			fruitoreView.stampaFruitoreScaduto();
//		} 
//		else {
//			LocalDateTime tempo = fruitoreModel.tempoRinnovoFruitore(f);
//			if( tempo == null ) {
//				fruitoreModel.rinnovoFruitore(f);
//				fruitoreView.stampaIscrizioneRinnovata();
//			} 
//			else {
//				fruitoreView.stampaRinnovoFruitoreTraPoco(Data.convertoData(tempo));
//			}
//		}
	}
	
	//COME SOPRA IL METODO RICHIEDI PRESTITO AVEVA BISOGNO SIA DI VIEW CHE DI MODEL
	//HO VOLUTO INSERIRLO IN UN ALTRA CLASSE CHE SI OCCUPA DELLA RICHIESTA DEL PRESTITO
	public void richiestaPrestito(Fruitore f) {
		AzioneRichiestaPrestito a = new AzioneRichiestaPrestito(f, mainManager);
		a.azioneRichiestaPrestitoInput();
	}

	//IDEM
	public void prorogaPrestito(Fruitore f){
		AzioneRichiestaProroga p = new AzioneRichiestaProroga(f, mainManager);
		p.prorogaConInput(LocalDateTime.now());
	}

	
	//METODO CHE NON POTEVA ANDARE IN FRUITORE MODEL /VIEW PERCHE' MISCHIA VIEW/MODEL DEL
	//FRUITORE-> E' UN OPZIONE DEL MENU' DELL'OPERATORE
	
		public void stampaFruitoriAttuali() {
			fruitoreModel.controlloScadenza(LocalDateTime.now());
			fruitoreView.stampaTuttiFruitori(fruitoreModel.getFruitori());
		}
	
		
		//INSERITO QUI E NON IN OPERATORE PER EVITARE TROPPI GET
		// E' UN OPZIONE DEL MENU' DELL'OPERATORE
		public void stampaStoricoFruitori(){
			fruitoreView.stampaTuttiFruitori(fruitoreModel.getStoricoFruitori());
		}
		
		
		//METODO CHE NON POTEVA ANDARE IN FRUITORE MODEL /VIEW PERCHE' MISCHIA VIEW/MODEL DEL
		//FRUITORE-> E' UN OPZIONE DEL MENU' DELL'OPERATORE
//		public void stampaNumPrestitiPerFruitore(){
//			Map <String,Integer> a = fruitoreModel.prestitiPerFruitore();
//			if(a.size() == 0){
//				fruitoreView.stampaZeroFruitori();
//			}
//			else{
//				fruitoreView.stampaPrestitiPerFruitore(a);
//			}
//		}
		
		//CHIAMATO IN OPERATORE CONTROLLER METODO "DELEGATO" PER EVITARE TROPPI GET E QUINDI FRAGILITA'
		//DI PROGETTO (MainManager.getFruitore.getFruitoreModel.prestitiPerFruitore)
		public Map<String, Integer> numPrestitiPerFruitore(){
		  return fruitoreModel.prestitiPerFruitore();
		}

		
		//INSERITO IN FRUITORE CONTROLLER METODO CHE NON POTEVA ANDARE IN FRUITORE MODEL /VIEW PERCHE' MISCHIA VIEW/MODEL DEL
		//FRUITORE
//	private void stampaPrestitiAttualiDelFruitore(Fruitore f){
//		PrestitoEntityController pec = null;
//		f.controlloScadenzaPrestitiFruitore();
//		for(String s : ConstantsRisorsa.TIPOLOGIE_RISORSE) {
//			pec = mainManager.getPrestitoControllerByKey(s);
//			//pec.controlloScadenzaTuttiPrestitiFruitore(f.getPrestitiPerTipo(s, f.getPrestiti()));
//			pec.stampaPrestiti(f.getPrestitiPerTipo(s, f.getPrestiti()));
//		}
		
//		mainManager.getPrestitoLibroController().controlloScadenzaTuttiPrestitiFruitore(f.getPrestitiPerTipo(ConstantsRisorsa.LIBRO, f.getPrestiti()));
//		mainManager.getPrestitoLibroController().stampaPrestiti(f.getPrestitiPerTipo(ConstantsRisorsa.LIBRO, f.getPrestiti()));
//		mainManager.getPrestitoFilmController().controlloScadenzaTuttiPrestitiFruitore(f.getPrestitiPerTipo(ConstantsRisorsa.FILM, f.getPrestiti()));
//		mainManager.getPrestitoFilmController().stampaPrestiti(f.getPrestitiPerTipo(ConstantsRisorsa.FILM, f.getPrestiti()));
	//}
	
//	public void stampaPrestitiAttualiTuttiFruitori(){
//		for(Fruitore f: fruitoreModel.getFruitori()){
//			stampaPrestitiAttualiDelFruitore(f);
//		}
//	}
	//INSERITO IN FRUITORE CONTROLLER METODO CHE NON POTEVA ANDARE IN FRUITORE MODEL /VIEW PERCHE' MISCHIA VIEW/MODEL DEL
	//FRUITORE
//	private void stampaStoricoPrestitiDelFruitore(Fruitore f){
//		PrestitoEntityController pec = null;
//		for(String s : ConstantsRisorsa.TIPOLOGIE_RISORSE) {
//			pec = mainManager.getPrestitoControllerByKey(s);
//			pec.stampaPrestiti(f.getPrestitiPerTipo(s, f.getPrestitiStoriciMiei()));
//		}
//	}
//
//	public void stampaPrestitiStoriciTuttiFruitori(){
//		for(Fruitore f: fruitoreModel.getStoricoFruitori()){
//			stampaStoricoPrestitiDelFruitore(f);
//		}
//	}

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
//				"Stampa miei prestiti",
//				"Stampa prestiti storici miei"
				};
		return opzioni;
	}

	public FruitoreModel getFruitoreModel() {
		return fruitoreModel;
	}

	public FruitoreView getFruitoreView() {
		return fruitoreView;
	}
	

	


	
	
}
