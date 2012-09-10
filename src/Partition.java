import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Partition {

	public static class Result {
		int z;
		List<List<String>> partitions;
	}

	public Result partition(Matrix<Integer> affMatrix,
			Matrix<Integer> attUsageMatrix, Map<String, Integer> acc) {
		Result max = null;
		for (int i = 1; i < affMatrix.getRows(); i++) {
			Result result = calculateMaxZ(affMatrix, attUsageMatrix, acc);
			if (max == null || max.z < result.z) {
				max = result;
			}
			affMatrix.shift();
		}
		return max;
	}

	public List<List<String>> partitionN(Matrix<Integer> affMatrix,
			Matrix<Integer> attUsageMatrix, Map<String, Integer> acc,
			int fragement) {
		List<List<String>> parts = new ArrayList<List<String>>();
		Result res = null;
		for (int i = 0; i < fragement; i++) {
			res = partition(affMatrix, attUsageMatrix, acc);
			List<String> subColumnNames = res.partitions.get(1);
			parts.add(res.partitions.get(0));

			affMatrix = affMatrix.subMatrix(subColumnNames, subColumnNames);
			List<String> queryRelatedToSubMatrix = getRelatedQuerys(
					attUsageMatrix, subColumnNames);
			attUsageMatrix = attUsageMatrix.subMatrix(subColumnNames,
					queryRelatedToSubMatrix);
		}
		if (res != null)
			parts.add(res.partitions.get(1));
		return parts;

	}

	private List<String> getRelatedQuerys(Matrix<Integer> attUsageMatrix,
			List<String> subColumnNames) {
		List<String> queryRelatedToSubMatrix = new LinkedList<String>();
		for (int j = 0; j < attUsageMatrix.getRows(); j++) {
			List<String> relatedAttributes = attribeReferedByQuery(
					attUsageMatrix, j);
			for (String attr : relatedAttributes) {
				if (subColumnNames.contains(attr)) {
					queryRelatedToSubMatrix.add(attUsageMatrix.rowNames.get(j));
				}
			}
		}
		return queryRelatedToSubMatrix;
	}

	private Result calculateMaxZ(Matrix<Integer> affMatrix,
			Matrix<Integer> attUsageMatrix, Map<String, Integer> acc) {
		Result max = null;
		for (int i = 1; i < affMatrix.getRows(); i++) {
			Result result = calacuteZ(affMatrix, attUsageMatrix, acc, i);
			if (max == null || max.z < result.z) {
				max = result;
			}
		}
		return max;
	}

	private Result calacuteZ(Matrix<Integer> affMatrix,
			Matrix<Integer> attUsageMatrix, Map<String, Integer> acc, int i) {
		List<String> tqAttrs = new ArrayList<String>(
				affMatrix.columnNames.subList(0, i));
		List<String> tqs = new ArrayList<String>();
		for (int j = 0; j < attUsageMatrix.getRows(); j++) {
			if (tqAttrs.containsAll(attribeReferedByQuery(attUsageMatrix, j))) {
				tqs.add(attUsageMatrix.rowNames.get(j));
			}
		}
		int ctq = 0;
		for (String tq : tqs) {
			ctq += acc.get(tq);
		}

		List<String> bqAttrs = new ArrayList<String>(
				affMatrix.columnNames.subList(i, affMatrix.getRows()));
		List<String> bqs = new ArrayList<String>();
		for (int j = 0; j < attUsageMatrix.getRows(); j++) {
			if (bqAttrs.containsAll(attribeReferedByQuery(attUsageMatrix, j)))
				bqs.add(attUsageMatrix.rowNames.get(j));
		}
		int cbq = 0;

		for (String bq : bqs) {
			cbq += acc.get(bq);
		}

		List<String> oqs = new ArrayList<String>();
		for (String q : attUsageMatrix.rowNames) {
			if (!tqs.contains(q) && !bqs.contains(q)) {
				oqs.add(q);
			}
		}

		int coq = 0;
		for (String oq : oqs) {
			coq += acc.get(oq);
		}
		int z = ctq * cbq - coq * coq;
		Result result = new Result();
		result.z = z;
		result.partitions = new ArrayList<List<String>>();
		result.partitions.add(tqAttrs);
		result.partitions.add(bqAttrs);
		return result;

	}

	private List<String> attribeReferedByQuery(Matrix<Integer> attUsageMatrix,
			int j) {
		List<String> attrRefered = new ArrayList<String>();
		for (int col = 0; col < attUsageMatrix.getColumns(); col++) {
			int isUsed = attUsageMatrix.get(j, col);
			if (isUsed == 1) {
				attrRefered.add(attUsageMatrix.columnNames.get(col));
			}
		}
		return attrRefered;

	}
}
