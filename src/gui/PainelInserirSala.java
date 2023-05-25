package gui;

import javax.swing.*;

import java.net.URL;


import org.prevayler.Prevayler;
import org.prevayler.TransactionWithQuery;

import de.preclipse.bo.*;



public class PainelInserirSala extends JPanel {

	private static final long serialVersionUID = 1L;
	public PainelInserirSala(Prevayler prevayler) {
		this.prevayler = prevayler;
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		labelTitulo = new javax.swing.JLabel();
		labelCodigo = new javax.swing.JLabel();
		labelNome = new javax.swing.JLabel();
		fieldCodigo = new javax.swing.JTextField();
		fieldNome = new javax.swing.JTextField();
		labelCapacidade = new javax.swing.JLabel();

		String pathFigure = "/gui/imagens/ajuda.png";
		URL url = this.getClass().getResource(pathFigure);
		Icon icone = new ImageIcon(url);
		buttonHelp = new JButton("",icone);
		buttonHelp.setContentAreaFilled(false);
		buttonHelp.setBorderPainted(false);

		buttonInserir = new javax.swing.JButton();
		buttonLimpar = new javax.swing.JButton();
		labelLogo = new javax.swing.JLabel();
		fieldCapacidade = new javax.swing.JTextField();

		jPanel1.setPreferredSize(new java.awt.Dimension(531, 544));

		labelTitulo.setFont(new java.awt.Font("Dialog", 0, 24));
		labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelTitulo.setText("Inserir Sala");

		labelCodigo.setText("Codigo");

		labelNome.setText("Nome");

		labelCapacidade.setText("Capacidade");

		buttonHelp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonHelpActionPerformed(evt);
			}
		});

		buttonInserir.setText("Inserir");
		buttonInserir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonInserirActionPerformed(evt);
			}
		});

		buttonLimpar.setText("Limpar");
		buttonLimpar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonLimparActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
										.addComponent(labelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
										.addGap(18, 18, 18)
										.addComponent(buttonHelp))
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addGap(204, 204, 204)
												.addComponent(labelTitulo))
												.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
														.addGap(170, 170, 170)
														.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addGroup(jPanel1Layout.createSequentialGroup()
																		.addComponent(labelCapacidade)
																		.addGap(18, 18, 18)
																		.addComponent(fieldCapacidade, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
																		.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
																				.addGap(29, 29, 29)
																				.addComponent(labelNome)
																				.addGap(18, 18, 18)
																				.addComponent(fieldNome, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
																				.addGroup(jPanel1Layout.createSequentialGroup()
																						.addGap(23, 23, 23)
																						.addComponent(labelCodigo)
																						.addGap(18, 18, 18)
																						.addComponent(fieldCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))))
																						.addGroup(jPanel1Layout.createSequentialGroup()
																								.addGap(160, 160, 160)
																								.addComponent(buttonInserir)
																								.addGap(18, 18, 18)
																								.addComponent(buttonLimpar)))
																								.addContainerGap())
		);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(labelTitulo)
						.addGap(62, 62, 62)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(fieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelCodigo))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(fieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(labelNome))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(labelCapacidade)
												.addComponent(fieldCapacidade, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(62, 62, 62)
												.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(buttonInserir)
														.addComponent(buttonLimpar))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
														.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(labelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addContainerGap())
		);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		fieldCodigo.setText("" + ((PrevalentSystem)prevayler.prevalentSystem()).getNextSalaID());
		fieldCodigo.setEditable(false);
	}// </editor-fold>

	private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {
		JanelaAjuda ajuda = new JanelaAjuda("Ajuda - Opera��o Inserir Sala", 
        		"-------------------------------------------------------------------------------------\n" +
        		"                              Opera��o Inserir Sala                                  \n" +
        		"-------------------------------------------------------------------------------------\n" +
        		"1. O n�mero do campo c�digo ser� o identificador da sala.                            \n" +
        		"2. Insira o nome do sala. � recomend�vel que o nome n�o seja repetido.               \n" +
        		"3. Insira a capacidade (em alunos) da sala.                                          \n" +
        		"-------------------------------------------------------------------------------------\n" +
        		"Obs.: O campo capacidade deve ser preenchido com um valor inteiro n�o-negativo.      \n" +
        		"-------------------------------------------------------------------------------------\n");
        ajuda.setVisible(true);
	}

	private void buttonLimparActionPerformed(java.awt.event.ActionEvent evt) {
		fieldCodigo.setText("" + ((PrevalentSystem)prevayler.prevalentSystem()).getNextSalaID());
		fieldNome.setText("");
		fieldCapacidade.setText("");
	}

	private void buttonInserirActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			if(validaEntrada()) {
				Sala temp = (Sala) prevayler.execute(new SalaConsultaPorCodigoTransaction(Integer.parseInt(fieldCodigo.getText().trim())));
				if(temp == null) {
					prevayler.execute(makeCreateSalaTransaction());
					informationMessage("Sala cadastrada com sucesso.");
					buttonLimparActionPerformed(evt);
				}
				else {
					JOptionPane.showMessageDialog(PainelInserirSala.this, "J� existe sala com esse c�digo!", "Aten��o", JOptionPane.WARNING_MESSAGE);
					buttonLimparActionPerformed(evt);
				}
			}
		}
		catch(NumberFormatException exception) {
			errorMessage("Deve ser informado um valor inteiro para a capacidade.");
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}


	// Variables declaration - do not modify
	private javax.swing.JButton buttonHelp;
	private javax.swing.JButton buttonInserir;
	private javax.swing.JButton buttonLimpar;
	private javax.swing.JTextField fieldCapacidade;
	private javax.swing.JTextField fieldCodigo;
	private javax.swing.JTextField fieldNome;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JLabel labelCapacidade;
	private javax.swing.JLabel labelCodigo;
	private javax.swing.JLabel labelLogo;
	private javax.swing.JLabel labelNome;
	private javax.swing.JLabel labelTitulo;
	// End of variables declaration

	private Prevayler prevayler;

	private boolean validaEntrada() throws NumberFormatException {
		if(Integer.parseInt(fieldCapacidade.getText().trim()) < 0) {
			errorMessage("A capacidade n�o pode ser negativa.");
			return false;
		}
		return true;
	}

	private TransactionWithQuery makeCreateSalaTransaction() throws Exception {
		return (TransactionWithQuery) new SalaCreateTransaction(Integer.parseInt(fieldCodigo.getText().trim()),
				fieldNome.getText().trim(),
				Integer.parseInt(fieldCapacidade.getText().trim()));
	}

	private void informationMessage(String s) {
		message(s, "Ok!", JOptionPane.INFORMATION_MESSAGE);
	}

	private void message(String s, String title, int type) {
		JOptionPane.showMessageDialog(PainelInserirSala.this, s, title, type);
	}

	private void errorMessage(String s) {
		message(s, "Erro!", JOptionPane.ERROR_MESSAGE);
	}

}