package view.risorsa;

import java.time.LocalDateTime;

import builder.FilmBuilder;
import entity.Film;
import entity.Libro;
import entity.Risorsa;
import mylib.ConstantsRisorsa;
import mylib.Data;
import mylib.UtilitaControllo;
import mylib.UtilitaCreazioneCampi;

/**
 * La classe FilmView rappresenta l'Input/Output.
 * Mantiene tutte le stampe relative agli oggetti di tipo
 * Risorsa, pi� in specifico film. 
 * Mantiene inoltre i metodi che contengono input presi dall'utente.
 * @author Marika
 *
 */
public class FilmView extends RisorsaView{


	/**	
	 * Metodo creaFilmInput permette di inserire in input i valori necessari per creare la risorsa
	 * film.
	 * @return
	 */
	@Override
	public Risorsa crea() {

		String nome = UtilitaCreazioneCampi.creaStringaConSpazi(ConstantsRisorsa.TITOLO);
		String regista = UtilitaCreazioneCampi.creaStringaConSpazi(ConstantsRisorsa.REGISTA);
		int durata = UtilitaCreazioneCampi.creaNumeroConMin(ConstantsRisorsa.DURATA, 30); //DURATA IN MIN
		int annoDiUscita = UtilitaCreazioneCampi.creaNumeroConMinMax(ConstantsRisorsa.ANNO_FILM, 1895,LocalDateTime.now().getYear());
		String casaProduttrice = UtilitaCreazioneCampi.creaStringaConSpazi(ConstantsRisorsa.CASA_PRODUTTRICE);
		String lingua = UtilitaCreazioneCampi.creaStringa(ConstantsRisorsa.LINGUA);
		String genere = UtilitaCreazioneCampi.creaStringa(ConstantsRisorsa.GENERE);
		int numLicenze = UtilitaControllo.randomcompreso(ConstantsRisorsa.MIN_LICENZA,ConstantsRisorsa.MAX_LICENZA);
		
		FilmBuilder<FilmBuilder> filmBuilder = new FilmBuilder<>();
		filmBuilder.titolo(nome);
		filmBuilder.regista(regista);
		filmBuilder.durata(durata);
		filmBuilder.annoDiUscita(annoDiUscita);
		filmBuilder.casaProduttrice(casaProduttrice);
		filmBuilder.lingua(lingua);
		filmBuilder.genere(genere);
		filmBuilder.numLicenze(numLicenze);
		return filmBuilder.build();
		 
		
		
//		Film film = new Film(nome, regista,  durata,   //EVITO IL PASSAGGIO DI TUTTI QUESTI PARAMETRI QUI
//				annoDiUscita, casaProduttrice, lingua, genere, numLicenze);
//		return film;
	}


	/**
	 * Metodo stampaFilm fornisce a video una rappresentazione contenente le 
	 * propriet� della risorsa Film.
	 * @param f
	 * @return
	 */
	@Override
	public String  stampa(Risorsa f){
		Film film = (Film)f;
		StringBuilder stringa = new StringBuilder();
//		stringa.append("TITOLO: ")
//		.append(film.getNome())
//		.append("\n  REGISTA: ")
//		.append(film.getRegista() + " ")
//		.append("\n  DURATA: ")
//		.append(film.getDurata())
//		.append("\n  ANNO DI USCITA : ")
//		.append(film.getAnnoDiUscita())
//		.append("\n  CASA PRODUTTRICE: ")
//		.append(film.getCasaProduttrice())
//		.append("\n  LINGUA: ")
//		.append(film.getLingua())
//		.append("\n  GENERE: ")
//		.append(film.getGenere())
//		.append("\n  LICENZE D'USO: ")
//		.append(film.getNumeroLicenze());
//		return stringa.toString();
		stringa.append(String.format("%14s | %15s | %15s | %10s | %20s | %10s | %10s | %7s |", film.getNome(), film.getRegista(),
											film.getDurata(), film.getAnnoDiUscita(), film.getCasaProduttrice(), film.getLingua(), film.getGenere(), film.getNumeroLicenze() ));
		return stringa.toString(); 

	}


	/**
	 * stampaCategoria stampa a video CATEGORIA FILM
	 */
	@Override
	public void stampaCategoria() {
		System.out.println(ConstantsRisorsa.FILM);

	}

}
