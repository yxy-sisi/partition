import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Input {

	public Matrix<Integer> readAsMatrix(String file) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)));
		List<List<Integer>> matrixData = new ArrayList<List<Integer>>();
		String line = br.readLine(); // read by line
		List<String> rowNames = new ArrayList<String>();
		List<String> columnNames = parserColumnNames(line);
		line = br.readLine();
		do {
			List<Integer> row = parseRow(line, rowNames);
			matrixData.add(row);
			line = br.readLine();
		} while (line != null);
		Matrix<Integer> matrix = new Matrix<Integer>(matrixData, columnNames,
				rowNames);
		br.close();
		return matrix;
	}

	private List<String> parserColumnNames(String line) {
		String[] columns = line.split("\\s+");
		List<String> columnNames = new ArrayList<String>();
		for (String column : columns) {
			if (column != null && !column.trim().isEmpty())
				columnNames.add(column);
		}
		return columnNames;
	}

	private List<Integer> parseRow(String line, List<String> rowNames) {
		String[] cellStrings = line.split("\\s+");
		List<Integer> row = new ArrayList<Integer>();
		for (String cell : cellStrings) {
			if (cell.matches("^\\d+$")) {
				row.add(Integer.parseInt(cell));
			}

			else if (cell != null && !cell.trim().isEmpty()) {
				rowNames.add(cell);
			}
		}
		return row;
	}
}
