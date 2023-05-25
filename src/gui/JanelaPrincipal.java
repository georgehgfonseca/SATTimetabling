package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;

import javax.swing.*;

import org.prevayler.Prevayler;

public class JanelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private PainelInserirClasse painelInserirClasse;
	private PainelEditarClasse painelEditarClasse;
	private PainelInserirDocente painelInserirDocente;
	private PainelEditarDocente painelEditarDocente;
	private PainelInserirCurso painelInserirCurso;
	private PainelEditarCurso painelEditarCurso;
	private PainelInserirSala painelInserirSala;
	private PainelEditarSala painelEditarSala;
	private PainelEncontroPredefinido painelEncontroPredefinido;
	private PainelGerarQuadroHorarios painelGerarQuadroHorarios;


	private JMenuBar barraMenu;
	private JMenu menuClasse;
	private JMenu menuDocente;
	private JMenu menuCurso;
	private JMenu menuSala;
	private JMenu menuHorarios;
	private JMenu menuAjuda;
	private JMenuItem menuInserirClasse;
	private JMenuItem menuEditarClasse;
	private JMenuItem menuInserirDocente;
	private JMenuItem menuEditarDocente;
	private JMenuItem menuInserirCurso;
	private JMenuItem menuEditarCurso;
	private JMenuItem menuInserirSala;
	private JMenuItem menuEditarSala;
	private JMenuItem menuPredefinirEncontro;
	private JMenuItem menuGerarQuadroHorarios;
	private JMenuItem menuSobre;
	private JMenuItem menuAjuda2;
	
	private JButton botaoInserirClasse;
	private JButton botaoEditarClasse;
	private JButton botaoInserirDocente;
	private JButton botaoEditarDocente;
	private JButton botaoInserirCurso;
	private JButton botaoEditarCurso;
	private JButton botaoInserirSala;
	private JButton botaoEditarSala;
	private JButton botaoPredefinirEncontro;
	private JButton botaoGerarQuadroHorarios;
	
	private JPanel painelLogo;

	public JanelaPrincipal(final Prevayler prevayler, String titulo) {
		super(titulo);
		
		painelInserirClasse = new PainelInserirClasse(prevayler);
		painelEditarClasse = new PainelEditarClasse(prevayler);
		painelInserirDocente = new PainelInserirDocente(prevayler);
		painelEditarDocente = new PainelEditarDocente(prevayler);
		painelInserirCurso = new PainelInserirCurso(prevayler);
		painelEditarCurso = new PainelEditarCurso(prevayler);
		painelInserirSala = new PainelInserirSala(prevayler);
		painelEditarSala = new PainelEditarSala(prevayler);
		painelEncontroPredefinido = new PainelEncontroPredefinido(prevayler);
		painelGerarQuadroHorarios = new PainelGerarQuadroHorarios();
		
		barraMenu = new JMenuBar();
		menuDocente = new JMenu("Docentes");
		menuCurso = new JMenu("Cursos");
		menuClasse = new JMenu("Classes");
		menuSala = new JMenu("Salas");
		menuHorarios = new JMenu("Horários");
		menuAjuda = new JMenu("Ajuda");
		menuInserirClasse = new JMenuItem("Inserir Classe");
		menuEditarClasse = new JMenuItem("Editar Classe");
		menuInserirDocente = new JMenuItem("Inserir Docente");
		menuEditarDocente = new JMenuItem("Editar Docente");
		menuInserirCurso = new JMenuItem("Inserir Curso");
		menuEditarCurso = new JMenuItem("Editar Curso");
		menuInserirSala = new JMenuItem("Inserir Sala");
		menuEditarSala = new JMenuItem("Editar Sala");
		menuPredefinirEncontro = new JMenuItem("Predefinir Encontros");
		menuGerarQuadroHorarios = new JMenuItem("Gerar Quadro de Horários");
		menuSobre = new JMenuItem("Sobre");
		menuAjuda2 = new JMenuItem("Ajuda");

		menuClasse.add(menuInserirClasse);
		menuClasse.add(menuEditarClasse);
		menuCurso.add(menuInserirCurso);
		menuCurso.add(menuEditarCurso);
		menuDocente.add(menuInserirDocente);
		menuDocente.add(menuEditarDocente);
		menuHorarios.add(menuPredefinirEncontro);
		menuHorarios.add(menuGerarQuadroHorarios);
		menuSala.add(menuInserirSala);
		menuSala.add(menuEditarSala);
		menuAjuda.add(menuSobre);
		menuAjuda.add(menuAjuda2);
		barraMenu.add(menuDocente);
		barraMenu.add(menuCurso);
		barraMenu.add(menuClasse);
		barraMenu.add(menuSala);
		barraMenu.add(menuHorarios);
		barraMenu.add(menuAjuda);

		setJMenuBar(barraMenu);

		JPanel painel = new JPanel(new GridLayout(5, 2));
		JPanel aux = new JPanel();

		BorderLayout layoutBotoes = new BorderLayout();
		setLayout(layoutBotoes);

		String pathFigure1 = "/gui/imagens/addClasse.png";
		URL url1 = this.getClass().getResource(pathFigure1);
		Icon icone1 = new ImageIcon(url1);
		botaoInserirClasse = new JButton("",icone1);
		botaoInserirClasse.setContentAreaFilled(false);
		botaoInserirClasse.setBorderPainted(false);

		String pathFigure2 = "/gui/imagens/editClasse.png";
		URL url2 = this.getClass().getResource(pathFigure2);
		Icon icone2 = new ImageIcon(url2);
		botaoEditarClasse = new JButton("",icone2);
		botaoEditarClasse.setContentAreaFilled(false);
		botaoEditarClasse.setBorderPainted(false);

		String pathFigure4 = "/gui/imagens/addDocente.png";
		URL url4 = this.getClass().getResource(pathFigure4);
		Icon icone4 = new ImageIcon(url4);
		botaoInserirDocente = new JButton("",icone4);
		botaoInserirDocente.setContentAreaFilled(false);
		botaoInserirDocente.setBorderPainted(false);

		String pathFigure5 = "/gui/imagens/editDocente.png";
		URL url5 = this.getClass().getResource(pathFigure5);
		Icon icone5 = new ImageIcon(url5);
		botaoEditarDocente = new JButton("",icone5);
		botaoEditarDocente.setContentAreaFilled(false);
		botaoEditarDocente.setBorderPainted(false);
		
		String pathFigure7 = "/gui/imagens/addCurso.png";
		URL url7 = this.getClass().getResource(pathFigure7);
		Icon icone7 = new ImageIcon(url7);
		botaoInserirCurso = new JButton("",icone7);
		botaoInserirCurso.setContentAreaFilled(false);
		botaoInserirCurso.setBorderPainted(false);

		String pathFigure8 = "/gui/imagens/editCurso.png";
		URL url8 = this.getClass().getResource(pathFigure8);
		Icon icone8 = new ImageIcon(url8);
		botaoEditarCurso = new JButton("",icone8);
		botaoEditarCurso.setContentAreaFilled(false);
		botaoEditarCurso.setBorderPainted(false);
		
		String pathFigure10 = "/gui/imagens/addSala.png";
		URL url10 = this.getClass().getResource(pathFigure10);
		Icon icone10 = new ImageIcon(url10);
		botaoInserirSala = new JButton("",icone10);
		botaoInserirSala.setContentAreaFilled(false);
		botaoInserirSala.setBorderPainted(false);

		String pathFigure11 = "/gui/imagens/editSala.png";
		URL url11 = this.getClass().getResource(pathFigure11);
		Icon icone11 = new ImageIcon(url11);
		botaoEditarSala = new JButton("",icone11);
		botaoEditarSala.setContentAreaFilled(false);
		botaoEditarSala.setBorderPainted(false);
		
		String pathFigure13 = "/gui/imagens/predefineMeeting.png";
		URL url13 = this.getClass().getResource(pathFigure13);
		Icon icone13 = new ImageIcon(url13);
		botaoPredefinirEncontro = new JButton("",icone13);
		botaoPredefinirEncontro.setContentAreaFilled(false);
		botaoPredefinirEncontro.setBorderPainted(false);
		
		String pathFigure14 = "/gui/imagens/getTimetabling.png";
		URL url14 = this.getClass().getResource(pathFigure14);
		Icon icone14 = new ImageIcon(url14);
		botaoGerarQuadroHorarios = new JButton("",icone14);
		botaoGerarQuadroHorarios.setContentAreaFilled(false);
		botaoGerarQuadroHorarios.setBorderPainted(false);

		painel.add(botaoInserirDocente);
		painel.add(botaoEditarDocente);
		painel.add(botaoInserirCurso);
		painel.add(botaoEditarCurso);
		painel.add(botaoInserirClasse);
		painel.add(botaoEditarClasse);
		painel.add(botaoInserirSala);
		painel.add(botaoEditarSala);
		painel.add(botaoPredefinirEncontro);
		painel.add(botaoGerarQuadroHorarios);

		String pathFigure16 = "/gui/imagens/logo.png";
		URL url16 = this.getClass().getResource(pathFigure16);
		Icon logoIcon = new ImageIcon(url16);
		JLabel logoLabel = new JLabel(logoIcon);		

		painelLogo = new JPanel(new BorderLayout());
		painelLogo.add(logoLabel, BorderLayout.WEST);

		aux.add(painel);

		add(aux, BorderLayout.WEST);
		add(painelLogo, BorderLayout.EAST);

		setSize(862,610);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		//Tratamento de eventos
		menuInserirClasse.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelInserirClasse = new PainelInserirClasse(prevayler);
						add(painelInserirClasse);
						validate();
						repaint();
					}
				}
		);

		menuEditarClasse.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelEditarClasse = new PainelEditarClasse(prevayler);
						add(painelEditarClasse);
						validate();
						repaint();
					}
				}
		);

		menuInserirDocente.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelInserirDocente = new PainelInserirDocente(prevayler);
						add(painelInserirDocente);
						validate();
						repaint();
					}
				}
		);

		menuEditarDocente.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelEditarDocente = new PainelEditarDocente(prevayler);
						add(painelEditarDocente);
						validate();
						repaint();
					}
				}
		);
		
		menuInserirSala.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelInserirSala = new PainelInserirSala(prevayler);
						add(painelInserirSala);
						validate();
						repaint();
					}
				}
		);
		
		menuEditarSala.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelEditarSala = new PainelEditarSala(prevayler);
						add(painelEditarSala);
						validate();
						repaint();
					}
				}
		);
		
		menuInserirCurso.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelInserirCurso = new PainelInserirCurso(prevayler);
						add(painelInserirCurso);
						validate();
						repaint();
					}
				}
		);
		
		menuEditarCurso.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelEditarCurso = new PainelEditarCurso(prevayler);
						add(painelEditarCurso);
						validate();
						repaint();
					}
				}
		);

		menuPredefinirEncontro.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelEncontroPredefinido = new PainelEncontroPredefinido(prevayler);
						add(painelEncontroPredefinido);
						validate();
						repaint();
					}
				}
		);

		menuGerarQuadroHorarios.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelGerarQuadroHorarios = new PainelGerarQuadroHorarios(prevayler);
						painelGerarQuadroHorarios.setVisible(true);
						validate();
						repaint();
					}
				}
		);
		
		menuAjuda2.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						exibeAjuda();
						validate();
						repaint();
					}					
				}
		);
		
		menuSobre.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						exibeSobre();
						validate();
						repaint();
					}					
				}
		);
		
		botaoInserirClasse.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelInserirClasse = new PainelInserirClasse(prevayler);
						add(painelInserirClasse);
						validate();
						repaint();
					}
				}
		);

		botaoEditarClasse.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelEditarClasse = new PainelEditarClasse(prevayler);
						add(painelEditarClasse);
						validate();
						repaint();
					}
				}
		);

		botaoInserirDocente.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelInserirDocente = new PainelInserirDocente(prevayler);
						add(painelInserirDocente);
						validate();
						repaint();
					}
				}
		);

		botaoEditarDocente.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelEditarDocente = new PainelEditarDocente(prevayler);
						add(painelEditarDocente);
						validate();
						repaint();
					}
				}
		);
		
		botaoInserirSala.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelInserirSala = new PainelInserirSala(prevayler);
						add(painelInserirSala);
						validate();
						repaint();
					}
				}
		);
		
		botaoEditarSala.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelEditarSala = new PainelEditarSala(prevayler);
						add(painelEditarSala);
						validate();
						repaint();
					}
				}
		);
		
		botaoInserirCurso.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelInserirCurso = new PainelInserirCurso(prevayler);
						add(painelInserirCurso);
						validate();
						repaint();
					}
				}
		);
		
		botaoEditarCurso.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelEditarCurso = new PainelEditarCurso(prevayler);
						add(painelEditarCurso);
						validate();
						repaint();
					}
				}
		);

		botaoPredefinirEncontro.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels();
						painelEncontroPredefinido = new PainelEncontroPredefinido(prevayler);
						add(painelEncontroPredefinido);
						validate();
						repaint();
					}
				}
		);

		botaoGerarQuadroHorarios.addActionListener (
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removePanels(); 
						painelGerarQuadroHorarios = new PainelGerarQuadroHorarios(prevayler);
						painelGerarQuadroHorarios.setVisible(true);
						validate();
						repaint();
					}
				}
		);

	}	
	
	private void removePanels() {
		remove(painelInserirClasse); 
		remove(painelEditarClasse); 
		remove(painelInserirDocente); 
		remove(painelEditarDocente); 
		remove(painelInserirCurso); 
		remove(painelEditarCurso); 
		remove(painelInserirSala); 
		remove(painelEditarSala); 
		remove(painelEncontroPredefinido); 
		remove(painelGerarQuadroHorarios);
		remove(painelLogo);
	}
	
	private void exibeAjuda() {
		JanelaAjuda ajuda = new JanelaAjuda("Ajuda - Timetabler", 
        		"----------------------------------------------------------------------------------\n" +
        		"                       Bem Vindo à Ajuda do Timetabler                            \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                                     Índice                                       \n" +
        		"                                                                                  \n" +
        		"      1.  Visão Geral                                                             \n" +
        		"      2.  Montando a base de dados                                                \n" +
        		"      3.  Formato de arquivos .phe e .phu2                                        \n" +
        		"      4.  Operação Inserir Docente                                                \n" +
        		"      5.  Operação Inserir Curso                                                  \n" +
        		"      6.  Operação Inserir Classe                                                 \n" +
        		"      7.  Operação Inserir Sala                                                   \n" +
        		"      8.  Operação Editar Docente                                                 \n" +
        		"      9.  Operação Editar Curso                                                   \n" +
        		"      10. Operação Editar Classe                                                  \n" +
        		"      11. Operação Editar Sala                                                    \n" +
        		"      12. Operação Predefinir Encontros                                           \n" +
        		"      13. Operação Gerar Horários                                                 \n" +
        		"      14. Sobre o Software                                                        \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                                  1. Visão Geral                                  \n" +
        		"                                                                                  \n" +
        		"     O Timetabler é um software com o intuito de auxiliar na geração de horários  \n" +
        		" escolares. A geração manual de horários escolares é uma tarefa bastante complica-\n" +
        		" da que pode levar alguns dias de trabalho, além de que a solução obtida pode não \n" +
        		" ser satisfatória.                                                                \n" +
        		"     Outros pesquisadores desenvolveram programas de qualidade com o mesmo intuíto\n" +
        		" , entretando os programas por eles desenvolvidos são voltados para o meio cientí-\n" +
        		" fico e dificilmente poderiam ser utilizados por usuários comuns.                 \n" +
        		"     O presente programa trata o problema através de uma modelagem em fórmula da  \n" +
        		" lógica proposicional e posterior otimização através de uma busca tabu. O usuário \n" +
        		" pode também alterar os horários manualmente caso veja alguma possível melhoria.  \n" +
        		"     O software desenvolvido, além de ter um desempenho competitivo no meio cien- \n" +
        		" tífico, possui uma interface gráfica simples e prática que auxilia o usuário no  \n" +
        		" processo de geração dos horários.                                                \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                             2. Montando a Base de Dados                          \n" +
        		"                                                                                  \n" +
        		"     Primeiramente, deve-se inserir os docentes. Após, deve-se cadastrar os cursos\n" +
        		" . Posteriormente, as classes, uma vez que elas necessitam de informações de do-  \n" +
        		" centes e cursos para serem inseridas.                                            \n" +
        		"     A persistência dos dados é feita através de serialização de objetos. Para re-\n" +
        		" duzir o tamanho da base de dados basta exclir todos os arquivos da pasta data    \n" +
        		" exceto o arquivo de extensão .snapshot de maior número (nome).                   \n" +
        		"     Caso deseje reiniciar a base de dados para outro projeto, basta apagar a pas-\n" +
        		" ta data. O backup da base de dados pode ser feito copiando o conteúdo da pasta   \n" +
        		" para outro diretório qualquer.                                                   \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                          3. Formato de Arquivos .phe e .phu                      \n" +
        		"                                                                                  \n" +
        		"     A base de dados pode ser lida de arquivos .phe (Programação de Horários de   \n" +
        		" Escolas) ou .phu (Programação de Horários em Universidades) a diferença entre    \n" +
        		" eles é que apenas o segundo trata a locação de salas. Linhas em branco devem     \n" +
        		" existir apenas quando requerido pelo formato.                                    \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"     Exemplo de arquivo .phe:                                                     \n" +
        		"__________________________________________________________________________________\n" +
        		" PROFESSORES:                                                                     \n" +
        		" 1; José; 2-0; 2-1; 2-2; 2-3; 2-4                                                 \n" +
        		" 2; Maria                                                                         \n" +
        		"                                                                                  \n" +
        		" CURSOS:                                                                          \n" +
        		" 1; T01 1o ano; 1                                                                 \n" +
        		" 2; T02 2o ano; 1                                                                 \n" +
        		"                                                                                  \n" +
        		" CLASSES:                                                                         \n" +
        		" 1; Matemática; 1; 6; 3; 2; 1                                                     \n" +
        		" 2; Português; 1; 6; 3; 2; 2                                                      \n" +
        		" 3; Física; 1; 4; 2; 2; 1                                                         \n" +
        		" 4; Química; 1; 4; 2; 2; 2                                                        \n" +
        		"                                                                                  \n" +
        		" FIM.                                                                             \n" +
        		"__________________________________________________________________________________\n" +
        		"     Cada atributo de cada entidade é separado por \"; \".                        \n" +
        		"                                                                                  \n" +
        		" PROFESSORES: <código> <nome> <restrição de horário> ... <restrição de horário>   \n" +
        		"     Obs.: Cada restrição de horário vem após o nome. O primeiro número represen- \n" +
        		" ta o dia (2 = segunda) e o segundo, o horário.                                   \n" +
        		"                                                                                  \n" +
        		" CURSOS: <código> <nome> <turno>                                                  \n" +
        		"                                                                                  \n" +
        		" CLASSES: <código> <nome> <codigo_curso> <carga horária> <min aulas geminadas>    \n" +
        		"          <lim diário aulas> <codigo_docente>                                     \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"     Exemplo de arquivo .phu:                                                     \n" +
        		"__________________________________________________________________________________\n" +
        		" PROFESSORES:                                                                     \n" +
        		" 1; José; 2-0; 2-1; 2-2; 2-3; 2-4                                                 \n" +
        		" 2; Maria                                                                         \n" +
        		"                                                                                  \n" +
        		" CURSOS:                                                                          \n" +
        		" 1; T01 1o ano; 1                                                                 \n" +
        		" 2; T02 2o ano; 1                                                                 \n" +
        		"                                                                                  \n" +
        		" CLASSES:                                                                         \n" +
        		" 1; Matemática; 1; 6; 3; 2; 20; 1                                                 \n" +
        		" 2; Português; 1; 6; 3; 2; 22; 2                                                  \n" +
        		" 3; Física; 1, 4; 2; 2; 18; 1                                                     \n" +
        		" 4; Química; 1, 4; 2; 2; 14; 2                                                    \n" +
        		"                                                                                  \n" +
        		" SALAS:                                                                           \n" +
        		" 1; Sala1; 20                                                                     \n" +
        		" 2; Sala2; 22                                                                     \n" +
        		"                                                                                  \n" +
        		" FIM.                                                                             \n" +
        		"__________________________________________________________________________________\n" +
        		"     Cada atributo de cada entidade é separado por \"; \".                        \n" +
        		"                                                                                  \n" +
        		" PROFESSORES: <código> <nome> <restrição de horário> ... <restrição de horário>   \n" +
        		"     Obs.: Cada restrição de horário vem após o nome. O primeiro número represen- \n" +
        		" ta o dia (2 = segunda) e o segundo, o horário.                                   \n" +
        		"                                                                                  \n" +
        		" CURSOS: <código> <nome> <turno>                                                  \n" +
        		"                                                                                  \n" +
        		" CLASSES: <código> <nome> <codigo_curso> <carga horária> <min aulas geminadas>    \n" +
        		"          <lim diário aulas> <num_alunos> <codigo_docente>                        \n" +
        		"                                                                                  \n" +
        		" SALAS: <código> <nome> <capacidade>                                              \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                          4. Operação Inserir Docente                             \n" +
        		"----------------------------------------------------------------------------------\n" +
        		" 1. O número do campo código será o identificador do docente.                     \n" +
        		" 2. Insira o nome do docente. É recomendável que o nome não seja repetido.        \n" +
        		" 3. Marque na tabela restrições de horário os horários em que o professor está in-\n" +
        		"    disponível.                                                                   \n" +
        		"                                                                                  \n" +
        		" Obs.: Marcar um número excessivo de restrições pode levar a um horário impossível\n" +
        		"      de gerar.                                                                   \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                           5. Operação Inserir Curso                              \n" +
        		"                                                                                  \n" +
        		" 1. O número do campo código será o identificador do curso.                       \n" +
        		" 2. Insira o nome do curso. É recomendável que o nome não seja repetido.          \n" +
        		" 3. Marque o turno no qual o curso ocorre. O sistema permite apenas dois turnos.  \n" +
        		" 4. Clique no botão Inserir.                                                      \n" +
        		"                                                                                  \n" +
        		" Obs.: O turno marcado será aplicado a todas as classes pertencentes ao curso.    \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                          6. Operação Inserir Classe                              \n" +
        		"                                                                                  \n" +
        		" 1. Insira o código da classe. O código é o identificador único de cada classe.   \n" +
        		" 2. Preencha os outros dados da classe: nome, curso, carga horária, mínimo de au- \n" +
        		"    las geminadas, limite diário de aulas, número de alunos e o docente responsá- \n" +
        		"    vel.                                                                          \n" +
        		" 3. Marque o turno no qual o curso ocorre. O sistema permite apenas dois turnos.  \n" +
        		"                                                                                  \n" +
        		" Obs.: Apenas o campo nome pode ser omitido.                                      \n" +
        		" Obs.: Os campos Carga Horária, Mínimo de Aulas Geminadas, Limite Diário de Aulas \n" +
        		"       e Número de Alunos devem ser preenchidos com números inteiros não negativos\n" +
        		"       O valor Mínimo de Aulas Geminadas deve ser no máximo a metade da Carga     \n" +
        		"       Horária da classe; caso contrário, leva a um horário impossível de gerar.  \n" +
        		"       Caso o Mínimo de Aulas Geminadas seja maior que 0, o limite diário de aulas\n" +
        		"       deve ser no mínimo 2 aulas.                                                \n" +
        		"       O limite diário de aulas não pode exceder a 5 aulas.                       \n" +
        		"       Caso deseje omitir o mínimo de aulas geminadas, preencha o campo relaciona-\n" +
        		"       do com 0.                                                                  \n" +
        		"       Caso deseje omitir o lim. diário de aulas, preencha o campo relacionado    \n" +
        		"       com 5.                                                                     \n" +
        		"       O campo número de alunos é útil somente caso deseje efetuar a alocação de  \n" +
        		"       salas. Caso contrário, pode-se omití-lo inserindo o valor 0.               \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                           7. Operação Inserir Sala                               \n" +
        		"                                                                                  \n" +
        		" 1. O número do campo código será o identificador da sala.                        \n" +
        		" 2. Insira o nome do sala. É recomendável que o nome não seja repetido.           \n" +
        		" 3. Insira a capacidade (em alunos) da sala.                                      \n" +
        		"                                                                                  \n" +
        		" Obs.: O campo capacidade deve ser preenchido com um valor inteiro não-negativo.  \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                           8. Operação Editar Docente                             \n" +
        		"                                                                                  \n" +
        		" 1. Selecione um dos registros de docente na tabela na parte superior do painel.  \n" +
        		" 2. Clique no botão selecionar e irão ser exibidas as informações do docente.     \n" +
        		" 3. Edite o nome e/ou as restrições do docente caso deseje alterar o registro.    \n" +
        		" 4. Clique no botão alterar ou no botão remover para finalizar a operação.        \n" +
        		"                                                                                  \n" +
        		" Obs.: Antes de remover um docente, verifique se não há alguma classe para a qual \n" +
        		"       ele leceiona. Caso haja, esta operação pode levar a uma inconsistência nos \n" +
        		"       dados.                                                                     \n" +
        		"       Marcar um número excessivo de restrições pode levar a um horário impossível\n" +
        		"       de gerar.                                                                  \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                              9. Operação Editar Curso                            \n" +
        		"                                                                                  \n" +
        		" 1. Selecione um dos registros de curso na tabela na parte superior do painel.    \n" +
        		" 2. Clique no botão selecionar e irão ser exibidas as informações do curso.       \n" +
        		" 3. Edite o nome ou o turno do curso caso deseje alterar o registro.              \n" +
        		" 4. Clique no botão alterar ou no botão remover para finalizar a operação.        \n" +
        		"                                                                                  \n" +
        		"Obs.: Antes de remover um curso, verifique se não há alguma classe pertencente ao \n" +
        		"      mesmo.                                                                      \n" +
        		"      Ao alterar o turno, todas as classes pertencentes ao curso terão o turno    \n" +
        		"      alterado.                                                                   \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                             10. Operação Editar Classe                           \n" +
        		"                                                                                  \n" +
        		" 1. Selecione um dos registros de classe na tabela na parte superior do painel.   \n" +
        		" 2. Clique no botão selecionar e irão ser exibidas as informações da classe.      \n" +
        		" 3. Edite os campos caso deseje alterar o registro.                               \n" +
        		" 4. Clique no botão alterar ou no botão remover para finalizar a operação.        \n" +
        		"                                                                                  \n" +
        		" Obs.: Os campos Carga Horária, Mínimo de Aulas Geminadas, Limite Diário de Aulas \n" +
        		"       e Número de Alunos devem ser preenchidos com números inteiros não negativos\n" +
        		"       O valor Mínimo de Aulas Geminadas deve ser no máximo a metade da Carga     \n" +
        		"       Horária da classe; caso contrário, leva a um horário impossível de gerar.  \n" +
        		"       Caso o Mínimo de Aulas Geminadas seja maior que 0, o limite diário de aulas\n" +
        		"       deve ser no mínimo 2 aulas.                                                \n" +
        		"       O limite diário de aulas não pode exceder a 5 aulas.                       \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                              11. Operação Editar Sala                            \n" +
        		"                                                                                  \n" +
        		" 1. Selecione um dos registros de sala na tabela na parte superior do painel.     \n" +
        		" 2. Clique no botão selecionar e irão ser exibidas as informações da sala.        \n" +
        		" 3. Edite o nome e/ou a capacidade da sala, caso deseje alterar o registro.       \n" +
        		" 4. Clique no botão alterar ou no botão remover para finalizar a operação.        \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                           12. Operação Predefinir Encontro                       \n" +
        		"                                                                                  \n" +
        		" 1. Selecione o docente e a classe. A classe deve necessáriamente ter como docente\n" +
        		"    responsável o docente selecionado.                                            \n" +
        		" 2. Marque os horários a predefinir.                                              \n" +
        		" 3. Clique no botão definir.                                                      \n" +
        		"                                                                                  \n" +
        		" Obs.: Essa operação assegura que ocorrerão aulas do docente selecionado para a   \n" +
        		"       classe selecionada nos horários marcados.                                  \n" +
        		"       O número de horários marcados não deve exceder a carga horária da classe.  \n" +
        		"       Não se deve marcar encontros fora do turno do curso ao qual a classe       \n" +
        		"       pertence.                                                                  \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                           13. Operação Gerar de Horários                         \n" +
        		"                                                                                  \n" +
        		" 1. Selecione a origem dos dados a serem utilizados. Eles podem vir da base de    \n" +
        		"    ou dados obtida pelo programa (padrão) ou de um arquivo texto nos formatos    \n" +
        		"    .phe ou .phu cujo nome deve ser informado (inclusive a extensão) no campo     \n" +
        		"    relacionado.                                                                  \n" +
        		" 2. A caixa efetuar alocação de salas habilita a alocação de selas caso use os    \n" +
        		"    dados obtidos pelo programa. Caso selecione um arquivo, a extensão .phu indica\n" +
        		"    que o programa deve efetuar a alocação de salas.                              \n" +
        		" 3. Clique no botão Gerar Horários.                                               \n" +
        		" 4. Esta operação pode levar alguns minutos (o programa, apesar de não estar      \n" +
        		"    respondendo aos comandos, está em execução). Você pode minimizá-lo se preferir\n" +
        		" 5. Os horários aparecerão na tabela quadro de horários. O campo legenda mostra   \n" +
        		"    informações relacionadas ao horário. O campo avaliação indica as falhas que o \n" +
        		"    horário possui e outras informações importantes.                              \n" +
        		" 6. Alterando os horários manualmente:                                            \n" +
        		"    6.1. Deve-se editar os horários, trocando os código em cada posição da tabela \n" +
        		"         conforme melhor lhe convir.                                              \n" +
        		"    6.2. Ao final da edição, deve-se clicar em Avaliar para obter a avaliação dos \n" +
        		"         horários.                                                                \n" +
        		"    6.3. Caso o horário não atenda os requisitos 1, 2 ou 3 ele é inviável. Pode-se\n" +
        		"         desfazer as alterações clicando em desfazer.                             \n" +
        		" 7. Clique em Imprimir para gerar um arquivo imprimível dos horários.             \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                               14. Sobre o Software                               \n" +
        		"                                                                                  \n" +
        		"     O Timetabler é fruto do programa voluntário de iniciação científica (PIVIC)  \n" +
        		" dos anos 2010 e 2011 da Universidade Federal de Ouro Preto (UFOP), tendo como    \n" +
        		" autor George Henrique Godim da Fonseca (orientado), graduando em Sistemas de In- \n" +
        		" formação e como orientador, Rodrigo Geraldo Ribeiro, doutorando em Ciência da    \n" +
        		" Computação.                                                                      \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"----------------------------------------------------------------------------------\n");
        ajuda.setVisible(true);
	}
	
	private void exibeSobre() {
		JanelaAjuda ajuda = new JanelaAjuda("Sobre - Timetabler", 
        		"----------------------------------------------------------------------------------\n" +
        		"                                 Sobre o Software                                 \n" +
        		"                                                                                  \n" +
        		"     O Timetabler é fruto do programa voluntário de iniciação científica (PIVIC)  \n" +
        		" dos anos 2010 e 2011 da Universidade Federal de Ouro Preto (UFOP), tendo como    \n" +
        		" autor George Henrique Godim da Fonseca (orientado), graduando em Sistemas de In- \n" +
        		" formação e como orientador, Rodrigo Geraldo Ribeiro, doutorando em Ciência da    \n" +
        		" Computação.                                                                      \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"----------------------------------------------------------------------------------\n");
        ajuda.setVisible(true);
	}
}