package hackerearth.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Missiles {

	private static Pair[] missiles = new Pair[100000];

	private static int[] count = new int[1000001];

	static class Pair {
		public int left;
		public int right;

		public Pair() {
			// TODO Auto-generated constructor stub
		}

		public Pair(int l, int r) {
			left = l;
			right = r;
		}
	}

	public static void main(String[] args) {
		InputReader reader = new InputReader(System.in);
		int T = reader.readInt();
		int N, M, size, K, pos, range;

		Set<Integer> set = null;
		for (int i = 1; i <= T; i++) {
			Arrays.fill(count, 0);
			N = reader.readInt();
			M = reader.readInt();
			size = reader.readInt();
			K = reader.readInt();
			set = new HashSet<>();
			set.add(1);
			set.add(M);
			for (int j = 0; j < N; j++) {
				pos = reader.readInt();
				range = reader.readInt();
				if (range < 0) {
					range = -range;
					missiles[j] = new Pair(pos - range, pos);
					for (int k = pos - range; k <= pos; k++) {
						if (k >= 0 && k <= M)
							count[k] += 1;
					}
					set.add(pos - range);
				} else {
					missiles[j] = new Pair(pos, pos + range);
					for (int k = pos; k <= pos + range; k++) {
						if (k >= 0 && k <= M)
							count[k] += 1;
					}
					set.add(pos + range);
				}
				set.add(pos);
			}

			int count_val = 0;
			int availableSize = M - 2;
			if (size > availableSize) {

			} else {
				int cur_len = 0;
				int centre = (2 - size/2) > 1?2:2+size/2;
				while (centre + (size/2) < M) {
					boolean checkIfContinue = false;
					int j;
					for (j = centre - size/2 + cur_len; j <= centre + size/2; j++) {
						if (count[j] > K || set.contains(j)) {
							checkIfContinue = true;
							break;
						}
					}
					if (checkIfContinue) {
						cur_len = 0;
						centre = j + 1 + size/2;
						continue;
					} else {
						/*if (!(set.contains(centre- size/2) && set.contains(centre + size/2))) {
							count_val++;
						}*/
						count_val++;
						// found possible length
						cur_len = size - 1;
						centre++;
					}
				}
			}
			System.out.println(count_val);

		}
	}

}
