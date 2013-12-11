import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeD 
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

	public static void main(String[] args) {
		Scanner sc = new Scanner();
		String[] formas = new String[]{"ABC", "ACB", "BAC", "BCA", "CAB", "CBA"};
		String[][] pares = new String[3][2];
		for(int i = 0; i < 3; i++)
		{
			String actual = sc.next();
			if(actual.contains("<"))
			{
				pares[i][0] = actual.substring(0, 1);
				pares[i][1] = actual.substring(2, 3);
			}
			else
			{
				pares[i][1] = actual.substring(0, 1);
				pares[i][0] = actual.substring(2, 3);
			}
		}
		for(String f : formas)
		{
			boolean paila = false;
			for(int i = 0; i < 3; i++)
			{
				if(f.indexOf(pares[i][0]) > f.indexOf(pares[i][1]))
				{
					paila = true;
					break;
				}
			}
			if(!paila)
			{
				System.out.println(f);
				return;
			}
		}
		System.out.println("Impossible");
	}

}
