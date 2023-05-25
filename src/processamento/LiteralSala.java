package processamento;

import org.prevayler.Prevayler;

import de.preclipse.bo.*;

public class LiteralSala {
	
	private int id;
	private Curso curso;
	private Sala sala;
	
	private Prevayler prevayler;
	public static final int PESOCURSO = 100;
	
	public LiteralSala(Curso curso, Sala sala) {
		this.id = calculaId(curso, sala);
		this.curso = curso;
		this.sala = sala;
	}
	
	public LiteralSala(Prevayler prevayler) {
		this.prevayler = prevayler;
	}
	
	public Curso getCurso() {
		return curso;
	}
	
	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*  O id é calculado de modo que um intero represente um único literal:
	 *  id = ccss,
	 *  onde: c = curso
	 *        s = sala
	 */
	public int calculaId(Curso curso, Sala sala) {
		int id;
		id = curso.getCodigo() * PESOCURSO;
		id += sala.getCodigo();
		return id;
	}
	
	public LiteralSala getLiteral(int locacao) {
		try {
			return new LiteralSala((Curso) prevayler.execute(new CursoConsultaPorCodigoTransaction(locacao / PESOCURSO)),
					(Sala) prevayler.execute(new SalaConsultaPorCodigoTransaction(locacao % PESOCURSO)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	

}
