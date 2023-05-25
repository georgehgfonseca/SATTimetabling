package de.preclipse.bo;

import java.util.Date;
import org.prevayler.TransactionWithQuery;

public class SalaCreateTransaction implements TransactionWithQuery {

	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nome;
	private int capacidade;

	public SalaCreateTransaction(int siape, String nome, int capacidade) {
		this.codigo = siape;
		this.nome = nome;
		this.capacidade = capacidade;
	}	
	
	public SalaCreateTransaction() {
	//default constructor needed for xml serialisation
	}

	public Object executeAndQuery(Object prevalentSystem, Date executionTime) throws Exception {
		return ((PrevalentSystem)prevalentSystem).addSala(codigo, nome, capacidade);
	}

}