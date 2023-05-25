package de.preclipse.bo;

import java.util.Date;
import org.prevayler.TransactionWithQuery;

public class ClasseRemoveTransaction implements TransactionWithQuery {

	private static final long serialVersionUID = 1L;
	private int codigo;

	public ClasseRemoveTransaction(int codigo) {
		this.codigo = codigo;
	}

	public ClasseRemoveTransaction() {
		//default constructor needed for xml serialisation
	}

	public Object executeAndQuery(Object prevalentSystem, Date executionTime)
	throws Exception {
		((PrevalentSystem)prevalentSystem).removeClasse(codigo);
		return null;
	}

}