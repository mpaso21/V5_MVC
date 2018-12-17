package mylib;

public class UtilitaCreazioneCampi {
	
	public static String creaStringa(String messaggio){   
		String nome;
		do{
			nome = mylib.InputDati.leggiStringaNonVuota(messaggio);
		}while(!(UtilitaControllo.controlloStringa(nome)));
		//verifica maiuscolo minouscolo
		return nome;
	}
	

	public static String creaStringaConSpazi(String messaggio){   
		String nome;
		do{//VERIFOC CHE NON è VUOTA
			nome = mylib.InputDati.leggiStringaNonVuota(messaggio);
		}while(!(UtilitaControllo.controlloStringaConSpazi(nome))); //VERIFICO CHE LA STRINGA CONTENGA SOLO LETTERE
		
		return nome;
	} 
	
	public static int creaNumeroConMin(String messaggio, int min){  
		return InputDati.leggiInteroConMinimo(messaggio, min);
	}
	
	public static int creaNumeroConMinMax(String messaggio, int min, int max){  
		return InputDati.leggiIntero(messaggio, min, max);
	}
}
