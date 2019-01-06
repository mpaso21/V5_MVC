package view.risorsa;

import java.time.LocalDateTime;
import builder.LibroBuilder;
import entity.Libro;
import entity.Risorsa;
import mylib.ConstantsRisorsa;
import mylib.UtilitaControllo;
import mylib.UtilitaCreazioneCampi;

/**
 * La classe LibroView rappresenta l'Input/Output.
 * Mantiene tutte le stampe relative agli oggetti di tipo
 * Risorsa, pi� in specifico libri.
 * Mantiene inoltre i metodi che contengono input presi dall'utente.
 * @author Marika
 *
 */
public class LibroView extends RisorsaView {
	
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
		int numLicenze = UtilitaControllo.randomcompreso(ConstantsRisorsa.MIN_LICENZA,ConstantsRisorsa.MAX_LICENZA);
		int anno = UtilitaCreazioneCampi.creaNumeroConMinMax(ConstantsRisorsa.ANNO_LIBRO, 0, LocalDateTime.now().getYear());
		//NEI TEST INSERISCO IO IL NUM LICENZE. NEL PROGRAMMA METTO RANDOM

		LibroBuilder<LibroBuilder> libroBuilder = new LibroBuilder<>();
		libroBuilder.titolo(titolo);
		libroBuilder.autore(autore);
		libroBuilder.lingua(lingua);
		libroBuilder.genere(genere);
		libroBuilder.casaEditrice(casaEditrice);
		libroBuilder.anno(anno);
		libroBuilder.numeroPagine(numeroPagine);
		libroBuilder.numLicenze(numLicenze);
		return libroBuilder.build();
		
//		Libro libro = new Libro(titolo, autore, lingua, genere, casaEditrice, anno, numeroPagine, numLicenze);
//		return libro;
	}
	
	/**
	 * Metodo stampaLibro fornisce a video una rappresentazione contenente le 
	 * propriet� della risorsa Libro.
	 * @param f
	 * @return
	 */
	@Override
	public String stampa(Risorsa l) {
		Libro libro = (Libro)l;
		StringBuilder stringa = new StringBuilder();
//		stringa.append("TITOLO: ")
//		.append(libro.getNome())
//		.append("\n  AUTORE: ")
//		.append(libro.getAutore() + " ")
//		.append("\n  NUMERO DI PAGINE: ")
//		.append(libro.getNumeroPagine())
//		.append("\n  ANNO DI PUBBLICAZIONE: ")
//		.append(libro.getAnno())
//		.append("\n  CASA EDITRICE: ")
//		.append(libro.getCasaEditrice())
//		.append("\n  LINGUA: ")
//		.append(libro.getLingua())
//		.append("\n  GENERE: ")
//		.append(libro.getGenere())
//		.append("\n  LICENZE D'USO: ")
//		.append(libro.getNumeroLicenze());
//
//		return stringa.toString();
		stringa.append(String.format("%14s | %15s | %15s | %10s | %20s | %10s | %10s | %7s |", libro.getNome(), libro.getAutore(),
				libro.getNumeroPagine(), libro.getAnno(), libro.getCasaEditrice(), libro.getLingua(), libro.getGenere(), libro.getNumeroLicenze() ));
		return stringa.toString(); 

	}


	/**
	 * stampaCategoria stampa a video CATEGORIA LIBRO
	 */
	@Override
	public void stampaCategoria() {
		System.out.println(ConstantsRisorsa.LIBRO);

	}

}