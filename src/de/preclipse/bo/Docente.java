package de.preclipse.bo;

import java.io.Serializable;
import java.util.ArrayList;

public class Docente implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private int siape;
	private String nome;
	private ArrayList<Restricao> restricoes;

	public Docente() {
		//just needed for xml serialisation.
	}

	public Docente(long id) {
		this.id = id;
	}

	public Docente(long id, int siape, String nome, ArrayList<Restricao> restricoes) {
		this.id = id;
		this.siape = siape;
		this.nome = nome;
		this.restricoes = restricoes;
	}

	public long getID() {
		return this.id;
	}

	public void setID(long id) {
		this.id = id;
	}

	public int getSiape() {
		return siape;
	}

	public void setSiape(int siape) {
		this.siape = siape;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		Docente other = (Docente) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (restricoes == null) {
			if (other.restricoes != null)
				return false;
		} else if (!restricoes.equals(other.restricoes))
			return false;
		if (siape != other.siape)
			return false;
		return true;
	}

	public String toString() {
		String str = "";
		str = String.format("%-4d%-30s\n", siape, nome);
		return str;
	}

}