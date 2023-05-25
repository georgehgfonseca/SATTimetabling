package de.preclipse.bo;

import java.io.Serializable;

public class Sala implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long id;
	private int codigo;
	private String nome;
	private int capacidade;
	
	public Sala() {
		//just needed for xml serialisation.
	}

	public Sala(long id) {
		this.id = id;
	}

	public Sala(long id, int codigo, String nome, int capacidade) {
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.capacidade = capacidade;
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

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

}
