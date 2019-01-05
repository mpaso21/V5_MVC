package mylib;

public class ConstantsCittadino {
	
	//IN OPERATORE VIEW
	public static final String SOLO_OPERATORE = "NON SEI AUTORIZZATO";
	public static final String NOME_OPERATORE = "ADMIN";
	
	
	//IN FRUITORE VIEW
	public static final int LIMITE_MAX_ETA = 90;
	public static final int LIMITE_MIN_ETA = 14;
	public static final String ETA = "INSERISCI LA TUA ETA': ";
	public static final String ID_NON_PRESENTE = "L'ID INSERITO NON E' PRESENTE NELL'ELENCO FRUITORI";
	public static final String ISCRIZIONE = "PROCEDI CON L'ISCRIZIONE";
	public static final String ISCRIZIONE_TERMINI_SCADUTI = "LA TUA ISCRIZIONE E' SCADUTA. " +
            "NON SEI PIU' UN FRUITORE DEL NOSTRO SERVIZIO";
	public static final String ISCRIZIONE_RINNOVATA_TRA_POCO = "DEVI ASPETTARE ANCORA PER POTER RINNOVARE L'ISCRIZIONE."
			+ " PUOI RINNOVARLA DA: ";
	public static final String NON_ISCRITTO = "NON PUOI DIVENTARE UN FRUITORE PERCHE' HAI MENO DI 18 ANNI";
	public static final String ZERO_FRUITORI = "Non ci sono fruitori presenti in archivio. ";
	public static final String ASSEGNO_ID = "ISCRIZIONE ANDATA A BUON FINE. PER UTILIZZARE IL SERVIZIO PUOI USUFRUIRE DEL TUO ID: ";
	public static final String ISCRIZIONE_RINNOVATA = "ISCRIZIONE RINNOVATA";
	
	//IN FRUITORE MODEL
	public static final int NUM_SCADENZA = 10;
	public static final int NUM_TEMPO_PRIMA_RINNOVO = 3;
	
	
}
