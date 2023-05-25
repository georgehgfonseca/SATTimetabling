package de.preclipse.bo;

import java.util.ArrayList;
import java.util.Date;
import org.prevayler.TransactionWithQuery;

public class ClasseChargeTransaction implements TransactionWithQuery {

	private static final long serialVersionUID = 1L;
	private int codigo;
    private String nome;
    private int curso;
    private int cargaHoraria;
    private int minAulasGeminadas;
    private int limiteDiarioAulas;
    private int numAlunos;
    private String siapeDocente;
    private ArrayList<Restricao> restricoes;

	public ClasseChargeTransaction(int codigo, String nome, int curso, 
			int cargaHoraria, int minAulasGeminadas, int limiteDiarioAulas, 
			int numAlunos, String siapeDocente, ArrayList<Restricao> restricoes) {
		this.codigo = codigo;
    	this.nome = nome;
    	this.curso = curso;
    	this.cargaHoraria = cargaHoraria;
    	this.minAulasGeminadas = minAulasGeminadas;
    	this.limiteDiarioAulas = limiteDiarioAulas;
    	this.numAlunos = numAlunos;
    	this.siapeDocente = siapeDocente;
    	this.restricoes = restricoes;
	}

	public ClasseChargeTransaction() {
		//default constructor needed for xml serialisation
	}

	public Object executeAndQuery(Object prevalentSystem, Date executionTime)
	throws Exception {
		PrevalentSystem prevayler = (PrevalentSystem)prevalentSystem;
    	Classe c = prevayler.getClasse(codigo);
    	c.setCodigo(codigo);
    	c.setNome(nome);
    	c.setCurso(curso);
    	c.setCargaHoraria(cargaHoraria);
    	c.setMinAulasGeminadas(minAulasGeminadas);
    	c.setLimiteDiarioAulas(limiteDiarioAulas);
    	c.setNumAlunos(numAlunos);
    	c.setSiapeDocente(siapeDocente);
    	c.setRestricoes(restricoes);
		return c;
	}

}