package view;

import java.util.List;

import entity.Film;
import entity.Libro;
import entity.Risorsa;
import mylib.Constants;
import mylib.InputDati;
import mylib.UtilitaCreazioneCampi;

public class FilmView extends RisorsaView{
/**
 * Metodo stampaFilm fornisce a video una rappresentazione contenente le 
 * proprietà della risorsa Film.
 * @param f
 * @return
 */
	@Override
	public String  stampa(Risorsa f){
		Film film = (Film)f;
		StringBuilder stringa = new StringBuilder();
		stringa.append("TITOLO: ")
		.append(film.getNome())
		.append("\n  REGISTA: ")
		.append(film.getRegista() + " ")
		.append("\n  DURATA: ")
		.append(film.getDurata())
		.append("\n  ANNO DI USCITA : ")
		.append(film.getAnnoDiUscita())
		.append("\n  CASA PRODUTTRICE: ")
		.append(film.getCasaProduttrice())
		.append("\n  LINGUA: ")
		.append(film.getLingua())
		.append("\n  GENERE: ")
		.append(film.getGenere())
		.append("\n  LICENZE D'USO: ")
		.append(film.getNumeroLicenze());
		return stringa.toString();	
	
	}
/**	
 * Metodo creaFilmInput permette di inserire in input i valori necessari per creare la risorsa
 * film.
 * @return
 */
	@Override
	public Risorsa crea() {
		
		String nome = UtilitaCreazioneCampi.creaStringaConSpazi(Constants.TITOLO);
		String regista = UtilitaCreazioneCampi.creaStringaConSpazi(Constants.REGISTA);
		int durata = UtilitaCreazioneCampi.creaNumeroConMin(Constants.DURATA, 30); //DURATA IN MIN
		int annoDiUscita = UtilitaCreazioneCampi.creaNumeroConMinMax(Constants.ANNO_FILM, 1895, 2018);
		String casaProduttrice = UtilitaCreazioneCampi.creaStringaConSpazi(Constants.CASA_PRODUTTRICE);
		String lingua = UtilitaCreazioneCampi.creaStringa(Constants.LINGUA);
		String genere = UtilitaCreazioneCampi.creaStringa(Constants.GENERE);
		Film film = new Film(nome, regista,  durata, 
				annoDiUscita, casaProduttrice, lingua, genere);
		return film;
	}

//	/**
//	 * Metodo inputTitoloFilm permette all'utente di inserire il titolo del
//	 * film richiesto.
//	 */
//	public String inputTitoloFilm(){
//		String titolo = UtilitaCreazioneCampi.creaStringaConSpazi(Constants.TITOLO);
//		return titolo;
//	}
//	/**
//	 * Metodo inputRegistaFilm permette all'utente di inserire il nome dell'autore del 
//	 * film richiesto.
//	 */
//	public String inputRegistaFilm(){
//		String autore = UtilitaCreazioneCampi.creaStringaConSpazi(Constants.REGISTA);
//		return  autore;
//	}
//	/**
//	 * Metodo inputGenereFilm permette all'utente di inserire il genere del
//	 * film richiesto.
//	 */
//	public String inputGenereFilm(){
//		String genere = UtilitaCreazioneCampi.creaStringaConSpazi(Constants.RISORSA_GENERE);
//		return genere;
//	}

	@Override
	public void stampaCategoria() {
		System.out.println(Constants.FILM);
		
	}
	
}
