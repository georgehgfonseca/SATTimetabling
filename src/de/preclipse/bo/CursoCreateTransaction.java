package de.preclipse.bo;

import java.util.Date;
import org.prevayler.TransactionWithQuery;

import de.preclipse.bo.PrevalentSystem;

public class CursoCreateTransaction implements TransactionWithQuery {

	private static final long serialVersionUID = 1L;
	private int codigo;
	private int turno;
	private String nome;

	public CursoCreateTransaction(int codigo, String nome, int turno) {
		this.codigo = codigo;
		this.turno = turno;
		this.nome = nome;
	}

	public CursoCreateTransaction() {
		//default constructor needed for xml serialisation
	}

	public Object executeAndQuery(Object prevalentSystem, Date executionTime)
	throws Exception {
		return ((PrevalentSystem)prevalentSystem).addCurso(codigo, turno, nome);
	}

}