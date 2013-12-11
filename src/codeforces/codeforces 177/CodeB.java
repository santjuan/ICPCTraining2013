import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeB 
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
	
	static boolean intentar(int inicial, int[] actuales, int destino)
	{
		boolean[] visitados = new boolean[actuales.length];
		if(inicial == destino)
			return true;
		int actual = actuales[inicial];
		while(actual != destino && !visitados[actual])
		{
			visitados[actual] = true;
			actual = actuales[actual];
		}
		return actual == destino;
	}
	static long generar(int indice, int[] actuales)
	{
		if(indice == actuales.length)
		{
			
			for(int i = 0; i < actuales.length; i++)
				if(!intentar(i, actuales, 0))
					return 0;
			return 1;
		}
		else
		{
			long ans = 0;
			for(int i = 0; i < actuales.length; i++)
			{
					actuales[indice] = i;
					ans += generar(indice + 1, actuales);
			}
			return ans;
		}
	}
	
	static long[] vals = new long[]
	{
		1,
		2,
		9,
		64,
		625,
		7776,
		117649,
		2097152,
	};
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int k = sc.nextInt();
		long izq = vals[k - 1];
		izq %= 1000000007;
		long der = BigInteger.valueOf(n - k).modPow(BigInteger.valueOf(n - k), BigInteger.valueOf(1000000007)).longValue();
		System.out.println((izq * der) % 1000000007);
	}

}
