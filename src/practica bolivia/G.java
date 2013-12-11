import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class G 
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

	static Boolean[][] dp = new Boolean[1000001][12];
	static int[] factoriales = new int[11];
	static boolean posible(int n, int menor)
	{
		if(n < 0)
			return false;
		if(n == 0)
			return true;
		if(dp[n][menor] != null)
			return dp[n][menor];
		for(int actual = menor; actual < 10; actual++)
		{
			if(n - factoriales[actual] < 0)
				return dp[n][menor] = false;
			if(posible(n - factoriales[actual], actual + 1))
				return dp[n][menor] = true;
		}
		return dp[n][menor] = false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner();
		factoriales[0] = 1;
		factoriales[1] = 1;
		for(int i = 2; i <= 10; i++)
			factoriales[i] = factoriales[i - 1] * i;
		int t = sc.nextInt();
		for(int i = 0; i < t; i++)
			if(posible(sc.nextInt(), 0))
				System.out.println("YES");
			else
				System.out.println("NO");
	}

}
