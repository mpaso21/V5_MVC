package mylib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilitaControllo {
	
	/**
	 * Questo metodo controlla che una stringa non contenga cifre e/o caratteri speciali
	 * @param nome
	 * @return
	 */
	public static boolean controlloStringa(String nome)
	{
		for (int i = 0; i < nome.length(); i++){
			if (!(Character.isLetter(nome.charAt(i)))){
				return false;
			}
		}
		return true;
	}

	public static boolean controlloStringaConSpazi(String nome)
	{
		for (int i = 0; i < nome.length(); i++){
			if (!(Character.isLetter(nome.charAt(i)))&& ! (Character.isWhitespace(nome.charAt(i)))){
				return false;
			}
		}
		return true;
	}
	/**
	 * Questo meotod controlla che una stringa contenga solo cifre 
	 * @param t
	 * @return
	 */
	public static boolean controlloNumero(String t){
		for (int i = 0; i < t.length(); i++){
			if (!(Character.isDigit(t.charAt(i)))){
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean controlloTelefono(String t){
		if(controlloNumero(t) && t.length()== 10){
			return true;
		}
		return false;
	}
	
	
	public static boolean controlloMatricola(String m){
		
			if (controlloNumero(m) && m.length() == 6){
				return true;
			}
			return false;
		}
	
	/**
	 * Controllo sesso M o F o m o f
	 * @param c
	 * @return
	 */
	public static boolean controlloGenere (char c){
		if(c == 'M' || c == 'F' || c == 'm' || c == 'f'){
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo per la creazione di un codice fiscale valido
	 * @return
	 */
	public static String creaCodiceFiscale() {
		  
		  boolean finito = false;
		  String codiceFiscale;
		  
		  do {
		   
		   codiceFiscale = InputDati.leggiStringaNonVuota("inserisci il tuo codice fiscale" );
		   
		   Pattern pattern = Pattern.compile("[a-z A-Z][a-z A-Z][a-z A-Z][a-z A-Z][a-z A-Z][a-z A-Z][0-9][0-9][a-z A-Z][0-9][0-9][a-z A-Z][0-9][0-9][0-9][a-z A-Z]");
		   
		   Matcher matcher = pattern.matcher(codiceFiscale);
		   finito = matcher.matches();   
		  
		  } while( !finito );
		  
		  return codiceFiscale;
		 }
	
	/**
	 * Meotodo per la creazione di una data valida
	 * @return
	 */
	public static String creaData() {
		  
		  StringBuilder stringa = new StringBuilder();
		  int anno = InputDati.leggiIntero("inserisci anno:", 1900, 2020);
		  int mese = InputDati.leggiIntero("inserisci mese: ", 1, 12 );
		  int giorno;
		  
		  //SE È BISESTILE:
		  if( ( anno % 400 == 0 || ( anno % 100 != 0 && anno % 4 == 0 ) ) && mese == 2 ) {
		   giorno = InputDati.leggiIntero("inserisci giorno: ", 1, 29 );
		  }
		  
		  else if( mese == 1 || mese == 3 || mese == 5 || mese == 7 || mese == 8 || mese == 10 || mese == 12 ) {
		   giorno = InputDati.leggiIntero("inserisci giorno: ", 1, 31 );
		  }
		  
		  else if( mese == 2 ) {
		   giorno = InputDati.leggiIntero("inserisci giorno: ", 1, 28 );
		  }
		  
		  else
		   giorno = InputDati.leggiIntero("inserisci giorno: ", 1, 30 );
		  
		  stringa.append(giorno);
		  stringa.append(" - ");
		  stringa.append(mese);
		  stringa.append(" - ");
		  stringa.append(anno);
		  
		  return stringa.toString();
		  
		  }
	
		public static int randomcompreso (int min, int max){
		int random;
		do{
			random = (int) (Math.random() * max+1);

		}
		while (random < min || random > max);

		return random;	
	}
		
//		private int generaNumeroLicenza(){
//			int numeroLicenze = UtilitaControllo.randomcompreso(1, 4);//RANDOM numero in base al numero di risorse possibili
//			return numeroLicenze;
//		}
	
}