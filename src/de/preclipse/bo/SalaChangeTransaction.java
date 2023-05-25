package de.preclipse.bo;

import java.util.Date;
import org.prevayler.TransactionWithQuery;

public class SalaChangeTransaction implements TransactionWithQuery {

	private static final long serialVersionUID = 1L;
	private int codigo;
    private String nome;
    private int capacidade;

    public SalaChangeTransaction(int codigo,
	    String nome, int capacidade) {
	this.codigo = codigo;
	this.nome = nome;
	this.capacidade = capacidade;
    }

    public SalaChangeTransaction() {
	//default constructor needed for xml serialisation
    }

    public Object executeAndQuery(Object prevalentSystem, Date executionTime)
	    throws Exception {
    	PrevalentSystem prevayler = (PrevalentSystem)prevalentSystem;
    	Sala s = prevayler.getSala(codigo);
    	s.setNome(nome);
    	s.setCapacidade(capacidade);
		return s;
    }

}