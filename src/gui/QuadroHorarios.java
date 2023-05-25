package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.*;


import org.prevayler.Prevayler;

import de.preclipse.bo.*;

public class QuadroHorarios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel cursoLabel;
	private JTextField cursoField;
	private JLabel salaLabel;
	private JTextField salaField;
	private JPanel painelCampos;
	
	private TabelaRestricoes tabelaRestricoes;
	
	public QuadroHorarios(Curso curso, final Prevayler prevayler, ArrayList<Integer> horarios) {
		cursoLabel = new JLabel("Curso:");
		cursoField = new JTextField(curso.getNome());
		cursoField.setEditable(false);

		tabelaRestricoes = new TabelaRestricoes();
		setTabelaRestricoes();

		painelCampos = new JPanel(new FlowLayout());
		painelCampos.add(cursoLabel);
		painelCampos.add(cursoField);
		setLayout(new BorderLayout(5, 5));
		add(painelCampos, BorderLayout.NORTH);
		add(tabelaRestricoes, BorderLayout.CENTER);
		
		try {
			for(int i = 0; i < horarios.size(); ++i) {
				if(horarios.get(i) != null) {
					int v = horarios.get(i);
					if(v < 0)
						v *= -1;
					Classe c = (Classe) prevayler.execute(new ClasseConsultaPorCodigoTransaction(v % 10000));
					int diaSemana = v / 100000000;
					int tipo = (v /10000000) % 10;
					if(c != null) {
						if(c.getCurso() == curso.getCodigo()) {
							addHorario(c.getCodigo(), diaSemana, tipo);
						}
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public QuadroHorarios(Curso curso, Sala sala, final Prevayler prevayler, ArrayList<Integer> horarios) {
		cursoLabel = new JLabel("Curso:");
		cursoField = new JTextField(curso.getNome());
		cursoField.setEditable(false);
		
		salaLabel = new JLabel("         Sala:");
		salaField = new JTextField(sala.getNome());
		salaField.setEditable(false);

		tabelaRestricoes = new TabelaRestricoes();
		setTabelaRestricoes();

		painelCampos = new JPanel(new FlowLayout());
		painelCampos.add(cursoLabel);
		painelCampos.add(cursoField);
		painelCampos.add(salaLabel);
		painelCampos.add(salaField);
		setLayout(new BorderLayout(5, 5));
		add(painelCampos, BorderLayout.NORTH);
		add(tabelaRestricoes, BorderLayout.CENTER);
		
		try {
			for(int i = 0; i < horarios.size(); ++i) {
				if(horarios.get(i) != null) {
					int v = horarios.get(i);
					if(v < 0)
						v *= -1;
					Classe c = (Classe) prevayler.execute(new ClasseConsultaPorCodigoTransaction(v % 10000));
					int diaSemana = v / 100000000;
					int tipo = (v /10000000) % 10;
					if(c != null) {
						if(c.getCurso() == curso.getCodigo()) {
							addHorario(c.getCodigo(), diaSemana, tipo);
						}
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void addHorario(int codigo, int diaSemana, int tipo) {
		tabelaRestricoes.getModel().setValueAt(codigo, tipo, diaSemana - 1);
	}

	public void setTabelaRestricoes() {
		for(int i = 0; i < 10; ++i)
			for(int j = 1; j < 6; ++j)
				tabelaRestricoes.getModel().setValueAt("", i, j);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JLabel getCursoLabel() {
		return cursoLabel;
	}

	public JTextField getCursoField() {
		return cursoField;
	}

	public JPanel getPainelCampos() {
		return painelCampos;
	}

	public TabelaRestricoes getTabelaRestricoes() {
		return tabelaRestricoes;
	}

}
