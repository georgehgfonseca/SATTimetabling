package processamento;

import java.util.*;


import org.prevayler.Prevayler;
import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;

import de.preclipse.bo.*;


public class CNFFormuleSala {

	private final Prevayler prevayler;

	private ArrayList<Curso> listCursos;
	private ArrayList<Sala> listSalas;
	private ArrayList<Classe> listClasses;
	private ArrayList<Integer> demanda;

	public CNFFormuleSala(Prevayler prevayler) {
		this.prevayler = prevayler;
		try {
			listCursos = ((ArrayList<Curso>)((PrevalentSystem)prevayler.prevalentSystem()).getCurso());
			listSalas = ((ArrayList<Sala>)((PrevalentSystem)prevayler.prevalentSystem()).getSala());
			listClasses = ((ArrayList<Classe>)((PrevalentSystem)prevayler.prevalentSystem()).getClasse());
			demanda = getDemanda();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Curso> getListCursos() {
		return listCursos;
	}

	public void setListCursos(ArrayList<Curso> listCursos) {
		this.listCursos = listCursos;
	}

	public ArrayList<Sala> getListSalas() {
		return listSalas;
	}

	public void setListSalas(ArrayList<Sala> listSalas) {
		this.listSalas = listSalas;
	}

	public void feedSolver(ISolver solver) throws NumberFormatException, Exception {
		clausulasCapacidadeSala(solver);
		clausulasPreenchimento(solver);
		clausulasUnicidade(solver);
	}

	private void clausulasCapacidadeSala(ISolver solver) throws Exception {
		for(int i = 0; i < listCursos.size(); ++i) {
			for(int j = 0; j < listSalas.size(); ++j) {
				if(demanda.get(i) > listSalas.get(j).getCapacidade()) {
					//not l(c, s)
					LiteralSala l = new LiteralSala(listCursos.get(i), listSalas.get(j));
					l.setId(l.getId() * -1);
					int [] clause = {l.getId()};
					solver.addClause(new VecInt(clause));
				}
			}
		}
	}

	private void clausulasPreenchimento(ISolver solver) throws ContradictionException {
		for(int i = 0; i < listCursos.size(); ++i) {
			int [] clause = new int [listSalas.size()];
			int pos = 0;
			for(int j = 0; j < listSalas.size(); ++j) {
				LiteralSala l = new LiteralSala(listCursos.get(i), listSalas.get(j));
				clause[pos] = l.getId();
				++pos;
			}
			solver.addClause(new VecInt(clause));
		}
	}

	private void clausulasUnicidade(ISolver solver) throws ContradictionException {
		for(int i = 0; i < listCursos.size(); ++i) { //Itera cada vértice
			for(int j = i + 1; j < listCursos.size(); ++j) {
				if(listCursos.get(i).getTurno() == listCursos.get(j).getTurno()) {
					for(int k = 0; k < listSalas.size(); ++k) {					
						LiteralSala l1 = new LiteralSala(listCursos.get(i), listSalas.get(k));
						LiteralSala l2 = new LiteralSala(listCursos.get(j), listSalas.get(k));
						l1.setId(l1.getId() * -1);
						l2.setId(l2.getId() * -1);
						int [] clause = {l1.getId(), l2.getId()};
						solver.addClause(new VecInt(clause));
					}
				}
			}
		}
	}

	private ArrayList<Integer> getDemanda() throws Exception {
		ArrayList<Integer> demanda = new ArrayList<Integer>();
		for(int i = 0; i < listCursos.size(); ++i) {
			demanda.add(0);
		}
		for(int i = 0; i < listClasses.size(); ++i) {
			Curso c = (Curso) prevayler.execute(new CursoConsultaPorCodigoTransaction(listClasses.get(i).getCurso()));
			if(listClasses.get(i).getNumAlunos() > demanda.get(listCursos.indexOf(c))) {
				demanda.set(listCursos.indexOf(c), listClasses.get(i).getNumAlunos());
			}
		}
		return demanda;
	}

	public ArrayList<Integer> selecionaMapeiaHorarios(int[] literals) {
		ArrayList<Integer> resultado = new ArrayList<Integer>();
		for(int i = 0; i < literals.length; ++i) {
			if(literals[i] > 0) {
				resultado.add(literals[i]);
			}
		}
		return resultado;
	}


}