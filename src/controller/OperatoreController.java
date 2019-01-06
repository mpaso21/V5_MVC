package controller;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import controller.entitycontroller.FilmEntityController;
import controller.entitycontroller.LibroEntityController;
import controller.entitycontroller.PrestitoEntityController;
import controller.entitycontroller.RisorsaEntityController;
import entity.Operatore;
import entity.Risorsa;
import mylib.Constants;
import mylib.ConstantsCittadino;
import mylib.ConstantsRisorsa;
import mylib.InputDati;
import mylib.MyMenu;
import mylib.UtilitaCreazioneCampi;
import view.cittadino.OperatoreView;

public class OperatoreController extends CreatoreMenu {

	 
	MyMenu menuOperatore = crea();
	
	private Controller mainManager;
	private OperatoreView operatoreView ;

	
	public OperatoreController(Controller mainManager){
		this.mainManager = mainManager;
		this.operatoreView = new  OperatoreView();
	}	


	public void init(Operatore operatore)	{
		boolean uscita = false;
		do{
			switch(menuOperatore.scegli()){
			case 1:
				mainManager.getFruitore().stampaFruitoriAttuali();
				break;
			case 2:
				aggiungiRisorsa();
				break;
			case 3:
				rimuoviRisorsa();
				break;
			case 4:
				stampaRisorse();
				break;
			case 5:
				ricercaTitolo();
				break;
			case 6:
				ricercaGenere();
				break;
			case 7:
				ricercaAutore();
				break;
			case 8:
				ricercaRegista();
				break;
			case 9:
				mainManager.getFruitore().stampaStoricoFruitori();
				break;
			case 10:
				stampaStoricoRisorse();
				break;
			case 11:
				this.stampaStoricoPrestiti();
				//stampaStoricoPrestiti();
				break;
			case 12:
				operatoreView.stampaNumPrestitiTotali(numPrestitiTotali());
				break;
			case 13:
				operatoreView.stampaNumProrogheTotali(numProrogheTotali());
				break;
			case 14:
				operatoreView.stampaNuMPrestitiPerFruitore(mainManager.getFruitore().numPrestitiPerFruitore());
				break;
			case 15:
				operatoreView.stampaRisorsaPiuPrestata(risorsaPiuPrestataTotale());
				break;
			case 0:
				uscita = true;
				break;
			}
		}while(uscita != true);
	}

	public void login() {
		Operatore operatore = new Operatore(ConstantsCittadino.NOME_OPERATORE, Constants.MAGGIORENNE);
		if(operatoreView.inserisciIdInput().equalsIgnoreCase(operatore.getNome())){
			init(operatore);
		}
		else{
			operatoreView.stampaNoOperatore();
		}
	}

	
	public void aggiungiRisorsa() {
		int selezione = InputDati.selezionaElementoDaArray(ConstantsRisorsa.TIPOLOGIE_RISORSE);
		String key = ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione];
		mainManager.getRisorsaControllerByKey(key).crea();
//		if(ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione].equals("LIBRI")){
//			mainManager.getLibroController().crea(); 
//		}
//		else if(ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione].equals("FILM")){
//			mainManager.getFilmController().crea();
//		}
	}
	


	public void rimuoviRisorsa(){
		int selezione = InputDati.selezionaElementoDaArray(ConstantsRisorsa.TIPOLOGIE_RISORSE);
		String key = ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione];
		
		Risorsa r = mainManager.getRisorsaControllerByKey(key).selezionaRisorsa();
		
		mainManager.getRisorsaControllerByKey(key).rimuoviRisorsa(r);
	}

	public void stampaRisorse(){
		
		for(String s : ConstantsRisorsa.TIPOLOGIE_RISORSE) {
			mainManager.getRisorsaControllerByKey(s).getRisorsaView().stampaCategoria();
			mainManager.getRisorsaControllerByKey(s).stampaArchivio();
		}				
	}

	public void ricercaTitolo(){
		int selezione = InputDati.selezionaElementoDaArray(ConstantsRisorsa.TIPOLOGIE_RISORSE);
		String inputTitolo = UtilitaCreazioneCampi.creaStringaConSpazi(ConstantsRisorsa.TITOLO);

		String key = ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione];
		List<Risorsa> risorse = mainManager.getRisorsaControllerByKey(key).ricercaTitolo(inputTitolo);
		mainManager.getRisorsaControllerByKey(key).stampaRisorseTrovate(risorse);
	}

	
	public void ricercaGenere(){
		int selezione = InputDati.selezionaElementoDaArray(ConstantsRisorsa.TIPOLOGIE_RISORSE);
		String inputGenere= UtilitaCreazioneCampi.creaStringaConSpazi(ConstantsRisorsa.GENERE);
		
		String key = ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione];
		List<Risorsa> risorse = mainManager.getRisorsaControllerByKey(key).ricercaGenere(inputGenere);
		mainManager.getRisorsaControllerByKey(key).stampaRisorseTrovate(risorse);
		
