package view.cittadino;

import mylib.Constants;
import mylib.ConstantsCittadino;

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

}
