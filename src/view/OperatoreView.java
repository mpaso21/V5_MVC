package view;

import mylib.Constants;
import mylib.InputDati;

public class OperatoreView {

	public String inserisciIdInput(){
		String id = mylib.InputDati.leggiStringaNonVuota(Constants.RICHIESTA_ID);
		return id;
	}
	
	public void stampaNoOperatore(){
		System.out.println(Constants.SOLO_OPERATORE);
	}

}
