package processamento;

import gui.PainelGerarQuadroHorarios;
import gui.PainelInserirClasse;
import gui.PainelInserirDocente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import org.prevayler.Prevayler;
import org.sat4j.core.VecInt;
import org.sat4j.maxsat.SolverFactory;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;
import org.sat4j.specs.TimeoutException;

import de.preclipse.bo.Classe;
import de.preclipse.bo.Docente;
import de.preclipse.bo.EncontroPredefinido;
import de.preclipse.bo.PrevalentSystem;
import de.preclipse.bo.Restricao;

public class Solver {

	private ArrayList<Integer> horarios;
	private ArrayList<Integer> locacoes;
	private ArrayList<Classe> classes;
	private ArrayList<Docente> docentes;
	private ArrayList<EncontroPredefinido> listPredefinidos;
	private Prevayler prevayler;
	public static int changes = 1;
	public static ArrayList<ArrayList<Literal>> listaTabu = new ArrayList<ArrayList<Literal>>();
	public static ArrayList<ArrayList<Literal>> listaTabuGeminada = new ArrayList<ArrayList<Literal>>();

	public Solver() {

	}

	public ArrayList<Integer> solveHorarios(Prevayler prevayler) throws TimeoutException, Exception {		
		this.prevayler = prevayler;
		CNFFormule f = new CNFFormule(prevayler);
		try {
			classes = ((ArrayList<Classe>)((PrevalentSystem)prevayler.prevalentSystem()).getClasse());
			docentes = ((ArrayList<Docente>)((PrevalentSystem)prevayler.prevalentSystem()).getDocente());
			listPredefinidos = ((ArrayList<EncontroPredefinido>)((PrevalentSystem)prevayler.prevalentSystem()).getEncontroPredefinido());
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		// CNF filename is given on the command line 
		ISolver solver = SolverFactory.newDefault();
		System.out.println("Etapa 1 de 4: Carregamento da fórmula...");
		solver.newVar(f.getListLiterais().size() * 25);
		f.feedSolver(solver);
		solver.setTimeout((classes.size() * 5) + 10);
		System.out.println("Etapa 2 de 4: Geração da solução inicial no resolvedor SAT...");
		long t1 = System.currentTimeMillis();
		IProblem problem = solver;
		if (problem.isSatisfiable()) {
			long t2 = System.currentTimeMillis();
			System.out.println(t2 - t1);
			System.out.println("Saída do resolvedor");
			System.out.println("Satisfiable !");
			horarios = f.selecionaMapeiaHorarios(problem.model());
			ArrayList<Literal> litHorarios = getLiterals(horarios);
			
			int consecutivoSemChange = 0;
			long t3 = System.currentTimeMillis();
			System.out.println("Etapa 3 de 4: Processamento da Busca Tabu...");
			while(consecutivoSemChange < 10) {
				if(changes == 0) {
					++consecutivoSemChange;
				}
				else {
					consecutivoSemChange = 0;
				}
				changes = 0;
				litHorarios = procedimentoSwap(litHorarios);
				System.out.println(changes);
			}
			long t4 = System.currentTimeMillis();
			System.out.println(t4 - t3);
			System.out.println("Fim da Busca Tabu");
			horarios = getIds(litHorarios);
		} else {
			System.out.println("Unsatisfiable !");
		}
		return horarios;
	}
	
	public ArrayList<LiteralSala> solveSalas(Prevayler prevayler) throws NumberFormatException, TimeoutException, Exception {
		this.prevayler = prevayler;
		CNFFormuleSala fSala = new CNFFormuleSala(prevayler);
		classes = ((ArrayList<Classe>)((PrevalentSystem)prevayler.prevalentSystem()).getClasse());
		docentes = ((ArrayList<Docente>)((PrevalentSystem)prevayler.prevalentSystem()).getDocente());
		listPredefinidos = ((ArrayList<EncontroPredefinido>)((PrevalentSystem)prevayler.prevalentSystem()).getEncontroPredefinido());

		ISolver solver = SolverFactory.newDefault();
		solver.newVar(9999);
		fSala.feedSolver(solver);
		solver.setTimeout(classes.size() * 3);
		IProblem problem = solver;
		if (problem.isSatisfiable()) {
			System.out.println("Satisfiable !");
			locacoes = fSala.selecionaMapeiaHorarios(problem.model());
		} else {
			System.out.println("Unsatisfiable !");
		}
		return getLiteralsLocacoes(locacoes);		
	}

	public ArrayList<Integer> getIds(ArrayList<Literal> horarios) {
		ArrayList<Integer> idHorarios = new ArrayList<Integer>();
		for(int i = 0; i < horarios.size(); ++i) {
			idHorarios.add(horarios.get(i).getId());
		}
		return idHorarios;
	}

	public ArrayList<Literal> getLiterals(ArrayList<Integer> horarios) {
		ArrayList<Literal> literals = new ArrayList<Literal>();
		for(int i = 0; i < horarios.size(); ++i) {
			if(horarios.get(i) != null) {
				Literal l = new Literal(prevayler);
				literals.add(l.getLiteral(Math.abs(horarios.get(i))));
			}
		}
		return literals;
	}

	public ArrayList<Integer> getIdsLocacoes(ArrayList<LiteralSala> locacoes) {
		ArrayList<Integer> idHorarios = new ArrayList<Integer>();
		for(int i = 0; i < locacoes.size(); ++i) {
			idHorarios.add(locacoes.get(i).getId());
		}
		return idHorarios;
	}

	public ArrayList<LiteralSala> getLiteralsLocacoes(ArrayList<Integer> locacoes) {
		ArrayList<LiteralSala> literals = new ArrayList<LiteralSala>();
		for(int i = 0; i < locacoes.size(); ++i) {
			if(locacoes.get(i) != null) {
				LiteralSala l = new LiteralSala(prevayler);
				literals.add(l.getLiteral(Math.abs(locacoes.get(i))));
			}
		}
		return literals;
	}

	public boolean swapHorario(Literal a1, int indiceA1, Literal a2, int indiceA2, ArrayList<Literal> horarios) {
		int diasP1 = getDiasProfessor(a1.getDocente(), horarios);
		int diasP2 = getDiasProfessor(a2.getDocente(), horarios);
		//System.out.printf("  %s: %d dias\n", a1, diasP1);
		//System.out.printf("  %s: %d dias\n", a2, diasP2);
		Literal newA1 = new Literal(a2.getDia(), a2.getHorario(), a1.getEncontro(), a1.getDocente(), a1.getClasse());
		horarios.set(indiceA1, newA1);
		Literal newA2 = new Literal(a1.getDia(), a1.getHorario(), a2.getEncontro(), a2.getDocente(), a2.getClasse());
		horarios.set(indiceA2, newA2);
		ArrayList<Literal> movimento = new ArrayList<Literal>();
		movimento.add(a1);
		movimento.add(a2);
		int custoReduzido = reduzCusto(horarios, a1, diasP1, a2, diasP2);
		if(! infringeRestricaoHorario(horarios, a1, a2) &&
				! infringeLimiteDiario(horarios, a1, a2) && 
				! infringeUnicidadeDocente(horarios, a1, a2) && 
				! infringePredefinido(horarios, a1, a2) &&
				custoReduzido >= 0 &&
				(! movimentoTabu(movimento) || custoReduzido  > 0)) {
			listaTabu.add(movimento);
			changes += custoReduzido;
			Literal temp = horarios.get(indiceA1);
			horarios.set(indiceA1, newA2);
			horarios.set(indiceA2, temp);
			/*if(reduzCusto(horarios, a1, diasP1, a2, diasP2) > 0)
				System.out.printf("***Swapped: %s e %s\n", a1, a2);
			else
				System.out.printf("   Swapped: %s e %s\n", a1, a2);*/
			return true;
		}
		else {
			horarios.set(indiceA2, a2);
			horarios.set(indiceA1, a1);
			return false;
		}
	}

	public void swapComum2(Literal a1, ArrayList<Literal> horarios, int i, ArrayList<ArrayList<Literal>> listaTabu) {
		for(int j = i + 1; j < horarios.size(); ++j) {
			if(a1.getClasse().getCurso() == horarios.get(j).getClasse().getCurso() && j + 1 < horarios.size() &&
					! a1.getDocente().equals(horarios.get(j).getDocente())) {
				if(! horarios.get(j).getClasse().equals(horarios.get(j + 1).getClasse())) {
					//System.out.printf("Swap comum: %s %s\n", horarios.get(i), horarios.get(j));
					ArrayList<Literal> movimento = new ArrayList<Literal>();
					movimento.add(a1);
					movimento.add(horarios.get(j));
					if(! listaTabu.contains(movimento) && swapHorario(a1, i, horarios.get(j), j, horarios)) {
						listaTabu.add(movimento);
						return;
					}
				}
				else {
					++j;
				}
			}
		}
	}

	public ArrayList<Literal> procedimentoSwap(ArrayList<Literal> horarios) {
		Collections.sort(horarios, new Comparator<Object>(){
			public int compare(final Object m1, final Object m2){
				final Literal aux1 = (Literal) m1;
				final Literal aux2 = (Literal) m2;
				if(aux1.getClasse().getCurso() > aux2.getClasse().getCurso())
					return 1;
				else if(aux1.getClasse().getCurso() < aux2.getClasse().getCurso())
					return -1;
				else {
					if(aux1.getDia() > aux2.getDia())
						return 1;
					else if(aux1.getDia() < aux2.getDia())
						return -1;
					else {
						if(aux1.getHorario() > aux2.getHorario())
							return 1;
						else if(aux1.getHorario() < aux2.getHorario())
							return -1;
						else
							return 0;
					}
				}
			}
		});
		for(int i = 0; i < horarios.size() - 1; ++i) {
			if(horarios.get(i).getClasse().equals(horarios.get(i + 1).getClasse()) &&
					horarios.get(i).getDia() == horarios.get(i + 1).getDia()) { //Tentar troca de aula geminada
				swapGeminada(horarios.get(i), horarios.get(i + 1), horarios, i);
				++i;
			}
			else {
				swapComum(horarios.get(i), horarios, i);
			}
		}
		return horarios;
	}

	public void swapComum(Literal a1, ArrayList<Literal> horarios, int i) {
		for(int j = i + 1; j < horarios.size(); ++j) {
			if(a1.getClasse().getCurso() == horarios.get(j).getClasse().getCurso() &&
					! a1.getDocente().equals(horarios.get(j).getDocente())) {
				if(j + 1 < horarios.size()) {
					if(! horarios.get(j).getClasse().equals(horarios.get(j + 1).getClasse())) {
						//System.out.printf("Swap comum: %s %s\n", horarios.get(i), horarios.get(j));
						if(swapHorario(a1, i, horarios.get(j), j, horarios)) {
							return;
						}
					}
					else {
						++j;
					}
				}
				else {
					if(swapHorario(a1, i, horarios.get(j), j, horarios))
						return;
				}
			}
		}
	}

	public int getDiasProfessor(Docente d, ArrayList<Literal> horarios) {
		ArrayList<Integer> dias = new ArrayList<Integer>();
		for(int i = 0; i < horarios.size(); ++i) {
			if(horarios.get(i).getDocente().equals(d) && ! dias.contains(horarios.get(i).getDia())) {
				dias.add(horarios.get(i).getDia());
			}
		}
		return dias.size();
	}

	public int reduzCusto(ArrayList<Literal> horarios, Literal a1, int diasP1, Literal a2, int diasP2) {
		int novoDiasP1 = getDiasProfessor(a1.getDocente(), horarios);
		int novoDiasP2 = getDiasProfessor(a2.getDocente(), horarios);
		//System.out.printf("  %s: %d dias\n", a1, novoDiasP1);
		//System.out.printf("  %s: %d dias\n", a2, novoDiasP2);
		return (diasP1 + diasP2 - novoDiasP1 - novoDiasP2);
	}

	public boolean infringeRestricaoHorario(ArrayList<Literal> horarios, Literal a1, Literal a2) {
		for(int i = 0; i < a1.getDocente().getRestricoes().size(); ++i) {
			if(a1.getDocente().getRestricoes().get(i).getDiaSemana() == a2.getDia() &&
					a1.getDocente().getRestricoes().get(i).getTipo() == a2.getHorario()) {
				//System.out.println("+++" + a1.getDocente().getSiape() + " contem restricao " + r1.getDiaSemana() + r1.getTipo());
				return true;
			}
		}
		for(int i = 0; i < a2.getDocente().getRestricoes().size(); ++i) {
			if(a2.getDocente().getRestricoes().get(i).getDiaSemana() == a1.getDia() &&
					a2.getDocente().getRestricoes().get(i).getTipo() == a1.getHorario()) {
				//System.out.println("+++" + a2.getDocente().getSiape() + " contem restricao " + r2.getDiaSemana() + r2.getTipo());
				return true;
			}
		}
		//System.out.println("   " + a1.getDocente().getSiape() + " nao contem restricao " + r1.getDiaSemana() + r1.getTipo());
		//System.out.println("   " + a2.getDocente().getSiape() + " nao contem restricao " + r2.getDiaSemana() + r2.getTipo());
		return false;
	}

	public boolean infringeLimiteDiario(ArrayList<Literal> horarios, Literal a1, Literal a2) {
		ArrayList<Literal> temp = new ArrayList<Literal>();
		for(int i = 0; i < horarios.size(); ++i) {
			if(horarios.get(i).getClasse().equals(a1.getClasse()) || horarios.get(i).getClasse().equals(a2.getClasse())) {
				temp.add(horarios.get(i));
			}
		}
		for(int i = 0; i < temp.size(); ++i) {
			int count = 1;
			for(int j = i + 1; j < temp.size(); ++j) {
				if(temp.get(i).getClasse().equals(temp.get(j).getClasse()) && temp.get(i).getDia() == temp.get(j).getDia()) {
					++count;
					if(count > temp.get(i).getClasse().getLimiteDiarioAulas()) {
						return true;
					}
					temp.remove(j);
					--j;
				}
			}
		}
		return false;
	}

	public boolean infringeUnicidadeDocente(ArrayList<Literal> horarios, Literal a1, Literal a2) {
		for(int i = 0; i < horarios.size(); ++i) {
			for(int j = i + 1; j < horarios.size(); ++j) {
				if(horarios.get(i).getHorario() == horarios.get(j).getHorario() &&
						horarios.get(i).getDia() == horarios.get(j).getDia() &&
						horarios.get(i).getDocente().equals(horarios.get(j).getDocente()) &&
						(horarios.get(i).getDocente().equals(a1.getDocente()) || horarios.get(i).getDocente().equals(a2.getDocente()))) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean infringePredefinido(ArrayList<Literal> horarios, Literal a1, Literal a2) {
		for(int i = 0; i < listPredefinidos.size(); ++i) {
			if(listPredefinidos.get(i).getClasse().equals(a1.getClasse()) && (listPredefinidos.get(i).getRestricao().getDiaSemana() != a1.getDia() || listPredefinidos.get(i).getRestricao().getTipo() != a1.getHorario()) ||
					listPredefinidos.get(i).getClasse().equals(a2.getClasse()) && (listPredefinidos.get(i).getRestricao().getDiaSemana() != a2.getDia() || listPredefinidos.get(i).getRestricao().getTipo() != a2.getHorario())) {
				return true;
			}
		}
		return false;
	}


	public void swapGeminada(Literal a1, Literal a2, ArrayList<Literal> horarios, int i) {

		for(int j = i + 2; j < horarios.size(); ++j) {
			if(j + 1 < horarios.size()) {
				if(horarios.get(j).getClasse().getCurso() == a1.getClasse().getCurso() &&
						horarios.get(j + 1).getClasse().getCurso() == a1.getClasse().getCurso() &&
						horarios.get(j).getDia() == horarios.get(j + 1).getDia() &&
						horarios.get(j).getHorario() + 1 == horarios.get(j + 1).getHorario()) {
					if(j + 2 < horarios.size()) {
						if(! horarios.get(j).getClasse().equals(horarios.get(j - 1).getClasse()) &&
								! horarios.get(j + 1).getClasse().equals(horarios.get(j + 2).getClasse())) {
							//System.out.printf("To test: %s %s e %s %s\n", a1, a2, horarios.get(j), horarios.get(j + 1));
							if(swapParHorario(a1, i, a2, i + 1, horarios.get(j), j, horarios.get(j + 1), j + 1, horarios)) {
								return;
							}
						}
					}
					else {
						if(! horarios.get(j).getClasse().equals(horarios.get(j - 1).getClasse())) {
							//System.out.printf("To test: %s %s e %s %s\n", a1, a2, horarios.get(j), horarios.get(j + 1));							
							if(swapParHorario(a1, i, a2, i + 1, horarios.get(j), j, horarios.get(j + 1), j + 1, horarios)) {
								return;
							}
						}
					}
				}
			}
		}
	}

	public boolean swapParHorario(final Literal a1, final int indiceA1, final Literal a2, final int indiceA2, 
			final Literal a3, final int indiceA3, final Literal a4, final int indiceA4, ArrayList<Literal> horarios) {
		int diasP1 = getDiasProfessor(a1.getDocente(), horarios);
		int diasP2 = getDiasProfessor(a2.getDocente(), horarios);
		int diasP3 = getDiasProfessor(a3.getDocente(), horarios);
		int diasP4 = getDiasProfessor(a4.getDocente(), horarios);
		/*System.out.printf("  %s: %d dias\n", a1, diasP1);
		System.out.printf("  %s: %d dias\n", a2, diasP2);
		System.out.printf("  %s: %d dias\n", a3, diasP3);
		System.out.printf("  %s: %d dias\n", a4, diasP4);*/
		Literal newA1 = new Literal(a3.getDia(), a3.getHorario(), a1.getEncontro(), a1.getDocente(), a1.getClasse());
		horarios.set(indiceA1, newA1);
		Literal newA2 = new Literal(a4.getDia(), a4.getHorario(), a2.getEncontro(), a2.getDocente(), a2.getClasse());
		horarios.set(indiceA2, newA2);
		Literal newA3 = new Literal(a1.getDia(), a1.getHorario(), a3.getEncontro(), a3.getDocente(), a3.getClasse());
		horarios.set(indiceA3, newA3);
		Literal newA4 = new Literal(a2.getDia(), a2.getHorario(), a4.getEncontro(), a4.getDocente(), a4.getClasse());
		horarios.set(indiceA4, newA4);
		/*System.out.printf("  %s\n", newA1);
		System.out.printf("  %s\n", newA2);
		System.out.printf("  %s\n", newA3);
		System.out.printf("  %s\n", newA4);*/
		ArrayList<Literal> movimento = new ArrayList<Literal>();
		movimento.add(a1);
		movimento.add(a2);
		movimento.add(a3);
		movimento.add(a4);
		int custoReduzido = parReduzCusto(horarios, a1, diasP1, a2, diasP2, a3, diasP3, a4, diasP4);
		if(! parInfringeRestricaoHorario(horarios, a1, a2, a3, a4) && 
				! parInfringeLimiteDiario(horarios, a1, a2, a3, a4) && 
				! parInfringeUnicidadeDocente(horarios, a1, a2, a3, a4) && 
				! parInfringePredefinido(horarios, a1, a2, a3, a4) &&
				custoReduzido >= 0 &&
				(! parMovimentoTabu(movimento) || custoReduzido > 0)) {
			listaTabuGeminada.add(movimento);
			changes += custoReduzido;
			Literal temp1 = horarios.get(indiceA1);
			Literal temp2 = horarios.get(indiceA2);
			horarios.set(indiceA1, newA3);
			horarios.set(indiceA2, newA4);
			horarios.set(indiceA3, temp1);
			horarios.set(indiceA4, temp2);
			/*
			if(parReduzCusto(horarios, a1, diasP1, a2, diasP2, a3, diasP3, a4, diasP4) > 0)
				System.out.printf("***Swapped: %s %s e %s %s\n", a1, a2, a3, a4);
			else
				System.out.printf("   Swapped: %s %s e %s %s\n", a1, a2, a3, a4);*/
			return true;
		}
		else {
			horarios.set(indiceA4, a4);
			horarios.set(indiceA3, a3);
			horarios.set(indiceA2, a2);
			horarios.set(indiceA1, a1);
			return false;
		}
	}

	public boolean parMovimentoTabu(ArrayList<Literal> movimento) {
		for(int i = 0; i < listaTabuGeminada.size(); ++i) {
			if(listaTabuGeminada.get(i).equals(movimento)) {
				return true;
			}
		}
		return false;
	}

	public boolean movimentoTabu(ArrayList<Literal> movimento) {
		for(int i = 0; i < listaTabu.size(); ++i) {
			if(listaTabu.get(i).equals(movimento)) {
				return true;
			}
		}
		return false;
	}

	public int parReduzCusto(final ArrayList<Literal> horarios, Literal a1, int diasP1, Literal a2, int diasP2, 
			Literal a3, int diasP3, Literal a4, int diasP4) {
		int novoDiasP1 = getDiasProfessor(a1.getDocente(), horarios);
		int novoDiasP3 = getDiasProfessor(a3.getDocente(), horarios);
		int novoDiasP4 = getDiasProfessor(a4.getDocente(), horarios);
		/*System.out.printf("  %s: %d dias\n", a1, novoDiasP1);
		System.out.printf("  %s: %d dias\n", a2, novoDiasP2);
		System.out.printf("  %s: %d dias\n", a3, novoDiasP3);
		System.out.printf("  %s: %d dias\n", a4, novoDiasP4);*/
		if(a3.getDocente().equals(a4.getDocente())) {
			return (diasP1 + diasP3 - novoDiasP1 - novoDiasP3);
		}
		else {
			return (diasP1 + diasP3 + diasP4 - novoDiasP1 - novoDiasP3 - novoDiasP4);
		}
	}

	public boolean parInfringeRestricaoHorario(final ArrayList<Literal> horarios, Literal a1, Literal a2, Literal a3, Literal a4) {
		Restricao r1 = new Restricao(a3.getDia(), a3.getHorario());
		Restricao r2 = new Restricao(a4.getDia(), a4.getHorario());
		Restricao r3 = new Restricao(a1.getDia(), a1.getHorario());
		Restricao r4 = new Restricao(a2.getDia(), a2.getHorario());
		for(int i = 0; i < a1.getDocente().getRestricoes().size(); ++i) {
			if(a1.getDocente().getRestricoes().get(i).getDiaSemana() == r1.getDiaSemana() &&
					a1.getDocente().getRestricoes().get(i).getTipo() == r1.getTipo()) {
				//System.out.println("+++" + a1.getDocente().getSiape() + " contem restricao " + r1.getDiaSemana() + r1.getTipo());
				return true;
			}
		}
		for(int i = 0; i < a2.getDocente().getRestricoes().size(); ++i) {
			if(a2.getDocente().getRestricoes().get(i).getDiaSemana() == r2.getDiaSemana() &&
					a2.getDocente().getRestricoes().get(i).getTipo() == r2.getTipo()) {
				//System.out.println("+++" + a2.getDocente().getSiape() + " contem restricao " + r2.getDiaSemana() + r2.getTipo());
				return true;
			}
		}
		for(int i = 0; i < a3.getDocente().getRestricoes().size(); ++i) {
			if(a3.getDocente().getRestricoes().get(i).getDiaSemana() == r3.getDiaSemana() &&
					a3.getDocente().getRestricoes().get(i).getTipo() == r3.getTipo()) {
				//System.out.println("+++" + a3.getDocente().getSiape() + " contem restricao " + r3.getDiaSemana() + r3.getTipo());
				return true;
			}
		}
		for(int i = 0; i < a4.getDocente().getRestricoes().size(); ++i) {
			if(a4.getDocente().getRestricoes().get(i).getDiaSemana() == r4.getDiaSemana() &&
					a4.getDocente().getRestricoes().get(i).getTipo() == r4.getTipo()) {
				//System.out.println("+++" + a4.getDocente().getSiape() + " contem restricao " + r4.getDiaSemana() + r4.getTipo());
				return true;
			}
		}
		//System.out.println("   " + a1.getDocente().getSiape() + " nao contem restricao " + r1.getDiaSemana() + r1.getTipo());
		//System.out.println("   " + a2.getDocente().getSiape() + " nao contem restricao " + r2.getDiaSemana() + r2.getTipo());
		//System.out.println("   " + a3.getDocente().getSiape() + " nao contem restricao " + r3.getDiaSemana() + r3.getTipo());
		//System.out.println("   " + a4.getDocente().getSiape() + " nao contem restricao " + r4.getDiaSemana() + r4.getTipo());
		return false;
	}

	public boolean parInfringeLimiteDiario(final ArrayList<Literal> horarios, Literal a1, Literal a2, Literal a3, Literal a4) {
		ArrayList<Literal> temp = new ArrayList<Literal>();
		for(int i = 0; i < horarios.size(); ++i) {
			if(horarios.get(i).getClasse().equals(a1.getClasse()) || horarios.get(i).getClasse().equals(a2.getClasse()) ||
					horarios.get(i).getClasse().equals(a3.getClasse()) || horarios.get(i).getClasse().equals(a4.getClasse()) ) {
				temp.add(horarios.get(i));
			}
		}
		for(int i = 0; i < temp.size(); ++i) {
			int count = 1;
			for(int j = i + 1; j < temp.size(); ++j) {
				if(temp.get(i).getClasse().equals(temp.get(j).getClasse()) && temp.get(i).getDia() == temp.get(j).getDia()) {
					++count;
					if(count > temp.get(i).getClasse().getLimiteDiarioAulas()) {
						return true;
					}
					temp.remove(j);
					--j;
				}
			}
		}
		return false;
	}

	public boolean parInfringeUnicidadeDocente(final ArrayList<Literal> horarios, Literal a1, Literal a2, Literal a3, Literal a4) {
		for(int i = 0; i < horarios.size(); ++i) {
			for(int j = i + 1; j < horarios.size(); ++j) {
				if(horarios.get(i).getHorario() == horarios.get(j).getHorario() &&
						horarios.get(i).getDia() == horarios.get(j).getDia() &&
						horarios.get(i).getDocente().equals(horarios.get(j).getDocente()) &&
						(horarios.get(i).getDocente().equals(a1.getDocente()) || horarios.get(i).getDocente().equals(a2.getDocente()) ||
								horarios.get(i).getDocente().equals(a3.getDocente()) || horarios.get(i).getDocente().equals(a4.getDocente()))) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean parInfringePredefinido(final ArrayList<Literal> horarios, Literal a1, Literal a2, Literal a3, Literal a4) {
		for(int i = 0; i < listPredefinidos.size(); ++i) {
			if(listPredefinidos.get(i).getClasse().equals(a1.getClasse()) && (listPredefinidos.get(i).getRestricao().getDiaSemana() != a1.getDia() || listPredefinidos.get(i).getRestricao().getTipo() != a1.getHorario()) ||
					listPredefinidos.get(i).getClasse().equals(a2.getClasse()) && (listPredefinidos.get(i).getRestricao().getDiaSemana() != a2.getDia() || listPredefinidos.get(i).getRestricao().getTipo() != a2.getHorario()) ||
					listPredefinidos.get(i).getClasse().equals(a3.getClasse()) && (listPredefinidos.get(i).getRestricao().getDiaSemana() != a3.getDia() || listPredefinidos.get(i).getRestricao().getTipo() != a3.getHorario()) ||
					listPredefinidos.get(i).getClasse().equals(a4.getClasse()) && (listPredefinidos.get(i).getRestricao().getDiaSemana() != a4.getDia() || listPredefinidos.get(i).getRestricao().getTipo() != a4.getHorario())) {
				return true;
			}
		}
		return false;
	}

}
