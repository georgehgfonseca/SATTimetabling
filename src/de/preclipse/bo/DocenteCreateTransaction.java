package de.preclipse.bo;

import java.util.ArrayList;
import java.util.Date;
import org.prevayler.TransactionWithQuery;

public class DocenteCreateTransaction implements TransactionWithQuery {

	private static final long serialVersionUID = 1L;
	private int siape;
	private String nome;
	private ArrayList<Restricao> restricoes;

	public DocenteCreateTransaction(int siape, String nome, ArrayList<Restricao> restricoes) {
		this.siape = siape;
		this.nome = nome;
		this.restricoes = restricoes;
	}	
	
	public DocenteCreateTransaction() {
	//default constructor needed for xml serialisation
	}

	public Object executeAndQuery(Object prevalentSystem, Date executionTime) throws Exception {
		return ((PrevalentSystem)prevalentSystem).addDocente(siape, nome, restricoes);
	}

}