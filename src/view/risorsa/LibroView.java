package view.risorsa;

import java.time.LocalDateTime;
import entity.Libro;
import entity.Risorsa;
import mylib.ConstantsRisorsa;
import mylib.UtilitaCreazioneCampi;

/**
 * La classe LibroView rappresenta l'Input/Output.
 * Mantiene tutte le stampe relative agli oggetti di tipo
 * Risorsa, più in specifico libri.
 * Mantiene inoltre i metodi che contengono input presi dall'utente.
 * @author Marika
 *
 */
public class LibroView extends RisorsaView {
	/**
	 * Metodo stampaLibro fornisce a video una rappresentazione contenente le 
	 * proprietà della risorsa Libro.
	 * @param f
	 * @return
	 */
	@Override
	public String stampa(Risorsa l) {
		Libro libro = (Libro)l;
		StringBuilder stringa = new StringBuilder();
		stringa.append("TITOLO: ")
		.append(libro.getNome())
		.append("\n  AUTORE: ")
		.append(libro.getAutore() + " ")
		.append("\n  NUMERO DI PAGINE: ")
		.append(libro.getNumeroPagine())
		.append("\n  ANNO DI PUBBLICAZIONE: ")
		.append(libro.getAnno())
		.append("\n  CASA EDITRICE: ")
		.append(libro.getCasaEditrice())
		.append("\n  LINGUA: ")
		.append(libro.getLingua())
		.append("\n  GENERE: ")
		.append(libro.getGenere())
		.append("\n  LICENZE D'USO: ")
		.append(libro.getNumeroLicenze());

		return stringa.toString();	
	}
	/**	
	 * Metodo creaLibronput permette di inserire in input i valori necessari per creare la risorsa
	 * libro.
	 * @return
	 */
	@Override
	public Risorsa crea() {
		String titolo = UtilitaCreazioneCampi.creaStringaConSpazi(ConstantsRisorsa.TITOLO);
		String autore = UtilitaCreazioneCampi.creaStringaConSpazi(ConstantsRisorsa.AUTORE_LIBRO);
		String lingua = UtilitaCreazioneCampi.creaStringa(ConstantsRisorsa.LINGUA);
		String genere = UtilitaCreazioneCampi.creaStringa(ConstantsRisorsa.GENERE);
		String casaEditrice = UtilitaCreazioneCampi.creaStringaConSpazi(ConstantsRisorsa.CASA_EDITRICE);
		int numeroPagine = UtilitaCreazioneCampi.creaNumeroConMin(ConstantsRisorsa.NUM_PAGINE, ConstantsRisorsa.MIN_PAGINE);
		int anno = UtilitaCreazioneCampi.creaNumeroConMinMax(ConstantsRisorsa.ANNO_LIBRO, 0, LocalDateTime.now().getYear());
		Libro libro = new Libro(titolo, autore, lingua, genere, casaEditrice, anno, numeroPagine);
		return libro;
	}


	/**
	 * stampaCategoria stampa a video CATEGORIA LIBRO
	 */
	@Override
	public void stampaCategoria() {
		System.out.println(ConstantsRisorsa.LIBRO);

	}

}