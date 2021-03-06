import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class C 
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

	static int noZeroes(String s)
	{
		if(s.length() > 1 && s.charAt(0) == '0')
			s = s.substring(1);
		return Integer.parseInt(s);
	}
	static int convert(String val)
	{
		String[] ped = val.split(":");
		return noZeroes(ped[0]) * 60 + noZeroes(ped[1]);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		boolean[] busy = new boolean[1440];
		for(int i = 0; i < n; i++)
		{
			Arrays.fill(busy, false);
			int a = convert(sc.next());
			int b = convert(sc.next());
			int c = convert(sc.next());
			int d = convert(sc.next());
			for(int j = a; j <= b; j++)
				busy[j] = true;
			boolean pudo = true;
			for(int j = c; j <= d; j++)
				if(busy[j])
					pudo = false;
			System.out.println("Case " + (i + 1) + ": " + (pudo ? "Hits Meeting" : "Mrs Meeting"));
		}
	}
}