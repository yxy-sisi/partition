import java.util.ArrayList;
import java.util.List;

public class Matrix<T> {
	private List<List<T>> data;

	public List<String> columnNames;
	public List<String> rowNames;

	public Matrix(List<List<T>> data, List<String> columnNames,
			List<String> rowNames) {
		this.data = data;
		this.columnNames = columnNames;
		this.rowNames = rowNames;
	}

	public List<T> getRow(int row) {
		return data.get(row);
	}

	public int getRows() {
		return rowNames.size();
	}

	public int getColumns() {
		return columnNames.size();
	}

	public void set(int row, int column, T value) {
		this.data.get(row).set(column, value);
	}

	public T get(int row, int column) {
		return this.data.get(row).get(column);
	}

	/**
	 * swap data of row i, and j
	 * 
	 * @param i
	 * @param j
	 */
	public void swapRow(int i, int j) {
		// swap entire row
		List<T> temp = data.get(i);
		data.set(i, data.get(j));
		data.set(j, temp);
		// swap row name
		String tempRowName = rowNames.get(i);
		rowNames.set(i, rowNames.get(j));
		rowNames.set(j, tempRowName);
	}

	/**
	 * swap data of cloumn i, and j
	 * 
	 * @param i
	 * @param j
	 */
	public void swapColumn(int i, int j) {
		// swap the column row by row
		for (int row = 0; row < rowNames.size(); row++) {
			T temp = get(row, i);
			set(row, i, get(row, j));
			set(row, j, temp);
		}

		// swap column name
		String tempColumnName = columnNames.get(i);
		columnNames.set(i, columnNames.get(j));
		columnNames.set(j, tempColumnName);
	}

	public Matrix<T> subMatrix(int row1, int col1, int row2, int col2) {
		int maxRow = row1 > row2 ? row1 : row2;
		int maxColumn = col1 > col2 ? col1 : col2;
		int minRow = row1 < row2 ? row1 : row2;
		int minColumn = col1 < col2 ? col1 : col2;
		List<String> subColumnNames = new ArrayList<String>(
				columnNames.subList(minColumn, maxColumn + 1));
		List<String> subRowNames = new ArrayList<String>(rowNames.subList(
				minRow, maxRow + 1));
		List<List<T>> subMatrixData = new ArrayList<List<T>>();
		for (int col = minColumn; col < maxColumn; col++) {
			List<T> rowData = new ArrayList<T>();
			for (int row = minRow; row <= maxRow; row++) {
				rowData.add(get(row, col));
			}
		}
		return new Matrix<T>(subMatrixData, subColumnNames, subRowNames);
	}

	/**
	 * shift the first clomun and row to last
	 */
	public void shift() {
		swapColumn(0, columnNames.size() - 1);
		swapRow(0, rowNames.size() - 1);
	}

	public void print() {
		System.out.print("\t");
		for (String colName : columnNames) {
			System.out.print(colName + "\t");
		}
		System.out.println();
		for (int row = 0; row < getRows(); row++) {
			System.out.print(rowNames.get(row) + "\t");
			for (int col = 0; col < getColumns(); col++) {
				System.out.print(get(row, col) + "\t");
			}
			System.out.println();
		}
	}
}
