package processamento;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import org.prevayler.Prevayler;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

import de.preclipse.bo.Curso;
import de.preclipse.bo.CursoConsultaPorCodigoTransaction;

public class GeradorPDF {

	private ArrayList<Literal> horarios;
	private Prevayler prevayler;

	public GeradorPDF(ArrayList<Literal> horarios, Prevayler prevayler) {
		this.horarios = horarios;
		this.prevayler = prevayler;
		Collections.sort(horarios, new Comparator<Object>(){
			public int compare(final Object m1, final Object m2){
				final Literal aux1 = (Literal) m1;
				final Literal aux2 = (Literal) m2;
				if(aux1.getClasse().getCurso() > aux2.getClasse().getCurso())
					return 1;
				else if(aux1.getClasse().getCurso() < aux2.getClasse().getCurso())
					return -1;
				else {
					if(aux1.getHorario() > aux2.getHorario())
						return 1;
					else if(aux1.getHorario() < aux2.getHorario())
						return -1;
					else {
						if(aux1.getDia() > aux2.getDia())
							return 1;
						else if(aux1.getDia() < aux2.getDia())
							return -1;
						else
							return 0;
					}
				}
			}
		});
	}

	public void gerarPDF(){
		Document doc = null;
		FileOutputStream os = null;	 
		try {   	
			//cria o documento tamanho A4, margens de 1,27cm
			doc = new Document(PageSize.A4.rotate(), 36, 36, 36, 36);      
			//cria a stream de saída
			os = new FileOutputStream("Horarios.pdf");	   
			//associa a stream de saída ao
			PdfWriter.getInstance(doc, os);	   
			//abre o documento
			doc.open();
			//adiciona o texto ao PDF
			writeFile(doc);
			//Abre o PFD no Desktop
			File pdf = new File("Horarios.pdf");  

			Desktop.getDesktop().open(pdf);  



		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		catch(Exception ex) {  
			ex.printStackTrace();  
			JOptionPane.showMessageDialog(null, "Erro no Desktop: " + ex);  
		} 

		finally {
			if (doc != null) {
				//fechamento do documento
				doc.close();
			}
			if (os != null) {
				//fechamento da stream de saída
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void writeFile(Document doc) throws Exception {
		ArrayList<Integer> cursos = obtemNumeroCursos(horarios);
		for(int i = 0; i < cursos.size(); ++i) {
			ArrayList<Literal> aulasCurso = getAulasCurso(horarios, cursos.get(i));
			writePage(doc, aulasCurso);
		}
	}

	private void writePage(Document doc, ArrayList<Literal> aulasCurso)
			throws Exception {
		Font f = new Font(FontFamily.TIMES_ROMAN, 16, Font.BOLD);
		//Font f2 = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD);
		Font f3 = new Font(FontFamily.COURIER, 7, Font.NORMAL);
		//Font f4 = new Font(FontFamily.TIMES_ROMAN, 1, Font.BOLD);
		
		Curso curso = (Curso) prevayler.execute(new CursoConsultaPorCodigoTransaction(aulasCurso.get(0).getClasse().getCurso()));
		Paragraph titulo = new Paragraph(curso.getNome() + "\n", f);
		Paragraph p1 = new Paragraph("+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n",f3);
		Paragraph p2 = new Paragraph("| HORARIO |           SEGUNDA-FEIRA         |            TERÇA-FEIRA          |           QUARTA-FEIRA          |           QUINTA-FEIRA          |            SEXTA-FEIRA          |\n",f3);
		Paragraph p3 = new Paragraph("| H1 - T1 | " + getCelula(2, 0, aulasCurso) + " | " + getCelula(3, 0, aulasCurso) + " | " + getCelula(4, 0, aulasCurso) + " | " + getCelula(5, 0, aulasCurso) + " | " + getCelula(6, 0, aulasCurso) + " |\n",f3);
		Paragraph p4 = new Paragraph("| H2 - T1 | " + getCelula(2, 1, aulasCurso) + " | " + getCelula(3, 1, aulasCurso) + " | " + getCelula(4, 1, aulasCurso) + " | " + getCelula(5, 1, aulasCurso) + " | " + getCelula(6, 1, aulasCurso) + " |\n",f3);
		Paragraph p5 = new Paragraph("| H3 - T1 | " + getCelula(2, 2, aulasCurso) + " | " + getCelula(3, 2, aulasCurso) + " | " + getCelula(4, 2, aulasCurso) + " | " + getCelula(5, 2, aulasCurso) + " | " + getCelula(6, 2, aulasCurso) + " |\n",f3);
		Paragraph p6 = new Paragraph("| H4 - T1 | " + getCelula(2, 3, aulasCurso) + " | " + getCelula(3, 3, aulasCurso) + " | " + getCelula(4, 3, aulasCurso) + " | " + getCelula(5, 3, aulasCurso) + " | " + getCelula(6, 3, aulasCurso) + " |\n",f3);
		Paragraph p7 = new Paragraph("| H5 - T1 | " + getCelula(2, 4, aulasCurso) + " | " + getCelula(3, 4, aulasCurso) + " | " + getCelula(4, 4, aulasCurso) + " | " + getCelula(5, 4, aulasCurso) + " | " + getCelula(6, 4, aulasCurso) + " |\n",f3);
		Paragraph p8 = new Paragraph("| H1 - T2 | " + getCelula(2, 5, aulasCurso) + " | " + getCelula(3, 5, aulasCurso) + " | " + getCelula(4, 5, aulasCurso) + " | " + getCelula(5, 5, aulasCurso) + " | " + getCelula(6, 5, aulasCurso) + " |\n",f3);
		Paragraph p9 = new Paragraph("| H2 - T2 | " + getCelula(2, 6, aulasCurso) + " | " + getCelula(3, 6, aulasCurso) + " | " + getCelula(4, 6, aulasCurso) + " | " + getCelula(5, 6, aulasCurso) + " | " + getCelula(6, 6, aulasCurso) + " |\n",f3);
		Paragraph p10 = new Paragraph("| H3 - T2 | " + getCelula(2, 7, aulasCurso) + " | " + getCelula(3, 7, aulasCurso) + " | " + getCelula(4, 7, aulasCurso) + " | " + getCelula(5, 7, aulasCurso) + " | " + getCelula(6, 7, aulasCurso) + " |\n",f3);
		Paragraph p11 = new Paragraph("| H4 - T2 | " + getCelula(2, 8, aulasCurso) + " | " + getCelula(3, 8, aulasCurso) + " | " + getCelula(4, 8, aulasCurso) + " | " + getCelula(5, 8, aulasCurso) + " | " + getCelula(6, 8, aulasCurso) + " |\n",f3);
		Paragraph p12 = new Paragraph("| H5 - T2 | " + getCelula(2, 9, aulasCurso) + " | " + getCelula(3, 9, aulasCurso) + " | " + getCelula(4, 9, aulasCurso) + " | " + getCelula(5, 9, aulasCurso) + " | " + getCelula(6, 9, aulasCurso) + " |\n",f3);
		Paragraph pQuebra = new Paragraph("\n\n\n",f3);
		Paragraph p13 = new Paragraph("+--- LEGENDA -----------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n",f3);
		Paragraph p14 = new Paragraph("| CÓDIGO |                                     NOME DA CLASSE                                     |                      PROFESSOR                   | CARGA HORARIA | N. DE ALUNOS |\n",f3);
		
		titulo.setAlignment(Element.ALIGN_CENTER);

		doc.add(titulo);
		doc.add(p1);
		doc.add(p2);
		doc.add(p1);
		doc.add(p3);
		doc.add(p1);
		doc.add(p4);
		doc.add(p1);
		doc.add(p5);
		doc.add(p1);
		doc.add(p6);
		doc.add(p1);
		doc.add(p7);
		doc.add(p1);
		doc.add(p8);
		doc.add(p1);
		doc.add(p9);
		doc.add(p1);
		doc.add(p10);
		doc.add(p1);
		doc.add(p11);
		doc.add(p1);
		doc.add(p12);
		doc.add(p1);
		doc.add(pQuebra);
		doc.add(p13);
		doc.add(p14);
		doc.add(p1);

		ArrayList<Integer> codClasses = new ArrayList<Integer>();
		for(int i = 0; i < aulasCurso.size(); ++i) {
			if(! codClasses.contains(aulasCurso.get(i).getClasse().getCodigo())) {
				codClasses.add(aulasCurso.get(i).getClasse().getCodigo());
				String classeStr = aulasCurso.get(i).getClasse().getNome();
				if(classeStr.length() > 86)
					classeStr = classeStr.substring(0, 86);
				String docenteStr = aulasCurso.get(i).getDocente().getNome();
				if(docenteStr.length() > 48)
					docenteStr = docenteStr.substring(0, 48);
				String str = String.format("| %-6d | %-86s | %-48s | %13d | %12d |\n", 
						aulasCurso.get(i).getClasse().getCodigo(), classeStr, docenteStr, 
						aulasCurso.get(i).getClasse().getCargaHoraria(), aulasCurso.get(i).getClasse().getNumAlunos());
				doc.add(new Paragraph(str,f3));
			}
		}
		doc.add(p1);
		doc.newPage();
	}
	
	public String getCelula(int dia, int horario, ArrayList<Literal> aulasCurso) {
		for(int i = 0; i < aulasCurso.size(); ++i) {
			if(aulasCurso.get(i).getDia() == dia && aulasCurso.get(i).getHorario() == horario) {
				if(aulasCurso.get(i).getClasse().getNome().length() > 31)
					return String.format("%-31s", aulasCurso.get(i).getClasse().getNome().substring(0, 28).concat("..."));
				else
					return String.format("%-31s", aulasCurso.get(i).getClasse().getNome());
			}
		}
		return "                               ";
	}
	
	public ArrayList<Integer> obtemNumeroCursos(ArrayList<Literal> horarios) {
		int count = 0;
		ArrayList<Integer> cursos = new ArrayList<Integer>();
		for(int i = 0; i < horarios.size(); ++i) {
			if(! cursos.contains(horarios.get(i).getClasse().getCurso())) {
				cursos.add(horarios.get(i).getClasse().getCurso());
				++count;
			}
		}
		return cursos;
	}
	
	public ArrayList<Literal> getAulasCurso(ArrayList<Literal> horarios, int curso) {
		ArrayList<Literal> aulasCurso = new ArrayList<Literal>();
		for(int i = 0; i < horarios.size(); ++i) {
			if(horarios.get(i).getClasse().getCurso() == curso)
				aulasCurso.add(horarios.get(i));
		}
		return aulasCurso;
	}

}
