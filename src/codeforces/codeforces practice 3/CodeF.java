import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeF
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
	
	private static boolean valido(String[] escogidas) 
	{
		if(escogidas[0].length() == escogidas[1].length() + escogidas[2].length() - 1)
		{
			if(escogidas[4].length() == escogidas[3].length() + escogidas[5].length() - 1)
			{
				if(escogidas[1].charAt(0) == escogidas[3].charAt(0) && escogidas[3].charAt(escogidas[3].length() - 1) == escogidas[0].charAt(0))
				{
					if(escogidas[1].charAt(escogidas[1].length() - 1) == escogidas[4].charAt(0) && escogidas[0].charAt(escogidas[1].length() - 1) == escogidas[4].charAt(escogidas[3].length() - 1) && escogidas[2].charAt(0) == escogidas[4].charAt(escogidas[4].length() - 1))
					{
						if(escogidas[2].charAt(escogidas[2].length() - 1) == escogidas[5].charAt(escogidas[5].length() - 1) && escogidas[0].charAt(escogidas[0].length() - 1) == escogidas[5].charAt(0))
							return true;
							
					}
				}
			}
		}
		return false;
	}
	
	static void poner(char[][] matriz, int i, int j, String palabra, int diffI, int diffJ)
	{
		int iActual = i;
		int jActual = j;
		for(char c : palabra.toCharArray())
		{
			matriz[iActual][jActual] = c;
			iActual += diffI;
			jActual += diffJ;
		}
	}
	static char[][] poner(String[] escogidas)
	{
		char[][] matriz = new char[escogidas[4].length()][escogidas[0].length()];
		for(char[] c : matriz)
			Arrays.fill(c, '.');
		poner(matriz, escogidas[3].length() - 1, 0, escogidas[0], 0, 1);
		poner(matriz, 0, 0, escogidas[1], 0, 1);
		poner(matriz, escogidas[4].length() - 1, escogidas[1].length() - 1, escogidas[2], 0, 1);
		poner(matriz, 0, 0, escogidas[3], 1, 0);
		poner(matriz, 0, escogidas[1].length() - 1, escogidas[4], 1, 0);
		poner(matriz, escogidas[3].length() - 1, escogidas[0].length() - 1, escogidas[5], 1, 0);
		return matriz;
	}
	
	static char[][] mejor;
	static void generar(int indice, String[] palabras, boolean[] usadas, String[] escogidas)
	{
		if(indice == 6)
		{
			if(valido(escogidas))
			{
				char[][] posible = poner(escogidas);
				if(esMejor(posible, mejor))
					mejor = posible;
			}
		}
		else
		{
			for(int i = 0; i < palabras.length; i++)
			{
				if(!usadas[i])
				{
					usadas[i] = true;
					escogidas[indice] = palabras[i];
					generar(indice + 1, palabras, usadas, escogidas);
					usadas[i] = false;
				}
			}
		}
	}

	private static boolean esMejor(char[][] a, char[][] b) 
	{
		if(b == null)
			return true;
		for(int i = 0; i < Math.min(a.length, b.length); i++)
		{
			String x = new String(a[i]);
			String y = new String(b[i]);
			int res = x.compareTo(y);
			if(res != 0)
				return res < 0;
		}
		return a.length < b.length ? true : false;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner();
		String[] palabras = new String[6];
		for(int i = 0; i < 6; i++)
			palabras[i] = sc.next();
		generar(0, palabras, new boolean[6], new String[6]);
		if(mejor == null)
			System.out.println("Impossible");
		else
		{
			for(char[] c : mejor)
				System.out.println(Arrays.toString(c).replace(", ", "").replace("[", "").replace("]", ""));
			System.out.println();
		}
	}

}
