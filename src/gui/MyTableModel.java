package gui;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private boolean DEBUG = false; 
	private String[] columnNames = {"   Hor�rio   ",
			"Seg",
			"Ter",
			"Qua",
			"Qui",
			"Sex"};
	private Object[][] data = {{"1o Hor�rio - Turno 1", new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false)},
			{"2o Hor�rio - Turno 1", new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false)},
			{"3o Hor�rio - Turno 1", new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false)},
			{"4o Hor�rio - Turno 1", new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false)},
			{"5o Hor�rio - Turno 1", new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false)},
			{"1o Hor�rio - Turno 2", new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false)},
			{"2o Hor�rio - Turno 2", new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false)},
			{"3o Hor�rio - Turno 2", new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false)},
			{"4o Hor�rio - Turno 2", new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false)},
			{"5o Hor�rio - Turno 2", new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false)}};

	public final Object[] longValues = {"Sharon", Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE};

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	/*
	 * JTable uses this method to determine the default renderer/
	 * editor for each cell.  If we didn't implement this method,
	 * then the last column would contain text ("true"/"false"),
	 * rather than a check box.
	 */
	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's
	 * editable.
	 */
	public boolean isCellEditable(int row, int col) {
		//Note that the data/cell address is constant,
		//no matter where the cell appears onscreen.

		return true;

	}

	/*
	 * Don't need to implement this method unless your table's
	 * data can change.
	 */
	public void setValueAt(Object value, int row, int col) {
		if (DEBUG) {
			System.out.println("Setting value at " + row + "," + col
					+ " to " + value
					+ " (an instance of "
					+ value.getClass() + ")");
		}

		data[row][col] = value;
		fireTableCellUpdated(row, col);

		if (DEBUG) {
			System.out.println("New value of data:");
			printDebugData();
		}
	}

	private void printDebugData() {
		int numRows = getRowCount();
		int numCols = getColumnCount();

		for (int i=0; i < numRows; i++) {
			System.out.print("    row " + i + ":");
			for (int j=0; j < numCols; j++) {
				System.out.print("  " + data[i][j]);
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}
}

