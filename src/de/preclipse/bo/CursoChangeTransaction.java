package de.preclipse.bo;

import java.util.Date;
import org.prevayler.TransactionWithQuery;

import de.preclipse.bo.PrevalentSystem;

public class CursoChangeTransaction implements TransactionWithQuery {

	private static final long serialVersionUID = 1L;
	private int codigo;
	private int turno;
	private String nome;

	public CursoChangeTransaction(int codigo, int turno, String nome) {
		this.codigo = codigo;
		this.turno = turno;
		this.nome = nome;
	}

	public CursoChangeTransaction() {
		//default constructor needed for xml serialisation
	}

	public Object executeAndQuery(Object prevalentSystem, Date executionTime)
	throws Exception {
		PrevalentSystem prevayler = (PrevalentSystem)prevalentSystem;
    	Curso c = prevayler.getCurso(codigo);
    	c.setNome(nome);
    	c.setTurno(turno);
		return c;
	}

}