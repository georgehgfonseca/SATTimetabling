package de.preclipse.bo;

import java.io.Serializable;

public class Restricao implements Serializable {

	private static final long serialVersionUID = 1L;
	private int diaSemana;
	private int tipo;
	
	public Restricao (int diaSemana, int tipo) {
		setDiaSemana(diaSemana);
		setTipo(tipo);
	}

	public int getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restricao other = (Restricao) obj;
		if (diaSemana != other.diaSemana)
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

}