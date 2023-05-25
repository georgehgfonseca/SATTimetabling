package processamento;

public class Violacao {
	
	private int tipo;
	private String descricao;
	
	public Violacao(int tipo, String descricao) {
		setTipo(tipo);
		setDescricao(descricao);
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return String.format("(%d) %30s", tipo, descricao);
	}
	
}
