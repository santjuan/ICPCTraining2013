import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeA 
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

	static String generarBruto(int indice, char anterior, char[] puestas, int k)
	{
		if(indice == puestas.length)
		{
			boolean[] distintas = new boolean[26];
			for(int i = 0; i < puestas.length; i++)
				distintas[puestas[i] - 'a'] = true;
			int cuenta = 0;
			for(boolean b : distintas)
				if(b)
					cuenta++;
			if(cuenta == k)
				return new String(puestas);
			else
				return null;
		}
		String mejor = null;
		for(char actual = 'a'; actual <= 'z'; actual++)
		{
			if(actual != anterior)
			{
				puestas[indice] = actual;
				String siguiente = generarBruto(indice + 1, actual, puestas, k);
				if(mejor == null)
					mejor = siguiente;
				if(siguiente != null)
					if(mejor.compareTo(siguiente) >= 0)
						mejor = siguiente;
			}
		}
		return mejor;
	}
	
	static String generar(int n, int k)
	{
		char[][] dp = new char[n + 2][k + 2];
		char[][] siguiente = new char[n + 2][k + 2];
		for(int i = 0; i < k; i++)
		{
			dp[n][i] = 1;
			siguiente[n][i] = 0;
		}
		for(int indice = n - 1; indice >= 1; indice--)
		{
			for(int anterior = 0; anterior < k; anterior++)
			{
				boolean ok = false;
				for(int i = 0; i < k; i++)
				{
					if(i != anterior && dp[indice + 1][i] == 1)
					{
						dp[indice][anterior] = 1;
						siguiente[indice][anterior] = (char) i;
						ok = true;
						break;
					}
				}
				if(!ok)
					dp[indice][anterior] = 0;
			}
		}
		for(int i = 0; i < k; i++)
		{
			if(dp[1][i] == 1)
			{
				dp[0][0] = 1;
				siguiente[0][0] = (char) i;
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		int primeraLetra = 0;
		if(k > n)
		{
			return null;
		}
		if(dp[0][0] == 0)
			return null;
		else
		{
			int indiceActual = 0;
			int letraActual = primeraLetra;
			int letraMayor = letraActual;
			for(indiceActual = 1; indiceActual <= n; indiceActual++)
			{
				letraActual = siguiente[indiceActual - 1][letraActual];
				letraMayor = Math.max(letraActual, letraMayor);
				sb.append((char) (letraActual + 'a'));
			}
			if(letraMayor < k - 1)
			{
				int aPoner = k - letraMayor - 1;
				letraActual = letraMayor + 1;
				int posicionActual = n - aPoner;
				while(posicionActual != n)
				{
					sb.setCharAt(posicionActual, (char) (letraActual + 'a'));
					letraActual++;
					posicionActual++;
				}
			}
			return sb.toString();
		}
	}
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int k = sc.nextInt();
		String val = generar(n, k);
		if(val == null)
			System.out.println("-1");
		else
			System.out.println(val);
		
	}

}
