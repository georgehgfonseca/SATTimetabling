package processamento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import de.preclipse.bo.*;

public class FuncaoObjetivo {

	private int unicidadeDocente;
	private int unicidadeClasse;
	private int preenchimentoCargaHoraria;
	private int restricaoHorarioDocente;
	private int restricaoHorarioClasse;
	private int geminacaoAulas;
	private int limiteDiarioAulas;
	private int minimizacaoJanela;
	private int diasDocenteEscola;

	private int pesoUnicidadeDocente;
	private int pesoUnicidadeClasse;
	private int pesoPreenchimentoCargaHoraria;
	private int pesoRestricaoHorarioDocente;
	private int pesoEncontroPredefinido;
	private int pesoGeminacaoAulas;
	private int pesoLimiteDiarioAulas;
	private int pesoMinimizacaoJanela;
	private int pesoDiasDocenteEscola;

	private ArrayList<Literal> horarios;
	private ArrayList<Violacao> violacoes;
	private ArrayList<Classe> listClasses;
	private ArrayList<Docente> listDocentes;
	private ArrayList<Curso> listCursos;
	private ArrayList<Sala> listSalas;
	ArrayList<EncontroPredefinido> listPredefinidos;

	public FuncaoObjetivo() {

	}

	public FuncaoObjetivo(ArrayList<Literal> horarios, int pesoUnicidadeDocente, int pesoUnicidadeClasse,
			int pesoPreenchimentoCargaHoraria, int pesoRestricaoHorarioDocente,
			int pesoEncontroPredefinido, int pesoGeminacaoAulas,
			int pesoLimiteDiarioAulas, int pesoDiasDocenteEscola, int pesoMinimizacaoJanela, 
			ArrayList<Classe> listClasses, ArrayList<Docente> listDocentes, 
			ArrayList<EncontroPredefinido> listPredefinidos, ArrayList<Curso> listCursos, ArrayList<Sala> listSalas) {
		setHorarios(horarios);
		setPesoUnicidadeDocente(pesoUnicidadeDocente);
		setPesoUnicidadeClasse(pesoUnicidadeClasse);
		setPesoPreenchimentoCargaHoraria(pesoPreenchimentoCargaHoraria);
		setPesoRestricaoHorarioDocente(pesoRestricaoHorarioDocente);
		setPesoRestricaoHorarioClasse(pesoEncontroPredefinido);
		setPesoGeminacaoAulas(pesoGeminacaoAulas);
		setPesoLimiteDiarioAulas(pesoLimiteDiarioAulas);
		setPesoMinimizacaoJanela(pesoMinimizacaoJanela);
		setPesoDiasDocenteEscola(pesoDiasDocenteEscola);
		setListClasses(listClasses);
		setListDocentes(listDocentes);
		setListPredefinidos(listPredefinidos);
		setListCursos(listCursos);
		setListSalas(listSalas);
		violacoes = new ArrayList<Violacao>();
	}

	public ArrayList<Literal> getHorarios() {
		return horarios;
	}

	public void setHorarios(ArrayList<Literal> horarios) {
		this.horarios = horarios;
	}

	public int getUnicidadeDocente() {
		return unicidadeDocente;
	}

	public void setUnicidadeDocente(int unicidadeDocente) {
		this.unicidadeDocente = unicidadeDocente;
	}

	public int getUnicidadeClasse() {
		return unicidadeClasse;
	}

	public void setUnicidadeClasse(int unicidadeClasse) {
		this.unicidadeClasse = unicidadeClasse;
	}

	public int getPreenchimentoCargaHoraria() {
		return preenchimentoCargaHoraria;
	}

	public void setPreenchimentoCargaHoraria(int preenchimentoCargaHoraria) {
		this.preenchimentoCargaHoraria = preenchimentoCargaHoraria;
	}

	public int getRestricaoHorarioDocente() {
		return restricaoHorarioDocente;
	}

	public void setRestricaoHorarioDocente(int restricaoHorarioDocente) {
		this.restricaoHorarioDocente = restricaoHorarioDocente;
	}

	public int getRestricaoHorarioClasse() {
		return restricaoHorarioClasse;
	}

	public void setRestricaoHorarioClasse(int restricaoHorarioClasse) {
		this.restricaoHorarioClasse = restricaoHorarioClasse;
	}

