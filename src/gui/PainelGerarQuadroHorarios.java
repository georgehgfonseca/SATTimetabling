package gui;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.prevayler.Prevayler;
import org.sat4j.specs.TimeoutException;

import de.preclipse.bo.*;

import processamento.*;

public class PainelGerarQuadroHorarios extends javax.swing.JFrame {

	public PainelGerarQuadroHorarios() {

	}

	/** Creates new form JanelaQuadroHorarios */
	public PainelGerarQuadroHorarios(final Prevayler prevayler) {
		super("Quadro de Hor�rios");
		this.prevayler = prevayler;
		classes = ((ArrayList<Classe>)((PrevalentSystem)prevayler.prevalentSystem()).getClasse());
		docentes = ((ArrayList<Docente>)((PrevalentSystem)prevayler.prevalentSystem()).getDocente());
		encontrosPredefinidos = ((ArrayList<EncontroPredefinido>)((PrevalentSystem)prevayler.prevalentSystem()).getEncontroPredefinido());
		cursos = ((ArrayList<Curso>)((PrevalentSystem)prevayler.prevalentSystem()).getCurso());
		salas = ((ArrayList<Sala>)((PrevalentSystem)prevayler.prevalentSystem()).getSala());

		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	private void initComponents() {

		buttonGroup14 = new javax.swing.ButtonGroup();
		buttonImprimir = new javax.swing.JButton();
		buttonAvaliar = new javax.swing.JButton();
		buttonDesfazer = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane3 = new javax.swing.JScrollPane();
		avaliacaoArea = new javax.swing.JTextArea();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		tabbedPane = new javax.swing.JTabbedPane();
		radioBaseDados = new javax.swing.JRadioButton();
		radioArquivo = new javax.swing.JRadioButton();
		fieldArquivo = new javax.swing.JTextField();
		buttonGerarHorarios = new javax.swing.JButton();
		boxAlocacaoSalas = new javax.swing.JCheckBox();
		tableLegenda = new javax.swing.JTable();

		String pathFigure = "/gui/imagens/ajuda.png";
		URL url = this.getClass().getResource(pathFigure);
		Icon icone = new ImageIcon(url);
		buttonHelp = new JButton("",icone);
		buttonHelp.setContentAreaFilled(false);
		buttonHelp.setBorderPainted(false);

		buttonImprimir.setText("Imprimir");
		buttonImprimir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonImprimirActionPerformed(evt);
			}
		});

		buttonAvaliar.setText("Avaliar");
		buttonAvaliar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonAvaliarActionPerformed(evt);
			}
		});

		buttonDesfazer.setText("Desfazer");
		buttonDesfazer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonDesfazerActionPerformed(evt);
			}
		});

		avaliacaoArea.setColumns(20);
		avaliacaoArea.setRows(5);
		jScrollPane1.setViewportView(avaliacaoArea);

		jLabel1.setText("Avalia��o da Solu��o");

		jLabel2.setText("Legenda");

		jLabel3.setText("Quadro de Horarios");

		buttonGroup14.add(radioBaseDados);
		radioBaseDados.setSelected(true);
		radioBaseDados.setText("Usar Informa��es da Base de Dados");
		radioBaseDados.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				radioBaseDadosActionPerformed(evt);
			}
		});

		buttonGroup14.add(radioArquivo);
		radioArquivo.setText("Carregar informa��es do Arquivo:");
		radioArquivo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				radioArquivoActionPerformed(evt);
			}
		});

		fieldArquivo.setText("Informe o diret�rio de um arquivo .phe ou .phu");
		fieldArquivo.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				fieldArquivoMouseClicked(evt);
			}
		});
		fieldArquivo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				fieldArquivoActionPerformed(evt);
			}
		});

		buttonGerarHorarios.setText("Gerar Hor�rios");
		buttonGerarHorarios.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonGerarHorariosActionPerformed(evt);
			}
		});

		boxAlocacaoSalas.setSelected(false);
		boxAlocacaoSalas.setText("Efetuar aloca��o de salas");

		buttonHelp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonHelpActionPerformed(evt);
			}
		});

		tableLegenda.setModel(new javax.swing.table.DefaultTableModel(
				new Object [][] {
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null}
				},
				new String [] {
						"C�digo", "Nome", "Docente", "Curso"
				}
		) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unchecked")
			Class[] types = new Class [] {
				java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
			};
			boolean[] canEdit = new boolean [] {
					false, false, false, false
			};

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return types [columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit [columnIndex];
			}
		});
		jScrollPane3.setViewportView(tableLegenda);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap(260, Short.MAX_VALUE)
						.addComponent(buttonAvaliar)
						.addGap(18, 18, 18)
						.addComponent(buttonDesfazer)
						.addGap(18, 18, 18)
						.addComponent(buttonImprimir)
						.addGap(236, 236, 236))
						.addGroup(layout.createSequentialGroup()
								.addGap(312, 312, 312)
								.addComponent(buttonGerarHorarios)
								.addContainerGap(332, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
										.addContainerGap()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
												.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
														.addComponent(jLabel1)
														.addGap(313, 313, 313)
														.addComponent(jLabel2)
														.addGap(169, 169, 169))
														.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
																.addGap(18, 18, 18)
																.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGroup(layout.createSequentialGroup()
																		.addComponent(jLabel3)
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 560, Short.MAX_VALUE)
																		.addComponent(buttonHelp))
																		.addGroup(layout.createSequentialGroup()
																				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(radioArquivo)
																						.addComponent(radioBaseDados))
																						.addGap(18, 18, 18)
																						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																								.addComponent(boxAlocacaoSalas)
																								.addComponent(fieldArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE))))
																								.addContainerGap())
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(radioBaseDados)
								.addComponent(boxAlocacaoSalas))
								.addGap(3, 3, 3)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(radioArquivo)
										.addComponent(fieldArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(layout.createSequentialGroup()
														.addComponent(buttonGerarHorarios)
														.addGap(10, 10, 10)
														.addComponent(jLabel3))
														.addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(jLabel1)
																.addComponent(jLabel2))
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																		.addComponent(jScrollPane1)
																		.addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(buttonDesfazer)
																				.addComponent(buttonAvaliar)
																				.addComponent(buttonImprimir)))
		);
		avaliacaoArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		pack();
	}// </editor-fold>

	private void buttonGerarHorariosActionPerformed(java.awt.event.ActionEvent evt) {
		informationMessage("Essa opera��o pode levar alguns minutos, por favor aguarde!");
		for(int i = 0; i < tabbedPane.getTabRunCount();) {
			tabbedPane.remove(i);
		}
		if(radioArquivo.isSelected()) {
			TratamentoArquivo arquivo = new TratamentoArquivo(prevayler, fieldArquivo.getText().trim());	
			String tipo = arquivo.getTipo(fieldArquivo.getText().trim());
			Solver solver = new Solver();
			try {
				horarios = solver.solveHorarios(prevayler);
			} catch (TimeoutException e1) {
				errorMessage("O tempo limite expirou! \n" +
				"Voc� deve verificar se h� restri��es contradit�rias que podem impossibilitar a gera��o de hor�rios.");					
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if(horarios == null) {
				errorMessage("� imposs�vel criar um quadro de hor�rios com as restri��es passadas!\n" +
				"Voc� deve verificar se h� restri��es contradit�rias que podem impossibilitar a gera��o de hor�rios.");	
				return;
			}
			if(tipo.equals(".phe")) {
				try {
					tabulaHorarios2(horarios);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
			else if(tipo.equals(".phu")) {				
				try {
					locacoes = solver.solveSalas(prevayler);
				} catch (TimeoutException e) {
					errorMessage("O tempo limite expirou! \n" +
					"Voc� deve verificar se h� restri��es contradit�rias que podem impossibilitar a aloca��o de salas.");
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(locacoes == null) {
					errorMessage("� imposs�vel alocar as salas com as restri��es informadas!\n" +
					"Voc� deve verificar se h� restri��es contradit�rias que podem impossibilitar a aloca��o de salas.");	
					return;
				}
				tabulaHorarios(horarios, locacoes);	
			}
			else {
				errorMessage("Extens�o de arquivo inv�lida.");
			}
		}
		else if(radioBaseDados.isSelected()){
			Solver solver = new Solver();
			try {
				horarios = solver.solveHorarios(prevayler);
			} catch (TimeoutException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if(horarios == null) {
				errorMessage("� imposs�vel criar um quadro de hor�rios com as restri��es passadas!\n" +
				"Voc� deve verificar se h� restri��es contradit�rias que podem impossibilitar a gera��o de hor�rios.");	
				return;
			}
			if(! boxAlocacaoSalas.isSelected()) {				
				try {
					tabulaHorarios2(horarios);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {				
				try {
					locacoes = solver.solveSalas(prevayler);
				} catch (TimeoutException e) {
					errorMessage("O tempo limite expirou! \nVoc� deve verificar se h� restri��es contradit�rias que podem impossibilitar a aloca��o de salas.");
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(locacoes == null) {
					errorMessage("� imposs�vel alocar as salas com as restri��es informadas!\n" +
					"Voc� deve verificar se h� restri��es contradit�rias que podem impossibilitar a aloca��o de salas.");	
					return;
				}
				tabulaHorarios(horarios, locacoes);	
			}
		}
		setTableLegenda();
		buttonAvaliarActionPerformed(evt);
	}

	private void buttonImprimirActionPerformed(java.awt.event.ActionEvent evt) {                                              
		GeradorPDF geradorPDF = new GeradorPDF(getLiterals(horarios), prevayler);
		geradorPDF.gerarPDF();
	}                                             

	private void radioBaseDadosActionPerformed(java.awt.event.ActionEvent evt) {                                              
		// TODO add your handling code here:
	}

	private void radioArquivoActionPerformed(java.awt.event.ActionEvent evt) {                                              
		fieldArquivo.setText("");
		radioArquivo.setSelected(true);
	}

	private void fieldArquivoMouseClicked(java.awt.event.MouseEvent evt) {
		fieldArquivo.setText("");
		radioArquivo.setSelected(true);		
	}

	private void fieldArquivoActionPerformed(java.awt.event.ActionEvent evt) {
		radioArquivo.setSelected(true);
	}                                           

	private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {                                            
		JanelaAjuda ajuda = new JanelaAjuda("Ajuda - Opera��o Predefinir Encontro", 
				"-------------------------------------------------------------------------------------\n" +
				"                           Opera��o Gerar de Hor�rios                                \n" +
				"-------------------------------------------------------------------------------------\n" +
				"1. Selecione a origem dos dados a serem utilizados. Eles podem vir da base de dados  \n" +
				"   obtida pelo programa (padr�o) ou de um arquivo texto nos formatos .phe ou .phu,   \n" +
				"   cujo nome deve ser informado (inclusive a extens�o) no campo relacionado.         \n" +
				"2. A caixa efetuar aloca��o de salas habilita a aloca��o de selas caso use os dados  \n" +
				"   obtidos pelo programa. Caso selecione um arquivo, a extens�o .phu indica que o    \n" +
				"   programa deve efetuar a aloca��o de salas.                                        \n" +
				"3. Clique no bot�o Gerar Hor�rios.                                                   \n" +
				"4. Esta opera��o pode levar alguns minutos (o programa, apesar de n�o estar respon-  \n" +
				"   dendo aos comandos, est� em execu��o). Voc� pode minimiz�-lo se preferir.         \n" +
				"5. Os hor�rios aparecer�o na tabela quadro de hor�rios. O campo legenda mostra infor-\n" +
				"   ma��es relacionadas ao hor�rio. O campo avalia��o indica as falhas que o hor�rio  \n" +
				"   possui e outras informa��es importantes.                                          \n" +
				"6. Alterando os hor�rios manualmente:                                                \n" +
				"   6.1. Deve-se editar os hor�rios, trocando os c�digo em cada posi��o da tabela con-\n" +
				"        forme melhor lhe convir.                                                     \n" +
				"   6.2. Ao final da edi��o, deve-se clicar em Avaliar para obter a avalia��o dos ho- \n" +
				"        r�rios.                                                                      \n" +
				"   6.3. Caso o hor�rio n�o atenda os requisitos 1, 2 ou 3 ele � invi�vel. Pode-se    \n" +
				"        desfazer as altera��es clicando em desfazer.                                 \n" +
				"7. Clique em Imprimir para gerar um arquivo imprim�vel dos hor�rios.                 \n" +
		"-------------------------------------------------------------------------------------\n");
		ajuda.setVisible(true);
	}  

	private void buttonDesfazerActionPerformed(java.awt.event.ActionEvent evt) {
		tabbedPane.removeAll();
		tabulaHorarios(desfazerHorarios, locacoes);
		buttonAvaliarActionPerformed(evt);
	}

	private void buttonAvaliarActionPerformed(java.awt.event.ActionEvent evt) {
		desfazerHorarios = horarios;
		horarios = new ArrayList<Integer>();
		QuadroHorarios quadroHorarios;
		for(int i = 0; i < tabbedPane.getTabCount(); ++i) {
			quadroHorarios = (QuadroHorarios) tabbedPane.getComponentAt(i);
			horarios.addAll(getHorariosFromTable(quadroHorarios.getTabelaRestricoes()));
		}
		avaliacaoArea.setText(getAvaliacao(avaliar(horarios)));
	}                                   

	// Variables declaration - do not modify
	private javax.swing.JTextArea avaliacaoArea;
	private javax.swing.JCheckBox boxAlocacaoSalas;
	private javax.swing.JButton buttonAvaliar;
	private javax.swing.JButton buttonDesfazer;
	private javax.swing.JButton buttonGerarHorarios;
	private javax.swing.ButtonGroup buttonGroup14;
	private javax.swing.JButton buttonHelp;
	private javax.swing.JButton buttonImprimir;
	private javax.swing.JTextField fieldArquivo;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JRadioButton radioArquivo;
	private javax.swing.JRadioButton radioBaseDados;
	private javax.swing.JTabbedPane tabbedPane;
	private javax.swing.JTable tableLegenda;
	// End of variables declaration

	private static final long serialVersionUID = 1L;
	private Prevayler prevayler;
	private ArrayList<Integer> horarios;
	private ArrayList<LiteralSala> locacoes;
	private ArrayList<Integer> desfazerHorarios;
	private ArrayList<Classe> classes;
	private ArrayList<Docente> docentes;
	private ArrayList<Curso> cursos;
	private ArrayList<Sala> salas;
	private ArrayList<EncontroPredefinido> encontrosPredefinidos;
	public static ArrayList<Integer> resultado;

	private void setTableLegenda() {
		ArrayList<Literal> litHorarios = getLiterals(horarios);
		for(int i = 0; i < ((DefaultTableModel) tableLegenda.getModel()).getRowCount();) {
			((DefaultTableModel) tableLegenda.getModel()).removeRow(i);
		}
		String[]lin = new String[4];
		ArrayList<Integer> classesJaVerificadas = new ArrayList<Integer>();
		for(int i = 0; i < horarios.size(); ++i) {
			if(! classesJaVerificadas.contains(litHorarios.get(i).getClasse().getCodigo())) {
				classesJaVerificadas.add(litHorarios.get(i).getClasse().getCodigo());
				lin[0]= "" + litHorarios.get(i).getClasse().getCodigo();
				lin[1]= "" + litHorarios.get(i).getClasse().getNome();
				lin[2]= "" + litHorarios.get(i).getDocente().getNome();
				Curso c;
				try {
					c = (Curso) prevayler.execute(new CursoConsultaPorCodigoTransaction(litHorarios.get(i).getClasse().getCurso()));
					lin[3]= "" + c.getNome();
				} catch (Exception e) {
					e.printStackTrace();
				}		
				((DefaultTableModel) tableLegenda.getModel()).addRow(lin);
			}
		}
	}

	private ArrayList<Integer> tabulaHorarios(ArrayList<Integer> horarios, ArrayList<LiteralSala> locacoes) {
		System.out.println("Etapa 4 de 4: Carregamento das abas da interface gr�fica...");
		int curso = 0;
		ArrayList<Integer> cursos = new ArrayList<Integer>();
		Collections.sort(classes, new Comparator<Object>(){
			public int compare(final Object m1, final Object m2){
				final Classe aux1 = (Classe) m1;
				final Classe aux2 = (Classe) m2;
				if(aux1.getCurso() > aux2.getCurso())
					return 1;
				else if(aux1.getCurso() < aux2.getCurso())
					return -1;
				else {
					return 0;
				}
			}
		});		

		for(int i = 0; i < classes.size(); ++i) {
			if(classes.get(i).getCurso() != curso) {
				curso = classes.get(i).getCurso();
				if(locacoes != null) {
					LiteralSala header = getLiteralSala(curso, locacoes);
					System.out.println(curso);
					cursos.add(curso);
					JComponent panel1 = new QuadroHorarios(header.getCurso(), header.getSala(), prevayler, horarios);
					tabbedPane.addTab(header.getCurso().getNome(), panel1);
					tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
				}
				else {
					try {
					System.out.println(curso);
					cursos.add(curso);
					JComponent panel1 = new QuadroHorarios((Curso) prevayler.execute(new CursoConsultaPorCodigoTransaction(curso)), prevayler, horarios);
					tabbedPane.addTab("" + curso, panel1);
					tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return cursos;
	}

	private ArrayList<Integer> tabulaHorarios2(ArrayList<Integer> horarios) throws Exception {
		System.out.println("Etapa 4 de 4: Carregamento das abas da interface gr�fica...");
		int curso = 0;
		ArrayList<Integer> cursos = new ArrayList<Integer>();
		Collections.sort(classes, new Comparator<Object>(){
			public int compare(final Object m1, final Object m2){
				final Classe aux1 = (Classe) m1;
				final Classe aux2 = (Classe) m2;
				if(aux1.getCurso() > aux2.getCurso())
					return 1;
				else if(aux1.getCurso() < aux2.getCurso())
					return -1;
				else {
					return 0;
				}
			}
		});		
		for(int i = 0; i < classes.size(); ++i) {
			if(classes.get(i).getCurso() != curso) {
				curso = classes.get(i).getCurso();
				System.out.println(curso);
				cursos.add(curso);
				JComponent panel1 = new QuadroHorarios((Curso) prevayler.execute(new CursoConsultaPorCodigoTransaction(curso)), prevayler, horarios);
				tabbedPane.addTab("" + curso, panel1);
				tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
			}
		}
		return cursos;
	}

	public LiteralSala getLiteralSala(int curso, ArrayList<LiteralSala> locacoes) {
		for(int i = 0; i < locacoes.size(); ++i) {
			if(locacoes.get(i).getCurso().getCodigo() == curso) {
				return locacoes.get(i);
			}
		}
		return null;
	}

	public ArrayList<Violacao> avaliar(ArrayList<Integer> horarios) {
		FuncaoObjetivo fo = new FuncaoObjetivo(getLiterals(horarios), 31, 31, 31, 31, 31, 10, 5, 3, 1, classes, docentes, encontrosPredefinidos, cursos, salas);
		resultado = fo.calcular();
		return fo.getViolacoes();
	}

	private String getAvaliacao(ArrayList<Violacao> violacoes) {
		int t1 = 0;
		int t2 = 0;
		int t3 = 0;
		int t4 = 0;
		int t5 = 0;
		int t6 = 0;
		int t7 = 0;
		int t8 = 0;
		int t9 = 0;
		for(int i = 0; i < violacoes.size(); ++i) {
			if(violacoes.get(i).getTipo() == 1)
				++t1;
			else if(violacoes.get(i).getTipo() == 2)
				++t2;
			else if(violacoes.get(i).getTipo() == 3)
				++t3;
			else if(violacoes.get(i).getTipo() == 4)
				++t4;
			else if(violacoes.get(i).getTipo() == 5)
				++t5;
			else if(violacoes.get(i).getTipo() == 6)
				++t6;
			else if(violacoes.get(i).getTipo() == 7)
				++t7;
			else if(violacoes.get(i).getTipo() == 8)
				t8 += Integer.parseInt("" + violacoes.get(i).getDescricao().charAt(violacoes.get(i).getDescricao().length() - 1));
			else if(violacoes.get(i).getTipo() == 9)
				++t9;
		}
		String str = "";
		str = str + "---------------------------------------------------\n";
		str = str + "                     Requisitos                    \n";
		str = str + "---------------------------------------------------\n";
		str = str + String.format("Tipo |          Descri��o           | Peso | NViol.\n");
		str = str + String.format("  1  | Unicidade de Professor       |  *31 | %6d\n", t1);
		str = str + String.format("  2  | Unicidade de Classe          |  *31 | %6d\n", t2);
		str = str + String.format("  3  | Atend. da Carga Hor�ria      |  *31 | %6d\n", t3);
		str = str + String.format("  4  | Indisponibilidade Professor  |  *31 | %6d\n", t4);
		str = str + String.format("  5  | Enc. Predefinido nao Atend.  |  *31 | %6d\n", t5);
		str = str + String.format("  6  | Gemina��o de Aulas           |   10 | %6d\n", t6);
		str = str + String.format("  7  | Limite Di�rio de Aulas       |    5 | %6d\n", t7);
		str = str + String.format("  8  | Min. numero dias Professor   |    3 | %6d\n", t8);
		str = str + String.format("  9  | Min. Buracos em hor�rio      |    1 | %6d\n", t9);
		str = str + "---------------------------------------------------\n";
		System.out.println(resultado);
		str = str + String.format("CUSTO TOTAL:                                  %5d\n", resultado.get(9));
		str = str + "---------------------------------------------------\n";
		str = str + "                    Viola��es                      \n";

		for(int i = 0; i < violacoes.size(); ++i) {
			str = str + violacoes.get(i) + "\n";
		}
		str = str + "---------------------------------------------------\n";
		return str;
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

	private ArrayList<Integer> getHorariosFromTable(TabelaRestricoes tabelaRestricoes) {
		final int LINES = 10;
		final int COLS = 6;
		ArrayList<Integer> horariosTabela = new ArrayList<Integer>();
		for(int i = 0; i < LINES; i++) {
			for(int j = 1; j < COLS; j++){
				if(tabelaRestricoes.getModel().getValueAt(i, j) != null) {
					if(! tabelaRestricoes.getModel().getValueAt(i, j).equals("")) {
						Literal l = new Literal();
						try {
							Classe classe = (Classe) prevayler.execute(new ClasseConsultaPorCodigoTransaction(Integer.parseInt(tabelaRestricoes.getModel().getValueAt(i, j).toString())));
							Docente docente = (Docente) prevayler.execute(new DocenteConsultaPorSiapeTransaction(Integer.parseInt(classe.getSiapeDocente())));
							horariosTabela.add(l.calculaId(j + 1, i, 0, docente, classe));
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return horariosTabela;
	}

	private void errorMessage(String s) {
		message(s, "Erro!", JOptionPane.ERROR_MESSAGE);
	}

	private void informationMessage(String s) {
		message(s, "Ok!", JOptionPane.INFORMATION_MESSAGE);
	}

	private void message(String s, String title, int type) {
		JOptionPane.showMessageDialog(PainelGerarQuadroHorarios.this, s, title, type);
	}

}

