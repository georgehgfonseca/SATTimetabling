package de.preclipse.bo;

import java.io.Serializable;

public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private int codigo;
	private int turno;
	private String nome;

	public Curso() {

	}

	public Curso(int codigo) {
		this.codigo = codigo;
	}

	public Curso(long id, int codigo, int turno, String nome) {
		this.id = id;
		this.codigo = codigo;
		this.turno = turno;
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (codigo != other.codigo)
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (turno != other.turno)
			return false;
		return true;
	}

	public String toString() {
		return String.format("%d %s %d", codigo, nome, turno);
	}

}
