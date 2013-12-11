import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class haunted {
	static class Scanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");

		public String nextLine() {
			try {
				return br.readLine();
			} catch (Exception e) {
				throw (new RuntimeException());
			}
		}

		public String next() {
			while (!st.hasMoreTokens()) {
				String l = nextLine();
				if (l == null)
					return null;
				st = new StringTokenizer(l);
			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public int[] nextIntArray(int n) {
			int[] res = new int[n];
			for (int i = 0; i < res.length; i++)
				res[i] = nextInt();
			return res;
		}

		public long[] nextLongArray(int n) {
			long[] res = new long[n];
			for (int i = 0; i < res.length; i++)
				res[i] = nextLong();
			return res;
		}

		public double[] nextDoubleArray(int n) {
			double[] res = new double[n];
			for (int i = 0; i < res.length; i++)
				res[i] = nextLong();
			return res;
		}

		public void sortIntArray(int[] array) {
			Integer[] vals = new Integer[array.length];
			for (int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for (int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}

		public void sortLongArray(long[] array) {
			Long[] vals = new Long[array.length];
			for (int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for (int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}

		public void sortDoubleArray(double[] array) {
			Double[] vals = new Double[array.length];
			for (int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for (int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}

		public String[] nextStringArray(int n) {
			String[] vals = new String[n];
			for (int i = 0; i < n; i++)
				vals[i] = next();
			return vals;
		}

		Integer nextInteger() {
			String s = next();
			if (s == null)
				return null;
			return Integer.parseInt(s);
		}

		int[][] nextIntMatrix(int n, int m) {
			int[][] ans = new int[n][];
			for (int i = 0; i < n; i++)
				ans[i] = nextIntArray(m);
			return ans;
		}
	}

	static class Node implements Comparable<Node> {
		int iC, jC, best = Integer.MAX_VALUE;
		boolean isPossible = true;
		boolean isGate = false;
		int id, jd, t;

		public Node(int i2, int j2) {
			iC = i2;
			jC = j2;
		}

		@Override
		public int compareTo(Node o) {
			if (best == o.best)
				if (iC == o.iC)
					return jC - o.jC;
				else
					return iC - o.iC;
			else
				return best - o.best;
		}
	}

	static Node[][] nodes;
	static int[][] diffs = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 },
			{ 0, -1 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner();
		while (true) {
			int w = sc.nextInt();
			int h = sc.nextInt();
			if (w == 0 && h == 0)
				return;
			nodes = new Node[h][w];
			for (int i = 0; i < h; i++)
				for (int j = 0; j < w; j++)
					nodes[i][j] = new Node(i, j);
			int g = sc.nextInt();
			for (int i = 0; i < g; i++) {
				int jj = sc.nextInt();
				int ii = sc.nextInt();
				nodes[ii][jj].isPossible = false;
			}
			int e = sc.nextInt();
			int total = 0;
			for (int i = 0; i < e; i++) {
				int jj = sc.nextInt();
				int ii = sc.nextInt();
				Node actual = nodes[ii][jj];
				actual.isGate = true;
				actual.jd = sc.nextInt();
				actual.id = sc.nextInt();
				actual.t = sc.nextInt();
				if (actual.t < 0)
					total += actual.t;
			}
			TreeSet<Node> pq = new TreeSet<Node>();
			boolean problem = false;
			nodes[0][0].best = 0;
			pq.add(nodes[0][0]);
			int mejorNum = Integer.MAX_VALUE;
			while (!pq.isEmpty()) {
				Node actual = pq.pollFirst();
				if (actual.best < total) {
					problem = true;
					break;
				}
				if (actual.iC == h - 1 && actual.jC == w - 1)
					mejorNum = Math.min(mejorNum, actual.best);
				else if (actual.isGate) {
					Node siguiente = nodes[actual.id][actual.jd];
					if (actual.best + actual.t < siguiente.best) {
						pq.remove(siguiente);
						siguiente.best = actual.best + actual.t;
						pq.add(siguiente);
					}
				} else {
					for (int[] d : diffs) {
						int iN = actual.iC + d[0];
						int jN = actual.jC + d[1];
						if (iN > -1 && iN < h && jN > -1 && jN < w
								&& nodes[iN][jN].isPossible) {
							Node siguiente = nodes[iN][jN];
							if (actual.best + 1 < siguiente.best) {
								if (siguiente.isGate) {
									pq.remove(siguiente);
									siguiente.best = actual.best + 1;
									pq.add(siguiente);
								} else {
									pq.remove(siguiente);
									siguiente.best = actual.best + 1;
									pq.add(siguiente);
								}
							}
						}
					}
				}
				if (problem)
					break;
			}
			if (problem)
				System.out.println("Never");
			else if (mejorNum == Integer.MAX_VALUE)
				System.out.println("Impossible");
			else
				System.out.println(mejorNum);
		}
	}
}