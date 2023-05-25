package de.preclipse.bo;

import java.util.Date;
import org.prevayler.TransactionWithQuery;

public class SalaRemoveTransaction implements TransactionWithQuery {

	private static final long serialVersionUID = 1L;
	private int codigo;

    public SalaRemoveTransaction(int codigo) {
	this.codigo = codigo;
    }

    public SalaRemoveTransaction() {
	//default constructor needed for xml serialisation
    }

    public Object executeAndQuery(Object prevalentSystem, Date executionTime)
	    throws Exception {
    	((PrevalentSystem)prevalentSystem).removeSala(codigo);
		return null;
    }

}