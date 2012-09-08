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

	/**
	 * shift the first clomun and row to last
	 */
	public void shift() {
		swapColumn(0, columnNames.size() - 1);
		swapRow(0, rowNames.size() - 1);
	}
}
