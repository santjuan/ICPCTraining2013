import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;


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

	static int n;
	static int m;
	

	
	static class Entrada implements Comparable <Entrada>
	{
		int[] intentos;

		public Entrada(int[] result) 
		{
			intentos = result;
		}

		@Override
		public int compareTo(Entrada o) 
		{
			for(int i = 0; i < intentos.length; i++)
				if(intentos[i] != o.intentos[i])
					return intentos[i] - o.intentos[i];
			return 0;
		}
	}
	
	static TreeMap <Entrada, Long> generar(int nBits, long[] intentos)
	{
		final int limite = 1 << nBits;
		final int mascara = limite - 1;
		TreeMap <Entrada, Long> resultado = new TreeMap <Entrada, Long> ();
		for(int i = 0; i < limite; i++)
		{
			int[] result = new int[m];
			for(int j = 0; j < m; j++)
				result[j] = Long.bitCount(~((intentos[j] & mascara) ^ i)) - (64 - nBits);
			Entrada nueva = new Entrada(result);
			if(!resultado.containsKey(nueva))
				resultado.put(nueva, 0L);
			resultado.put(nueva, resultado.get(nueva) + 1);
		}
		return resultado;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		n = sc.nextInt();
		m = sc.nextInt();
		int[] resultadosI = new int[m];
		long[] intentos = new long[m];
		for(int i = 0; i < m; i++)
		{
			intentos[i] = Long.parseLong(sc.next(), 2);
			resultadosI[i] = sc.nextInt();
		}
		int mitad = n >> 1;
		TreeMap <Entrada, Long> a = generar(mitad, intentos);
		for(int i = 0; i < m; i++)
			intentos[i] >>>= mitad;
		TreeMap <Entrada, Long> b = generar(n - mitad, intentos);
		long result = 0;
		for(Map.Entry<Entrada, Long> e : a.entrySet())
		{
			int[] complementos = new int[m];
			for(int i = 0; i < m; i++)
				complementos[i] = resultadosI[i] - e.getKey().intentos[i];
			Entrada complemento = new Entrada(complementos);
			if(b.containsKey(complemento))
				result += e.getValue() * b.get(complemento);
		}
		System.out.println(result);
	}
}