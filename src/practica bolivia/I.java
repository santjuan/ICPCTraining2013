import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class I 
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

	static int modulo(int a, long b, int c) 
	{
	    long x=1;
	    long y=a;
	    while(b > 0)
	    {
	        if((b & 1) == 1)
	            x=(x * y) % c;
	        y = (y * y) % c;
	        b >>= 1;
	    }
	    return (int) x % c;
	}

	static final int MOD = 1000000007;
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		for(int i = 0; i < t; i++)
		{
			long n = sc.nextLong();
			if(n <= 3)
				System.out.println(n);
			else if((n % 3) == 1)
			{
				n -= 4;
				long result = modulo(3, n / 3, MOD);
				result *= 4;
				result %= MOD;
				System.out.println(result);
			}
			else if((n % 3) == 2)
			{
				n -= 2;
				long result = modulo(3, n / 3, MOD);
				result *= 2;
				result %= MOD;
				System.out.println(result);
			}
			else
				System.out.println(modulo(3, n / 3, MOD));
		}
	}
}