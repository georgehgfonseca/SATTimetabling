package processamento;

import org.prevayler.Prevayler;

import de.preclipse.bo.Classe;
import de.preclipse.bo.ClasseConsultaPorCodigoTransaction;
import de.preclipse.bo.Docente;
import de.preclipse.bo.DocenteConsultaPorSiapeTransaction;

public class Literal {
	
	private int id;
	private Classe classe;
	private Docente docente;
	private int encontro;
	private int horario;
	private int dia;
	private static final int PESODIA = 100000000;
	private static final int PESOHORARIO = 10000000;
	private static final int PESOENCONTRO = 1000000;
	private static final int PESODOCENTE = 10000;
	private static final int PESOCLASSE = 1;
	private Prevayler prevayler;
	
	public Literal() {
		
	}
	
	public Literal(int dia, int horario, int encontro, Docente docente, Classe classe) {
		id = calculaId(dia, horario, encontro, docente, classe);
		this.classe = classe;
		this.docente = docente;
		this.encontro = encontro;
		this.horario = horario;
		this.dia = dia;
	}
	
	public Literal(Prevayler prevayler) {
		this.prevayler = prevayler;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getEncontro() {
		return encontro;
	}

	public void setEncontro(int encontro) {
		this.encontro = encontro;
	}

	public int getHorario() {
		return horario;
	}

	public void setHorario(int horario) {
		this.horario = horario;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	/*  O id é calculado de modo que um intero represente um único literal:
	 *  id = dheddcccc,
	 *  onde: d = dia
	 *        h = horário
	 *        e = encontro
	 *        d = docente
	 *        c = classe
	 */
	public int calculaId(int dia, int horario, int encontro, Docente docente, Classe classe) {
		int id;
		id = dia * PESODIA;
		id += horario * PESOHORARIO;
		id += encontro * PESOENCONTRO;
		id += docente.getSiape() * PESODOCENTE;
		id += classe.getCodigo() * PESOCLASSE;
		return id;
	}
	
	public Literal getLiteral(int horario) {
		try {
			return new Literal(horario / PESODIA, 
					(horario % PESODIA) / PESOHORARIO, 
					(horario % PESOHORARIO) / PESOENCONTRO, 
					(Docente) prevayler.execute(new DocenteConsultaPorSiapeTransaction((horario % PESOENCONTRO) / PESODOCENTE)),
					(Classe) prevayler.execute(new ClasseConsultaPorCodigoTransaction((horario % PESODOCENTE) / PESOCLASSE)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Literal other = (Literal) obj;
		if (classe == null) {
			if (other.classe != null)
				return false;
		} else if (!classe.equals(other.classe))
			return false;
		if (dia != other.dia)
			return false;
		if (docente == null) {
			if (other.docente != null)
				return false;
		} else if (!docente.equals(other.docente))
			return false;
		if (encontro != other.encontro)
			return false;
		if (horario != other.horario)
			return false;
		if (id != other.id)
			return false;
		if (prevayler == null) {
			if (other.prevayler != null)
				return false;
		} else if (!prevayler.equals(other.prevayler))
			return false;
		return true;
	}

	public String toString() {
		return String.format("%d", id);
	}

}