	public int getGeminacaoAulas() {
		return geminacaoAulas;
	}

	public void setGeminacaoAulas(int geminacaoAulas) {
		this.geminacaoAulas = geminacaoAulas;
	}

	public int getLimiteDiarioAulas() {
		return limiteDiarioAulas;
	}

	public void setLimiteDiarioAulas(int limiteDiarioAulas) {
		this.limiteDiarioAulas = limiteDiarioAulas;
	}

	public int getMinimizacaoJanela() {
		return minimizacaoJanela;
	}

	public void setMinimizacaoJanela(int minimizacaoJanela) {
		this.minimizacaoJanela = minimizacaoJanela;
	}

	public int getDiasDocenteEscola() {
		return diasDocenteEscola;
	}

	public void setDiasDocenteEscola(int diasDocenteEscola) {
		this.diasDocenteEscola = diasDocenteEscola;
	}

	public int getPesoUnicidadeDocente() {
		return pesoUnicidadeDocente;
	}

	public void setPesoUnicidadeDocente(int pesoUnicidadeDocente) {
		this.pesoUnicidadeDocente = pesoUnicidadeDocente;
	}

	public int getPesoUnicidadeClasse() {
		return pesoUnicidadeClasse;
	}

	public void setPesoUnicidadeClasse(int peosUnicidadeClasse) {
		this.pesoUnicidadeClasse = peosUnicidadeClasse;
	}

	public int getPesoPreenchimentoCargaHoraria() {
		return pesoPreenchimentoCargaHoraria;
	}

	public void setPesoPreenchimentoCargaHoraria(int pesoPreenchimentoCargaHoraria) {
		this.pesoPreenchimentoCargaHoraria = pesoPreenchimentoCargaHoraria;
	}

	public int getPesoRestricaoHorarioDocente() {
		return pesoRestricaoHorarioDocente;
	}

	public void setPesoRestricaoHorarioDocente(int pesoRestricaoHorarioDocente) {
		this.pesoRestricaoHorarioDocente = pesoRestricaoHorarioDocente;
	}

	public int getPesoRestricaoHorarioClasse() {
		return pesoEncontroPredefinido;
	}

	public void setPesoRestricaoHorarioClasse(int pesoRestricaoHorarioClasse) {
		this.pesoEncontroPredefinido = pesoRestricaoHorarioClasse;
	}

	public int getPesoGeminacaoAulas() {
		return pesoGeminacaoAulas;
	}

	public void setPesoGeminacaoAulas(int pesoGeminacaoAulas) {
		this.pesoGeminacaoAulas = pesoGeminacaoAulas;
	}

	public int getPesoLimiteDiarioAulas() {
		return pesoLimiteDiarioAulas;
	}

	public void setPesoLimiteDiarioAulas(int pesoLimiteDiarioAulas) {
		this.pesoLimiteDiarioAulas = pesoLimiteDiarioAulas;
	}

	public int getPesoMinimizacaoJanela() {
		return pesoMinimizacaoJanela;
	}

	public void setPesoMinimizacaoJanela(int pesoMinimizacaoJanela) {
		this.pesoMinimizacaoJanela = pesoMinimizacaoJanela;
	}

	public int getPesoDiasDocenteEscola() {
		return pesoDiasDocenteEscola;
	}

	public void setPesoDiasDocenteEscola(int pesoDiasDocenteEscola) {
		this.pesoDiasDocenteEscola = pesoDiasDocenteEscola;
	}

	public ArrayList<Violacao> getViolacoes() {
		return violacoes;
	}

	public void setViolacoes(ArrayList<Violacao> violacoes) {
		this.violacoes = violacoes;
	}

	public ArrayList<Classe> getListClasses() {
		return listClasses;
	}

	public void setListClasses(ArrayList<Classe> listClasses) {
		this.listClasses = listClasses;
	}

	public ArrayList<Docente> getListDocentes() {
		return listDocentes;
	}

	public void setListDocentes(ArrayList<Docente> listDocentes) {
		this.listDocentes = listDocentes;
	}

	public ArrayList<EncontroPredefinido> getListPredefinidos() {
		return listPredefinidos;
	}

	public void setListPredefinidos(ArrayList<EncontroPredefinido> listPredefinidos) {
		this.listPredefinidos = listPredefinidos;
	}

