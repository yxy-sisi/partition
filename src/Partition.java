import java.util.List;

public class Partition {

	public static class Result {
		int z;
		List<List<String>> partitions;
	}

	public List<List<String>> partition(Matrix<Integer> affMatrix,
			Matrix<Integer> accMatrix) {

		for (int cut = 0; cut < affMatrix.getRows(); cut++) {

		}
		// TODO
		return null;
	}

	public Result partition(int cut, Matrix<Integer> affMatrix,
			Matrix<Integer> accMatrix) {
		if (cut == 1) {
			Result maxResult = null;
			for (int i = 1; i < affMatrix.getRows(); i++) {
				Result result = calculateMaxZ(affMatrix, accMatrix);
				if (maxResult == null || result.z > maxResult.z) {
					maxResult = result;
				}
				affMatrix.shift();
			}
			return maxResult;
		} else {
			for (int i = 1; i < affMatrix.getRows(); i++) {
				Result maxResult = null;
				for (int j = 0; j < affMatrix.getRows() - 1; j++) {
					
				}
			}
		}
		return null;

	}

	private Result calculateMaxZ(Matrix<Integer> affMatrix,
			Matrix<Integer> accMatrix) {
		// TODO Auto-generated method stub
		return null;
	}
}
