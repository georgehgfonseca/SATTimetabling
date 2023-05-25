package processamento;

import java.util.*;


import org.prevayler.Prevayler;
import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;

import de.preclipse.bo.*;


public class CNFFormule {

	private TabelaHash hash = new TabelaHash();
	private final Prevayler prevayler;
	private ArrayList<Literal> listLiterais;
	private static final int PESOENCONTRO = 10000000;
	private static final int SEGUNDA = 2;
	private static final int SEXTA = 6;
	private static final int NUMHORARIOS = 5;


	private ArrayList<Classe> listClasses;
	private ArrayList<Docente> listDocentes;
	private ArrayList<EncontroPredefinido> listPredefinidos;

	public CNFFormule(Prevayler prevayler) {
		this.prevayler = prevayler;
		try {
			listLiterais = getLiterais();
			listClasses = ((ArrayList<Classe>)((PrevalentSystem)prevayler.prevalentSystem()).getClasse());
			listDocentes = ((ArrayList<Docente>)((PrevalentSystem)prevayler.prevalentSystem()).getDocente());
			listPredefinidos = ((ArrayList<EncontroPredefinido>)((PrevalentSystem)prevayler.prevalentSystem()).getEncontroPredefinido());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Literal> getListLiterais() {
		return listLiterais;
	}



	public void setListLiterais(ArrayList<Literal> listLiterais) {
		this.listLiterais = listLiterais;
	}

	public void feedSolver(ISolver solver) throws NumberFormatException, Exception {
		//clausulasJanelaHorario(solver);
		clausulasPredefinidas(solver);
		clausulasRestricaoHorario(solver);
		clausulasUnicidade(solver);
		clausulasPreenchimento(solver);
		clausulasLimiteCargaHoraria(solver);
		clausulasGeminacao(solver);
		clausulasLimiteDiarioAulas(solver);
	}

	public ArrayList<Literal> getTempHorarios() {
		ArrayList<Literal> temp = new ArrayList<Literal>();
		for(int i = 0; i < listLiterais.size(); ++i) {
			temp.add(listLiterais.get(i));
		}
		return temp;
	}

	private void clausulasJanelaHorario(ISolver solver) throws Exception {
		ArrayList<Literal> temp = getTempHorarios();
		for(int i = 0; i < temp.size(); ++i) {
			ArrayList<Literal> literaisCurso = new ArrayList<Literal>();
			literaisCurso.add(temp.get(i));
			int codCurso = temp.get(i).getClasse().getCurso();
			int aulasCurso = 1;
			for(int j = i + 1; j < temp.size(); ++j) {
				if(temp.get(j).getClasse().getCurso() == codCurso) {
					literaisCurso.add(temp.get(j));
					++aulasCurso;
					temp.remove(j);
					--j;
				}
			}
			if(aulasCurso > 15) {
				int diferenca = 25 - aulasCurso;
				for(int k = 0; k < literaisCurso.size(); ++k) {
					Curso c = (Curso) prevayler.execute(new CursoConsultaPorCodigoTransaction(codCurso));
					if(c.getTurno() == 1) {
						for(int m = 0; m < (diferenca / 5); ++m) {
							for(int l = SEGUNDA; l <= SEXTA; ++l) {
								Literal literal = new Literal(l, m, literaisCurso.get(k).getEncontro(), literaisCurso.get(k).getDocente(), literaisCurso.get(k).getClasse());
								literal.setId(literal.getId() * -1);
								int [] clause = {hash.addVertice(literal.getId())};
								solver.addClause(new VecInt(clause));
							}
						}
					}
					else {
						for(int m = 5; m < (diferenca / 5) + 5; ++m) {
							for(int l = SEGUNDA; l <= SEXTA; ++l) {
								Literal literal = new Literal(l, m, literaisCurso.get(k).getEncontro(), literaisCurso.get(k).getDocente(), literaisCurso.get(k).getClasse());
								literal.setId(literal.getId() * -1);
								int [] clause = {hash.addVertice(literal.getId())};
								solver.addClause(new VecInt(clause));
							}
						}
					}
				}
			}
		}
	}

	private void clausulasPredefinidas(ISolver solver) throws ContradictionException {
		ArrayList<Literal> literalsList = new ArrayList<Literal>();
		int i = 0;
		while(! listPredefinidos.isEmpty() && i < listPredefinidos.size()) {
			Literal l = new Literal(listPredefinidos.get(i).getRestricao().getDiaSemana(),
					listPredefinidos.get(i).getRestricao().getTipo() - 1,
					1,
					listPredefinidos.get(i).getDocente(),
					listPredefinidos.get(i).getClasse());
			for(int j = 0; ! literalsList.isEmpty() && j < literalsList.size(); ++j) {
				if(literalsList.get(j).getId() % PESOENCONTRO == l.getId() % PESOENCONTRO) {
					l.setId(l.getId() + PESOENCONTRO);
				}
			}
			literalsList.add(l);
			int [] clause = {hash.addVertice(l.getId())};
			solver.addClause(new VecInt(clause));
			++i;
		}
	}

	private void clausulasRestricaoHorario(ISolver solver) throws NumberFormatException, Exception {
		//clausulasRestricaoHorarioClasses(listClasses, listDocentes); 
		clausulasRestricaoHorarioDocentes(solver, listClasses, listDocentes);
	}

	private void clausulasRestricaoHorarioClasses(ISolver solver, ArrayList<Classe> listClasses, ArrayList<Docente> listDocentes) throws NumberFormatException, Exception {
		for(int i = 0; ! listClasses.isEmpty() && i < listClasses.size(); ++i) { //Percorre lista de classes
			for(int k = SEGUNDA; k <= SEXTA; ++k) {
				for(int h = 1; h <= NUMHORARIOS; ++h) {
					if(conferirRestricaoClasse(k, h, listClasses.get(i))) {
						for(int encontro = 1; encontro <= listClasses.get(i).getCargaHoraria(); ++encontro) {
							Literal l = new Literal(k, h - 1, encontro, getDocente(Integer.parseInt(listClasses.get(i).getSiapeDocente())), listClasses.get(i));
							l.setId(l.getId() * -1);
							int [] clause = {hash.addVertice(l.getId())};
							solver.addClause(new VecInt(clause));
						}
					}
				}
			}
		}
	}

	private void clausulasRestricaoHorarioDocentes(ISolver solver, ArrayList<Classe> listClasses, ArrayList<Docente> listDocentes) throws ContradictionException {
		for(int i = 0; i < listDocentes.size(); ++i) { //Percorre lista de classes
			for(int k = 0; k < listDocentes.get(i).getRestricoes().size(); ++k) {
				ArrayList<Classe> classes = getClassesMinistradas(listClasses, listDocentes.get(i));
				for(int j = 0; j < classes.size(); ++j) {
					for(int encontro = 1; encontro <= classes.get(j).getCargaHoraria(); ++encontro) {
						Literal l = new Literal(listDocentes.get(i).getRestricoes().get(k).getDiaSemana(), listDocentes.get(i).getRestricoes().get(k).getTipo(), encontro, listDocentes.get(i), classes.get(j));
						l.setId(l.getId() * -1);
						int [] clause = {hash.addVertice(l.getId())};
						solver.addClause(new VecInt(clause));					
					}
				}
			}
		}
	}

	public boolean conferirRestricaoClasse(int diaSemana, int tipo, Classe c) { //Confere se a classe c NAO esta disponivel no horario(diaSemana, tipo)
		for(int i = 0; i < c.getRestricoes().size(); ++i) {
			if(c.getRestricoes().get(i).getDiaSemana() == diaSemana && c.getRestricoes().get(i).getTipo() == tipo) {
				return true;
			}
		}
		return false;
	}

	public boolean conferirRestricaoDocente(int diaSemana, int tipo, Docente d) { //Confere se a docente d NAO esta disponivel no horario(diaSemana, tipo)
		for(int i = 0; i < d.getRestricoes().size(); ++i) {
			if(d.getRestricoes().get(i).getDiaSemana() == diaSemana && d.getRestricoes().get(i).getTipo() == tipo) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Classe> getClassesMinistradas(ArrayList<Classe> listClasses, Docente docente) {
		ArrayList<Classe> classesMinistradas = new ArrayList<Classe>();
		for(int i = 0; i < listClasses.size(); ++i) {
			if(docente.getSiape() == Integer.parseInt(listClasses.get(i).getSiapeDocente()))
				classesMinistradas.add(listClasses.get(i));
		}
		return classesMinistradas;
	}

	private ArrayList<Literal> getLiterais() {
		ArrayList<Literal> listLiterais = new ArrayList<Literal>();
		ArrayList<Classe> listClasses = ((ArrayList<Classe>)((PrevalentSystem)prevayler.prevalentSystem()).getClasse());
		for(int i = 0; i < listClasses.size(); ++i) { //Percorre lista de classes
			for(int k = 1; k < listClasses.get(i).getCargaHoraria() + 1; ++k) {				
				try {
					Literal l;				
					l = new Literal(0, 0, k, getDocente(Integer.parseInt(listClasses.get(i).getSiapeDocente())), listClasses.get(i));
					listLiterais.add(l); //É adicionado um novo vertice (sem preassigned!
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		return listLiterais;
	}

	private Docente getDocente(int siape) throws Exception {
		return (Docente) prevayler.execute(new DocenteConsultaPorSiapeTransaction(siape));
	}

	private void clausulasUnicidade(ISolver solver) throws Exception {
		for(int i = 0; i < listLiterais.size(); ++i) { //Itera cada vértice
			for(int j = i + 1; j < listLiterais.size(); ++j) { //Itera a lista de vertices na busca de arestas
				if((listLiterais.get(i).getClasse().getCurso() == listLiterais.get(j).getClasse().getCurso() || listLiterais.get(i).getDocente().getSiape() == listLiterais.get(j).getDocente().getSiape())) { //Aresta!
					adicionaClausulasUnicidade(solver, listLiterais.get(i), listLiterais.get(j));
				}
			}
		}
	}

	private void adicionaClausulasUnicidade(ISolver solver, Literal l1, Literal l2) throws Exception {
		Curso c = (Curso) prevayler.execute(new CursoConsultaPorCodigoTransaction(l1.getClasse().getCurso()));
		if(c.getTurno() == 1) {
			for(int k = SEGUNDA; k <= SEXTA; ++k) {                           //Para cada dia da semana
				for(int h = 0; h < NUMHORARIOS; ++h) {                      //Para cada horário
					Literal temp1 = new Literal(k, h, l1.getEncontro(), l1.getDocente(), l1.getClasse());
					Literal temp2 = new Literal(k, h, l2.getEncontro(), l2.getDocente(), l2.getClasse());
					temp1.setId(temp1.getId() * -1);
					temp2.setId(temp2.getId() * -1);
					int [] clause = {hash.addVertice(temp1.getId()), hash.addVertice(temp2.getId())};
					solver.addClause(new VecInt(clause));
				}
			}
		}
		else {
			for(int k = SEGUNDA; k <= SEXTA; ++k) {                           //Para cada dia da semana
				for(int h = 5; h < NUMHORARIOS + 5; ++h) {                      //Para cada horário
					Literal temp1 = new Literal(k, h, l1.getEncontro(), l1.getDocente(), l1.getClasse());
					Literal temp2 = new Literal(k, h, l2.getEncontro(), l2.getDocente(), l2.getClasse());
					temp1.setId(temp1.getId() * -1);
					temp2.setId(temp2.getId() * -1);
					int [] clause = {hash.addVertice(temp1.getId()), hash.addVertice(temp2.getId())};
					solver.addClause(new VecInt(clause));
				}
			}
		}
	}

	private void clausulasPreenchimento(ISolver solver) throws Exception {
		for(int i = 0; i < listLiterais.size(); ++i) {
			int [] clause = new int [25];
			int pos = 0;
			Curso c = (Curso) prevayler.execute(new CursoConsultaPorCodigoTransaction(listLiterais.get(i).getClasse().getCurso()));
			if(c.getTurno() == 1) {			
				for(int k = SEGUNDA; k <= SEXTA; ++k) {                     //Para cada dia da semana
					for(int h = 0; h < NUMHORARIOS; ++h) {                //Para cada horário
						Literal l = new Literal(k, h, listLiterais.get(i).getEncontro(), listLiterais.get(i).getDocente(), listLiterais.get(i).getClasse());
						clause[pos] = hash.addVertice(l.getId());
						++pos;
					}
				}
			}
			else {
				for(int k = SEGUNDA; k <= SEXTA; ++k) {                     //Para cada dia da semana
					for(int h = 5; h < NUMHORARIOS + 5; ++h) {                //Para cada horário
						Literal l = new Literal(k, h, listLiterais.get(i).getEncontro(), listLiterais.get(i).getDocente(), listLiterais.get(i).getClasse());
						clause[pos] = hash.addVertice(l.getId());
						++pos;
					}
				}
			}
			solver.addClause(new VecInt(clause));
		}
	}

	private void clausulasLimiteCargaHoraria(ISolver solver) throws Exception {
		for(int i = 0; i < listLiterais.size(); ++i) {
			Curso c = (Curso) prevayler.execute(new CursoConsultaPorCodigoTransaction(listLiterais.get(i).getClasse().getCurso()));
			if(c.getTurno() == 1) {				
				for(int k1 = SEGUNDA; k1 <= SEXTA; ++k1) {
					for(int h1 = 0; h1 < NUMHORARIOS; ++h1) {  
						for(int k2 = SEGUNDA; k2 <= SEXTA; ++k2) {
							for(int h2 = 0; h2 < NUMHORARIOS; ++h2) {
								if(k2 >= k1 && h2 >= h1 && (h1 != h2 || k1 != k2)) {
									Literal l1 = new Literal(k1, h1, listLiterais.get(i).getEncontro(), listLiterais.get(i).getDocente(), listLiterais.get(i).getClasse());
									Literal l2 = new Literal(k2, h2, listLiterais.get(i).getEncontro(), listLiterais.get(i).getDocente(), listLiterais.get(i).getClasse());
									l1.setId(l1.getId() * -1);
									l2.setId(l2.getId() * -1);
									int [] clause = {hash.addVertice(l1.getId()), hash.addVertice(l2.getId())};
									solver.addClause(new VecInt(clause));
								}
							}
						}
					}

				}
			}
			else {
				for(int k1 = SEGUNDA; k1 <= SEXTA; ++k1) {
					for(int h1 = 5; h1 < NUMHORARIOS + 5; ++h1) {  
						for(int k2 = SEGUNDA; k2 <= SEXTA; ++k2) {
							for(int h2 = 5; h2 < NUMHORARIOS + 5; ++h2) {
								if(k2 >= k1 && h2 >= h1 && (h1 != h2 || k1 != k2)) {
									Literal l1 = new Literal(k1, h1, listLiterais.get(i).getEncontro(), listLiterais.get(i).getDocente(), listLiterais.get(i).getClasse());
									Literal l2 = new Literal(k2, h2, listLiterais.get(i).getEncontro(), listLiterais.get(i).getDocente(), listLiterais.get(i).getClasse());
									l1.setId(l1.getId() * -1);
									l2.setId(l2.getId() * -1);
									int [] clause = {hash.addVertice(l1.getId()), hash.addVertice(l2.getId())};
									solver.addClause(new VecInt(clause));
								}
							}
						}
					}

				}
			}
		}
	}

	private void clausulasGeminacao(ISolver solver) throws Exception {
		for(int i = 0; i < listLiterais.size(); ++i) {
			int aulasGeminadas = listLiterais.get(i).getClasse().getMinAulasGeminadas();
			Curso c = (Curso) prevayler.execute(new CursoConsultaPorCodigoTransaction(listLiterais.get(i).getClasse().getCurso()));
			if(c.getTurno() == 1) {				
				for(int d = SEGUNDA; d <= SEXTA; ++d) {
					for(int j = 0; j < aulasGeminadas; ++j) {
						for(int h = 0; h < NUMHORARIOS - 1; ++h) {					
							Literal l1 = new Literal(d, h, (j * 2) + 1, listLiterais.get(i).getDocente(), listLiterais.get(i).getClasse());
							Literal l2 = new Literal(d, h + 1, (j * 2) + 2, listLiterais.get(i).getDocente(), listLiterais.get(i).getClasse());
							l1.setId(l1.getId() * -1);
							int [] clause = {hash.addVertice(l1.getId()), hash.addVertice(l2.getId())};
							solver.addClause(new VecInt(clause));
							l1.setId(l1.getId() * -1);
							l2.setId(l2.getId() * -1);
							int [] clause2 = {hash.addVertice(l2.getId()), hash.addVertice(l1.getId())};
							solver.addClause(new VecInt(clause2));
						}
						//nao colocar aula a ser geminada no ultimo horario de cada dia						
						Literal l3 = new Literal(d, NUMHORARIOS - 1, (j * 2) + 1, listLiterais.get(i).getDocente(), listLiterais.get(i).getClasse());
						l3.setId(l3.getId() * -1);
						int [] clause = {hash.addVertice(l3.getId())};
						solver.addClause(new VecInt(clause));
					}
				}
			}
			else {
				for(int d = SEGUNDA; d <= SEXTA; ++d) {
					for(int j = 0; j < aulasGeminadas; ++j) {
						for(int h = 5; h < NUMHORARIOS + 4; ++h) {					
							Literal l1 = new Literal(d, h, (j * 2) + 1, listLiterais.get(i).getDocente(), listLiterais.get(i).getClasse());
							Literal l2 = new Literal(d, h + 1, (j * 2) + 2, listLiterais.get(i).getDocente(), listLiterais.get(i).getClasse());
							l1.setId(l1.getId() * -1);
							int [] clause = {hash.addVertice(l1.getId()), hash.addVertice(l2.getId())};
							solver.addClause(new VecInt(clause));
							l1.setId(l1.getId() * -1);
							l2.setId(l2.getId() * -1);
							int [] clause2 = {hash.addVertice(l2.getId()), hash.addVertice(l1.getId())};
							solver.addClause(new VecInt(clause2));
						}
						//nao colocar aula a ser geminada no ultimo horario de cada dia						
						Literal l3 = new Literal(d, NUMHORARIOS + 4, (j * 2) + 1, listLiterais.get(i).getDocente(), listLiterais.get(i).getClasse());
						l3.setId(l3.getId() * -1);
						int [] clause = {hash.addVertice(l3.getId())};
						solver.addClause(new VecInt(clause));
					}
				}
			}
			i += listLiterais.get(i).getClasse().getCargaHoraria() - 1;
		}
	}

	private void clausulasLimiteDiarioAulas(ISolver solver) throws Exception {
		ArrayList<Literal> temp = getLiterais();
		for(int i = 0; i < temp.size(); ++i) {
			ArrayList<Literal> aulasClasse = new ArrayList<Literal>();
			int limiteDiario = temp.get(i).getClasse().getLimiteDiarioAulas();
			aulasClasse.add(temp.get(i));
			for(int j = i + 1; j < temp.size(); ++j) {
				if(temp.get(i).getClasse().equals(temp.get(j).getClasse())) {
					aulasClasse.add(temp.get(j));
					temp.remove(j);
					--j;
				}
			}
			Curso c = (Curso) prevayler.execute(new CursoConsultaPorCodigoTransaction(temp.get(i).getClasse().getCurso()));
			if(limiteDiario == 1) {
				if(c.getTurno() == 1) {				
					for(int h1 = 0; h1 < NUMHORARIOS; ++h1) {
						for(int h2 = 0; h2 < NUMHORARIOS; ++h2) {
							if(h1 != h2) {
								for(int k = 0; k < aulasClasse.size(); ++k) {
									for(int l = k + 1; l < aulasClasse.size(); ++l) {
										for(int d = SEGUNDA; d <= SEXTA; ++d) {
											Literal l1 = new Literal(d, h1, aulasClasse.get(k).getEncontro(), aulasClasse.get(k).getDocente(), aulasClasse.get(k).getClasse());
											Literal l2 = new Literal(d, h2, aulasClasse.get(l).getEncontro(), aulasClasse.get(l).getDocente(), aulasClasse.get(l).getClasse());
											l1.setId(l1.getId() * -1);
											l2.setId(l2.getId() * -1);
											int [] clause = {hash.addVertice(l1.getId()), hash.addVertice(l2.getId())};
											solver.addClause(new VecInt(clause));
										}
									}
								}
							}
						}
					}
				}
				else {
					for(int h1 = 5; h1 < NUMHORARIOS + 5; ++h1) {
						for(int h2 = 5; h2 < NUMHORARIOS + 5; ++h2) {
							if(h1 != h2) {
								for(int k = 0; k < aulasClasse.size(); ++k) {
									for(int l = k + 1; l < aulasClasse.size(); ++l) {
										for(int d = SEGUNDA; d <= SEXTA; ++d) {
											Literal l1 = new Literal(d, h1, aulasClasse.get(k).getEncontro(), aulasClasse.get(k).getDocente(), aulasClasse.get(k).getClasse());
											Literal l2 = new Literal(d, h2, aulasClasse.get(l).getEncontro(), aulasClasse.get(l).getDocente(), aulasClasse.get(l).getClasse());
											l1.setId(l1.getId() * -1);
											l2.setId(l2.getId() * -1);
											int [] clause = {hash.addVertice(l1.getId()), hash.addVertice(l2.getId())};
											solver.addClause(new VecInt(clause));
										}
									}
								}
							}
						}
					}
				}
			}
			else if(limiteDiario == 2) {
				if(c.getTurno() == 1) {				
					for(int h1 = 0; h1 < NUMHORARIOS; ++h1) {
						for(int h2 = 0; h2 < NUMHORARIOS; ++h2) {
							for(int h3 = 0; h3 < NUMHORARIOS; ++h3) {
								if(h1 != h2 && h1 != h3 && h2 != h3) {
									for(int k = 0; k < aulasClasse.size(); ++k) {
										for(int l = k + 1; l < aulasClasse.size(); ++l) {
											for(int m = l + 1; m < aulasClasse.size(); ++m) {
												for(int d = SEGUNDA; d <= SEXTA; ++d) {
													Literal l1 = new Literal(d, h1, aulasClasse.get(k).getEncontro(), aulasClasse.get(k).getDocente(), aulasClasse.get(k).getClasse());
													Literal l2 = new Literal(d, h2, aulasClasse.get(l).getEncontro(), aulasClasse.get(l).getDocente(), aulasClasse.get(l).getClasse());
													Literal l3 = new Literal(d, h3, aulasClasse.get(m).getEncontro(), aulasClasse.get(m).getDocente(), aulasClasse.get(m).getClasse());
													l1.setId(l1.getId() * -1);
													l2.setId(l2.getId() * -1);
													l3.setId(l3.getId() * -1);
													int [] clause = {hash.addVertice(l1.getId()), hash.addVertice(l2.getId()), hash.addVertice(l3.getId())};
													solver.addClause(new VecInt(clause));
												}
											}
										}
									}
								}
							}
						}
					}
				}
				else {
					for(int h1 = 5; h1 < NUMHORARIOS + 5; ++h1) {
						for(int h2 = 5; h2 < NUMHORARIOS + 5; ++h2) {
							for(int h3 = 5; h3 < NUMHORARIOS + 5; ++h3) {
								if(h1 != h2 && h1 != h3 && h2 != h3) {
									for(int k = 0; k < aulasClasse.size(); ++k) {
										for(int l = k + 1; l < aulasClasse.size(); ++l) {
											for(int m = l + 1; m < aulasClasse.size(); ++m) {
												for(int d = SEGUNDA; d <= SEXTA; ++d) {
													Literal l1 = new Literal(d, h1, aulasClasse.get(k).getEncontro(), aulasClasse.get(k).getDocente(), aulasClasse.get(k).getClasse());
													Literal l2 = new Literal(d, h2, aulasClasse.get(l).getEncontro(), aulasClasse.get(l).getDocente(), aulasClasse.get(l).getClasse());
													Literal l3 = new Literal(d, h3, aulasClasse.get(m).getEncontro(), aulasClasse.get(m).getDocente(), aulasClasse.get(m).getClasse());
													l1.setId(l1.getId() * -1);
													l2.setId(l2.getId() * -1);
													l3.setId(l3.getId() * -1);
													int [] clause = {hash.addVertice(l1.getId()), hash.addVertice(l2.getId()), hash.addVertice(l3.getId())};
													solver.addClause(new VecInt(clause));
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			else if(limiteDiario == 3) {
				if(c.getTurno() == 1) {				
					for(int h1 = 0; h1 < NUMHORARIOS; ++h1) {
						for(int h2 = 0; h2 < NUMHORARIOS; ++h2) {
							for(int h3 = 0; h3 < NUMHORARIOS; ++h3) {
								for(int h4 = 0; h4 < NUMHORARIOS; ++h4) {
									if(h1 != h2 && h1 != h3 && h2 != h3 && h1 != h4 && h2 != h4 && h3 != h4) {
										for(int k = 0; k < aulasClasse.size(); ++k) {
											for(int l = k + 1; l < aulasClasse.size(); ++l) {
												for(int m = l + 1; m < aulasClasse.size(); ++m) {
													for(int n = m + 1; n < aulasClasse.size(); ++n) {
														for(int d = SEGUNDA; d <= SEXTA; ++d) {
															Literal l1 = new Literal(d, h1, aulasClasse.get(k).getEncontro(), aulasClasse.get(k).getDocente(), aulasClasse.get(k).getClasse());
															Literal l2 = new Literal(d, h2, aulasClasse.get(l).getEncontro(), aulasClasse.get(l).getDocente(), aulasClasse.get(l).getClasse());
															Literal l3 = new Literal(d, h3, aulasClasse.get(m).getEncontro(), aulasClasse.get(m).getDocente(), aulasClasse.get(m).getClasse());
															Literal l4 = new Literal(d, h4, aulasClasse.get(n).getEncontro(), aulasClasse.get(n).getDocente(), aulasClasse.get(n).getClasse());
															l1.setId(l1.getId() * -1);
															l2.setId(l2.getId() * -1);
															l3.setId(l3.getId() * -1);
															l4.setId(l4.getId() * -1);
															int [] clause = {hash.addVertice(l1.getId()), hash.addVertice(l2.getId()), hash.addVertice(l3.getId()), hash.addVertice(l4.getId())};
															solver.addClause(new VecInt(clause));
														}
													}
												}
											}
										}
									}
								}

							}
						}
					}
				}
				else {
					for(int h1 = 5; h1 < NUMHORARIOS + 5; ++h1) {
						for(int h2 = 5; h2 < NUMHORARIOS + 5; ++h2) {
							for(int h3 = 5; h3 < NUMHORARIOS + 5; ++h3) {
								for(int h4 = 5; h4 < NUMHORARIOS + 5; ++h4) {
									if(h1 != h2 && h1 != h3 && h2 != h3 && h1 != h4 && h2 != h4 && h3 != h4) {
										for(int k = 0; k < aulasClasse.size(); ++k) {
											for(int l = k + 1; l < aulasClasse.size(); ++l) {
												for(int m = l + 1; m < aulasClasse.size(); ++m) {
													for(int n = m + 1; n < aulasClasse.size(); ++n) {
														for(int d = SEGUNDA; d <= SEXTA; ++d) {
															Literal l1 = new Literal(d, h1, aulasClasse.get(k).getEncontro(), aulasClasse.get(k).getDocente(), aulasClasse.get(k).getClasse());
															Literal l2 = new Literal(d, h2, aulasClasse.get(l).getEncontro(), aulasClasse.get(l).getDocente(), aulasClasse.get(l).getClasse());
															Literal l3 = new Literal(d, h3, aulasClasse.get(m).getEncontro(), aulasClasse.get(m).getDocente(), aulasClasse.get(m).getClasse());
															Literal l4 = new Literal(d, h4, aulasClasse.get(n).getEncontro(), aulasClasse.get(n).getDocente(), aulasClasse.get(n).getClasse());
															l1.setId(l1.getId() * -1);
															l2.setId(l2.getId() * -1);
															l3.setId(l3.getId() * -1);
															l4.setId(l4.getId() * -1);
															int [] clause = {hash.addVertice(l1.getId()), hash.addVertice(l2.getId()), hash.addVertice(l3.getId()), hash.addVertice(l4.getId())};
															solver.addClause(new VecInt(clause));
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			else if(limiteDiario == 4) {				
				if(c.getTurno() == 1) {				
					for(int h1 = 0; h1 < NUMHORARIOS; ++h1) {
						for(int h2 = 0; h2 < NUMHORARIOS; ++h2) {
							for(int h3 = 0; h3 < NUMHORARIOS; ++h3) {
								for(int h4 = 0; h4 < NUMHORARIOS; ++h4) {
									for(int h5 = 0; h5 < NUMHORARIOS; ++h5) {
										if(h1 != h2 && h1 != h3 && h2 != h3 && h1 != h4 && h2 != h4 && h3 != h4 && h1 != h5 && h2 != h5 && h3 != h5 && h4 != h5) {
											for(int k = 0; k < aulasClasse.size(); ++k) {
												for(int l = k + 1; l < aulasClasse.size(); ++l) {
													for(int m = l + 1; m < aulasClasse.size(); ++m) {
														for(int n = m + 1; n < aulasClasse.size(); ++n) {
															for(int p = n + 1; p < aulasClasse.size(); ++p) {
																for(int d = SEGUNDA; d <= SEXTA; ++d) {
																	Literal l1 = new Literal(d, h1, aulasClasse.get(k).getEncontro(), aulasClasse.get(k).getDocente(), aulasClasse.get(k).getClasse());
																	Literal l2 = new Literal(d, h2, aulasClasse.get(l).getEncontro(), aulasClasse.get(l).getDocente(), aulasClasse.get(l).getClasse());
																	Literal l3 = new Literal(d, h3, aulasClasse.get(m).getEncontro(), aulasClasse.get(m).getDocente(), aulasClasse.get(m).getClasse());
																	Literal l4 = new Literal(d, h4, aulasClasse.get(n).getEncontro(), aulasClasse.get(n).getDocente(), aulasClasse.get(n).getClasse());
																	Literal l5 = new Literal(d, h5, aulasClasse.get(p).getEncontro(), aulasClasse.get(p).getDocente(), aulasClasse.get(p).getClasse());
																	l1.setId(l1.getId() * -1);
																	l2.setId(l2.getId() * -1);
																	l3.setId(l3.getId() * -1);
																	l4.setId(l4.getId() * -1);
																	l5.setId(l5.getId() * -1);
																	int [] clause = {hash.addVertice(l1.getId()), hash.addVertice(l2.getId()), hash.addVertice(l3.getId()), hash.addVertice(l4.getId()), hash.addVertice(l5.getId())};
																	solver.addClause(new VecInt(clause));
																}
															}
														}
													}
												}
											}
										}
									}
								}

							}
						}
					}
				}
				else {
					for(int h1 = 5; h1 < NUMHORARIOS + 5; ++h1) {
						for(int h2 = 5; h2 < NUMHORARIOS + 5; ++h2) {
							for(int h3 = 5; h3 < NUMHORARIOS + 5; ++h3) {
								for(int h4 = 5; h4 < NUMHORARIOS + 5; ++h4) {
									for(int h5 = 5; h5 < NUMHORARIOS + 5; ++h5) {
										if(h1 != h2 && h1 != h3 && h2 != h3 && h1 != h4 && h2 != h4 && h3 != h4 && h1 != h5 && h2 != h5 && h3 != h5 && h4 != h5) {
											for(int k = 0; k < aulasClasse.size(); ++k) {
												for(int l = k + 1; l < aulasClasse.size(); ++l) {
													for(int m = l + 1; m < aulasClasse.size(); ++m) {
														for(int n = m + 1; n < aulasClasse.size(); ++n) {
															for(int p = n + 1; p < aulasClasse.size(); ++p) {
																for(int d = SEGUNDA; d <= SEXTA; ++d) {
																	Literal l1 = new Literal(d, h1, aulasClasse.get(k).getEncontro(), aulasClasse.get(k).getDocente(), aulasClasse.get(k).getClasse());
																	Literal l2 = new Literal(d, h2, aulasClasse.get(l).getEncontro(), aulasClasse.get(l).getDocente(), aulasClasse.get(l).getClasse());
																	Literal l3 = new Literal(d, h3, aulasClasse.get(m).getEncontro(), aulasClasse.get(m).getDocente(), aulasClasse.get(m).getClasse());
																	Literal l4 = new Literal(d, h4, aulasClasse.get(n).getEncontro(), aulasClasse.get(n).getDocente(), aulasClasse.get(n).getClasse());
																	Literal l5 = new Literal(d, h5, aulasClasse.get(p).getEncontro(), aulasClasse.get(p).getDocente(), aulasClasse.get(p).getClasse());
																	l1.setId(l1.getId() * -1);
																	l2.setId(l2.getId() * -1);
																	l3.setId(l3.getId() * -1);
																	l4.setId(l4.getId() * -1);
																	l5.setId(l5.getId() * -1);
																	int [] clause = {hash.addVertice(l1.getId()), hash.addVertice(l2.getId()), hash.addVertice(l3.getId()), hash.addVertice(l4.getId()), hash.addVertice(l5.getId())};
																	solver.addClause(new VecInt(clause));
																}
															}
														}
													}
												}
											}
										}
									}
								}

							}
						}
					}
				}
			}
		}
	}

	public ArrayList<Integer> selecionaMapeiaHorarios(int[] literals) {
		ArrayList<Integer> resultado = new ArrayList<Integer>();
		for(int i = 0; i < literals.length; ++i) {
			if(literals[i] > 0) {
				resultado.add(hash.getVertice(literals[i]));
			}
		}
		return resultado;
	}

}