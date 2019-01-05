package controller;

import mylib.MyMenu;
//PATTERN TEMPLATE METHOD
public abstract class CreatoreMenu {
	//SEQUENZA DEI PASSI NON VARIA, MA VARIA IL CONTENUTO
	public final MyMenu crea() {
		this.titolo();
		this.opzioni();
		return new MyMenu(titolo(), opzioni());
	}


	protected abstract String titolo();

	protected abstract String[] opzioni();


}
