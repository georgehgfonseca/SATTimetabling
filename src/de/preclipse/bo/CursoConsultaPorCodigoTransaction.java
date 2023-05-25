package de.preclipse.bo;

import java.util.Date;
import org.prevayler.TransactionWithQuery;

import de.preclipse.bo.PrevalentSystem;

public class CursoConsultaPorCodigoTransaction implements TransactionWithQuery {

	private static final long serialVersionUID = 1L;
	private int codigo;

	public CursoConsultaPorCodigoTransaction(int codigo) {
		this.codigo = codigo;
	}

	public CursoConsultaPorCodigoTransaction() {
		//default constructor needed for xml serialisation
	}

	public Object executeAndQuery(Object prevalentSystem, Date executionTime)
	throws Exception {
		return ((PrevalentSystem)prevalentSystem).getCurso(codigo);
	}

}