package de.preclipse.bo;

import java.io.Serializable;
import java.util.ArrayList;

public class Classe implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
    private int codigo;
    private String nome;
    private int curso;
    private int cargaHoraria;
    private int minAulasGeminadas;
    private int limiteDiarioAulas;
    private int numAlunos;
    private String siapeDocente;
    private ArrayList<Restricao> restricoes;

    public Classe() {
    	
    }
    
	public Classe(long id) {
		this.id = id;
	}
    
    public Classe(long id, int codigo, String nome, int curso, 
    		int cargaHoraria, int minAulasGeminadas, int limiteDiarioAulas, 
    		int numAlunos, String siapeDocente, ArrayList<Restricao> restricoes) {
    	this.id = id;
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

    public long getID() {
    	return this.id;
    }

    public void setID(long id) {
    	this.id = id;
    }

    public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCurso() {
		return curso;
	}

	public void setCurso(int curso) {
		this.curso = curso;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public int getMinAulasGeminadas() {
		return minAulasGeminadas;
	}

	public void setMinAulasGeminadas(int minAulasGeminadas) {
		this.minAulasGeminadas = minAulasGeminadas;
	}

	public int getLimiteDiarioAulas() {
		return limiteDiarioAulas;
	}

	public void setLimiteDiarioAulas(int limiteDiarioAulas) {
		this.limiteDiarioAulas = limiteDiarioAulas;
	}

	public int getNumAlunos() {
		return numAlunos;
	}

	public void setNumAlunos(int numAlunos) {
		this.numAlunos = numAlunos;
	}

	public String getSiapeDocente() {
		return siapeDocente;
	}

	public void setSiapeDocente(String siapeDocente) {
		this.siapeDocente = siapeDocente;
	}

	public ArrayList<Restricao> getRestricoes() {
		return restricoes;
	}

	public void setRestricoes(ArrayList<Restricao> restricoes) {
		this.restricoes = restricoes;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Classe other = (Classe) obj;
		if (cargaHoraria != other.cargaHoraria)
			return false;
		if (codigo != other.codigo)
			return false;
		if (curso != other.curso)
			return false;
		if (id != other.id)
			return false;
		if (limiteDiarioAulas != other.limiteDiarioAulas)
			return false;
		if (minAulasGeminadas != other.minAulasGeminadas)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numAlunos != other.numAlunos)
			return false;
		if (restricoes == null) {
			if (other.restricoes != null)
				return false;
		} else if (!restricoes.equals(other.restricoes))
			return false;
		if (siapeDocente == null) {
			if (other.siapeDocente != null)
				return false;
		} else if (!siapeDocente.equals(other.siapeDocente))
			return false;
		return true;
	}

	public String toString() {
		String str = String.format("%-5s%-20s%-10d%-4d%-4d%-4d-8s\n", codigo, nome, curso, cargaHoraria, minAulasGeminadas, limiteDiarioAulas, siapeDocente, getRestricoes());
		return str;
	}
	
}