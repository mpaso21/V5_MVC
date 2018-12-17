package mylib;

public class Constants {
	

	public static final String[] TIPOLOGIE_RISORSE = {"LIBRI", "FILM"};
	
	public static final String ISCRITTO = "SEI DIVENTATO FRUITORE! ORA POTRAI USUFRUIRE DEI PRESTITI BIBLIOTECARI";
	public static final String NON_ISCRITTO = "NON PUOI DIVENTARE UN FRUITORE PERCHE' HAI MENO DI 18 ANNI";
	public static final String ELENCO_VUOTO = "L'ELENCO E' VUOTO";
	public static final String ISCRIZIONE_ORA = "PUOI RINNOVARE L'ISCRIZIONE ORA";
	public static final String ISCRIZIONE_RINNOVATA = "ISCRIZIONE RINNOVATA";
	public static final String ISCRIZIONE_TERMINI_SCADUTI = "LA TUA ISCRIZIONE E' SCADUTA. " +
			                                                "NON SEI PIU' UN FRUITORE DEL NOSTRO SERVIZIO";
	public static final String ISCRIZIONE_RINNOVATA_TRA_POCO = "DEVI ASPETTARE ANCORA PER POTER RINNOVARE L'ISCRIZIONE."
																+ " PUOI RINNOVARLA DA: ";
	public static final String RICHIESTA_ID = "INSERISCI IL TUO ID: ";
	public static final String ID_NON_PRESENTE = "L'ID INSERITO NON E' PRESENTE NELL'ELENCO FRUITORI";
	public static final String SOLO_OPERATORE = "NON SEI AUTORIZZATO";
	public static final String ASSEGNO_ID = "PER UTILIZZARE IL SERVIZIO PUOI USUFRUIRE DEL TUO ID: ";
	public static final String NOME_CITTADINO = "INSERISCI IL TUO NOME: ";
	public static final String ETA_CITTADINO = "INSERISCI LA TUA ETA': ";
	

	public static final String TITOLO = "INSERISCI IL TITOLO: ";
	public static final String AUTORE_LIBRO = "INSERISCI L'AUTORE: ";
	public static final String REGISTA = "INSERISCI IL REGISTA: "; 
	public static final String NUM_PAGINE = "INSERISCI IL NUMERO DI PAGINE: ";
	public static final String DURATA = "INSERISCI LA DURATA: ";
	public static final String ANNO_LIBRO = "INSERISCI L'ANNO DI PUBBLICAZIONE: ";
	public static final String ANNO_FILM = "INSERISCI L'ANNO DI USCITA";
	public static final String CASA_EDITRICE = "INSERISCI IL NOME DELLA CASA EDITRICE: ";
	public static final String CASA_PRODUTTRICE = "INSERISCI IL NOME DELLA CASA PRODUTTRICE: ";
	public static final String LINGUA = "INSERISCI LA LINGUA: ";
	public static final String GENERE = "INSERISCI IL GENERE: ";
	public static final String ERRORE_ARCHIVIO = "L'ARCHIVIO E' VUOTO";
	public static final String NUM_RISORSA = "INSERISCI IL NUMERO RELATIVO ALLA RISORSA: ";
	public static final String NUM_CATEGORIA = "INSERISCI IL NUMERO RELATIVO ALLA CATEGORIA: ";
	
	
	public static final String FRUITORE_GIA_PRESENTE = "SEI GIA PRESENTE NELL'ELENCO FRUITORI.";
	public static final String ISCRIZIONE_PRESTITO_ORA ="PUOI RINNOVARE IL PRESTITO ORA";
	public static final String PRESTITO_RINNOVATO = "PRESTITO RINNOVATO";
	public static final String ISCRIZIONE_TERMINI_SCADUTI_PRESTITI = "I TERMINI ENTRO CUI POTER RINNOVARE IL PRESTITO SONO CONCLUSI. " +
																	"DEVI ASSOLUTAMENTE RESTITUIRE LA RISORSA";
	public static final String ISCRIZIONE_RINNOVATA_TRA_POCO_PRESTITO = "DEVI ASPETTARE ANCORA PER POTER RINNOVARE IL PRESTITO."
																+ " PUOI RINNOVARLO DA: ";
	public static final String PRESTITO_NON_SCADUTO = "PRESTITO NON ANCORA SCADUTO";
	public static final String PRESTITO_SCADUTO = "PRESTITO SCADUTO";
	public static final String COPIE_ESAURITE = " ->LE COPIE SONO ESAURITE";
	public static final String LIMITE_PRESTITO_LIBRI = "HAI RAGGIUNTO IL LIMITE DI PRESTITI";
	public static final String RINNOVO_NON_POSSIBILE = "HAI GIA' EFFETTUATO UN RINNOVO DI QUESTA RISORSA";
	public static final String NUM_PRESTITO = "INSERISCI IL NUMERO RELATIVO AL PRESTITO: ";
 
	public static final String RISORSA_TITOLO = "NON E' STATA TROVATA ALCUNA RISORSA.";
	public static final String RISORSA_AUTORE = "NON E' STATA TROVATA NESSUNA RISORSA CON L'AUTORE INSERITO.";
	public static final String RISORSA_GENERE = "NON E' STATA TROVATA NESSUNA RISORSA CON IL GENERE INSERITO.";
	public static final String RISORSA_REGISTA = "NON E' STATA TROVATA NESSUNA RISORSA CON IL REGISTA INSERITO. ";
	public static final String LIMITE_PRESTITO_FILM = "HAI RAGGIUNTO IL LIMITE DI PRESTITI PER LA CATEGORIA FILM";
	public static final String ISCRIZIONE = "PROCEDI CON L'ISCRIZIONE";
	public static final String PRESTITI_VUOTO = "L'ELENCO DEI TUOI PRESTITI E' VUOTO";

	public static final String NO_PRESTITO = "NON E' POSSIBILE EFFETTUARE IL PRESTITO";

	public static final int MIN_LICENZA = 1;
	public static final int MAX_LICENZA = 5;

	public static final String LIBRO = "CATEGORIA LIBRO";

	public static final String FILM = "CATEGORIA FILM";

	public static final int NUM_SCADENZA = 3;

	public static final int NUM_TEMPO_PRIMA_RINNOVO = 1;

	public static final String RISORSA = " CATEGORIA RISORSA";

	public static final int RANDOM = 10000;

	public static final int MAGGIORENNE = 18;

	public static final int LIMITE_MAX_ETA = 90;

	public static final int LIMITE_MIN_ETA = 14;



	

	
}
