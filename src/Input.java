import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Input {

	public Matrix<Integer> readAsMatrix(String file) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)));
		List<List<Integer>> matrixData = new LinkedList<List<Integer>>();
		String line = br.readLine(); // read by line
		List<String> rowNames = new LinkedList<String>();
		List<String> columnNames = parserColumnNames(line);
		line = readNextNonEmptyLine(br);
		do {
			List<Integer> row = parseRow(line, rowNames);
			if (!row.isEmpty())
				matrixData.add(row);
			line = readNextNonEmptyLine(br);
		} while (line != null);
		Matrix<Integer> matrix = new Matrix<Integer>(matrixData, columnNames,
				rowNames);
		br.close();
		return matrix;
	}

	String readNextNonEmptyLine(BufferedReader br) throws IOException {
		String line = br.readLine();
		while (line != null && line.trim().isEmpty()) {
			line = br.readLine();
		}
		return line;

	}

	private List<String> parserColumnNames(String line) {
		String[] columns = line.split("\\s+");
		List<String> columnNames = new LinkedList<String>();
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
			} else if (cell != null && !cell.trim().isEmpty()) {
				rowNames.add(cell);
			}
		}
		return row;
	}

}
