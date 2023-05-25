package de.preclipse.bo;

import java.util.ArrayList;
import java.util.Date;
import org.prevayler.TransactionWithQuery;

public class DocenteChargeTransaction implements TransactionWithQuery {

	private static final long serialVersionUID = 1L;
	private int siape;
	private String nome;
	private ArrayList<Restricao> restricoes;

	public DocenteChargeTransaction(int siape, String nome, ArrayList<Restricao> restricoes) {
		this.siape = siape;
		this.nome = nome;
		this.restricoes = restricoes;
	}	
	
	public DocenteChargeTransaction() {
		//default constructor needed for xml serialisation
	}

	public Object executeAndQuery(Object prevalentSystem, Date executionTime) throws Exception {
		PrevalentSystem prevayler = (PrevalentSystem)prevalentSystem;
    	Docente d = prevayler.getDocente(siape);
		d.setSiape(siape);
		d.setNome(nome);
		d.setRestricoes(restricoes);
		return d;
	}

}