package processamento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.prevayler.Prevayler;
import org.prevayler.TransactionWithQuery;

import de.preclipse.bo.ClasseCreateTransaction;
import de.preclipse.bo.CursoCreateTransaction;
import de.preclipse.bo.DocenteCreateTransaction;
import de.preclipse.bo.Restricao;
import de.preclipse.bo.SalaCreateTransaction;

public class TratamentoArquivo {

	private File file;
	private static Prevayler prevayler;

	@SuppressWarnings("static-access")
	public TratamentoArquivo(Prevayler prevayler, String nome) {
		this.prevayler = prevayler;
		solveFile(nome);
	}

	public String getTipo(String nome) {
		return nome.substring(nome.lastIndexOf("."));
	}

	public void solveFile(String nome) {
		this.file = new File(nome);
		if(file.exists()) {
			FileReader reader;
			try {
				String tipo = getTipo(nome);
				reader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(reader);
				readFile(bufferedReader, tipo);
				bufferedReader.close();
				reader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else {
			System.out.println("Arquivo inexistente.");
		}
	}

	private static void readFile(BufferedReader bufferedReader, String tipo) throws NumberFormatException, Exception {
		String line = null;
		int counter = 0;
		while((line = bufferedReader.readLine()) != null) {
			if(line.contains("PROFESSORES:")) {
				while(! ((line = bufferedReader.readLine())).equals("")) {
					readProfessor(line);
					++counter;
				}
			}
			else if(line.contains("CURSOS:")) {
				while(! ((line = bufferedReader.readLine())).equals("")) {
					readCurso(line);
					++counter;
				}
			}
			else if(line.contains("CLASSES:")) {
				while(! ((line = bufferedReader.readLine())).equals("")) {
					if(tipo.equals(".phu"))
						readClasse(line);
					else if(tipo.equals(".phe"))
						readClasse2(line);
					++counter;
				}
			}
			else if(line.contains("SALAS:")) {
				while(! ((line = bufferedReader.readLine())).equals("")) {
					readSala(line);
					++counter;
				}
			}
			else if(line.contains("FIM.")) {
				break;
			}
			++counter;
		}
	}

	private static void readCurso(String line) throws NumberFormatException, Exception {
		String[] cursoLine = line.split("; ");
		prevayler.execute((TransactionWithQuery) new CursoCreateTransaction(Integer.parseInt(cursoLine[0]),
				cursoLine[1],
				Integer.parseInt(cursoLine[2])));
	}

	private static void readClasse(String line) throws NumberFormatException, Exception {
		String[] classeLine = line.split("; ");
		prevayler.execute((TransactionWithQuery) new ClasseCreateTransaction(Integer.parseInt(classeLine[0]),
				classeLine[1],
				Integer.parseInt(classeLine[2]),
				Integer.parseInt(classeLine[3]),
				Integer.parseInt(classeLine[4]),
				Integer.parseInt(classeLine[5]),
				Integer.parseInt(classeLine[6]),
				classeLine[7],
				new ArrayList<Restricao>()));
	}

	private static void readClasse2(String line) throws NumberFormatException, Exception {
		String[] classeLine = line.split("; ");
		prevayler.execute((TransactionWithQuery) new ClasseCreateTransaction(Integer.parseInt(classeLine[0]),
				classeLine[1],
				Integer.parseInt(classeLine[2]),
				Integer.parseInt(classeLine[3]),
				Integer.parseInt(classeLine[4]),
				Integer.parseInt(classeLine[5]),
				0,
				classeLine[6],
				new ArrayList<Restricao>()));
	}

	private static void readSala(String line) throws NumberFormatException, Exception {
		String[] salaLine = line.split("; ");
		prevayler.execute((TransactionWithQuery) new SalaCreateTransaction(Integer.parseInt(salaLine[0]),
				salaLine[1],
				Integer.parseInt(salaLine[2])));
	}

	private static void readProfessor(String line) throws NumberFormatException, Exception {
		String[] professorLine = line.split("; ");
		ArrayList<Restricao> restricoes = new ArrayList<Restricao>();
		for(int i = 2; i < professorLine.length; ++i) {
			if(professorLine[i].charAt(2) == '*') {
				for(int j = 0; j < 10; ++j)
					restricoes.add(new Restricao(Integer.parseInt("" + professorLine[i].charAt(0)), j));
			}
			else
				restricoes.add(new Restricao(Integer.parseInt("" + professorLine[i].charAt(0)), Integer.parseInt("" + professorLine[i].charAt(2))));
		}
		prevayler.execute((TransactionWithQuery) new DocenteCreateTransaction(Integer.parseInt(professorLine[0]),
				professorLine[1],
				restricoes));
	}

}