	public int getPesoEncontroPredefinido() {
		return pesoEncontroPredefinido;
	}

	public void setPesoEncontroPredefinido(int pesoEncontroPredefinido) {
		this.pesoEncontroPredefinido = pesoEncontroPredefinido;
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

	public ArrayList<Integer> calcular() {
	    ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(violacoesUnicidadeProfessor() * pesoUnicidadeDocente);
		result.add(violacoesUnicidadeClasse() * pesoUnicidadeClasse);
		result.add(violacoesPreenchimento() * pesoPreenchimentoCargaHoraria);
	    result.add(violacoesResticaoHorario() * pesoRestricaoHorarioDocente);
	    result.add(violacoesPredefinidas() * pesoEncontroPredefinido);
	    result.add(violacoesGeminacao() * pesoGeminacaoAulas);
	    result.add(violacoesLimiteDiarioAulas() * pesoLimiteDiarioAulas);
	    result.add(violacoesMinimizarDiasProfessor() * pesoDiasDocenteEscola);
	    result.add(violacoesMinimizarJanelasHorario() * pesoMinimizacaoJanela);
	    int temp = 0;
	    for(int i = 0; i < result.size(); ++i) {
	    	temp += result.get(i);
	    }
	    result.add(temp);
	    return result;
	}

	public int violacoesUnicidadeProfessor() {
		int quantia = 0;
		for(int i = 0; i < horarios.size(); ++i) {
			for(int j = i + 1; j < horarios.size(); ++j) {
				if(horarios.get(i).getHorario() == horarios.get(j).getHorario() &&
						horarios.get(i).getDia() == horarios.get(j).getDia() &&
						horarios.get(i).getDocente().equals(horarios.get(j).getDocente())) {
					++quantia;
					violacoes.add(new Violacao(1, "O professor " + horarios.get(j).getDocente().getID() + ". " + horarios.get(j).getDocente().getNome() + " leciona para duas classes no " + (horarios.get(j).getHorario() + 1) + "o horário do dia " + horarios.get(j).getDia()));
				}
			}
		}
		return quantia;
	}

	public int violacoesUnicidadeClasse() {
		int quantia = 0;
		for(int i = 0; i < horarios.size(); ++i) {
			for(int j = i + 1; j < horarios.size(); ++j) {
				if(horarios.get(i).getHorario() == horarios.get(j).getHorario() &&
						horarios.get(i).getDia() == horarios.get(j).getDia() &&
						horarios.get(i).getClasse().getCurso() == horarios.get(j).getClasse().getCurso()) {
					++quantia;
					violacoes.add(new Violacao(2, "As classes " + horarios.get(i).getClasse().getCodigo() + ". " + horarios.get(i).getClasse().getNome() + " e " + horarios.get(j).getClasse().getCodigo() + ". " + horarios.get(j).getClasse().getNome() + "ocorrem simultaneamente, no " + (horarios.get(j).getHorario() + 1) + "o horário do dia " + horarios.get(j).getDia()));
				}
			}
		}
		return quantia;
	}

	public int violacoesPreenchimento() {
		int quantia = 0;
		ArrayList<Literal> temp = getTempHorarios();
		for(int i = 0; i < temp.size(); ++i) {
			int aulas = 1;
			int cargaHoraria = temp.get(i).getClasse().getCargaHoraria();
			for(int j = i + 1; j < temp.size(); ++j) {
				if(temp.get(i).getClasse().getCodigo() == temp.get(j).getClasse().getCodigo()) {
					++aulas;
					temp.remove(j);
					--j;
				}
			}
			if(aulas != cargaHoraria) {
					++quantia;
					violacoes.add(new Violacao(3, "A classe " + temp.get(i).getClasse().getCodigo() + ". " + temp.get(i).getClasse().getNome() + " não teve a carga horária de " + temp.get(i).getClasse().getCargaHoraria() + "horas semanais atendida"));
			}			
		}
		return quantia;
	}
	
	public int violacoesResticaoHorario() {
		int quantia = 0;
		for(int i = 0; i < listDocentes.size(); ++i) {
			for(int j = 0; j < listDocentes.get(i).getRestricoes().size(); ++j) {
				for(int k = 0; k < horarios.size(); ++k) {
					if(horarios.get(k).getDocente().equals(listDocentes.get(i)) &&
							horarios.get(k).getDia() == listDocentes.get(i).getRestricoes().get(j).getDiaSemana() &&
							horarios.get(k).getHorario() == listDocentes.get(i).getRestricoes().get(j).getTipo()) {
						++quantia;
						violacoes.add(new Violacao(4, "O professor " + horarios.get(k).getDocente().getSiape() + ". " + horarios.get(k).getDocente().getNome() + " leciona para a classe " + horarios.get(k).getClasse().getCodigo() + ". " + horarios.get(k).getClasse().getNome() + " em horário em que está indisponível no " +  + (horarios.get(k).getHorario() + 1) + "o horário do dia " + horarios.get(k).getDia()));
					}
				}
			}
		}
		return quantia;
	}
	
	public int violacoesGeminacao() {
		int quantia = 0;
		ArrayList<Literal> temp = getTempHorarios();
		for(int i = 0; i < temp.size(); ++i) {
			ArrayList<Literal> aulasClasse = new ArrayList<Literal>();
			int numAulasGeminadas = 0;
			aulasClasse.add(temp.get(i));
			for(int j = i + 1; j < temp.size(); ++j) {
				if(temp.get(i).getClasse().equals(temp.get(j).getClasse())) {
					aulasClasse.add(temp.get(j));
					temp.remove(j);
					--j;
				}
			}
			for(int k = 0; k < aulasClasse.size(); ++k) {
				for(int l = k + 1; l < aulasClasse.size(); ++l) {
					if(aulasClasse.get(k).getDia() == aulasClasse.get(l).getDia() &&
							(aulasClasse.get(k).getHorario() == aulasClasse.get(l).getHorario() - 1 || aulasClasse.get(k).getHorario() == aulasClasse.get(l).getHorario() + 1)) {
						++numAulasGeminadas;
						aulasClasse.remove(l);
						--l;
					}
				}	
				aulasClasse.remove(k);
				--k;
			}
			if(numAulasGeminadas < temp.get(i).getClasse().getMinAulasGeminadas()) {
				quantia += temp.get(i).getClasse().getMinAulasGeminadas() - numAulasGeminadas;
				violacoes.add(new Violacao(6, "A classe " + temp.get(i).getClasse().getCodigo() + ". " + temp.get(i).getClasse().getNome() + " não teve o número requerido de aulas geminadas no " + (temp.get(i).getHorario() + 1) + " horário do dia " + temp.get(i).getDia()));
			}
		}
		return quantia;
	}
	
	public int violacoesLimiteDiarioAulas() {
		int quantia = 0;
		ArrayList<Literal> temp = getTempHorarios();
		for(int i = 0; i < temp.size(); ++i) {
			int count = 1;
			for(int j = i + 1; j < temp.size(); ++j) {
				if(temp.get(i).getClasse().equals(temp.get(j).getClasse()) && temp.get(i).getDia() == temp.get(j).getDia()) {
					++count;
					if(count > temp.get(i).getClasse().getLimiteDiarioAulas()) {
						++quantia;
						violacoes.add(new Violacao(7, "A classe " + temp.get(j).getClasse().getCodigo() + ". " + temp.get(j).getClasse().getNome() + " excedeu seu limite diário de aulas no dia " + horarios.get(i).getDia()));
					}
					temp.remove(j);
					--j;
				}
			}
		}
		return quantia;
	}
	
	public int violacoesPredefinidas() {
		int quantia = 0;
		for(int i = 0; i < listPredefinidos.size(); ++i) {
			boolean atendido = false;
			for(int j = 0; j < horarios.size(); ++j) {
				if(listPredefinidos.get(i).getClasse() == horarios.get(j).getClasse() &&
						listPredefinidos.get(i).getRestricao().getDiaSemana() == horarios.get(j).getDia() &&
						listPredefinidos.get(i).getRestricao().getTipo() == horarios.get(j).getHorario()) {
					atendido = true;
					break;
				}
			}
			if(atendido == false) {
				++quantia;
				Literal l = new Literal(listPredefinidos.get(i).getRestricao().getDiaSemana(),
						listPredefinidos.get(i).getRestricao().getTipo(),
						0,
						listPredefinidos.get(i).getDocente(),
						listPredefinidos.get(i).getClasse());
				violacoes.add(new Violacao(5, "O encontro predefinido entre o professor " + l.getDocente().getSiape() + ". " + l.getDocente().getNome() + " e a classe " + l.getClasse().getCodigo() + ". " + l.getClasse().getNome() + " no " + (l.getHorario() + 1) + "o horário do dia " + l.getDia() + " não foi atendido"));
			}
		}
		return quantia;
	}
	
	public int violacoesMinimizarDiasProfessor() {
		int quantia = 0;
		for(int i = 0; i < listDocentes.size(); ++i) {
			int temp = getDiasProfessor(listDocentes.get(i), horarios);
			quantia += temp;
			violacoes.add(new Violacao(8, "Número de dias que " + listDocentes.get(i).getNome() + " tem que vir: " + temp));
		}
		return quantia;
	}
	
	public int violacoesMinimizarJanelasHorario() {
		int quantia = 0;
		ArrayList<ArrayList<Literal>> aulasCurso = getAulasCurso(horarios);
		
		for(int i = 0; i < aulasCurso.size(); ++i) {
			Collections.sort(aulasCurso.get(i), new Comparator<Object>(){
				public int compare(final Object m1, final Object m2){
					final Literal aux1 = (Literal) m1;
					final Literal aux2 = (Literal) m2;
					if(aux1.getDia() > aux2.getDia())
						return 1;
					else if(aux1.getDia() < aux2.getDia())
						return -1;
					else {
						if(aux1.getHorario() > aux2.getHorario())
							return 1;
						else if(aux1.getHorario() < aux2.getHorario())
							return -1;
						else {
							return 0;
						}
					}
				}
			});
			for(int j = 0; j < aulasCurso.get(i).size() - 1; ++j) {
				if(aulasCurso.get(i).get(j).getHorario() != aulasCurso.get(i).get(j + 1).getHorario() - 1 &&
						aulasCurso.get(i).get(j).getDia() == aulasCurso.get(i).get(j + 1).getDia()) {
					Curso c = new Curso();
					for(int k = 0; k < listCursos.size(); ++k) {
						if(listCursos.get(i).getCodigo() == aulasCurso.get(i).get(j).getClasse().getCurso()) {
							c = listCursos.get(i);
							break;
						}
					}
					violacoes.add(new Violacao(9, "Há um buraco no curso " + c.getNome() + " entre o " + aulasCurso.get(i).get(j).getHorario() + "o horário e o " + aulasCurso.get(i).get(j + 1).getHorario() + "o do dia " + aulasCurso.get(i).get(j).getDia()));
					++quantia;
				}
			}
			//System.out.println(aulasCurso.get(i));
		}
		return quantia;
	}
	
	public ArrayList<ArrayList<Literal>> getAulasCurso(ArrayList<Literal> horarios) {
		ArrayList<ArrayList<Literal>> aulasCurso = new ArrayList<ArrayList<Literal>>();
		for(int i = 0; i < horarios.size(); ++i) {
			boolean contem = false;
			for(int j = 0; j < aulasCurso.size(); ++j) {
				if(aulasCurso.get(j).get(0).getClasse().getCurso() == horarios.get(i).getClasse().getCurso()) {
					aulasCurso.get(j).add(horarios.get(i));
					contem = true;
				}
			}
			if(contem == false) {
				ArrayList<Literal> curso = new ArrayList<Literal>();
				curso.add(horarios.get(i));
				aulasCurso.add(curso);
			}
		}
		return aulasCurso;
	}
	
	public int getDiasProfessor(Docente d, ArrayList<Literal> horarios) {
		ArrayList<Integer> dias = new ArrayList<Integer>();
		for(int i = 0; i < horarios.size(); ++i) {
			if(horarios.get(i).getDocente().equals(d) && ! dias.contains(horarios.get(i).getDia())) {
				dias.add(horarios.get(i).getDia());
			}
		}
		System.out.println(dias);
		return dias.size();
	}
	
	public ArrayList<Literal> getTempHorarios() {
		ArrayList<Literal> temp = new ArrayList<Literal>();
		for(int i = 0; i < horarios.size(); ++i) {
			temp.add(horarios.get(i));
		}
		return temp;
	}

}