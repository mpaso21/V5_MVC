package action;

import java.util.HashMap;
import java.util.Map;

import controller.LoginController;
import controller.entitycontroller.PrestitoEntityController;
import controller.entitycontroller.RisorsaEntityController;
//REFACTOR INTRODUCE PARAMETER OBJECT
import mylib.ConstantsRisorsa;

public class TableInfoPerPrestito {

	private LoginController mainManager;

	
	private Map<String, RisorsaEntityController> risorseEntityControllerMap;
	private Map<String, PrestitoEntityController> prestitiEntityControllerMap;

	
	public TableInfoPerPrestito(LoginController main) {
		this.mainManager = main;
		
		this.risorseEntityControllerMap =  main.getRisorseEntityControllerMap();
		this.prestitiEntityControllerMap = main.getPrestitiEntityControllerMap();
	}

	

	public Map<String, RisorsaEntityController> getRisorseEntityControllerMap() {
		return risorseEntityControllerMap;
	}



	public Map<String, PrestitoEntityController> getPrestitiEntityControllerMap() {
		return prestitiEntityControllerMap;
	}


	public RisorsaEntityController getRisorsaControllerByKey(String k){
		return this.risorseEntityControllerMap.get(k);
	}
	

	public PrestitoEntityController getPrestitoControllerByKey(String k){
		return this.prestitiEntityControllerMap.get(k);
	}
	
	
}
