package de.preclipse.bo;

import java.util.Date;
import org.prevayler.TransactionWithQuery;

public class ClasseConsultaPorCodigoTransaction implements TransactionWithQuery {

	private static final long serialVersionUID = 1L;
	private int codigo;

    public ClasseConsultaPorCodigoTransaction(int codigo) {
    	this.codigo = codigo;
    }

    public ClasseConsultaPorCodigoTransaction() {
	//default constructor needed for xml serialisation
    }

    public Object executeAndQuery(Object prevalentSystem, Date executionTime)
	    throws Exception {
		return ((PrevalentSystem)prevalentSystem).getClasse(codigo);
    }

}