package de.preclipse.bo;

import java.util.Date;
import org.prevayler.TransactionWithQuery;

public class DocenteRemoveTransaction implements TransactionWithQuery {

	private static final long serialVersionUID = 1L;
	private int siape;

	public DocenteRemoveTransaction(int siape) {
		this.siape = siape;
	}

	public DocenteRemoveTransaction() {
		//default constructor needed for xml serialisation
	}

	public Object executeAndQuery(Object prevalentSystem, Date executionTime)
	throws Exception {
		((PrevalentSystem)prevalentSystem).removeDocente(siape);
		return null;
	}

}