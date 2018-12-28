package controller;

import java.util.List;

import controller.entitycontroller.FilmEntityController;
import controller.entitycontroller.LibroEntityController;
import entity.Operatore;
import entity.Risorsa;
import mylib.Constants;
import mylib.ConstantsCittadino;
import mylib.ConstantsRisorsa;
import mylib.InputDati;
import mylib.MyMenu;
import mylib.UtilitaCreazioneCampi;
import view.risorsa.FilmView;
import view.risorsa.LibroView;
import view.cittadino.OperatoreView;

public class OperatoreController extends CreatoreMenu {

	 
	MyMenu m = crea();
	private boolean uscita = false;
	
	private LoginController mainManager;
	private OperatoreView operatoreView ;

	
	public OperatoreController(LoginController mainManager){//BRUTTA DIPEDENZA
		this.mainManager = mainManager; //SE SONO ENTRAMBI NELLO STESSO PACKAGE LA DIPENDENZA C'E'?
		this.operatoreView = new  OperatoreView();
	}	


	public void init(Operatore operatore)	{

		do{

			switch(m.scegli()){
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
				stampaLibri();
				stampaFilm();
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
				stampaLibriStorico();
				stampaFilmStorico();
				break;
			case 11:
				stampaPrestitiAttualiTotali();
				break;
			case 12:
				stampaPrestitiStoriciTotali();
				break;
			case 13:
				stampaNumPrestitiPerAnno();
				break;
			case 14:
				stampaNumProroghePerAnno();
				break;
			case 15:
				mainManager.getFruitore().stampaNumPrestitiPerFruitore();
				break;
			case 16:
				stampaRisorsaPiuPrestata();
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

	/* TENTARE DISPERATAMENTE DI IMPLEMENTARE IL PATTERN COMMAND*/
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
		if(ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione].equals("LIBRI")){
			mainManager.getLibroController().rimuovi(); 
		}
		else if(ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione].equals("FILM")){
			mainManager.getFilmController().rimuovi();
		}
	}

	public void stampaLibri(){
		((LibroView)(mainManager.getLibroController().getRisorsaView())).stampaCategoria(); 
		mainManager.getLibroController().stampaArchivio();					
	}

	public void stampaFilm() {
		((FilmView)(mainManager.getFilmController().getRisorsaView())).stampaCategoria();
		mainManager.getFilmController().stampaArchivio();
	}

	//DA SEMPLIFICARE COME AGGIUNGI METODO E RIMUOVI
	public void ricercaTitolo(){
		int selezione = InputDati.selezionaElementoDaArray(ConstantsRisorsa.TIPOLOGIE_RISORSE);
		String inputTitolo = UtilitaCreazioneCampi.creaStringaConSpazi(ConstantsRisorsa.TITOLO);
		List<Risorsa> risorse;

		if(ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione].equals("LIBRI")){
			risorse = mainManager.getLibroController().ricercaTitolo(inputTitolo);
			mainManager.getLibroController().stampaRisorseTrovate(risorse);
		}
		else if(ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione].equals("FILM")){

			risorse = mainManager.getFilmController().ricercaTitolo(inputTitolo);
			mainManager.getFilmController().stampaRisorseTrovate(risorse);
		}
	}

	public void ricercaGenere(){
		int selezione = InputDati.selezionaElementoDaArray(ConstantsRisorsa.TIPOLOGIE_RISORSE);
		String inputGenere= UtilitaCreazioneCampi.creaStringaConSpazi(ConstantsRisorsa.GENERE);
		List<Risorsa> risorse;

		if(ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione].equals("LIBRI")){
			risorse = mainManager.getLibroController().ricercaGenere(inputGenere);
			mainManager.getLibroController().stampaRisorseTrovate(risorse);
		}
		else if(ConstantsRisorsa.TIPOLOGIE_RISORSE[selezione].equals("FILM")){

			risorse = mainManager.getFilmController().ricercaGenere(inputGenere);
			mainManager.getFilmController().stampaRisorseTrovate(risorse);
		}
	}

	public void ricercaAutore(){
		String inputAutore= UtilitaCreazioneCampi.creaStringaConSpazi(ConstantsRisorsa.AUTORE_LIBRO);
		List<Risorsa> risorse = ((LibroEntityController) mainManager.getLibroController()).ricercaAutore(inputAutore);
		mainManager.getLibroController().stampaRisorseTrovate(risorse);
	}

	public void ricercaRegista(){
		String inputRegista= UtilitaCreazioneCampi.creaStringaConSpazi(ConstantsRisorsa.REGISTA);
		List<Risorsa> risorse = ((FilmEntityController) mainManager.getFilmController()).ricercaRegista(inputRegista);
		mainManager.getFilmController().stampaRisorseTrovate(risorse);
	}

	public void stampaLibriStorico(){
		((LibroView)(mainManager.getLibroController().getRisorsaView())).stampaCategoria(); 
		mainManager.getLibroController().stampaStoricoArchivio();
	}	

	public void stampaFilmStorico(){
		((FilmView)(mainManager.getFilmController().getRisorsaView())).stampaCategoria(); 
		mainManager.getFilmController().stampaStoricoArchivio();
	}	

	
	public void stampaPrestitiAttualiTotali(){
		mainManager.getFruitore().stampaPrestitiAttualiTuttiFruitori();
	}

	public void stampaPrestitiStoriciTotali(){
		mainManager.getFruitore().stampaPrestitiStoriciFruitori();
	}
	
	public void stampaNumPrestitiPerAnno(){
		mainManager.getPrestitoLibroController().prestitiPerAnno();
	}

	public void stampaNumProroghePerAnno(){
		mainManager.getPrestitoLibroController().proroghePerAnno();
	}

	public void stampaRisorsaPiuPrestata(){ //METODO STAMPA RISORSA PIU PRSTATO MESSO IN FRUITORE CONTROLLER PERCHE' DA QUI NON POTEVO ACCEDERE AL PRESTITO
		mainManager.getPrestitoLibroController().risorsaPiuPrestata();
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
				"STAMPA PRESTITI ATTUALI",
				"STAMPA STORICO PRESTITI",
				"NUMERO PRESTITI PER ANNO",
				"NUMERO PROROGHE PER ANNO",
				"NUMERO PRESTITI PER FRUITORE",
				"RISORSA PIU PRESTATA"}; 
		
		return opzioni;
	}

	

	

}