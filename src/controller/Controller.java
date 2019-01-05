package controller;

import java.util.HashMap;
import java.util.Map;

import controller.entitycontroller.FilmEntityController;
import controller.entitycontroller.LibroEntityController;
import controller.entitycontroller.PrestitoEntityController;
import controller.entitycontroller.RisorsaEntityController;
import model.prestito.PrestitoModel;
import mylib.ConstantsRisorsa;
import view.prestito.PrestitoFilmView;
import view.prestito.PrestitoLibroView;

public class Controller {

	
	//CLASSE COME INTERMEDIARIO PER TOGLIERE LA DIPENDENZA DI LOGIN CONTROLLER CON FRUITORE/OPERATORE CONTROLLER,
	//COSI LOGIN CONTROLLER PUO' FUNGERE DA FACADE.
	private FruitoreController fruitore;
	private OperatoreController operatore;

	private Map<String, RisorsaEntityController> risorseEntityControllerMap;
	private Map<String, PrestitoEntityController> prestitiEntityControllerMap;
	
	public Controller(){
		//CREO CONTROLLER MENU PRINCIPALI
		fruitore = new FruitoreController(this);
		operatore = new OperatoreController(this);
		
		//CREO SOTTOCONTROLLER NECESSARI PER IL FUNZIONAMENTO DEL MENU PRINCIPALI
		this.risorseEntityControllerMap = new HashMap<>();
		this.prestitiEntityControllerMap = new HashMap<>();

		this.risorseEntityControllerMap.put(ConstantsRisorsa.LIBRO, new LibroEntityController());
		this.risorseEntityControllerMap.put(ConstantsRisorsa.FILM,  new FilmEntityController());
		
		this.prestitiEntityControllerMap.put(ConstantsRisorsa.LIBRO, new PrestitoEntityController(new PrestitoLibroView(), new PrestitoModel()));
		this.prestitiEntityControllerMap.put(ConstantsRisorsa.FILM,  new PrestitoEntityController(new PrestitoFilmView(), new PrestitoModel()));
	}	
	
	public FruitoreController getFruitore() {
		return fruitore;
	}

	public OperatoreController getOperatore() {
		return operatore;
	}

	//METODO GENERICI PER OTTENERE UN RISORSAENTITYCONTROLLER
	public RisorsaEntityController getRisorsaControllerByKey(String k){
		return risorseEntityControllerMap.get(k);
	}
	//METODO GENERICI PER OTTENERE UN PRESTITOENTITYCONTROLLER
	public PrestitoEntityController getPrestitoControllerByKey(String k){
		return prestitiEntityControllerMap.get(k);
	}
}
