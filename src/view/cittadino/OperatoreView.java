package view.cittadino;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

import mylib.Constants;
import mylib.ConstantsCittadino;
import mylib.ConstantsPrestito;

/**
 * La classe OperatoreView rappresenta la vista dell'operatore.
 * Contiene un metodo per l'input e un metodo per l'output.
 * @author Marika
 *
 */
public class OperatoreView {

/**		
 * Metodo inserisciIdInput permette l'inserimento dell'id da parte
 * dell'operatore.
 * @return
 */
	public String inserisciIdInput(){
		String id = mylib.InputDati.leggiStringaNonVuota(Constants.RICHIESTA_ID);
		return id;
	}
	
	public void stampaNoOperatore(){
		System.out.println(ConstantsCittadino.SOLO_OPERATORE);
	}
	
	public void stampaRisorsaPiuPrestata(Map <String, Integer> map){ //METODO STAMPA RISORSA PIU PRSTATO MESSO IN FRUITORE CONTROLLER PERCHE' DA QUI NON POTEVO ACCEDERE AL PRESTITO
		if(map.size() == 0){
			stampaZeroPrestiti();
		}
		else{
			System.out.println(ConstantsPrestito.RISORSE_PIU_PRESTATE + LocalDateTime.now().getYear() +  "\n");
			System.out.println(Arrays.asList(map));
		}	
    }

	public void stampaNuMPrestitiPerFruitore(Map <String, Integer> map){
		if(map.size() == 0){
			stampaZeroFruitori();
		}
		else{
			System.out.println(ConstantsPrestito.NUM_PRESTITI_PER_FRUITORE + LocalDateTime.now().getYear() +  "\n");
			System.out.println(Arrays.asList(map));
		}
	}
	public void stampaZeroFruitori(){
		System.out.println(ConstantsCittadino.ZERO_FRUITORI);
	}
	
	public void stampaZeroPrestiti() {
		System.out.println(ConstantsPrestito.PRESTITI_VUOTO);
		
	}
	
	public void stampaNumPrestitiTotali(int valore){
		System.out.println("IL NUMERO DI PRESTITI NELL'ANNO SOLARE E': " + valore); //STAMPA DA METTERE A PARTE
	
}

public void stampaNumProrogheTotali(int valore){
	System.out.println("IL NUMERO DI PROROGHE NELL'ANNO SOLARE E': " + valore);
}

	
	
}
