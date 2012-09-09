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
		// check rows, columns match with data
		verify();
	}

	private void verify() {
		if (rowNames.size() != data.size()) {
			throw new IllegalStateException("rows not match with data");
		}
		for (List<T> row : data) {
			if (row.size() != columnNames.size()) {
				throw new IllegalStateException("columns not match with data ");
			}
		}

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
	 * swap data of cloumn i, and j
	 * 
	 * @param i
	 * @param j
	 */
	public void shiftRow() {
		shift(data);
		shift(rowNames);
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
	 * shift the first element to last
	 * 
	 * @param list
	 */
	private <E> void shift(List<E> list) {
		E first = list.get(0);
		list.remove(0);
		list.add(first);
	}

	/**
	 * shift the first clomun and row to last
	 */
	public void shift() {
		shiftRow();
		shiftColumn();
	}

	private void shiftColumn() {
		for (List<T> row : data) {
			shift(row);
		}
		shift(columnNames);
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
