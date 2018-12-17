package view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entity.Libro;
import entity.Risorsa;
import mylib.Constants;
import mylib.InputDati;
import mylib.UtilitaCreazioneCampi;

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
		.append("\n  REGISTA: ")
		.append(libro.getAutore() + " ")
		.append("\n  DURATA: ")
		.append(libro.getNumeroPagine())
		.append("\n  ANNO DI USCITA : ")
		.append(libro.getAnno())
		.append("\n  CASA PRODUTTRICE: ")
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
		String titolo = UtilitaCreazioneCampi.creaStringaConSpazi(Constants.TITOLO);
		String autore = UtilitaCreazioneCampi.creaStringaConSpazi(Constants.AUTORE_LIBRO);
		String lingua = UtilitaCreazioneCampi.creaStringa(Constants.LINGUA);
		String genere = UtilitaCreazioneCampi.creaStringa(Constants.GENERE);
		String casaEditrice = UtilitaCreazioneCampi.creaStringaConSpazi(Constants.CASA_EDITRICE);
		int numeroPagine = UtilitaCreazioneCampi.creaNumeroConMin(Constants.NUM_PAGINE, 30);
		int anno = UtilitaCreazioneCampi.creaNumeroConMinMax(Constants.ANNO_LIBRO, 0, LocalDateTime.now().getYear());
		Libro libro = new Libro(titolo, autore, lingua, genere, casaEditrice, anno, numeroPagine);
		return libro;
	}

//	/**
//	 * Metodo inputTitoloLibro permette all'utente di inserire il titolo del
//	 * libro richiesto.
//	 */
//	public String inputTitoloLibro(){
//		String titolo = UtilitaCreazioneCampi.creaStringaConSpazi(Constants.TITOLO);
//		return titolo;
//	}
//	/**
//	 * Metodo inputTitoloLibro permette all'utente di inserire il titolo del
//	 * libro richiesto.
//	 */
//	public String inputAutoreLibro(){
//		String autore = UtilitaCreazioneCampi.creaStringaConSpazi(Constants.AUTORE_LIBRO);
//		return  autore;
//	}
//	/**
//	 * Metodo inputTitoloLibro permette all'utente di inserire il titolo del
//	 * libro richiesto.
//	 */
//	public String inputGenereLibro(){
//		String genere = UtilitaCreazioneCampi.creaStringaConSpazi(Constants.RISORSA_GENERE);
//		return genere;
//	}
	@Override
	public void stampaCategoria() {
		System.out.println( Constants.LIBRO);
		
	}
	
	
}