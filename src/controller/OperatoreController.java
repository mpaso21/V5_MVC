package controller;

import java.util.List;

import controller.entitycontroller.RisorsaEntityController;
import entity.Risorsa;
import mylib.Constants;
import mylib.InputDati;
import mylib.MyMenu;
import mylib.UtilitaCreazioneCampi;
import view.FilmView;
import view.LibroView;
import view.OperatoreView;

public class OperatoreController implements Controller {
	
	private String titolo = "OPZIONI DI LOGIN";
	private String opzioni[] = {
	"STAMPA FRUITORI ATTUALI",
	"AGGIUNGI RISORSA",
	"RIMUOVI RISORSA",
	"STAMPA ARCHIVIO RISORSE",
	"RICERCA RISORSA- PER TITOLO",
	"RICERCA RISORSA- PER GENERE",
	"RICECA RISORSA- PER AUTORE",
	"RICERCA RISORSA- PER REGISTA"};
	private MyMenu m = new MyMenu(titolo, opzioni);
	private boolean uscita = false;
	private LoginController mainManager;
	private OperatoreView operatoreView ;
	private RisorsaEntityController libroController;
	private RisorsaEntityController filmController;
	
	public OperatoreController(LoginController mainManager){
		this.mainManager = mainManager;
		this.operatoreView = new  OperatoreView();
		this.libroController = mainManager.getLibroController();
		this.filmController = mainManager.getFilmController();
	}	


	public void init()	{
		
		do{

			switch(m.scegli()){
			case 1:
				mainManager.getFruitore().stampaFruitori();
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
			case 0:
				uscita = true;
				break;
			}
		}while(uscita != true);
	}

	public void login() {
		if(this.operatoreView.inserisciIdInput().equalsIgnoreCase("ADMIN")){
			init();
		}
		else{
			operatoreView.stampaNoOperatore();
		}
	}
	
	/* TENTARE DISPERATAMENTE DI IMPLEMENTARE IL PATTERN COMMAND*/
	public void aggiungiRisorsa() {
		int selezione = InputDati.selezionaElementoDaArray(Constants.TIPOLOGIE_RISORSE);
		if(Constants.TIPOLOGIE_RISORSE[selezione].equals("LIBRI")){
			this.libroController.crea();
		}
		else if(Constants.TIPOLOGIE_RISORSE[selezione].equals("FILM")){
			this.filmController.crea();
		}
	}

	public void rimuoviRisorsa(){
		int selezione = InputDati.selezionaElementoDaArray(Constants.TIPOLOGIE_RISORSE);
		if(Constants.TIPOLOGIE_RISORSE[selezione].equals("LIBRI")){
			this.libroController.rimuovi();
		}
		else if(Constants.TIPOLOGIE_RISORSE[selezione].equals("FILM")){
			this.filmController.rimuovi();
		}
	}
	
	public void stampaLibri(){
		((LibroView)(libroController.getRisorsaView())).stampaCategoria(); 
		this.libroController.stampaArchivio();						
	}//STAMPA CATEGORIA IN LIBRO,FILM VIEW MA IN REALTA' LA CATEGORIA E' NELLA RISORSA STESSA
	
	public void stampaFilm() {
		((FilmView)(filmController.getRisorsaView())).stampaCategoria();
		this.filmController.stampaArchivio();
	}
	
	//DA SEMPLIFICARE COME AGGIUNGI METODO E RIMUOVI
	public void ricercaTitolo(){
		int selezione = InputDati.selezionaElementoDaArray(Constants.TIPOLOGIE_RISORSE);
		String inputTitolo = UtilitaCreazioneCampi.creaStringaConSpazi(Constants.TITOLO);
		List<Risorsa> risorse;
		
		if(Constants.TIPOLOGIE_RISORSE[selezione].equals("LIBRI")){
				risorse = this.libroController.ricercaTitolo(inputTitolo);
				this.libroController.stampaRisorseTrovate(risorse);
		}
		else if(Constants.TIPOLOGIE_RISORSE[selezione].equals("FILM")){
	
			risorse = this.filmController.ricercaTitolo(inputTitolo);
			this.filmController.stampaRisorseTrovate(risorse);
		}
	}
	
	public void ricercaGenere(){
		int selezione = InputDati.selezionaElementoDaArray(Constants.TIPOLOGIE_RISORSE);
		String inputGenere= UtilitaCreazioneCampi.creaStringaConSpazi(Constants.GENERE);
		List<Risorsa> risorse;
		
		if(Constants.TIPOLOGIE_RISORSE[selezione].equals("LIBRI")){
				risorse = this.libroController.ricercaGenere(inputGenere);
				this.libroController.stampaRisorseTrovate(risorse);
		}
		else if(Constants.TIPOLOGIE_RISORSE[selezione].equals("FILM")){
	
			risorse = this.filmController.ricercaTitolo(inputGenere);
			this.filmController.stampaRisorseTrovate(risorse);
		}
	}
	
	public void ricercaRegista(){
		String inputRegista= UtilitaCreazioneCampi.creaStringaConSpazi(Constants.REGISTA);
		List<Risorsa> risorse;
		
		risorse = this.filmController.ricercaRegista(inputRegista);
		this.filmController.stampaRisorseTrovate(risorse);
	}
	
	public void ricercaAutore(){
		String inputAutore= UtilitaCreazioneCampi.creaStringaConSpazi(Constants.AUTORE_LIBRO);
		List<Risorsa> risorse;
		
		risorse = this.libroController.ricercaAutore(inputAutore);
		this.libroController.stampaRisorseTrovate(risorse);
	}
	
	


}