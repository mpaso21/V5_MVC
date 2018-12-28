package view.risorsa;

import java.time.LocalDateTime;
import entity.Film;
import entity.Risorsa;
import mylib.ConstantsRisorsa;
import mylib.UtilitaCreazioneCampi;

/**
 * La classe FilmView rappresenta l'Input/Output.
 * Mantiene tutte le stampe relative agli oggetti di tipo
 * Risorsa, più in specifico film. 
 * Mantiene inoltre i metodi che contengono input presi dall'utente.
 * @author Marika
 *
 */
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

		String nome = UtilitaCreazioneCampi.creaStringaConSpazi(ConstantsRisorsa.TITOLO);
		String regista = UtilitaCreazioneCampi.creaStringaConSpazi(ConstantsRisorsa.REGISTA);
		int durata = UtilitaCreazioneCampi.creaNumeroConMin(ConstantsRisorsa.DURATA, 30); //DURATA IN MIN
		int annoDiUscita = UtilitaCreazioneCampi.creaNumeroConMinMax(ConstantsRisorsa.ANNO_FILM, 1895,LocalDateTime.now().getYear());
		String casaProduttrice = UtilitaCreazioneCampi.creaStringaConSpazi(ConstantsRisorsa.CASA_PRODUTTRICE);
		String lingua = UtilitaCreazioneCampi.creaStringa(ConstantsRisorsa.LINGUA);
		String genere = UtilitaCreazioneCampi.creaStringa(ConstantsRisorsa.GENERE);
		Film film = new Film(nome, regista,  durata, 
				annoDiUscita, casaProduttrice, lingua, genere);
		return film;
	}

	/**
	 * stampaCategoria stampa a video CATEGORIA FILM
	 */
	@Override
	public void stampaCategoria() {
		System.out.println(ConstantsRisorsa.FILM);

	}

}
