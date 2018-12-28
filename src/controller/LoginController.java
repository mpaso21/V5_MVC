package controller;


import java.util.HashMap;
import java.util.Map;

import controller.entitycontroller.PrestitoEntityController;
import controller.entitycontroller.RisorsaEntityController;
import model.prestito.PrestitoModel;
import model.risorsa.FilmModel;
import model.risorsa.LibroModel;
import mylib.ConstantsRisorsa;
import mylib.MyMenu;
import view.prestito.PrestitoFilmView;
import view.prestito.PrestitoLibroView;
import view.risorsa.FilmView;
import view.risorsa.LibroView;

public class LoginController extends CreatoreMenu {

	MyMenu m1 = crea();
	boolean uscita = false;

	
	private FruitoreController fruitore;
	private OperatoreController operatore;

	private Map<String, RisorsaEntityController> risorseEntityControllerMap;
	private Map<String, PrestitoEntityController> prestitiEntityControllerMap;
	
	public LoginController(){
		//CREO CONTROLLER MENU PRINCIPALI
		fruitore = new FruitoreController(this);
		operatore = new OperatoreController(this);
		
		//CREO ANCHE SOTTOCONTROLLER NECESSARI PER IL FUNZIONAMENTO DEL MENU PRINCIPALES
		this.risorseEntityControllerMap = new HashMap<>();
		this.prestitiEntityControllerMap = new HashMap<>();

		this.risorseEntityControllerMap.put(ConstantsRisorsa.LIBRO, new RisorsaEntityController(new LibroView(), new LibroModel()));
		this.risorseEntityControllerMap.put(ConstantsRisorsa.FILM,  new RisorsaEntityController(new FilmView(), new FilmModel()));
		
		this.prestitiEntityControllerMap.put(ConstantsRisorsa.LIBRO, new PrestitoEntityController(new PrestitoLibroView(), new PrestitoModel()));
		this.prestitiEntityControllerMap.put(ConstantsRisorsa.FILM,  new PrestitoEntityController(new PrestitoFilmView(), new PrestitoModel()));
	}


	public void init(){

		do{
			switch(m1.scegli()){
			case 1:
				getFruitore().aggiungi();
				break;
			case 2:
				getFruitore().login();
				break;
			case 3:
				getOperatore().login();
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
	

	public FruitoreController getFruitore() {
		return fruitore;
	}

	public OperatoreController getOperatore() {
		return operatore;
	}

	
	public Map<String, RisorsaEntityController> getRisorseEntityControllerMap() {
		return risorseEntityControllerMap;
	}


	public Map<String, PrestitoEntityController> getPrestitiEntityControllerMap() {
		return prestitiEntityControllerMap;
	}


	public RisorsaEntityController getFilmController() {
		return risorseEntityControllerMap.get(ConstantsRisorsa.FILM);
	}

	public RisorsaEntityController getLibroController() {
		return risorseEntityControllerMap.get(ConstantsRisorsa.LIBRO);
	}

	public PrestitoEntityController getPrestitoLibroController() {
		return prestitiEntityControllerMap.get(ConstantsRisorsa.LIBRO);
	}

	public PrestitoEntityController getPrestitoFilmController() {
		return prestitiEntityControllerMap.get(ConstantsRisorsa.FILM);
	}
	
	
	//GENERICI
	public RisorsaEntityController getRisorsaControllerByKey(String k){
		return this.risorseEntityControllerMap.get(k);
	}
	
	public PrestitoEntityController getPrestitoControllerByKey(String k){
		return this.prestitiEntityControllerMap.get(k);
	}
}


