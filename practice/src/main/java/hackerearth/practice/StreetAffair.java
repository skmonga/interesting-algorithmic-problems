package hackerearth.practice;

import java.util.ArrayList;
import java.util.List;

public class StreetAffair {

	private static final int modulo = 1000000007;

	private static List<Pair> pairs = new ArrayList<>();

	static {
		pairs.add(new Pair(3, 2));
		Pair last = pairs.get(0);
		for (int i = 1; i < 1000000; i++) {
			int first = (last.first + ((2 * last.second) % modulo)) % modulo;
			int second = (last.first + last.second) % modulo;
			Pair new_p = new Pair(first, second);
			last = new_p;
			pairs.add(last);
		}
	}

	static class Pair {
		int first;
		int second;

		public Pair() {

		}

		public Pair(int f, int s) {
			first = f;
			second = s;
		}
	}

	public static void main(String[] args) {
		InputReader reader = new InputReader(System.in);
		int T = reader.readInt();
		for (int i = 1; i <= T; i++) {
			int N = reader.readInt();
			if (N == 1) {
				System.out.println(3);
			} else {
				Pair p = pairs.get(N - 2);
				int val = (p.first + ((2 * p.second) % modulo)) % modulo;
				System.out.println(val);
			}
		}
	}

}
