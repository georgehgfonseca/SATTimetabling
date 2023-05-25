package gui;

import java.net.URL;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


import org.prevayler.Prevayler;
import org.prevayler.TransactionWithQuery;

import de.preclipse.bo.*;



public class PainelEditarDocente extends JPanel {

	private static final long serialVersionUID = 1L;

	/** Creates new form JanelaPrincipal */
	public PainelEditarDocente(Prevayler prevayler) {
		this.prevayler = prevayler;
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		labelTitulo = new javax.swing.JLabel();
		labelCodigo = new javax.swing.JLabel();
		labelNome = new javax.swing.JLabel();
		fieldCodigo = new javax.swing.JTextField();
		fieldNome = new javax.swing.JTextField();
		labelRestricoes = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tableRestricoes = new TabelaRestricoes();

		String pathFigure = "/gui/imagens/ajuda.png";
		URL url = this.getClass().getResource(pathFigure);
		Icon icone = new ImageIcon(url);
		buttonHelp = new JButton("",icone);
		buttonHelp.setContentAreaFilled(false);
		buttonHelp.setBorderPainted(false);
		
		buttonAlterar = new javax.swing.JButton();
		buttonRemover = new javax.swing.JButton();
		labelSelecione = new javax.swing.JLabel();
		jScrollPane2 = new javax.swing.JScrollPane();
		tableListDocentes = new javax.swing.JTable();
		buttonSelecionar = new javax.swing.JButton();

		labelTitulo.setFont(new java.awt.Font("Dialog", 0, 24));
		labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelTitulo.setText("Editar Docente");

		labelCodigo.setText("Codigo");

		labelNome.setText("Nome");

		labelRestricoes.setText("Restri��es de Hor�rio");

		jScrollPane1.setViewportView(tableRestricoes);

		buttonHelp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonHelpActionPerformed(evt);
			}
		});

		buttonAlterar.setText("Alterar");
		buttonAlterar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonAlterarActionPerformed(evt);
			}
		});

		buttonRemover.setText("Remover");
		buttonRemover.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonRemoverActionPerformed(evt);
			}
		});

		labelSelecione.setText("Selecione");

		tableListDocentes.setModel(new javax.swing.table.DefaultTableModel(
				new Object [][] {},
				new String [] {
						"C�digo", "Nome"
				}
		) {
			private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean [] {
					false, false
			};

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit [columnIndex];
			}
		});
		jScrollPane2.setViewportView(tableListDocentes);		

		buttonSelecionar.setText("Selecionar");
		buttonSelecionar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonSelecionarActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(jPanel1Layout.createSequentialGroup()
														.addComponent(buttonAlterar)
														.addGap(18, 18, 18)
														.addComponent(buttonRemover)
														.addGap(109, 109, 109)
														.addComponent(buttonHelp))
														.addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
																.addComponent(labelSelecione)
																.addGap(18, 18, 18)
																.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
																				.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																						.addGroup(jPanel1Layout.createSequentialGroup()
																								.addGap(10, 10, 10)
																								.addComponent(buttonSelecionar)
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE))
																								.addGroup(jPanel1Layout.createSequentialGroup()
																										.addGap(66, 66, 66)
																										.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																												.addComponent(labelNome)
																												.addComponent(labelCodigo)
																												.addComponent(labelRestricoes))
																												.addGap(18, 18, 18)))
																												.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
																														.addComponent(fieldCodigo)
																														.addComponent(fieldNome, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)))
																														.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))))
																														.addContainerGap())
																														.addGroup(jPanel1Layout.createSequentialGroup()
																																.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
																																.addContainerGap())
																																.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
																																		.addComponent(labelTitulo)
																																		.addGap(178, 178, 178))))
		);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addComponent(labelTitulo)
						.addGap(17, 17, 17)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(jPanel1Layout.createSequentialGroup()
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
														.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(fieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(labelCodigo))
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(fieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(labelNome))
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
																		.addGroup(jPanel1Layout.createSequentialGroup()
																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(buttonSelecionar)
																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
																				.addGap(18, 18, 18)
																				.addComponent(labelRestricoes))
																				.addComponent(labelSelecione))
																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																				.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																				.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(buttonAlterar)
																						.addComponent(buttonRemover))
																						.addContainerGap())
		);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 531, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(0, 0, Short.MAX_VALUE)
								.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 544, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(0, 0, Short.MAX_VALUE)
								.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)))
		);
		setTableDocentes();
		fieldCodigo.setEditable(false);
	}// </editor-fold>

	private void setTableDocentes() {
		for(int i = 0; i < ((DefaultTableModel) tableListDocentes.getModel()).getRowCount();) {
			((DefaultTableModel) tableListDocentes.getModel()).removeRow(i);
		}
		String[]lin = new String[2];
		ArrayList<Docente> docentes = ((ArrayList<Docente>)((PrevalentSystem)prevayler.prevalentSystem()).getDocente());			
		for(int i = 0; i < docentes.size(); ++i) {			
			lin[0]= "" + docentes.get(i).getSiape();
			lin[1]= "" + docentes.get(i).getNome();
			((DefaultTableModel) tableListDocentes.getModel()).addRow(lin);
		}
	}

	private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {
		JanelaAjuda ajuda = new JanelaAjuda("Ajuda - Opera��o Editar Docente", 
        		"-------------------------------------------------------------------------------------\n" +
        		"                              Opera��o Editar Docente                                \n" +
        		"-------------------------------------------------------------------------------------\n" +
        		"1. Selecione um dos registros de docente na tabela na parte superior do painel.      \n" +
        		"2. Clique no bot�o selecionar e ir�o ser exibidas as informa��es do docente.         \n" +
        		"3. Edite o nome e/ou as restri��es do docente caso deseje alterar o registro.        \n" +
        		"4. Clique no bot�o alterar ou no bot�o remover para finalizar a opera��o.            \n" +
        		"-------------------------------------------------------------------------------------\n" +
        		"Obs.: Antes de remover um docente, verifique se n�o h� alguma classe para a qual ele \n" +
        		"      leceiona. Caso haja, esta opera��o pode levar a uma inconsist�ncia nos dados.  \n" +
        		"      Marcar um n�mero excessivo de restri��es pode levar a um hor�rio imposs�vel de \n" +
        		"      gerar.                                                                         \n" +
        		"-------------------------------------------------------------------------------------\n");
        ajuda.setVisible(true);
	}

	private void buttonAlterarActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			prevayler.execute(makeDocenteChargeTransaction());
			informationMessage("Docente alterado com sucesso.");
			setTableDocentes();
			cleanScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buttonRemoverActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			prevayler.execute(makeClasseRemoveTransaction());
			informationMessage("Docente removido com sucesso.");
			setTableDocentes();
			cleanScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private void buttonSelecionarActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			Docente temp = (Docente) prevayler.execute(new DocenteConsultaPorSiapeTransaction(getSelectedCodigo()));
			if(temp == null)
				JOptionPane.showMessageDialog(PainelEditarDocente.this, "O siape informado n�o existe!", "Aten��o", JOptionPane.WARNING_MESSAGE);
			else {
				fieldCodigo.setText("" + temp.getSiape());
				fieldNome.setText(temp.getNome());
				setTabelaRestricoes(temp);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// Variables declaration - do not modify
	private javax.swing.JButton buttonAlterar;
	private javax.swing.JButton buttonHelp;
	private javax.swing.JButton buttonRemover;
	private javax.swing.JButton buttonSelecionar;
	private javax.swing.JTextField fieldCodigo;
	private javax.swing.JTextField fieldNome;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTable tableListDocentes;
	private javax.swing.JLabel labelCodigo;
	private javax.swing.JLabel labelNome;
	private javax.swing.JLabel labelRestricoes;
	private javax.swing.JLabel labelSelecione;
	private javax.swing.JLabel labelTitulo;
	private TabelaRestricoes tableRestricoes;
	// End of variables declaration

	private Prevayler prevayler;
	
	private int getSelectedCodigo() {
		return Integer.parseInt((String) tableListDocentes.getModel().getValueAt(tableListDocentes.getSelectedRow(), 0));
	}
	
	public TransactionWithQuery makeDocenteChargeTransaction() throws Exception {
		return (TransactionWithQuery) new DocenteChargeTransaction(Integer.parseInt(fieldCodigo.getText().trim()),
				fieldNome.getText().trim(),
				getRestricoesFromTable());
	}

	public TransactionWithQuery makeClasseRemoveTransaction() {
		return (TransactionWithQuery) new DocenteRemoveTransaction(Integer.parseInt(fieldCodigo.getText().trim()));
	}

	public void setTabelaRestricoes(Docente temp) {
		cleanTabelaRestricoes();
		int i = 0;
		while(i < temp.getRestricoes().size()) {
			tableRestricoes.getModel().setValueAt(true, temp.getRestricoes().get(i).getTipo(), temp.getRestricoes().get(i).getDiaSemana() - 1);
			++i;
		}
	}

	public void cleanTabelaRestricoes() {
		final int LINES = 10;
		final int COLS = 6;
		for(int i = 0; i < LINES; i++) {
			for(int j = 1; j < COLS; j++){
				tableRestricoes.getModel().setValueAt(false, i, j);
			}
		}
	}

	public void cleanScreen() {
		fieldCodigo.setText("");
		fieldNome.setText("");
		cleanTabelaRestricoes();
	}

	private ArrayList<Restricao> getRestricoesFromTable() {
		final int LINES = 10;
		final int COLS = 6;
		ArrayList<Restricao> restricoes = new ArrayList<Restricao>();
		for(int i = 0; i < LINES; i++) {
			for(int j = 1; j < COLS; j++){
				if(((Boolean) tableRestricoes.getModel().getValueAt(i, j)).booleanValue()) {
					restricoes.add(new Restricao(j + 1, i));
				}
			}
		}
		return restricoes;
	}

	private void informationMessage(String s) {
		message(s, "Ok!", JOptionPane.INFORMATION_MESSAGE);
	}

	private void message(String s, String title, int type) {
		JOptionPane.showMessageDialog(PainelEditarDocente.this, s, title, type);
	}

}