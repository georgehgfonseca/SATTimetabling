package de.preclipse.bo;

import java.io.Serializable;

public class EncontroPredefinido implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private Restricao restricao;
	private Classe classe;
	private Docente docente;
	
	public EncontroPredefinido(long id) {
		this.id = id;
	}
	
	public EncontroPredefinido(long id, Restricao restricao, Docente docente, Classe classe) {
		this.id = id;
		this.restricao = restricao;
		this.docente = docente;
		this.classe = classe;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Restricao getRestricao() {
		return restricao;
	}

	public void setRestricao(Restricao restricao) {
		this.restricao = restricao;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}
	
}
