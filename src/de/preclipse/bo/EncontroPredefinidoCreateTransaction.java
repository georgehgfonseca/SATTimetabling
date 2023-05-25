package de.preclipse.bo;

import java.util.Date;


import org.prevayler.TransactionWithQuery;

import de.preclipse.bo.Restricao;


public class EncontroPredefinidoCreateTransaction implements
	TransactionWithQuery {

	private static final long serialVersionUID = 1L;
	private Restricao restricao;
    private Docente docente;
    private Classe classe;

    public EncontroPredefinidoCreateTransaction(Restricao restricao, Docente docente, Classe classe) {
	    this.restricao = restricao;
	    this.docente = docente;
	    this.classe = classe;
    }

    public EncontroPredefinidoCreateTransaction() {
	//default constructor needed for xml serialisation
    }

    public Object executeAndQuery(Object prevalentSystem, Date executionTime)
	    throws Exception {
    	return ((PrevalentSystem)prevalentSystem).addEncontroPredefinido(restricao, docente, classe);
    }

}