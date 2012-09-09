import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		String caInputFile1 = "case/1/ca.txt";
		String refInputFile1 = "case/1/ref.txt";
		String accInputFile1 = "case/1/acc.txt";
		runTest(caInputFile1, refInputFile1, accInputFile1);
		String caInputFile2 = "case/2/ca.txt";
		String refInputFile2 = "case/2/ref.txt";
		String accInputFile2 = "case/2/acc.txt";
		runTest(caInputFile2, refInputFile2, accInputFile2);
	}

	private static void runTest(String caInputFile, String refInputFile,
			String accInputFile) throws IOException {
		Input input = new Input();
		Matrix<Integer> attifyMatrix = input.readAsMatrix(caInputFile);
		attifyMatrix.print();
		Matrix<Integer> accMatrix = input.readAsMatrix(accInputFile);
		Map<String, Integer> acc = calcuteAcc(accMatrix);
		accMatrix.print();
		Matrix<Integer> attUsageMatrix = input.readAsMatrix(refInputFile);
		attUsageMatrix.print();

		Partition p = new Partition();
		List<List<String>> partitions = p.partition(attifyMatrix,
				attUsageMatrix, acc);
		System.out.println(partitions);
	}

	private static Map<String, Integer> calcuteAcc(Matrix<Integer> accMatrix) {
		Map<String, Integer> acc = new HashMap<String, Integer>();
		for (int row = 0; row < accMatrix.getRows(); row++) {
			int accOfQuery = 0;
			for (int col = 0; col < accMatrix.getColumns(); col++) {
				accOfQuery += accMatrix.get(row, col);
			}
			acc.put(accMatrix.rowNames.get(row), accOfQuery);
		}
		return acc;
	}
}
