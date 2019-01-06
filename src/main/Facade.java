package main;



import controller.Controller;
import controller.CreatoreMenu;
import mylib.Constants;
import mylib.MyMenu;


public class Facade extends CreatoreMenu {

	MyMenu menu = crea();

	Controller controller;
//	private FruitoreController fruitore;
//	private OperatoreController operatore;
//
//	private Map<String, RisorsaEntityController> risorseEntityControllerMap;
//	private Map<String, PrestitoEntityController> prestitiEntityControllerMap;
//	
	public Facade(){
		
		controller = new Controller();
		//CREO CONTROLLER MENU PRINCIPALI
//		fruitore = new FruitoreController(this);
//		operatore = new OperatoreController(this);
//		
//		//CREO SOTTOCONTROLLER NECESSARI PER IL FUNZIONAMENTO DEL MENU PRINCIPALI
//		this.risorseEntityControllerMap = new HashMap<>();
//		this.prestitiEntityControllerMap = new HashMap<>();
//
//		this.risorseEntityControllerMap.put(ConstantsRisorsa.LIBRO, new LibroEntityController());
//		this.risorseEntityControllerMap.put(ConstantsRisorsa.FILM,  new FilmEntityController());
//		
//		this.prestitiEntityControllerMap.put(ConstantsRisorsa.LIBRO, new PrestitoEntityController(new PrestitoLibroView(), new PrestitoModel()));
//		this.prestitiEntityControllerMap.put(ConstantsRisorsa.FILM,  new PrestitoEntityController(new PrestitoFilmView(), new PrestitoModel()));
	}																//QUI NON HO CREATO DUE PRESTITO LIBRO ENTITY CONTROLLER
////																	//POICHE' PRESTITO ENTITY CONTROLLER FA RIF AI PRESTITI IN GENERALE, IL TIPO E' CONTENUTO IN RISORSA

	public void init(){
		boolean uscita = false;
		do{
			switch(menu.scegli()){
			case 1:
				controller.getFruitore().aggiungi(); //ISCRIZIONE FRUITORE
				break;
			case 2:
				controller.getFruitore().login(); //MENU FRUITORE
				break;
			case 3:
				controller.getOperatore().login();//MENU OPERATORE
				break;
			case 0:
				uscita = true;
				break;
			}
		}while(uscita != true);
	}


	@Override
	public String titolo() {
		String titolo = "OPZIONI DI LOGIN";
		return titolo;
	}


	@Override
	public String[] opzioni() {
		String opzioni[] = {
				"Registra nuovo fruitore",
				"Accedi come fruitore",
				"Accedi come operatore"};
		return opzioni;
	}
	

//	public FruitoreController getFruitore() {
//		return fruitore;
//	}
//
//	public OperatoreController getOperatore() {
//		return operatore;
//	}
//
//	//METODO GENERICI PER OTTENERE UN RISORSAENTITYCONTROLLER
//	public RisorsaEntityController getRisorsaControllerByKey(String k){
//		return risorseEntityControllerMap.get(k);
//	}
//	//METODO GENERICI PER OTTENERE UN PRESTITOENTITYCONTROLLER
//	public PrestitoEntityController getPrestitoControllerByKey(String k){
//		return prestitiEntityControllerMap.get(k);
//	}
	
//	public Map<String, RisorsaEntityController> getRisorseEntityControllerMap() {
//		return risorseEntityControllerMap;
//	}
//
//
//	public Map<String, PrestitoEntityController> getPrestitiEntityControllerMap() {
//		return prestitiEntityControllerMap;
//	}

//
//	public RisorsaEntityController getFilmController() { //L'ISTANZA E' DI TIPO FILMENETITYCONTROLLER
//		return risorseEntityControllerMap.get(ConstantsRisorsa.FILM);
//	}
//
//	public RisorsaEntityController getLibroController() {
//		return risorseEntityControllerMap.get(ConstantsRisorsa.LIBRO);
//	}

//	public PrestitoEntityController getPrestitoLibroController() {
//		return prestitiEntityControllerMap.get(ConstantsRisorsa.LIBRO);
//	}
//
//	public PrestitoEntityController getPrestitoFilmController() {
//		return prestitiEntityControllerMap.get(ConstantsRisorsa.FILM);
//	}

}


