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
		menuHorarios = new JMenu("Hor�rios");
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
		menuGerarQuadroHorarios = new JMenuItem("Gerar Quadro de Hor�rios");
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
        		"                       Bem Vindo � Ajuda do Timetabler                            \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                                     �ndice                                       \n" +
        		"                                                                                  \n" +
        		"      1.  Vis�o Geral                                                             \n" +
        		"      2.  Montando a base de dados                                                \n" +
        		"      3.  Formato de arquivos .phe e .phu2                                        \n" +
        		"      4.  Opera��o Inserir Docente                                                \n" +
        		"      5.  Opera��o Inserir Curso                                                  \n" +
        		"      6.  Opera��o Inserir Classe                                                 \n" +
        		"      7.  Opera��o Inserir Sala                                                   \n" +
        		"      8.  Opera��o Editar Docente                                                 \n" +
        		"      9.  Opera��o Editar Curso                                                   \n" +
        		"      10. Opera��o Editar Classe                                                  \n" +
        		"      11. Opera��o Editar Sala                                                    \n" +
        		"      12. Opera��o Predefinir Encontros                                           \n" +
        		"      13. Opera��o Gerar Hor�rios                                                 \n" +
        		"      14. Sobre o Software                                                        \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                                  1. Vis�o Geral                                  \n" +
        		"                                                                                  \n" +
        		"     O Timetabler � um software com o intuito de auxiliar na gera��o de hor�rios  \n" +
        		" escolares. A gera��o manual de hor�rios escolares � uma tarefa bastante complica-\n" +
        		" da que pode levar alguns dias de trabalho, al�m de que a solu��o obtida pode n�o \n" +
        		" ser satisfat�ria.                                                                \n" +
        		"     Outros pesquisadores desenvolveram programas de qualidade com o mesmo intu�to\n" +
        		" , entretando os programas por eles desenvolvidos s�o voltados para o meio cient�-\n" +
        		" fico e dificilmente poderiam ser utilizados por usu�rios comuns.                 \n" +
        		"     O presente programa trata o problema atrav�s de uma modelagem em f�rmula da  \n" +
        		" l�gica proposicional e posterior otimiza��o atrav�s de uma busca tabu. O usu�rio \n" +
        		" pode tamb�m alterar os hor�rios manualmente caso veja alguma poss�vel melhoria.  \n" +
        		"     O software desenvolvido, al�m de ter um desempenho competitivo no meio cien- \n" +
        		" t�fico, possui uma interface gr�fica simples e pr�tica que auxilia o usu�rio no  \n" +
        		" processo de gera��o dos hor�rios.                                                \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                             2. Montando a Base de Dados                          \n" +
        		"                                                                                  \n" +
        		"     Primeiramente, deve-se inserir os docentes. Ap�s, deve-se cadastrar os cursos\n" +
        		" . Posteriormente, as classes, uma vez que elas necessitam de informa��es de do-  \n" +
        		" centes e cursos para serem inseridas.                                            \n" +
        		"     A persist�ncia dos dados � feita atrav�s de serializa��o de objetos. Para re-\n" +
        		" duzir o tamanho da base de dados basta exclir todos os arquivos da pasta data    \n" +
        		" exceto o arquivo de extens�o .snapshot de maior n�mero (nome).                   \n" +
        		"     Caso deseje reiniciar a base de dados para outro projeto, basta apagar a pas-\n" +
        		" ta data. O backup da base de dados pode ser feito copiando o conte�do da pasta   \n" +
        		" para outro diret�rio qualquer.                                                   \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                          3. Formato de Arquivos .phe e .phu                      \n" +
        		"                                                                                  \n" +
        		"     A base de dados pode ser lida de arquivos .phe (Programa��o de Hor�rios de   \n" +
        		" Escolas) ou .phu (Programa��o de Hor�rios em Universidades) a diferen�a entre    \n" +
        		" eles � que apenas o segundo trata a loca��o de salas. Linhas em branco devem     \n" +
        		" existir apenas quando requerido pelo formato.                                    \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"     Exemplo de arquivo .phe:                                                     \n" +
        		"__________________________________________________________________________________\n" +
        		" PROFESSORES:                                                                     \n" +
        		" 1; Jos�; 2-0; 2-1; 2-2; 2-3; 2-4                                                 \n" +
        		" 2; Maria                                                                         \n" +
        		"                                                                                  \n" +
        		" CURSOS:                                                                          \n" +
        		" 1; T01 1o ano; 1                                                                 \n" +
        		" 2; T02 2o ano; 1                                                                 \n" +
        		"                                                                                  \n" +
        		" CLASSES:                                                                         \n" +
        		" 1; Matem�tica; 1; 6; 3; 2; 1                                                     \n" +
        		" 2; Portugu�s; 1; 6; 3; 2; 2                                                      \n" +
        		" 3; F�sica; 1; 4; 2; 2; 1                                                         \n" +
        		" 4; Qu�mica; 1; 4; 2; 2; 2                                                        \n" +
        		"                                                                                  \n" +
        		" FIM.                                                                             \n" +
        		"__________________________________________________________________________________\n" +
        		"     Cada atributo de cada entidade � separado por \"; \".                        \n" +
        		"                                                                                  \n" +
        		" PROFESSORES: <c�digo> <nome> <restri��o de hor�rio> ... <restri��o de hor�rio>   \n" +
        		"     Obs.: Cada restri��o de hor�rio vem ap�s o nome. O primeiro n�mero represen- \n" +
        		" ta o dia (2 = segunda) e o segundo, o hor�rio.                                   \n" +
        		"                                                                                  \n" +
        		" CURSOS: <c�digo> <nome> <turno>                                                  \n" +
        		"                                                                                  \n" +
        		" CLASSES: <c�digo> <nome> <codigo_curso> <carga hor�ria> <min aulas geminadas>    \n" +
        		"          <lim di�rio aulas> <codigo_docente>                                     \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"     Exemplo de arquivo .phu:                                                     \n" +
        		"__________________________________________________________________________________\n" +
        		" PROFESSORES:                                                                     \n" +
        		" 1; Jos�; 2-0; 2-1; 2-2; 2-3; 2-4                                                 \n" +
        		" 2; Maria                                                                         \n" +
        		"                                                                                  \n" +
        		" CURSOS:                                                                          \n" +
        		" 1; T01 1o ano; 1                                                                 \n" +
        		" 2; T02 2o ano; 1                                                                 \n" +
        		"                                                                                  \n" +
        		" CLASSES:                                                                         \n" +
        		" 1; Matem�tica; 1; 6; 3; 2; 20; 1                                                 \n" +
        		" 2; Portugu�s; 1; 6; 3; 2; 22; 2                                                  \n" +
        		" 3; F�sica; 1, 4; 2; 2; 18; 1                                                     \n" +
        		" 4; Qu�mica; 1, 4; 2; 2; 14; 2                                                    \n" +
        		"                                                                                  \n" +
        		" SALAS:                                                                           \n" +
        		" 1; Sala1; 20                                                                     \n" +
        		" 2; Sala2; 22                                                                     \n" +
        		"                                                                                  \n" +
        		" FIM.                                                                             \n" +
        		"__________________________________________________________________________________\n" +
        		"     Cada atributo de cada entidade � separado por \"; \".                        \n" +
        		"                                                                                  \n" +
        		" PROFESSORES: <c�digo> <nome> <restri��o de hor�rio> ... <restri��o de hor�rio>   \n" +
        		"     Obs.: Cada restri��o de hor�rio vem ap�s o nome. O primeiro n�mero represen- \n" +
        		" ta o dia (2 = segunda) e o segundo, o hor�rio.                                   \n" +
        		"                                                                                  \n" +
        		" CURSOS: <c�digo> <nome> <turno>                                                  \n" +
        		"                                                                                  \n" +
        		" CLASSES: <c�digo> <nome> <codigo_curso> <carga hor�ria> <min aulas geminadas>    \n" +
        		"          <lim di�rio aulas> <num_alunos> <codigo_docente>                        \n" +
        		"                                                                                  \n" +
        		" SALAS: <c�digo> <nome> <capacidade>                                              \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                          4. Opera��o Inserir Docente                             \n" +
        		"----------------------------------------------------------------------------------\n" +
        		" 1. O n�mero do campo c�digo ser� o identificador do docente.                     \n" +
        		" 2. Insira o nome do docente. � recomend�vel que o nome n�o seja repetido.        \n" +
        		" 3. Marque na tabela restri��es de hor�rio os hor�rios em que o professor est� in-\n" +
        		"    dispon�vel.                                                                   \n" +
        		"                                                                                  \n" +
        		" Obs.: Marcar um n�mero excessivo de restri��es pode levar a um hor�rio imposs�vel\n" +
        		"      de gerar.                                                                   \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                           5. Opera��o Inserir Curso                              \n" +
        		"                                                                                  \n" +
        		" 1. O n�mero do campo c�digo ser� o identificador do curso.                       \n" +
        		" 2. Insira o nome do curso. � recomend�vel que o nome n�o seja repetido.          \n" +
        		" 3. Marque o turno no qual o curso ocorre. O sistema permite apenas dois turnos.  \n" +
        		" 4. Clique no bot�o Inserir.                                                      \n" +
        		"                                                                                  \n" +
        		" Obs.: O turno marcado ser� aplicado a todas as classes pertencentes ao curso.    \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                          6. Opera��o Inserir Classe                              \n" +
        		"                                                                                  \n" +
        		" 1. Insira o c�digo da classe. O c�digo � o identificador �nico de cada classe.   \n" +
        		" 2. Preencha os outros dados da classe: nome, curso, carga hor�ria, m�nimo de au- \n" +
        		"    las geminadas, limite di�rio de aulas, n�mero de alunos e o docente respons�- \n" +
        		"    vel.                                                                          \n" +
        		" 3. Marque o turno no qual o curso ocorre. O sistema permite apenas dois turnos.  \n" +
        		"                                                                                  \n" +
        		" Obs.: Apenas o campo nome pode ser omitido.                                      \n" +
        		" Obs.: Os campos Carga Hor�ria, M�nimo de Aulas Geminadas, Limite Di�rio de Aulas \n" +
        		"       e N�mero de Alunos devem ser preenchidos com n�meros inteiros n�o negativos\n" +
        		"       O valor M�nimo de Aulas Geminadas deve ser no m�ximo a metade da Carga     \n" +
        		"       Hor�ria da classe; caso contr�rio, leva a um hor�rio imposs�vel de gerar.  \n" +
        		"       Caso o M�nimo de Aulas Geminadas seja maior que 0, o limite di�rio de aulas\n" +
        		"       deve ser no m�nimo 2 aulas.                                                \n" +
        		"       O limite di�rio de aulas n�o pode exceder a 5 aulas.                       \n" +
        		"       Caso deseje omitir o m�nimo de aulas geminadas, preencha o campo relaciona-\n" +
        		"       do com 0.                                                                  \n" +
        		"       Caso deseje omitir o lim. di�rio de aulas, preencha o campo relacionado    \n" +
        		"       com 5.                                                                     \n" +
        		"       O campo n�mero de alunos � �til somente caso deseje efetuar a aloca��o de  \n" +
        		"       salas. Caso contr�rio, pode-se omit�-lo inserindo o valor 0.               \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                           7. Opera��o Inserir Sala                               \n" +
        		"                                                                                  \n" +
        		" 1. O n�mero do campo c�digo ser� o identificador da sala.                        \n" +
        		" 2. Insira o nome do sala. � recomend�vel que o nome n�o seja repetido.           \n" +
        		" 3. Insira a capacidade (em alunos) da sala.                                      \n" +
        		"                                                                                  \n" +
        		" Obs.: O campo capacidade deve ser preenchido com um valor inteiro n�o-negativo.  \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                           8. Opera��o Editar Docente                             \n" +
        		"                                                                                  \n" +
        		" 1. Selecione um dos registros de docente na tabela na parte superior do painel.  \n" +
        		" 2. Clique no bot�o selecionar e ir�o ser exibidas as informa��es do docente.     \n" +
        		" 3. Edite o nome e/ou as restri��es do docente caso deseje alterar o registro.    \n" +
        		" 4. Clique no bot�o alterar ou no bot�o remover para finalizar a opera��o.        \n" +
        		"                                                                                  \n" +
        		" Obs.: Antes de remover um docente, verifique se n�o h� alguma classe para a qual \n" +
        		"       ele leceiona. Caso haja, esta opera��o pode levar a uma inconsist�ncia nos \n" +
        		"       dados.                                                                     \n" +
        		"       Marcar um n�mero excessivo de restri��es pode levar a um hor�rio imposs�vel\n" +
        		"       de gerar.                                                                  \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                              9. Opera��o Editar Curso                            \n" +
        		"                                                                                  \n" +
        		" 1. Selecione um dos registros de curso na tabela na parte superior do painel.    \n" +
        		" 2. Clique no bot�o selecionar e ir�o ser exibidas as informa��es do curso.       \n" +
        		" 3. Edite o nome ou o turno do curso caso deseje alterar o registro.              \n" +
        		" 4. Clique no bot�o alterar ou no bot�o remover para finalizar a opera��o.        \n" +
        		"                                                                                  \n" +
        		"Obs.: Antes de remover um curso, verifique se n�o h� alguma classe pertencente ao \n" +
        		"      mesmo.                                                                      \n" +
        		"      Ao alterar o turno, todas as classes pertencentes ao curso ter�o o turno    \n" +
        		"      alterado.                                                                   \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                             10. Opera��o Editar Classe                           \n" +
        		"                                                                                  \n" +
        		" 1. Selecione um dos registros de classe na tabela na parte superior do painel.   \n" +
        		" 2. Clique no bot�o selecionar e ir�o ser exibidas as informa��es da classe.      \n" +
        		" 3. Edite os campos caso deseje alterar o registro.                               \n" +
        		" 4. Clique no bot�o alterar ou no bot�o remover para finalizar a opera��o.        \n" +
        		"                                                                                  \n" +
        		" Obs.: Os campos Carga Hor�ria, M�nimo de Aulas Geminadas, Limite Di�rio de Aulas \n" +
        		"       e N�mero de Alunos devem ser preenchidos com n�meros inteiros n�o negativos\n" +
        		"       O valor M�nimo de Aulas Geminadas deve ser no m�ximo a metade da Carga     \n" +
        		"       Hor�ria da classe; caso contr�rio, leva a um hor�rio imposs�vel de gerar.  \n" +
        		"       Caso o M�nimo de Aulas Geminadas seja maior que 0, o limite di�rio de aulas\n" +
        		"       deve ser no m�nimo 2 aulas.                                                \n" +
        		"       O limite di�rio de aulas n�o pode exceder a 5 aulas.                       \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                              11. Opera��o Editar Sala                            \n" +
        		"                                                                                  \n" +
        		" 1. Selecione um dos registros de sala na tabela na parte superior do painel.     \n" +
        		" 2. Clique no bot�o selecionar e ir�o ser exibidas as informa��es da sala.        \n" +
        		" 3. Edite o nome e/ou a capacidade da sala, caso deseje alterar o registro.       \n" +
        		" 4. Clique no bot�o alterar ou no bot�o remover para finalizar a opera��o.        \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                           12. Opera��o Predefinir Encontro                       \n" +
        		"                                                                                  \n" +
        		" 1. Selecione o docente e a classe. A classe deve necess�riamente ter como docente\n" +
        		"    respons�vel o docente selecionado.                                            \n" +
        		" 2. Marque os hor�rios a predefinir.                                              \n" +
        		" 3. Clique no bot�o definir.                                                      \n" +
        		"                                                                                  \n" +
        		" Obs.: Essa opera��o assegura que ocorrer�o aulas do docente selecionado para a   \n" +
        		"       classe selecionada nos hor�rios marcados.                                  \n" +
        		"       O n�mero de hor�rios marcados n�o deve exceder a carga hor�ria da classe.  \n" +
        		"       N�o se deve marcar encontros fora do turno do curso ao qual a classe       \n" +
        		"       pertence.                                                                  \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                           13. Opera��o Gerar de Hor�rios                         \n" +
        		"                                                                                  \n" +
        		" 1. Selecione a origem dos dados a serem utilizados. Eles podem vir da base de    \n" +
        		"    ou dados obtida pelo programa (padr�o) ou de um arquivo texto nos formatos    \n" +
        		"    .phe ou .phu cujo nome deve ser informado (inclusive a extens�o) no campo     \n" +
        		"    relacionado.                                                                  \n" +
        		" 2. A caixa efetuar aloca��o de salas habilita a aloca��o de selas caso use os    \n" +
        		"    dados obtidos pelo programa. Caso selecione um arquivo, a extens�o .phu indica\n" +
        		"    que o programa deve efetuar a aloca��o de salas.                              \n" +
        		" 3. Clique no bot�o Gerar Hor�rios.                                               \n" +
        		" 4. Esta opera��o pode levar alguns minutos (o programa, apesar de n�o estar      \n" +
        		"    respondendo aos comandos, est� em execu��o). Voc� pode minimiz�-lo se preferir\n" +
        		" 5. Os hor�rios aparecer�o na tabela quadro de hor�rios. O campo legenda mostra   \n" +
        		"    informa��es relacionadas ao hor�rio. O campo avalia��o indica as falhas que o \n" +
        		"    hor�rio possui e outras informa��es importantes.                              \n" +
        		" 6. Alterando os hor�rios manualmente:                                            \n" +
        		"    6.1. Deve-se editar os hor�rios, trocando os c�digo em cada posi��o da tabela \n" +
        		"         conforme melhor lhe convir.                                              \n" +
        		"    6.2. Ao final da edi��o, deve-se clicar em Avaliar para obter a avalia��o dos \n" +
        		"         hor�rios.                                                                \n" +
        		"    6.3. Caso o hor�rio n�o atenda os requisitos 1, 2 ou 3 ele � invi�vel. Pode-se\n" +
        		"         desfazer as altera��es clicando em desfazer.                             \n" +
        		" 7. Clique em Imprimir para gerar um arquivo imprim�vel dos hor�rios.             \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"                               14. Sobre o Software                               \n" +
        		"                                                                                  \n" +
        		"     O Timetabler � fruto do programa volunt�rio de inicia��o cient�fica (PIVIC)  \n" +
        		" dos anos 2010 e 2011 da Universidade Federal de Ouro Preto (UFOP), tendo como    \n" +
        		" autor George Henrique Godim da Fonseca (orientado), graduando em Sistemas de In- \n" +
        		" forma��o e como orientador, Rodrigo Geraldo Ribeiro, doutorando em Ci�ncia da    \n" +
        		" Computa��o.                                                                      \n" +
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
        		"     O Timetabler � fruto do programa volunt�rio de inicia��o cient�fica (PIVIC)  \n" +
        		" dos anos 2010 e 2011 da Universidade Federal de Ouro Preto (UFOP), tendo como    \n" +
        		" autor George Henrique Godim da Fonseca (orientado), graduando em Sistemas de In- \n" +
        		" forma��o e como orientador, Rodrigo Geraldo Ribeiro, doutorando em Ci�ncia da    \n" +
        		" Computa��o.                                                                      \n" +
        		"                                                                                  \n" +
        		"----------------------------------------------------------------------------------\n" +
        		"----------------------------------------------------------------------------------\n");
        ajuda.setVisible(true);
	}
}