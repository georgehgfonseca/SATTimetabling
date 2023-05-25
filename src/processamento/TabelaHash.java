package processamento;

import java.util.HashMap;
import java.util.Map;

public class TabelaHash {

	private Map<Integer,Integer> tableFromVerticeToLiteral = new HashMap<Integer,Integer>();
	private int varIndex1 = 1;
	private Map<Integer,Integer> tableFromLiteralToVertice = new HashMap<Integer,Integer>();

	public TabelaHash() {

	}

	public int addVertice(int vertice) {
		if(! tableFromVerticeToLiteral.containsKey(Math.abs(vertice))) {
			tableFromVerticeToLiteral.put(Math.abs(vertice), varIndex1);
			varIndex1++;
		}
		int literal = vertice < 0 ? (-1) * tableFromVerticeToLiteral.get(Math.abs(vertice)) : tableFromVerticeToLiteral.get(Math.abs(vertice));
		if(! tableFromLiteralToVertice.containsKey(Math.abs(literal))) {
			tableFromLiteralToVertice.put(Math.abs(literal),vertice);
		}
		
		return literal;
		//return vertice < 0 ? (-1) * tableFromVerticeToLiteral.get(Math.abs(vertice)) : tableFromVerticeToLiteral.get(Math.abs(vertice));
	}

	public Map<Integer, Integer> getTableFromVerticeToLiteral() {
		return tableFromVerticeToLiteral;
	}

	public void setTableFromVerticeToLiteral(
			Map<Integer, Integer> tableFromVerticeToLiteral) {
		this.tableFromVerticeToLiteral = tableFromVerticeToLiteral;
	}

	public int getVarIndex1() {
		return varIndex1;
	}

	public void setVarIndex1(int varIndex1) {
		this.varIndex1 = varIndex1;
	}

	public Map<Integer, Integer> getTableFromLiteralToVertice() {
		return tableFromLiteralToVertice;
	}

	public void setTableFromLiteralToVertice(
			Map<Integer, Integer> tableFromLiteralToVertice) {
		this.tableFromLiteralToVertice = tableFromLiteralToVertice;
	}

	public Integer getVertice(int i) {
		return tableFromLiteralToVertice.get(i);
	}
	
}