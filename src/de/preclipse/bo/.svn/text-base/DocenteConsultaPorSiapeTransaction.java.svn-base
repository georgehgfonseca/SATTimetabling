package de.preclipse.bo;

import java.util.Date;
import org.prevayler.TransactionWithQuery;

public class DocenteConsultaPorSiapeTransaction implements TransactionWithQuery {

	private static final long serialVersionUID = 1L;
	private int siape;

	public DocenteConsultaPorSiapeTransaction(int siape) {
		this.siape = siape;
	}

	public DocenteConsultaPorSiapeTransaction() {
		//default constructor needed for xml serialisation
	}

	public Object executeAndQuery(Object prevalentSystem, Date executionTime)
	throws Exception {
		return ((PrevalentSystem)prevalentSystem).getDocente(siape);
	}

}