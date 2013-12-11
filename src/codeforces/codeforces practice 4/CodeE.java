import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;


public class CodeE 
{
	
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
	}
	
	static class DisjointSet {
		int[] p, rank;

		public DisjointSet(int size) {
			rank = new int[size];
			p = new int[size];
			for (int i = 0; i < size; i++)
				make_set(i);
		}

		public void make_set(int x) {
			p[x] = x;
			rank[x] = 0;
		}

		public void merge(int x, int y) {
			link(find_set(x), find_set(y));
		}

		public void link(int x, int y) {
			if (rank[x] > rank[y])
				p[y] = x;
			else {
				p[x] = y;
				if (rank[x] == rank[y])
					rank[y] += 1;
			}
		}

		public int find_set(int x) {
			if (x != p[x])
				p[x] = find_set(p[x]);
			return p[x];
		}

		public int countSets(int n) 
		{
			HashSet <Integer> sets = new HashSet <Integer> ();
			for(int i = 0; i < n; i++)
				sets.add(find_set(i));
			return sets.size();
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		DisjointSet ds = new DisjointSet(n);
		for(int i = 0; i < n; i++)
		{
			int primero = i;
			int ultimo = i + k - 1;
			if(ultimo >= n)
				continue;
			while(primero <= ultimo)
			{
				ds.merge(primero, ultimo);
				primero++;
				ultimo--;
			}
		}
		int howMany = ds.countSets(n);
		System.out.println(BigInteger.valueOf(m).modPow(BigInteger.valueOf(howMany), BigInteger.valueOf(1000000007)));
	}
}
