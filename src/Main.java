import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {

		Input input = new Input();
		Matrix<Integer> attifyMatrix = input.readAsMatrix("ca.txt");
		attifyMatrix.print();
		Matrix<Integer> accMatrix = input.readAsMatrix("acc.txt");
		Map<String, Integer> acc = calcuteAcc(accMatrix);
		accMatrix.print();
		Matrix<Integer> attUsageMatrix = input.readAsMatrix("ref.txt");
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
