import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class ParSort {
	public static int cutoff = 200;
	public static int threads = 0;

	public static void sort(int[] arr, int from, int to) {
		int size = to - from;
		if (size <= cutoff) {
			Arrays.sort(arr, from, to); 
		}
		else {
			int mid = (to - from) / 2 + from;
			CompletableFuture<int[]> parallelSort1 = parsort(arr, from, mid);
			CompletableFuture<int[]> parallelSort2 = parsort(arr, mid, to);

			CompletableFuture<int[]> parallelSort = parallelSort1.thenCombine(parallelSort2, (xs1, xs2) -> {
				int[] combinedArray = new int[xs1.length + xs2.length];
				int i = 0;
				int j = 0;
				int k = 0;
				int l1 = xs1.length;
				int l2 = xs2.length;
				while (i < l1 && j < l2) {
					if (xs1[i] <= xs2[j]) {
						combinedArray[k] = xs1[i];
						i++;
					} else if (xs1[i] > xs2[j]) {
						combinedArray[k] = xs2[j];
						j++;
					}
					k++;
				}
				while (i < l1) {
					combinedArray[k] = xs1[i];
					k++;
					i++;
				}
				while (j < l2) {
					combinedArray[k] = xs2[j];
					k++;
					j++;
				}
				for (int p = 0; p < combinedArray.length; p++) {
					arr[p + from] = combinedArray[p];
				}
				return combinedArray;
			});

			parallelSort.whenComplete((result, throwable) -> {
				if (throwable == null) {
					parallelSort.complete(arr);
				} else {
					parallelSort.completeExceptionally(throwable);
				}
			});
			parallelSort.join();
		}
	}

	private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
		threads = threads+1;
		return CompletableFuture.supplyAsync(() -> {
			int[] result = new int[to - from];
			sort(array, from, to);
			for (int i = from; i < to; i++) {
				result[i - from] = array[i];
			}
			return result;
		});
	}
}
