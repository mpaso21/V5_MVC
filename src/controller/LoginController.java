package controller;


import controller.entitycontroller.RisorsaEntityController;
import model.FilmModel;
import model.LibroModel;
import mylib.Constants;
import mylib.InputDati;
import mylib.MyMenu;
import view.FilmView;
import view.LibroView;

public class LoginController implements Controller {
		
	String titolo = "OPZIONI DI LOGIN";
	String opzioni[] = {
	"Registra nuovo fruitore",
	"Accedi come fruitore",
	"Accedi come operatore"};
	MyMenu m = new MyMenu(titolo, opzioni);
	boolean uscita = false;

	
	private FruitoreController fruitore;
	private OperatoreController operatore;
	private RisorsaEntityController filmController;
	private RisorsaEntityController libroController;
	
	
	public LoginController(){
		
		filmController = new RisorsaEntityController(new FilmView(), new FilmModel());
		libroController = new RisorsaEntityController(new LibroView(), new LibroModel());
		fruitore = new FruitoreController(this);
		operatore = new OperatoreController(this);
	}



	public FruitoreController getFruitore() {
		return fruitore;
	}


	public OperatoreController getOperatore() {
		return operatore;
	}


	public void setFruitore(FruitoreController fruitore) {
		this.fruitore = fruitore;
	}


	public void setOperatore(OperatoreController operatore) {
		this.operatore = operatore;
	}

	

	public RisorsaEntityController getFilmController() {
		return filmController;
	}



	public RisorsaEntityController getLibroController() {
		return libroController;
	}



	public void init(){
		
		do{
			switch(m.scegli()){
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
}