//		if(ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione].equals("LIBRI")){
//			risorse = mainManager.getLibroController().ricercaGenere(inputGenere);
//			mainManager.getLibroController().stampaRisorseTrovate(risorse);
//		}
//		else if(ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione].equals("FILM")){
//
//			risorse = mainManager.getFilmController().ricercaGenere(inputGenere);
//			mainManager.getFilmController().stampaRisorseTrovate(risorse);
//		}
	}

	public void ricercaAutore() {
		String inputAutore= UtilitaCreazioneCampi.creaStringaConSpazi(ConstantsRisorsa.AUTORE_LIBRO);
		List<Risorsa> risorse = ((LibroEntityController) mainManager.getRisorsaControllerByKey(ConstantsRisorsa.LIBRO)).ricercaAutore(inputAutore);
		mainManager.getRisorsaControllerByKey(ConstantsRisorsa.LIBRO).stampaRisorseTrovate(risorse);
	}
	
	public void ricercaRegista() {
		String inputRegista= UtilitaCreazioneCampi.creaStringaConSpazi(ConstantsRisorsa.REGISTA);
		List<Risorsa> risorse =((FilmEntityController) mainManager.getRisorsaControllerByKey(ConstantsRisorsa.FILM)).ricercaRegista(inputRegista);
		mainManager.getRisorsaControllerByKey(ConstantsRisorsa.FILM).stampaRisorseTrovate(risorse);
	}

	public void stampaStoricoRisorse(){
		for(String s : ConstantsRisorsa.TIPOLOGIE_RISORSE) {
			mainManager.getRisorsaControllerByKey(s).getRisorsaView().stampaCategoria();
			mainManager.getRisorsaControllerByKey(s).stampaStoricoArchivio();
		}	
	}
	

//	public void stampaPrestitiAttualiTotali(){
//		mainManager.getFruitore().stampaPrestitiAttualiTuttiFruitori();
//	}

	//LOGICA + STAMPA
	public void stampaStoricoPrestiti(){
		for(String s : ConstantsRisorsa.TIPOLOGIE_RISORSE) {
			 PrestitoEntityController controller =  mainManager.getPrestitoControllerByKey(s);
			 RisorsaEntityController contrRis = mainManager.getRisorsaControllerByKey(s); 
			 contrRis.getRisorsaView().stampaCategoria();
			 controller.stampaPrestiti(controller.getPrestitoModel().getArchivioStoricoPrestiti());
		}	
	}
	
//	public void stampaStoricoPrestiti(){
//		mainManager.getFruitore().stampaPrestitiStoriciTuttiFruitori();
//	}

	//E' LOGICA MA INTERAGISCE CON CLASSE PRESTITOCONTROLLER
	//HO PREFERITO FARLA QUI PIUTTOSTO CHE CREARE UNA CLASSE OPERATORE MODEL
	public int numPrestitiTotali(){
		int count = 0;
		for(String s : ConstantsRisorsa.TIPOLOGIE_RISORSE) {
			count = count + mainManager.getPrestitoControllerByKey(s).prestitiPerAnno();
		}
		return count;
	}
	
	public int numProrogheTotali(){
		int count = 0;
		for(String s : ConstantsRisorsa.TIPOLOGIE_RISORSE) {
		count = count + mainManager.getPrestitoControllerByKey(s).proroghePerAnno();
		}
		return count;
	}
	

	public Map<String, Integer> risorsaPiuPrestataTotale(){
	Map<String, Integer> ris = new HashMap<>();
	Map<String, Integer> nuovo = new HashMap<>();
		for(String s : ConstantsRisorsa.TIPOLOGIE_RISORSE) {
			ris.putAll(mainManager.getPrestitoControllerByKey(s).getMappaRisorsaPiuPrestata());
		}
		if(ris.isEmpty()){
			return ris;
		}
		//TROVO IL MAX DA UN HASHMAP
		 int maxValueInMap=(Collections.max(ris.values()));  
	        for (Entry<String, Integer> entry : ris.entrySet()) {  
	            if (entry.getValue()==maxValueInMap) {
	            	nuovo.put(entry.getKey(), entry.getValue());
	            }
	        }
	     return nuovo;
	}

	

	@Override
	protected String titolo() {
		String titolo = "MENU OPERATORE";
		return titolo;
	}


	@Override
	protected String[] opzioni() {
		String opzioni[] = {
				"STAMPA FRUITORI ATTUALI",
				"AGGIUNGI RISORSA",
				"RIMUOVI RISORSA",
				"STAMPA ARCHIVIO RISORSE",
				"RICERCA RISORSA- PER TITOLO",
				"RICERCA RISORSA- PER GENERE",
				"RICECA RISORSA- PER AUTORE",
				"RICERCA RISORSA- PER REGISTA",
				"STAMPA STORICO FRUITORI",
				"STAMPA STORICO RISORSE",
//				"STAMPA PRESTITI ATTUALI",
				"STAMPA STORICO PRESTITI",
				"NUMERO PRESTITI PER ANNO",
				"NUMERO PROROGHE PER ANNO",
				"NUMERO PRESTITI PER FRUITORE",
				"NOME RISORSA PIU PRESTATA"}; 
		
		return opzioni;
	}

	

	

}