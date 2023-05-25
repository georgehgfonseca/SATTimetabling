package de.preclipse.bo;

import java.util.Date;
import org.prevayler.TransactionWithQuery;

public class SalaConsultaPorCodigoTransaction implements TransactionWithQuery {

	private static final long serialVersionUID = 1L;
	private int codigo;

	public SalaConsultaPorCodigoTransaction(int codigo) {
		this.codigo = codigo;
	}

	public SalaConsultaPorCodigoTransaction() {
		//default constructor needed for xml serialisation
	}

	public Object executeAndQuery(Object prevalentSystem, Date executionTime)
	throws Exception {
		return ((PrevalentSystem)prevalentSystem).getSala(codigo);
	}

}