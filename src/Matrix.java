import java.util.ArrayList;
import java.util.LinkedList;
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

	public Matrix<T> subMatrix(List<String> subColumnNames,
			List<String> subRowNames) {
		List<List<T>> data = new ArrayList<List<T>>();
		for (String row : subRowNames) {
			List<T> originRow = getRow(rowNames.indexOf(row));
			List<T> targetRow = new LinkedList<T>();
			for (String col : subColumnNames) {
				targetRow.add(originRow.get(columnNames.indexOf(col)));
			}
			data.add(targetRow);
		}
		return new Matrix<T>(data, subColumnNames, subRowNames);

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
