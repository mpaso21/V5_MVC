package mylib;

public class ConstantsPrestito {

	//IN PRESTITO VIEW
	public static final String NUM_PRESTITO = "INSERISCI IL NUMERO RELATIVO AL PRESTITO: ";
	public static final String NO_PRESTITO = "NON E' POSSIBILE EFFETTUARE IL PRESTITO";
	public static final String RINNOVO_NON_POSSIBILE = "HAI GIA' EFFETTUATO UN RINNOVO DI QUESTA RISORSA";
	public static final String PRESTITO_RINNOVATO = "PRESTITO RINNOVATO";
	public static final String ISCRIZIONE_RINNOVATA_TRA_POCO_PRESTITO = "DEVI ASPETTARE ANCORA PER POTER RINNOVARE IL PRESTITO."
			+ " PUOI RINNOVARLO DA: ";
	public static final String NUM_PRESTITI = "Il numero di prestiti per anno solare è: ";
	public static final String NUM_PROROGHE = "Il numero di proroghe per anno solare è: ";
	public static final String PRESTITI_VUOTO = "NON SONO STATI EFFETTUATI PRESTITI NELL'ANNO SOLARE";
	public static final String RISORSE_PIU_PRESTATE = "RISORSE PIU PRESTATE NELL'ANNO SOLARE: ";
	public static final String LIMITE_PRESTITO = "HAI RAGGIUNTO IL LIMITE DI PRESTITI PER QUESTA CATEGORIA";
	
	//IN CLASSE LIBRO
	public static final int DURATA_MAX_PRESTITO_LIBRO = 3;  //30 GG
	public static final int INTERVALLO_RICHIESTA_PROROGA_LIBRO = 3;
	public static final int NUM_MAX_PRESTITI_LIBRO = 3;
	
	//IN CLASSE FILM
	public static final int DURATA_MAX_PRESTITO_FILM = 3; //30 GG
	public static final int INTERVALLO_RICHIESTA_PROROGA_FILM = 3;
	public static final int NUM_MAX_PRESTITI_FILM = 3;
}
