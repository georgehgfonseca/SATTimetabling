package de.preclipse.bo;

import java.util.Date;
import org.prevayler.TransactionWithQuery;

import de.preclipse.bo.PrevalentSystem;

public class CursoRemoveTransaction implements TransactionWithQuery {
	
	private static final long serialVersionUID = 1L;
	private int codigo;
	
    public CursoRemoveTransaction(int codigo) {
    	this.codigo = codigo;
    }

    public Object executeAndQuery(Object prevalentSystem, Date executionTime)
	    throws Exception {
    	((PrevalentSystem)prevalentSystem).removeCurso(codigo);
		return null;
    }

}